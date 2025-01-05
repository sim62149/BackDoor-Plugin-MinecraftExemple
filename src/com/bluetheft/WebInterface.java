package com.bluetheft; // WebInterface For Credibility

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class WebInterface {

    private static String loadHtmlTemplate() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Joueurs en ligne</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #121212;\n" +
                "            color: #e0e0e0;\n" +
                "        }\n" +
                "        .player-card {\n" +
                "            margin: 10px auto;\n" +
                "            padding: 15px;\n" +
                "            background: #1e1e1e;\n" +
                "            color: #fff;\n" +
                "            border-radius: 5px;\n" +
                "            max-width: 300px;\n" +
                "        }\n" +
                "        .player-card button {\n" +
                "            cursor: pointer;\n" +
                "            padding: 8px 12px;\n" +
                "            background: #007BFF;\n" +
                "            color: white;\n" +
                "            border: none;\n" +
                "            border-radius: 3px;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <script>\n" +
                "        function sendMessageToPlayer(playerName) {\n" +
                "            const message = prompt('Entrez le message à envoyer à ' + playerName + ':');\n" +
                "            if (message) {\n" +
                "                fetch('/sendMessage?player=' + encodeURIComponent(playerName) + '&message=' + encodeURIComponent(message), { method: 'POST' })\n" +
                "                    .then(response => {\n" +
                "                        if (response.ok) {\n" +
                "                            alert('Message envoyé avec succès à ' + playerName + ' !');\n" +
                "                        } else {\n" +
                "                            alert('Échec de l\\'envoi du message.');\n" +
                "                        }\n" +
                "                    })\n" +
                "                    .catch(error => {\n" +
                "                        console.error('Erreur réseau:', error);\n" +
                "                        alert('Erreur de connexion au serveur.');\n" +
                "                    });\n" +
                "            }\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Joueurs en ligne</h1>\n" +
                "<div id=\"players-list\">\n" +
                "    <!-- PLAYERS_PLACEHOLDER -->\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

    public static void addPlayersContext(HttpServer server) {
        if (server == null) {
            Bukkit.getLogger().severe("Le serveur HTTP n'est pas démarré !");
            return;
        }

        server.createContext("/players", (HttpExchange httpExchange) -> {
            try {
                String html = loadHtmlTemplate();
                String playersHtml = Bukkit.getOnlinePlayers().stream()
                        .map(player -> String.format(
                                "<div class='player-card'>" +
                                        "<h2>%s</h2>" +
                                        "<p>Vie: %.1f</p>" +
                                        "<p>Nourriture: %d</p>" +
                                        "<p>Position: X: %.1f, Y: %.1f, Z: %.1f</p>" +
                                        "<button onclick=\"sendMessageToPlayer('%s')\">Envoyer un message</button>" +
                                        "</div>",
                                player.getName(),
                                player.getHealth(),
                                player.getFoodLevel(),
                                player.getLocation().getX(),
                                player.getLocation().getY(),
                                player.getLocation().getZ(),
                                player.getName().replace("'", "\\'")
                        ))
                        .collect(Collectors.joining());

                html = html.replace("<!-- PLAYERS_PLACEHOLDER -->", playersHtml);
                byte[] responseBytes = html.getBytes(StandardCharsets.UTF_8);
                httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                httpExchange.sendResponseHeaders(200, responseBytes.length);

                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(responseBytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
                httpExchange.sendResponseHeaders(500, -1);
            }
        });

        server.createContext("/sendMessage", (HttpExchange httpExchange) -> {
            try {
                URI requestUri = httpExchange.getRequestURI();
                String query = requestUri.getQuery();
                Map<String, String> params = parseQuery(query);

                String playerName = params.get("player");
                String message = params.get("message");

                if (playerName != null && message != null) {
                    Player player = Bukkit.getPlayerExact(playerName);
                    if (player != null && player.isOnline()) {
                        player.sendMessage("[ADMIN] " + message);
                        httpExchange.sendResponseHeaders(200, -1);
                    } else {
                        httpExchange.sendResponseHeaders(404, -1);
                    }
                } else {
                    httpExchange.sendResponseHeaders(400, -1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                httpExchange.sendResponseHeaders(500, -1);
            }
        });

     //   Bukkit.getLogger().info("Contextes /players et /sendMessage ajoutés au serveur HTTP.");
    }

    private static Map<String, String> parseQuery(String query) {
        return query == null ? new java.util.HashMap<>() :
                java.util.Arrays.stream(query.split("&"))
                        .map(param -> param.split("=", 2))
                        .filter(pair -> pair.length == 2)
                        .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
    }
}
