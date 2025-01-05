package com.bluetheft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WebPanelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        // Vérifie si l'expéditeur est un joueur
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;  // Retourne vrai pour indiquer que la commande a été traitée
        }

        Player player = (Player) commandSender;  // Cast du CommandSender en Player
        String playerName = player.getName();  // Récupère le nom du joueur

        // Envoie l'adresse du panel Web à l'utilisateur
        player.sendMessage("Adresse du panel Web : 127.0.0.1:30000/players");

        return true;  // Retourne vrai pour indiquer que la commande a été traitée correctement
    }
}

