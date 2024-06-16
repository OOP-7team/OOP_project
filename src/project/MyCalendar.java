package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyCalendar extends JFrame implements ActionListener{
    
	Container container = getContentPane();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JButton buttonBefore = new JButton("< 전월");
	JButton buttonAfter = new JButton("내월 >");
	
	JLabel label = new JLabel("00년 0월");
	
	JButton[] buttons = new JButton[49];
	String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
	
	CalendarLogic cl = new CalendarLogic();
	JLabel dueDateLabel;
	
	public MyCalendar(JLabel dueDateLabel) {
		this.dueDateLabel = dueDateLabel;
		
		setTitle("날짜 선택");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(550, 400);
		setLocation(400, 400);
		init();
		start();
		setVisible(true);
	}
	
	private void init() {
		 container.setLayout(new BorderLayout());
		 container.add("North", panel1);
		 container.add("Center", panel2);
		 
		 panel1.setLayout(new FlowLayout());
		 panel1.add(buttonBefore);
		 panel1.add(label);
		 panel1.add(buttonAfter);
		 
		 Font font = new Font("SansSerif", Font.BOLD, 20);
		 buttonAfter.setFont(font);
		 buttonBefore.setFont(font);
		 label.setFont(font);
		 
		 label.setText(cl.getCalText());
		 
		 panel2.setLayout(new GridLayout(7, 7, 5, 5));
		 for(int i = 0; i < buttons.length; i++) {
			 buttons[i] = new JButton();
			 panel2.add(buttons[i]);
			 
			 buttons[i].setFont(new Font("SansSerif", Font.BOLD, 24));
			 
			 if(i < 7) buttons[i].setText(dayNames[i]); 
			 
			 if(i%7 == 0) buttons[i].setForeground(Color.RED);
			 if(i%7 == 6) buttons[i].setForeground(Color.BLUE);
			 
			 if (i >= 7) {
	                buttons[i].addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        JButton button = (JButton) e.getSource();
	                        String selectedDate = cl.year + "-0" + cl.month + "-" + button.getText();
	                        if(cl.month >= 10) {
	                        	selectedDate = cl.year + "-" + cl.month + "-" + button.getText();
	                        }
	                        
	                        dueDateLabel.setText(selectedDate);
	                        dispose();
	                    }
	                });
	            }
		 }
		 cl.setButtons(buttons);
		 cl.calSet();
		 
		 
	}

	private void start() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttonAfter.addActionListener(this);
		buttonBefore.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int gap = 0;
		if(e.getSource() == buttonAfter) {				// 1달 후
			gap = 1;
		} else if(e.getSource() == buttonBefore ) {		// 1달 전
			gap = -1;
		}
		cl.allInit(gap);
		label.setText(cl.getCalText());		// 년월 글자 갱신		
	}	
}

