package com.task.back.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.task.back.dto
 * fileName       : LectureDto
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
public class LectureDto {

    @Getter
    @Builder
    public static class Req {

        @Schema(description = "강연자")
        @NotEmpty
        private String speakerName;

        @Schema(description = "강연장")
        private String location;

        @Schema(description = "신청 가능 인원")
        @NotNull
        private Integer totalCount;

        @Schema(description = "강연 시작 시간")
        @NotEmpty
        private String lectureStartTime;

        @Schema(description = "강연 종료 시간")
        private String lectureEndTime;

        @Schema(description = "강연 내용")
        private String content;
    }

    @Getter
    @Builder
    public static class Res {

        @Schema(description = "강연 PK ID")
        private Integer lectureMainId;

        @Schema(description = "강연자")
        private String speakerName;

        @Schema(description = "강연장")
        private String location;

        @Schema(description = "신청 인원")
        private Integer attendeeCount;

        @Schema(description = "신청 가능 인원")
        private Integer totalCount;

        @Schema(description = "강연 시작 시간")
        private String lectureStartTime;

        @Schema(description = "강연 종료 시간")
        private String lectureEndTime;

        @Schema(description = "강연 내용")
        private String content;
    }
}
