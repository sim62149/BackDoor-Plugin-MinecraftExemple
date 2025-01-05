package com.bluetheft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NicknameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifie que l'expéditeur est un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return false;
        }

        Player player = (Player) sender;

        // Vérifie qu'un pseudo a été fourni en argument
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Veuillez spécifier un pseudo. Utilisation : /nickname <nouveau_pseudo>");
            return false;
        }

        // Récupère le nouveau pseudo
        String newNickname = args[0];

        // Vérifie la longueur du pseudo (par exemple, entre 3 et 16 caractères)
        if (newNickname.length() < 3 || newNickname.length() > 16) {
            player.sendMessage(ChatColor.RED + "Le pseudo doit comporter entre 3 et 16 caractères.");
            return false;
        }

        // Applique le nouveau pseudo au joueur
        player.setDisplayName(newNickname);
        player.setPlayerListName(newNickname);

        // Message de confirmation
        player.sendMessage(ChatColor.GREEN + "Votre pseudo a été changé en : " + ChatColor.GOLD + newNickname);

        // Diffuse un message aux autres joueurs (optionnel)
        Bukkit.broadcastMessage(ChatColor.AQUA + player.getName() + " a changé son pseudo en " + ChatColor.GOLD + newNickname);

        return true;
    }
}
