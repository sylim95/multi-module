package com.task.common.domain;

import com.task.common.common.code.ReturnCode;
import com.task.common.error.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.task.common.domain
 * fileName       : Lecture
 * author         : limsooyoung
 * date           : 6/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/2/24        limsooyoung       최초 생성
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "lecture_main")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_main_id")
    private Integer lectureMainId;

    @Comment("강연자")
    @Column(name = "speaker_name")
    private String speakerName;

    @Comment("강연장")
    @Column(name = "location")
    private String location;

    @Builder.Default
    @Comment("신청 인원")
    @Column(name = "attendee_count")
    private Integer attendeeCount = 0;

    @Comment("신청 가능 인원")
    @Column(name = "total_count")
    private Integer totalCount;

    @Comment("강연 시작 시간")
    @Column(name = "lecture_start_time")
    private LocalDateTime lectureStartTime;

    @Comment("강연 종료 시간")
    @Column(name = "lecture_end_time")
    private LocalDateTime lectureEndTime;

    @Comment("강연 내용")
    @Column(name = "content")
    private String content;

    @Builder.Default
    @Comment("강연 신청자")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lecture")
    List<LectureAttendee> lectureAttendees = new ArrayList<>();

    public void increaseAttendeeCount() {
        int count = this.attendeeCount + 1;
        if(count > this.totalCount) {
            throw BusinessException.of("신청 인원을 초과하였습니다.", ReturnCode.ERROR_500);
        }
        this.attendeeCount = count;
    }

    public void decreaseAttendeeCount() {
        int count = this.attendeeCount - 1;
        if(count < 0) {
            throw BusinessException.of("신청 내역을 삭제하는 데 문제가 발생하였습니다.", ReturnCode.ERROR_500);
        }
        this.attendeeCount = count;
    }
}
