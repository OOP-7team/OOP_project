package project;

public class User {
    // 사용자 정보를 담는 클래스

    private String id;         // 사용자 아이디
    private String password;   // 사용자 비밀번호
    private String name;       // 사용자 이름
    private String school;     // 사용자 학교
    private String grade;      // 사용자 학년
    private String className;  // 사용자 반
    private String birthDate;  // 사용자 생년월일
    private String secret;     // 사용자 주민등록번호 뒷자리
    private String userType;   // 사용자 유형 (학생/교사)

    // 생성자: 모든 필드를 초기화
    public User(String id, String password, String name, String school, String grade, String className, String birthDate, String secret, String userType) {
        this.id = id;               // 아이디 초기화
        this.password = password;   // 비밀번호 초기화
        this.name = name;           // 이름 초기화
        this.school = school;       // 학교 초기화
        this.grade = grade;         // 학년 초기화
        this.className = className; // 반 초기화
        this.birthDate = birthDate; // 생년월일 초기화
        this.secret = secret;       // 주민등록번호 뒷자리 초기화
        this.userType = userType;   // 사용자 유형 초기화
    }

    // 아이디를 반환하는 getter 메서드
    public String getId() {
        return id;
    }

    // 비밀번호를 반환하는 getter 메서드
    public String getPassword() {
        return password;
    }

    // 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        // 사용자 정보를 형식화된 문자열로 반환
        return String.format("아이디: %s\n이름: %s\n학교: %s\n학년: %s\n반: %s\n생년월일: %s\n신분: %s", 
                             id, name, school, grade, className, birthDate, userType);
    }
}
