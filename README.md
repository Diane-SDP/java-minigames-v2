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

- [Le Plus ou Moins](#le-plus-ou-moins)
- [Le Vrai ou Faux](#le-vrai-ou-faux)
- [Le Pendu](#le-pendu)
- [Le Memory](#le-memory)
- [Le Snake](#le-snake)
- [Le Pacman](#le-pacman)
- [Le Flappy Bird](#le-flappy-bird)
- [Le Blackjack](#le-blackjack)
- [Le 2048](#le-2048)
- [Le Sudoku](#le-sudoku)
- [Le Jeu d'échec](#le-jeu-déchec)

## Comment lancer le projet

Ce projet nécessite le setup de MySQL, et l'installation de Javasound.

- Assurez-vous d'avoir Java installé sur votre système.
- Clonez ce dépôt Git ou téléchargez-le sous forme de fichier zip.
- Executez cette commande à la racine du projet :

```bash
java -jar minigames.jar
```

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

### Le Plus ou Moins

Ce jeu consiste à deviner un nombre (entre 0 et 1000) choisi aléatoirement par l'ordinateur. Le joueur propose un nombre, puis l'ordinateur lui indique si le nombre à deviner est plus grand ou plus petit que celui proposé par le joueur. Le jeu continue jusqu'à ce que le joueur devine correctement le nombre.

### Le Vrai ou Faux

Dans ce jeu, une affirmation est présentée au joueur et celui-ci doit deviner si elle est vraie ou fausse. Le joueur gagne un point s'il répond correctement et perd un point s'il répond incorrectement.

### Le Pendu

Le Pendu est un jeu de devinette où le joueur doit deviner un mot en suggérant des lettres. Chaque lettre incorrecte entraîne l'ajout d'une partie du corps du pendu. Le joueur gagne s'il devine le mot avant que le pendu ne soit complet.

### Le Memory

Dans ce jeu, le joueur est présenté avec un plateau de cartes face cachée. Le joueur retourne deux cartes à la fois pour trouver des paires correspondantes. Le jeu continue jusqu'à ce que toutes les paires soient trouvées.

### Le Snake

Le Snake est un jeu où le joueur contrôle un train qui se déplace à travers un espace en mangeant des larmes de lumière et en évitant les obstacles. Le train grandit à chaque fois qu'il mange une larme de lumière et le jeu se termine si le train entre en collision avec lui-même ou les bords de l'espace de jeu.

### Le Pacman

Pacman est un jeu classique où le joueur contrôle un personnage qui doit manger touts les rubis à l'intérieur d'un labyrinthe tout en évitant les bokoblins. Le joueur peut également récupérer des master-sword pour rendre les bokoblins vulnérables.

### Le Flappy Bird

Flappy Bird est un jeu où le joueur contrôle une peste mojo qui doit voler à travers une forêt en évitant de toucher les arbres. Le joueur appuie sur la barre d'espace pour faire voler la peste mojo, et le jeu se termine si il entre en collision avec un arbre.

### Le Blackjack

Blackjack est un jeu de cartes où le joueur essaie de battre le croupier en obtenant une main dont la valeur est la plus proche possible de 21 sans la dépasser. Le joueur reçoit deux cartes initiales et peut choisir de rester avec sa main actuelle ou de tirer des cartes supplémentaires.

### Le 2048

2048 est un jeu de puzzle où le joueur combine des tuiles numérotées pour créer une tuile de valeur plus élevée. Le joueur déplace les tuiles sur un tableau en utilisant les touches fléchées et le jeu se termine lorsque le tableau est rempli et qu'il n'y a plus de mouvements possibles.

### Le Sudoku

Le Sudoku est un jeu de puzzle numérique où le joueur remplit un tableau 9x9 avec des chiffres de sorte que chaque ligne, chaque colonne et chaque région de 3x3 contienne tous les chiffres de 1 à 9 sans répétition.

### Le Jeu d'échec

Le Jeu d'échec est un jeu de stratégie où deux joueurs s'affrontent sur un plateau 8x8 avec plusieurs pièces qui se dépkacent chacune différement. Le but du jeu est de mettre en échec le roi adverse tout en protégeant son propre roi.

## Auteurs

Projet réalisé par Maxime CHORT, Aymeric MAREC et Diane SAUTEREAU DU PART  
*En cas de bugs ou de suggestions, contactez nous !*
