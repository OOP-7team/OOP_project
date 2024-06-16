package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FindID extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldBirthDate; // 생년월일 입력 필드
    private JPasswordField textFieldSecret; // 주민등록번호 뒷자리 입력 필드

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindID frame = new FindID();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FindID() {
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(">아이디 찾기");
        lblNewLabel.setForeground(new Color(191, 242, 153));
        lblNewLabel.setFont(new Font("굴림체", Font.PLAIN, 30));
        lblNewLabel.setBounds(12, 10, 215, 27);
        contentPane.add(lblNewLabel);

        textFieldBirthDate = new JTextField();
        textFieldBirthDate.setBounds(322, 256, 150, 27);
        contentPane.add(textFieldBirthDate);
        textFieldBirthDate.setColumns(10);

        textFieldSecret = new JPasswordField();
        textFieldSecret.setBounds(322, 308, 150, 27);
        contentPane.add(textFieldSecret);
        textFieldSecret.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("생년월일");
        lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(161, 253, 86, 29);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("주민등록번호 뒷자리");
        lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(110, 305, 200, 29);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton = new JButton("확인");
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
        btnNewButton.setBackground(new Color(128, 255, 128));
        btnNewButton.setBounds(411, 345, 67, 35);
        contentPane.add(btnNewButton);

        // "확인" 버튼 클릭 시 실행되는 리스너 추가
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String birthDate = textFieldBirthDate.getText();
                String secret = new String(textFieldSecret.getPassword());

                // 사용자가 입력한 정보가 비어 있는지 확인
                if (birthDate.isEmpty() || secret.isEmpty()) {
                    JOptionPane.showMessageDialog(FindID.this,
                            "생년월일과 주민등록번호 뒷자리를 모두 입력하세요.",
                            "입력 오류",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // users 객체를 가져와서 사용
                UserDataSet users = UserDataSet.getUserDataSetInstance();
                User user = users.findUserByBirthDateAndSecret(birthDate, secret);

                if (user != null) {
                    String userId = user.getUserId();
                    String maskedUserId = maskUserId(userId);
                    JOptionPane.showMessageDialog(FindID.this,
                            "아이디: " + maskedUserId,
                            "아이디 찾기 성공",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FindID.this,
                            "일치하는 사용자를 찾을 수 없습니다.",
                            "아이디 찾기 실패",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

 // 아이디의 가운데 4자리를 *로 가리는 메서드
    private String maskUserId(String userId) {
        int length = userId.length();
        if (length <= 4) {
            // 아이디가 4자 이하인 경우, 전체를 *로 가림
            StringBuilder maskedId = new StringBuilder();
            for (int i = 0; i < length; i++) {
                maskedId.append('*');
            }
            return maskedId.toString();
        }
        // 아이디 가운데 4자리를 *로 가림
        int start = (length - 4) / 2;
        StringBuilder maskedId = new StringBuilder(userId);
        for (int i = start; i < start + 4; i++) {
            maskedId.setCharAt(i, '*');
        }
        return maskedId.toString();
    }
}
