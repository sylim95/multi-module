package com.task.back.dto;

import com.task.common.domain.Lecture;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;

/**
 * packageName    : com.task.back.dto
 * fileName       : LectureAttendeeDto
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
public class LectureAttendeeDto {

    @Getter
    @Builder
    public static class Res {
        // 신청자 PK
        private Integer lectureAttendeeId;

        // 신청자 사번
        private String employeeId;

        // 삭제 여부
        private String delYn;

        // 등록 일자
        private String regDate;

        // 수정 일자
        private String modDate;
    }
}
