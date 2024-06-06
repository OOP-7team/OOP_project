package project;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TodayQuiz extends JFrame{

	private JFrame frame;

	public TodayQuiz() {
		initialize();
	}

	private void initialize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        setBounds(100, 100, 1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        
        JPanel content = new JPanel();
        getContentPane().add(content, BorderLayout.CENTER);
        content.setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
	}

}
