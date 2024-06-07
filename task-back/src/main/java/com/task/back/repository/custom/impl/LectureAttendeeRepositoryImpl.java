package com.task.back.repository.custom.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.task.back.repository.custom.LectureAttendeeCustomRepository;
import com.task.common.domain.LectureAttendee;
import com.task.common.domain.QLectureAttendee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.task.back.repository.impl
 * fileName       : LectureRepositoryImpl
 * author         : limsooyoung
 * date           : 6/7/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/7/24        limsooyoung       최초 생성
 */
@RequiredArgsConstructor
@Repository
public class LectureAttendeeRepositoryImpl implements LectureAttendeeCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<LectureAttendee> findByDelYnAndLectureMainId(Integer lectureMainId, String delYn) {
        QLectureAttendee lectureAttendee = QLectureAttendee.lectureAttendee;

        BooleanBuilder builder = new BooleanBuilder();
        if (delYn != null) {
            builder.and(lectureAttendee.delYn.eq(delYn));
        }
        if (lectureMainId != null) {
            builder.and(lectureAttendee.lecture.lectureMainId.eq(lectureMainId));
        }

        return queryFactory.selectFrom(lectureAttendee)
                .where(builder)
                .fetch();
    }
}
