-- Genres
INSERT IGNORE INTO genre (name) VALUES 
('Action'), ('Comedy'), ('Drama');

INSERT IGNORE INTO movie (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year) VALUES 
('The Dark Knight', 'A billionaire man battles a mentally ill clown.', 9.0, '2008-07-18', 'Christopher Nolan', 'Jonathan Nolan, Christopher Nolan', 'Christian Bale, Heath Ledger, Aaron Eckhart', 152, 'tt0468569', 2008),
('The Prestige', 'A magician has a Tesla and is in a conflict with twins', 8.8, '2006-10-20', 'Christopher Nolan', 'Jonathan Nolan, Christopher Priest', 'Christian Bale, Hugh Jackman, Scarlett Johansson', 130, 'tt0482571', 2006),
('Inception', 'Team falls asleep during a flight.', 8.8, '2010-07-16', 'Christopher Nolan', 'Christopher Nolan', 'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page', 148, 'tt1375666', 2010);

INSERT IGNORE INTO tvshow (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year) VALUES 
('Breaking Bad', 'High school teacher gone wild', 9.5, '2008-01-20', 'Vince Gilligan', 'Vince Gilligan', 'Bryan Cranston, Aaron Paul, Anna Gunn', 47, 'tt0903747', 2008),
('Fringe', 'Mad scientist accidentally tears reality', 8.9, '2008-09-09', 'J.J. Abrams', 'J.J. Abrams', 'Anna Torv, Joshua Jackson, John Noble', 22, 'tt1119644', 2008),
('Game of Thrones', 'Dragons, Zombies and back stabbing', 9.3, '2011-04-17', 'David Benioff, D.B. Weiss', 'David Benioff, D.B. Weiss', 'Emilia Clarke, Peter Dinklage, Kit Harington', 57, 'tt0944947', 2011);

-- TV Seasons
INSERT IGNORE INTO tvseason (number, tv_show_id) VALUES 
(1, 1), (1, 2), (1, 3);

INSERT IGNORE INTO tvepisode (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year, number, tv_season_id) VALUES 
('Pilot', 'Chem teacher starts cooking meth?', 9.0, '2008-01-20', 'Vince Gilligan', 'Vince Gilligan', 'Bryan Cranston, Aaron Paul', 58, 'tt0959621', 2008, 1, 1),
('Pilot', 'FBI agent teams up with mad scientist and his son', 8.7, '2008-09-09', 'Alex Graves', 'J.J. Abrams, Alex Kurtzman', 'Anna Torv, Joshua Jackson', 48, 'tt1054728', 2008, 1, 2),
('Winter is Coming', 'Winter begins its approach.', 8.3, '2011-04-17', 'Tim Van Patten', 'David Benioff, D.B. Weiss', 'Emilia Clarke, Peter Dinklage', 60, 'tt1480055', 2011, 1, 3);

-- Movie Genres
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES 
(1, 1), (2, 2), (3, 3);

-- TV Show Genres
INSERT IGNORE INTO tv_show_genres (tv_show_id, genre_id) VALUES 
(1, 3), (2, 2), (3, 1);