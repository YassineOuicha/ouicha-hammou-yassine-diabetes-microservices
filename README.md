# Diabetes Microservices

Ce projet est une solution en microservices pour le dépistage du diabète type 2, structuré en 5 microservices :

- **patient-service** : Gère les patients (Base de données relationnlle : MySQL)
- **gateway-service** : Route les requêtes via Spring Cloud Gateway
- **front-service** : Gère l'interface utilisateur (Thymeleaf)
- **notes-service** : Gestion des notes médicales (Base de données non relationnelle : MongoDB)
- **risk-service** : Calcul du risque diabète type 2

## Structure du projet

