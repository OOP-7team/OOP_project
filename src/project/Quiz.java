package project;

import java.util.List;

//Quiz 클래스
public class Quiz {
    private String subject;
    private String title;
    private String date;
    private String score;
    private List<Question> questions;

    public Quiz(String subject, String title, String date, String score, List<Question> questions) {
        this.subject = subject;
        this.title = title;
        this.date = date;
        this.score = score;
        this.questions = questions;
    }

    // Getter 메서드들
    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
