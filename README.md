# 공공데이터 이용 주변 대피소 찾기 API 서버
# 👋소개
* 외부 API(카카오, 네이버 지도 검색)를 이용한 위치 검색 결과를 바탕으로 주위 대피소를 검색하는 서버입니다.
<br>

# ⚙️세부 기술스택
* Java 17
* Spring Framework
  * Spring Data Jpa 3.1.1
  * Spring Data Redis 3.1.1
  * Spring retry 2.0.2
  * Spring Web Mvc 6.0.10
* DB
  * H2 DB, Mysql 8(Docker)
  * Redis(Docker)
* opencsv 5.5(공공데이터 변환 목적)
* proj4j: 1.3.0(좌표정보 변환 목적)
<br>

# 🗃️프로젝트 구조
<details>
<summary>핵심 구조도</summary>
  
![구조](https://github.com/galmegiz/shelter-search-project/assets/126640838/c4262e8e-6ec7-48a5-9b7d-4ee6c3fb025b)

</details>

* Presentation Module : 사용자 입력 및 입력값 검증, 예외처리 로직 포함
* Domain Module : 프로젝트 핵심 모듈
  * 공공데이터 CSV파일 추출 및 좌표정보 변환 후 DB 저장
  * 검색 결과 Cache 저장
* External-api Module : 외부 API(카카오, 네이버) 연동, 장애 시 복구 기능 구현
<br>

# 👉주요 기능
 * [핵심구현 요소] <span>**시작 옵션을 통한 Standalone/DB모드 전환**</span>
    * 스프링 애노테이션(@ConditionalOnProperty)을 통한 빈 동적 생성 및 주입
    * Standalone 사용 시 In-memory db(h2), ConcurrnetHas를 이용한 cache 사용
    * DB 모드 사용 시 Mysql, Redis(Cache)를 사용
 * [핵심구현 요소] <span style="color:red">**Cache 이용 서비스 응답속도 향상 및 외부 API 호출 횟수 감소**</span>
   * Cache 이용 시 응답 속도 500 ~ 3000ms -> 10ms으로 단축
 * [핵심구현 요소] <span>**인터페이스 기반 외부 연동 API 구현하여 향후 추가 API 연동 용이**</span>
    * 특정 외부 API 장애 시 다음 외부 API 자동 호출
    * 추가 API 연결 시 API 클래스 작성 외 기존 로직 수정 불필요
 * 핵심 모듈과 Presentation, 외부 연동 모듈을 분리하여 유지보수성 향상
 * OpenCsv 이용 공공데이터 필터링 및 DB 저장
 * 공공 데이터 좌표 데이터 변환(공공데이터 좌표 EPSG2097 -> EPSG4326)
 * Haversine 알고리즘 이용 지정 좌표 최단 거리 대피소 10개 계산
 
  
<br>
