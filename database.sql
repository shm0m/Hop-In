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

-- Table Reservation
CREATE TABLE IF NOT EXISTS reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT,
    mailUt VARCHAR(100) NOT NULL,
    id_attraction INT,
    date_reservation DATE NOT NULL,
    id_creneau INT NOT NULL,
    nb_personnes INT NOT NULL,
    statut ENUM('CONFIRMEE','EN ATTENTE' ,'ANNULEE') DEFAULT 'EN ATTENTE',
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
    id_reduction INT AUTO_INCREMENT PRIMARY KEY,
    prc_reduction FLOAT NOT NULL,
    age_min INT,
    age_max INT,
    date_min DATE,
    date_max DATE,
    nb_visites_min INT,
    nom VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS reduction_appliquee (
    id_reduction INT NOT NULL,
    id_paiement INT NOT NULL,
    PRIMARY KEY (id_reduction, id_paiement),
    FOREIGN KEY (id_reduction) REFERENCES reduction(id_reduction) ON DELETE CASCADE,
    FOREIGN KEY (id_paiement)  REFERENCES paiement(id_paiement) ON DELETE CASCADE
);



INSERT INTO creneau VALUES (1, '10:00:00');
INSERT INTO creneau VALUES (2, '11:00:00');
INSERT INTO creneau VALUES (3, '12:00:00');
INSERT INTO creneau VALUES (4, '13:00:00');
INSERT INTO creneau VALUES (5, '14:00:00');
INSERT INTO creneau VALUES (6, '15:00:00');
INSERT INTO creneau VALUES (7, '16:00:00');
INSERT INTO creneau VALUES (8, '17:00:00');
INSERT INTO creneau VALUES (9, '18:00:00');
INSERT INTO creneau VALUES (10, '19:00:00');

INSERT INTO attraction (id_attraction, nom, description, prix, capacite_max) VALUES
(1, 'Laser Game', 'Combat laser entre amis dans un labyrinthe immersif.', 5.00, 20),
(2, 'Exploration', 'Parcours d’énigmes et d’obstacles pour les plus téméraires.', 4.50, 25),
(3, 'Sculpture Citrouille', 'Atelier Halloween de sculpture sur citrouille.', 3.00, 15),
(4, 'Nocturne Halloween', 'Parc transformé en manoir hanté de nuit.', 7.50, 50),
(5, 'Escape Game', 'Énigmes à résoudre en équipe pour s’échapper.', 6.00, 10),
(6, 'Parcours Aventure', 'Accrobranche et tyrolienne dans les arbres.', 8.00, 30),
(7, 'Chasse aux Fantômes', 'Chasse interactive aux fantômes dans le parc.', 4.00, 40),
(8, 'Atelier Sorcellerie', 'Atelier de potions et de magie.', 3.50, 20),
(9, 'Spectacle de Magie', 'Magicien en direct avec des tours époustouflants.', 5.50, 100),
(10, 'Visite Nocturne', 'Visite guidée du parc à la tombée de la nuit.', 4.00, 60);

INSERT INTO reduction (prc_reduction, age_min, age_max, date_min, date_max, nb_visites_min, nom)
VALUES (20, 0, 12, NULL, NULL, NULL, 'Réduction Enfant');

INSERT INTO reduction (prc_reduction, age_min, age_max, date_min, date_max, nb_visites_min, nom)
VALUES (25, 60, NULL, NULL, NULL, NULL, 'Réduction Senior');

INSERT INTO reduction (prc_reduction, age_min, age_max, date_min, date_max, nb_visites_min, nom)
VALUES (10, NULL, NULL, NULL, NULL, 5, 'Réduction Client Régulier');

INSERT INTO reduction (prc_reduction, age_min, age_max, date_min, date_max, nb_visites_min, nom)
VALUES (30, NULL, NULL, '2025-10-25', '2025-10-31', NULL, 'Réduction Halloween');

INSERT INTO reduction (prc_reduction, age_min, age_max, date_min, date_max, nb_visites_min, nom)
VALUES (35, NULL, NULL, '2025-12-20', '2025-12-31', NULL, 'Réduction Noël');
