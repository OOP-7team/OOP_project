package project;

import java.util.List;

public class Question {
    private String question;
    private String correctAnswer;
    private List<Answer> answers;
    private String questionType;
    // private String userAnswer;
    // private boolean isCorrect;

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.questionType = "단답식";
        // this.userAnswer = userAnswer;
        // this.isCorrect = (correctAnswer == userAnswer);
    }
    
    public Question(String question, String correctAnswer, List<Answer> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.setAnswers(answers);
        this.questionType = "객관식";
        // this.userAnswer = userAnswer;
        // this.isCorrect = (correctAnswer == userAnswer);
    }

    // Getter 및 Setter 메서드들
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

//    public String getUserAnswer() {
//        return userAnswer;
//    }
//
//    public void setUserAnswer(String userAnswer) {
//        this.userAnswer = userAnswer;
//    }
//
//    public boolean isCorrect() {
//        return isCorrect;
//    }
//
//    public void setCorrect(boolean correct) {
//        isCorrect = correct;
//    }
}