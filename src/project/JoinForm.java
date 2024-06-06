package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import javax.swing.SwingConstants;

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
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        setBounds(100, 100, 685, 715);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 이름 입력 필드
        textField = new JTextField();
        textField.setFont(new Font("굴림", Font.PLAIN, 25));
        textField.setBounds(190, 62, 200, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        // 아이디/이메일 입력 필드
        textField_1 = new JTextField();
        textField_1.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_1.setBounds(278, 115, 250, 40);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        // 비밀번호 입력 필드
        textField_2 = new JPasswordField();
        textField_2.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_2.setBounds(278, 178, 250, 40);
        contentPane.add(textField_2);

        // 비밀번호 보기 버튼
        btnShowPassword = new JButton("보기");
        btnShowPassword.setFont(new Font("굴림", Font.PLAIN, 25));
        btnShowPassword.setBounds(538, 178, 100, 40);
        btnShowPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowPassword);

        // 비밀번호 확인 입력 필드
        textField_3 = new JPasswordField();
        textField_3.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_3.setBounds(278, 244, 250, 40);
        contentPane.add(textField_3);

        // 비밀번호 확인 보기 버튼
        btnShowConfirmPassword = new JButton("보기");
        btnShowConfirmPassword.setFont(new Font("굴림", Font.PLAIN, 25));
        btnShowConfirmPassword.setBounds(538, 244, 100, 40);
        btnShowConfirmPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowConfirmPassword);

        // 학교 입력 필드
        textField_4 = new JTextField();
        textField_4.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_4.setBounds(224, 440, 250, 40);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        // 학년 입력 필드
        textField_5 = new JTextField();
        textField_5.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_5.setBounds(224, 490, 120, 40);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        // 반 입력 필드
        textField_6 = new JTextField();
        textField_6.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_6.setBounds(447, 490, 120, 40);
        contentPane.add(textField_6);
        textField_6.setColumns(10);
        
     // 생년월일 입력 필드
        textField_8 = new JTextField();
        textField_8.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_8.setBounds(278, 306, 250, 40);
        contentPane.add(textField_8);
        textField_8.setColumns(10);

        // 주민등록번호 뒷자리 입력 필드
        textField_9 = new JPasswordField(); // 주민등록번호 뒷자리 필드를 JPasswordField로 변경
        textField_9.setFont(new Font("굴림", Font.PLAIN, 25));
        textField_9.setBounds(278, 367, 250, 40);
        contentPane.add(textField_9);
        textField_9.setColumns(10);
        
     // 주민등록번호 보기 버튼
        btnShowSecret = new JButton("보기");
        btnShowSecret.setFont(new Font("굴림", Font.PLAIN, 25));
        btnShowSecret.setBounds(538, 367, 100, 40);
        btnShowSecret.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowSecret);

        // 학생 라디오 버튼
        rdbtnNewRadioButton = new JRadioButton("학생");
        rdbtnNewRadioButton.setFont(new Font("굴림", Font.PLAIN, 25));
        rdbtnNewRadioButton.setBounds(407, 65, 88, 27);
        contentPane.add(rdbtnNewRadioButton);

        // 교사 라디오 버튼
        rdbtnNewRadioButton_1 = new JRadioButton("교사");
        rdbtnNewRadioButton_1.setFont(new Font("굴림", Font.PLAIN, 25));
        rdbtnNewRadioButton_1.setBounds(507, 68, 119, 23);
        contentPane.add(rdbtnNewRadioButton_1);

        // 라디오 버튼 그룹 설정
        group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);

        // 이름 라벨
        JLabel lblNewLabel = new JLabel("이름");
        lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel.setBounds(128, 65, 52, 30);
        contentPane.add(lblNewLabel);

        // 아이디/이메일 라벨
        JLabel lblNewLabel_1 = new JLabel("아이디 혹은 이메일");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_1.setBounds(26, 115, 242, 40);
        contentPane.add(lblNewLabel_1);

        // 비밀번호 라벨
        JLabel lblNewLabel_2 = new JLabel("비밀번호");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_2.setBounds(95, 176, 119, 30);
        contentPane.add(lblNewLabel_2);

        // 비밀번호 재확인 라벨
        JLabel lblNewLabel_3 = new JLabel("비밀번호 재확인");
        lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_3.setBounds(70, 248, 184, 30);
        contentPane.add(lblNewLabel_3);

        // 학교 라벨
        JLabel lblNewLabel_4 = new JLabel("학교명");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_4.setBounds(132, 450, 82, 30);
        contentPane.add(lblNewLabel_4);

        // 학년 라벨
        lblNewLabel_5 = new JLabel("학년");
        lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_5.setBounds(351, 496, 52, 30);
        contentPane.add(lblNewLabel_5);

        // 반 라벨
        lblNewLabel_6 = new JLabel("반");
        lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_6.setBounds(574, 496, 52, 30);
        contentPane.add(lblNewLabel_6);

        // 가입하기 버튼
        btnNewButton = new JButton("가입하기");
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 30));
        btnNewButton.setBackground(new Color(192, 236, 149));
        btnNewButton.setBounds(205, 545, 252, 53);
        contentPane.add(btnNewButton);

        // 생년월일 라벨
        lblNewLabel_7 = new JLabel("생년월일(6자리)");
        lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_7.setBounds(70, 310, 184, 30);
        contentPane.add(lblNewLabel_7);

        // 주민등록번호 뒷자리 라벨
        lblNewLabel_8 = new JLabel("주민등록번호 뒷자리");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_8.setBounds(28, 374, 265, 30);
        contentPane.add(lblNewLabel_8);

        // 비밀번호 안내 라벨
        lblNewLabel_9 = new JLabel("(7자리 이상의 영문자+숫자)");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_9.setForeground(Color.RED);
        lblNewLabel_9.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_9.setBounds(44, 205, 224, 15);
        contentPane.add(lblNewLabel_9);

        // 정보 입력 안내 라벨
        lblNewLabel_10 = new JLabel(">정보를 입력하세요");
        lblNewLabel_10.setForeground(new Color(117, 216, 103));
        lblNewLabel_10.setFont(new Font("굴림", Font.BOLD, 35));
        lblNewLabel_10.setBounds(18, 10, 385, 42);
        contentPane.add(lblNewLabel_10);
        
        JLabel lblNewLabel_4_1 = new JLabel("학급");
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setFont(new Font("굴림", Font.PLAIN, 25));
        lblNewLabel_4_1.setBounds(132, 500, 82, 30);
        contentPane.add(lblNewLabel_4_1);

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
                    
                    // UserDataSet 싱글톤 인스턴스 가져오기
                    UserDataSet users = UserDataSet.getUserDataSetInstance();
                    // 사용자 추가
                    users.addUser(newUser);

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
