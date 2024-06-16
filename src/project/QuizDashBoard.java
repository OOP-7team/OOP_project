package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuizDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentWrapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizDashBoard window = new QuizDashBoard();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuizDashBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("퀴즈 대시보드");
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentWrapper = new JPanel();
		contentWrapper.setBackground(Color.WHITE);
		getContentPane().add(contentWrapper, BorderLayout.CENTER);
		contentWrapper.setLayout(new BorderLayout(0, 0));

		// 기본적으로 QuizChart 패널을 추가
		QuizChart quizChart = new QuizChart();
		contentWrapper.add(quizChart, BorderLayout.CENTER);

		JPanel gnb = new JPanel();
		gnb.setBackground(new Color(47, 79, 79));
		getContentPane().add(gnb, BorderLayout.NORTH);
		gnb.setLayout(new BoxLayout(gnb, BoxLayout.X_AXIS));

		JButton menuBtn = new JButton();
		menuBtn.setBackground(new Color(47, 79, 79));
		menuBtn.setBorderPainted(false);
		menuBtn.setFocusPainted(false);
		menuBtn.setContentAreaFilled(false);
		menuBtn.setPreferredSize(new Dimension(60, 60));

		// 이미지 크기 조정
		ImageIcon icon = new ImageIcon(QuizDashBoard.class.getResource("/images/menu.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);

		JLabel temp = new JLabel("    ");
		temp.setForeground(new Color(47, 79, 79));
		gnb.add(temp);

		menuBtn.setIcon(updateIcon);
		gnb.add(menuBtn);

		JLabel title = new JLabel("   QUIZ DASHBOARD");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("굴림", Font.BOLD, 25));
		gnb.add(title);

		JPanel sideMenu = new JPanel();
		sideMenu.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(sideMenu, BorderLayout.WEST);
		sideMenu.setLayout(new GridLayout(10, 0));

		JButton chartMenu = new JButton("> 차트 보기");
		chartMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentWrapper.removeAll();
				QuizChart newQuizChart = new QuizChart();
				contentWrapper.add(newQuizChart, BorderLayout.CENTER);
				contentWrapper.revalidate();
				contentWrapper.repaint();
			}
		});
		chartMenu.setFont(new Font("굴림", Font.BOLD, 15));
		chartMenu.setBorderPainted(false);
		chartMenu.setContentAreaFilled(false);
		sideMenu.add(chartMenu);

		JButton statisticsMenu = new JButton("> 통계 보기");
		statisticsMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentWrapper.removeAll();
				QuizStatistics quizStatistics = new QuizStatistics();
				contentWrapper.add(quizStatistics, BorderLayout.CENTER);
				contentWrapper.revalidate();
				contentWrapper.repaint();
			}
		});
		statisticsMenu.setFont(new Font("굴림", Font.BOLD, 15));
		statisticsMenu.setBorderPainted(false);
		statisticsMenu.setContentAreaFilled(false);
		sideMenu.add(statisticsMenu);
	}
}
