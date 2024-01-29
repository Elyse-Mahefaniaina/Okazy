CREATE TABLE boitevitesse (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE cassis (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE marque (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE model (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE optiondivertisement (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE optionsecurite (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE sourceenergie (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE suspension (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE systemdirection (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE systemfreinage (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE voiture (
    id SERIAL PRIMARY KEY ,
    idmarque INT REFERENCES marque NOT NULL,
    idmodel INT REFERENCES model NOT NULL,
    puissancefiscale DOUBLE PRECISION,
    cylindre DOUBLE PRECISION,
    puissancemoteur DOUBLE PRECISION,
    idcassis INT REFERENCES cassis ,
    nombreporte INT ,
    nombreplace INT ,
    miseencirculation DATE
);

CREATE TABLE role(
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE utilisateur(
    id SERIAL PRIMARY KEY ,
    nom VARCHAR NOT NULL ,
    username VARCHAR UNIQUE ,
    password VARCHAR(16),
    idrole INT REFERENCES role
);

CREATE TABLE annonce(
    id SERIAL PRIMARY KEY ,
    date DATE ,
    titre VARCHAR ,
    description VARCHAR ,
    idutilisateur INT REFERENCES utilisateur,
    idvoiture INT REFERENCES voiture ,
    idsystemedirection INT REFERENCES systemdirection ,
    idsystemefreinage INT REFERENCES systemfreinage ,
    idboitevitesse INT REFERENCES boitevitesse ,
    idsourceenergie INT REFERENCES sourceenergie ,
    idsuspension INT REFERENCES suspension ,
    couleurInterieur VARCHAR ,
    couleurExterieur VARCHAR ,
    prix DOUBLE PRECISION ,
    state INT
);

CREATE TABLE vente(
    id SERIAL PRIMARY KEY ,
    idannonce INT REFERENCES annonce ,
    date date
);

CREATE TABLE voiture_annonce_photo (
    id SERIAL PRIMARY KEY ,
    idannonce INT REFERENCES annonce ,
    photo VARCHAR
);

CREATE TABLE annonce_optiondivertissement(
    id SERIAL PRIMARY KEY ,
    idannonce INT REFERENCES annonce ,
    idoptiondivertissement INT REFERENCES optiondivertisement
);

CREATE TABLE annonce_optionsecurite (
    id SERIAL PRIMARY KEY ,
    idannonce INT REFERENCES annonce,
    idoptionsecutite INT REFERENCES optionsecurite
);

CREATE TABLE favoris(
    id SERIAL PRIMARY KEY ,
    idutilisateur INT REFERENCES utilisateur ,
    idannonce INT REFERENCES annonce
);

CREATE TABLE commission(
    id SERIAL PRIMARY KEY ,
    commission DOUBLE PRECISION
);

CREATE VIEW v_benef_marque AS
    SELECT
        m.id idmarque,
        EXTRACT(YEAR FROM a.date) as annee,
        SUM(a.prix * commission.commission) benef
    FROM annonce a
        INNER JOIN voiture v ON v.id = a.idvoiture
        INNER JOIN marque m ON v.idmarque = m.id
        INNER JOIN commission ON a.state = 21
    WHERE a.state = 21
    GROUP BY m.id, EXTRACT(YEAR FROM a.date);

CREATE VIEW v_benef_marque_avg AS
    SELECT
        v.idmarque ,
        v.annee ,
        v.benef ,
        COUNT(*) AS total
    FROM v_benef_marque v
    GROUP BY v.idmarque, v.annee, v.benef;

CREATE VIEW v_vente_month AS
    SELECT
        EXTRACT(YEAR FROM a.date) AS annee,
        EXTRACT(MONTH FROM a.date) AS mois,
        SUM(a.prix * commission.commission) total
    FROM annonce a
        INNER JOIN vente v ON v.idannonce = a.id
        INNER JOIN commission ON a.state = 21
    WHERE a.state = 21
    GROUP BY a.date;

CREATE VIEW v_vente_avg_month AS
    SELECT
        vvm.mois,
        AVG(vvm.total) AS moyenne
    FROM v_vente_month vvm
    GROUP BY vvm.mois;