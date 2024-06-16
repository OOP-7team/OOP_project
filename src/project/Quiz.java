package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Quiz 클래스
public class Quiz {
	public static int id = 0;
	private int quizId;
    private String title;
    private String subject; // 과목
    private String grade; // 학년
    private String className; // 반
    private List<Question> questions = new ArrayList<>(); // 문제들
    private LocalDateTime createdDateTime; // 퀴즈 등록 일시
    private LocalDateTime dueDateTime; // 퀴즈 마감 일시

    public Quiz(String title, String subject, String grade, String className, LocalDateTime dueDateTime) {
        this.quizId = id++;
        this.title = title;
        this.subject = subject;
        this.grade = grade;
        this.className = className;
        this.createdDateTime = LocalDateTime.now(); // 등록될 때 바로 시간 담기
        this.dueDateTime = dueDateTime;
    }

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(LocalDateTime dueDateTime) {
		this.dueDateTime = dueDateTime;
	}
    
	@Override
    public String toString() {
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH시 mm분 ss초까지");
        return title + "\t" +
               subject + "\t" +
               // createdDateTime.format(formatter1) + "\t" +
               dueDateTime.format(formatter2);
               // questions.size() + "개";
    }
    
}