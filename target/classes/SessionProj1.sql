ALTER SESSION SET nls_date_format='YYYY-MM-DD';

SELECT * FROM userlogin;
SELECT * FROM request;
SELECT * FROM award;
SELECT * FROM departments;
SELECT * FROM event;
SELECT * FROM upload;
commit;
UPDATE userlogin SET available = 1000 WHERE u_id = 1021;
UPDATE request SET req_status = 1001 WHERE u_id = 1000
DELETE FROM userlogin WHERE u_email = 'DUMMY@DUMMY.COM'
UPDATE request SET req_value = 9002, req_status = 9002, req_flag = 0, req_notes = 'Test notes' WHERE req_id = 
SELECT * FROM event WHERE e_name = 'Parks for Dums';
SELECT * FROM request WHERE u_id = 1000 AND req_value = 9001;
DELETE FROM request WHERE u_id = 1000 AND req_value = 9001;
DELETE FROM event WHERE e_name = 'Parks for Dums';
UPDATE userlogin SET s_id = 1001 WHERE u_id = 1001;
UPDATE departments SET D_head = 1002 WHERE d_id = 104;
DELETE FROM request WHERE u_id = 1001;
rollback;
DELETE FROM request WHERE u_id = 1001;
UPDATE request SET req_status = 1001 WHERE req_id = 62 
UPDATE request SET req_status = 1001 WHERE req_id = 8;
-- CLean ups for tests
DELETE FROM request WHERE REQ_id = 42 ;
DELETE FROM event WHERE e_name = ' Parks for Dummies ';
commit;
UPDATE departments SET d_head = 1040 WHERE d_id = 104;
SELECT userlogin.u_id, request.req_id, event.e_id, request.req_value, available, event.e_cate, request.req_notes FROM userlogin 
    INNER JOIN request ON userlogin.u_id = request.u_id 
    INNER JOIN event ON request.e_id = event.e_id;

SELECT * FROM userlogin INNER JOIN departments ON departments.d_id = userlogin.d_id WHERE departments.d_id = 100;

-- Shows department head for each employee
SELECT u.u_id, u.u_firstname, u.u_lastname, u.d_id, u.available, u.s_id, d.d_name, d.d_head 
    FROM userlogin u 
    INNER JOIN departments d 
    ON d.d_id = u.d_id WHERE d.d_id = 100;
-- Select all records where the department head is what is the given.
SELECT d.d_head FROM userlogin u INNER JOIN departments d ON d.d_id = u.d_id WHERE d.d_head = 1001;
-- 
SELECT d.d_head FROM departments d INNER JOIN userlogin u ON d.d_id = u.d_id WHERE u_id = 1001;


UPDATE request SET req_notes = ' ' WHERE REQ_id=61;

SELECT userlogin.u_id, userlogin.u_firstname, userlogin.u_lastname, userlogin.available, request.req_id, request.req_value, request.req_time, request.req_status, request.req_flag, userlogin.d_id FROM userlogin INNER JOIN request ON userlogin.u_id = request.u_id WHERE s_id = 1001;