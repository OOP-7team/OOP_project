package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LearningCheck extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LearningCheck window = new LearningCheck();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public LearningCheck() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // JFrame의 기본 속성을 설정
        System.out.println("새로운 창");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setBounds(100, 100, 2000, 1200);
        System.out.println("크기 조정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 패널과 버튼 추가
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	}
}
