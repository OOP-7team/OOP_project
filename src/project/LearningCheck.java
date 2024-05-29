package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class LearningCheck extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private JFrame learningCheck;
    private JTable myDetail;
    private JTable checkTable;

    public LearningCheck() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initialize();
    }

    private void initialize() {
        // JFrame의 기본 속성을 설정
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setBounds(100, 100, 1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        setVisible(true);

		
		
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		getContentPane().add(content, BorderLayout.CENTER);
		content.setLayout(null);
		
		JLabel contentTitle = new JLabel("> 나의 학습점검");
		contentTitle.setFont(new Font("굴림", Font.PLAIN, 30));
		contentTitle.setBounds(40, 40, 305, 35);
		content.add(contentTitle);
		
		
		// 문제 생성
        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("수학 문제 1", "답1", "사용자 답1"));
        mathQuestions.add(new Question("수학 문제 2", "답2", "사용자 답2"));

        List<Question> koreanQuestions = new ArrayList<>();
        koreanQuestions.add(new Question("국어 문제 1", "답1", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 2", "답2", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 3", "답3", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 4", "답4", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 5", "답5", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 6", "답6", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 7", "답7", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 8", "답8", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 9", "답9", "사용자 답"));
        koreanQuestions.add(new Question("국어 문제 10", "답10", "사용자 답"));

        List<Question> englishQuestions = new ArrayList<>();
        englishQuestions.add(new Question("영어 문제 1", "답1", "사용자 답1"));
        englishQuestions.add(new Question("영어 문제 2", "답2", "사용자 답2"));

        // 퀴즈 생성
        Quiz mathQuiz = new Quiz("수학", "1단원 평가", "24.03.15", "7/10", mathQuestions);
        Quiz koreanQuiz = new Quiz("국어", "1단원 쪽지시험", "24.03.10", "9/10", koreanQuestions);
        Quiz englishQuiz = new Quiz("영어", "ch1. Quiz", "24.04.07", "10/10", englishQuestions);

        // 퀴즈 리스트에 추가
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(mathQuiz);
        quizList.add(koreanQuiz);
        quizList.add(englishQuiz);

        // 생성한 퀴즈 리스트 사용 예시
        for (Quiz quiz : quizList) {
            System.out.println("과목: " + quiz.getSubject());
            System.out.println("제목: " + quiz.getTitle());
            System.out.println("날짜: " + quiz.getDate());
            System.out.println("점수: " + quiz.getScore());
            System.out.println("문제 리스트:");

            for (Question question : quiz.getQuestions()) {
                System.out.println("문제: " + question.getQuestion());
                System.out.println("정답: " + question.getCorrectAnswer());
                System.out.println("사용자 답: " + question.getUserAnswer());
                System.out.println("정답 여부: " + (question.isCorrect() ? "맞음" : "틀림"));
                System.out.println();
            }
        }
		
		
		String[] checkColumnNames = {"과목", "퀴즈 제목", "날짜", "점수"};
        Object[][] checkRowData = new Object[quizList.size()][4];
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            checkRowData[i][0] = quiz.getSubject();
            checkRowData[i][1] = quiz.getTitle();
            checkRowData[i][2] = quiz.getDate();
            checkRowData[i][3] = quiz.getScore();
        }
		
		checkTable = new JTable(checkRowData, checkColumnNames);
		checkTable.setFont(new Font("굴림", Font.PLAIN, 25));
		checkTable.setBounds(40, 120, 950, 300);
		
		// 테이블의 행 선택 이벤트 리스너 추가
        checkTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = checkTable.getSelectedRow();
                if (!e.getValueIsAdjusting() && selectedRow != -1) {
                    // 선택된 행의 데이터 가져오기
                    Object[] rowData = new Object[checkTable.getColumnCount()];
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = checkTable.getValueAt(selectedRow, i);
                    }
                    // 새로운 QuizDetail 창 열기
                    QuizDetail quizDetail = new QuizDetail(quizList.get(selectedRow));
                    quizDetail.setVisible(true);

                    // 선택 이벤트 리스너 제거
                    //checkTable.getSelectionModel().removeListSelectionListener(this);
                }
            }
        });
		
		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer check_dtcr = new DefaultTableCellRenderer();
		check_dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		TableColumnModel check_tcm = checkTable.getColumnModel();
		//전체 열에 가운데 정렬 지정
	    for(int i = 0 ; i < check_tcm.getColumnCount() ; i++){
	    	check_tcm.getColumn(i).setCellRenderer(check_dtcr);
	    }
	    
	    checkTable.getColumn("과목").setPreferredWidth(75);
	    checkTable.getColumn("퀴즈 제목").setPreferredWidth(75);
	    checkTable.getColumn("날짜").setPreferredWidth(75);
	    checkTable.getColumn("점수").setPreferredWidth(75);
	    checkTable.setRowHeight(50);
		
	    // JTableHeader 생성 및 폰트 설정
        JTableHeader tableHeader = checkTable.getTableHeader();
        tableHeader.setFont(new Font("굴림", Font.BOLD, 25));
	    
	    
		// JTable을 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(checkTable);
        
        // JScrollPane의 높이를 JTable의 전체 높이에 맞춤
        scrollPane.setBounds(40, 120, 950, 300);
        
        content.add(scrollPane);
        
		
		//-------------------------------------------------------------좌측 상세정보
    }
    
}
