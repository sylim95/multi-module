package com.task.external;

import com.task.common.error.exception.BusinessException;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.service.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
@SpringBootTest
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
            int finalI = 1;
            service.execute(() -> {
                try {
                    lectureService.creatLectureAttendee(LectureAttendeeDto.Req.builder().lectureMainId(1).employeeId(String.valueOf(finalI)).build());
                    successCount.getAndIncrement();
                    System.out.println("성공");
                } catch(BusinessException e) {
                    System.out.println(e);
                }
                latch.countDown();
            });
        }
        latch.await();
    }
}
