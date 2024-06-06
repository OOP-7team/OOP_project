package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public LoginForm() {
        init(); // 컴포넌트 초기화
        setDisplay(); // 컴포넌트를 프레임에 배치
        addListeners(); // 리스너 추가
        showFrame(); // 프레임을 보여줌
    }

    public void init() {
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
    }

    private void setDisplay() {
        // 프레임 설정 및 컴포넌트 배치
        setBounds(100, 100, 685, 715);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        
        getContentPane().setLayout(null);

        lblId.setBounds(44, 166, 125, 42);
        getContentPane().add(lblId);

        tfId.setBounds(176, 156, 392, 69);
        getContentPane().add(tfId);
        tfId.setColumns(10);

        lblPw.setBounds(44, 261, 122, 42);
        getContentPane().add(lblPw);

        tfPw.setBounds(176, 251, 392, 69);
        getContentPane().add(tfPw);
        tfPw.setColumns(10);

        btnLogin.setBounds(223, 401, 213, 55);
        btnLogin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnLogin);

        btnJoin.setBounds(223, 470, 213, 55);
        btnJoin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnJoin);
    }

//    private void addListeners() {
//        // 로그인 버튼에 리스너 추가
//        btnLogin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String enteredId = tfId.getText();
//                String enteredPw = new String(tfPw.getPassword());
//
//                // 아이디와 비밀번호가 입력되었는지 확인
//                if (enteredId.isEmpty() || enteredPw.isEmpty()) {
//                    JOptionPane.showMessageDialog(LoginForm.this,
//                            "아이디와 비밀번호를 모두 입력하세요.",
//                            "로그인 오류",
//                            JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//
//                // 입력된 아이디로 사용자 정보 가져오기
//                User user = users.getUser(enteredId);
//                // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 오류 메시지 표시
//                if (user == null || !user.getPassword().equals(enteredPw)) {
//                    JOptionPane.showMessageDialog(LoginForm.this,
//                            "아이디 또는 비밀번호가 올바르지 않습니다.",
//                            "로그인 오류",
//                            JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                
//                // 로그인 성공시
//                MainPage.setLoginUser(user);
//                JOptionPane.showMessageDialog(LoginForm.this,
//                        "로그인 성공! 화면 이동 중...",
//                        "로그인 성공",
//                        JOptionPane.INFORMATION_MESSAGE);
//
//                // 로그인 성공 후 로그인 창을 닫음
//                dispose();
//                
//                // MainPage의 버튼 상태 업데이트
//                MainPage mainPage = MainPage.getInstance();
//                mainPage.updateLoginButtons();
//            }
//        });
//
//        // 회원가입 버튼에 리스너 추가
//        btnJoin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JoinForm joinForm = new JoinForm();
//                joinForm.setVisible(true); // JoinForm 화면 표시
//            }
//        });
//    }
    
    private void addListeners() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredId = tfId.getText();
                String enteredPw = new String(tfPw.getPassword());

                if (enteredId.isEmpty() || enteredPw.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "아이디와 비밀번호를 모두 입력하세요.",
                            "로그인 오류",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // UserDataSet 싱글톤 인스턴스 가져오기
                UserDataSet users = UserDataSet.getUserDataSetInstance();
                // 사용자 정보 가져오기
                User user = users.getUser(enteredId);

                if (user == null || !user.getPassword().equals(enteredPw)) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "아이디 또는 비밀번호가 올바르지 않습니다.",
                            "로그인 오류",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                MainPage.setLoginUser(user);
                JOptionPane.showMessageDialog(LoginForm.this,
                        "로그인 성공! 화면 이동 중...",
                        "로그인 성공",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
                MainPage mainPage = MainPage.getInstance();
                mainPage.updateLoginButtons();
            }
        });

        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JoinForm joinForm = new JoinForm();
                joinForm.setVisible(true);
            }
        });
    }

//    private void showFrame() {
//        // 프레임 설정
//        setTitle("로그인");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                super.windowClosed(e);
//            }
//        });
//        setLocationRelativeTo(null); // 화면 중앙에 표시
//        setVisible(true);
//    }
    
    private void showFrame() {
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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

    public static void main(String[] args) {
        // 애플리케이션 시작 지점
        SwingUtilities.invokeLater(() -> {
            new LoginForm();
        });
    }
}
