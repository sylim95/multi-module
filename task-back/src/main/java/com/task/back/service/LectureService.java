package com.task.back.service;

import com.task.back.dto.LectureDto;
import com.task.back.mapper.LectureMapper;
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

    private static final LectureMapper LECTURE_MAPPER = LectureMapper.INSTANCE;

    @Transactional(readOnly = true)
    public List<LectureDto.Res> getLectureList() {
        return LECTURE_MAPPER.toDto(lectureRepository.findAllLectureWithoutAttendees());
    }

    @Transactional
    public void createLecture(LectureDto.Req request) {
        lectureRepository.save(LECTURE_MAPPER.toEntity(request));
    }
}
