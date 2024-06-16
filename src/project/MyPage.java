package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

public class MyPage extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static MyPage myPageInstance;
	public static JPanel myBook;
	private JTable myDetail;
	private static JPanel myStudyPaper;
	private JButton uploadButton;

    public static MyPage getInstance() {
        if (myPageInstance == null) {
            myPageInstance = new MyPage();
        }
        return myPageInstance;
    }

	
	// MyPage 객체 생성자
	public MyPage() {
		setLayout(new BorderLayout());
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
		// myPage의 다양한 메뉴가 있는 Tab바 : myPageTab
		JTabbedPane myPageTab = new JTabbedPane(JTabbedPane.LEFT);
		myPageTab.setFont(new Font("굴림", Font.PLAIN, 30));
		add(myPageTab, BorderLayout.CENTER);
		
		User currentUser = MainPage.getLoginUser();
		
		 // 내 정보 탭 생성
        JPanel myinform = new JPanel();
        myPageTab.addTab("내 정보", null, myinform, null);
        myinform.setLayout(null);
        
        // 이름 입력 필드
        JLabel nameLabel = new JLabel("이름:");
        nameLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        nameLabel.setBounds(50, 50, 100, 30);
        myinform.add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        myinform.add(nameField);
        
        // 학교 입력 필드
        JLabel schoolLabel = new JLabel("학교:");
        schoolLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        schoolLabel.setBounds(50, 100, 100, 30);
        myinform.add(schoolLabel);
        JTextField schoolField = new JTextField();
        schoolField.setBounds(150, 100, 200, 30);
        myinform.add(schoolField);
        
        // 학년 입력 필드
        JLabel gradeLabel = new JLabel("학년:");
        gradeLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        gradeLabel.setBounds(50, 150, 100, 30);
        myinform.add(gradeLabel);
        JTextField gradeField = new JTextField();
        gradeField.setBounds(150, 150, 200, 30);
        myinform.add(gradeField);
        
        // 반 입력 필드
        JLabel classLabel = new JLabel("반:");
        classLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        classLabel.setBounds(50, 200, 100, 30);
        myinform.add(classLabel);
        JTextField classField = new JTextField();
        classField.setBounds(150, 200, 200, 30);
        myinform.add(classField);
        
        // 생년월일 입력 필드
        JLabel birthLabel = new JLabel("생년월일:");
        birthLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        birthLabel.setBounds(50, 250, 100, 30);
        myinform.add(birthLabel);
        JTextField birthField = new JTextField();
        birthField.setBounds(150, 250, 200, 30);
        myinform.add(birthField);
        
        // 사용자 정보 설정
        nameField.setText(currentUser.getName());
        schoolField.setText(currentUser.getSchool());
        gradeField.setText(currentUser.getGrade());
        classField.setText(currentUser.getClassName());
        birthField.setText(currentUser.getBirthDate());
        
        // 지도보기 버튼
        JButton mapButton = new JButton("지도보기");
        mapButton.setFont(new Font("굴림", Font.PLAIN, 20));
        mapButton.setBounds(50, 300, 150, 40);
        mapButton.setBackground(new Color(173, 216, 230));
        myinform.add(mapButton);
        
        // 지도보기 버튼 이벤트 처리
        mapButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MyMap myMap = new MyMap();
        	}
        });
        
        // 수정하기 버튼
        JButton editButton = new JButton("수정하기");
        editButton.setFont(new Font("굴림", Font.PLAIN, 20));
        editButton.setBounds(50, 350, 150, 40);
        editButton.setBackground(new Color(192, 236, 149));
        myinform.add(editButton);

        // 수정하기 버튼 이벤트 처리
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 입력 필드들을 수정 가능하도록 설정
                nameField.setEditable(true);
                schoolField.setEditable(true);
                gradeField.setEditable(true);
                classField.setEditable(true);
                birthField.setEditable(true);
                
                // 저장 버튼 생성
                JButton saveButton = new JButton("저장");
                saveButton.setFont(new Font("굴림", Font.PLAIN, 20));
                saveButton.setBounds(210, 300, 150, 40);
                saveButton.setBackground(new Color(192, 236, 149));
                myinform.add(saveButton);
                
                // 저장 버튼 이벤트 처리
                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 수정된 정보를 가져와서 저장
                        String newName = nameField.getText();
                        String newSchool = schoolField.getText();
                        String newGrade = gradeField.getText();
                        String newClass = classField.getText();
                        String newBirth = birthField.getText();
                        
                        // 여기서 저장하는 작업을 수행
                        // 예를 들어, 사용자 정보를 수정하는 메서드를 호출하고 저장하는 등의 작업을 수행
                        
                        // 저장 후 입력 필드들을 다시 수정 불가능하게 설정
                        nameField.setEditable(false);
                        schoolField.setEditable(false);
                        gradeField.setEditable(false);
                        classField.setEditable(false);
                        birthField.setEditable(false);
                        
                        // 저장 버튼 제거
                        myinform.remove(saveButton);
                        
                        // 화면을 다시 그리기 위해 패널을 다시 그림
                        myinform.revalidate();
                        myinform.repaint();
                    }
                });
                
                // 화면을 다시 그리기 위해 패널을 다시 그림
                myinform.revalidate();
                myinform.repaint();
            }
        });
        
     // 회원탈퇴 버튼
       JButton deleteAccountButton = new JButton("회원탈퇴");
       deleteAccountButton.setFont(new Font("굴림", Font.PLAIN, 20));
       deleteAccountButton.setBounds(50, 400, 150, 40);
       deleteAccountButton.setBackground(new Color(255, 105, 97)); // 붉은색으로 배경 설정
       myinform.add(deleteAccountButton);
       
       deleteAccountButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int response = JOptionPane.showConfirmDialog(null, "정말로 회원탈퇴를 하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
               if (response == JOptionPane.YES_OPTION) {
                   // 회원탈퇴 작업 수행
                   MainPage.deleteAccount();
                   JOptionPane.showMessageDialog(null, "회원탈퇴가 완료되었습니다.", "회원탈퇴", JOptionPane.INFORMATION_MESSAGE);
                   MainPage.performLogout(); // Call the logout method

               }
           }
       });

       
    // 학급 공부시간 Tab
       JPanel classStudyTime = new JPanel(new BorderLayout());
       myPageTab.addTab("학급 공부시간", null, classStudyTime, null);

       // 스탑워치 패널 추가 (상단 오른쪽)
       StopWatch stopWatchPanel = new StopWatch();
       stopWatchPanel.setDummyData(); // 더미 데이터 설정
       classStudyTime.add(stopWatchPanel, BorderLayout.CENTER);

       // 학급 공지사항 Tab
       JPanel classNotice = new JPanel(new BorderLayout());
       myPageTab.addTab("학급 공지사항", null, classNotice, null);

       // ClassNoticeManagement 객체 생성
       ClassNoticeManagement noticeManagement = new ClassNoticeManagement(classNotice);
       noticeManagement.initialize();

       // 나의 교과서 Tab
       myBook = new JPanel();
       myPageTab.addTab("나의 교과서", null, myBook, null);

       // 나의 학습자료 Tab
       JPanel myStudyPaper = new JPanel(new BorderLayout());
       myPageTab.addTab("나의 학습자료", null, myStudyPaper, null);
       
         /* 학습자료 업로드 */ 
       JPanel Studypaper_bottom = new JPanel(new BorderLayout());
       myStudyPaper.add(Studypaper_bottom, BorderLayout.SOUTH);

       JPanel Studypaper_file = new JPanel();
       myStudyPaper.add(Studypaper_file);
       
         JButton uploadButton = new JButton(new ImageIcon(""));
         uploadButton.setBackground(Color.WHITE);
         uploadButton.setIcon(new ImageIcon(MyPage.class.getResource("/images/plusfile.png")));
         Studypaper_bottom.add(uploadButton);
         
         uploadButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if (MainPage.getLoginUser().getUserType().equals("교사")) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                         File selectedFile = fileChooser.getSelectedFile();
                         addFileButton(selectedFile);
                     }
                     File file = new File("/path/to/your/file"); // 예시 파일 경로
                 } else {
                     JOptionPane.showMessageDialog(null, "업로드 권한이 없습니다", "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }

             private void addFileButton(File file) {
                 JButton fileButton = new JButton(file.getName());
                 fileButton.setPreferredSize(new Dimension(100, 30));
                 
                 fileButton.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {
                             Desktop.getDesktop().open(file);
                         } catch (IOException ioException) {
                             JOptionPane.showMessageDialog(myStudyPaper, "파일을 열 수 없습니다: " + ioException.getMessage());
                         }
                     }
                 });
                 Studypaper_file.add(fileButton);
                 Studypaper_file.revalidate();
                 Studypaper_file.repaint();
                 /*myStudyPaper.add(fileButton);
                 myStudyPaper.revalidate();
                 myStudyPaper.repaint();*/
             }
         });
       

       

       // 그룹목록 Tab
       JPanel group = new JPanel(new BorderLayout());
       myPageTab.addTab("그룹목록", null, group, null);

       // 검색 패널
       JPanel searchPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER));
       group.add(searchPanel, BorderLayout.NORTH);

       JTextField searchField = new JTextField(" 그룹명", 40);
       searchField.setBackground(new Color(255, 255, 255));
       searchField.setFont(new Font("굴림", Font.PLAIN, 18));
       searchPanel.add(searchField);

       JButton searchBtn = new JButton("");
       searchBtn.setBackground(Color.WHITE);
       ImageIcon icon = new ImageIcon(MyPage.class.getResource("/images/search.png"));
       Image img = icon.getImage();
       Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       ImageIcon updateIcon = new ImageIcon(updateImg);
      searchBtn.setIcon(updateIcon);
       searchPanel.add(searchBtn);

       // 그룹 목록 테이블
       JTable GroupListTable = new JTable();
       GroupListTable.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));   // 여백 생성
       GroupListTable.setForeground(new Color(0, 0, 0));
       GroupListTable.setFillsViewportHeight(true);
       GroupListTable.setModel(new DefaultTableModel(
           new Object[][] {},
           new String[] {"그룹명", "그룹 소개", "공개 여부"}
       ));
       GroupListTable.setColumnSelectionAllowed(true);
       GroupListTable.setCellSelectionEnabled(true);
       GroupListTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

       JScrollPane scrollPane = new JScrollPane(GroupListTable);
       group.add(scrollPane, BorderLayout.CENTER);

       // 그룹 비밀번호를 저장하기 위한 Hashtable
       Hashtable<String, String> groupPasswords = new Hashtable<>();

       GroupListTable.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               int row = GroupListTable.rowAtPoint(e.getPoint());
               if (row >= 0) {
                   String groupName = (String) GroupListTable.getValueAt(row, 0);
                   String groupIntro = (String) GroupListTable.getValueAt(row, 1);
                   String groupStatus = (String) GroupListTable.getValueAt(row, 2);

                   GroupManagement groupManagement = new GroupManagement();

                   if (groupStatus.equals("비공개")) {
                       String inputPW = JOptionPane.showInputDialog(null, "비밀번호를 입력하세요:", "비공개 그룹", JOptionPane.PLAIN_MESSAGE);
                       if (inputPW != null) {
                           String correctPW = groupPasswords.get(groupName);
                           if (inputPW.equals(correctPW)) {
                               groupManagement.displayDetail(groupName, groupIntro);
                           } else {
                               JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                           }
                       }
                   } else {
                       groupManagement.displayDetail(groupName, groupIntro);
                   }
               }
           }
       });

       // 그룹생성 Tab
       JPanel createGroup = new JPanel(new BorderLayout());
       myPageTab.addTab("그룹생성", null, createGroup, null);

       JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
       createGroup.add(formPanel, BorderLayout.CENTER);

       JLabel lbGroupName = new JLabel("그룹명");
       lbGroupName.setFont(new Font("굴림", Font.PLAIN, 25));
       formPanel.add(lbGroupName);

       JTextField jtxGName = new JTextField();
       formPanel.add(jtxGName);

       JLabel lbGroupIntro = new JLabel("그룹 소개");
       lbGroupIntro.setFont(new Font("굴림", Font.PLAIN, 25));
       formPanel.add(lbGroupIntro);

       JTextField jtxGIntro = new JTextField();
       formPanel.add(jtxGIntro);

       JLabel lbSStatus = new JLabel("공개 여부");
       lbSStatus.setFont(new Font("굴림", Font.PLAIN, 25));
       formPanel.add(lbSStatus);

       JComboBox<String> cmbSStatus = new JComboBox<>(new String[] {"공개/비공개 여부", "공개", "비공개"});
       cmbSStatus.setFont(new Font("굴림", Font.PLAIN, 20));
       formPanel.add(cmbSStatus);

       JLabel lbPW = new JLabel("비밀번호");
       lbPW.setFont(new Font("굴림", Font.PLAIN, 25));
       formPanel.add(lbPW);

       JPasswordField pwField = new JPasswordField();
       formPanel.add(pwField);

       JLabel lbPWCheck = new JLabel("비밀번호 확인");
       lbPWCheck.setFont(new Font("굴림", Font.PLAIN, 25));
       formPanel.add(lbPWCheck);

       JPasswordField pwCheckField = new JPasswordField();
       formPanel.add(pwCheckField);

       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       createGroup.add(buttonPanel, BorderLayout.SOUTH);

       JButton btnReset = new JButton("내용 지우기");
       btnReset.setFont(new Font("굴림", Font.BOLD, 25));
       buttonPanel.add(btnReset);

       btnReset.addActionListener(e -> {
           jtxGName.setText("");
           jtxGIntro.setText("");
           cmbSStatus.setSelectedItem("공개/비공개 여부");
           pwField.setText("");
           pwCheckField.setText("");
       });

       JButton btnCreateGroup = new JButton("그룹 생성");
       btnCreateGroup.setFont(new Font("굴림", Font.BOLD, 25));
       buttonPanel.add(btnCreateGroup);

       btnCreateGroup.addActionListener(e -> {
           DefaultTableModel model = (DefaultTableModel) GroupListTable.getModel();
           String groupName = jtxGName.getText();
           String groupIntro = jtxGIntro.getText();
           String groupStatus = (String) cmbSStatus.getSelectedItem();

           // 비밀번호 필드의 텍스트 가져오기
           String password = new String(pwField.getPassword());

           // 비밀번호 확인 필드의 텍스트 가져오기
           String confirmPassword = new String(pwCheckField.getPassword());
           
           if (groupStatus.equals("비공개") ) {   
              // 모든 필드가 채워져 있는지 확인
              if (groupName.isEmpty() || groupIntro.isEmpty() || groupStatus.equals("공개/비공개 여부") || password.isEmpty() || confirmPassword.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                 return;
              }
              // 비공개 그룹이면서 비밀번호가 일치하는지 확인
              if (!password.equals(confirmPassword)) {
                 JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                  return;
              }
           }
           
           if (groupStatus.equals("공개") ) {   
              // 모든 필드가 채워져 있는지 확인
              if (groupName.isEmpty() || groupIntro.isEmpty() || groupStatus.equals("공개/비공개 여부")) {
                 JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                 return;
              }
           }
           
           // 그룹 목록에 새로운 행 추가
           model.addRow(new Object[]{
               groupName,
               groupIntro,
               groupStatus
           });

           // 그룹이 비공개인 경우 비밀번호를 해시 테이블에 저장
           if (groupStatus.equals("비공개")) {
               groupPasswords.put(groupName, password);
           }

           // 입력 필드 초기화
           jtxGName.setText("");
           jtxGIntro.setText("");
           cmbSStatus.setSelectedItem("공개/비공개 여부");
           pwField.setText("");
           pwCheckField.setText("");

           JOptionPane.showMessageDialog(null, "그룹 생성 성공!", "그룹 생성", JOptionPane.INFORMATION_MESSAGE);
       });
      
      //-------------------------------------------------------------Tab

		
		// 좌측 내 정보가 들어갈 JPanel. myProfile 객체와 myDetail 객체가 들어감
		JPanel myInfo = new JPanel();
		myInfo.setBackground(Color.WHITE);
		add(myInfo, BorderLayout.WEST);
		
		// 내 프로필 사진이 들어갈 JLabel: myProfile
		JLabel myProfile = new JLabel("");
		//myProfile.setHorizontalAlignment(SwingConstants.CENTER);
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
				{"학년", MainPage.getLoginUser().getGrade()},
				{"반", MainPage.getLoginUser().getClassName()},
				{"공부시간", MainPage.getLoginUser().getStudyTime()}
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