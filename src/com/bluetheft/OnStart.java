package com.bluetheft;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class OnStart {
   private final Main plugin;

   public OnStart(Main plugin) {
      this.plugin = plugin;
   }

   public void initialize() {
      File configFile = new File(this.plugin.getDataFolder(), "config.yml");
      if (!configFile.exists()) {
         this.plugin.getLogger().info("Création du fichier config.yml...");
         this.createConfigFile(configFile);
      } else {
         this.plugin.getLogger().info("Le fichier config.yml existe déjà.");
      }

   }
// Fake Config File
   private void createConfigFile(File file) {
      try {
         if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdirs();
         }

         FileConfiguration config = new YamlConfiguration();
         config.createSection("SERVER");
         config.set("SERVER.Name", "BlueTheft Server");
         config.set("SERVER.MOTD", "Bienvenue sur le serveur !");
         config.set("SERVER.Max-Players", 100);
         config.set("SERVER.Difficulty", "HARD");
         config.set("SERVER.Whitelist", true);
         config.set("SERVER.IP-Address", "play.bluetheft.net");
         config.createSection("ROLES");
         config.set("ROLES.Admins", new String[]{"PseudoAdmin1", "PseudoAdmin2"});
         config.set("ROLES.Moderators", new String[]{"PseudoMod1", "PseudoMod2"});
         config.set("ROLES.Helpers", new String[]{"PseudoHelper1", "PseudoHelper2"});
         config.set("ROLES.Builders", new String[]{"PseudoBuilder1", "PseudoBuilder2"});
         config.createSection("WORLD");
         config.set("WORLD.Spawn.X", 0);
         config.set("WORLD.Spawn.Y", 64);
         config.set("WORLD.Spawn.Z", 0);
         config.set("WORLD.Spawn.World", "world");
         config.set("WORLD.Nether-Enabled", true);
         config.set("WORLD.End-Enabled", true);
         config.set("WORLD.Weather.Allow-Thunder", false);
         config.set("WORLD.Weather.Allow-Rain", true);
         config.set("WORLD.TimeLock.Enabled", true);
         config.set("WORLD.TimeLock.Time", "day");
         config.createSection("PLUGINS");
         config.set("PLUGINS.Essentials.Enabled", true);
         config.set("PLUGINS.Essentials.Warp-Locations.Spawn.X", 0);
         config.set("PLUGINS.Essentials.Warp-Locations.Spawn.Y", 64);
         config.set("PLUGINS.Essentials.Warp-Locations.Spawn.Z", 0);
         config.set("PLUGINS.Essentials.Warp-Locations.Spawn.World", "world");
         config.set("PLUGINS.Essentials.Warp-Locations.Shop.X", 100);
         config.set("PLUGINS.Essentials.Warp-Locations.Shop.Y", 64);
         config.set("PLUGINS.Essentials.Warp-Locations.Shop.Z", -50);
         config.set("PLUGINS.Essentials.Warp-Locations.Shop.World", "world");
         config.set("PLUGINS.WorldGuard.Enabled", true);
         config.set("PLUGINS.WorldGuard.Regions.Spawn.Flags.Build", false);
         config.set("PLUGINS.WorldGuard.Regions.Spawn.Flags.PVP", false);
         config.set("PLUGINS.WorldGuard.Regions.Arena.Flags.Build", true);
         config.set("PLUGINS.WorldGuard.Regions.Arena.Flags.PVP", true);
         config.createSection("ECONOMY");
         config.set("ECONOMY.Currency", "Émeraudes");
         config.set("ECONOMY.Starting-Balance", 100);
         config.set("ECONOMY.Max-Balance", 1000000);
         config.set("ECONOMY.Shops.Enabled", true);
         config.set("ECONOMY.Shops.Shop-Locations", new String[]{"world,100,65,-100", "world,-200,70,50"});
         config.createSection("ANTICHEAT");
         config.set("ANTICHEAT.Enabled", true);
         config.set("ANTICHEAT.Checks.Fly", true);
         config.set("ANTICHEAT.Checks.Speed", true);
         config.set("ANTICHEAT.Checks.XRay", false);
         config.set("ANTICHEAT.Checks.KillAura", true);
         config.set("ANTICHEAT.Kick-Threshold", 5);
         config.createSection("MINIGAMES");
         config.set("MINIGAMES.Enabled", true);
         config.set("MINIGAMES.Games.Spleef.Enabled", true);
         config.set("MINIGAMES.Games.Spleef.Arena-Location.X", 200);
         config.set("MINIGAMES.Games.Spleef.Arena-Location.Y", 64);
         config.set("MINIGAMES.Games.Spleef.Arena-Location.Z", -200);
         config.set("MINIGAMES.Games.Spleef.Arena-Location.World", "minigames");
         config.set("MINIGAMES.Games.BedWars.Enabled", true);
         config.set("MINIGAMES.Games.BedWars.Teams", new String[]{"Red", "Blue", "Green", "Yellow"});
         config.set("MINIGAMES.Games.Parkour.Enabled", true);
         config.set("MINIGAMES.Games.Parkour.Checkpoints", new String[]{"world,50,70,50", "world,100,75,100", "world,150,80,150"});
         config.createSection("MESSAGES");
         config.set("MESSAGES.Join", "&aBienvenue sur le serveur, %player% !");
         config.set("MESSAGES.Quit", "&c%player% a quitté le serveur.");
         config.set("MESSAGES.Death", "&e%player% est mort à cause de %reason%.");
         config.createSection("SAVE");
         config.set("SAVE.Autosave-Interval", 10);
         config.set("SAVE.Backup.Enabled", true);
         config.set("SAVE.Backup.Interval", 60);
         config.set("SAVE.Backup.Backup-Location", "backups/");
         config.createSection("ADVANCED");
         config.set("ADVANCED.Debug-Mode", false);
         config.set("ADVANCED.Metrics-Enabled", true);
         config.set("ADVANCED.Language", "fr");
         config.createSection("ITEM-BLACKLIST");
         config.set("ITEM-BLACKLIST.Enabled", true);
         config.set("ITEM-BLACKLIST.Blacklisted-Items", new String[]{"TNT", "Bedrock", "Ender_Pearl"});
         config.createSection("EVENTS");
         config.set("EVENTS.Holiday-Events.Christmas.Enabled", true);
         config.set("EVENTS.Holiday-Events.Christmas.Start-Date", "2025-12-20");
         config.set("EVENTS.Holiday-Events.Christmas.End-Date", "2025-12-31");
         config.set("EVENTS.Holiday-Events.Christmas.Gifts", new String[]{"Diamond:5", "Gold_Ingot:10", "Emerald:2"});
         config.set("EVENTS.Holiday-Events.Halloween.Enabled", true);
         config.set("EVENTS.Holiday-Events.Halloween.Start-Date", "2025-10-25");
         config.set("EVENTS.Holiday-Events.Halloween.End-Date", "2025-10-31");
         config.set("EVENTS.Holiday-Events.Halloween.Special-Mobs.Pumpkin-Zombie", true);
         config.set("EVENTS.Holiday-Events.Halloween.Special-Mobs.Skeleton-King", true);
         config.save(file);
         this.plugin.getLogger().info("Fichier config.yml créé avec succès !");
      } catch (IOException var3) {
         this.plugin.getLogger().severe("Erreur lors de la création de config.yml : " + var3.getMessage());
      }

   }
}
