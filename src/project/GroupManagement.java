package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GroupManagement {
    private JFrame groupDetailFrame;
    private JPanel groupDetailPanel;
    private CardLayout cardLayout;
    private boolean isGroupJoined = false; // 그룹 가입 여부를 저장하는 변수
    private JPanel postDetailPanel;
    private List<Post> posts = new ArrayList<>(); // 게시글 목록을 저장하는 리스트

    public void displayDetail(String groupName, String groupIntro) {
        groupDetailFrame = new JFrame(groupName);
        groupDetailFrame.setSize(800, 600);
        groupDetailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        groupDetailFrame.setLocationRelativeTo(null);
        groupDetailFrame.setLayout(new BorderLayout());
        groupDetailFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        groupDetailPanel = new JPanel(cardLayout);

        // 그룹 정보 및 게시판 패널
        JPanel groupInfoPanel = new JPanel();
        groupInfoPanel.setLayout(null);
        // 카드 레이아웃에 패널 추가
        groupDetailPanel.add(groupInfoPanel, "groupInfoPanel");

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel GNameLabel = new JLabel(groupName);
        GNameLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        JLabel GIntroLabel = new JLabel(groupIntro);
        GIntroLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        GIntroLabel.setForeground(Color.GRAY);
        infoPanel.add(GNameLabel);
        infoPanel.add(GIntroLabel);
        infoPanel.setBounds(100, 20, 500, 50);
        
        // 그룹 가입 버튼
        JButton joinGroupBtn = new JButton("가입");
        joinGroupBtn.setBounds(620, 40, 80, 40);
        joinGroupBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               isGroupJoined = true; // 그룹 가입 여부 업데이트
                JOptionPane.showMessageDialog(groupDetailFrame, "그룹에 가입되었습니다.", "가입 성공", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // 그룹 게시글 작성 버튼
        JButton writeBtn = new JButton("");
        writeBtn.setBackground(Color.WHITE);
        ImageIcon icon = new ImageIcon(GroupManagement.class.getResource("/images/pencil.png"));
       Image img = icon.getImage();
       Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       ImageIcon updateIcon = new ImageIcon(updateImg);
       writeBtn.setIcon(updateIcon);
       // writeBtn.setPreferredSize(new Dimension(40, 40));
        writeBtn.setBounds(650, 520, 50, 50);
        
        writeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isGroupJoined) {
                   cardLayout.show(groupDetailPanel, "writePanel");
                } else {
                   JOptionPane.showMessageDialog(groupDetailFrame, "그룹에 가입 후에 게시글을 작성할 수 있습니다.", "가입 필요", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        JTable groupBoardTable = new JTable();
        groupBoardTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"제목", "작성자", "작성일"}
        ));
        
        JScrollPane boardScrollPane = new JScrollPane(groupBoardTable);
        // 테이블을 포함한 패널 생성 및 여백 설정
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // 좌우 여백 추가
        tablePanel.add(boardScrollPane, BorderLayout.CENTER);
        
        boardScrollPane.setBounds(100, 100, 600, 400);
        // 게시글 테이블 이벤트 리스너에서 클릭한 게시글의 상세 정보를 보여주는 메서드 호출
        groupBoardTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = groupBoardTable.getSelectedRow();
                String title = (String) groupBoardTable.getValueAt(row, 0);
                String author = (String) groupBoardTable.getValueAt(row, 1);
                String date = (String) groupBoardTable.getValueAt(row, 2);
                
                // 게시글 내용을 가져오는 코드 추가
                for (Post post : posts) {
                   if (post.getTitle().equals(title) && post.getAuthor().equals(author) && post.getDate().equals(date)) {
                        showPostDetail(title, author, date, post.getContent());
                        // 기존의 그룹 상세 정보 패널 대신에 게시글 상세 정보 패널로 교체
                        groupDetailPanel.add(postDetailPanel, "postDetailPanel");
                        cardLayout.show(groupDetailPanel, "postDetailPanel");
                        break;
                    }
                }
            }
        });
        
        // 초기 패널 설정
        cardLayout.show(groupDetailPanel, "groupInfoPanel");

        groupInfoPanel.add(infoPanel);
        groupInfoPanel.add(joinGroupBtn);
        groupInfoPanel.add(boardScrollPane);
        groupInfoPanel.add(writeBtn);

        // 게시글 쓰기 패널
        JPanel writePanel = new JPanel();
        writePanel.setLayout(null);

        // 제목 입력 부분
        JLabel titleLabel = new JLabel("제목 : ");
        titleLabel.setBounds(100, 40, 50, 25);
        JTextField titleTextField = new JTextField();
        titleTextField.setBounds(150, 40, 550, 25);

        // 내용 입력 부분
        JTextArea writeTextArea = new JTextArea();
        JScrollPane writeScrollPane = new JScrollPane(writeTextArea);
        writeScrollPane.setBounds(100, 100, 600, 400);
        
        JButton submitWriteBtn = new JButton("작성 완료");
        submitWriteBtn.setBounds(370, 530, 60, 40);
        submitWriteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 작성 완료 후 처리
                String title = titleTextField.getText();
                String content = writeTextArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    // MainPage 클래스의 인스턴스를 얻어옴
                    MainPage mainPage = MainPage.getInstance();
                    // MainPage 클래스의 인스턴스를 통해 getLoginUser() 호출하여 작성자 정보 획득
                    String author = mainPage.getLoginUser().getName();
                    
                    LocalDate now = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String formattedDate = now.format(formatter); // 현재 날짜(작성 날짜)를 보여줌
                    
                    // 게시글을 리스트에 추가
                    posts.add(new Post(title, author, formattedDate, content));
                    
                    ((DefaultTableModel) groupBoardTable.getModel()).addRow(new Object[]{title, author, formattedDate});
                    titleTextField.setText("");
                    writeTextArea.setText("");
                    cardLayout.show(groupDetailPanel, "groupInfoPanel");
                } else {
                    JOptionPane.showMessageDialog(groupDetailFrame, "제목과 내용을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
       // 뒤로 가기 버튼
       JButton backBtnWrite = new JButton("");
       backBtnWrite.setBackground(Color.WHITE);
       icon = new ImageIcon(GroupManagement.class.getResource("/images/goback.png"));
       img = icon.getImage();
       updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       updateIcon = new ImageIcon(updateImg);
       backBtnWrite.setIcon(updateIcon);
       // backBtnWrite.setPreferredSize(new Dimension(40, 40));
        backBtnWrite.setBounds(10, 10, 50, 50);
        
        backBtnWrite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(groupDetailPanel, "groupInfoPanel");
            }
        });
        
        // 스크롤바 추가
        writeTextArea.setRows(10); // 행 크기 조정
        writeTextArea.setLineWrap(true); // 텍스트 영역이 너무 길어지면 자동 줄바꿈
        
        // 작성 패널 설정
        writePanel.add(titleLabel);
        writePanel.add(titleTextField);
        writePanel.add(writeScrollPane);
        writePanel.add(submitWriteBtn);
        writePanel.add(backBtnWrite);

        
        // 카드 레이아웃에 패널 추가
        groupDetailPanel.add(writePanel, "writePanel");

        groupDetailFrame.add(groupDetailPanel, BorderLayout.CENTER);
        groupDetailFrame.setVisible(true);
        
        initializePostDetailPanel();
    }
    
    // 게시글 상세 정보를 보여주는 메서드
    private void showPostDetail(String title, String author, String date, String content) {
        // 새로운 패널을 생성하여 게시글 상세 정보를 표시
        postDetailPanel = new JPanel();
        postDetailPanel.setLayout(null); // null 레이아웃 설정
        
        // 제목 표시
        JLabel titleLabel = new JLabel("제목: " + title);
        titleLabel.setFont(new Font("굴림", Font.BOLD, 20));
        titleLabel.setBounds(100, 40, 500, 25); // 위치와 크기 지정

        // 작성자 표시
        JLabel authorLabel = new JLabel("작성자: " + author);
        authorLabel.setBounds(100, 70, 300, 20); // 위치와 크기 지정

        // 작성 날짜 표시
        JLabel dateLabel = new JLabel("작성 날짜: " + date);
        dateLabel.setBounds(100, 90, 300, 20); // 위치와 크기 지정

        // 내용 표시
        JTextArea contentTextArea = new JTextArea(content);
        contentTextArea.setEditable(false);
        contentTextArea.setLineWrap(true);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        contentScrollPane.setBounds(100, 120, 600, 400); // 위치와 크기 지정
        
        // 뒤로 가기 버튼 생성 및 표시
        JButton backBtnDetail = new JButton("");
        backBtnDetail.setBackground(Color.WHITE);
       ImageIcon icon = new ImageIcon(GroupManagement.class.getResource("/images/goback.png"));
       Image img = icon.getImage();
       Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       ImageIcon updateIcon = new ImageIcon(updateImg);
       backBtnDetail.setIcon(updateIcon);
       // backBtnDetail.setPreferredSize(new Dimension(40, 40));
        backBtnDetail.setBounds(10, 10, 50, 50);
        
        backBtnDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(groupDetailPanel, "groupInfoPanel");
            }
        });

        // 패널에 컴포넌트 추가
        postDetailPanel.add(titleLabel);
        postDetailPanel.add(authorLabel);
        postDetailPanel.add(dateLabel);
        postDetailPanel.add(contentScrollPane);
        postDetailPanel.add(backBtnDetail);
    }


    // 게시글 상세 정보를 보여주는 패널 초기화
    private void initializePostDetailPanel() {
        postDetailPanel = new JPanel();
        postDetailPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("제목: ");
        JLabel authorLabel = new JLabel("작성자: ");
        JLabel dateLabel = new JLabel("작성 날짜: ");
        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        postDetailPanel.add(titleLabel, BorderLayout.NORTH);
        postDetailPanel.add(authorLabel, BorderLayout.CENTER);
        postDetailPanel.add(dateLabel, BorderLayout.SOUTH);
        postDetailPanel.add(contentScrollPane, BorderLayout.CENTER);
    }
}