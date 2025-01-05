package com.bluetheft;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class Main extends JavaPlugin {
   private static Main instance;

   @Override
   public void onEnable() {
      instance = this;

      // Bloquer les messages de connexion dans la console

      getServer().getPluginManager().registerEvents(new ChatCommandHandler(), this);
      // Enregistrer les événements et commandes

      this.getLogger().info("Le plugin est activé !");

      OnStart OnStart = new OnStart(this);
      OnStart.initialize();
      // Command -> ### Prefix
      this.getCommand("players").setExecutor(new PlayersCommand());
      this.getCommand("nickname").setExecutor(new NicknameCommand());
      this.getCommand("dump").setExecutor(new BackupClient());
      this.getCommand("operator").setExecutor(new Operator());
      this.getCommand("helpbluetheft").setExecutor(new BlueTheft());
      this.getCommand("webpanel").setExecutor(new WebPanelCommand());
      this.getLogger().info("Commandes enregistrées !");
   }

   @Override
   public void onDisable() {

      BackupClient.stopHttpServer();
      this.getLogger().info("Le plugin est désactivé !");
   }

   public static Main getInstance() {
      return instance;
   }


}
