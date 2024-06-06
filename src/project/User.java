package project;

public class User {
    // 사용자 정보를 담는 클래스

    private String userId;         // 사용자 아이디
    private String password;   // 사용자 비밀번호
    private String name;       // 사용자 이름
    private String school;     // 사용자 학교
    private String grade;      // 사용자 학년
    private String className;  // 사용자 반
    private String birthDate;  // 사용자 생년월일
    private String secret;     // 사용자 주민등록번호 뒷자리
    private String userType;   // 사용자 유형 (학생/교사)
    private int studyTime;    // 사용자 공부 시간

    // 생성자: 모든 필드를 초기화
    public User(String userId, String password, String name, String school, String grade, String className, String birthDate, String secret, String userType) {
        this.userId = userId;               // 아이디 초기화
        this.password = password;   // 비밀번호 초기화
        this.name = name;           // 이름 초기화
        this.school = school;       // 학교 초기화
        this.grade = grade;         // 학년 초기화
        this.className = className; // 반 초기화
        this.birthDate = birthDate; // 생년월일 초기화
        this.secret = secret;       // 주민등록번호 뒷자리 초기화
        this.userType = userType;   // 사용자 유형 초기화
        this.studyTime = 0;
    }

    // 아이디를 반환하는 getter 메서드
    public String getUserId() {
        return userId;
    }

    // 비밀번호를 반환하는 getter 메서드
    public String getPassword() {
        return password;
    }
    
    // 이름을 반환하는 getter 메서드
    public String getName() {
    	return name;
    }

    public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(int studyTime) {
		this.studyTime = studyTime;
	}

	// 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        // 사용자 정보를 형식화된 문자열로 반환
        return String.format("아이디: %s\n이름: %s\n학교: %s\n학년: %s\n반: %s\n생년월일: %s\n신분: %s", 
        		userId, name, school, grade, className, birthDate, userType);
    }
}
