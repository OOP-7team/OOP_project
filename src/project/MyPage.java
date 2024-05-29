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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MyPage extends JFrame{

	private static final long serialVersionUID = 1L;
	
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
	public static void close(Window window) {
		WindowEvent closeWindow = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * MyPage의 JFrame 객체 초기화
	 */
	private void initialize() {
		myPage = new JFrame();
		myPage.getContentPane().setBackground(Color.WHITE);
		myPage.setBounds(100, 100, 1300, 800);
		myPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
		myPage.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
		myPage.setVisible(true);
		
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
		
		// Main 로고 부분 JPanel로 구현		
		JPanel mainBtn = new JPanel();
		mainBtn.setBackground(Color.WHITE);
		mainBtn.setLayout(null);
		GridBagConstraints gbc_mainBtn = new GridBagConstraints();
		gbc_mainBtn.fill = GridBagConstraints.BOTH;
		gbc_mainBtn.insets = new Insets(0, 0, 5, 0);
		gbc_mainBtn.gridx = 0;
		gbc_mainBtn.gridy = 0;
		gnb.add(mainBtn, gbc_mainBtn);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(updateIcon);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(0, 0, 100, 100);
		mainBtn.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("북적북적");
		lblNewLabel.setForeground(new Color(153, 204, 0));
		lblNewLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 40));
		lblNewLabel.setBounds(110, 25, 196, 60);
		mainBtn.add(lblNewLabel);
		
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
		JButton goToMyPage = new JButton("로그인");
		goToMyPage.setBackground(Color.WHITE);
		goToMyPage.setFont(new Font("굴림", Font.PLAIN, 30));
		goToMyPage.setBounds(200, 0, 160, 100);
		sideBtnWrapper.add(goToMyPage);

		// Add ActionListener to goToMyPage button
		goToMyPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm loginForm = new LoginForm(); // Create an instance of LoginForm
				loginForm.setVisible(true); // Make the LoginForm window visible
			}
		});
		
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
				// close(myPage);
				TodayQuiz todayQuiz = new TodayQuiz();
				todayQuiz.setVisible(true);
			}
		});
		goToQuiz.setBackground(new Color(192, 236, 149));
		goToQuiz.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuiz.setBounds(10, 23, 221, 50);
		mainBtnWrapper.add(goToQuiz);
		
		
		// 두 번째 main button : goToLearningCheck
		JButton goToLearningCheck = new JButton("학습 점검");
		goToLearningCheck.setBackground(new Color(192, 236, 149));
		
		goToLearningCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				LearningCheck learningCheck = new LearningCheck();
				learningCheck.setVisible(true);
			}
		});
		
		goToLearningCheck.setFont(new Font("굴림", Font.PLAIN, 30));
		goToLearningCheck.setBounds(258, 23, 221, 50);
		mainBtnWrapper.add(goToLearningCheck);
		
		// 세 번째 main button : goToBookMenu
		JButton goToBookMenu = new JButton("교과서 목록");
		goToBookMenu.setBackground(new Color(192, 236, 149));
		
		goToBookMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				BookMenu bookMenu = new BookMenu();
				bookMenu.setVisible(true);
			}
		});
		
		goToBookMenu.setFont(new Font("굴림", Font.PLAIN, 30));
		goToBookMenu.setBounds(512, 23, 221, 50);
		mainBtnWrapper.add(goToBookMenu);
		
		//-------------------------------------------------------------상단 gnb bar
		
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
		  group.setLayout(null);
		  
		  
		  JTextField searchField = new JTextField();
		  searchField.setBounds(227, 32, 571, 37);
		  searchField.setBackground(new Color(255, 255, 255));
		  searchField.setText("  그룹명");
		  searchField.setFont(new Font("굴림", Font.PLAIN, 18));
		  group.add(searchField);
		  searchField.setColumns(40);
		  
		  JButton searchBtn = new JButton("New button");
		  searchBtn.setIcon(new ImageIcon(MyPage.class.getResource("/images/search.png")));
		  searchBtn.setBounds(810, 32, 62, 37);
		  group.add(searchBtn);
		  
		//	      public void searchGroups(String keyword) {
		//	         DefaultTableModel model = (DefaultTableModel) GroupListTable.getModel();
		//	         DefaultTableModel searchModel = new DefaultTableModel(
		//	             new Object[][] {},
		//	             new String[] {"그룹명", "그룹 소개", "공개 여부"}
		//	         );
		//
		//	         for (int i = 0; i < model.getRowCount(); i++) {
		//	             String groupName = (String) model.getValueAt(i, 0);
		//	             if (groupName.contains(keyword)) {
		//	                 String groupIntro = (String) model.getValueAt(i, 1);
		//	                 String groupStatus = (String) model.getValueAt(i, 2);
		//	                 searchModel.addRow(new Object[] {groupName, groupIntro, groupStatus});
		//	             }
		//	         }
		//
		//	         if (searchModel.getRowCount() == 0) {
		//	             JOptionPane.showMessageDialog(myPage, "검색 결과가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		//	             return;
		//	         }
		//
		//	         JFrame searchResultsFrame = new JFrame("검색 결과");
		//	         searchResultsFrame.setSize(600, 400);
		//	         searchResultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//	         searchResultsFrame.setLocationRelativeTo(null);
		//
		//	         JTable searchResultsTable = new JTable(searchModel);
		//	         searchResultsTable.setFillsViewportHeight(true);
		//	         JScrollPane searchScrollPane = new JScrollPane(searchResultsTable);
		//
		//	         searchResultsFrame.getContentPane().add(searchScrollPane, BorderLayout.CENTER);
		//	         searchResultsFrame.setVisible(true);
		//	    }
		//	      
		//	      // 그룹명 검색 버튼 이벤트 처리
		//	      searchBtn.addActionListener(new ActionListener() {
		//	          public void actionPerformed(ActionEvent e) {
		//	              String keyword = searchField.getText().trim();
		//	              if (keyword.isEmpty() || keyword.equals("  그룹명")) {
		//	                  JOptionPane.showMessageDialog(myPage, "검색어를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
		//	                  return;
		//	              }
		//	              searchGroups(keyword);
		//	          }
		//	      });
		//	      
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(76, 95, 886, 497);
		  group.add(scrollPane);
		  
		  JTable GroupListTable = new JTable();
		  GroupListTable.setForeground(new Color(0, 0, 0));
		  scrollPane.setViewportView(GroupListTable);
		  GroupListTable.setFillsViewportHeight(true);
		  GroupListTable.setModel(new DefaultTableModel(
		     new Object[][] {},
		     new String[] {
		        "그룹명", "그룹 소개", "공개 여부"
		     }
		  ));
		  
		  GroupListTable.getColumnModel().getColumn(0).setPreferredWidth(84);
		  GroupListTable.getColumnModel().getColumn(0).setMinWidth(20);
		  GroupListTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		  GroupListTable.setColumnSelectionAllowed(true);
		  GroupListTable.setCellSelectionEnabled(true);
		  GroupListTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		  //group.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{searchField, searchBtn, GroupListTable, table, table_1, scrollPane}));
		  
		  GroupListTable.addMouseListener(new MouseAdapter() {
		       @Override
		       public void mouseClicked(MouseEvent e) {
		           int row = GroupListTable.rowAtPoint(e.getPoint());
		           if (row >= 0) {
		               String groupName = (String) GroupListTable.getValueAt(row, 0);
		               String groupIntro = (String) GroupListTable.getValueAt(row, 1);
		               String groupStatus = (String) GroupListTable.getValueAt(row, 2);
		
		               JFrame groupDetailFrame = new JFrame("Group Detail");
		   groupDetailFrame.setSize(600, 400);
		   groupDetailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   groupDetailFrame.setLocationRelativeTo(null); // Center the frame
		
		   JPanel panel = new JPanel();
		   panel.setLayout(new GridLayout(3, 2, 10, 10));
		   panel.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		   panel.add(new JLabel("그룹 상세 정보"));
		              
		               groupDetailFrame.getContentPane().add(panel);
		               groupDetailFrame.setVisible(true);
		           }
		       }
		   });
		  
		  JTable table = new JTable();
		  table.setEnabled(true);
		  table.setFillsViewportHeight(true);
		  table.setColumnSelectionAllowed(true);
		  table.setCellSelectionEnabled(true);
		  table.setBounds(305, 488, 1, 1);
		  group.add(table);
		  
		  JTable table_1 = new JTable();
		  table_1.setBounds(316, 192, 1, 1);
		  group.add(table_1);
		  
		  
		  // 그룹생성 Tab
		  JPanel createGroup = new JPanel();
		  myPageTab.addTab("그룹생성", null, createGroup, null);
		  createGroup.setLayout(null);
		  
		  JLabel lbGroupName = new JLabel("그룹명");
		  lbGroupName.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbGroupName.setBounds(68, 31, 88, 41);
		  createGroup.add(lbGroupName);
		  
		  JLabel lbGroupIntro = new JLabel("그룹 소개");
		  lbGroupIntro.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbGroupIntro.setBounds(68, 95, 120, 41);
		  createGroup.add(lbGroupIntro);
		  
		  JLabel lbMaxMem = new JLabel("최대 수용 인원");
		  lbMaxMem.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbMaxMem.setBounds(68, 163, 168, 41);
		  createGroup.add(lbMaxMem);
		  
		  JTextField jtxGName = new JTextField();
		  jtxGName.setBounds(261, 34, 708, 41);
		  createGroup.add(jtxGName);
		  jtxGName.setColumns(10);
		  
		  JTextField jtxGIntro = new JTextField();
		  jtxGIntro.setColumns(10);
		  jtxGIntro.setBounds(261, 95, 708, 41);
		  createGroup.add(jtxGIntro);
		  
		  JTextField jtxMaxMem = new JTextField();
		  jtxMaxMem.setColumns(10);
		  jtxMaxMem.setBounds(261, 163, 708, 41);
		  createGroup.add(jtxMaxMem);
		  
		  JLabel lbSStatus = new JLabel("공개 여부");
		  lbSStatus.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbSStatus.setBounds(68, 233, 168, 41);
		  createGroup.add(lbSStatus);
		  
		  JLabel lbPW = new JLabel("비밀번호");
		  lbPW.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbPW.setBounds(68, 313, 168, 41);
		  createGroup.add(lbPW);
		  
		  //추후에 PW와 일치하지 않는 예외처리 함수 작성
		  JLabel lbPWCheck = new JLabel("비밀번호 확인");
		  lbPWCheck.setFont(new Font("굴림", Font.PLAIN, 25));
		  lbPWCheck.setBounds(68, 381, 168, 41);
		  createGroup.add(lbPWCheck);
		  
		  JPasswordField pwField = new JPasswordField();
		  pwField.setBounds(261, 313, 708, 41);
		  createGroup.add(pwField);
		  
		  JPasswordField pwCheckField = new JPasswordField();
		  pwCheckField.setBounds(261, 381, 708, 41);
		  createGroup.add(pwCheckField);
		  
		  JComboBox cmbSStatus = new JComboBox();
		  cmbSStatus.setFont(new Font("굴림", Font.PLAIN, 20));
		  cmbSStatus.setModel(new DefaultComboBoxModel(new String[] {"공개/비공개 여부", "공개", "비공개"}));
		  cmbSStatus.setBounds(261, 236, 708, 41);
		  createGroup.add(cmbSStatus);
		  
		  //모든 칸 지우기 이벤트
		  JButton btnReset = new JButton("내용 지우기");
		  btnReset.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
		         jtxGName.setText("");
		 jtxGIntro.setText("");
		 jtxMaxMem.setText("");
		 cmbSStatus.setSelectedItem("공개/비공개 여부");
		 pwField.setText("");
		 pwCheckField.setText("");
		     }
		  });
		  
		  btnReset.setFont(new Font("굴림", Font.BOLD, 25));
		  btnReset.setBounds(598, 556, 185, 57);
		  createGroup.add(btnReset);
		  
		  JButton btnCreateGroup = new JButton("그룹 생성");
		  btnCreateGroup.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent e) {
		        DefaultTableModel model = (DefaultTableModel) GroupListTable.getModel();
		        model.addRow(new Object[] {
		              jtxGName.getText(),
		                  jtxGIntro.getText(),
		                  cmbSStatus.getSelectedItem()
		        });
		        
		        if(table.getSelectedRow() == -1) {
		           if (table.getRowCount() == 0) {
		           JOptionPane.showMessageDialog(null, "그룹 생성 성공!", "그룹 생성",
		                 JOptionPane.OK_OPTION);
		           }
		        }
		     }
		  });
		  
		  btnCreateGroup.setFont(new Font("굴림", Font.BOLD, 25));
		  btnCreateGroup.setBounds(311, 556, 185, 57);
		  createGroup.add(btnCreateGroup);
		  
		  
		
		//-------------------------------------------------------------Tab
		
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
		
		//-------------------------------------------------------------좌측 상세정보
		
				
		
	}
}