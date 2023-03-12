SELECT * FROM rsvp.rsvp;

use rsvp;

select * from rsvp;

insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Tom Hanks', 'tom@hanks.com', '912838388','2023-03-10','comments'); 

insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Tim Cook', 'tim@apple.com', '912838387','2023-03-11','excited'); 

insert into rsvp (full_name, email, phone, confirmation_date, comments) 
values ('Peter Pan', 'peter@pan.com', '912888387','2022-03-11','cooool'); 

SELECT * FROM rsvp WHERE full_name LIKE '%tim%';

INSERT INTO RSVP (id, full_name, email, phone, confirmation_date, comments) 
VALUES (1, 'Tom Hanks Version 2', 'tomV2@hanks.com', '912838388','2023-03-10','commentsV2') 
ON DUPLICATE KEY UPDATE 
  full_name = VALUES(full_name), 
  email = VALUES(email), 
  phone = VALUES(phone), 
  confirmation_date = VALUES(confirmation_date), 
  comments = VALUES(comments);

INSERT INTO RSVP (id, full_name, email, phone, confirmation_date, comments) 
VALUES (1, 'Tom Hanks Version 2', 'tomV2@hanks.com', '912838388','2023-03-10','commentsV2') AS new
ON DUPLICATE KEY UPDATE 
  full_name = VALUES(new.full_name), 
  email = VALUES(new.email), 
  phone = VALUES(new.phone), 
  confirmation_date = VALUES(new.confirmation_date), 
  comments = VALUES(new.comments);
  
  
