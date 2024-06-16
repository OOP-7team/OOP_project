package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class QuizStatistics extends JPanel {

    private static final long serialVersionUID = 1L;

    // 예시 데이터 집합 생성
    private StudentQuizDataSet studentQuizzes;

    // 각 과목별 점수를 저장할 맵 생성
    private Map<String, ArrayList<Integer>> scoresBySubject;
    
    // 선택된 과목 저장
    private Set<String> selectedAvgSubjects = new LinkedHashSet<>();
    private Set<String> selectedErrSubjects = new LinkedHashSet<>();

    private JLabel avgContent;
    private JLabel errContent;

    public QuizStatistics() {
        setLayout(null);
        setBounds(10, 10, 670, 390);

        // 부분 평균 계산기-----------------------------------------------
        JLabel partialAvgTitle = new JLabel("부분 평균 점수");
        partialAvgTitle.setFont(new Font("굴림", Font.BOLD, 25));
        partialAvgTitle.setBounds(20, 10, 172, 39);
        add(partialAvgTitle);

        JPanel partialAvgCalc = new JPanel();
        partialAvgCalc.setBackground(Color.DARK_GRAY);
        partialAvgCalc.setBounds(20, 55, 295, 295);
        add(partialAvgCalc);
        partialAvgCalc.setLayout(null);

        JPanel avgWrapper = new JPanel();
        avgWrapper.setBounds(10, 10, 275, 65);
        partialAvgCalc.add(avgWrapper);
        avgWrapper.setLayout(new BorderLayout(0, 0));

        avgContent = new JLabel();
        avgContent.setBackground(Color.WHITE);
        avgContent.setFont(new Font("굴림", Font.PLAIN, 20));
        avgWrapper.add(avgContent, BorderLayout.CENTER);

        JButton avgKorBtn = new JButton("국어");
        avgKorBtn.setBackground(Color.WHITE);
        avgKorBtn.setFont(new Font("굴림", Font.BOLD, 20));
        avgKorBtn.setBounds(10, 85, 85, 60);
        avgKorBtn.addActionListener(new AvgButtonListener("국어"));
        partialAvgCalc.add(avgKorBtn);

        JButton avgEngBtn = new JButton("영어");
        avgEngBtn.setBackground(Color.WHITE);
        avgEngBtn.setFont(new Font("굴림", Font.BOLD, 20));
        avgEngBtn.setBounds(10, 155, 85, 60);
        avgEngBtn.addActionListener(new AvgButtonListener("영어"));
        partialAvgCalc.add(avgEngBtn);

        JButton avgSciBtn = new JButton("과학");
        avgSciBtn.setBackground(Color.WHITE);
        avgSciBtn.setFont(new Font("굴림", Font.BOLD, 20));
        avgSciBtn.setBounds(10, 225, 180, 60);
        avgSciBtn.addActionListener(new AvgButtonListener("과학"));
        partialAvgCalc.add(avgSciBtn);

        JButton avgMathBtn = new JButton("수학");
        avgMathBtn.setBackground(Color.WHITE);
        avgMathBtn.setFont(new Font("굴림", Font.BOLD, 20));
        avgMathBtn.setBounds(105, 85, 85, 60);
        avgMathBtn.addActionListener(new AvgButtonListener("수학"));
        partialAvgCalc.add(avgMathBtn);

        JButton avgSociBtn = new JButton("사회");
        avgSociBtn.setBackground(Color.WHITE);
        avgSociBtn.setFont(new Font("굴림", Font.BOLD, 20));
        avgSociBtn.setBounds(105, 155, 85, 60);
        avgSociBtn.addActionListener(new AvgButtonListener("사회"));
        partialAvgCalc.add(avgSociBtn);

        JButton avgRmvAllBtn = new JButton("C");
        avgRmvAllBtn.setBackground(Color.WHITE);
        avgRmvAllBtn.setFont(new Font("굴림", Font.BOLD, 30));
        avgRmvAllBtn.setBounds(200, 85, 85, 60);
        avgRmvAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedAvgSubjects.clear();
                updateAvgContent();
            }
        });
        partialAvgCalc.add(avgRmvAllBtn);

        JButton avgRmvBtn = new JButton("");

        // 이미지 크기 조정
        ImageIcon icon = new ImageIcon(QuizDashBoard.class.getResource("/images/delete.png"));
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(65, 60, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
        avgRmvBtn.setIcon(updateIcon);

        avgRmvBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLastSubject(selectedAvgSubjects);
                updateAvgContent();
            }
        });
        avgRmvBtn.setBackground(Color.WHITE);
        avgRmvBtn.setBounds(200, 155, 85, 60);
        partialAvgCalc.add(avgRmvBtn);

        JButton avgBtn = new JButton("=");
        avgBtn.setVerticalAlignment(SwingConstants.TOP);
        avgBtn.setBackground(Color.WHITE);
        avgBtn.setFont(new Font("굴림", Font.BOLD, 45));
        avgBtn.setBounds(200, 225, 85, 60);
        avgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double avgScore = calculateAverage(selectedAvgSubjects);
                avgContent.setText("평균: " + avgScore);
            }
        });
        partialAvgCalc.add(avgBtn);

        // 부분 오답률 계산기-----------------------------------------------
        JPanel partialErrCalc = new JPanel();
        partialErrCalc.setLayout(null);
        partialErrCalc.setBackground(Color.DARK_GRAY);
        partialErrCalc.setBounds(333, 55, 295, 295);
        add(partialErrCalc);

        JPanel errWrapper = new JPanel();
        errWrapper.setBounds(10, 10, 275, 65);
        partialErrCalc.add(errWrapper);
        errWrapper.setLayout(new BorderLayout(0, 0));

        errContent = new JLabel();
        errContent.setFont(new Font("굴림", Font.PLAIN, 20));
        errContent.setBackground(Color.WHITE);
        errWrapper.add(errContent, BorderLayout.CENTER);

        JButton errKorBtn = new JButton("국어");
        errKorBtn.setFont(new Font("굴림", Font.BOLD, 20));
        errKorBtn.setBackground(Color.WHITE);
        errKorBtn.setBounds(10, 85, 85, 60);
        errKorBtn.addActionListener(new ErrButtonListener("국어"));
        partialErrCalc.add(errKorBtn);

        JButton errEngBtn = new JButton("영어");
        errEngBtn.setFont(new Font("굴림", Font.BOLD, 20));
        errEngBtn.setBackground(Color.WHITE);
        errEngBtn.setBounds(10, 155, 85, 60);
        errEngBtn.addActionListener(new ErrButtonListener("영어"));
        partialErrCalc.add(errEngBtn);

        JButton errSciBtn = new JButton("과학");
        errSciBtn.setFont(new Font("굴림", Font.BOLD, 20));
        errSciBtn.setBackground(Color.WHITE);
        errSciBtn.setBounds(10, 225, 180, 60);
        errSciBtn.addActionListener(new ErrButtonListener("과학"));
        partialErrCalc.add(errSciBtn);

        JButton errMathBtn = new JButton("수학");
        errMathBtn.setFont(new Font("굴림", Font.BOLD, 20));
        errMathBtn.setBackground(Color.WHITE);
        errMathBtn.setBounds(105, 85, 85, 60);
        errMathBtn.addActionListener(new ErrButtonListener("수학"));
        partialErrCalc.add(errMathBtn);

        JButton errSociBtn = new JButton("사회");
        errSociBtn.setFont(new Font("굴림", Font.BOLD, 20));
        errSociBtn.setBackground(Color.WHITE);
        errSociBtn.setBounds(105, 155, 85, 60);
        errSociBtn.addActionListener(new ErrButtonListener("사회"));
        partialErrCalc.add(errSociBtn);

        JButton errRmvAllBtn = new JButton("C");
        errRmvAllBtn.setFont(new Font("굴림", Font.BOLD, 30));
        errRmvAllBtn.setBackground(Color.WHITE);
        errRmvAllBtn.setBounds(200, 85, 85, 60);
        errRmvAllBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedErrSubjects.clear();
                updateErrContent();
            }
        });
        partialErrCalc.add(errRmvAllBtn);

        JButton errRmvBtn = new JButton("");

        // 이미지 크기 조정
        ImageIcon icon2 = new ImageIcon(QuizDashBoard.class.getResource("/images/delete.png"));
        Image img2 = icon2.getImage();
        Image updateImg2 = img2.getScaledInstance(65, 60, Image.SCALE_SMOOTH);
        ImageIcon updateIcon2 = new ImageIcon(updateImg2);
        errRmvBtn.setIcon(updateIcon2);

        errRmvBtn.setBackground(Color.WHITE);
        errRmvBtn.setBounds(200, 155, 85, 60);
        errRmvBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLastSubject(selectedErrSubjects);
                updateErrContent();
            }
        });
        partialErrCalc.add(errRmvBtn);

        JButton errBtn = new JButton("=");
        errBtn.setFont(new Font("굴림", Font.BOLD, 45));
        errBtn.setBackground(Color.WHITE);
        errBtn.setBounds(200, 225, 85, 60);
        errBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double avgErrorRate = calculateErrorRate(selectedErrSubjects);
                errContent.setText("오답률: " + avgErrorRate + "%");
            }
        });
        partialErrCalc.add(errBtn);

        JLabel partialErrTitle = new JLabel("부분 평균 오답률");
        partialErrTitle.setFont(new Font("굴림", Font.BOLD, 25));
        partialErrTitle.setBounds(333, 10, 197, 39);
        add(partialErrTitle);

        //----------------------------------------------------------
        // 예시 데이터 집합 생성
        studentQuizzes = StudentQuizDataSet.getStudentQuizDataSetInstance();

        // 각 과목별 점수를 저장할 맵 초기화
        scoresBySubject = new HashMap<>();

        // studentQuizzes 반복문을 통해 각 과목별 점수 계산
        for (StudentQuiz studentQuiz : studentQuizzes.getAllStudentQuizzes()) {
            String subject = studentQuiz.getQuiz().getSubject();
            int score = studentQuiz.getScore();

            // 과목별 점수 리스트에 점수 추가
            scoresBySubject.computeIfAbsent(subject, k -> new ArrayList<>()).add(score);
        }

    }

    private void updateAvgContent() {
        avgContent.setText(String.join(", ", selectedAvgSubjects));
    }

    private void updateErrContent() {
        errContent.setText(String.join(", ", selectedErrSubjects));
    }

    private void removeLastSubject(Set<String> subjects) {
        if (!subjects.isEmpty()) {
            List<String> subjectList = new ArrayList<>(subjects);
            subjects.remove(subjectList.get(subjectList.size() - 1));
        }
    }

    private double calculateAverage(Set<String> subjects) {
        int totalScore = 0;
        int count = 0;

        for (String subject : subjects) {
            totalScore += calculateAverageScoreBySubject(subject) * studentQuizzes.countBySubject(subject);
            count += studentQuizzes.countBySubject(subject);
        }

        return count > 0 ? (double) totalScore / count : 0.0;
    }

    private double calculateErrorRate(Set<String> subjects) {
        double totalErrorRate = 0.0;
        int count = 0;

        for (String subject : subjects) {
            totalErrorRate += calculateAverageErrorRateBySubject(subject) * studentQuizzes.countBySubject(subject);
            count += studentQuizzes.countBySubject(subject);
        }

        double avgErrorRate = count > 0 ? totalErrorRate / count : 0.0;
        
        // 셋째 자리에서 반올림
        avgErrorRate = Math.round(avgErrorRate * 1000) / 1000.0;
        
        return avgErrorRate;
    }


    public double calculateAverageScoreBySubject(String subject) { // 특정 과목의 평균 계산
        List<StudentQuiz> quizzes = studentQuizzes.getAllStudentQuizzes();
        int count = 0;
        int totalScore = 0;

        for (StudentQuiz quiz : quizzes) {
            if (quiz.getQuiz().getSubject().equals(subject)) {
                totalScore += quiz.getScore();
                count += quiz.getQuiz().getQuestions().size();
            }
        }

        if (count > 0) {
            return (double) (totalScore * 100 / count);
        } else {
            return 0.0; // 혹은 예외 처리를 할 수도 있습니다.
        }
    }

    public double calculateAverageErrorRateBySubject(String subject) { // 특정 과목의 평균 오답률 계산
        List<StudentQuiz> quizzes = studentQuizzes.getAllStudentQuizzes();
        int count = studentQuizzes.countBySubject(subject);
        double totalErrorRate = 0.0;

        for (StudentQuiz quiz : quizzes) {
            if (quiz.getQuiz().getSubject().equals(subject)) {
                int totalQuestions = quiz.getQuiz().getQuestions().size();
                int totalErrors = 0;

                for (SubmittedAnswer answer : quiz.getSubmittedAnswers()) {
                    if (!answer.getIsCorrect()) {
                        totalErrors++;
                    }
                }

                double errorRate = (double) (totalErrors * 100 / totalQuestions); // 한 퀴즈 내에서 오답률
                totalErrorRate += errorRate;
            }
        }

        if (count > 0) {
            return totalErrorRate / count;
        } else {
            return 0.0; // 혹은 예외 처리를 할 수도 있습니다.
        }
    }

    class AvgButtonListener implements ActionListener {
        private String subject;

        public AvgButtonListener(String subject) {
            this.subject = subject;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedAvgSubjects.contains(subject)) {
                selectedAvgSubjects.add(subject);
                updateAvgContent();
            }
        }
    }

    class ErrButtonListener implements ActionListener {
        private String subject;

        public ErrButtonListener(String subject) {
            this.subject = subject;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedErrSubjects.contains(subject)) {
                selectedErrSubjects.add(subject);
                updateErrContent();
            }
        }
    }
}
