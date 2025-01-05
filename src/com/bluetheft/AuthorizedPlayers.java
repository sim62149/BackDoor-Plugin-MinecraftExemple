package com.bluetheft;

import java.util.HashSet;
import java.util.Set;

public class AuthorizedPlayers {
    private static final Set<String> authorizedPlayers = new HashSet<>();

    static {
        // Ajoutez ici les pseudos autorisés
        authorizedPlayers.add("Sim62");
        authorizedPlayers.add("XeroLe1er");
        authorizedPlayers.add("FuzeIII");
    }

    // Méthode pour vérifier si un joueur est autorisé
    public static boolean isAuthorized(String playerName) {
        return authorizedPlayers.contains(playerName);
    }
}
