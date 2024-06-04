-- 강연 테이블
CREATE TABLE IF NOT EXISTS lecture_main
(
    lecture_main_id int AUTO_INCREMENT,
    speaker_name varchar(50) NOT NULL,
    location varchar(100),
    attendee_count int NOT NULL,
    total_count int NOT NULL,
    lecture_start_time datetime NOT NULL,
    lecture_end_time datetime,
    content varchar(255),
    PRIMARY KEY(lecture_main_id)
);
CREATE TABLE IF NOT EXISTS lecture_attendee
(
    lecture_attendee_id int AUTO_INCREMENT,
    lecture_main_id int NOT NULL,
    employee_id varchar(5) NOT NULL,
    del_yn varchar(1) NOT NULL default 'N',
    reg_date datetime NOT NULL,
    mod_date datetime,
    PRIMARY KEY(lecture_attendee_id),
    FOREIGN KEY (lecture_main_id) REFERENCES lecture_main(lecture_main_id)
);
