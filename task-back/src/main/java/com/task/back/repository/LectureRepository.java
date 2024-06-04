package com.task.back.repository;

import com.task.common.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.task.back.repository
 * fileName       : LectureRepository
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query("SELECT main FROM Lecture main")
    List<Lecture> findAllLectureWithoutAttendees();
}
