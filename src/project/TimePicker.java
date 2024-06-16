package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TimePicker extends JFrame {

    private JLabel timeLabel;
    private JSpinner hourSpinner, minuteSpinner, secondSpinner;
    private DateTimeFormatter timeFormatter;
    private LocalDateTime selectedTime; // 선택된 시간을 저장할 변수 추가

    public TimePicker(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        setTitle("시간 선택");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Container container = getContentPane();
        JPanel panel = new JPanel(new BorderLayout());
        container.add(panel);

        JPanel timePanel = new JPanel();
        panel.add(timePanel, BorderLayout.CENTER);

        // 시간 선택 스피너 (시)
        hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1)); // 초기값, 최소값, 최대값, 증가값
        hourSpinner.setPreferredSize(new Dimension(50, 30));
        timePanel.add(hourSpinner);

        // 시간 선택 스피너 (분)
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        minuteSpinner.setPreferredSize(new Dimension(50, 30));
        timePanel.add(minuteSpinner);

        // 시간 선택 스피너 (초)
        secondSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secondSpinner.setPreferredSize(new Dimension(50, 30));
        timePanel.add(secondSpinner);

        JButton okButton = new JButton("확인");
        panel.add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedTime();
                dispose(); // 창 닫기
            }
        });
    }

    private void updateSelectedTime() {
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();
        int second = (int) secondSpinner.getValue();

        selectedTime = LocalDateTime.of(0, 1, 1, hour, minute, second);
        String formattedTime = selectedTime.format(timeFormatter);
        timeLabel.setText(formattedTime);
    }

    public LocalDateTime getSelectedTime() {
        return selectedTime;
    }
}
