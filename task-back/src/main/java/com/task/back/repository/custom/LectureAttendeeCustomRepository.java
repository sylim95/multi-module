package com.task.back.repository.custom;

import com.task.common.domain.LectureAttendee;

import java.util.List;

/**
 * packageName    : com.task.back.repository.custom.impl
 * fileName       : LectureCustomRepository
 * author         : limsooyoung
 * date           : 6/7/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/7/24        limsooyoung       최초 생성
 */
public interface LectureAttendeeCustomRepository {
    List<LectureAttendee> findByDelYnAndLectureMainId(Integer lectureMainId, String delYn);
}
