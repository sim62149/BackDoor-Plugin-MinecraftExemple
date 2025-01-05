package com.bluetheft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        // Vérifie si l'expéditeur est un joueur
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;  // Retourne vrai pour indiquer que la commande a été traitée
        }

        Player player = (Player) commandSender;  // Cast du CommandSender en Player

        // Récupère la liste des joueurs en ligne
        StringBuilder playersList = new StringBuilder("Joueurs en ligne :\n");

        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            playersList.append(onlinePlayer.getName()).append("\n");
        });

        // Envoie la liste des joueurs à l'expéditeur
        player.sendMessage(playersList.toString());

        return true;  // Retourne vrai pour indiquer que la commande a été traitée correctement
    }
}