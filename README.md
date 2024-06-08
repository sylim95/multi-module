# Project Summary

이 프로젝트는 Java, Spring Boot, Gradle 그리고 H2를 사용한 멀티모듈 프로젝트 입니다.
모듈은 task-back, task-common, task-external 로 이루어져 있습니다. 

## 모듈

* task-back - 백오피스 관련 서비스(모든 강연 조회, 강연 등록 등)를 포함하고 있습니다.
* task-common - entity 및 DB 세팅, 에러 정보, 기본 환경 설정 등을 포함하고 있습니다.
* task-external - 모듈은 외부 공개용 서비스(강연 신청, 실시간 인기 강연 조회 등)를 포함하고 있습니다.

## API 문서

이 프로젝트는 Springdoc OpenAPI를 사용하여 API 문서를 자동으로 생성합니다. 로컬에서 프로젝트를 실행한 후, 웹 브라우저에서 다음 URL을 방문하여 API 문서를 확인할 수 있습니다:

* http://localhost:8080/lecture/swagger.html

이 페이지에서는 모든 API 엔드포인트, 요청 및 응답 형식에 대한 정보를 찾을 수 있습니다. 또한, 이 페이지에서 직접 API 요청을 보내고 응답을 확인하는 것도 가능합니다.
(프로젝트 내 모든 응답/객체를 확인할 수 있는 모델은 제공하지 않습니다.)

## 사용된 도구 요약

* Java 17 - 사용된 프로그래밍 언어
* Spring Boot 3 - 사용된 프레임워크
* Gradle - 의존성 관리 도구
* Lombok - Java 라이브러리, 코드 단순화 도구
* springdoc-openapi - API 문서화 도구
* H2(Embedded) - 자바로 작성된 관계형 데이터베이스 관리 시스템
* JPA - Java Persistence API, 데이터 접근 및 관리를 위한 표준
* MapStruct - Java bean 매핑 도구

## 에러 응답 포맷
```json
{
    "code": "ERROR" ,
    "message": "",
    "errors": [
        {
            "field": null,
            "value": null,
            "reason": ""
        }
    ]
}
```

## 데이터베이스 구조
### 강연 테이블(lecture_main)
* lecture_main_id: 고유한 식별자로 자동 생성됩니다.
* speaker_name: 강연자 이름으로 필수값입니다.
* location: 강연장 정보 입니다.
* attendee_count: 강연 신청 인원 으로 필수 값이고, 기본값 0입니다.
* total_count: 강연 신청 가능 인원 으로 필수 값입니다.
* lecture_start_time: 강연 시작 시간 으로 필수 값입니다.
* lecture_end_time: 강연 종료 시간 으로 필수 값입니다.
* content: 강연 내용 입니다.

### 강연 신청자 테이블(lecture_attendee)
강연 테이블과 1:N 연관 관계를 가집니다.

* lecture_attendee_id: 고유한 식별자로 자동 생성됩니다.
* lecture_main_id: 강연 PK 아이디로, 강연 테이블을 참조합니다.
* employee_id: 신청자 아이디(사번) 입니다.
* del_yn: 삭제 여부 입니다. 삭제 시 hard delete가 아닌 해당 컬럼으로 soft delete가 진행됩니다.
* reg_date: 신청 등록 일자 입니다.
* mod_date: 신청 수정 일자 입니다.

## 부가 정보
모듈 간의 데이터 의사 소통을 위해 임베디드 데이터베이스를 사용하고 있습니다.
따라서 멀티 모듈로 작성했으나 각 모듈의 포트를 설정하지 않았습니다.
서버 실행 시 각 모듈 별로 실행 및 종료를 진행해 주시면 됩니다.

## 고민 했던 점
* 만일 데이터가 많아 성능이 우려되는 상황이라면?
  * 현재 결과 전체를 조회하여 자바 코드로 필터하여 리턴하는데, 데이터가 매우 많을 경우 다르게 생각할 수 있습니다.
    1. 인덱스 적용, 그래도 성능이 떨어진다면 페이징을 고려할 수 있습니다.
    2. 페이징을 적용한다면 전체 데이터가 아닌 쿼리로 필요한 데이터만 가져오는 것이 효율적입니다.
    3. querydsl 도입을 고려해볼 수 있습니다.
* 락 구현시 고려사항
  * 락이 오랫동안 유지되면 데드락이 발생할 가능성이 있어 LOCK_TIMEOUT을 10,000ms(10s)로 설정하였습니다.

