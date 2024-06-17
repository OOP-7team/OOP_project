package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class FindPW extends JFrame {

    private JPanel contentPane;
    private JComboBox<String> questionComboBox; // questionComboBox 필드 추가
    private JTextField answerField; // answerField 필드 추가
    private JTextField usernameField; // 아이디 입력 필드 추가
    private List<String> questionList;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindPW frame = new FindPW();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FindPW() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(">비밀번호 찾기");
        lblNewLabel.setForeground(new Color(128, 255, 128));
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel.setBounds(28, 20, 167, 32);
        contentPane.add(lblNewLabel);

        // 아이디 입력 필드
        JLabel usernameLabel = new JLabel("아이디");
        usernameLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        usernameLabel.setBounds(128, 80, 150, 30);
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("굴림", Font.PLAIN, 25));
        usernameField.setBounds(278, 80, 360, 40);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        questionList = new ArrayList<>();
        questionList.add("당신이 가장 존경하는 교수님 성함은?");
        questionList.add("당신의 어머니의 성함은?");
        questionList.add("당신의 아버지의 성함은?");
        questionList.add("당신이 가장 기억에 남는 책은?");
        questionList.add("가장 좋아하는 음식은?");

        JLabel securityQuestionLabel = new JLabel("보안 질문");
        securityQuestionLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        securityQuestionLabel.setBounds(128, 150, 150, 30);
        contentPane.add(securityQuestionLabel);

        questionComboBox = new JComboBox<>(questionList.toArray(new String[0])); // questionComboBox 초기화
        questionComboBox.setFont(new Font("굴림", Font.PLAIN, 20));
        questionComboBox.setBounds(278, 150, 380, 40);
        contentPane.add(questionComboBox);

        // 보안 질문 답변 라벨과 텍스트 필드
        JLabel securityAnswerLabel = new JLabel("보안 질문 답변");
        securityAnswerLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        securityAnswerLabel.setBounds(128, 200, 200, 30);
        contentPane.add(securityAnswerLabel);

        answerField = new JTextField();
        answerField.setFont(new Font("굴림", Font.PLAIN, 25));
        answerField.setBounds(328, 200, 310, 40);
        contentPane.add(answerField);
        answerField.setColumns(10);
       

        JButton btnNewButton = new JButton("확인");
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
        btnNewButton.setBackground(new Color(128, 255, 128));
        btnNewButton.setBounds(411, 345, 67, 35);
        contentPane.add(btnNewButton);
        
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력된 아이디, 보안 질문, 답변, 새 비밀번호 가져오기
                String username = usernameField.getText();
                String securityQuestion = (String) questionComboBox.getSelectedItem();
                String answer = answerField.getText();

                // 입력된 아이디와 보안 질문 답변이 일치하는지 확인하고 비밀번호 변경
                UserDataSet users = UserDataSet.getUserDataSetInstance();
                User user = users.getUser(username);
                if (user != null) {
                    // 사용자가 입력한 보안 질문과 실제 사용자의 보안 질문이 일치하는지 확인
                    if (user.getSecurityQuestion().equals(securityQuestion)) {
                        if (user.changePassword(username, answer, "")) {
                            // 비밀번호 변경을 위한 대화 상자 표시
                            String newPassword = JOptionPane.showInputDialog(null, "새로운 비밀번호를 입력하세요:", "비밀번호 변경", JOptionPane.PLAIN_MESSAGE);
                            if (newPassword != null) {
                                if (user.changePassword(username, answer, newPassword)) {
                                    JOptionPane.showMessageDialog(null, "비밀번호가 성공적으로 변경되었습니다.", "비밀번호 변경 성공", JOptionPane.INFORMATION_MESSAGE);
                                    dispose(); // FindPW 창 닫기
                                } else {
                                    JOptionPane.showMessageDialog(null, "비밀번호 변경에 실패했습니다.", "비밀번호 변경 실패", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "입력한 정보가 올바르지 않습니다.", "정보 확인", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "보안 질문이 일치하지 않습니다.", "정보 확인", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}

        
      
