package com.task.external.repository;

import com.task.common.domain.Lecture;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.task.external.repository
 * fileName       : LectureRepository
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query("SELECT main FROM Lecture main")
    List<Lecture> findAllLectureWithoutAttendees();

    // Read, Write 비관적 락
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT main FROM Lecture main WHERE main.lectureMainId = :id")
    Optional<Lecture> findByIdForUpdate(@Param("id") Integer id);

}
