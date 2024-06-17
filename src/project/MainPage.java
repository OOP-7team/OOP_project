package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MainPage extends JFrame{
	
	private static MainPage mainPageInstance;
	
	JPanel sideBtnWrapper = new JPanel();
	
	private JButton userButton;
    private JButton logoutButton;
    
    // 공지사항 표시에 이용
    private List<Notice> notices = new ArrayList<>();
    private ClassNoticeManagement noticeManager;  // Notice 관리 클래스
    JPanel noticeContentWrapper, toDoContentWrapper;
    
    private JLabel imageLabel;
    private int imageIndex = 0;
    private final String[] images = {
        "/images/mainBanner/image1.png",
        "/images/mainBanner/image2.png",
        "/images/mainBanner/image3.png"
    };
    
    private JScrollPane scrollPane;
    private JPanel mainContentWrapper;
    
    private void setImage(int index) {
        ImageIcon imageIcon = new ImageIcon(MainPage.class.getResource(images[index]));
        Image image = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }
    
	static User loginUser = null;

	// 접근자 메서드 제공
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		MainPage.loginUser = loginUser;
	}

	
	public static void main(String[] args) {
		// 사용자 더미데이터-------------------------------------------------------
		// 새로운 사용자 생성
        User newUser1 = new User("cyaein", "choi0026", "최예인", "숙명초등학교", "2", 
        		"1", "2003.10.13", "", "학생", "", "");
        User newUser2 = new User("yyi", "yoonyi1004", "윤용익", "숙명초등학교", "2", 
        		"1", "2003.10.13", "", "교사", "", "");
        
        // UserDataSet 싱글톤 인스턴스 가져오기
        UserDataSet users = UserDataSet.getUserDataSetInstance();
        // 사용자 추가
        users.addUser(newUser1);
        users.addUser(newUser2);
        
        // 국어 퀴즈 더미데이터--------------------------------------------------------
        // 1. Question 선언
        // DateTimeFormatter를 이용하여 문자열을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Question k_q1 = new Question("빈칸에 알맞은 말을 써 보세요.\n 지금은 봄입니다. 꽃들이 _____ 피었습니다.", "예쁘게"); // 단답식
        Question k_q2 = new Question("다음 글의 맞춤법을 고쳐 쓰세요. \n 엄마는 우유과 빵을 사러 시장에 가셨어요.", "엄마는 우유와 빵을 사러 시장에 가셨어요."); // 단답식
        Question k_q3 = new Question("주어진 단어를 보고, 색깔을 써 보세요. \n 사과: _____", "빨강"); // 단답식
        // 문제들을 생성해서 퀴즈 객체에 추가
        List<Question> k_questions = new ArrayList<>();
        k_questions.add(k_q1);
        k_questions.add(k_q2);
        k_questions.add(k_q3);
        
        // 2. Quiz 선언
        // 주어진 문자열
        String dateString = "2024-06-18 12:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        String dateString2 = "2024-06-17 18:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateString2, formatter);
        
        Quiz k_quiz1 = new Quiz("[1] 1단원 글짓기", "국어", "2", "1", dateTime);
        Quiz k_quiz2 = new Quiz("[2] 1단원 글쓰기", "국어", "2", "1", dateTime2);
        // 퀴즈 객체에 질문들 추가
        k_quiz1.setQuestions(k_questions);
        k_quiz2.setQuestions(k_questions);
        
        // 3. QuizDataSet에 넣기
        // QuizDataSet 싱글톤 인스턴스 가져오기
        QuizDataSet k_quizzes = QuizDataSet.getQuizDataSetInstance();
        
        // 퀴즈 추가
        k_quizzes.addQuiz(k_quiz1);
        k_quizzes.addQuiz(k_quiz2);
        List<User> k_filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass("2", "1");
        // filteredUsers의 각 사용자에게 퀴즈 추가
        for (User user : k_filteredUsers) {
            // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
            user.getQuizzes().add(k_quiz1);
            user.getQuizzes().add(k_quiz2);
        }
        
        
        // 4. SubmittedAnswer 선언
        SubmittedAnswer k_s1_1 = new SubmittedAnswer(k_q1, "예쁘게", true);
        SubmittedAnswer k_s1_2 = new SubmittedAnswer(k_q2, "엄마는 우유와 빵을 사러 시장에 가셨어요.", true);
        SubmittedAnswer k_s1_3 = new SubmittedAnswer(k_q3, "노랑", false);
        
        SubmittedAnswer k_s2_1 = new SubmittedAnswer(k_q1, "예쁘게", true);
        SubmittedAnswer k_s2_2 = new SubmittedAnswer(k_q2, "엄마는 우유와 빵을 사러 시장에 가셨어요.", true);
        SubmittedAnswer k_s2_3 = new SubmittedAnswer(k_q3, "노랑", false);
        
        SubmittedAnswer k_s3_1 = new SubmittedAnswer(k_q1, "예쁘게", true);
        SubmittedAnswer k_s3_2 = new SubmittedAnswer(k_q2, "엄마는 우유와 빵을 사러 시장에 가셨어요.", true);
        SubmittedAnswer k_s3_3 = new SubmittedAnswer(k_q3, "빨강", true);
        
        List<SubmittedAnswer> k_sa1 = new ArrayList<>();
        k_sa1.add(k_s1_1);
        k_sa1.add(k_s1_2);
        k_sa1.add(k_s1_3);
        
        List<SubmittedAnswer> k_sa2 = new ArrayList<>();
        k_sa2.add(k_s2_1);
        k_sa2.add(k_s2_2);
        k_sa2.add(k_s2_3);
        
        List<SubmittedAnswer> k_sa3 = new ArrayList<>();
        k_sa3.add(k_s3_1);
        k_sa3.add(k_s3_2);
        k_sa3.add(k_s3_3);
        
        // 5. StudentQuiz 선언
        StudentQuiz k_st1 = new StudentQuiz(k_quiz1, k_sa1, 2);
        StudentQuiz k_st2 = new StudentQuiz(k_quiz1, k_sa2, 2);
        StudentQuiz k_st3 = new StudentQuiz(k_quiz1, k_sa3, 3);
        StudentQuizDataSet k_studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
        k_studentQuizzes.addStudentQuiz(k_st1);
        k_studentQuizzes.addStudentQuiz(k_st2);
        k_studentQuizzes.addStudentQuiz(k_st3);
        
        // 6. loginUser.studentQuizzes에 추가
        newUser1.addstudentQuiz(k_st1);
        newUser1.addstudentQuiz(k_st2);
        newUser1.addstudentQuiz(k_st3);
        
        // 수학 퀴즈 더미데이터--------------------------------------------------------
        // 1. Question 선언
        // DateTimeFormatter를 이용하여 문자열을 LocalDateTime으로 변환
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        Question m_q1 = new Question("1+2의 값은?", "3"); // 단답식
        Question m_q2 = new Question("4+7의 값은?", "11"); // 단답식
        Question m_q3 = new Question("3+5의 값은?", "8"); // 단답식
        // 문제들을 생성해서 퀴즈 객체에 추가
        List<Question> m_questions = new ArrayList<>();
        m_questions.add(m_q1);
        m_questions.add(m_q2);
        m_questions.add(m_q3);
        
        // 2. Quiz 선언
        // 주어진 문자열
        Quiz m_quiz1 = new Quiz("1단원 복습", "수학", "2", "1", dateTime);
        Quiz m_quiz2 = new Quiz("2단원 복습", "수학", "2", "1", dateTime2);
        // 퀴즈 객체에 질문들 추가
        m_quiz1.setQuestions(m_questions);
        m_quiz2.setQuestions(m_questions);
        
        // 3. QuizDataSet에 넣기
        // QuizDataSet 싱글톤 인스턴스 가져오기
        QuizDataSet m_quizzes = QuizDataSet.getQuizDataSetInstance();
        
        // 퀴즈 추가
        m_quizzes.addQuiz(m_quiz1);
        m_quizzes.addQuiz(m_quiz2);
        List<User> m_filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass("2", "1");
        // filteredUsers의 각 사용자에게 퀴즈 추가
        for (User user : m_filteredUsers) {
            // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
            user.getQuizzes().add(m_quiz1);
            user.getQuizzes().add(m_quiz2);
        }
        
        
        // 4. SubmittedAnswer 선언
        SubmittedAnswer m_s1_1 = new SubmittedAnswer(m_q1, "3", true);
        SubmittedAnswer m_s1_2 = new SubmittedAnswer(m_q2, "3", false);
        SubmittedAnswer m_s1_3 = new SubmittedAnswer(m_q3, "3", false);
        
        SubmittedAnswer m_s2_1 = new SubmittedAnswer(m_q1, "3", true);
        SubmittedAnswer m_s2_2 = new SubmittedAnswer(m_q2, "11", true);
        SubmittedAnswer m_s2_3 = new SubmittedAnswer(m_q3, "10", false);
        
        SubmittedAnswer m_s3_1 = new SubmittedAnswer(m_q1, "3", true);
        SubmittedAnswer m_s3_2 = new SubmittedAnswer(m_q2, "11", true);
        SubmittedAnswer m_s3_3 = new SubmittedAnswer(m_q3, "8", true);
        
        List<SubmittedAnswer> m_sa1 = new ArrayList<>();
        m_sa1.add(m_s1_1);
        m_sa1.add(m_s1_2);
        m_sa1.add(m_s1_3);
        
        List<SubmittedAnswer> m_sa2 = new ArrayList<>();
        m_sa2.add(m_s2_1);
        m_sa2.add(m_s2_2);
        m_sa2.add(m_s2_3);
        
        List<SubmittedAnswer> m_sa3 = new ArrayList<>();
        m_sa3.add(m_s3_1);
        m_sa3.add(m_s3_2);
        m_sa3.add(m_s3_3);
        
        // 5. StudentQuiz 선언
        StudentQuiz m_st1 = new StudentQuiz(m_quiz1, m_sa1, 1);
        StudentQuiz m_st2 = new StudentQuiz(m_quiz1, m_sa2, 2);
        StudentQuiz m_st3 = new StudentQuiz(m_quiz1, m_sa3, 3);
        StudentQuizDataSet m_studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
        m_studentQuizzes.addStudentQuiz(m_st1);
        m_studentQuizzes.addStudentQuiz(m_st2);
        m_studentQuizzes.addStudentQuiz(m_st3);
        
        // 6. loginUser.studentQuizzes에 추가
        newUser1.addstudentQuiz(m_st1);
        newUser1.addstudentQuiz(m_st2);
        newUser1.addstudentQuiz(m_st3);
        
        // 영어 퀴즈 더미데이터--------------------------------------------------------
        // 1. Question 선언
        // DateTimeFormatter를 이용하여 문자열을 LocalDateTime으로 변환
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Question e_q1 = new Question("다음 문장에서 빈 칸에 알맞은 단어를 고르세요. \n I have __ apple.", "an"); // 단답식
        Question e_q2 = new Question("다음 문장에서 빈 칸에 알맞은 단어를 고르세요. \n She ___ a teacher.", "is"); // 단답식
        Question e_q3 = new Question("다음 문장에서 올바른 단어를 고르세요. \n I ___ a dog. (have / has)", "have"); // 단답식
        // 문제들을 생성해서 퀴즈 객체에 추가
        List<Question> e_questions = new ArrayList<>();
        e_questions.add(e_q1);
        e_questions.add(e_q2);
        e_questions.add(e_q3);
        
        // 2. Quiz 선언
        // 주어진 문자열
        Quiz e_quiz1 = new Quiz("1. Fruits !", "영어", "2", "1", dateTime);
        Quiz e_quiz2 = new Quiz("2. Summer Vacation !", "영어", "2", "1", dateTime2);
        // 퀴즈 객체에 질문들 추가
        e_quiz1.setQuestions(e_questions);
        e_quiz2.setQuestions(e_questions);
        
        // 3. QuizDataSet에 넣기
        // QuizDataSet 싱글톤 인스턴스 가져오기
        QuizDataSet e_quizzes = QuizDataSet.getQuizDataSetInstance();
        
        // 퀴즈 추가
        e_quizzes.addQuiz(e_quiz1);
        e_quizzes.addQuiz(e_quiz2);
        List<User> e_filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass("2", "1");
        // filteredUsers의 각 사용자에게 퀴즈 추가
        for (User user : e_filteredUsers) {
            // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
            user.getQuizzes().add(e_quiz1);
            user.getQuizzes().add(e_quiz2);
        }
        
        
        // 4. SubmittedAnswer 선언
        SubmittedAnswer e_s1_1 = new SubmittedAnswer(e_q1, "a", false);
        SubmittedAnswer e_s1_2 = new SubmittedAnswer(e_q2, "are", false);
        SubmittedAnswer e_s1_3 = new SubmittedAnswer(e_q3, "has", false);
        
        SubmittedAnswer e_s2_1 = new SubmittedAnswer(e_q1, "an", true);
        SubmittedAnswer e_s2_2 = new SubmittedAnswer(e_q2, "are", false);
        SubmittedAnswer e_s2_3 = new SubmittedAnswer(e_q3, "has", false);
        
        SubmittedAnswer e_s3_1 = new SubmittedAnswer(e_q1, "an", true);
        SubmittedAnswer e_s3_2 = new SubmittedAnswer(e_q2, "are", false);
        SubmittedAnswer e_s3_3 = new SubmittedAnswer(e_q3, "has", false);
        
        List<SubmittedAnswer> e_sa1 = new ArrayList<>();
        e_sa1.add(e_s1_1);
        e_sa1.add(e_s1_2);
        e_sa1.add(e_s1_3);
        
        List<SubmittedAnswer> e_sa2 = new ArrayList<>();
        e_sa2.add(e_s2_1);
        e_sa2.add(e_s2_2);
        e_sa2.add(e_s2_3);
        
        List<SubmittedAnswer> e_sa3 = new ArrayList<>();
        e_sa3.add(e_s3_1);
        e_sa3.add(e_s3_2);
        e_sa3.add(e_s3_3);
        
        // 5. StudentQuiz 선언
        StudentQuiz e_st1 = new StudentQuiz(e_quiz1, e_sa1, 0);
        StudentQuiz e_st2 = new StudentQuiz(e_quiz1, e_sa2, 1);
        StudentQuiz e_st3 = new StudentQuiz(e_quiz1, e_sa3, 1);
        StudentQuizDataSet e_studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
        e_studentQuizzes.addStudentQuiz(e_st1);
        e_studentQuizzes.addStudentQuiz(e_st2);
        e_studentQuizzes.addStudentQuiz(e_st3);
        
        // 6. loginUser.studentQuizzes에 추가
        newUser1.addstudentQuiz(e_st1);
        newUser1.addstudentQuiz(e_st2);
        newUser1.addstudentQuiz(e_st3);
        
        // 사회 퀴즈 더미데이터--------------------------------------------------------
        // 1. Question 선언
        // DateTimeFormatter를 이용하여 문자열을 LocalDateTime으로 변환
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Question s_q1 = new Question("우리나라의 수도는 어디인가요?", "서울"); // 단답식
        Question s_q2 = new Question("어떤 사람들이 우리를 보호하고 법과 질서를 지키도록 도와줄까요?", "경찰"); // 단답식
        Question s_q3 = new Question("가을에 나뭇잎이 떨어지는 현상은 무엇인가요?", "낙엽"); // 단답식
        // 문제들을 생성해서 퀴즈 객체에 추가
        List<Question> s_questions = new ArrayList<>();
        s_questions.add(s_q1);
        s_questions.add(s_q2);
        s_questions.add(s_q3);
        
        // 2. Quiz 선언
        // 주어진 문자열
        Quiz s_quiz1 = new Quiz("1. 우리나라와 계절", "사회", "2", "1", dateTime);
        Quiz s_quiz2 = new Quiz("1. 우리나라와 계절", "사회", "2", "1", dateTime2);
        // 퀴즈 객체에 질문들 추가
        s_quiz1.setQuestions(s_questions);
        s_quiz2.setQuestions(s_questions);
        
        // 3. QuizDataSet에 넣기
        // QuizDataSet 싱글톤 인스턴스 가져오기
        QuizDataSet s_quizzes = QuizDataSet.getQuizDataSetInstance();
        
        // 퀴즈 추가
        s_quizzes.addQuiz(s_quiz1);
        s_quizzes.addQuiz(s_quiz2);
        List<User> s_filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass("2", "1");
        // filteredUsers의 각 사용자에게 퀴즈 추가
        for (User user : s_filteredUsers) {
            // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
            user.getQuizzes().add(s_quiz1);
            user.getQuizzes().add(s_quiz2);
        }
        
        
        // 4. SubmittedAnswer 선언
        SubmittedAnswer s_s1_1 = new SubmittedAnswer(s_q1, "서울", true);
        SubmittedAnswer s_s1_2 = new SubmittedAnswer(s_q2, "경찰", true);
        SubmittedAnswer s_s1_3 = new SubmittedAnswer(s_q3, "낙엽", true);
        
        SubmittedAnswer s_s2_1 = new SubmittedAnswer(s_q1, "서울", true);
        SubmittedAnswer s_s2_2 = new SubmittedAnswer(s_q2, "경찰", true);
        SubmittedAnswer s_s2_3 = new SubmittedAnswer(s_q3, "낙엽", true);
        
        SubmittedAnswer s_s3_1 = new SubmittedAnswer(s_q1, "서울", true);
        SubmittedAnswer s_s3_2 = new SubmittedAnswer(s_q2, "경찰", true);
        SubmittedAnswer s_s3_3 = new SubmittedAnswer(s_q3, "낙엽", true);
        
        List<SubmittedAnswer> s_sa1 = new ArrayList<>();
        s_sa1.add(s_s1_1);
        s_sa1.add(s_s1_2);
        s_sa1.add(s_s1_3);
        
        List<SubmittedAnswer> s_sa2 = new ArrayList<>();
        s_sa2.add(s_s2_1);
        s_sa2.add(s_s2_2);
        s_sa2.add(s_s2_3);
        
        List<SubmittedAnswer> s_sa3 = new ArrayList<>();
        s_sa3.add(s_s3_1);
        s_sa3.add(s_s3_2);
        s_sa3.add(s_s3_3);
        
        // 5. StudentQuiz 선언
        StudentQuiz s_st1 = new StudentQuiz(s_quiz1, s_sa1, 3);
        StudentQuiz s_st2 = new StudentQuiz(s_quiz1, s_sa2, 3);
        StudentQuiz s_st3 = new StudentQuiz(s_quiz1, s_sa3, 3);
        StudentQuizDataSet s_studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
        s_studentQuizzes.addStudentQuiz(s_st1);
        s_studentQuizzes.addStudentQuiz(s_st2);
        s_studentQuizzes.addStudentQuiz(s_st3);
        
        // 6. loginUser.studentQuizzes에 추가
        newUser1.addstudentQuiz(s_st1);
        newUser1.addstudentQuiz(s_st2);
        newUser1.addstudentQuiz(s_st3);
        
        // 과학 퀴즈 더미데이터--------------------------------------------------------
        // 1. Question 선언
        // DateTimeFormatter를 이용하여 문자열을 LocalDateTime으로 변환
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Question c_q1 = new Question("우리가 손으로 만질 수 있는 물질은 무엇인가요?", "고체"); // 단답식
        Question c_q2 = new Question("태양이 무엇을 제공해 줄까요?", "빛과 열"); // 단답식
        Question c_q3 = new Question("물체가 던진 후 떨어지는 이유는 무엇인가요?", "중력"); // 단답식
        // 문제들을 생성해서 퀴즈 객체에 추가
        List<Question> c_questions = new ArrayList<>();
        c_questions.add(c_q1);
        c_questions.add(c_q2);
        c_questions.add(c_q3);
        
        // 2. Quiz 선언
        // 주어진 문자열
        Quiz c_quiz1 = new Quiz("1. 우리 주변의 다양한 물체와 에너지", "과학", "2", "1", dateTime);
        Quiz c_quiz2 = new Quiz("1. 우리 주변의 다양한 물체와 에너지", "과학", "2", "1", dateTime2);
        // 퀴즈 객체에 질문들 추가
        c_quiz1.setQuestions(c_questions);
        c_quiz2.setQuestions(c_questions);
        
        // 3. QuizDataSet에 넣기
        // QuizDataSet 싱글톤 인스턴스 가져오기
        QuizDataSet c_quizzes = QuizDataSet.getQuizDataSetInstance();
        
        // 퀴즈 추가
        c_quizzes.addQuiz(c_quiz1);
        c_quizzes.addQuiz(c_quiz2);
        List<User> c_filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass("2", "1");
        // filteredUsers의 각 사용자에게 퀴즈 추가
        for (User user : c_filteredUsers) {
            // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
            user.getQuizzes().add(c_quiz1);
            user.getQuizzes().add(c_quiz2);
        }
        
        
        // 4. SubmittedAnswer 선언
        SubmittedAnswer c_s1_1 = new SubmittedAnswer(c_q1, "고체", true);
        SubmittedAnswer c_s1_2 = new SubmittedAnswer(c_q2, "빛과 열", true);
        SubmittedAnswer c_s1_3 = new SubmittedAnswer(c_q3, "중력", true);
        
        SubmittedAnswer c_s2_1 = new SubmittedAnswer(c_q1, "고체", true);
        SubmittedAnswer c_s2_2 = new SubmittedAnswer(c_q2, "빛과 열", true);
        SubmittedAnswer c_s2_3 = new SubmittedAnswer(c_q3, "체력", false);
        
        SubmittedAnswer c_s3_1 = new SubmittedAnswer(c_q1, "고체", true);
        SubmittedAnswer c_s3_2 = new SubmittedAnswer(c_q2, "빛과 열", true);
        SubmittedAnswer c_s3_3 = new SubmittedAnswer(c_q3, "체력", false);
        
        List<SubmittedAnswer> c_sa1 = new ArrayList<>();
        c_sa1.add(c_s1_1);
        c_sa1.add(c_s1_2);
        c_sa1.add(c_s1_3);
        
        List<SubmittedAnswer> c_sa2 = new ArrayList<>();
        c_sa2.add(c_s2_1);
        c_sa2.add(c_s2_2);
        c_sa2.add(c_s2_3);
        
        List<SubmittedAnswer> c_sa3 = new ArrayList<>();
        c_sa3.add(c_s3_1);
        c_sa3.add(c_s3_2);
        c_sa3.add(c_s3_3);
        
        // 5. StudentQuiz 선언
        StudentQuiz c_st1 = new StudentQuiz(c_quiz1, c_sa1, 3);
        StudentQuiz c_st2 = new StudentQuiz(c_quiz1, c_sa2, 2);
        StudentQuiz c_st3 = new StudentQuiz(c_quiz1, c_sa3, 2);
        StudentQuizDataSet c_studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
        c_studentQuizzes.addStudentQuiz(c_st1);
        c_studentQuizzes.addStudentQuiz(c_st2);
        c_studentQuizzes.addStudentQuiz(c_st3);
        
        // 6. loginUser.studentQuizzes에 추가
        newUser1.addstudentQuiz(c_st1);
        newUser1.addstudentQuiz(c_st2);
        newUser1.addstudentQuiz(c_st3);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPage window = MainPage.getInstance();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	// 싱글톤 패턴을 위한 getInstance() 메서드
    public static MainPage getInstance() {
        if (mainPageInstance == null) {
        	mainPageInstance = new MainPage();
        }
        return mainPageInstance;
    }

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}
	
	public static void close(Window window) {
		WindowEvent closeWindow = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1300, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
		setVisible(true);
		
		// 창이 정 가운데에서 뜨도록 해줌
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int leftTopX = centerPoint.x - getWidth()/2;
		int leftTopY = centerPoint.y - getHeight()/2;
		setLocation(leftTopX, leftTopY);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// 상단 gnb 부분
		JPanel gnb = new JPanel();
		gnb.setBackground(Color.WHITE);
		getContentPane().add(gnb, BorderLayout.NORTH);
		
		// gnb의 Layout 설정(gridBagLayout을 선택함)
		GridBagLayout gbl_gnb = new GridBagLayout();
		gbl_gnb.columnWidths = new int[]{360, 360, 360, 360, 0};
		gbl_gnb.rowHeights = new int[]{100, 100, 0};
		gbl_gnb.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_gnb.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gnb.setLayout(gbl_gnb);
		
		// gnb에 들어가는 로고 이미지 크기 조절(크기 조절한 아이콘을 아래 goToMain 라벨에 넣을 예정)
		ImageIcon icon = new ImageIcon(MyPage.class.getResource("/images/logo.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		// Main 로고 부분 JPanel로 구현		
		JPanel mainBtnWrapper = new JPanel();
		mainBtnWrapper.setBackground(Color.WHITE);
		mainBtnWrapper.setLayout(null);
		GridBagConstraints gbc_mainBtnWrapper = new GridBagConstraints();
		gbc_mainBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_mainBtnWrapper.insets = new Insets(0, 0, 5, 0);
		gbc_mainBtnWrapper.gridx = 0;
		gbc_mainBtnWrapper.gridy = 0;
		gnb.add(mainBtnWrapper, gbc_mainBtnWrapper);
		
		JButton mainBtn = new JButton("");
		mainBtn.setBackground(Color.WHITE);
		mainBtn.setIcon(updateIcon);
		mainBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(loginUser == null) {
            		return;
            	}
                showMainPage();
            }
        });
		mainBtn.setBounds(0, 0, 100, 100);
		mainBtnWrapper.add(mainBtn);
		
		JLabel mainName = new JLabel("북적북적");
		mainName.setForeground(new Color(153, 204, 0));
		mainName.setFont(new Font("HY헤드라인M", Font.PLAIN, 40));
		mainName.setBounds(110, 25, 196, 60);
		mainBtnWrapper.add(mainName);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JLabel temp1 = new JLabel("");
		GridBagConstraints gbc_temp1 = new GridBagConstraints();
		gbc_temp1.fill = GridBagConstraints.BOTH;
		gbc_temp1.insets = new Insets(0, 0, 5, 5);
		gbc_temp1.gridx = 1;
		gbc_temp1.gridy = 0;
		gnb.add(temp1, gbc_temp1);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JPanel temp2 = new JPanel();
		temp2.setBackground(Color.WHITE);
		temp2.setLayout(null);
		GridBagConstraints gbc_temp2 = new GridBagConstraints();
		gbc_temp2.fill = GridBagConstraints.BOTH;
		gbc_temp2.insets = new Insets(0, 0, 5, 0);
		gbc_temp2.gridx = 2;
		gbc_temp2.gridy = 0;
		gnb.add(temp2, gbc_temp2);
		
		
		// side button : goToQuizRegistration
		JButton goToQuizRegistration = new JButton("");
		goToQuizRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuizRegistration, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(loginUser.getUserType().equals("학생")) {
					JOptionPane.showMessageDialog(goToQuizRegistration, "교사만 이용 가능합니다.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				QuizRegistration quizRegistration = new QuizRegistration();
				quizRegistration.setVisible(true);
			}
		});
		goToQuizRegistration.setIcon(new ImageIcon(MyPage.class.getResource("/images/book.png")));
		goToQuizRegistration.setForeground(Color.WHITE);
		goToQuizRegistration.setBackground(Color.WHITE);
		goToQuizRegistration.setBounds(100, 0, 100, 100);
		icon = new ImageIcon(MyPage.class.getResource("/images/book.png"));
		img = icon.getImage();
		updateImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		updateIcon = new ImageIcon(updateImg);
		goToQuizRegistration.setIcon(updateIcon);
		temp2.add(goToQuizRegistration);
		
		
		// gnb의 side에 들어가는 로그인/마이페이지&로그아웃 버튼이 들어가는 JPanel
		
		sideBtnWrapper.setBackground(Color.WHITE);
		GridBagConstraints gbc_sideBtnWrapper = new GridBagConstraints();
		gbc_sideBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_sideBtnWrapper.insets = new Insets(0, 0, 5, 0);
		gbc_sideBtnWrapper.gridx = 3;
		gbc_sideBtnWrapper.gridy = 0;
		gnb.add(sideBtnWrapper, gbc_sideBtnWrapper);
		sideBtnWrapper.setLayout(null);

		userButton = new JButton();
        userButton.setBounds(0, 0, 180, 100);
        sideBtnWrapper.add(userButton);

        logoutButton = new JButton();
        logoutButton.setBounds(180, 0, 180, 100);
        sideBtnWrapper.add(logoutButton);
		
        updateLoginButtons();
		
		// gnb 하단에 들어가는 버튼들이 들어간 JPanel
		JPanel bottomBtnWrapper = new JPanel();
		bottomBtnWrapper.setBackground(Color.WHITE);
		bottomBtnWrapper.setLayout(null);
		GridBagConstraints gbc_bottomBtnWrapper = new GridBagConstraints();
		gbc_bottomBtnWrapper.gridwidth = 4;
		gbc_bottomBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_bottomBtnWrapper.insets = new Insets(0, 0, 0, 5);
		gbc_bottomBtnWrapper.gridx = 0;
		gbc_bottomBtnWrapper.gridy = 1;
		gnb.add(bottomBtnWrapper, gbc_bottomBtnWrapper);
		
		// 첫 번째 main button : goToQuiz
		JButton goToQuiz = new JButton("오늘의 퀴즈");
		goToQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuiz, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				TodayQuiz todayQuiz = new TodayQuiz();
				todayQuiz.setVisible(true);
			}
		});
		goToQuiz.setBackground(new Color(192, 236, 149));
		goToQuiz.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuiz.setBounds(10, 23, 221, 50);
		bottomBtnWrapper.add(goToQuiz);
		
		
		// 두 번째 main button : goToLearningCheck
		JButton goToLearningCheck = new JButton("학습 점검");
		goToLearningCheck.setBackground(new Color(192, 236, 149));
		
		goToLearningCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToLearningCheck, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				LearningCheck learningCheck = new LearningCheck();
				learningCheck.setVisible(true);
			}
		});
		
		goToLearningCheck.setFont(new Font("굴림", Font.PLAIN, 30));
		goToLearningCheck.setBounds(258, 23, 221, 50);
		bottomBtnWrapper.add(goToLearningCheck);
		
		// 세 번째 main button : goToBookMenu
		JButton goToBookMenu = new JButton("교과서 목록");
		goToBookMenu.setBackground(new Color(192, 236, 149));
		
		goToBookMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToBookMenu, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				BookMenu bookMenu = new BookMenu();
				bookMenu.setVisible(true);
			}
		});
		
		
		goToBookMenu.setFont(new Font("굴림", Font.PLAIN, 30));
		goToBookMenu.setBounds(512, 23, 221, 50);
		bottomBtnWrapper.add(goToBookMenu);
		
		// 네 번째 main button : goToQuizCaculator
		JButton goToQuizCalculator = new JButton("퀴즈 대시보드");
		goToQuizCalculator.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuizCalculator.setBackground(new Color(192, 236, 149));
		goToQuizCalculator.setBounds(766, 23, 262, 50);
		
		goToQuizCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuizCalculator, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				QuizDashBoard quizCalculator = new QuizDashBoard();
				quizCalculator.setVisible(true);
			}
		});
		
		bottomBtnWrapper.add(goToQuizCalculator);
		
		//----------------------------------------------------------------------------------------------상단 gnb bar
		
		mainContentWrapper = new JPanel();
		mainContentWrapper.setBackground(Color.WHITE);
		mainContentWrapper.setLayout(null);
		mainContentWrapper.setPreferredSize(new Dimension(1200, 1000));
		
		scrollPane = new JScrollPane(mainContentWrapper);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		// Add image label
        imageLabel = new JLabel();
        imageLabel.setBounds(150, 50, 975, 350);
        setImage(imageIndex);
        mainContentWrapper.add(imageLabel);

        // Add left button
        JButton leftButton = new JButton("<");
        leftButton.setBounds(50, 200, 50, 50);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageIndex = (imageIndex - 1 + images.length) % images.length;
                setImage(imageIndex);
            }
        });
        mainContentWrapper.add(leftButton);

        // Add right button
        JButton rightButton = new JButton(">");
        rightButton.setBounds(1175, 200, 50, 50);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageIndex = (imageIndex + 1) % images.length;
                setImage(imageIndex);
            }
        });
        mainContentWrapper.add(rightButton);

        
        JPanel noticeWrapper = new JPanel();
        noticeWrapper.setBorder(new LineBorder(new Color(192, 236, 149), 2));
        noticeWrapper.setBackground(Color.WHITE);
        noticeWrapper.setBounds(50, 453, 602, 500);
        mainContentWrapper.add(noticeWrapper);
        noticeWrapper.setLayout(null);
        
        JLabel noticeContentTitle = new JLabel("학급 공지사항");
        noticeContentTitle.setFont(new Font("굴림", Font.BOLD, 35));
        noticeContentTitle.setBounds(47, 46, 360, 61);
        noticeWrapper.add(noticeContentTitle);
        
        noticeContentWrapper = new JPanel();
        noticeContentWrapper.setBounds(47, 127, 510, 350);
        noticeWrapper.add(noticeContentWrapper);
        
        if(loginUser == null) {
           JLabel noticeLoginMessage = new JLabel("");
            noticeLoginMessage.setFont(new Font("굴림", Font.PLAIN, 25));
            noticeContentWrapper.add(noticeLoginMessage);
        }else {
            
        }
        
        
        JPanel toDoWrapper = new JPanel();
        toDoWrapper.setBackground(new Color(192, 236, 149));
        toDoWrapper.setBounds(674, 453, 551, 500);
        mainContentWrapper.add(toDoWrapper);
        toDoWrapper.setLayout(null);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JLabel toDoContentTitle = new JLabel("오늘의 할 일");
        toDoContentTitle.setFont(new Font("굴림", Font.BOLD, 35));
        toDoContentTitle.setBounds(47, 46, 360, 61);
        toDoWrapper.add(toDoContentTitle);
        
        toDoContentWrapper = new JPanel();
        toDoContentWrapper.setBackground(Color.WHITE);
        toDoContentWrapper.setBounds(47, 127, 455, 350);
        toDoWrapper.add(toDoContentWrapper);
        toDoContentWrapper.setLayout(null);
        
        displayQuizzes();
        

    }
	
	public void displayQuizzes() {
	    // 기존 내용 제거
	    toDoContentWrapper.removeAll();

	    if (loginUser == null) {
	        JLabel toDoLoginMessage = new JLabel("로그인 후 이용해주세요");
	        toDoLoginMessage.setFont(new Font("굴림", Font.PLAIN, 25));
	        toDoLoginMessage.setBounds(20, 0, 300, 40);
	        toDoContentWrapper.add(toDoLoginMessage);
	    } else {
	        List<Quiz> quizzes = loginUser.getQuizzes();
	        LocalDate today = LocalDate.now();

	        // 오늘 날짜에 해당하는 퀴즈 필터링
	        List<Quiz> todaysQuizzes = quizzes.stream()
	                .filter(quiz -> quiz.getDueDateTime().toLocalDate().equals(today))
	                .collect(Collectors.toList());

	        // 테이블 모델 생성
	        String[] columnNames = {"제목", "과목", "마감일"};
	        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
	        
	        // 퀴즈 데이터를 모델에 추가
	        for (Quiz quiz : todaysQuizzes) {
	            Object[] rowData = {
	                quiz.getTitle(),
	                quiz.getSubject(),
	                quiz.getDueDateTime().format(formatter)
	            };
	            tableModel.addRow(rowData);
	        }

	        // 테이블 생성 및 설정
	        JTable quizTable = new JTable(tableModel);
	        quizTable.setFont(new Font("굴림", Font.PLAIN, 15));
	        quizTable.setRowHeight(50);


	        // 스크롤 페인 생성 및 추가
	        JScrollPane scrollPane = new JScrollPane(quizTable);
	        scrollPane.setBounds(20, 0, 400, 350);
	        toDoContentWrapper.add(scrollPane);
	    }

	    // 변경사항 적용 및 새로고침
	    toDoContentWrapper.revalidate();
	    toDoContentWrapper.repaint();
	}
	
	public void addNotice(Notice notice) {
        notices.add(0, notice); // 작성한 공지사항을 가장 위에 배치
        updateNotices(); // 공지사항 패널 업데이트
        JTable table = noticeManager.getNoticeTable(); // 공지사항 테이블에 공지사항 추가하기
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{notice.getTitle(), notice.getAuthor(), notice.getDate()});
    }


	private void updateNotices() {
        if (noticeContentWrapper == null) {
             // Just for safety, if noticeContentWrapper is somehow null, initialize it
             noticeContentWrapper = new JPanel();
             noticeContentWrapper.setLayout(new BoxLayout(noticeContentWrapper, BoxLayout.Y_AXIS));
         }

         noticeContentWrapper.removeAll();
         
         int displayCount = Math.min(notices.size(), 5);
         for (int i = 0; i < displayCount; i++) {
             Notice notice = notices.get(i);
             JLabel noticeLabel = new JLabel(notice.getTitle());
             // 라벨의 크기를 최대 크기로 설정하여 공지사항 제목이 레이아웃 나머지 부분을 차지하지 않도록 
             noticeLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, noticeLabel.getPreferredSize().height));
             noticeLabel.setFont(new Font("굴림", Font.PLAIN, 30));
             noticeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
             noticeLabel.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {
                     // 클릭된 라벨의 텍스트를 가져와서 해당 공지사항을 찾음
                     String selectedTitle = noticeLabel.getText();
                     Notice selectedNotice = getNoticeByTitle(selectedTitle);
                     if (selectedNotice != null) {
                         showNoticeDetail(selectedNotice);
                     }
                 }
             });
             noticeContentWrapper.add(noticeLabel);
             
          // 공지사항 사이의 간격 추가
             if (i < displayCount - 1) {
                 noticeContentWrapper.add(Box.createVerticalStrut(60));  // 10px 간격 추가
             }
         }
         
         // 남는 공간을 채우기 위한 빈 패널 추가
         noticeContentWrapper.add(Box.createVerticalGlue());

         noticeContentWrapper.revalidate();
         noticeContentWrapper.repaint();
     }

	 private Notice getNoticeByTitle(String title) {
	        for (Notice notice : notices) {
	            if (notice.getTitle().equals(title)) {
	                return notice;
	            }
	        }
	        return null;
	    }
	   
	    public void showNoticeDetail(Notice notice) {
	        JPanel classNoticeContainer = new JPanel(); // 적절한 패널을 생성하여 넘겨줘야 함
	        ClassNoticeManagement noticeManager = new ClassNoticeManagement(classNoticeContainer);
	        noticeManager.displayNoticeDetail(notice);
	    }   

	
	 public void updateLoginButtons() {
	        if (getLoginUser() == null) {
	            userButton.setText("로그인");
	            userButton.setBackground(Color.WHITE);
	            userButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : userButton.getActionListeners()) {
	                userButton.removeActionListener(al);
	            }
	            userButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    LoginForm loginForm = new LoginForm();
	                    loginForm.setVisible(true);
	                }
	            });
	            
	            logoutButton.setVisible(false);
	            
	        } else {
	        	
	        	
//              Notice newNotice1 = new Notice("[중요] 현장체험학습 동의서 제출 (~5/20)", "윤용익", "2024/05/10", "공지사항 예시");
//              Notice newNotice2= new Notice("2024학년도 1차 구입 예정 도서 목록", "윤용익", "2024/05/11", "공지사항 예시");
//              Notice newNotice3= new Notice("2024학년도 1학년 신입생 안내문 (수정)", "윤용익", "2024/05/12", "공지사항 예시");
//              Notice newNotice4= new Notice("학교안전공제회 관련 개인정보 제공 내역", "윤용익", "2024/06/7", "공지사항 예시");
//              addNotice(newNotice1); addNotice(newNotice2); addNotice(newNotice3); addNotice(newNotice4);
	        	
	            userButton.setText(getLoginUser().getName() + "님");
	            userButton.setBackground(Color.WHITE);
	            userButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : userButton.getActionListeners()) {
	                userButton.removeActionListener(al);
	            } // 앞선 이벤트 리스터 삭제
	            userButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	if(loginUser != null) {
	                		displayMyPage();
	                		
	                	}
	                }
	            });
	            
	            logoutButton.setText("로그아웃");
	            logoutButton.setBackground(Color.WHITE);
	            logoutButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : logoutButton.getActionListeners()) {
	                logoutButton.removeActionListener(al);
	            }
	            logoutButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	// 순서 바꾸지 말 것
	                    showMainPage();
	                    setLoginUser(null);
	                    updateLoginButtons();
	                    displayQuizzes();
	                }
	            });
	            logoutButton.setVisible(true);
	        }
	        sideBtnWrapper.revalidate();
	        sideBtnWrapper.repaint();
	    }
	 
	 private void displayMyPage() {
	        getContentPane().remove(scrollPane);
	        MyPage myPage = MyPage.getInstance();
	        getContentPane().add(myPage, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	 }
	 
	 public void showMainPage() {
		 	MyPage myPage = MyPage.getInstance();
	        getContentPane().remove(myPage);
	        getContentPane().add(scrollPane, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	    }
	 
	 public static void deleteAccount() {
         if (loginUser != null) {
             UserDataSet.getUserDataSetInstance().deleteUser(loginUser.getUserId());
             performLogout();
         }
     }

     public static void performLogout() {
         if (mainPageInstance != null && mainPageInstance.logoutButton != null) {
             mainPageInstance.logoutButton.doClick();
     }
 }
}
