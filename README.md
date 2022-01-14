# exampleSpringBootSecurity

### **1. Description**
* **Spring Boot**를 기반으로 **Spring Security**를 사용한 간단한 Security 웹 프로젝트
  + 로그인, 로그아웃, 회원가입 기능 구현
* **Spring Boot Version: 2.4.5**

### **2. Environment**
* **IDE**
  + Intellij IDEA
* **Database**
  + MySQL
* **Server**
  + Tomcat
  
  
### **3. Packages & Class**
* **conf package**
  + View Controller 설정 및 Authentication & Authorization을 처리하는 Class
  + **WebConfig, WebSecurityConfig**
* **controller package**
  + RegistrationService의 객체를 사용하여 사용자 요청(회원가입)을 처리하는 Class
  + **RegistrationController**
* **entity package**
  + Entity 정보를 저장하는 Class
  + **Role, User**
* **repository package**
  + Spring Data JPA를 사용하여 CRUD 기능을 구현한 Class
  + **RoleRepository, UserRepository**
* **service package**
  + **CustomUserDetailsService**: Spring Security에서 사용자의 정보를 가져오는 인터페이스
  + **RegistrationService Interface & RegistrationServiceImpl**: 회원가입 절차를 수행
    - **BCryptPasswordEncoder**를 사용하여 비밀번호 암호화
    - ID & PW 예시: alice@naver.com & alicepw


### **4. 주요 Files**
* **pom.xml**
  + 프로젝트 기본 정보 및 Dependency 관리
  + 사용한 Dependency
    - lombok
    - spring-boot-configuration-processor
    - spring-boot-starter-thymeleaf
    - spring-boot-starter-web
    - spring-boot-starter-devtools
    - spring-boot-starter-data-jpa
    - mysql-connector-java
    - spring-boot-starter-security
    - thymeleaf-extras-springsecurity5
* **Templates**
  + **Thymeleaf**를 사용한 웹 페이지
  + **home.html**: 애플리케이션 실행 시 나타나는 메인 페이지(로그인 및 회원가입 가능)
  + **adminhome.html**: 관리자 권한을 가진 사용자가 접근 가능한 페이지
  + **login.html**: 로그인 페이지
  + **signup.html**: 회원가입 페이지
  + **403.html**: 인가받지 않은 사용자가 접근한 경우 나타나는 오류 페이지
* **application.properties**
  + 애플리케이션 속성(Property) 정보를 작성하는 파일
    - **Data Source, JPA & Hibernate, Logging** 관련 설정
* **data.sql**
  + 테이블에 저장될 데이터
  + 사용자(User) 및 권한(Role) 관련 정보
