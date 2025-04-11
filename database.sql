-- Création de la base de données
CREATE DATABASE IF NOT EXISTS hop_in;
USE hop_in;

-- Table Utilisateur
CREATE TABLE IF NOT EXISTS utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    email VARCHAR(100) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(100) NOT NULL,
    date_naissance DATE,
    type_membre ENUM('AUCUN', 'REGULIER', 'SENIOR', 'ENFANT') DEFAULT 'AUCUN',
    role ENUM('CLIENT', 'ADMIN') NOT NULL DEFAULT 'CLIENT'
);

-- Table Attraction
CREATE TABLE IF NOT EXISTS attraction (
    id_attraction INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(6,2) NOT NULL,
    capacite_max INT NOT NULL DEFAULT 30
);

-- Table Creneau (pour horaires standards)
CREATE TABLE IF NOT EXISTS creneau (
    id_creneau INT AUTO_INCREMENT PRIMARY KEY,
    heure TIME NOT NULL
);

-- Table Reservation (version simplifiée)
CREATE TABLE IF NOT EXISTS reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT,         -- Référence à l'utilisateur connecté (NULL pour une réservation invité)
    mailUt VARCHAR(100),        -- Email, pour réservation d'invité
    id_attraction INT,          -- Référence à l'attraction réservée
    date_reservation DATE NOT NULL,
    id_creneau INT NOT NULL,    -- Référence au créneau horaire standard (obligatoire)
    nb_personnes INT NOT NULL,
    statut ENUM('CONFIRMEE', 'ANNULEE') DEFAULT 'CONFIRMEE',
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id),
    FOREIGN KEY (id_attraction) REFERENCES attraction(id_attraction),
    FOREIGN KEY (id_creneau) REFERENCES creneau(id_creneau)
);

-- Table Paiement
CREATE TABLE IF NOT EXISTS paiement (
    id_paiement INT AUTO_INCREMENT PRIMARY KEY,
    id_reservation INT,
    methode ENUM('CARTE', 'PAYPAL', 'ESPECES') NOT NULL,
    montant DECIMAL(8,2) NOT NULL,
    statut ENUM('SUCCES', 'ECHEC') NOT NULL,
    date_paiement DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_reservation) REFERENCES reservation(id_reservation)
);

CREATE TABLE IF NOT EXISTS reduction (
    id_reduc INT PRIMARY KEY NOT NULL,
    prc INT NOT NULL,
    date_deb DATE,
    date_fin DATE,
    age_min INT,
    age_max INT,
    nb_Reserv_Min INT
);

CREATE TABLE IF NOT EXISTS hist_reduc (
    id_paiement INT REFERENCES paiement(id_paiement),
    id_reduc INT REFERENCES reduction(id_reduc),
    PRIMARY KEY (id_paiement, id_reduc)
);reduc PRIMARY KEY REFERENCES reduction(id_reduc)

INSERT INTO attraction (nom, description, prix, capacite_max) VALUES
('Laser-Game', 'Jeu de tir au laser immersif.', 15.00, 20),
('Grand8 Express', 'Montagnes russes à grande vitesse.', 20.00, 25),
('Les buches de folie', 'Descente en bûches mouvementée.', 12.00, 18),
('La maison hantée', 'Parcours effrayant dans une maison hantée.', 10.00, 15),
('Opération Citrouille', 'Chasse aux citrouilles pour Halloween.', 8.00, 22);
