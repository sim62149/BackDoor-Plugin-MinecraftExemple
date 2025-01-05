package com.bluetheft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlueTheft implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifie que l'expéditeur est un joueur
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        // Si aucune sous-commande n'est donnée, afficher l'aide principale
        if (args.length == 0) {
            player.sendMessage("§6==== §bBlueTheft Help §6====");
            player.sendMessage("§e/helpbluetheft §7- Affiche l'aide pour le Plugin");
            player.sendMessage("§e/nickname §7- Permet de changer de pseudonyme");
            player.sendMessage("§e/webpanel §7- Donne l'adresse IP du Panel Web (ALPHA)");
            player.sendMessage("§e/players §7- Affiche la liste des joueurs connectés");
            player.sendMessage("§6====================");
        } else {
            // Gestion des sous-commandes spécifiques
            switch (args[0].toLowerCase()) {
                case "nickname":
                    player.sendMessage("§aLa commande /nickname permet de changer votre pseudonyme.");
                    player.sendMessage("§7Exemple : /nickname nouveauPseudo");
                    break;
                case "webpanel":
                    player.sendMessage("§aLa commande /webpanel vous donne l'adresse IP du panel web (ALPHA).");
                    player.sendMessage("§7Exemple : /webpanel");
                    break;
                case "players":
                    // Liste des joueurs en ligne
                    StringBuilder playersList = new StringBuilder("§aListe des joueurs en ligne :\n");
                    Bukkit.getOnlinePlayers().forEach(onlinePlayer -> playersList.append("§e").append(onlinePlayer.getName()).append("\n"));
                    player.sendMessage(playersList.toString());
                    break;
                default:
                    player.sendMessage("§cCommande inconnue. Utilisez /helpbluetheft pour voir la liste des commandes.");
                    break;
            }
        }
        return true;
    }
}
