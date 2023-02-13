CREATE TABLE member (
    id          UUID        PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    surname     VARCHAR(50) NOT NULL,
    birthday    DATE,
    team_id     UUID
);

CREATE TABLE team (
    id          UUID            PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    city        VARCHAR(100)    NOT NULL
);

ALTER TABLE member
ADD CONSTRAINT fk_member_team
FOREIGN KEY (team_id) REFERENCES team(id);