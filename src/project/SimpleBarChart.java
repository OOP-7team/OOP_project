package project;

import javax.swing.*;
import java.awt.*;

public class SimpleBarChart extends JPanel {
	public SimpleBarChart() {
	}

    private int[] minScores = {50, 60, 55, 70, 65};
    private int[] maxScores = {95, 90, 85, 80, 75};
    private String[] subjects = {"국어", "수학", "영어", "사회", "과학"};

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
        for (int i = 0; i < 10; i++) {
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

            // Draw subject labels
            int labelX = x + barWidth / 2;
            int labelY = height - padding + labelPadding / 2;
            g2d.drawString(subjects[i], labelX, labelY - 20);
        }
    }

    public static void createAndShowGui() {
        JFrame frame = new JFrame("Simple Bar Chart");
        SimpleBarChart chart = new SimpleBarChart();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chart);
        frame.pack();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleBarChart::createAndShowGui);
    }
}
