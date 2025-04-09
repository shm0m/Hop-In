-- 🌐 Création de la base
CREATE DATABASE IF NOT EXISTS hop_in;
USE hop_in;

-- 👤 Table Utilisateur
CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    date_naissance DATE,
    type_membre ENUM('AUCUN', 'REGULIER', 'SENIOR', 'ENFANT') DEFAULT 'AUCUN',
    role ENUM('CLIENT', 'ADMIN') NOT NULL DEFAULT 'CLIENT'
);

-- 🎢 Table Attraction
CREATE TABLE attraction (
    id_attraction INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(6,2) NOT NULL,
    capacite_max INT NOT NULL DEFAULT 30
);

-- ⏰ Table Créneau (horaire unique)
CREATE TABLE creneau (
    id_creneau INT AUTO_INCREMENT PRIMARY KEY,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL
);

-- 📅 Table Réservation
CREATE TABLE reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id INT,  -- FK utilisateur
    id_attraction INT,
    id_creneau INT,
    date_reservation DATE NOT NULL,
    nb_personnes INT NOT NULL,
    statut ENUM('CONFIRMEE', 'ANNULEE') NOT NULL DEFAULT 'CONFIRMEE',
    FOREIGN KEY (id) REFERENCES utilisateur(id),
    FOREIGN KEY (id_attraction) REFERENCES attraction(id_attraction),
    FOREIGN KEY (id_creneau) REFERENCES creneau(id_creneau)
);

-- 💳 Table Paiement (liée à une réservation)
CREATE TABLE paiement (
    id_paiement INT AUTO_INCREMENT PRIMARY KEY,
    id_reservation INT,
    methode ENUM('CARTE', 'PAYPAL', 'ESPECES') NOT NULL,
    montant DECIMAL(8,2) NOT NULL,
    statut ENUM('SUCCES', 'ECHEC') NOT NULL,
    date_paiement DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation)
);
