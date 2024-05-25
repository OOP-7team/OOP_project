package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.regex.Pattern;

public class JoinForm extends JFrame {

    private JPanel contentPane; // 메인 패널
    private JTextField textField; // 이름 입력 필드
    private JTextField textField_1; // 아이디/이메일 입력 필드
    private JPasswordField textField_2; // 비밀번호 입력 필드
    private JPasswordField textField_3; // 비밀번호 확인 입력 필드
    private JTextField textField_4; // 학교 입력 필드
    private JTextField textField_5; // 학년 입력 필드
    private JTextField textField_6; // 반 입력 필드
    private JLabel lblNewLabel_5; // 학년 라벨
    private JLabel lblNewLabel_6; // 반 라벨
    private JButton btnNewButton; // 가입하기 버튼
    private JTextField textField_7; // (사용되지 않음)
    private JLabel lblNewLabel_7; // 생년월일 라벨
    private JTextField textField_8; // 생년월일 입력 필드
    private JPasswordField textField_9; // 주민등록번호 뒷자리 입력 필드
    private JButton btnShowPassword; // 비밀번호 보기 버튼
    private JButton btnShowConfirmPassword; // 비밀번호 확인 보기 버튼
    private JButton btnShowSecret; // 주민등록번호 보기 버튼
    private JLabel lblNewLabel_8; // 주민등록번호 뒷자리 라벨
    private JLabel lblNewLabel_9; // 비밀번호 안내 라벨
    private JLabel lblNewLabel_10; // 정보 입력 안내 라벨
    private JRadioButton rdbtnNewRadioButton; // 학생 라디오 버튼
    private JRadioButton rdbtnNewRadioButton_1; // 교사 라디오 버튼
    private ButtonGroup group; // 라디오 버튼 그룹

    public static void main(String[] args) {
        // 메인 메소드: 애플리케이션 시작 지점
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JoinForm frame = new JoinForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JoinForm() {
        // 프레임 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 이름 입력 필드
        textField = new JTextField();
        textField.setBounds(54, 65, 143, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        // 아이디/이메일 입력 필드
        textField_1 = new JTextField();
        textField_1.setBounds(190, 96, 143, 21);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        // 비밀번호 입력 필드
        textField_2 = new JPasswordField();
        textField_2.setBounds(190, 127, 143, 21);
        contentPane.add(textField_2);

        // 비밀번호 보기 버튼
        btnShowPassword = new JButton("보기");
        btnShowPassword.setBounds(340, 127, 60, 21);
        btnShowPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowPassword);

        // 비밀번호 확인 입력 필드
        textField_3 = new JPasswordField();
        textField_3.setBounds(190, 158, 143, 21);
        contentPane.add(textField_3);

        // 비밀번호 확인 보기 버튼
        btnShowConfirmPassword = new JButton("보기");
        btnShowConfirmPassword.setBounds(340, 158, 60, 21);
        btnShowConfirmPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowConfirmPassword);

        // 학교 입력 필드
        textField_4 = new JTextField();
        textField_4.setBounds(139, 251, 143, 23);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        // 학년 입력 필드
        textField_5 = new JTextField();
        textField_5.setBounds(139, 284, 36, 21);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        // 반 입력 필드
        textField_6 = new JTextField();
        textField_6.setBounds(223, 284, 36, 21);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        // 주민등록번호 뒷자리 입력 필드
        textField_9 = new JPasswordField(); // 주민등록번호 뒷자리 필드를 JPasswordField로 변경
        textField_9.setBounds(139, 220, 143, 21);
        contentPane.add(textField_9);
        textField_9.setColumns(10);

        // 주민등록번호 보기 버튼
        btnShowSecret = new JButton("보기");
        btnShowSecret.setBounds(290, 220, 60, 21);
        btnShowSecret.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowSecret);

        // 학생 라디오 버튼
        rdbtnNewRadioButton = new JRadioButton("학생");
        rdbtnNewRadioButton.setBounds(205, 64, 69, 23);
        contentPane.add(rdbtnNewRadioButton);

        // 교사 라디오 버튼
        rdbtnNewRadioButton_1 = new JRadioButton("교사");
        rdbtnNewRadioButton_1.setBounds(278, 64, 119, 23);
        contentPane.add(rdbtnNewRadioButton_1);

        // 라디오 버튼 그룹 설정
        group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);

        // 이름 라벨
        JLabel lblNewLabel = new JLabel("이름");
        lblNewLabel.setBounds(12, 68, 52, 15);
        contentPane.add(lblNewLabel);

        // 아이디/이메일 라벨
        JLabel lblNewLabel_1 = new JLabel("아이디 혹은 이메일");
        lblNewLabel_1.setBounds(12, 99, 119, 15);
        contentPane.add(lblNewLabel_1);

        // 비밀번호 라벨
        JLabel lblNewLabel_2 = new JLabel("비밀번호");
        lblNewLabel_2.setBounds(12, 130, 52, 15);
        contentPane.add(lblNewLabel_2);

        // 비밀번호 재확인 라벨
        JLabel lblNewLabel_3 = new JLabel("비밀번호 재확인");
        lblNewLabel_3.setBounds(12, 159, 119, 18);
        contentPane.add(lblNewLabel_3);

        // 학교 라벨
        JLabel lblNewLabel_4 = new JLabel("학교");
        lblNewLabel_4.setBounds(12, 255, 52, 15);
        contentPane.add(lblNewLabel_4);

        // 학년 라벨
        lblNewLabel_5 = new JLabel("학년");
        lblNewLabel_5.setBounds(180, 287, 52, 15);
        contentPane.add(lblNewLabel_5);

        // 반 라벨
        lblNewLabel_6 = new JLabel("반");
        lblNewLabel_6.setBounds(266, 287, 52, 15);
        contentPane.add(lblNewLabel_6);

        // 가입하기 버튼
        btnNewButton = new JButton("가입하기");
        btnNewButton.setBackground(new Color(192, 236, 149));
        btnNewButton.setBounds(12, 330, 109, 28);
        contentPane.add(btnNewButton);

        // 생년월일 라벨
        lblNewLabel_7 = new JLabel("생년월일(6자리)");
        lblNewLabel_7.setBounds(12, 179, 119, 41);
        contentPane.add(lblNewLabel_7);

        // 생년월일 입력 필드
        textField_8 = new JTextField();
        textField_8.setBounds(139, 189, 143, 21);
        contentPane.add(textField_8);
        textField_8.setColumns(10);

        // 주민등록번호 뒷자리 라벨
        lblNewLabel_8 = new JLabel("주민등록번호 뒷자리");
        lblNewLabel_8.setBounds(12, 216, 136, 28);
        contentPane.add(lblNewLabel_8);

        // 비밀번호 안내 라벨
        lblNewLabel_9 = new JLabel("(7자리 이상의 영문자+숫자)");
        lblNewLabel_9.setForeground(Color.RED);
        lblNewLabel_9.setFont(new Font("굴림", Font.PLAIN, 9));
        lblNewLabel_9.setBounds(69, 131, 119, 15);
        contentPane.add(lblNewLabel_9);

        // 정보 입력 안내 라벨
        lblNewLabel_10 = new JLabel(">정보를 입력하세요");
        lblNewLabel_10.setForeground(new Color(117, 216, 103));
        lblNewLabel_10.setFont(new Font("굴림", Font.BOLD, 15));
        lblNewLabel_10.setBounds(12, 40, 176, 15);
        contentPane.add(lblNewLabel_10);

        addListeners();
    }

    private void addListeners() {
        // 비밀번호 보기 버튼 클릭 시
        btnShowPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_2.setEchoChar((char) 0); // 비밀번호 보이기
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField_2.setEchoChar('*'); // 비밀번호 감추기
            }
        });

        // 비밀번호 확인 보기 버튼 클릭 시
        btnShowConfirmPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_3.setEchoChar((char) 0); // 비밀번호 확인 보이기
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField_3.setEchoChar('*'); // 비밀번호 확인 감추기
            }
        });

        // 주민등록번호 보기 버튼 클릭 시
        btnShowSecret.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textField_9.setEchoChar((char) 0); // 주민등록번호 보이기
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField_9.setEchoChar('*'); // 주민등록번호 감추기
            }
        });

        // 가입하기 버튼 클릭 시
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) { // 입력 값 유효성 검사
                    String id = textField_1.getText();
                    String password = new String(textField_2.getPassword());
                    String name = textField.getText();
                    String school = textField_4.getText();
                    String grade = textField_5.getText();
                    String className = textField_6.getText();
                    String birthDate = textField_8.getText();
                    String secret = new String(textField_9.getPassword()); // 주민등록번호 값 가져오기
                    String userType = rdbtnNewRadioButton.isSelected() ? "학생" : "교사";

                    // 새로운 사용자 생성
                    User newUser = new User(id, password, name, school, grade, className, birthDate, secret, userType);
                    LoginForm.users.addUser(newUser); // 사용자 추가

                    // 사용자 정보 메시지
                    String message = String.format(
                            "회원가입 정보\n아이디: %s\n이름: %s\n학교: %s\n학년: %s\n반: %s\n생년월일: %s\n주민등록번호: %s\n신분: %s",
                            id, name, school, grade, className, birthDate, "******" + secret.substring(1), userType
                    );
                    JOptionPane.showMessageDialog(JoinForm.this, message);

                    setVisible(false); // 가입 폼 숨기기
                }
            }
        });
    }

    private boolean isInputValid() {
        String password = new String(textField_2.getPassword());
        String confirmPassword = new String(textField_3.getPassword());
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$"; // 비밀번호 유효성 검사용 정규표현식

        // 입력 필드가 모두 채워졌는지 확인
        if (textField.getText().isEmpty() || textField_1.getText().isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() ||
                textField_4.getText().isEmpty() || textField_5.getText().isEmpty() ||
                textField_6.getText().isEmpty() || textField_8.getText().isEmpty() ||
                textField_9.getPassword().length != 7) {
            JOptionPane.showMessageDialog(JoinForm.this, "모든 정보를 입력해주세요.");
            return false;
        }

        // 비밀번호 유효성 검사
        if (!password.matches(passwordPattern)) {
            JOptionPane.showMessageDialog(JoinForm.this, "비밀번호 7자리 이상의 영문자와 숫자를 조합하여 작성해주세요.");
            return false;
        }

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(JoinForm.this, "비밀번호가 일치하지 않습니다.");
            return false;
        }

        // 라디오 버튼이 선택되었는지 확인
        if (!rdbtnNewRadioButton.isSelected() && !rdbtnNewRadioButton_1.isSelected()) {
            JOptionPane.showMessageDialog(JoinForm.this, "신분을 선택해주세요.");
            return false;
        }

        return true; // 모든 조건을 만족하면 true 반환
    }
}
