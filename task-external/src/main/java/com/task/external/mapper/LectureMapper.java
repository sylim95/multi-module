package com.task.external.mapper;

import com.task.common.domain.Lecture;
import com.task.external.dto.LectureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * packageName    : com.task.external.mapper
 * fileName       : LectureMapper
 * author         : limsooyoung
 * date           : 6/3/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/3/24        limsooyoung       최초 생성
 */
@Mapper
public interface LectureMapper {

    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

    @Mapping(target = "lectureStartTime", source = "lectureStartTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "lectureEndTime", source = "lectureEndTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    LectureDto.Res toDto(Lecture request);

    List<LectureDto.Res> toDtoList(List<Lecture> request);
}
