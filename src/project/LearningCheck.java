package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
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

		
		
		JPanel content = 
				new JPanel();
		content.setBackground(Color.WHITE);
		getContentPane().add(content, BorderLayout.CENTER);
		content.setLayout(null);
		
		JLabel contentTitle = new JLabel("> 나의 학습점검");
		contentTitle.setFont(new Font("굴림", Font.PLAIN, 30));
		contentTitle.setBounds(40, 40, 305, 35);
		content.add(contentTitle);
		

        // 로그인 유저가 풀었던 퀴즈들 가져오기
		List<StudentQuiz> studentQuizzes = MainPage.loginUser.getStudentQuizzes();
		
		
		String[] checkColumnNames = {"등록 날짜", "과목", "퀴즈 제목", "점수", "푼 날짜", "마감 날짜"};
        Object[][] checkRowData = new Object[studentQuizzes.size()][6];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss");
        for (int i = 0; i < studentQuizzes.size(); i++) {
        	StudentQuiz studentQuiz = studentQuizzes.get(i);
            checkRowData[i][0] = studentQuiz.getQuiz().getCreatedDateTime().format(formatter);
            checkRowData[i][1] = studentQuiz.getQuiz().getSubject();
            checkRowData[i][2] = studentQuiz.getQuiz().getTitle();
            checkRowData[i][3] = studentQuiz.getScore();
            checkRowData[i][4] = studentQuiz.getSubmissionDateTime().format(formatter);
            checkRowData[i][5] = studentQuiz.getQuiz().getDueDateTime().format(formatter);
        }
		
		checkTable = new JTable(checkRowData, checkColumnNames);
		checkTable.setFont(new Font("굴림", Font.PLAIN, 15));
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
                    QuizDetail quizDetail = new QuizDetail(studentQuizzes.get(selectedRow));
                    quizDetail.setVisible(true);
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
	    //"등록 날짜", "과목", "퀴즈 제목", "점수", "푼 날짜", "마감날짜"
	    checkTable.getColumn("등록 날짜").setPreferredWidth(75);
	    checkTable.getColumn("과목").setPreferredWidth(75);
	    checkTable.getColumn("퀴즈 제목").setPreferredWidth(75);
	    checkTable.getColumn("점수").setPreferredWidth(75);
	    checkTable.getColumn("푼 날짜").setPreferredWidth(75);
	    checkTable.getColumn("마감 날짜").setPreferredWidth(75);
	    checkTable.setRowHeight(50);
		
	    // JTableHeader 생성 및 폰트 설정
        JTableHeader tableHeader = checkTable.getTableHeader();
        tableHeader.setFont(new Font("굴림", Font.BOLD, 25));
	    
	    
		// JTable을 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(checkTable);
        
        // JScrollPane의 높이를 JTable의 전체 높이에 맞춤
        scrollPane.setBounds(40, 120, 1210, 550);
        
        content.add(scrollPane);
        
		
		//-------------------------------------------------------------좌측 상세정보
    }
    
}