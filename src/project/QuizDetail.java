package project;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.time.format.DateTimeFormatter;

public class QuizDetail extends JFrame {

    private StudentQuiz studentQuiz;
    private JTable questionTable;

    public QuizDetail(StudentQuiz studentQuiz) {
        this.studentQuiz = studentQuiz;
        initialize(studentQuiz);
    }

    private void initialize(StudentQuiz studentQuiz) {
        setTitle("퀴즈 상세정보");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        
        JPanel info = new JPanel(new GridLayout(2, 2));
        info.setBackground(Color.WHITE);
        getContentPane().add(info, BorderLayout.NORTH);
        
        //"등록 날짜", "과목", "퀴즈 제목", "점수", "푼 날짜", "마감날짜"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss");
        
        JLabel createdDateLabel = new JLabel("등록 날짜: " + studentQuiz.getQuiz().getCreatedDateTime().format(formatter));
        createdDateLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(createdDateLabel);
        
        JLabel subjectLabel = new JLabel("과목: " + studentQuiz.getQuiz().getSubject());
        subjectLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(subjectLabel);
        
        JLabel quizTitleLabel = new JLabel("퀴즈 제목: " + studentQuiz.getQuiz().getTitle());
        quizTitleLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(quizTitleLabel);
        
        JLabel dateLabel = new JLabel("마감 날짜: " + studentQuiz.getQuiz().getDueDateTime().format(formatter));
        dateLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(dateLabel);
        
        JLabel scoreLabel = new JLabel("점수: " + studentQuiz.getScore());
        scoreLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        info.add(scoreLabel);
        
        JPanel questionList = new JPanel();
        getContentPane().add(questionList, BorderLayout.CENTER);
        
        
        String[] questionColumnNames = {"문제", "내 정답", "오답 여부"};
        Object[][] questionRowData = new Object[studentQuiz.getQuiz().getQuestions().size()][3];
        for (int i = 0; i < studentQuiz.getQuiz().getQuestions().size(); i++) {
            Question question = studentQuiz.getQuiz().getQuestions().get(i);
            questionRowData[i][0] = question.getQuestion();
            questionRowData[i][1] = studentQuiz.getSubmittedAnswers().get(i).getSubmittedText();
            if(question.getQuestionType().equals("객관식")) {
            	questionRowData[i][1] = studentQuiz.getSubmittedAnswers().get(i).getSubmittedText() + "번 선지";
            }
            questionRowData[i][2] = studentQuiz.getSubmittedAnswers().get(i).getIsCorrect() ? "정답" : "오답";
        }
		
        questionTable = new JTable(questionRowData, questionColumnNames);
        questionTable.setFont(new Font("굴림", Font.PLAIN, 25));
        questionTable.setBounds(40, 120, 950, 300);
        
        questionTable.getColumn("문제").setPreferredWidth(75);
        questionTable.getColumn("내 정답").setPreferredWidth(75);
        questionTable.getColumn("오답 여부").setPreferredWidth(75);
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
 
    }
}
