package com.task.back.mapper;

import com.task.back.dto.LectureAttendeeDto;
import com.task.common.domain.LectureAttendee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * packageName    : com.task.back.mapper
 * fileName       : LectureAttendeeMapper
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureAttendeeMapper {

    LectureAttendeeMapper INSTANCE = Mappers.getMapper(LectureAttendeeMapper.class);

    @Mapping(source = "lecture.lectureMainId", target = "lectureMainId")
    LectureAttendeeDto.Res toDto(LectureAttendee request);

    List<LectureAttendeeDto.Res> toDtoList(List<LectureAttendee> list);

}
