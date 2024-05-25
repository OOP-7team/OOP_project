package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {

    public static UserDataSet users; // UserDataSet을 static으로 선언하여 모든 인스턴스가 공유하도록 함

    private JLabel lblId; // 아이디 라벨
    private JLabel lblPw; // 비밀번호 라벨
    private JTextField tfId; // 아이디 입력 필드
    private JPasswordField tfPw; // 비밀번호 입력 필드
    private JButton btnLogin; // 로그인 버튼
    private JButton btnJoin; // 회원가입 버튼
    
    public LoginForm() {
        users = new UserDataSet(); // UserDataSet 인스턴스 생성

        init(); // 컴포넌트 초기화
        setDisplay(); // 컴포넌트를 프레임에 배치
        addListeners(); // 리스너 추가
        showFrame(); // 프레임을 보여줌
    }

    public void init() {
        // 컴포넌트 초기화
        lblId = new JLabel("아이디");
        lblPw = new JLabel("비밀번호");

        tfId = new JTextField();
        tfPw = new JPasswordField();

        btnLogin = new JButton("로그인");
        btnJoin = new JButton("회원가입");
    }

    private void setDisplay() {
        // 프레임 설정 및 컴포넌트 배치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(null);

        lblId.setBounds(132, 63, 52, 21);
        getContentPane().add(lblId);

        tfId.setBounds(196, 63, 106, 21);
        getContentPane().add(tfId);
        tfId.setColumns(10);

        lblPw.setBounds(132, 94, 52, 21);
        getContentPane().add(lblPw);

        tfPw.setBounds(196, 94, 106, 21);
        getContentPane().add(tfPw);
        tfPw.setColumns(10);

        btnLogin.setBounds(162, 125, 105, 28);
        btnLogin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnLogin);

        btnJoin.setBounds(162, 163, 106, 29);
        btnJoin.setBackground(new Color(192, 236, 149));
        getContentPane().add(btnJoin);
    }

    private void addListeners() {
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

                // 입력된 아이디로 사용자 정보 가져오기
                User user = users.getUser(enteredId);
                // 사용자 정보가 없거나 비밀번호가 일치하지 않으면 오류 메시지 표시
                if (user == null || !user.getPassword().equals(enteredPw)) {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "아이디 또는 비밀번호가 올바르지 않습니다.",
                            "로그인 오류",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

     
                JOptionPane.showMessageDialog(LoginForm.this,
                        "로그인 성공! 화면 이동 중...",
                        "로그인 성공",
                        JOptionPane.INFORMATION_MESSAGE);

                // 로그인 성공 후 로그인 창을 닫음
                dispose();
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
    }

    private void showFrame() {
        // 프레임 설정
        setTitle("로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static void main(String[] args) {
        // 애플리케이션 시작 지점
        SwingUtilities.invokeLater(() -> {
            new LoginForm();
        });
    }
}
