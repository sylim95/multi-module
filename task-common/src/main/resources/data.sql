INSERT INTO lecture_main (lecture_main_id, speaker_name, location, attendee_count, total_count, lecture_start_time, lecture_end_time, content)
SELECT 1, '홍길동', 'A동 201호', 0, 2, '2024-06-05 09:00:00', '2024-06-05 18:00:00', '스프링 배치 강연'
WHERE NOT EXISTS (SELECT 1 FROM lecture_main WHERE lecture_main_id = 1);