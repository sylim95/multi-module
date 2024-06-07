package com.task.external.service;

import com.task.common.common.code.ReturnCode;
import com.task.common.domain.Lecture;
import com.task.common.domain.LectureAttendee;
import com.task.common.error.exception.BusinessException;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import com.task.external.mapper.LectureAttendeeMapper;
import com.task.external.mapper.LectureMapper;
import com.task.external.repository.LectureAttendeeRepository;
import com.task.external.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * 강연 시작 시간 1주일 전부터 강연 시작 시간 1일 후까지 강연 목록 조회
     * @return List <LectureDto.Res>
     * */
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

    /**
     * 강연 신청 - 중복 신청 제한
     * @param request
     * @exception 400 error
     * */
    @Transactional
    public void creatLectureAttendee(LectureAttendeeDto.Req request) {
        Lecture lecture = lectureRepository.findByIdForUpdate(request.getLectureMainId())
                .orElseThrow(() -> BusinessException.of("해당하는 강연 정보가 없습니다.", ReturnCode.ERROR_400));

        // 중복 신청 제한
        if(lectureAttendeeRepository.existsByLecture_LectureMainIdAndEmployeeIdAndDelYn(request.getLectureMainId(), request.getEmployeeId(), "N")) {
            throw BusinessException.of("이미 신청 내역이 있습니다.", ReturnCode.ERROR_400);
        }

        // 종료된 강연일 경우
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lectureEndTime = lecture.getLectureEndTime();
        if(now.isAfter(lectureEndTime)) {
            throw BusinessException.of("종료된 강연입니다.", ReturnCode.ERROR_400);
        }

        lectureAttendeeRepository.save(LECTURE_ATTENDEE_MAPPER.toEntity(request));

        lecture.increaseAttendeeCount();
    }

    /**
     * 신청 내역 조회
     * @param employeeId
     * @return List <LectureAttendeeDto.Res>
     * */
    @Transactional(readOnly = true)
    public List<LectureAttendeeDto.Res> getLectureAttendee(String employeeId) {
        List<LectureAttendee> response = lectureAttendeeRepository.findByEmployeeIdAndDelYn(employeeId, "N");

        return LECTURE_ATTENDEE_MAPPER.toDtoList(response)
                .stream()
                .sorted(Comparator.comparing(LectureAttendeeDto.Res::getRegDate))
                .toList();
    }

    /**
     * 강연 신청 취소 & 강연 신청자 카운트 변경
     * @param request
     * @exception 400 error
     * */
    @Transactional
    public void removeLectureAttendee(LectureAttendeeDto.Req request) {
        LectureAttendee lectureAttendee = lectureAttendeeRepository.findByLecture_LectureMainIdAndEmployeeIdAndDelYn(request.getLectureMainId(), request.getEmployeeId(), "N");
        if(ObjectUtils.isEmpty(lectureAttendee)) {
            throw BusinessException.of("강연 신청 내역이 없습니다.", ReturnCode.ERROR_400);
        }

        lectureAttendee.delete();
    }

    /**
     * 3일간 가장 신청이 많은 강연 순으로 조회
     * if> 데이터가 너무 많아 인덱스만으로 성능 개선이 어렵다면?
     *     -> 페이징 적용 가능, 자바 소스 단이 아닌 쿼리로 해결
     * @return List <LectureAttendeeInterface>
     * */
    @Transactional(readOnly = true)
    public List<LectureAttendeeDto.CountRes> getPopularLecture() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysAgo = now.minusDays(3L);

        List<LectureAttendee> response = lectureAttendeeRepository.findAllWithOutLectureMain();

        Map<Integer, Long> lectureAttendeeCountMap = response.stream()
                .filter(e -> Objects.equals("N", e.getDelYn()))
                .filter(e -> now.compareTo(e.getRegDate()) >= 0 && threeDaysAgo.compareTo(e.getRegDate()) < 0)
                .collect(Collectors.groupingBy(e -> e.getLecture().getLectureMainId(), Collectors.counting()));

        return lectureAttendeeCountMap.entrySet().stream()
                .map(entry -> LectureAttendeeDto.CountRes.builder()
                        .lectureMainId(entry.getKey())
                        .count(entry.getValue().intValue())
                        .build())
                .sorted(Comparator.comparingInt(LectureAttendeeDto.CountRes::getCount).reversed())
                .toList();
    }
}
