# Diabetes Microservices

Ce projet est une solution en microservices pour le dépistage du diabète, structuré en 5 microservices :

- **patient-service** : Gère les patients (MySQL)
- **gateway-service** : Route les requêtes via Spring Cloud Gateway
- **front-service** : Interface utilisateur (Thymeleaf)
- **notes-service** : Gestion des notes médicales (MongoDB)
- **risk-service** : Calcul du risque diabète

## Structure du projet

