package com.task.back.mapper;

import com.task.back.dto.LectureAttendeeDto;
import com.task.common.domain.Lecture;
import com.task.common.domain.LectureAttendee;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-07T14:30:25+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
public class LectureAttendeeMapperImpl implements LectureAttendeeMapper {

    @Override
    public LectureAttendeeDto.Res toDto(LectureAttendee request) {
        if ( request == null ) {
            return null;
        }

        LectureAttendeeDto.Res.ResBuilder res = LectureAttendeeDto.Res.builder();

        res.lectureMainId( requestLectureLectureMainId( request ) );
        res.lectureAttendeeId( request.getLectureAttendeeId() );
        res.employeeId( request.getEmployeeId() );
        res.delYn( request.getDelYn() );
        if ( request.getRegDate() != null ) {
            res.regDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( request.getRegDate() ) );
        }
        if ( request.getModDate() != null ) {
            res.modDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( request.getModDate() ) );
        }

        return res.build();
    }

    @Override
    public List<LectureAttendeeDto.Res> toDtoList(List<LectureAttendee> list) {
        if ( list == null ) {
            return null;
        }

        List<LectureAttendeeDto.Res> list1 = new ArrayList<LectureAttendeeDto.Res>( list.size() );
        for ( LectureAttendee lectureAttendee : list ) {
            list1.add( toDto( lectureAttendee ) );
        }

        return list1;
    }

    private Integer requestLectureLectureMainId(LectureAttendee lectureAttendee) {
        if ( lectureAttendee == null ) {
            return null;
        }
        Lecture lecture = lectureAttendee.getLecture();
        if ( lecture == null ) {
            return null;
        }
        Integer lectureMainId = lecture.getLectureMainId();
        if ( lectureMainId == null ) {
            return null;
        }
        return lectureMainId;
    }
}
