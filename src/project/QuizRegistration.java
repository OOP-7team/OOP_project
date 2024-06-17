package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class QuizRegistration extends JFrame {

	private JFrame quizRegistration;
	private JScrollPane questionInfoWrapper;
	private JPanel questionInfo;
	private JTextField titleField, subjectField, gradeField, classNameField;
	private JLabel dueDateLabel;
    private JSpinner dueTimeSpinner;
    private LocalDateTime dueTime;

	/**
	 * Create the application.
	 */
	public QuizRegistration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setBounds(100, 100, 800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        setVisible(true);
        
        dueTime = LocalDateTime.now(); // dueTime 초기화
		
		JPanel content = new JPanel();
		getContentPane().add(content, BorderLayout.CENTER);
		content.setLayout(null);
		
		JPanel quizInfo = new JPanel();
		quizInfo.setBounds(0, 0, 790, 200);
		content.add(quizInfo);
		quizInfo.setLayout(new GridLayout(8, 2));
        
        JLabel titleLabel = new JLabel("제목:");
        titleLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        titleField = new JTextField();
        titleField.setFont(new Font("굴림", Font.PLAIN, 25));

        JLabel subjectLabel = new JLabel("과목:");
        subjectLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        subjectField = new JTextField();
        subjectField.setFont(new Font("굴림", Font.PLAIN, 25));

        JLabel gradeLabel = new JLabel("학년:");
        gradeLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        gradeField = new JTextField();
        gradeField.setFont(new Font("굴림", Font.PLAIN, 25));

        JLabel classNameLabel = new JLabel("반:");
        classNameLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        classNameField = new JTextField();
        classNameField.setFont(new Font("굴림", Font.PLAIN, 25));

        JButton dueDateButton = new JButton("마감일자 선택");
        dueDateButton.setFont(new Font("굴림", Font.PLAIN, 25));
        dueDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyCalendar(dueDateLabel);
            }
        });

        dueDateLabel = new JLabel();
        dueDateLabel.setFont(new Font("굴림", Font.PLAIN, 25));
        dueDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        // 마감시간 선택 버튼과 라벨 추가
        JLabel dueTimeLabel = new JLabel("마감시간 선택:");
        dueTimeLabel.setFont(new Font("굴림", Font.PLAIN, 20));
        dueTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dueTimeSpinner, "HH:mm:ss");
        dueTimeSpinner.setEditor(timeEditor);
        dueTimeSpinner.setFont(new Font("굴림", Font.PLAIN, 25));
        dueTimeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dueTime = LocalDateTime.of(dueTime.toLocalDate(), ((SpinnerDateModel) dueTimeSpinner.getModel()).getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime());
            }
        });
        
       // 마감시간 선택 버튼 추가 및 액션 리스너 연결
        JButton dueTimeButton = new JButton("마감시간 선택");
        dueTimeButton.setFont(new Font("굴림", Font.PLAIN, 25));
        dueTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimePicker timePicker = new TimePicker(dueTimeLabel);
                timePicker.setVisible(true);
                timePicker.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        LocalDateTime selectedTime = timePicker.getSelectedTime();
                        if (selectedTime != null) {
                            dueTime = selectedTime;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            dueTimeLabel.setText("마감 시간: " + dueTime.format(formatter));
                        }
                    }
                });
            }
        });

        quizInfo.add(titleLabel);
        quizInfo.add(titleField);
        quizInfo.add(subjectLabel);
        quizInfo.add(subjectField);
        quizInfo.add(gradeLabel);
        quizInfo.add(gradeField);
        quizInfo.add(classNameLabel);
        quizInfo.add(classNameField);
        // quizInfo.add(dueDateLabelTitle);
        quizInfo.add(dueDateButton);
        quizInfo.add(dueTimeButton);
        quizInfo.add(dueDateLabel);
        quizInfo.add(dueTimeLabel);
        quizInfo.add(new JLabel());
        
        JButton submitButton = new JButton("퀴즈 등록");
        submitButton.setFont(new Font("굴림", Font.BOLD, 20));
        submitButton.addActionListener(new SubmitButtonListener());
        quizInfo.add(submitButton);
        
        JButton createShortQuestion = new JButton("단답식 생성");
        createShortQuestion.setFont(new Font("굴림", Font.BOLD, 20));
        createShortQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel shortQuestionPanel = new JPanel();
                shortQuestionPanel.setLayout(new GridLayout(3, 2));

                JLabel questionLabel = new JLabel("단답식 문제", SwingConstants.CENTER);
                questionLabel.setFont(new Font("굴림", Font.BOLD, 25));
                questionLabel.setPreferredSize(new Dimension(100, 80));
                shortQuestionPanel.add(questionLabel);

                JTextField questionField = new JTextField();
                questionField.setFont(new Font("굴림", Font.PLAIN, 25));
                questionField.setPreferredSize(new Dimension(300, 80));
                shortQuestionPanel.add(questionField);

                JLabel answerLabel = new JLabel("정답", SwingConstants.CENTER);
                answerLabel.setFont(new Font("굴림", Font.BOLD, 25));
                answerLabel.setPreferredSize(new Dimension(100, 80));
                shortQuestionPanel.add(answerLabel);

                JTextField answerField = new JTextField();
                answerField.setFont(new Font("굴림", Font.PLAIN, 25));
                answerField.setPreferredSize(new Dimension(300, 80));
                shortQuestionPanel.add(answerField);

                // 삭제 버튼 추가
                JButton deleteButton = new JButton("삭제");
                deleteButton.setFont(new Font("굴림", Font.PLAIN, 20));
                deleteButton.setMinimumSize(new Dimension(80, 40));
                deleteButton.setMaximumSize(new Dimension(80, 40));
                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        questionInfo.remove(shortQuestionPanel);
                        questionInfo.revalidate();
                        questionInfo.repaint();
                    }
                });
                shortQuestionPanel.add(new JLabel()); // 빈 셀 추가
                shortQuestionPanel.add(deleteButton);

                questionInfo.add(shortQuestionPanel);
                questionInfo.revalidate();
                questionInfo.repaint();
            }
        });
        quizInfo.add(createShortQuestion);
        
        JButton createMultipleChoiceQuestion = new JButton("객관식 생성");
        createMultipleChoiceQuestion.setFont(new Font("굴림", Font.BOLD, 20));
        createMultipleChoiceQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel multipleChoiceQuestionPanel = new JPanel();
                multipleChoiceQuestionPanel.setLayout(new BoxLayout(multipleChoiceQuestionPanel, BoxLayout.Y_AXIS));

                // 문제 라벨 및 입력 필드
                JPanel questionPanel = new JPanel(new GridLayout(1, 2));
                JLabel questionLabel = new JLabel("객관식 문제", SwingConstants.CENTER);
                questionLabel.setFont(new Font("굴림", Font.BOLD, 25));
                questionLabel.setPreferredSize(new Dimension(100, 80));
                questionPanel.add(questionLabel);

                JTextField questionField = new JTextField();
                questionField.setFont(new Font("굴림", Font.PLAIN, 25));
                questionField.setPreferredSize(new Dimension(300, 80));
                questionPanel.add(questionField);

                multipleChoiceQuestionPanel.add(questionPanel);

                // 객관식 선지
                for (int i = 1; i <= 4; i++) {
                    JPanel choicePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JRadioButton radioButton = new JRadioButton();
                    radioButton.setFont(new Font("굴림", Font.PLAIN, 25));
                    choicePanel.add(radioButton);

                    JTextField choiceField = new JTextField();
                    choiceField.setFont(new Font("굴림", Font.PLAIN, 25));
                    choiceField.setPreferredSize(new Dimension(300, 80));
                    choicePanel.add(choiceField);

                    multipleChoiceQuestionPanel.add(choicePanel);
                }

                // 삭제 버튼 추가
                JButton deleteButton = new JButton("삭제");
                deleteButton.setFont(new Font("굴림", Font.PLAIN, 20));
                deleteButton.setMinimumSize(new Dimension(80, 40));
                deleteButton.setMaximumSize(new Dimension(80, 40));
                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        questionInfo.remove(multipleChoiceQuestionPanel);
                        questionInfo.revalidate();
                        questionInfo.repaint();
                    }
                });

                multipleChoiceQuestionPanel.add(deleteButton);

                questionInfo.add(multipleChoiceQuestionPanel);
                questionInfo.revalidate();
                questionInfo.repaint();
            }
        });
        quizInfo.add(createMultipleChoiceQuestion);

        
     // 스크롤 패널과 질문 정보 패널 추가
        questionInfo = new JPanel();
        questionInfo.setBackground(Color.WHITE);
        questionInfo.setLayout(new BoxLayout(questionInfo, BoxLayout.Y_AXIS));

        questionInfoWrapper = new JScrollPane(questionInfo);
        questionInfoWrapper.setBounds(0, 200, 790, 560);
        questionInfoWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        questionInfoWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        content.add(questionInfoWrapper);
	}
	
	private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String subject = subjectField.getText();
            String grade = gradeField.getText();
            String className = classNameField.getText();
            String dueDateText = dueDateLabel.getText();

            // 퀴즈 생성 및 등록
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss");
            LocalDateTime dueDateTime = LocalDateTime.parse(dueDateText + " " + dueTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), formatter);
            Quiz newQuiz = new Quiz(title, subject, grade, className, dueDateTime);
            // 여기서 퀴즈 객체를 어딘가에 저장하거나 처리합니다.
            // 예를 들어, 퀴즈 목록에 추가하거나 데이터베이스에 저장할 수 있습니다.
            
            // 문제들을 생성해서 퀴즈 객체에 추가
            List<Question> questions = new ArrayList<>();
            
	         // 단답식 문제 추가
	            for (Component component : questionInfo.getComponents()) {
	                if (component instanceof JPanel) {
	                    JPanel panel = (JPanel) component;
	                    if (panel.getComponentCount() > 0) {
	                        Component firstComponent = panel.getComponent(0);
	                        if (firstComponent instanceof JLabel) {
	                            JLabel label = (JLabel) firstComponent;
	                            if (label.getText().equals("단답식 문제")) {
	                                JTextField questionField = (JTextField) panel.getComponent(1);
	                                JTextField answerField = (JTextField) panel.getComponent(3);
	                                String questionText = questionField.getText();
	                                String answerText = answerField.getText();
	                                Question question = new Question(questionText, answerText);
	                                questions.add(question); // Quiz 객체의 questions에 추가
	                            }
	                        }
	                    }
	                }
	            }

	         // 객관식 문제 추가
	            for (Component component : questionInfo.getComponents()) {
	                if (component instanceof JPanel) {
	                    JPanel panel = (JPanel) component;
	                    if (panel.getComponentCount() > 0) {
	                        Component firstComponent = panel.getComponent(0);
	                        if (firstComponent instanceof JPanel) {
	                            JPanel questionPanel = (JPanel) firstComponent;
	                            JLabel label = (JLabel) questionPanel.getComponent(0);
	                            if (label.getText().equals("객관식 문제")) {
	                                JTextField questionField = (JTextField) questionPanel.getComponent(1);
	                                
	                                // 객관식 선지 담기
	                                List<Answer> answers = new ArrayList<>();
	                                int selectedIndex = -1;
	                                for (int i = 1; i < panel.getComponentCount() - 1; i++) {
	                                    Component choiceComponent = panel.getComponent(i);
	                                    if (choiceComponent instanceof JPanel) {
	                                        JPanel choicePanel = (JPanel) choiceComponent;
	                                        JRadioButton radioButton = (JRadioButton) choicePanel.getComponent(0);
	                                        JTextField choiceField = (JTextField) choicePanel.getComponent(1);
	                                        answers.add(new Answer(i, choiceField.getText()));
	                                        if (radioButton.isSelected()) {
	                                            selectedIndex = i;
	                                        }
	                                    }
	                                }

	                                Question question = new Question(questionField.getText(), Integer.toString(selectedIndex), answers);
	                                System.out.println("객관식 선지 설정: " + selectedIndex);
	                                questions.add(question);
	                            }
	                        }
	                    }
	                }
	            }

            
            // 퀴즈 객체에 질문들 추가
            newQuiz.setQuestions(questions);
            
            // 여기서 퀴즈 객체를 어딘가에 저장하거나 처리합니다.
            // 예를 들어, 퀴즈 목록에 추가하거나 데이터베이스에 저장할 수 있습니다.
            
            
            // QuizDataSet 싱글톤 인스턴스 가져오기
            QuizDataSet quizzes = QuizDataSet.getQuizDataSetInstance();
            
            // 퀴즈 추가
            quizzes.addQuiz(newQuiz);
            
            List<User> filteredUsers = UserDataSet.getUserDataSetInstance().getUsersByGradeAndClass(grade, className);
            System.out.println("필터링된 학급의 학생들" + filteredUsers.toString());
            // filteredUsers의 각 사용자에게 퀴즈 추가
            for (User user : filteredUsers) {
                // 사용자의 quizzes 리스트에 새로운 퀴즈 추가
                user.getQuizzes().add(newQuiz);
                System.out.println("해당 학급 학생들에게 추가" + user.getQuizzes().toString());
            }

            // 등록 완료 메시지 표시
            JOptionPane.showMessageDialog(quizRegistration, "퀴즈 등록이 완료되었습니다.");
           
        }
    }
}
