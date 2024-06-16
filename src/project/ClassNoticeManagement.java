package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClassNoticeManagement {
    private CardLayout cardLayout;
    private JPanel classNoticePanel;
    private List<Notice> notices = new ArrayList<>();
    private MainPage mainPage = MainPage.getInstance();
    private JTable noticeTable;
    private JPanel classNoticeContainer;

    public ClassNoticeManagement(JPanel classNoticeContainer) {
        this.classNoticeContainer = classNoticeContainer;
        this.classNoticeContainer.setLayout(new CardLayout());
        initialize();
    }

    public void initialize() {
        classNoticePanel = new JPanel(new BorderLayout());

        noticeTable = new JTable();
        noticeTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"제목", "작성자", "작성일"}
        ));
        JScrollPane noticeScrollPane = new JScrollPane(noticeTable);
        
        // 테이블을 포함한 패널 생성 및 여백 설정
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // 좌우 여백 추가
        tablePanel.add(noticeScrollPane, BorderLayout.CENTER);
        
        classNoticePanel.add(noticeScrollPane, BorderLayout.CENTER);

        noticeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = noticeTable.getSelectedRow();
                if (row >= 0) {
                    String title = (String) noticeTable.getValueAt(row, 0);
                    String author = (String) noticeTable.getValueAt(row, 1);
                    String date = (String) noticeTable.getValueAt(row, 2);
                    Notice selectedNotice = getNoticeByDetails(title, author, date);
                    if (selectedNotice != null) {
                        displayNoticeDetail(selectedNotice);
                    }
                }
            }
        });

        String grade = mainPage.loginUser.getGrade();
        String classname = mainPage.loginUser.getClassName();

        JLabel noticeLabel = new JLabel(grade + "-" + classname + " 공지사항");
        noticeLabel.setFont(new Font("굴림", Font.PLAIN, 30));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        classNoticePanel.add(noticeLabel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton writeNoticeBtn = new JButton("");
        writeNoticeBtn.setBackground(Color.WHITE);
       ImageIcon icon = new ImageIcon("/images/pencil.png");
       Image img = icon.getImage();
       Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       ImageIcon updateIcon = new ImageIcon(updateImg);
       writeNoticeBtn.setIcon(updateIcon);
        // writeNoticeBtn.setPreferredSize(new Dimension(40, 40));
        
        writeNoticeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mainPage.loginUser.getUserType().equals("교사")) {
                    cardLayout.show(classNoticeContainer, "writeNoticePanel");
                } else {
                    JOptionPane.showMessageDialog(null, "공지사항을 작성할 권한이 없습니다.", "권한 없음", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bottomPanel.add(writeNoticeBtn);

        classNoticePanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel writeNoticePanel = new JPanel(new BorderLayout(10, 10)); // 여백 추가
        writeNoticePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 패널 여백 추가

        JPanel topPanel = new JPanel(new BorderLayout(10, 10)); // 여백 추가
        JButton backBtn = new JButton("");
        backBtn.setBackground(Color.WHITE);
       icon = new ImageIcon("/images/goback.png");
       img = icon.getImage();
       updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
       updateIcon = new ImageIcon(updateImg);
       backBtn.setIcon(updateIcon);
        // backBtn.setPreferredSize(new Dimension(50, 50));
        
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(classNoticeContainer, "classNoticePanel");
            }
        });
        topPanel.add(backBtn, BorderLayout.WEST);

        JPanel titlePanel = new JPanel(new BorderLayout(10, 10));
        JLabel ntitleLabel = new JLabel("제목: ");
        JTextField ntitleTextField = new JTextField();
        titlePanel.add(ntitleLabel, BorderLayout.WEST);
        titlePanel.add(ntitleTextField, BorderLayout.CENTER);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        writeNoticePanel.add(topPanel, BorderLayout.NORTH);

        JTextArea nwriteTextArea = new JTextArea();
        JScrollPane nwriteScrollPane = new JScrollPane(nwriteTextArea);
        writeNoticePanel.add(nwriteScrollPane, BorderLayout.CENTER);

        JPanel bottomWritePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitNoticeBtn = new JButton("작성 완료");
        submitNoticeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = ntitleTextField.getText();
                String content = nwriteTextArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    String author = mainPage.loginUser.getName();
                    LocalDate now = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String formattedDate = now.format(formatter);

                    Notice newNotice = new Notice(title, author, formattedDate, content);
                    notices.add(newNotice);
                    ((DefaultTableModel) noticeTable.getModel()).addRow(new Object[]{title, author, formattedDate});
                    ntitleTextField.setText("");
                    nwriteTextArea.setText("");
                    cardLayout.show(classNoticeContainer, "classNoticePanel");
                    mainPage.addNotice(newNotice);
                } else {
                    JOptionPane.showMessageDialog(null, "제목과 내용을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bottomWritePanel.add(submitNoticeBtn);
        writeNoticePanel.add(bottomWritePanel, BorderLayout.SOUTH);

        classNoticeContainer.add(classNoticePanel, "classNoticePanel");
        classNoticeContainer.add(writeNoticePanel, "writeNoticePanel");

        cardLayout = (CardLayout) classNoticeContainer.getLayout();
    }

    public Notice getNoticeByDetails(String title, String author, String date) {
        for (Notice notice : notices) {
            if (notice.getTitle().equals(title) && notice.getAuthor().equals(author) && notice.getDate().equals(date)) {
                return notice;
            }
        }
        return null;
    }

    public void displayNoticeDetail(Notice notice) {
        JFrame ndetailFrame = new JFrame(notice.getTitle());
        ndetailFrame.setLayout(new BorderLayout());
        ndetailFrame.setSize(800, 600);

        JLabel titleLabel = new JLabel(notice.getTitle());
        titleLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 패널 여백 추가
        ndetailFrame.add(titleLabel, BorderLayout.NORTH);

        JTextArea contentArea = new JTextArea(notice.getContent());
        contentArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 패널 여백 추가
        contentArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        ndetailFrame.add(contentScrollPane, BorderLayout.CENTER);

        JPanel authorPanel = new JPanel(new BorderLayout());
        JLabel authorLabel = new JLabel("작성자: " + notice.getAuthor());
        JLabel dateLabel = new JLabel("작성일: " + notice.getDate());
        authorPanel.add(authorLabel, BorderLayout.WEST);
        authorPanel.add(dateLabel, BorderLayout.EAST);
        ndetailFrame.add(authorPanel, BorderLayout.SOUTH);

        ndetailFrame.setVisible(true);
    }
}