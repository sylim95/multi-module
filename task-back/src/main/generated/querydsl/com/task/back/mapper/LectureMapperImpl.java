package com.task.back.mapper;

import com.task.back.dto.LectureDto;
import com.task.common.domain.Lecture;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-07T14:30:25+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class LectureMapperImpl implements LectureMapper {

    @Override
    public List<LectureDto.Res> toDto(List<Lecture> request) {
        if ( request == null ) {
            return null;
        }

        List<LectureDto.Res> list = new ArrayList<LectureDto.Res>( request.size() );
        for ( Lecture lecture : request ) {
            list.add( lectureToRes( lecture ) );
        }

        return list;
    }

    @Override
    public Lecture toEntity(LectureDto.Req request) {
        if ( request == null ) {
            return null;
        }

        Lecture.LectureBuilder lecture = Lecture.builder();

        lecture.lectureStartTime( stringToLocalDateTime( request.getLectureStartTime() ) );
        lecture.lectureEndTime( stringToLocalDateTime( request.getLectureEndTime() ) );
        lecture.speakerName( request.getSpeakerName() );
        lecture.location( request.getLocation() );
        lecture.totalCount( request.getTotalCount() );
        lecture.content( request.getContent() );

        return lecture.build();
    }

    protected LectureDto.Res lectureToRes(Lecture lecture) {
        if ( lecture == null ) {
            return null;
        }

        LectureDto.Res.ResBuilder res = LectureDto.Res.builder();

        res.lectureMainId( lecture.getLectureMainId() );
        res.speakerName( lecture.getSpeakerName() );
        res.location( lecture.getLocation() );
        res.attendeeCount( lecture.getAttendeeCount() );
        res.totalCount( lecture.getTotalCount() );
        if ( lecture.getLectureStartTime() != null ) {
            res.lectureStartTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( lecture.getLectureStartTime() ) );
        }
        if ( lecture.getLectureEndTime() != null ) {
            res.lectureEndTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( lecture.getLectureEndTime() ) );
        }
        res.content( lecture.getContent() );

        return res.build();
    }
}
