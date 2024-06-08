INSERT INTO lecture_main (lecture_main_id, speaker_name, location, attendee_count, total_count, lecture_start_time, lecture_end_time, content)
SELECT 1, '홍길동', 'A동 201호', 0, 2,
       DATEADD('DAY', 1, CURRENT_DATE) || ' 09:00:00',
       DATEADD('DAY', 1, CURRENT_DATE) || ' 18:00:00',
       '스프링 부트 강연'
WHERE NOT EXISTS (SELECT 1 FROM lecture_main WHERE lecture_main_id = 1);