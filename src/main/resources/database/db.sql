CREATE TABLE  IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS games(
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL,
    difficulty VARCHAR(15) NOT NULL,
    tries_left  INT DEFAULT 0,
    board_state TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_player
        FOREIGN KEY (player_id)
        REFERENCES players(id)
        ON DELETE CASCADE        
);