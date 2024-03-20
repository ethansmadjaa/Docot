# ECE RDV Médical : Projet Java ING3 2024

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![gitmoji](https://img.shields.io/badge/gitmoji-%F0%9F%98%9C-yellow.svg?style=for-the-badge&logo=gitmoji&logoColor=yellow)

## 📖 Introduction

ECE RDV Médical est un projet Java pour la gestion des rendez-vous médicaux. 
Il permet aux utilisateurs (patients et employés) de naviguer et de gérer des rendez-vous avec des spécialistes de 
santé. Ce système aide également les gestionnaires de soins de santé à conserver des enregistrements pour les 
réservations et les clients.

## 📚 Table of Contents

- [Introduction](#-introduction)
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [Gitmoji guide](#-gitmoji-guide)
- [Contributing](#-contributing)


# ✨ Features

- **Gestion des Rendez-vous** : Permet aux patients de réserver des rendez-vous avec des médecins spécialistes.
- **Interface Graphique Utilisateur (GUI)** : Interface claire et intuitive pour les clients et les employés.
- **Gestion des Utilisateurs** : Nouveaux patients peuvent s'inscrire et les anciens utilisateurs peuvent utiliser 
leurs connexions existantes.
- **Base de Données** : Conserve les enregistrements de réservations et les détails des clients.
- **Reporting** : Génère des rapports statistiques sur les rendez-vous et les opérations.

# 🛠 Installation

Pour commencer avec le projet ECE RDV Médical, suivez ces étapes :

1. Clonez le dépôt :
   ```sh
   git clone https://github.com/ethansmadjaa/DoctoLibECE.git

2. Assurez-vous que Java JDK est installé et correctement configuré sur votre machine.

3. Importez les dépendances nécessaires à votre projet, par exemple, MySQL Connector pour la gestion de la base de 
données et JFreeChart pour la génération de rapports sous forme de graphiques.

4. Configurez la connexion à la base de données en modifiant le fichier de 
configuration avec vos propres paramètres de connexion.

# 🚀 Usage

Pour lancer l'application ECE RDV Médical, procédez comme suit :

1. Lancez votre serveur MySQL et créez la base de données en exécutant le script SQL fourni avec le projet.

2. Ouvrez le projet dans votre IDE et exécutez-le. Si un fichier exécutable `.jar` est fourni, vous pouvez 
également exécuter l'application en utilisant la commande :
   ```sh
   java -jar ECE-RDV-Medical.jar

# 😃 Gitmoji Guide

Incorporating gitmojis into your commit messages can make them more engaging 
and easier to understand at a glance. Here's a quick guide to using emojis in your commits:

- 🎉 `:tada:` for initial commits.
- ✨ `:sparkles:` when introducing new features.
- 🐛 `:bug:` when fixing a bug.
- 📝 `:memo:` for updates to documentation.
- 💄 `:lipstick:` for cosmetic changes to the UI.
- 🔧 `:wrench:` when changing configuration files.
- 🚀 `:rocket:` when improving performance.
- 🚧 `:construction:` for work in progress.
- 🗑️ `:wastebasket:` when removing code or files.

To add a gitmoji to your commit message, simply start the message with the corresponding code. For example:

```bash
git commit -m ":sparkles: Add search functionality"
```
This approach helps to visually categorize commit types, making your project's history more readable and fun!

For a comprehensive list of gitmojis and their meanings, visit [Gitmoji](https://gitmoji.dev).

# 👨‍💻 Contributing

1. Verifiez le nom des dernieres branches

2. Apartir de la branche Main, Créez votre branche de fonctionnalité 
```sh
git checkout -b doc-n
 ```

3. Committez vos changements 
```sh
git commit -m ':emoji: Feature implemented'
```

4. Push vers la branche 
```sh
git push origin doc-n
```

5. Ouvrez une Pull Request, depuis [github](https://github.com/ethansmadjaa/DoctoLibECE.git)

# ✍️ Auteurs du projet
### [Ethan SMADJA](mailto:Ethan.smadja@edu.ece.fr)
### [Pierre LOUIS](mailto:pierre.louis@edu.ece.fr)
### [Tenzin CHOEPHEL TSERING](tenzin.choepheltsering@edu.ece.fr)