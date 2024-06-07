package com.task.external.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.task.external.dto
 * fileName       : LectureDto
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
public class LectureDto {

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
