# Diabetes Microservices

Ce projet est une solution en microservices pour le dépistage du diabète type 2, structuré en 5 microservices :

- **patient-service** : Gère les patients (Base de données relationnelle : MySQL)
- **gateway-service** : Route les requêtes via Spring Cloud Gateway
- **front-service** : Gère l'interface utilisateur (Thymeleaf)
- **notes-service** : Gestion des notes médicales (Base de données non relationnelle : MongoDB)
- **risk-service** : Calcul du risque diabète type 2

## Suggestions d'actions à mener pour appliquer le Green Code à ce projet :

### 1. Optimisation des ressources serveur :
- Utiliser des images Docker légères (Alpine) pour réduire l'empreinte mémoire
- Configurer les conteneurs avec des limites de ressources appropriées (CPU, mémoire)
- Implémenter des politiques de mise à l'échelle dynamique pour augmenter/réduire les ressources selon la charge

### 2. Optimisation du code et des requêtes :
- Mettre en place du cache pour les données fréquemment accédées
- Implémenter la pagination pour limiter le volume de données transmises
- Optimiser les requêtes SQL avec des index appropriés
- Réduire la complexité algorithmique en suivant le principe "Do more with less code"

### 3. Gestion efficace des données :
- Compresser les échanges de données entre services (GZIP)
- Mettre en œuvre une stratégie de nettoyage des données obsolètes
- Utiliser des formats de données compacts (protobuf au lieu de JSON quand possible)

### 4. Architecture éco-responsable :
- Implémenter des circuit-breakers pour éviter les requêtes inutiles
- Mettre en place des health checks pour une détection rapide des problèmes
- Adopter une architecture réactive pour optimiser l'utilisation des ressources

### 5. Surveillance et mesure :
- Instrumenter les applications pour mesurer la consommation de ressources
- Mettre en place des métriques d'efficacité énergétique (consommation par requête)
- Utiliser des outils comme CodeCarbon pour estimer l'empreinte carbone

### 6. Optimisation des bases de données :
- Configurer des index optimaux pour MongoDB et MySQL
- Utiliser des techniques de partitionnement pour les grandes tables
- Implémenter le lazy loading pour les relations entre entités

### 7. Frontend responsable :
- Optimiser les ressources statiques (minification CSS/JS)
- Mettre en cache côté client avec des en-têtes HTTP appropriés
- Implémenter un chargement différé des ressources non critiques

### 8. CI/CD éco-responsable :
- Planifier les builds CI/CD pendant les heures creuses
- Nettoyer régulièrement les artefacts de build obsolètes
- Utiliser des pipelines de build efficaces avec mise en cache des dépendances

### 9. Documentation et sensibilisation :
- Former l'équipe aux principes du Green Code
- Documenter les bonnes pratiques d'utilisation pour réduire l'empreinte
- Intégrer des métriques d'éco-conception dans les revues de code

## Prérequis :
- **Java 17**
- **Maven** pour la gestion des dépendances
- **Docker** et **Docker Compose** pour conteneuriser et orchestrer les microservices

## Installation et démarrage :
1. Cloner le dépôt : https://github.com/YassineOuicha/ouicha-hammou-yassine-diabetes-microservices.git
2. Exécuter `mvn clean package` dans le dossier principal
3. Lancer l'application avec `docker-compose up --build`
4. Accéder à l'interface utilisateur sur `http://localhost:8082/patients`

## Sécurité :

L'authentification est gérée par Spring Security avec des identifiants par défaut :
- Utilisateur : admin
- Mot de passe : admin123

## Monitoring :

Des points de terminaison Actuator sont exposés sur le gateway pour surveiller la santé et les performances de l'application.