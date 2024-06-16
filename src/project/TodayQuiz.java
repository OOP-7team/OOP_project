package project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class TodayQuiz extends JFrame{

	private JFrame frame;
    private JTable quizTable;

	public TodayQuiz() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
	}

	private void initialize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        setBounds(100, 100, 1050, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        
        JPanel content = new JPanel();
        getContentPane().add(content, BorderLayout.CENTER);
        content.setLayout(null);
        
        JLabel contentTitle = new JLabel("> 오늘의 퀴즈");
		contentTitle.setFont(new Font("굴림", Font.PLAIN, 30));
		contentTitle.setBounds(40, 40, 305, 35);
		content.add(contentTitle);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        
     // 등록된 퀴즈 목록을 가져와서 표시
     List<Quiz> quizList = MainPage.loginUser.getQuizzes();
     System.out.println("로그인 계정: " + MainPage.loginUser.toString());
     System.out.println(quizList.toString());

     // 퀴즈 정보를 화면에 표시하는 코드 작성
     // 예시: 각 퀴즈를 JLabel로 생성하여 화면에 추가
     // 퀴즈 생성
		
	 String[] quizColumnNames = {"과목", "퀴즈 제목", "등록날짜", "마감날짜"};
     Object[][] quizRowData = new Object[quizList.size()][4];
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초");
     
     for (int i = 0; i < quizList.size(); i++) {
         Quiz quiz = quizList.get(i);
         quizRowData[i][0] = quiz.getSubject();
         quizRowData[i][1] = quiz.getTitle();
         quizRowData[i][2] = quiz.getCreatedDateTime().format(formatter); // 등록날짜 포맷팅
         quizRowData[i][3] = quiz.getDueDateTime().format(formatter); // 마감날짜 포맷팅
     }
		
     quizTable = new JTable(quizRowData, quizColumnNames);
     quizTable.setFont(new Font("굴림", Font.PLAIN, 25));
     quizTable.setBounds(40, 120, 950, 300);
     
     // 테이블 내용 가운데 정렬
		DefaultTableCellRenderer quiz_dtcr = new DefaultTableCellRenderer();
		quiz_dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		TableColumnModel quiz_tcm = quizTable.getColumnModel();
		//전체 열에 가운데 정렬 지정
	    for(int i = 0 ; i < quiz_tcm.getColumnCount() ; i++){
	    	quiz_tcm.getColumn(i).setCellRenderer(quiz_dtcr);
	    }
	    
	    quizTable.getColumn("과목").setPreferredWidth(75);
	    quizTable.getColumn("퀴즈 제목").setPreferredWidth(75);
	    quizTable.getColumn("등록날짜").setPreferredWidth(75);
	    quizTable.getColumn("마감날짜").setPreferredWidth(75);
	    quizTable.setRowHeight(50);
		
	    // JTableHeader 생성 및 폰트 설정
	     JTableHeader tableHeader = quizTable.getTableHeader();
	     tableHeader.setFont(new Font("굴림", Font.BOLD, 25));
	    
	    
		// JTable을 JScrollPane에 추가
	     JScrollPane scrollPane = new JScrollPane(quizTable);
	     
	     // JScrollPane의 높이를 JTable의 전체 높이에 맞춤
	     scrollPane.setBounds(40, 120, 950, 300);
	     
	     content.add(scrollPane);
	     
	     // JTable에 MouseListener 추가
	        quizTable.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
                    int selectedRow = quizTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Quiz selectedQuiz = quizList.get(selectedRow);
                        new TakeQuiz(selectedQuiz);
                    }
	            }
	        });
	        
	        
	}

}
