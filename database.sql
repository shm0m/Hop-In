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

-- Table Attraction (avec capacite_max pour gérer le quota)
CREATE TABLE Attraction (
    id_attraction INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    description TEXT,
    prix DECIMAL(6,2) NOT NULL,
    capacite_max INT NOT NULL DEFAULT 30
);

-- Table Reservation
CREATE TABLE Reservation (
    id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    id_attraction INT,
    date_reservation DATE NOT NULL,
    nb_personnes INT NOT NULL,
    id_reduction_utilisateur INT references ReductionUtilisateur(id_reduction_utilisateur),
    prix_total DECIMAL(8,2) NOT NULL,
    statut ENUM('CONFIRMEE', 'ANNULEE'),
    FOREIGN KEY (id) REFERENCES Utilisateur(id),
    FOREIGN KEY (id_attraction) REFERENCES Attraction(id_attraction)
);

-- Table ReductionUtilisateur (réduction personnalisée par user)
CREATE TABLE ReductionUtilisateur (
    id_reduction_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    pourcentage DECIMAL(4,2) NOT NULL,
    membre_necessaire BOOLEAN,
    ageMin DATE;
    ageMax DATE,
    dateDeb DATE,
    dateFin DATE
);
/* ////////////////////////////////////////////////// */
/*À mettre si vous n'avez pas la dernière version !!*/
/*alter table Reservation drop reduction_appliquee; alter table Reservation add id_reduction_utilisateur INT references ReductionUtilisateur(id_reduction_utilisateur);
alter table ReductionUtilisateur drop id;
alter table ReductionUtilisateur add ageMin DATE;
alter table ReductionUtilisateur add ageMax DATE;
alter table ReductionUtilisateur add dateDeb DATE;
alter table ReductionUtilisateur add dateFin DATE;*/


/*//////////////////////////////////////////////////*/

Modifs à faire:
Créer une table Promotions qui contient les codes promos (dont aucun (0%) sénior et junior) ainsi que
d'autres crées par les admins. RéductionUtilisateur a desormais deux clefs qui sont à la fois primaires
et étrangères: idReduc et IdReservation.
