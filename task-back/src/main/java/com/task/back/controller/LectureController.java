package com.task.back.controller;

import com.task.back.dto.LectureDto;
import com.task.back.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.task.back.controller
 * fileName       : LectureController
 * author         : limsooyoung
 * date           : 6/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/2/24        limsooyoung       최초 생성
 */
@Slf4j
@RequestMapping("/admin/v1/lecture")
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 전체 강연 목록
    @GetMapping("")
    public List<LectureDto.Res> getLectureList() {
        return lectureService.getLectureList();
    }

    // 강연 등록
    @PostMapping("")
    public void createLecture(@Valid @RequestBody LectureDto.Req request) {
        lectureService.createLecture(request);
    }
}
