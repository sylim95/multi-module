package com.task.back.mapper;

import com.task.back.dto.LectureDto;
import com.task.common.domain.Lecture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * packageName    : com.task.back.mapper
 * fileName       : LectureMapper
 * author         : limsooyoung
 * date           : 6/4/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 6/4/24        limsooyoung       최초 생성
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LectureMapper {

    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

    List<LectureDto.Res> toDto(List<Lecture> request);

    @Mapping(target = "lectureStartTime", source = "lectureStartTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(target = "lectureEndTime", source = "lectureEndTime", qualifiedByName = "stringToLocalDateTime")
    Lecture toEntity(LectureDto.Req request);

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, format);
    }
}
