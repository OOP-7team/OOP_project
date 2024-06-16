package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class StopWatch extends JPanel {

    private JButton start, reset, pause, record;
    private JLabel w1, w2, w3;
    private int hh, mm, ss; // 시간, 분, 초 단위
    private JComboBox<String> subjectComboBox;
    private TreeMap<String, Integer> studyTimeMap; // 과목별 공부 시간 저장
    private JPanel pieChartPanel;
    private boolean isRunning; // 스톱워치 실행 여부를 나타내는 플래그
    private Thread p_display; // 스톱워치를 동작시키는 스레드

    public StopWatch() {
       studyTimeMap = new TreeMap<>((s1, s2) -> {
          // 정렬 기준: 공부 시간 내림차순
            int time1 = studyTimeMap.getOrDefault(s1, 0);
            int time2 = studyTimeMap.getOrDefault(s2, 0);
            return time2 - time1;
        });
       
        studyTimeMap = new TreeMap<>();
        studyTimeMap.put("국어", 0);
        studyTimeMap.put("영어", 0);
        studyTimeMap.put("수학", 0);
        studyTimeMap.put("사회", 0);
        studyTimeMap.put("과학", 0);

        buildGUI();
    }

    private void buildGUI() {
        setLayout(new BorderLayout());

        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 패널 여백 추가
        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel wp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        subjectComboBox = new JComboBox<>(new String[]{"국어", "영어", "수학", "사회", "과학"});
        JLabel c1 = new JLabel(" : "); JLabel c2 = new JLabel(" : ");
        
        w1 = new JLabel("00"); w2 = new JLabel("00"); w3 = new JLabel("00");

        start = new JButton("시작");
        pause = new JButton("중지");
        reset = new JButton("리셋");
        record = new JButton("반영");

        bp.add(start); bp.add(pause); bp.add(reset); bp.add(record);
        
        wp.add(w1); wp.add(c1); wp.add(w2); wp.add(c2); wp.add(w3);

        p.add(subjectComboBox, BorderLayout.NORTH);
        p.add(wp, BorderLayout.CENTER);
        p.add(bp, BorderLayout.SOUTH);
        add(p, BorderLayout.EAST);

        pieChartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };
        pieChartPanel.setPreferredSize(new Dimension(400, 400)); // 크기 조정
        pieChartPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // 패널 여백 추가
        add(pieChartPanel, BorderLayout.CENTER); // 그래프를 중앙에 배치

        start.setFont(new Font("굴림", Font.BOLD, 25));
        pause.setFont(new Font("굴림", Font.BOLD, 25));
        reset.setFont(new Font("굴림", Font.BOLD, 25));
        record.setFont(new Font("굴림", Font.BOLD, 25));

        w1.setFont(new Font("굴림", Font.BOLD, 25));
        w2.setFont(new Font("굴림", Font.BOLD, 25));
        w3.setFont(new Font("굴림", Font.BOLD, 25));

        c1.setFont(new Font("굴림", Font.BOLD, 25));
        c2.setFont(new Font("굴림", Font.BOLD, 25));

        pause.setEnabled(false);
        reset.setEnabled(false);

        start.addActionListener(new ButtonListener());
        pause.addActionListener(new ButtonListener());
        reset.addActionListener(new ButtonListener());
        record.addActionListener(new ButtonListener());
    }

    protected void drawPieChart(Graphics g) {
        int total = studyTimeMap.values().stream().mapToInt(Integer::intValue).sum();
        if (total == 0) return;

        int startAngle = 0;
        int width = pieChartPanel.getWidth();
        int height = pieChartPanel.getHeight();
        int minDimension = Math.min(width, height);
        int pieSize = minDimension - 20;

        int x = (width - pieSize) / 2;
        int y = (height - pieSize) / 2;
        
        Font font = new Font("굴림", Font.BOLD, 12);
        g.setFont(font);
        int labelY = y; // 라벨 Y 좌표 초기값

        List<String> topSubjects = new ArrayList<>();

        int rank = 1;
        for (Map.Entry<String, Integer> entry : studyTimeMap.entrySet()) {
            String subject = entry.getKey();
            int time = entry.getValue();
            int angle = (int) (time * 360.0 / total);

            // 그래프 그리기
            g.setColor(getPastelColorForSubject(subject));
            g.fillArc(x, y, pieSize, pieSize, startAngle, angle);

            // 과목 이름 표시
            int labelX = x + pieSize + 20; // 라벨 X 좌표
            g.setColor(Color.BLACK);
            g.drawString(subject, labelX, labelY);
            labelY += 20; // 라벨 Y 좌표 증가

            startAngle += angle;

            // 현재 3순위까지의 과목 리스트를 만듦
            if (rank <= 3) {
                topSubjects.add(subject);
                rank++;
            }
        }

        // 3순위까지의 과목을 출력
        int rankY = y + pieSize + 30; // 라벨 Y 좌표 초기값
        g.setFont(new Font("굴림", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.drawString("Top Subjects:", x + 10, rankY);
        rankY += 20;
        for (int i = 0; i < topSubjects.size(); i++) {
            String subject = topSubjects.get(i);
            int time = studyTimeMap.getOrDefault(subject, 0);
            g.drawString((i + 1) + ". " + subject + ": " + time + "초", x + 10, rankY);
            rankY += 20;
        }
    }

    private Color getPastelColorForSubject(String subject) {
        switch (subject) {
            case "국어":
                return new Color(255, 175, 175); // 핑크
            case "영어":
                return new Color(175, 175, 255); // 파랑
            case "수학":
                return new Color(175, 255, 175); // 초록
            case "사회":
                return new Color(255, 255, 175); // 노랑
            case "과학":
                return new Color(255, 200, 100); // 주황
            default:
                return Color.GRAY;
        }
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();

            if (s.equals("시작")) {
                start.setEnabled(false);
                pause.setEnabled(true);
                reset.setEnabled(true);
                isRunning = true;

                if (p_display == null || !p_display.isAlive()) {
                    p_display = new Thread(() -> {
                        while (isRunning) {
                            try {
                                Thread.sleep(1000);
                                ss++;
                                if (ss == 60) {
                                    ss = 0;
                                    mm++;
                                    if (mm == 60) {
                                        mm = 0;
                                        hh++;
                                    }
                                }

                                SwingUtilities.invokeLater(() -> {
                                    w1.setText(String.format("%02d", hh));
                                    w2.setText(String.format("%02d", mm));
                                    w3.setText(String.format("%02d", ss));
                                });
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    });
                    p_display.start();
                }
            } else if (s.equals("중지")) {
                start.setEnabled(true);
                pause.setEnabled(false);
                isRunning = false;
            } else if (s.equals("리셋")) {
                start.setEnabled(true);
                pause.setEnabled(false);
                reset.setEnabled(false);

                w1.setText("00");
                w2.setText("00");
                w3.setText("00");

                hh = 0;
                mm = 0;
                ss = 0;
            } else if (s.equals("반영")) {
                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int recordedTime = hh * 3600 + mm * 60 + ss;
                studyTimeMap.put(selectedSubject, studyTimeMap.get(selectedSubject) + recordedTime);

                // 순위 재정렬을 위해 TreeMap 재정렬
                studyTimeMap = sortMapByValue(studyTimeMap);

                w1.setText("00");
                w2.setText("00");
                w3.setText("00");
                hh = 0;
                mm = 0;
                ss = 0;

                pieChartPanel.repaint();
            }
        }
    }

    public void setDummyData() {
        studyTimeMap.put("국어", 3600); // 1시간
        studyTimeMap.put("영어", 1800); // 30분
        studyTimeMap.put("수학", 5400); // 1시간 30분
        studyTimeMap.put("사회", 7200); // 2시간
        studyTimeMap.put("과학", 2700); // 45분

        // 초기 순위 정렬
        studyTimeMap = sortMapByValue(studyTimeMap);
    }

    public TreeMap<String, Integer> sortMapByValue(Map<String, Integer> map) {
        TreeMap<String, Integer> sortedMap = new TreeMap<>((s1, s2) -> {
            int time1 = map.getOrDefault(s1, 0);
            int time2 = map.getOrDefault(s2, 0);
            return time2 - time1;
        });
        sortedMap.putAll(map);
        return sortedMap;
    }
}