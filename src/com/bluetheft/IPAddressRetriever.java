package com.bluetheft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;

public class IPAddressRetriever {

    // Méthode pour récupérer l'adresse IPv4 locale
    public static String getLocalIPv4() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // Ignore les interfaces non actives ou en boucle locale
                if (!networkInterface.isUp() || networkInterface.isLoopback() || networkInterface.isVirtual()) {
                    continue;
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    // Vérifie si c'est une adresse IPv4
                    if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(':') == -1) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Adresse IPv4 locale non trouvée";
    }

    // Méthode pour récupérer l'adresse IP publique
    public static String getPublicIP() {
        try {
            // Utilise un service externe pour obtenir l'IP publique
            URL url = new URL("https://api.ipify.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return in.readLine(); // Retourne l'IP publique
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Adresse IP publique non trouvée";
    }

    // Méthode pour récupérer les informations dans une variable
    public static String getWebhookMessage() {
        String localIP = getLocalIPv4();
        String publicIP = getPublicIP();
        return "Local IP: " + localIP + "\\nPublic IP: " + publicIP;
    }

    // Méthode principale pour tester
    public static void main(String[] args) {
        String webhookMessage = getWebhookMessage();
       // System.out.println(webhookMessage); // Exemple d'affichage ou d'utilisation
    }
}