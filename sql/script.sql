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

CREATE TABLE syspension (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE systemdirection (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);

CREATE TABLE systemefreinage (
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
    idboitevitesse INT REFERENCES boitevitesse ,
    idsourceenergie INT REFERENCES sourceenergie ,
    idsuspension INT REFERENCES syspension ,
    idcassis INT REFERENCES cassis ,
    idsystemedirection INT REFERENCES systemdirection ,
    idsystemefreinage INT REFERENCES systemefreinage ,
    nombreporte INT ,
    nombreplace INT
);

CREATE TABLE voiture_optiondivertissement(
    id SERIAL PRIMARY KEY ,
    idvoiture INT REFERENCES voiture ,
    idoptiondivertissement INT REFERENCES optiondivertisement
);

CREATE TABLE voiture_optionsecurite (
    id SERIAL PRIMARY KEY ,
    idvoiture INT REFERENCES voiture,
    idoptionsecutite INT REFERENCES optionsecurite
);

CREATE TABLE utilisateur(
    id SERIAL PRIMARY KEY ,
    nom VARCHAR NOT NULL ,
    username VARCHAR UNIQUE ,
    password VARCHAR(16),
    idrole INT REFERENCES role
);

CREATE TABLE role(
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);