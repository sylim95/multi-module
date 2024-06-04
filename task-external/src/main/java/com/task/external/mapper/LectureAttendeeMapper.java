package com.task.external.mapper;

import com.task.common.domain.LectureAttendee;
import com.task.external.dto.LectureAttendeeDto;
import com.task.external.dto.LectureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * packageName    : com.task.external.mapper
 * fileName       : LectureAttendeeMapper
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Mapper
public interface LectureAttendeeMapper {

    LectureAttendeeMapper INSTANCE = Mappers.getMapper(LectureAttendeeMapper.class);

    @Mapping(target = "lecture.lectureMainId", source = "lectureMainId")
    LectureAttendee toEntity(LectureAttendeeDto.Req request);

    @Mapping(source = "lecture.lectureMainId", target = "lectureMainId")
    @Mapping(source = "lecture.speakerName", target = "speakerName")
    LectureAttendeeDto.Res toDto(LectureAttendee request);

    List<LectureAttendeeDto.Res> toDtoList(List<LectureAttendee> list);
}
