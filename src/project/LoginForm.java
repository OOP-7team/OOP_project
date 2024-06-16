package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginForm extends JFrame {

    public static UserDataSet users; // UserDataSet을 static으로 선언하여 모든 인스턴스가 공유하도록 함

    private JLabel lblId; // 아이디 라벨
    private JLabel lblPw; // 비밀번호 라벨
    private JTextField tfId; // 아이디 입력 필드
    private JPasswordField tfPw; // 비밀번호 입력 필드
    private JButton btnLogin; // 로그인 버튼
    private JButton btnJoin; // 회원가입 버튼
    private JButton btnFindId; // 아이디 찾기 버튼
    private JButton btnFindPw; // 비밀번호 찾기 버튼
    private JRadioButton rbStudent; // 학생 라디오 버튼
    private JRadioButton rbTeacher; // 교사 라디오 버튼
    private ButtonGroup roleGroup; // 라디오 버튼 그룹
    private JButton btnShowPassword; // 비밀번호 보기 버튼

    public LoginForm() {
        users = new UserDataSet(); // UserDataSet 인스턴스 생성

        // 컴포넌트 초기화
        lblId = new JLabel("아이디");
        lblId.setFont(new Font("굴림", Font.PLAIN, 25));
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblPw = new JLabel("비밀번호");
        lblPw.setFont(new Font("굴림", Font.PLAIN, 25));
        lblPw.setHorizontalAlignment(SwingConstants.CENTER);

        tfId = new JTextField();
        tfId.setFont(new Font("굴림", Font.PLAIN, 25));
        tfPw = new JPasswordField();
        tfPw.setFont(new Font("굴림", Font.PLAIN, 25));

        btnLogin = new JButton("로그인");
        btnLogin.setFont(new Font("굴림", Font.PLAIN, 25));
        btnJoin = new JButton("회원가입");
        btnJoin.setFont(new Font("굴림", Font.PLAIN, 25));

        // 아이디/비밀번호 찾기 버튼 초기화
        btnFindId = new JButton("아이디 찾기");
        btnFindId.setFont(new Font("굴림", Font.PLAIN, 15));
        btnFindId.setForeground(new Color(0, 128, 0)); // 진한 초록색으로 설정
        btnFindId.setContentAreaFilled(false); // 배경색을 투명으로 설정
        btnFindId.setBorderPainted(false); // 테두리 색상을 투명으로 설정

        btnFindPw = new JButton("비밀번호 찾기");
        btnFindPw.setFont(new Font("굴림", Font.PLAIN, 15));
        btnFindPw.setForeground(new Color(0, 128, 0)); // 진한 초록색으로 설정
        btnFindPw.setContentAreaFilled(false); // 배경색을 투명으로 설정
        btnFindPw.setBorderPainted(false); // 테두리 색상을 투명으로 설정

        // 라디오 버튼 초기화
        rbStudent = new JRadioButton("학생");
        rbStudent.setFont(new Font("굴림", Font.PLAIN, 25));
        rbTeacher = new JRadioButton("교사");
        rbTeacher.setFont(new Font("굴림", Font.PLAIN, 25));
        roleGroup = new ButtonGroup();
        roleGroup.add(rbStudent);
        roleGroup.add(rbTeacher);

        btnShowPassword = new JButton("보기");
        btnShowPassword.setFont(new Font("굴림", Font.PLAIN, 25));
        btnShowPassword.setBounds(538, 150, 100, 40);
        btnShowPassword.setBackground(new Color(192, 236, 149));

        // 프레임 설정 및 컴포넌트 배치
        setBounds(100, 100, 685, 715);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        getContentPane().setLayout(null);

        // 라디오 버튼 위치 조정
        rbStudent.setBounds(225, 80, 100, 50); // 위치와 크기 조정
        getContentPane().add(rbStudent);

        rbTeacher.setBounds(365, 80, 100, 50); // 위치와 크기 조정
        getContentPane().add(rbTeacher);

        lblId.setBounds(44, 166, 125, 42);
        getContentPane().add(lblId);

        tfId.setBounds(176, 156, 400, 69);
        getContentPane().add(tfId);
        tfId.setColumns(10);

        lblPw.setBounds(44, 261, 122, 42);
        getContentPane().add(lblPw);

        tfPw.setBounds(176, 251, 299, 69);
        getContentPane().add(tfPw);
        tfPw.setColumns(10);

        btnLogin.setBounds(223, 401, 213, 55);
        btnLogin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnLogin);

        btnJoin.setBounds(223, 470, 213, 55);
        btnJoin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnJoin);

        // 아이디/비밀번호 찾기 버튼 배치
        btnFindId.setBounds(225, 320, 150, 40); // 크기 조정
        getContentPane().add(btnFindId);

        btnFindPw.setBounds(365, 320, 150, 40); // 크기 조정
        getContentPane().add(btnFindPw);
        
        btnShowPassword = new JButton("보기");
        btnShowPassword.setFont(new Font("굴림", Font.PLAIN, 25));
        btnShowPassword.setBounds(476, 251, 100, 69); // 보기 버튼 위치 조정
        btnShowPassword.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnShowPassword); // 보기 버튼을 프레임에 추가


        // 비밀번호 보기 버튼 클릭 시
        btnShowPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tfPw.setEchoChar((char) 0); // 비밀번호 보이기
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                tfPw.setEchoChar('*'); // 비밀번호 감추기
            }
        });

        // 로그인 버튼에 리스너 추가
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredId = tfId.getText();
                String enteredPw = new String(tfPw.getPassword());

                // 아이디와 비밀번호가 입력되었는지 확인
                if (enteredId.isEmpty() || enteredPw.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "아이디와 비밀번호를 모두 입력하세요.",
                            "로그인 오류",
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // 사용자 역할 선택 확인
                    if (!rbStudent.isSelected() && !rbTeacher.isSelected()) {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "학생 또는 교사 역할을 선택하세요.",
                                "로그인 오류",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }


                    // 입력된 아이디로 사용자 정보 가져오기
                    UserDataSet users = UserDataSet.getUserDataSetInstance();
                    User user = users.getUser(enteredId);

                    // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 오류 메시지 표시
                    if (user == null || !user.getPassword().equals(enteredPw)) {
                        JOptionPane.showMessageDialog(LoginForm.this,
                                "아이디 또는 비밀번호가 올바르지 않습니다.",
                                "로그인 오류",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String role = rbStudent.isSelected() ? "학생" : "교사";
                    if(!user.getUserType().equals(role)) {
                    	JOptionPane.showMessageDialog(LoginForm.this,
                                "계정 유형을 올바르게 선택해주세요",
                                "로그인 오류",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "로그인 성공! " + role + "으로 화면 이동 중...",
                            "로그인 성공",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    // 로그인 성공 후 로그인 창을 닫음
                    dispose();
                    
                    //MainPage의 버튼 상태 업데이트
                    MainPage.setLoginUser(user);
                    MainPage mainPage = MainPage.getInstance();
                    mainPage.updateLoginButtons();
                }
            });

            // 회원가입 버튼에 리스너 추가
            btnJoin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JoinForm joinForm = new JoinForm();
                    joinForm.setVisible(true); // JoinForm 화면 표시
                }
            });

            // 아이디 찾기 버튼에 리스너 추가
            btnFindId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FindID findIdFrame = new FindID();
                    findIdFrame.setVisible(true); // findID 화면 표시
                }
            });

            // 비밀번호 찾기 버튼에 리스너 추가
            btnFindPw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FindPW findPW = new FindPW();
                    findPW.setVisible(true); // FindPW 화면 표시

                }
            });

            // 프레임 설정
            setTitle("로그인");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                }
            });
            setLocationRelativeTo(null); // 화면 중앙에 표시
            setVisible(true);
        }

        // UserDataSet을 반환하는 메서드
        public static UserDataSet getUsers() {
            return users;
        }

        // 아이디 필드를 반환하는 메서드
        public String getTfId() {
            return tfId.getText();
        }
    }

