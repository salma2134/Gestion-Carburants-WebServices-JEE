# Gestion des Carburants – Web Services JEE

## Description du projet

Ce projet consiste à développer une application de gestion des carburants en utilisant les technologies Java EE (Jakarta EE) et les Web Services.

L'application permet de gérer les stations-service, les carburants et l'historique des prix. Elle propose également plusieurs types de Web Services et différentes applications clientes permettant de communiquer avec le backend.

## Architecture du projet

Le dépôt contient quatre applications principales :

### 1. GestionCarburantsWS

**Backend principal – JAX-WS et JAX-RS**

Ce projet représente le backend principal de l'application.

Il assure :
- La gestion des stations-service (CRUD).
- La gestion des carburants (CRUD).
- La gestion de l'historique des prix.
- La consultation des prix des carburants selon le nom de la station et la date.
- L'exposition des fonctionnalités à travers les Web Services SOAP (JAX-WS) et REST (JAX-RS).
- La sécurisation des ressources REST avec JWT.

Technologies utilisées :
- Java 21
- Jakarta EE
- JAX-WS
- JAX-RS
- JPA / Hibernate
- MySQL
- Maven
- WildFly

### 2. GestionCarburantsClient

**Application Web cliente – Servlets et JSP**

Cette application représente l'interface Web permettant aux utilisateurs d'interagir avec le backend.

Elle permet :
- D'afficher, ajouter, modifier et supprimer des stations.
- D'afficher, ajouter, modifier et supprimer des carburants.
- De gérer les historiques des prix.
- De consulter les prix des carburants d'une station selon une date.
- D'appeler les opérations du Web Service SOAP.

Technologies utilisées :
- Java
- Jakarta Servlets
- JSP
- HTML / CSS
- Maven
- JAX-WS

### 3. GestionCarburantsJavaClient

**Client Java – JAX-RS**

Ce projet contient une application Java indépendante avec une méthode `main()`.

Son rôle est de :
- Se connecter au Web Service REST.
- Envoyer les paramètres nécessaires à la consultation des prix.
- Récupérer et afficher les résultats retournés par le backend.

Technologies utilisées :
- Java 21
- JAX-RS
- Java HTTP Client
- Maven

### 4. GestionCarburantsJersey

**Web Service REST indépendant – Jersey**

Ce projet représente une deuxième implémentation du service REST en utilisant Jersey.

Il permet :
- De consulter les prix des carburants.
- De rechercher les prix selon le nom de la station et la date.
- De communiquer avec la base de données MySQL.
- D'exposer les résultats sous forme de service REST.

Technologies utilisées :
- Java
- Jersey
- JAX-RS
- Grizzly
- JDBC
- MySQL
- Maven

## Base de données

Le projet utilise une base de données MySQL nommée `gestion_carburants`.

Elle contient principalement les tables :
- `station` : informations sur les stations-service.
- `carburant` : informations sur les types de carburants.
- `histocarb` : historique des prix des carburants.

## Sécurité

Une sécurisation par JWT (JSON Web Token) a été mise en place sur les ressources REST concernées.

Le mécanisme comprend :
- L'authentification de l'utilisateur.
- La génération d'un token JWT.
- La vérification du token lors des appels aux ressources REST protégées.
- Le refus des requêtes sans token valide.

## Installation et exécution

1. Cloner le dépôt GitHub.
2. Importer les quatre projets dans Eclipse en tant que projets Maven.
3. Configurer la connexion à la base de données MySQL.
4. Déployer `GestionCarburantsWS` et `GestionCarburantsClient` sur WildFly.
5. Démarrer le projet `GestionCarburantsJersey` pour lancer le service REST indépendant.
6. Exécuter `GestionCarburantsJavaClient` pour tester le client Java.

## Auteur

Projet académique réalisé dans le cadre du module **Web Services JEE**.

Université Abdelmalek Essaâdi  
Faculté des Sciences et Techniques de Tanger  
Département Génie Informatique

**Année universitaire : 2025–2026**
