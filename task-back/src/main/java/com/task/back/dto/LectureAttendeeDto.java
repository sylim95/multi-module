package com.task.back.dto;

import com.task.common.domain.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;
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

        @Schema(description = "신청자 PK ID")
        private Integer lectureAttendeeId;

        @Schema(description = "신청 강연 PK ID")
        private Integer lectureMainId;

        @Schema(description = "신청자 사번")
        private String employeeId;

        @Schema(description = "삭제 여부")
        private String delYn;

        @Schema(description = "등록 일자")
        private String regDate;

        @Schema(description = "수정 일자")
        private String modDate;
    }
}
