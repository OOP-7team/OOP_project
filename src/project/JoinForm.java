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
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JoinForm extends JFrame {

    private JPanel contentPane; // 메인 패널
    private JTextField textField; // 이름 입력 필드
    private JTextField textField_1; // 아이디/이메일 입력 필드
    private JPasswordField textField_2; // 비밀번호 입력 필드
    private JPasswordField textField_3; // 비밀번호 확인 입력 필드
    private JTextField textField_4; // 학교 입력 필드
    private JTextField textField_5; // 학년 입력 필드
    private JTextField textField_6; // 반 입력 필드
    private JTextField textField_8; // 생년월일 입력 필드
    private JPasswordField textField_9; // 주민등록번호 뒷자리 입력 필드
    private JLabel lblNewLabel_5; // 학년 라벨
    private JLabel lblNewLabel_6; // 반 라벨
    private JButton btnNewButton; // 가입하기 버튼
    private JButton btnShowPassword; // 비밀번호 보기 버튼
    private JButton btnShowConfirmPassword; // 비밀번호 확인 보기 버튼
    private JButton btnShowSecret; // 주민등록번호 보기 버튼
    private JLabel lblNewLabel_7; // 생년월일 라벨
    private JLabel lblNewLabel_8; // 주민등록번호 뒷자리 라벨
    private JLabel lblNewLabel_9; // 비밀번호 안내 라벨
    private JLabel lblNewLabel_10; // 정보 입력 안내 라벨
    private JRadioButton rdbtnNewRadioButton; // 학생 라디오 버튼
    private JRadioButton rdbtnNewRadioButton_1; // 교사 라디오 버튼
    private ButtonGroup group; // 라디오 버튼 그룹
    private JButton checkIdButton; // 아이디 중복 검사 버튼
    private JLabel idCheckLabel; // 아이디 중복 검사 결과 메시지

    private boolean idChecked;

    
    // 보안 질문 관련 변수들
    private JComboBox<String> questionComboBox;
    private JTextField answerField;
    private List<String> questionList;

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
        // 보안 질문 리스트 초기화
        questionList = new ArrayList<>();
        questionList.add("당신이 가장 존경하는 교수님 성함은?");
        questionList.add("당신의 어머니의 성함은?");
        questionList.add("당신의 아버지의 성함은?");
        questionList.add("당신이 가장 기억에 남는 책은?");
        questionList.add("당신이 가장 좋아하는 음식은?");
        

        // 프레임 기본 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        setBounds(100, 100, 700, 700); // 높이를 늘려서 새로운 요소들을 추가할 공간 확보
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        setLocationRelativeTo(null);

        // 이름 라벨과 텍스트 필드
        JLabel nameLabel = new JLabel("이름");
        nameLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        nameLabel.setBounds(56, 51, 100, 25);
        contentPane.add(nameLabel);

        textField = new JTextField();
        textField.setFont(new Font("굴림", Font.PLAIN, 20));
        textField.setBounds(118, 51, 180, 35);
        contentPane.add(textField);
        textField.setColumns(10);

        // 아이디 라벨과 텍스트 필드
        JLabel idLabel = new JLabel("아이디");
        idLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        idLabel.setBounds(56, 101, 100, 25);
        contentPane.add(idLabel);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_1.setBounds(156, 101, 230, 35);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        // 비밀번호 라벨과 텍스트 필드
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        passwordLabel.setBounds(56, 151, 250, 25);
        contentPane.add(passwordLabel);

        textField_2 = new JPasswordField();
        textField_2.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_2.setBounds(206, 151, 230, 35);
        contentPane.add(textField_2);

        // 비밀번호 보기 버튼
        btnShowPassword = new JButton("보기");
        btnShowPassword.setFont(new Font("굴림", Font.PLAIN, 20));
        btnShowPassword.setBounds(466, 151, 80, 35);
        btnShowPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowPassword);

        // 비밀번호 확인 라벨과 텍스트 필드
        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인");
        confirmPasswordLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        confirmPasswordLabel.setBounds(56, 201, 250, 25);
        contentPane.add(confirmPasswordLabel);

        textField_3 = new JPasswordField();
        textField_3.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_3.setBounds(278, 200, 230, 35);
        contentPane.add(textField_3);
        
        // 비밀번호 안내 라벨 추가
        lblNewLabel_9 = new JLabel("영문자와 숫자 7자리 이상");
        lblNewLabel_9.setFont(new Font("굴림", Font.PLAIN, 12));
        lblNewLabel_9.setForeground(Color.RED); // 글자색 빨강으로 설정
        lblNewLabel_9.setBounds(56, 221, 230, 25); // 위치 설정
        contentPane.add(lblNewLabel_9);

        // 비밀번호 확인 보기 버튼
        btnShowConfirmPassword = new JButton("보기");
        btnShowConfirmPassword.setFont(new Font("굴림", Font.PLAIN, 20));
        btnShowConfirmPassword.setBounds(520, 201, 80, 35);
        btnShowConfirmPassword.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowConfirmPassword);

        // 학교 라벨과 텍스트 필드
        JLabel schoolLabel = new JLabel("학교");
        schoolLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        schoolLabel.setBounds(56, 251, 100, 25);
        contentPane.add(schoolLabel);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_4.setBounds(156, 251, 230, 35);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        // 학년 라벨과 텍스트 필드
        lblNewLabel_5 = new JLabel("학년");
        lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(56, 301, 100, 25);
        contentPane.add(lblNewLabel_5);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_5.setBounds(156, 301, 100, 35);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        // 반 라벨과 텍스트 필드
        lblNewLabel_6 = new JLabel("반");
        lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(288, 301, 120, 25);
        contentPane.add(lblNewLabel_6);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_6.setBounds(328, 301, 100, 35);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        // 생년월일 라벨과 텍스트 필드
        lblNewLabel_7 = new JLabel("생년월일 6자리");
        lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(56, 351, 150, 25);
        contentPane.add(lblNewLabel_7);

        textField_8 = new JTextField();
        textField_8.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_8.setBounds(206, 351, 180, 35);
        contentPane.add(textField_8);
        textField_8.setColumns(10);

        // 주민등록번호 뒷자리 라벨과 텍스트 필드
        lblNewLabel_8 = new JLabel("주민등록번호 뒷자리");
        lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_8.setBounds(56, 401, 200, 25);
        contentPane.add(lblNewLabel_8);

        textField_9 = new JPasswordField();
        textField_9.setFont(new Font("굴림", Font.PLAIN, 20));
        textField_9.setBounds(288, 401, 150, 35);
        contentPane.add(textField_9);

        // 주민등록번호 보기 버튼
        btnShowSecret = new JButton("보기");
        btnShowSecret.setFont(new Font("굴림", Font.PLAIN, 20));
        btnShowSecret.setBounds(466, 401, 90, 35);
        btnShowSecret.setBackground(new Color(192, 236, 149));
        contentPane.add(btnShowSecret);

        // 보안 질문 라벨과 콤보 박스
        JLabel securityQuestionLabel = new JLabel("보안 질문");
        securityQuestionLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        securityQuestionLabel.setBounds(56, 451, 120, 25);
        contentPane.add(securityQuestionLabel);

        questionComboBox = new JComboBox<>(questionList.toArray(new String[0]));
        questionComboBox.setFont(new Font("굴림", Font.PLAIN, 20));
        questionComboBox.setBounds(206, 451, 380, 35);
        contentPane.add(questionComboBox);

        // 보안 질문 답변 라벨과 텍스트 필드
        JLabel securityAnswerLabel = new JLabel("보안 질문 답변");
        securityAnswerLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        securityAnswerLabel.setBounds(56, 501, 150, 25);
        contentPane.add(securityAnswerLabel);

        answerField = new JTextField();
        answerField.setFont(new Font("굴림", Font.PLAIN, 20));
        answerField.setBounds(256, 501, 330, 35);
        contentPane.add(answerField);
        answerField.setColumns(10);

        // 가입하기 버튼
        btnNewButton = new JButton("가입하기");
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 20));
        btnNewButton.setBounds(278, 551, 120, 50);
        btnNewButton.setBackground(new Color(192, 236, 149)); // 수정된 색상
        contentPane.add(btnNewButton);

        // 라디오 버튼 그룹 설정
        rdbtnNewRadioButton = new JRadioButton("학생");
        rdbtnNewRadioButton.setFont(new Font("굴림", Font.PLAIN, 20));
        rdbtnNewRadioButton.setBounds(328, 51, 100, 30);
        contentPane.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("교사");
        rdbtnNewRadioButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
        rdbtnNewRadioButton_1.setBounds(428, 51, 100, 30);
        contentPane.add(rdbtnNewRadioButton_1);

        group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);
        
        checkIdButton = new JButton("아이디중복검사");
        checkIdButton.setFont(new Font("굴림", Font.PLAIN, 15));
        checkIdButton.setBounds(398, 101, 150, 35);
        checkIdButton.setBackground(new Color(192, 236, 149)); 
        contentPane.add(checkIdButton);

        idCheckLabel = new JLabel("");
        idCheckLabel.setFont(new Font("굴림", Font.PLAIN, 15));
        idCheckLabel.setBounds(400, 135, 200, 25);
        contentPane.add(idCheckLabel);
        
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
        
     // 아이디 중복 검사 버튼 클릭 시
        checkIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = textField_1.getText().trim(); // 입력된 아이디 가져오기

                // 아이디가 비어있는 경우 메시지 출력
                if (userId.isEmpty()) {
                    idCheckLabel.setText("아이디를 입력해주세요.");
                    idCheckLabel.setForeground(Color.RED);
                    return;
                }

                // UserDataSet 싱글톤 인스턴스 가져오기
                UserDataSet users = UserDataSet.getUserDataSetInstance();
                idChecked = true; // 아이디 중복 검사 실행
                
                // 아이디 중복 검사
                if (users.isUserIdExists(userId)) {
                    idCheckLabel.setText("중복된 아이디입니다.");
                    idCheckLabel.setForeground(Color.RED);
                    idCheckLabel.setFont(new Font("굴림", Font.PLAIN, 11));     
                } else {
                    idCheckLabel.setText("사용할 수 있는 아이디입니다.");
                    idCheckLabel.setForeground(Color.BLUE);
                    idCheckLabel.setFont(new Font("굴림", Font.PLAIN, 11));
    
                }
            }
        });

        // 가입하기 버튼 클릭 시
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자가 아이디 중복 검사를 먼저 수행했는지 확인
                if (!isIdChecked()) {
                    JOptionPane.showMessageDialog(JoinForm.this, "아이디 중복검사를 실시해주세요.");
                    return;
                }

                // 여기서부터 유효성 검사 및 회원가입 로직을 수행
                UserDataSet users = UserDataSet.getUserDataSetInstance();
                String userId = textField_1.getText().trim();
                if (users.isUserIdExists(userId)) {
                    JOptionPane.showMessageDialog(JoinForm.this, "중복된 아이디입니다. 다른 아이디를 입력해주세요.");
                    return; // 중복된 아이디일 경우 회원가입 처리 중단
                }

                if (isInputValid()) {
                    // 유효성 검사를 통과하면 회원가입 처리
                    String password = new String(textField_2.getPassword());
                    String name = textField.getText();
                    String school = textField_4.getText();
                    String grade = textField_5.getText();
                    String className = textField_6.getText();
                    String birthDate = textField_8.getText();
                    String secret = new String(textField_9.getPassword()); // 주민등록번호 값 가져오기
                    String userType = rdbtnNewRadioButton.isSelected() ? "학생" : "교사";
                    String securityQuestion = (String) questionComboBox.getSelectedItem();
                    String securityAnswer = answerField.getText();
                    
                   //주민등록번호 유효성 검사
                    if (secret.length() != 7 || !isValidsecret(secret)) {
                        JOptionPane.showMessageDialog(JoinForm.this, "주민등록번호 뒷자리는 7자리의 숫자여야 합니다.");
                        return;
                    }
                    
                    // 생년월일 유효성 검사
                    if (birthDate.length() != 6 || !isValidBirthDate(birthDate)) {
                        JOptionPane.showMessageDialog(JoinForm.this, "생년월일은 6자리의 숫자여야 합니다.");
                        return;
                    }

                    // 새로운 사용자 생성
                    User newUser = new User(userId, password, name, school, grade, className, birthDate, secret, userType, securityQuestion, securityAnswer);

                    // 사용자 추가
                    users.addUser(newUser);

                    JOptionPane.showMessageDialog(JoinForm.this, name + "님 가입을 환영합니다!");
                    setVisible(false); // 가입 폼 숨기기
                }
            }
        });

    }
    
    private boolean isValidsecret(String secret) {
        // 숫자로만 구성되어 있는지 확인
        if (!secret.matches("\\d{7}")) {
            return false;
        }
        return true;
    }
    
    private boolean isValidBirthDate(String birthDate) {
        // 숫자로만 구성되어 있는지 확인
        if (!birthDate.matches("\\d{6}")) {
            return false;
        }
        return true;
    }
    

    
    private boolean isIdChecked() {
        return idChecked; // 현재 idChecked 값 반환
    }
    
    private boolean isInputValid() {
        String password = new String(textField_2.getPassword());
        String secret = new String(textField_2.getPassword());
        String confirmPassword = new String(textField_3.getPassword());
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$"; // 비밀번호 유효성 검사용 정규표현식

        // 입력 필드가 모두 채워졌는지 확인
        if (textField.getText().isEmpty() || textField_1.getText().isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() ||
                textField_4.getText().isEmpty() || textField_5.getText().isEmpty() ||
                textField_6.getText().isEmpty() || textField_8.getText().isEmpty() ||
                secret.isEmpty() || answerField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(JoinForm.this, "모든 필드를 채워주세요.");
            return false;
        }

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(JoinForm.this, "비밀번호가 일치하지 않습니다.");
            return false;
        }

        // 비밀번호가 유효한지 확인
        if (!password.matches(passwordPattern)) {
            JOptionPane.showMessageDialog(JoinForm.this, "비밀번호는 최소 7자 이상이어야 하며, 숫자와 문자를 포함해야 합니다.");
            return false;
        }
        return true; // 모든 유효성 검사를 통과하면 true 반환
    }
}
