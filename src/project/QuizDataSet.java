package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizDataSet {
    // 퀴즈 정보를 관리하는 클래스

	private static QuizDataSet quizDataSetInstance;
    private Map<Integer, Quiz> quizzes; // 사용자 정보를 저장할 HashMap

    // 생성자: HashMap을 초기화
    public QuizDataSet() {
    	quizzes = new HashMap<>(); // 새로운 HashMap 인스턴스를 생성하여 users에 할당
    }
    
    public static QuizDataSet getQuizDataSetInstance() {
        if (quizDataSetInstance == null) {
        	quizDataSetInstance = new QuizDataSet();
        }
        return quizDataSetInstance;
    }

    // 사용자 정보를 추가하는 메서드
    public void addQuiz(Quiz quiz) {
        quizzes.put(quiz.getQuizId(), quiz); // 사용자의 아이디를 키로, User 객체를 값으로 HashMap에 추가
    }

    // 아이디로 사용자 정보를 가져오는 메서드
    public Quiz getQuiz(int quizId) {
        return quizzes.get((Integer)quizId); // 아이디를 키로 사용하여 HashMap에서 User 객체를 반환
    }
    
    // 모든 퀴즈를 반환하는 메서드
    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

}
