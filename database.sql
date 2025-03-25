-- Création de la base de données
CREATE DATABASE IF NOT EXISTS hop_in;
USE hop_in;

-- Table Utilisateur
CREATE TABLE Utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    date_naissance DATE,
    type_membre ENUM('AUCUN', 'REGULIER', 'SENIOR', 'ENFANT') DEFAULT 'AUCUN',
    role ENUM('CLIENT', 'ADMIN') NOT NULL
);

-- Table Attraction
CREATE TABLE Attraction (
    id_attraction INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(6,2) NOT NULL
);

-- Table Reservation
CREATE TABLE Reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    id_attraction INT,
    date_reservation DATE NOT NULL,
    nb_personnes INT NOT NULL,
    reduction_appliquee DECIMAL(4,2) NOT NULL,
    prix_total DECIMAL(8,2) NOT NULL,
    statut ENUM('CONFIRMEE', 'ANNULEE'),
    FOREIGN KEY (id) REFERENCES Utilisateur(id),
    FOREIGN KEY (id_attraction) REFERENCES Attraction(id_attraction)
);

-- Table ReductionUtilisateur
CREATE TABLE ReductionUtilisateur (
    id_reduction_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    nom VARCHAR(100) NOT NULL,
    pourcentage DECIMAL(4,2) NOT NULL,
    membre_necessaire BOOLEAN,
    FOREIGN KEY (id) REFERENCES Utilisateur(id)
);
