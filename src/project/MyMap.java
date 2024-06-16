package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyMap extends JFrame {
	
	private JTextField textField = new JTextField(30);
	private JButton button = new JButton("검색");
	private JPanel panel = new JPanel();
	
	private GoogleAPI googleAPI = new GoogleAPI();
	private JLabel googleMap = new JLabel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMap window = new MyMap();
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
	
	public class Event implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			setMap(textField.getText());
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setMap(String location) {
		
		googleAPI.downloadMap(location);
		googleMap.setIcon(googleAPI.getMap(location));
		googleAPI.fileDelete(location);
		add(BorderLayout.SOUTH, googleMap);
		pack();
	}
	
	public MyMap() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setTitle("Google Maps");
		setBounds(100, 100, 612, 612);
		setVisible(true);
		
		panel.add(textField);
		panel.add(button);
		button.addMouseListener(new Event());

		add(BorderLayout.NORTH, panel);
		pack();
		
	}

	
	
	

	
	

}
