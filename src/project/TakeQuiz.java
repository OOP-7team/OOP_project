package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class TakeQuiz extends JFrame {

    private JFrame takeQuizFrame;
    private JScrollPane quizPane;
    private JPanel quizPanel;
    private Quiz quiz;
    private List<Question> questions;
    private JTextField[] shortAnswerFields;
    private ButtonGroup[] multipleChoiceGroups;
    private JProgressBar progressBar;
    
    // CardLayout에 필요
    private CardLayout cardLayout;
    private JPanel questionPanel;
    private int currentQuestionIndex = 0;
    
    // Learning Check를 위해 필요
    private StudentQuiz studentQuiz; // 학생이 퀴즈를 푼 정보를 저장할 객체
    
    // 마감까지 남은 시간 표시
    JLabel due;

    /**
     * Create the application.
     */
    public TakeQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.questions = quiz.getQuestions();
        System.out.println("문제들 전달됨" + questions.toString());
        shortAnswerFields = new JTextField[questions.size()];
        multipleChoiceGroups = new ButtonGroup[questions.size()];
        initialize(quiz);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(Quiz quiz) {
        takeQuizFrame = new JFrame();
        takeQuizFrame.setTitle(quiz.getTitle());
        takeQuizFrame.setBounds(100, 100, 800, 800);
        takeQuizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        takeQuizFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        quizPanel = new JPanel();
        quizPanel.setBackground(Color.WHITE);

        quizPane = new JScrollPane(quizPanel);
        quizPanel.setLayout(null);
        
        JPanel basicInfo = new JPanel();
        basicInfo.setBounds(10, 10, 766, 156);
        quizPanel.add(basicInfo);
        basicInfo.setLayout(null);
        
        JLabel subjectLabel = new JLabel("과목");
        subjectLabel.setBackground(Color.WHITE);
        subjectLabel.setFont(new Font("굴림", Font.BOLD, 25));
        subjectLabel.setBounds(10, 10, 137, 35);
        basicInfo.add(subjectLabel);
        
        JLabel subject = new JLabel(quiz.getSubject());
        subject.setFont(new Font("굴림", Font.PLAIN, 25));
        subject.setBackground(Color.WHITE);
        subject.setBounds(157, 10, 155, 35);
        basicInfo.add(subject);
        
        JLabel titleLabel = new JLabel("퀴즈 제목");
        titleLabel.setFont(new Font("굴림", Font.BOLD, 25));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setBounds(326, 10, 137, 35);
        basicInfo.add(titleLabel);
        
        JLabel title = new JLabel(quiz.getTitle());
        title.setFont(new Font("굴림", Font.PLAIN, 25));
        title.setBackground(Color.WHITE);
        title.setBounds(474, 10, 282, 35);
        basicInfo.add(title);
        
        JLabel dueLabel = new JLabel("마감까지 남은 시간");
        dueLabel.setFont(new Font("굴림", Font.BOLD, 25));
        dueLabel.setBackground(Color.WHITE);
        dueLabel.setBounds(10, 55, 273, 35);
        basicInfo.add(dueLabel);
        
        
        LocalDateTime dueDateTime = quiz.getDueDateTime();
        
        // 백그라운드 스레드를 통해 시간 업데이트
        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                Duration duration = Duration.between(LocalDateTime.now(), dueDateTime);
                long seconds = duration.getSeconds();
                if (seconds <= 0) {
                    updateDueTimeLabel("마감됨");
                    break;
                } else {
                    long hours = seconds / 3600;
                    long minutes = (seconds % 3600) / 60;
                    long secs = seconds % 60;
                    String timeLeft = String.format("%02d:%02d:%02d", hours, minutes, secs);
                    updateDueTimeLabel(timeLeft);
                }

                try {
                    Thread.sleep(1000); // 1초마다 업데이트
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateTimeThread.start();
        
        due = new JLabel();
        due.setFont(new Font("굴림", Font.PLAIN, 25));
        due.setBackground(Color.WHITE);
        due.setBounds(293, 55, 155, 35);
        basicInfo.add(due);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 100, 746, 2);
        basicInfo.add(separator);
        
        progressBar = new JProgressBar();
        progressBar.setBounds(157, 112, 599, 34);
        basicInfo.add(progressBar);
        
        JLabel progressLabel = new JLabel("진행률");
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        progressLabel.setFont(new Font("굴림", Font.BOLD, 25));
        progressLabel.setBackground(Color.WHITE);
        progressLabel.setBounds(10, 112, 137, 35);
        basicInfo.add(progressLabel);
        
        JPanel questionWrapper = new JPanel();
        questionWrapper.setBounds(10, 176, 766, 469);
        quizPanel.add(questionWrapper);
        questionWrapper.setLayout(new BorderLayout(0, 0));
        
        JPanel btnPanel = new JPanel();
        questionWrapper.add(btnPanel, BorderLayout.SOUTH);
        btnPanel.setLayout(new GridLayout(0, 4, 0, 0));
        
        JButton goToFirstBtn = new JButton("처음으로");
        goToFirstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(questionPanel);
                currentQuestionIndex = 0;
                updateProgress();
            }
        });
        
        goToFirstBtn.setFont(new Font("굴림", Font.PLAIN, 25));
        btnPanel.add(goToFirstBtn);
        
        JButton prevBtn = new JButton("<");
        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    cardLayout.show(questionPanel, String.valueOf(currentQuestionIndex));
                    updateProgress();
                }
            }
        });
        prevBtn.setFont(new Font("굴림", Font.PLAIN, 25));
        btnPanel.add(prevBtn);
        
        JButton nextBtn = new JButton(">");
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    cardLayout.show(questionPanel, String.valueOf(currentQuestionIndex));
                    updateProgress();
                }
            }
        });
        nextBtn.setFont(new Font("굴림", Font.PLAIN, 25));
        btnPanel.add(nextBtn);
        
        JButton goToLastBtn = new JButton("마지막으로");
        goToLastBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.last(questionPanel);
                currentQuestionIndex = questions.size() - 1;
                updateProgress();
            }
        });
        goToLastBtn.setFont(new Font("굴림", Font.PLAIN, 25));
        btnPanel.add(goToLastBtn);
        
        questionPanel = new JPanel();
        questionWrapper.add(questionPanel, BorderLayout.CENTER);
        cardLayout = new CardLayout();
        questionPanel.setLayout(cardLayout);
        takeQuizFrame.getContentPane().add(quizPane, BorderLayout.CENTER);

        displayQuestions();

        JButton submitButton = new JButton("제출");
        submitButton.setFont(new Font("굴림", Font.BOLD, 25));
        submitButton.addActionListener(new SubmitButtonListener());
        takeQuizFrame.getContentPane().add(submitButton, BorderLayout.SOUTH);

        takeQuizFrame.setVisible(true);
        updateProgress();
    }

    private void displayQuestions() {
        int questionIndex = 0;

        for (Question question : this.questions) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            // 타이틀 폰트 설정
            TitledBorder border = javax.swing.BorderFactory.createTitledBorder("문제 " + (questionIndex + 1));
            border.setTitleFont(new Font("굴림", Font.PLAIN, 25));
            panel.setBorder(border);

            JLabel questionLabel = new JLabel(question.getQuestion()); // 문제 지문 설정
            questionLabel.setFont(new Font("굴림", Font.PLAIN, 25));
            panel.add(questionLabel);

            if (question.getQuestionType().equals("단답식")) {
                // 단답식 문제
                JTextField answerField = new JTextField();
                answerField.setFont(new Font("굴림", Font.PLAIN, 25));
                shortAnswerFields[questionIndex] = answerField;
                panel.add(answerField);
            } else {
                // 객관식 문제
                ButtonGroup group = new ButtonGroup();
                multipleChoiceGroups[questionIndex] = group;

                for (Answer answer : question.getAnswers()) {
                    JRadioButton radioButton = new JRadioButton(answer.getText());
                    radioButton.setFont(new Font("굴림", Font.PLAIN, 25));
                    group.add(radioButton);
                    panel.add(radioButton);
                }
            }

            questionPanel.add(panel, String.valueOf(questionIndex));
            questionIndex++;
        }
    }
    
    private void updateProgress() {
        int completedQuestions = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionType().equals("단답식")) {
                if (!shortAnswerFields[i].getText().trim().isEmpty()) {
                    completedQuestions++;
                }
            } else {
                ButtonGroup group = multipleChoiceGroups[i];
                if (group.getSelection() != null) {
                    completedQuestions++;
                }
            }
        }
        int progress = (int) ((double) completedQuestions / questions.size() * 100);
        progressBar.setValue(progress);
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            
            // 학생이 퀴즈를 제출한 시간
            //LocalDateTime submissionDateTime = LocalDateTime.now(); 생성자에서 자동 저장됨

            // 퀴즈에 대한 답변 정보 생성 및 저장
            List<SubmittedAnswer> submittedAnswers = new ArrayList<>();
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                String submittedText;
                boolean isCorrect;

                if (question.getQuestionType().equals("단답식")) {
                    // 단답식 문제
                    submittedText = shortAnswerFields[i].getText();
                    String correctAnswer = question.getCorrectAnswer();
                    isCorrect = submittedText.equals(correctAnswer);
                } else {
                    // 객관식 문제
                    ButtonGroup group = multipleChoiceGroups[i];
                    Enumeration<AbstractButton> buttons = group.getElements();
                    int selectedButtonIndex = -1;
                    int correctButtonIndex = Integer.parseInt(question.getCorrectAnswer()) - 1;
                    int buttonIndex = 0;
                    while (buttons.hasMoreElements()) {
                        JRadioButton radioButton = (JRadioButton) buttons.nextElement();
                        if (radioButton.isSelected()) {
                            selectedButtonIndex = buttonIndex;
                            break;
                        }
                        buttonIndex++;
                    }
                    
                    isCorrect = selectedButtonIndex == correctButtonIndex;
                    System.out.println("선택된 정답" + selectedButtonIndex);
                    System.out.println("실제 정답" + correctButtonIndex);
                    submittedText = isCorrect ? Integer.toString(correctButtonIndex + 1) :  Integer.toString(selectedButtonIndex + 1);
                }

                SubmittedAnswer submittedAnswer = new SubmittedAnswer(question, submittedText, isCorrect);
                submittedAnswers.add(submittedAnswer);
            }

            int score = calculateScore(submittedAnswers);
            JOptionPane.showMessageDialog(takeQuizFrame, "퀴즈가 제출되었습니다. 점수: " + score);
            
            // 학생이 퀴즈를 푼 정보를 저장하는 StudentQuiz 객체 생성
            studentQuiz = new StudentQuiz(quiz, submittedAnswers, score);
            MainPage.loginUser.addstudentQuiz(studentQuiz);
            // studentQuiz.setSubmissionDateTime(submissionDateTime);
            
            StudentQuizDataSet studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();
            studentQuizzes.addStudentQuiz(studentQuiz);
            System.out.println("데이터 삽입 테스트" + studentQuizzes.toString());

            // 여기서 StudentQuiz 객체를 어딘가에 저장하거나 활용할 수 있습니다.
            // 예를 들어, 데이터베이스에 저장하거나 다른 곳에 전달할 수 있습니다.
            
            takeQuizFrame.dispose();
        }
    }

    private int calculateScore(List<SubmittedAnswer> submittedAnswers) {
        int score = 0;

        for (int i = 0; i < submittedAnswers.size(); i++) {
        	if (submittedAnswers.get(i).getIsCorrect()) {
                score++;
            }
        }
        return score;
    }
    
    private void updateDueTimeLabel(String timeLeft) {
        SwingUtilities.invokeLater(() -> due.setText(timeLeft));
    }
}
