
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE genre;
TRUNCATE TABLE movie;
TRUNCATE TABLE tvshow;
TRUNCATE TABLE tvseason;
TRUNCATE TABLE tvepisode;
TRUNCATE TABLE movie_genres;
TRUNCATE TABLE tv_show_genres;

SET FOREIGN_KEY_CHECKS = 1;

-- Genres
INSERT IGNORE INTO genre (name) VALUES 
('Action'), ('Comedy'), ('Drama'), ('Thriller'), ('Sci-Fi'), ('Fantasy');

-- Movies
INSERT IGNORE INTO movie (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year) VALUES 
('The Dark Knight', 'A billionaire man battles a mentally ill clown.', 9.0, '2008-07-18', 'Christopher Nolan', 'Jonathan Nolan, Christopher Nolan', 'Christian Bale, Heath Ledger, Aaron Eckhart', 152, 'tt0468569', 2008),
('The Prestige', 'A magician has a Tesla and is in a conflict with twins', 8.8, '2006-10-20', 'Christopher Nolan', 'Jonathan Nolan, Christopher Priest', 'Christian Bale, Hugh Jackman, Scarlett Johansson', 130, 'tt0482571', 2006),
('Inception', 'Team falls asleep during a flight.', 8.8, '2010-07-16', 'Christopher Nolan', 'Christopher Nolan', 'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page', 148, 'tt1375666', 2010),
('Interstellar', 'Astronauts travel through a wormhole.', 8.6, '2014-11-07', 'Christopher Nolan', 'Jonathan Nolan, Christopher Nolan', 'Matthew McConaughey, Anne Hathaway, Jessica Chastain', 169, 'tt0816692', 2014),
('Dunkirk', 'Allied soldiers are evacuated from Dunkirk.', 7.9, '2017-07-21', 'Christopher Nolan', 'Christopher Nolan', 'Fionn Whitehead, Barry Keoghan, Mark Rylance', 106, 'tt5013056', 2017),
('Tenet', 'A protagonist fights for the survival of the world.', 7.4, '2020-08-26', 'Christopher Nolan', 'Christopher Nolan', 'John David Washington, Robert Pattinson, Elizabeth Debicki', 150, 'tt6723592', 2020);


-- TV Shows
INSERT IGNORE INTO tvshow (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year) VALUES 
('Breaking Bad', 'High school teacher gone wild', 9.5, '2008-01-20', 'Vince Gilligan', 'Vince Gilligan', 'Bryan Cranston, Aaron Paul, Anna Gunn', 47, 'tt0903747', 2008),
('Fringe', 'Mad scientist accidentally tears reality', 8.9, '2008-09-09', 'J.J. Abrams', 'J.J. Abrams', 'Anna Torv, Joshua Jackson, John Noble', 22, 'tt1119644', 2008),
('Game of Thrones', 'Dragons, Zombies and back stabbing', 9.3, '2011-04-17', 'David Benioff, D.B. Weiss', 'David Benioff, D.B. Weiss', 'Emilia Clarke, Peter Dinklage, Kit Harington', 57, 'tt0944947', 2011),
('Stranger Things', 'Typical Childhood in 80s.', 8.7, '2016-07-15', 'The Duffer Brothers', 'The Duffer Brothers', 'Millie Bobby Brown, Finn Wolfhard, Winona Ryder', 51, 'tt4574334', 2016),
('The Mandalorian', 'Spagetti Western in Space.', 8.8, '2019-11-12', 'Jon Favreau', 'Jon Favreau', 'Pedro Pascal, Gina Carano, Giancarlo Esposito', 35, 'tt8111088', 2019),
('The Witcher', 'Just watch first season.', 8.2, '2019-12-20', 'Lauren Schmidt Hissrich', 'Lauren Schmidt Hissrich', 'Henry Cavill, Freya Allan, Anya Chalotra', 60, 'tt5180504', 2019);

-- TV Seasons
INSERT IGNORE INTO tvseason (number, tv_show_id) VALUES 
(1, 1), (2, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);

-- TV Episodes
INSERT IGNORE INTO tvepisode (title, description, rating, release_date, director, writer, stars, duration, imdb_id, year, number, tv_season_id) VALUES 
('Pilot', 'Chem teacher starts cooking meth?', 9.0, '2008-01-20', 'Vince Gilligan', 'Vince Gilligan', 'Bryan Cranston, Aaron Paul', 58, 'tt0959621', 2008, 1, 1),
('Cat\'s in the Bag...', 'Yo yo yo. 148-3 to the 3 to the 6 to the 9, representing the ABQ, what up, bi**ch?', 8.8, '2008-01-27', 'Adam Bernstein', 'Vince Gilligan', 'Bryan Cranston, Aaron Paul', 48, 'tt1054728', 2008, 2, 1),
('Pilot', 'FBI agent teams up with mad scientist and his son', 8.7, '2008-09-09', 'Alex Graves', 'J.J. Abrams, Alex Kurtzman', 'Anna Torv, Joshua Jackson', 48, 'tt1054728', 2008, 1, 3),
('Winter is Coming', 'Winter begins its approach.', 8.3, '2011-04-17', 'Tim Van Patten', 'David Benioff, D.B. Weiss', 'Emilia Clarke, Peter Dinklage', 60, 'tt1480055', 2011, 1, 4),
('Chapter One: The Vanishing of Will Byers', 'Nerds gone wild after D&D Campaign.', 8.9, '2016-07-15', 'The Duffer Brothers', 'The Duffer Brothers', 'Winona Ryder, David Harbour', 47, 'tt4593118', 2016, 1, 5),
('Chapter One: The Mandalorian', 'Mando shot first', 8.7, '2019-11-12', 'Dave Filoni', 'Jon Favreau', 'Pedro Pascal, Carl Weathers', 39, 'tt8111088', 2019, 1, 6);

-- Movie Genres
INSERT IGNORE INTO movie_genres (movie_id, genre_id) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);

-- TV Show Genres
INSERT IGNORE INTO tv_show_genres (tv_show_id, genre_id) VALUES 
(1, 3), (2, 2), (3, 4), (4, 5), (5, 6), (6, 4);