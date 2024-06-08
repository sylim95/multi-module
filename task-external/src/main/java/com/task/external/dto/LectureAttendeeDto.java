package com.task.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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

        @Schema(description = "강연 ID")
        @NotNull
        private Integer lectureMainId;

        @Schema(description = "신청자 ID", hidden = true)
        private String employeeId;
    }

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Res {

        @Schema(description = "강연 ID")
        private Integer lectureMainId;

        @Schema(description = "강연자")
        private String speakerName;

        @Schema(description = "신청자 아이디")
        private String employeeId;

        @Schema(description = "신청 일자")
        private String regDate;
    }

    @Getter
    @Builder
    public static class CountRes {

        @Schema(description = "강연 ID")
        private Integer lectureMainId;

        @Schema(description = "신청자 수")
        private Integer count;
    }
}
