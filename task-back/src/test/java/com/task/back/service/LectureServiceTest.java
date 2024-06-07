package com.task.back.service;

import com.task.back.dto.LectureAttendeeDto;
import com.task.back.dto.LectureDto;
import com.task.back.repository.LectureAttendeeRepository;
import com.task.back.repository.LectureRepository;
import com.task.common.domain.Lecture;
import com.task.common.domain.LectureAttendee;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

/**
 * packageName    : com.task.back.service
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

    private Integer lectureMainId;
    private String speakerName;
    private String location;
    private Integer attendeeCount;
    private String employeeId;
    private String lectureStartTime;
    private String lectureEndTime;
    private String content;
    private String delYn;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @BeforeEach
    void setup() {
        lectureMainId = 1;
        speakerName = "홍길동";
        location = "A동 502호";
        attendeeCount = 0;
        employeeId = "12345";
        lectureStartTime = "2024-06-05 10:00:00";
        lectureEndTime = "2024-06-05 18:00:00";
        content = "코틀린 강연";
        delYn = "N";
    }

    @DisplayName("전체 강연 조회")
    @Test
    void getAllLectureList() {
        // Given
        Lecture.LectureBuilder entity = Lecture.builder();
        entity.lectureMainId(lectureMainId);
        entity.speakerName(speakerName);
        entity.location(location);
        entity.attendeeCount(attendeeCount);
        entity.lectureStartTime(LocalDateTime.parse(lectureStartTime, formatter));
        entity.lectureEndTime(LocalDateTime.parse(lectureEndTime, formatter));
        entity.content(content);

        LectureDto.Res.ResBuilder response = LectureDto.Res.builder();
        response.lectureMainId(lectureMainId);
        response.speakerName(speakerName);
        response.location(location);
        response.attendeeCount(attendeeCount);
        response.lectureStartTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(entity.build().getLectureStartTime()));
        response.lectureEndTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(entity.build().getLectureEndTime()));
        response.content(content);

        // When
        when(lectureRepository.findAllLectureWithoutAttendees()).thenReturn(List.of(entity.build()));

        // Then
        assertThat(lectureService.getLectureList())
                .usingRecursiveComparison()
                .isEqualTo(List.of(response.build()));
    }

    @DisplayName("강연 등록")
    @Test
    void createLecture() {
        // Given
        LectureDto.Req.ReqBuilder request = LectureDto.Req.builder();
        request.speakerName(speakerName);
        request.location(location);
        request.lectureStartTime(lectureStartTime);
        request.lectureEndTime(lectureEndTime);
        request.content(content);

        Lecture.LectureBuilder entity = Lecture.builder();
        entity.speakerName(speakerName);
        entity.location(location);
        entity.lectureStartTime(LocalDateTime.parse(lectureStartTime, formatter));
        entity.lectureEndTime(LocalDateTime.parse(lectureEndTime, formatter));
        entity.content(content);

        // When
        lectureService.createLecture(request.build());

        // Then
        verify(lectureRepository, times(1)).save(entity.build());
    }

    @DisplayName("강연별 신청자 목록")
    @Test
    void getLectureAttendeeList() {
        // Given
        LectureAttendeeDto.Res.ResBuilder response = LectureAttendeeDto.Res.builder();
        response.lectureMainId(lectureMainId);
        response.employeeId(employeeId);
        response.delYn(delYn);

        LectureAttendee.LectureAttendeeBuilder<?, ?> entity = LectureAttendee.builder();
        entity.lecture(Lecture.builder().lectureMainId(lectureMainId).build());
        entity.employeeId(employeeId);
        entity.delYn(delYn);

        // When
        when(lectureAttendeeRepository.findByDelYnAndLectureMainId(null, null)).thenReturn(List.of(entity.build()));

        // Then
        assertThat(lectureService.getLectureAttendeeList(null, null))
                .usingRecursiveComparison()
                .isEqualTo(List.of(response.build()));
    }
}
