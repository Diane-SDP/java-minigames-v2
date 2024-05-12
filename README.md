# The Legend of Babar

**"The Legend of Babar"** est une application Java qui regroupe de nombreux mini-jeux, le tout avec une interface graphique réalisé avec Swing inspirée par le célèbre jeu "The Legend of Zelda".

## Sommaire

1. [Fonctionnalités](#fonctionnalités)
2. [Comment lancer le projet](#comment-lancer-le-projet)
    - [Installation de MySQL](#installation-de-mysql)
    - [Installation de JavaSound](#installation-de-javasound)
3. [Utilisation](#utilisation)
4. [Les auteurs](#auteurs)

## Fonctionnalités

Dans le menu, 11 jeux sont mis à disposition :

- Le Plus ou Moins
- Le Vrai ou Faux
- Le Pendu
- Le Memory
- Le Snake
- Le Pacman
- Le Flappy Bird
- Le Blackjack
- Le 2048
- Le Sudoku
- Le Jeu d'échec

## Comment lancer le projet

Ce projet nécessite le setup de MySQL, et l'installation de Javasound.

- Assurez-vous d'avoir Java installé sur votre système.
- Clonez ce dépôt Git ou téléchargez-le sous forme de fichier zip.
- Compilez les fichiers source Java à l'aide de votre environnement de développement préféré.
- Exécutez l'application en lançant la classe principale SelectionWindow.java

## Installation de MySQL

Pour installer **MySQL**, veuillez suivre les étapes suivantes :

1. Téléchargez MySQL depuis [ce lien](https://dev.mysql.com/downloads/installer/).

2. Cliquez sur "Download" et ouvrez le fichier téléchargé.

----------------------------

**Si MySQL server 8.0 n'est pas déjà téléchargé**

- Cliquez sur "Add".
- Dans **Select Product**, choisissez MySQL Server 8.0 et appuyez sur "Next", puis "Execute".
- Laissez la configuration par défaut. Choisissez un mot de passe pour root, puis créez un nouvel utilisateur avec les informations suivantes :
  - **Pseudo** : Diane
  - **Mot de passe** : J5T_/pg/G##u9~g  
   (Si vous souhaitez changer les identifiants, il faudra les modifier également dans le fichier main.go).
- Finalisez l'installation puis quittez l'installateur.

------------------------

3. Cliquez sur "Add"
7. Dans un terminal, déplacez-vous dans le dossier `C:\Program Files\MySQL\MySQL Server 8.0\bin`.
8. Tapez la commande suivante : `./mysql -u Diane -p` et entrez le mot de passe.
9. Une fois connecté, créez la base de données avec la commande : `CREATE DATABASE minigames;`.
10. Téléchargez le driver JDBC depuis [ce lien](https://dbschema.com/jdbc-driver/mysql.html)
11. A la racine du repo, créez un dossier "lib" et ajouter le fichier .jar dedans.
12. C'est tout !  
 4. Dans **Select Product**, choisissez Connector/J (la dernière version) et appuyez sur "Next", puis "Execute".

  *En cas de problème ou de difficultés, contactez Aymeric Marec, Diane Sautereau ou Maxime Chort !*

## Installation de JavaSound

Javasound est une bibliothèque que l'on utilise, son installation peut être nécessaire au bon fonctionnement du code.

Pour installer **JavaSound**, veuillez suivre les étapes suivantes :

**ATTENTION**  
Le setup JavaSound doit etre executé en tant qu'administrateur, car il necessite d'avoir accès Programme Files.

Lancer le Setup_JavaSound.bat **en tant qu'administrateur** situé à la racine du projet pour l'installer automatiquement

Si le fichier ne fonctionne pas, vous pouvez suivre les étapes suivantes :

1. Téléchargez JavaSound depuis [ce lien]("https://www.oracle.com/technetwork/java/soundbank-mid-149984.zip").

2. Décompressez le fichier téléchargé.

3. Copiez le fichier "soundbank-mid.gm" dans le dossier "Program Files/Java/(version_de_votre_java/lib/audio)" de votre ordinateur.
*(Si le dossier "audio" n'existe pas, créez-le.)*  

*En cas de problème ou de difficultés, contactez Aymeric Marec, Diane Sautereau ou Maxime Chort !*

## Utilisation

Une fois le projet lancé, vous pourrez choisir votre mini-jeu parmi ceux proposés sur le menu, et suivre les instructions de chacuns d'entre eux.
**REMARQUE**
Ce projet n'est executable QUE si vous avez une connexion internet, les temps de chargements peuvent être impactés par votre débit.

## Auteurs

Projet réalisé par Maxime CHORT, Aymeric MAREC et Diane SAUTEREAU DU PART  
*En cas de bugs ou de suggestions, contactez nous !*
