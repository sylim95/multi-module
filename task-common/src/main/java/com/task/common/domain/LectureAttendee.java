package com.task.common.domain;

import com.task.common.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * packageName    : com.task.common.domain
 * fileName       : LectureAttendee
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
@Table(name = "lecture_attendee")
public class LectureAttendee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_attendee_id")
    private Integer lectureAttendeeId;

    @ManyToOne
    @Comment("강연 아이디")
    @JoinColumn(name = "lecture_main_id")
    private Lecture lecture;

    @Comment("신청자 아이디")
    @Column(name = "employee_id")
    private String employeeId;

    @Builder.Default
    @Comment("삭제 여부")
    @Column(name = "del_yn")
    private String delYn = "N";

    public void delete() {
        this.delYn = "Y";
    }
}
