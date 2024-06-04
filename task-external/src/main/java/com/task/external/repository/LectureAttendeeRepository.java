package com.task.external.repository;

import com.task.common.domain.LectureAttendee;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.task.common.repository
 * fileName       : LectureAttendeeRepository
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Repository
public interface LectureAttendeeRepository extends JpaRepository<LectureAttendee, Integer> {
    boolean existsByLecture_LectureMainIdAndEmployeeIdAndDelYn(Integer lectureMainId, String employeeId, String delYn);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LectureAttendee findByLecture_LectureMainIdAndEmployeeIdAndDelYn(Integer lectureMainId, String employeeId, String delYn);

    List<LectureAttendee> findByEmployeeIdAndDelYn(String employeeId, String delYn);
}
