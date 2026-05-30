CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE diseases (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE specialties (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE patients (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    cpf VARCHAR(14) UNIQUE,
    birth_date DATE,
    phone VARCHAR(20),
    cep VARCHAR (9),
    address VARCHAR(255)
);

CREATE TABLE providers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    registration_number VARCHAR(50),
    profession VARCHAR(100),
    bio TEXT,
    cep VARCHAR (9),
    address VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE institutions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    cnpj VARCHAR(18) UNIQUE,
    description TEXT,
    cep VARCHAR (9),
    address VARCHAR(255),
    phone VARCHAR(20),
    website VARCHAR(255)
);

CREATE TABLE patient_diseases (
    patient_id BIGINT REFERENCES patients(id),
    disease_id BIGINT REFERENCES diseases(id),
    PRIMARY KEY (patient_id, disease_id)
);

CREATE TABLE provider_specialties (
    provider_id BIGINT REFERENCES providers(id),
    specialty_id BIGINT REFERENCES specialties(id),
    PRIMARY KEY (provider_id, specialty_id)
);

CREATE TABLE institution_providers (
    institution_id BIGINT REFERENCES institutions(id),
    provider_id BIGINT REFERENCES providers(id),
    PRIMARY KEY (institution_id, provider_id)
);

