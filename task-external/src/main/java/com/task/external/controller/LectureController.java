package com.task.external.controller;

import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import com.task.external.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "강연 Front API")
@Slf4j
@RequestMapping("/v1/lecture")
@RequiredArgsConstructor
@RestController
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("")
    @Operation(summary = "강연 목록", description = "강연 시작 시간 1주일 전부터 강연 시작 시간 1일 후까지의 강연 목록 API")
    public List<LectureDto.Res> getLectureList() {
        return lectureService.getLectureList();
    }

    @PostMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "강연 신청",
            description = """ 
            강연 신청 API
            중복 신청 및 종료된 강연에 대한 신청 불가""")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLectureAttendee(
            @NotEmpty @PathVariable("employee-id") String employeeId,
            @Valid @RequestBody LectureAttendeeDto.Req request) {
        request.setEmployeeId(employeeId);
        lectureService.creatLectureAttendee(request);
    }

    @GetMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "강연 신청 내역 조회", description = "강연 신청 내역 조회 API")
    public List<LectureAttendeeDto.Res> getLectureAttendee(@NotNull @PathVariable("employee-id") String employeeId) {
        return lectureService.getLectureAttendee(employeeId);
    }

    @DeleteMapping(value = "/attendee/{employee-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "신청한 강연 취소", description = "신청한 강연 취소 API")
    public void removeLectureAttendee(
            @NotEmpty @PathVariable("employee-id") String employeeId,
            @Valid @RequestBody LectureAttendeeDto.Req request) {
        request.setEmployeeId(employeeId);
        lectureService.removeLectureAttendee(request);
    }

    // 실시간 인기 강연
    @GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "실시간 인기 강연", description = "3일간 가장 신청이 많은 강연 순으로 조회하는 API")
    public List<LectureAttendeeDto.CountRes> getPopularLecture() {
        return lectureService.getPopularLecture();
    }

}
