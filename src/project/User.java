package project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<Quiz> quizzes = new ArrayList<>();
    private List<StudentQuiz> studentQuizzes = new ArrayList<>(); // 학생이 풀었던 퀴즈 답변 정보
    private String securityQuestion; // 보안 질문
    private String securityAnswer;   // 보안 질문 답변


    // 생성자: 모든 필드를 초기화
    public User(String userId, String password, String name, String school, String grade, String className, String birthDate, String secret, String userType, String securityQuestion, String securityAnswer) {
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
        this.securityQuestion = securityQuestion; // 보안 질문 초기화
        this.securityAnswer = securityAnswer;     // 보안 질문 답변 초기화

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
	
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	 public List<StudentQuiz> getStudentQuizzes() {
		return studentQuizzes;
	}

	public void setStudentQuizzes(List<StudentQuiz> studentQuizzes) {
		this.studentQuizzes = studentQuizzes;
	}

	// 보안 질문을 반환하는 getter 메서드
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    // 보안 질문을 설정하는 setter 메서드
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    // 보안 질문 답변을 반환하는 getter 메서드
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    // 보안 질문 답변을 설정하는 setter 메서드
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    
	public void addstudentQuiz(StudentQuiz studentQiuz) {
		List<StudentQuiz> studentQuizzes = getStudentQuizzes();
		studentQuizzes.add(studentQiuz);
	}

 // 객체의 문자열 표현을 반환하는 메서드
    @Override
    public String toString() {
        // 사용자 정보를 형식화된 문자열로 반환
        return String.format("아이디: %s\n이름: %s\n학교: %s\n학년: %s\n반: %s\n생년월일: %s\n신분: %s\n보안질문: %s\n보안답변: %s",
                userId, name, school, grade, className, birthDate, userType, securityQuestion, securityAnswer);
    }
    
    // 비밀번호 변경을 위한 메서드
    public boolean changePassword(String enteredUsername, String enteredAnswer, String newPassword) {
        // 입력된 아이디와 보안 질문 답변이 기존 정보와 일치하는지 확인
        if (enteredUsername.equals(userId) && enteredAnswer.equals(securityAnswer)) {
            // 아이디와 보안 질문 답변이 일치하면 비밀번호 변경
            this.password = newPassword;
            return true;
        } else {
            // 일치하지 않으면 비밀번호 변경 실패
            return false;
        }
    }
}
