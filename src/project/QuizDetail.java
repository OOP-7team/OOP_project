package project;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class QuizDetail extends JFrame {

    private Quiz quiz;
    private JTable questionTable;

    public QuizDetail(Quiz quiz) {
        this.quiz = quiz;
        initialize(quiz);
    }

    private void initialize(Quiz quiz) {
        setTitle("퀴즈 상세정보");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        
        JPanel info = new JPanel(new GridLayout(2, 2));
        info.setBackground(Color.WHITE);
        getContentPane().add(info, BorderLayout.NORTH);
        
        JLabel subjectLabel = new JLabel("과목: " + quiz.getSubject());
        subjectLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(subjectLabel);
        JLabel quizTitleLabel = new JLabel("퀴즈 제목: " + quiz.getSubject());
        quizTitleLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(quizTitleLabel);
        JLabel dateLabel = new JLabel("날짜: " + quiz.getDate());
        dateLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(dateLabel);
        JLabel scoreLabel = new JLabel("점수: " + quiz.getScore());
        scoreLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(scoreLabel);
        
        JPanel questionList = new JPanel();
        getContentPane().add(questionList, BorderLayout.CENTER);
        
        
        String[] questionColumnNames = {"문제", "내 정답"};
        Object[][] questionRowData = new Object[quiz.getQuestions().size()][2];
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question question = quiz.getQuestions().get(i);
            questionRowData[i][0] = question.getQuestion();
            questionRowData[i][1] = question.getUserAnswer();
        }
		
        questionTable = new JTable(questionRowData, questionColumnNames);
        questionTable.setFont(new Font("굴림", Font.PLAIN, 25));
        questionTable.setBounds(40, 120, 950, 300);
        
        questionTable.getColumn("문제").setPreferredWidth(75);
        questionTable.getColumn("내 정답").setPreferredWidth(75);
        questionTable.setRowHeight(50);
		
	    // JTableHeader 생성 및 폰트 설정
        JTableHeader tableHeader = questionTable.getTableHeader();
        tableHeader.setFont(new Font("굴림", Font.BOLD, 25));
	    
	    
		// JTable을 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(questionTable);
        
        // JScrollPane의 높이를 JTable의 전체 높이에 맞춤
        scrollPane.setBounds(40, 120, 950, 300);
        
        questionList.add(scrollPane);
        
        
        
        JPanel panel_2 = new JPanel();
        getContentPane().add(panel_2, BorderLayout.SOUTH);
 
//        JPanel panel_1 = new JPanel();
//        panel_1.setBackground(Color.WHITE);
//        panel.add(panel_1, BorderLayout.NORTH);
//        
//        JPanel questions = new JPanel();
//        questions.setBackground(Color.WHITE);
//        panel.add(questions, BorderLayout.SOUTH);
//        
//     // 문제 정보 추가
//        for (Question question : quiz.getQuestions()) {
//            JLabel questionLabel = new JLabel("문제: ");
//            JLabel questionValueLabel = new JLabel(question.getQuestion());
//            infoPanel.add(questionLabel);
//            infoPanel.add(questionValueLabel);
//
//            JLabel correctAnswerLabel = new JLabel("정답: ");
//            JLabel correctAnswerValueLabel = new JLabel(question.getCorrectAnswer());
//            infoPanel.add(correctAnswerLabel);
//            infoPanel.add(correctAnswerValueLabel);
//
//            JLabel userAnswerLabel = new JLabel("내 답: ");
//            JLabel userAnswerValueLabel = new JLabel(question.getUserAnswer());
//            infoPanel.add(userAnswerLabel);
//            infoPanel.add(userAnswerValueLabel);
//
//            JLabel isCorrectLabel = new JLabel("정답 여부: ");
//            JLabel isCorrectValueLabel = new JLabel(question.isCorrect() ? "정답" : "오답");
//            infoPanel.add(isCorrectLabel);
//            infoPanel.add(isCorrectValueLabel);
//        }
    }
}
