package com.task.back.repository;

import com.task.back.repository.custom.LectureAttendeeCustomRepository;
import com.task.common.domain.LectureAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.task.back.repository
 * fileName       : LectureAttendeeRepository
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
@Repository
public interface LectureAttendeeRepository extends JpaRepository<LectureAttendee, Integer>, LectureAttendeeCustomRepository {
}
