-- Création de la base de données
CREATE DATABASE IF NOT EXISTS hop_in;
USE hop_in;

-- Table Utilisateur
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

-- Table Attraction (avec capacite_max pour gérer le quota)
CREATE TABLE attraction (
    id_attraction INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(6,2) NOT NULL,
    capacite_max INT NOT NULL DEFAULT 30
);


-- Table ReductionUtilisateur (réduction personnalisée par user)
CREATE TABLE reductionUtilisateur (
    id_reduction_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    pourcentage DECIMAL(4,2) NOT NULL,
    membre_necessaire BOOLEAN,
    ageMin DATE,
    ageMax DATE,
    dateDeb DATE,
    dateFin DATE
);

-- Table Reservation
CREATE TABLE reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    id_attraction INT,
    date_reservation DATE NOT NULL,
    nb_personnes INT NOT NULL,
    id_reduction_utilisateur INT
    prix_total DECIMAL(8,2) NOT NULL,
    statut ENUM('CONFIRMEE', 'ANNULEE'),
    FOREIGN KEY (id) REFERENCES utilisateur(id),
    FOREIGN KEY (id_attraction) REFERENCES attraction(id_attraction),
    FOREIGN KEY (id_reduction_utilisateur) REFERENCES reductionUtilisateur(id_reduction_utilisateur)
);

INSERT INTO attraction (nom, description, prix, capacite_max) VALUES
('Laser Game', 'Affrontez vos amis dans un labyrinthe lumineux avec des pistolets laser.', 12.00, 25),
('Exploration', 'Parcours interactif pour découvrir la jungle et les animaux exotiques.', 10.50, 30),
('Sculpture Citrouille', 'Atelier créatif d’Halloween : sculptez votre propre citrouille !', 8.00, 20),
('Nocturne Halloween', 'Parc ouvert en nocturne avec ambiance effrayante et shows spéciaux.', 15.00, 40),
('Train Fantôme', 'Un parcours rempli de frissons et de surprises.', 9.00, 25),
('Manège Aventure', 'Manège pour enfants et familles avec décors immersifs.', 7.50, 20),
('Montagnes Russes', 'Les sensations fortes à leur apogée !', 14.00, 35),
('Salle des Énigmes', 'Escape game immersif en groupe dans l’univers Hop’In.', 11.00, 20);



