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

    // 필요에 따라 다른 유틸리티 메서드를 추가할 수 있습니다.
}
