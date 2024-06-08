package com.task.external;

import com.task.common.error.exception.BusinessException;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import com.task.external.service.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * packageName    : com.task.external
 * fileName       : TaskExternalApplicationTests
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskExternalApplicationTests {
    @Autowired
    private LectureService lectureService;

    @DisplayName("강연 신청 동시 제어 테스트")
    @Test
    void increaseAttendeeCount() throws Exception {
        AtomicInteger successCount = new AtomicInteger();
        int execute = 100;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(execute);

        for(int i = 0; i < execute; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    lectureService.creatLectureAttendee(LectureAttendeeDto.Req.builder().lectureMainId(1).employeeId(String.valueOf(finalI)).build());
                    successCount.getAndIncrement();
                    System.out.println("신청 성공: " + finalI);
                } catch(BusinessException e) {
                    System.out.println("신청 실패: " + finalI + " - " + e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();
        service.shutdown();

        assertEquals(lectureService.getLectureList().stream()
                .filter(lecture -> lecture.getLectureMainId().equals(1))
                .map(LectureDto.Res::getTotalCount).findFirst().orElse(0), successCount.get());
    }

    @DisplayName("강연 취소 동시 제어 테스트")
    @Test
    void decreaseAttendeeCount() throws Exception {
        AtomicInteger successCount = new AtomicInteger();
        int execute = 100;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(execute);

        for(int i = 0; i < execute; i++) {
            int finalI = i;
            service.execute(() -> {
                try {
                    lectureService.removeLectureAttendee(LectureAttendeeDto.Req.builder().lectureMainId(1).employeeId(String.valueOf(finalI)).build());
                    successCount.getAndIncrement();
                    System.out.println("신청 취소 성공: " + finalI);
                } catch(BusinessException e) {
                    System.out.println("신청 취소 실패: " + finalI + " - " + e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();
        service.shutdown();

        assertEquals(lectureService.getLectureList().stream()
                .filter(lecture -> lecture.getLectureMainId().equals(1))
                .map(LectureDto.Res::getTotalCount).findFirst().orElse(0), successCount.get());
    }
}
