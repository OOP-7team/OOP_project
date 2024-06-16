package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class QuizChart extends JPanel {

    // 예시 데이터 준비
    private int[] minScores, maxScores;
    private String[] subjects;

    // 과목 수
    private int numSubjects = 5;

    // 예시 데이터 집합 생성
    private StudentQuizDataSet studentQuizzes;

    // 각 과목별 점수를 저장할 맵 생성
    private Map<String, ArrayList<Integer>> scoresBySubject;

    public QuizChart() {
        setLayout(new GridLayout(1, 2)); // 1행 2열의 GridLayout 사용

        // 예시 데이터 초기화
        minScores = new int[numSubjects];
        maxScores = new int[numSubjects];
        subjects = new String[]{"국어", "수학", "영어", "사회", "과학"};

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

        // 각 과목별 최소값과 최대값 계산 및 저장
        calculateMinMaxScores();

        JPanel chart1Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                // Padding and scale settings
                int padding = 40;
                int labelPadding = 30;
                int barPadding = 20;  // 과목 간의 간격 추가
                int initialPadding = 40; // 첫 번째 과목 전에 간격 추가
                double scale = (double) (height - 2 * padding - labelPadding) / 100;

                // Draw background
                g2d.setColor(Color.WHITE);
                g2d.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
                g2d.setColor(Color.BLACK);

                // Create hatch marks and grid lines for y-axis
                for (int i = 0; i <= 10; i++) {
                    int y = height - ((i * (height - padding * 2 - labelPadding)) / 10 + padding + labelPadding);
                    g2d.drawLine(padding + labelPadding, y, width - padding, y);
                    g2d.drawString((i * 10) + "", padding, y + (labelPadding / 2));
                }

                // Draw the bars
                int barWidth = (width - 2 * padding - labelPadding - (subjects.length - 1) * barPadding - initialPadding) / (subjects.length * 2);
                for (int i = 0; i < subjects.length; i++) {
                    int x = i * (2 * barWidth + barPadding) + padding + labelPadding + initialPadding / 2;
                    int minBarHeight = (int) (minScores[i] * scale);
                    int maxBarHeight = (int) (maxScores[i] * scale);

                    g2d.setColor(new Color(192, 236, 149));
                    g2d.fillRect(x, height - minBarHeight - padding - labelPadding, barWidth, minBarHeight);
                    g2d.setColor(new Color(142, 186, 99));
                    g2d.fillRect(x + barWidth, height - maxBarHeight - padding - labelPadding, barWidth, maxBarHeight);

                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, height - minBarHeight - padding - labelPadding, barWidth, minBarHeight);
                    g2d.drawRect(x + barWidth, height - maxBarHeight - padding - labelPadding, barWidth, maxBarHeight);

                    // Draw value labels for minScores1
                    if (minScores[i] != 100) {
                        g2d.drawString(String.valueOf(minScores[i]), x + barWidth / 2, height - minBarHeight - padding - labelPadding - 5);
                    }

                    // Draw value labels for maxScores1
                    if (maxScores[i] != 100) {
                        g2d.drawString(String.valueOf(maxScores[i]), x + barWidth + barWidth / 2, height - maxBarHeight - padding - labelPadding - 5);
                    }

                    // Draw subject labels
                    int labelX = x + barWidth / 2;
                    int labelY = height - padding + labelPadding / 2;
                    g2d.drawString(subjects[i], labelX, labelY - 20);
                }

                // Draw chart title
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("굴림", Font.BOLD, 20));
                g2d.drawString("과목별 최저점/최고점", 10, 30);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 600); // 첫 번째 차트의 기본 크기 설정
            }
        };

        JPanel chart2Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                // Padding and scale settings
                int padding = 40;
                int labelPadding = 30;
                int barPadding = 20;  // 각 과목 간의 간격 추가
                int initialPadding = 40; // 첫 번째 과목 전에 간격 추가
                double scoreScale = (double) (height - 2 * padding - labelPadding) / 100; // 점수 스케일
                double errorRateScale = (double) (height - 2 * padding - labelPadding) / 100; // 오답률 스케일

                // Draw background
                g2d.setColor(Color.WHITE);
                g2d.fillRect(padding + labelPadding, padding, width - 2 * padding - labelPadding, height - 2 * padding - labelPadding);
                g2d.setColor(Color.BLACK);

                // Create hatch marks and grid lines for y-axis
                for (int i = 0; i <= 10; i++) {
                    int y = height - ((i * (height - padding * 2 - labelPadding)) / 10 + padding + labelPadding);
                    g2d.drawLine(padding + labelPadding, y, width - padding, y);
                    g2d.drawString((i * 10) + "", padding, y + (labelPadding / 2));
                }

                // Draw the bars
                int barWidth = (width - 2 * padding - labelPadding - (subjects.length - 1) * barPadding - initialPadding) / (subjects.length * 2);
                for (int i = 0; i < subjects.length; i++) {
                    int x = i * (2 * barWidth + barPadding) + padding + labelPadding + initialPadding / 2;

                    // Calculate average score and error rate
                    double averageScore = calculateAverageScoreBySubject(subjects[i]);
                    double averageErrorRate = calculateAverageErrorRateBySubject(subjects[i]);

                    // Scale values to fit in the graph
                    int averageScoreHeight = (int) (averageScore * scoreScale);
                    int averageErrorRateHeight = (int) (averageErrorRate * errorRateScale);
                    
                    System.out.println("평균 점수 높이" + averageScoreHeight);
                    System.out.println("평균 점수 오답률 높이" + averageErrorRateHeight);

                    // Draw average score bar
                    g2d.setColor(new Color(192, 236, 149));
                    int barX = x + barPadding / 2; // 막대 그래프의 x 좌표
                    int barY = height - padding - labelPadding - averageScoreHeight; // 막대 그래프의 y 좌표
                    g2d.fillRect(barX, barY, barWidth, averageScoreHeight);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(barX, barY, barWidth, averageScoreHeight);

                    // Draw average error rate bar
                    g2d.setColor(new Color(142, 186, 99));
                    barX += barWidth + barPadding / 2; // 오답률 막대 그래프의 x 좌표
                    barY = height - padding - labelPadding - averageErrorRateHeight; // 오답률 막대 그래프의 y 좌표
                    g2d.fillRect(barX, barY, barWidth, averageErrorRateHeight);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(barX, barY, barWidth, averageErrorRateHeight);

                    // Draw value labels
                    g2d.setFont(new Font("굴림", Font.PLAIN, 12));

                    // Draw value labels for minScores1
                    if (height - padding - labelPadding - averageScoreHeight - 5 != 100.0) {
                    	g2d.drawString(String.format("%.1f", averageScore), x + barWidth / 2, height - padding - labelPadding - averageScoreHeight - 5);
                    }

                    // Draw value labels for maxScores1
                    if (height - padding - labelPadding - averageErrorRateHeight - 5 != 100.0) {
                    	g2d.drawString(String.format("%.1f%%", averageErrorRate), x + barWidth + barPadding / 2 + barWidth / 2, height - padding - labelPadding - averageErrorRateHeight - 5);
                    }
                    // Draw subject labels
                    int labelX = x + barWidth / 2;
                    int labelY = height - padding + labelPadding / 2;
                    g2d.drawString(subjects[i], labelX, labelY - 20);
                }

                // Draw chart title
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("굴림", Font.BOLD, 20));
                g2d.drawString("과목별 평균/오답률", 10, 30);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 600); // 두 번째 차트의 기본 크기 설정
            }
        };




        add(chart1Panel);
        add(chart2Panel);
    }

    private void calculateMinMaxScores() {
        int k_count = studentQuizzes.countBySubject("국어");
        int m_count = studentQuizzes.countBySubject("수학");
        int e_count = studentQuizzes.countBySubject("영어");
        int s_count = studentQuizzes.countBySubject("사회");
        int c_count = studentQuizzes.countBySubject("과학");
        
        // subjects1에 대한 최소값과 최대값 계산
        for (int i = 0; i < numSubjects; i++) {
            String subject = subjects[i];
            ArrayList<Integer> scores = scoresBySubject.get(subject);

            if (scores != null && !scores.isEmpty()) {
                // 최소값과 최대값 계산
                int minScore = scores.stream().min(Integer::compareTo).orElse(0);
                int maxScore = scores.stream().max(Integer::compareTo).orElse(0);
                
                // 백분율 계산 및 저장
                switch (i) {
                    case 0:
                        minScores[i] = (minScore * 100) / k_count; // 국어 과목의 퀴즈 수를 이용하여 백분율 계산
                        maxScores[i] = (maxScore * 100) / k_count;
                        break;
                    case 1:
                        minScores[i] = (minScore * 100) / m_count; // 수학 과목의 퀴즈 수를 이용하여 백분율 계산
                        maxScores[i] = (maxScore * 100) / m_count;
                        break;
                    case 2:
                        minScores[i] = (minScore * 100) / e_count; // 영어 과목의 퀴즈 수를 이용하여 백분율 계산
                        maxScores[i] = (maxScore * 100) / e_count;
                        break;
                    case 3:
                        minScores[i] = (minScore * 100) / s_count; // 사회 과목의 퀴즈 수를 이용하여 백분율 계산
                        maxScores[i] = (maxScore * 100) / s_count;
                        break;
                    case 4:
                        minScores[i] = (minScore * 100) / c_count; // 과학 과목의 퀴즈 수를 이용하여 백분율 계산
                        maxScores[i] = (maxScore * 100) / c_count;
                        break;
                    default:
                        // 처리할 과목이 없을 경우
                        break;
                }
            }
        }
        
    }
    
    public double calculateAverageScoreBySubject(String subject) {
        List<StudentQuiz> quizzes = studentQuizzes.getAllStudentQuizzes();
        //int count = studentQuizzes.countBySubject(subject);
        int count = 0;
        int totalScore = 0;

        for (StudentQuiz quiz : quizzes) {
            if (quiz.getQuiz().getSubject().equals(subject)) {
                totalScore += quiz.getScore();
                count += quiz.getQuiz().getQuestions().size();
            }
        }

        if (count > 0) {
        	System.out.println(subject + "의 평균점수" + (double) (totalScore * 100 / count));
            return (double) (totalScore  * 100 / count);
        } else {
            return 0.0; // 혹은 예외 처리를 할 수도 있습니다.
        }
    }
    
    
    public double calculateAverageErrorRateBySubject(String subject) {
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




    public static void createAndShowGui() {
        JFrame frame = new JFrame("Dual Quiz Charts");
        QuizChart dualQuizCharts = new QuizChart();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(dualQuizCharts);
        frame.pack();
        frame.setSize(900, 600); // 전체 프레임 크기 설정
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizChart::createAndShowGui);
    }
}
