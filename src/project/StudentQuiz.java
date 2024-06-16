package project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentQuiz { // 학생이 제출한 퀴즈
	// private User student; // 현재 로그인해서 푼 학생 객체(static loginUser)
    private Quiz quiz; // 학생이 푼 퀴즈 객체
    private List<SubmittedAnswer> submittedAnswers = new ArrayList<>(); // 제출된 정답들
    private int score;
    private LocalDateTime submissionDateTime; // 제출된 시간

    public StudentQuiz(Quiz quiz, List<SubmittedAnswer> submittedAnswers, int score) {
        // this.student = student;
        this.quiz = quiz;
        this.submittedAnswers = submittedAnswers;
        this.score = score;
        this.submissionDateTime = LocalDateTime.now();
    }
    
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public List<SubmittedAnswer> getSubmittedAnswers() {
		return submittedAnswers;
	}

	public LocalDateTime getSubmissionDateTime() {
		return submissionDateTime;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public void setSubmittedAnswers(List<SubmittedAnswer> submittedAnswers) {
		this.submittedAnswers = submittedAnswers;
	}

	public void setSubmissionDateTime(LocalDateTime submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}

	
	public void addSubmittedAnswer(Question question, String submittedText, boolean isCorrect) {
	    SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, submittedText, isCorrect);
	    submittedAnswers.add(submittedAnswer);
	}

    
}
