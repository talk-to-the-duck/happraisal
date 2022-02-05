insert into person (id, first_name, last_name, is_manager)
values
('8a37adbc-0c29-491a-8b6f-f10a4219fb91', 'John', 'Doe', true),
('3556c835-3bcd-420e-bb6e-a8b7c0bb139d', 'Jehnna', 'Eod', false);

insert into form (id) values ('df9b2aad-d32e-469e-b434-be71a5531a35');

insert into question_answer (id, question, form_id)
values
('87e115f1-5809-4b05-946b-91c670f629bc', 'What is a good test for you', 'df9b2aad-d32e-469e-b434-be71a5531a35'),
('d2d408e2-01be-4850-bf1c-e1026da4a17e', 'Name two types of tests', 'df9b2aad-d32e-469e-b434-be71a5531a35');
