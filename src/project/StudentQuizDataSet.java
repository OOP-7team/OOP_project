package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentQuizDataSet {
    private static StudentQuizDataSet studentQuizDataSetInstance;
    private List<StudentQuiz> studentQuizzes;

    private StudentQuizDataSet() {
        studentQuizzes = new ArrayList<>();
    }

    public static StudentQuizDataSet getStudentQuizDataSetInstance() {
        if (studentQuizDataSetInstance == null) {
        	studentQuizDataSetInstance = new StudentQuizDataSet();
        }
        return studentQuizDataSetInstance;
    }

    public void addStudentQuiz(StudentQuiz studentQuiz) {
        studentQuizzes.add(studentQuiz);
    }

    public List<StudentQuiz> getAllStudentQuizzes() {
        return studentQuizzes;
    }
    
 // 특정 과목명을 가진 퀴즈의 개수를 반환하는 메서드
    public int countBySubject(String subject) {
        int count = 0;
        for (StudentQuiz studentQuiz : studentQuizzes) {
            if (studentQuiz.getQuiz().getSubject().equals(subject)) {
                count++;
            }
        }
        return count;
    }

    // 필요에 따라 다른 유틸리티 메서드를 추가할 수 있습니다.
}
