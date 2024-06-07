package com.task.external.service;

import com.task.common.domain.Lecture;
import com.task.common.domain.LectureAttendee;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import com.task.external.repository.LectureAttendeeRepository;
import com.task.external.repository.LectureRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.task.external.service
 * fileName       : LectureServiceTest
 * author         : limsooyoung
 * date           : 6/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/5/24        limsooyoung       최초 생성
 */
@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private LectureAttendeeRepository lectureAttendeeRepository;

    @DisplayName("강연 시작 시간 1주일 전부터 강연 시작 시간 1일 후까지 강연 목록 조회")
    @Test
    void getLectureList() {
        // Given
        Lecture.LectureBuilder entity1 = Lecture.builder();
        entity1.lectureMainId(1);
        entity1.attendeeCount(8);
        entity1.lectureStartTime(LocalDateTime.now().plusDays(8L));

        Lecture.LectureBuilder entity2 = Lecture.builder();
        entity2.lectureMainId(2);
        entity1.attendeeCount(7);
        entity2.lectureStartTime(LocalDateTime.now().plusDays(7L));

        Lecture.LectureBuilder entity3 = Lecture.builder();
        entity3.lectureMainId(3);
        entity3.attendeeCount(3);
        entity3.lectureStartTime(LocalDateTime.now().plusDays(3L));

        Lecture.LectureBuilder entity4 = Lecture.builder();
        entity4.lectureMainId(4);
        entity4.attendeeCount(1);
        entity4.lectureStartTime(LocalDateTime.now().minusDays(1L));

        Lecture.LectureBuilder entity5 = Lecture.builder();
        entity5.lectureMainId(5);
        entity5.attendeeCount(2);
        entity5.lectureStartTime(LocalDateTime.now().minusDays(2L));

        LectureDto.Res.ResBuilder response1 = LectureDto.Res.builder();
        response1.lectureMainId(entity2.build().getLectureMainId());
        response1.attendeeCount(entity2.build().getAttendeeCount());
        response1.lectureStartTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(entity2.build().getLectureStartTime()));

        LectureDto.Res.ResBuilder response2 = LectureDto.Res.builder();
        response2.lectureMainId(entity3.build().getLectureMainId());
        response2.attendeeCount(entity3.build().getAttendeeCount());
        response2.lectureStartTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(entity3.build().getLectureStartTime()));

        LectureDto.Res.ResBuilder response3 = LectureDto.Res.builder();
        response3.lectureMainId(entity4.build().getLectureMainId());
        response3.attendeeCount(entity4.build().getAttendeeCount());
        response3.lectureStartTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(entity4.build().getLectureStartTime()));

        // When
        when(lectureRepository.findAllLectureWithoutAttendees())
                .thenReturn(List.of(entity1.build(), entity2.build(), entity3.build(), entity4.build(), entity5.build()));

        // Then
        assertThat(lectureService.getLectureList())
                .usingRecursiveComparison()
                .isEqualTo(List.of(response1.build(), response2.build(), response3.build()));

    }

    @DisplayName("강연 신청 내역 조회")
    @Test
    void getLectureAttendee() {
        // Given
        Integer lectureMainId = 1;
        String employeeId = "12345";
        LectureAttendee.LectureAttendeeBuilder<?, ?> entity = LectureAttendee.builder();
        entity.lecture(Lecture.builder().lectureMainId(lectureMainId).build());
        entity.employeeId(employeeId);

        LectureAttendeeDto.Res.ResBuilder response = LectureAttendeeDto.Res.builder();
        response.lectureMainId(lectureMainId);
        response.employeeId(employeeId);

        // When
        when(lectureAttendeeRepository.findByEmployeeIdAndDelYn(employeeId, "N"))
                .thenReturn(List.of(entity.build()));

        // Then
        assertThat(lectureService.getLectureAttendee("12345"))
                .usingRecursiveComparison()
                .isEqualTo(List.of(response.build()));
    }

    @DisplayName("3일간 가장 신청이 많은 강연 순으로 조회")
    @Test
    void getPopularLecture() {
        // Given
        LectureAttendee.LectureAttendeeBuilder<?, ?> entity1 = LectureAttendee.builder();
        entity1.lecture(Lecture.builder().lectureMainId(1).build());
        entity1.delYn("N");
        entity1.employeeId("12345");
        entity1.regDate(LocalDateTime.now().minusDays(3L));

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity2 = LectureAttendee.builder();
        entity2.lecture(Lecture.builder().lectureMainId(1).build());
        entity2.delYn("N");
        entity2.employeeId("23456");
        entity2.regDate(LocalDateTime.now().minusDays(2L));

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity3 = LectureAttendee.builder();
        entity3.lecture(Lecture.builder().lectureMainId(1).build());
        entity3.delYn("N");
        entity3.employeeId("34567");
        entity3.regDate(LocalDateTime.now().minusDays(1L));

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity4 = LectureAttendee.builder();
        entity4.lecture(Lecture.builder().lectureMainId(2).build());
        entity4.delYn("N");
        entity4.employeeId("12345");
        entity4.regDate(LocalDateTime.now().minusDays(3L));

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity5 = LectureAttendee.builder();
        entity5.lecture(Lecture.builder().lectureMainId(2).build());
        entity5.delYn("N");
        entity5.employeeId("23456");
        entity5.regDate(LocalDateTime.now().minusDays(2L));

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity6 = LectureAttendee.builder();
        entity6.lecture(Lecture.builder().lectureMainId(3).build());
        entity6.delYn("N");
        entity6.employeeId("12345");
        entity6.regDate(LocalDateTime.now());

        LectureAttendeeDto.CountRes.CountResBuilder response1 = LectureAttendeeDto.CountRes.builder();
        response1.lectureMainId(entity2.build().getLecture().getLectureMainId());
        response1.count(2);

        LectureAttendeeDto.CountRes.CountResBuilder response2 = LectureAttendeeDto.CountRes.builder();
        response2.lectureMainId(entity5.build().getLecture().getLectureMainId());
        response2.count(1);

        LectureAttendeeDto.CountRes.CountResBuilder response3 = LectureAttendeeDto.CountRes.builder();
        response3.lectureMainId(entity6.build().getLecture().getLectureMainId());
        response3.count(1);

        // When
        when(lectureAttendeeRepository.findAllWithOutLectureMain())
                .thenReturn(List.of(entity1.build(), entity2.build(), entity3.build(), entity4.build(), entity5.build(), entity6.build()));

        // Then
        assertThat(lectureService.getPopularLecture())
                .usingRecursiveComparison()
                .isEqualTo(List.of(response1.build(), response2.build(), response3.build()));
    }
}
