package com.task.back.service;

import com.task.back.dto.LectureAttendeeDto;
import com.task.back.dto.LectureDto;
import com.task.back.mapper.LectureAttendeeMapper;
import com.task.back.mapper.LectureMapper;
import com.task.back.repository.LectureAttendeeRepository;
import com.task.back.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.task.back.service
 * fileName       : LectureService
 * author         : limsooyoung
 * date           : 6/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/2/24        limsooyoung       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    private final LectureAttendeeRepository lectureAttendeeRepository;

    private static final LectureMapper LECTURE_MAPPER = LectureMapper.INSTANCE;

    private static final LectureAttendeeMapper LECTURE_ATTENDEE_MAPPER = LectureAttendeeMapper.INSTANCE;

    /**
     * 강연 전체 조회
     * @return List <LectureDto.Res>
     * */
    @Transactional(readOnly = true)
    public List<LectureDto.Res> getLectureList() {
        return LECTURE_MAPPER.toDto(lectureRepository.findAllLectureWithoutAttendees());
    }

    /**
     * 강연 등록
     * @param request
     * */
    @Transactional
    public void createLecture(LectureDto.Req request) {
        lectureRepository.save(LECTURE_MAPPER.toEntity(request));
    }

    /**
     * 강연 신청자 목록 조회
     * @return List <LectureAttendeeDto.Res>
     * */
    @Transactional(readOnly = true)
    public List<LectureAttendeeDto.Res> getLectureAttendeeList(Integer lectureMainId, String delYn) {
        return LECTURE_ATTENDEE_MAPPER.toDtoList(lectureAttendeeRepository.findByDelYnAndLectureMainId(lectureMainId, delYn));
    }
}
