package com.task.external.service;

import com.task.common.common.code.ReturnCode;
import com.task.common.domain.Lecture;
import com.task.common.domain.LectureAttendee;
import com.task.common.error.exception.BusinessException;
import com.task.external.dto.LectureAttendeeInterface;
import com.task.external.repository.LectureAttendeeRepository;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import com.task.external.mapper.LectureAttendeeMapper;
import com.task.external.mapper.LectureMapper;
import com.task.external.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

/**
 * packageName    : com.task.external.service
 * fileName       : LectureService
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    private final LectureAttendeeRepository lectureAttendeeRepository;

    private static final LectureMapper LECTURE_MAPPER = LectureMapper.INSTANCE;
    private static final LectureAttendeeMapper LECTURE_ATTENDEE_MAPPER = LectureAttendeeMapper.INSTANCE;

    @Transactional(readOnly = true)
    public List<LectureDto.Res> getLectureList() {
        List<Lecture> response = lectureRepository.findAllLectureWithoutAttendees();

        LocalDate now = LocalDate.now();
        // 강연 시작 시간 1주일 전부터 강연 시작 시간 1일 후까지
        List<Lecture> lectures = response.stream()
                .filter(lecture -> {
                    LocalDate lectureStartDate = lecture.getLectureStartTime().toLocalDate();
                    long betweenDays = ChronoUnit.DAYS.between(now, lectureStartDate);

                    return (betweenDays >= 0 && betweenDays <= 7) || betweenDays == -1;
                }).toList();
        return LECTURE_MAPPER.toDtoList(lectures);
    }

    @Transactional
    public void creatLectureAttendee(LectureAttendeeDto.Req request) {
        Lecture lecture = lectureRepository.findByIdForUpdate(request.getLectureMainId())
                .orElseThrow(() -> BusinessException.of("해당하는 강연 정보가 없습니다.", ReturnCode.ERROR_400));

        // 중복 신청 제한
        if(lectureAttendeeRepository.existsByLecture_LectureMainIdAndEmployeeIdAndDelYn(request.getLectureMainId(), request.getEmployeeId(), "N")) {
            throw BusinessException.of("이미 신청 내역이 있습니다.", ReturnCode.ERROR_400);
        }

        lectureAttendeeRepository.save(LECTURE_ATTENDEE_MAPPER.toEntity(request));

        lecture.increaseAttendeeCount();
    }

    @Transactional(readOnly = true)
    public List<LectureAttendeeDto.Res> getLectureAttendee(String employeeId) {
        List<LectureAttendee> response = lectureAttendeeRepository.findByEmployeeIdAndDelYn(employeeId, "N");

        return LECTURE_ATTENDEE_MAPPER.toDtoList(response)
                .stream()
                .sorted(Comparator.comparing(LectureAttendeeDto.Res::getLectureMainId))
                .toList();
    }

    @Transactional
    public void removeLectureAttendee(LectureAttendeeDto.Req request) {
        LectureAttendee lectureAttendee = lectureAttendeeRepository.findByLecture_LectureMainIdAndEmployeeIdAndDelYn(request.getLectureMainId(), request.getEmployeeId(), "N");
        if(ObjectUtils.isEmpty(lectureAttendee)) {
            throw BusinessException.of("강연 신청 내역이 없습니다.", ReturnCode.ERROR_400);
        }

        lectureAttendee.delete();
        lectureAttendee.getLecture().decreaseAttendeeCount();
    }

    @Transactional(readOnly = true)
    public List<LectureAttendeeInterface> getPopularLecture() {
        return lectureRepository.findLecturesOrderByAttendeeCount(LocalDate.now().minusDays(3L));
    }
}
