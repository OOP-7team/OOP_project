package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Closeable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.border.BevelBorder;

public class MyPage extends JFrame{

	private JFrame myPage;
	private JTable myDetail;

	// 프로그램 실행 부분
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyPage window = new MyPage();
					window.myPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	// MyPage 객체 생성자
	public MyPage() {
		initialize(); // 모듈화
	}
	
	
	/**
	 * 창을 닫는 동작을 실행.
	 * 사용자가 이전 창을 닫고 새 창을 열 수 있도록 도와줌.
	 * 만약 이 함수가 없다면 그 전 창이 그대로 열려있는 상태에서 새 창이 열리게 됨
	 */
	public void close() {
		WindowEvent closeWindow = new WindowEvent(this.myPage, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * MyPage의 JFrame 객체 초기화
	 */
	private void initialize() {
		myPage = new JFrame();
		myPage.getContentPane().setBackground(Color.WHITE);
		myPage.setBounds(100, 100, 2000, 1200);
		myPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 창이 정 가운데에서 뜨도록 해줌
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int leftTopX = centerPoint.x - myPage.getWidth()/2;
		int leftTopY = centerPoint.y - myPage.getHeight()/2;
		myPage.setLocation(leftTopX, leftTopY);
		myPage.getContentPane().setLayout(new BorderLayout(0, 0));
		
		// 상단 gnb 부분
		JPanel gnb = new JPanel();
		gnb.setBackground(Color.WHITE);
		myPage.getContentPane().add(gnb, BorderLayout.NORTH);
		
		// gnb의 Layout 설정(gridBagLayout을 선택함)
		GridBagLayout gbl_gnb = new GridBagLayout();
		gbl_gnb.columnWidths = new int[]{360, 360, 360, 360, 0};
		gbl_gnb.rowHeights = new int[]{100, 100, 0};
		gbl_gnb.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_gnb.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gnb.setLayout(gbl_gnb);
		
		// gnb에 들어가는 로고 이미지 크기 조절(크기 조절한 아이콘을 아래 goToMain 라벨에 넣을 예정)
		ImageIcon icon = new ImageIcon(MyPage.class.getResource("/images/logo.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		// Main 로고 부분 JLabel로 구현
		JLabel goToMain = new JLabel("북적북적");
		
		goToMain.setForeground(new Color(102, 204, 51));
		goToMain.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 40));
		goToMain.setBackground(Color.WHITE);
		goToMain.setHorizontalAlignment(SwingConstants.LEFT);
		
		goToMain.setIcon(updateIcon); // 크기 조절했던 로고가 여기에 들어감
		
		GridBagConstraints gbc_goToMain = new GridBagConstraints();
		gbc_goToMain.fill = GridBagConstraints.BOTH;
		gbc_goToMain.insets = new Insets(0, 0, 5, 5);
		gbc_goToMain.gridx = 0;
		gbc_goToMain.gridy = 0;
		gnb.add(goToMain, gbc_goToMain);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JLabel temp1 = new JLabel("");
		GridBagConstraints gbc_temp1 = new GridBagConstraints();
		gbc_temp1.fill = GridBagConstraints.BOTH;
		gbc_temp1.insets = new Insets(0, 0, 5, 5);
		gbc_temp1.gridx = 1;
		gbc_temp1.gridy = 0;
		gnb.add(temp1, gbc_temp1);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JLabel temp2 = new JLabel("");
		GridBagConstraints gbc_temp2 = new GridBagConstraints();
		gbc_temp2.fill = GridBagConstraints.BOTH;
		gbc_temp2.insets = new Insets(0, 0, 5, 5);
		gbc_temp2.gridx = 2;
		gbc_temp2.gridy = 0;
		gnb.add(temp2, gbc_temp2);
		
		// gnb의 side에 들어가는 button들이 들어가는 JPanel
		JPanel sideBtnWrapper = new JPanel();
		sideBtnWrapper.setBackground(Color.WHITE);
		GridBagConstraints gbc_sideBtnWrapper = new GridBagConstraints();
		gbc_sideBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_sideBtnWrapper.insets = new Insets(0, 0, 5, 0);
		gbc_sideBtnWrapper.gridx = 3;
		gbc_sideBtnWrapper.gridy = 0;
		gnb.add(sideBtnWrapper, gbc_sideBtnWrapper);
		sideBtnWrapper.setLayout(null);
		
		// 첫 번째 side button : goToGroup
		JButton goToGroup = new JButton("");
		goToGroup.setForeground(Color.WHITE);
		goToGroup.setBackground(Color.WHITE);
		goToGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		icon = new ImageIcon(MyPage.class.getResource("/images/group.png"));
		img = icon.getImage();
		updateImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		updateIcon = new ImageIcon(updateImg);
		goToGroup.setIcon(updateIcon);
		goToGroup.setBounds(0, 0, 100, 100);
		sideBtnWrapper.add(goToGroup);
		
		// 두 번째 side button : goToBook
		JButton goToBook = new JButton("");
		goToBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		goToBook.setIcon(new ImageIcon(MyPage.class.getResource("/images/book.png")));
		goToBook.setForeground(Color.WHITE);
		goToBook.setBackground(Color.WHITE);
		goToBook.setBounds(100, 0, 100, 100);
		icon = new ImageIcon(MyPage.class.getResource("/images/book.png"));
		img = icon.getImage();
		updateImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		updateIcon = new ImageIcon(updateImg);
		goToBook.setIcon(updateIcon);
		sideBtnWrapper.add(goToBook);
		
		// 세 번째 side button : goToMyPage
		JButton goToMyPage = new JButton("최예인님");
		goToMyPage.setBackground(Color.WHITE);
		goToMyPage.setFont(new Font("굴림", Font.PLAIN, 30));
		goToMyPage.setBounds(200, 0, 160, 100);
		sideBtnWrapper.add(goToMyPage);
		
		// gnb 하단에 들어가는 버튼들이 들어간 JPanel
		JPanel mainBtnWrapper = new JPanel();
		mainBtnWrapper.setBackground(Color.WHITE);
		mainBtnWrapper.setLayout(null);
		GridBagConstraints gbc_mainBtnWrapper = new GridBagConstraints();
		gbc_mainBtnWrapper.gridwidth = 4;
		gbc_mainBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_mainBtnWrapper.insets = new Insets(0, 0, 0, 5);
		gbc_mainBtnWrapper.gridx = 0;
		gbc_mainBtnWrapper.gridy = 1;
		gnb.add(mainBtnWrapper, gbc_mainBtnWrapper);
		
		// 첫 번째 main button : goToQuiz
		JButton goToQuiz = new JButton("오늘의 퀴즈");
		goToQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		goToQuiz.setBackground(new Color(192, 236, 149));
		goToQuiz.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuiz.setBounds(10, 23, 221, 50);
		mainBtnWrapper.add(goToQuiz);
		
		
		// 두 번째 main button : goToLearningCheck
		JButton goToLearningCheck = new JButton("학습 점검");
		goToLearningCheck.setBackground(new Color(192, 236, 149));
		
		
		/**
		 * 클릭 시 "학습 점검"창이 새로 뜸
		 * */
		goToLearningCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				LearningCheck jf2 = new LearningCheck();
				jf2.setVisible(true);
			}
		});
		
		goToLearningCheck.setFont(new Font("굴림", Font.PLAIN, 30));
		goToLearningCheck.setBounds(258, 23, 221, 50);
		mainBtnWrapper.add(goToLearningCheck);
		
		// 세 번째 main button : goToBookMenu
		JButton goToBookMenu = new JButton("교과서 목록");
		goToBookMenu.setBackground(new Color(192, 236, 149));
		goToBookMenu.setFont(new Font("굴림", Font.PLAIN, 30));
		goToBookMenu.setBounds(512, 23, 221, 50);
		mainBtnWrapper.add(goToBookMenu);
		
		// myPage의 다양한 메뉴가 있는 Tab바 : myPageTab
		JTabbedPane myPageTab = new JTabbedPane(JTabbedPane.LEFT);
		myPageTab.setFont(new Font("굴림", Font.PLAIN, 30));
		myPage.getContentPane().add(myPageTab, BorderLayout.CENTER);
		
		
		// 학급 공부시간 Tab
		JPanel classStudyTime = new JPanel();
		myPageTab.addTab("학급 공부시간", null, classStudyTime, null);
		
		// 학급 공지사항 Tab
		JPanel classNotice = new JPanel();
		myPageTab.addTab("학급 공지사항", null, classNotice, null);
		
		// 나의 교과서 Tab
		JPanel myBook = new JPanel();
		myPageTab.addTab("나의 교과서", null, myBook, null);
		
		// 나의 학습자료 Tab
		JPanel myStudyPaper = new JPanel();
		myPageTab.addTab("나의 학습자료", null, myStudyPaper, null);
		
		// 그룹목록 Tab
		JPanel group = new JPanel();
		myPageTab.addTab("그룹목록", null, group, null);
		
		// 그룹생성 Tab
		JPanel createGroup = new JPanel();
		myPageTab.addTab("그룹생성", null, createGroup, null);
		
		
		// 좌측 내 정보가 들어갈 JPanel. myProfile 객체와 myDetail 객체가 들어감
		JPanel myInfo = new JPanel();
		myInfo.setBackground(Color.WHITE);
		myPage.getContentPane().add(myInfo, BorderLayout.WEST);
		
		// 내 프로필 사진이 들어갈 JLabel: myProfile
		JLabel myProfile = new JLabel("");
		myProfile.setHorizontalAlignment(SwingConstants.CENTER);
		icon = new ImageIcon(MyPage.class.getResource("/images/profile.png"));
		img = icon.getImage();
		updateImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		updateIcon = new ImageIcon(updateImg);
		myInfo.setLayout(new GridLayout(2, 0, 0, 0));
		myProfile.setIcon(updateIcon);
		myInfo.add(myProfile);
		
		// 내 상세 정보가 들어가는 JTable: myDetail
		String[] columnNames = {"정보", "내용"};
		Object[][] rowData = {
				{"학년", 2},
				{"반", 3},
				{"공부시간", "29시간"}
		};
		
		myDetail = new JTable(rowData, columnNames);
		myDetail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		myDetail.setFont(new Font("굴림", Font.PLAIN, 25));
		
		
		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		TableColumnModel tcm = myDetail.getColumnModel();
		//전체 열에 가운데 정렬 지정
	    for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	    	tcm.getColumn(i).setCellRenderer(dtcr);
	    }
	    
		myDetail.getColumn("정보").setPreferredWidth(75);
		myDetail.getColumn("내용").setPreferredWidth(75);
		myDetail.setRowHeight(50);
		
		myInfo.add(myDetail);
		
				
		
	}
}
