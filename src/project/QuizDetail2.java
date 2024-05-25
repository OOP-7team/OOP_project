//package project;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class QuizDetail2 extends JFrame {
//
//    private Quiz quiz;
//
//    // public QuizDetail2(Quiz quiz) {
//        this.quiz = quiz;
//        
//        JPanel panel = new JPanel();
//        getContentPane().add(panel, BorderLayout.NORTH);
//        initialize();
//    }
//
//    private void initialize() {
//          setTitle("퀴즈 상세정보");
//          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//          getContentPane().setLayout(new BorderLayout(0, 0));
//          
//          JPanel panel = new JPanel();
//          getContentPane().add(panel);
//          panel.setLayout(null);
//  
//        // 퀴즈 정보를 보여줄 패널 생성
//          JPanel quizInfoPanel = new JPanel(new GridLayout(4, 2));
//          quizInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 500, 500));
//  
//          JLabel subjectLabel = new JLabel("과목:");
//          JLabel quizTitleLabel = new JLabel("퀴즈 제목:");
//          JLabel dateLabel = new JLabel("날짜:");
//          JLabel scoreLabel = new JLabel("점수:");
//  
//          JLabel subjectValueLabel = new JLabel(quiz.getSubject());
//          JLabel quizTitleValueLabel = new JLabel(quiz.getTitle());
//          JLabel dateValueLabel = new JLabel(quiz.getDate());
//          JLabel scoreValueLabel = new JLabel(quiz.getScore());
//  
//          quizInfoPanel.add(subjectLabel);
//          quizInfoPanel.add(subjectValueLabel);
//          quizInfoPanel.add(quizTitleLabel);
//          quizInfoPanel.add(quizTitleValueLabel);
//          quizInfoPanel.add(dateLabel);
//          quizInfoPanel.add(dateValueLabel);
//          quizInfoPanel.add(scoreLabel);
//          quizInfoPanel.add(scoreValueLabel);
//  
//          getContentPane().add(quizInfoPanel, BorderLayout.NORTH);
//  
//          // 퀴즈의 각 문제를 보여줄 패널 생성
//          JPanel questionPanel = new JPanel(new GridLayout(quiz.getQuestions().size(), 1));
//          questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//  
//          for (Question question : quiz.getQuestions()) {
//              JLabel questionLabel = new JLabel("문제: " + question.getQuestion());
//              JLabel correctAnswerLabel = new JLabel("정답: " + question.getCorrectAnswer());
//              JLabel userAnswerLabel = new JLabel("사용자 답: " + question.getUserAnswer());
//              JLabel correctLabel = new JLabel("정답 여부: " + (question.isCorrect() ? "맞음" : "틀림"));
//  
//              JPanel questionInfoPanel = new JPanel(new GridLayout(4, 1));
//              questionInfoPanel.add(questionLabel);
//              questionInfoPanel.add(correctAnswerLabel);
//              questionInfoPanel.add(userAnswerLabel);
//              questionInfoPanel.add(correctLabel);
//  
//              questionPanel.add(questionInfoPanel);
//          }
//  
//          JScrollPane scrollPane = new JScrollPane(questionPanel);
//          getContentPane().add(scrollPane, BorderLayout.CENTER);
//  
//          JButton closeButton = new JButton("닫기");
//          closeButton.addActionListener(new ActionListener() {
//              @Override
//              public void actionPerformed(ActionEvent e) {
//                  dispose();
//              }
//          });
//          getContentPane().add(closeButton, BorderLayout.SOUTH);
//  
//          pack();
//          setLocationRelativeTo(null);
//          setVisible(true);
//    }
//}
