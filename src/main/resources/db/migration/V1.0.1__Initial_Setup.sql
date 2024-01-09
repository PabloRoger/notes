-- Version: 1-- Version:001
-- Description: Create all tables from concept

--Setting DB default timezone
set timezone = 'UTC';

CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS notes (
    note_id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(user_id),
    title VARCHAR(255) NOT NULL,
    content TEXT,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user FOREIGN KEY (user_id)
    REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS shared_notes(
    share_id UUID PRIMARY KEY,
    note_id UUID REFERENCES notes(note_id),
    shared_with_user_id UUID REFERENCES users(user_id),
    shared_by_user_id UUID REFERENCES users(user_id),
    shared_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_note_shared FOREIGN KEY (note_id)
    REFERENCES notes(note_id),

    CONSTRAINT fk_shared_with_user FOREIGN KEY (shared_with_user_id)
    REFERENCES users(user_id),

    CONSTRAINT fk_shared_by_user FOREIGN KEY (shared_by_user_id)
    REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS labels (
    label_id UUID PRIMARY KEY,
    label_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS note_labels (
    note_id UUID REFERENCES notes(note_id),
    label_id UUID REFERENCES labels(label_id),
    PRIMARY KEY (note_id, label_id),

    CONSTRAINT fk_note FOREIGN KEY (note_id)
    REFERENCES notes(note_id),

    CONSTRAINT fk_label FOREIGN KEY (label_id)
    REFERENCES labels(label_id)
);

CREATE TABLE IF NOT EXISTS trash (
    trash_id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(user_id),
    note_id UUID REFERENCES notes(note_id),
    deleted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_trash FOREIGN KEY (user_id)
    REFERENCES users(user_id),

    CONSTRAINT fk_note_trash FOREIGN KEY (note_id)
    REFERENCES notes(note_id)
);