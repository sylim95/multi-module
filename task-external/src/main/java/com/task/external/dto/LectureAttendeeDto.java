package com.task.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.task.external.dto
 * fileName       : LectureAttendeeDto
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
public class LectureAttendeeDto {

    @Getter
    @Setter
    @Builder
    public static class Req {
        // 강연 ID
        @NotNull
        private Integer lectureMainId;

        // 신청자 ID
        private String employeeId;
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Res {

        // 강연 ID
        private Integer lectureMainId;

        // 강연자
        private String speakerName;

        // 신청자 아이디
        private String employeeId;
    }
}
