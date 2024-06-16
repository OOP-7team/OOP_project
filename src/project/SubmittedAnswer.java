package project;

public class SubmittedAnswer {
	private Question question; // Quiz 객체 안에 있는 Question 객체
    private String submittedText; // 제출된 정답
    private boolean isCorrect; // 오답 여부
    

    public SubmittedAnswer(Question question, String submittedText, boolean isCorrect) {
        this.question = question;
        this.submittedText = submittedText;
        this.isCorrect = isCorrect;
    }

	public Question getQuestion() {
		return question;
	}

	public String getSubmittedText() {
		return submittedText;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

    
}
