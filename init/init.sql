CREATE TYPE status AS ENUM ('AVAILABLE', 'ALLOCATED', 'REDEEMED', 'EXPIRED', 'CANCELLED');

CREATE TYPE role AS ENUM ('ADMIN', 'MANAGER', 'COLLABORATOR');

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(150) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role role NOT NULL
);

CREATE TABLE certifications (
                                id UUID PRIMARY KEY,
                                code VARCHAR(6) UNIQUE NOT NULL
);

CREATE TABLE vouchers (
                          id UUID PRIMARY KEY,
                          supplier VARCHAR(255) NOT NULL,
                          certification_id UUID REFERENCES certifications(id),
                          value NUMERIC(10, 2) NOT NULL,
                          expiration_date DATE NOT NULL,
                          rules TEXT[] NOT NULL,
                          status status NOT NULL
);