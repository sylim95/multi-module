package com.task.back.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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

        // 강연자
        @NotEmpty
        private String speakerName;

        // 강연장
        private String location;

        // 신청 가능 인원
        @NotNull
        private Integer totalCount;

        // 강연 시작 시간
        @NotEmpty
        private String lectureStartTime;

        // 강연 종료 시간
        private String lectureEndTime;

        // 강연 내용
        private String content;
    }

    @Getter
    @Builder
    public static class Res {
        // 강연자
        private String speakerName;

        // 강연장
        private String location;

        // 신청 인원
        private Integer attendeeCount;

        // 신청 가능 인원
        private Integer totalCount;

        // 강연 시작 시간
        private String lectureStartTime;

        // 강연 종료 시간
        private String lectureEndTime;

        // 강연 내용
        private String content;
    }
}
