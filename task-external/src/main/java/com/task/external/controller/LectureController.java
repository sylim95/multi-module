package com.task.external.controller;

import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureAttendeeInterface;
import com.task.external.dto.LectureDto;
import com.task.external.service.LectureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.task.external.controller
 * fileName       : LectureController
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Slf4j
@RequestMapping("/v1/lecture")
@RequiredArgsConstructor
@RestController
public class LectureController {

    private final LectureService lectureService;

    // 강연 목록
    @GetMapping("")
    public List<LectureDto.Res> getLectureList() {
        return lectureService.getLectureList();
    }

    // 강연 신청
    @PostMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createLectureAttendee(
            @NotEmpty @PathVariable("employee-id") String employeeId,
            @Valid @RequestBody LectureAttendeeDto.Req request) {
        request.setEmployeeId(employeeId);
        lectureService.creatLectureAttendee(request);
    }

    // 강연 신청 내역 조회
    @GetMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LectureAttendeeDto.Res> getLectureAttendee(@NotNull @PathVariable("employee-id") String employeeId) {
        return lectureService.getLectureAttendee(employeeId);
    }

    // 신청한 강연 취소
    @DeleteMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeLectureAttendee(
            @NotEmpty @PathVariable("employee-id") String employeeId,
            @Valid @RequestBody LectureAttendeeDto.Req request) {
        request.setEmployeeId(employeeId);
        lectureService.removeLectureAttendee(request);
    }

    // 실시간 인기 강연
    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LectureAttendeeInterface> getPopularLecture() {
        return lectureService.getPopularLecture();
    }

}
