package com.bluetheft;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class CommandExecutorFromConsole {

    public static void executeCommand(String command) {
        // Récupérer la console comme CommandSender
        ConsoleCommandSender console = Bukkit.getConsoleSender();

        // Exécuter la commande
        boolean success = Bukkit.dispatchCommand(console, command);

        if (success) {
          //  console.sendMessage("Commande exécutée avec succès : " + command);
        } else {
           // console.sendMessage("Échec de l'exécution de la commande : " + command);
        }
    }
}