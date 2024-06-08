package com.task.back.controller;

import com.task.back.dto.LectureAttendeeDto;
import com.task.back.dto.LectureDto;
import com.task.back.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "강연 Back-Office API")
@Slf4j
@RequestMapping("/admin/v1/lecture")
@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("")
    @Operation(summary = "전체 강연 목록", description = "전체 강연 목록 API")
    public List<LectureDto.Res> getLectureList() {
        return lectureService.getLectureList();
    }

    @PostMapping("")
    @Operation(summary = "강연 등록", description = "강연 등록 API")
    public void createLecture(@Valid @RequestBody LectureDto.Req request) {
        lectureService.createLecture(request);
    }

    @GetMapping("/attendee")
    @Operation(
            summary = "강연별 신청자 목록",
            description = """
            조회 조건: 강연 Id, 삭제 여부 (없을 시 전체 조회)
            강연별 신청자 목록 API""")
    public List<LectureAttendeeDto.Res> getLectureAttendeeList(
            @RequestParam(value = "lectureMainId", required = false) Integer lectureMainId,
            @RequestParam(value = "delYn", required = false) String delYn
    ) {
        return lectureService.getLectureAttendeeList(lectureMainId, delYn);
    }
}
