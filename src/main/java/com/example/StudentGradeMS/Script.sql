INSERT INTO user_login (username, password) VALUES ('admin', 'Abc1234');


INSERT INTO Student (STUDENT_NO, COURSE, FACULTY, NAME)
VALUES
(1, 'Computer Science', 'Engineering', 'Alice Johnson'),
(2, 'Mechanical Engineering', 'Engineering', 'Bob Smith'),
(3, 'Biology', 'Science', 'Carol White');


INSERT INTO Lecturer (ID, FACULTY, NAME)
VALUES
(1, 'Engineering', 'Dr. John Smith'),
(2, 'Science', 'Prof. Jane Doe'),
(3, 'Arts', 'Dr. Emily Brown');


INSERT INTO Subject (SUBJECT_ID, CODE, CREDIT_HOURS, LECTURER_NAME, NAME)
VALUES
(1, 'CS101', 3, 'Dr. Jane Smith', 'Introduction to Computer Science'),
(2, 'MATH201', 4, 'Prof. John Doe', 'Calculus I'),
(3, 'PHY101', 3, 'Dr. Emily Brown', 'Physics I');