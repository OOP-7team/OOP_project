package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class MainPage extends JFrame{
	
	private static MainPage mainPageInstance;
	
	JPanel sideBtnWrapper = new JPanel();
	
	private JButton userButton;
    private JButton logoutButton;
    
    // 공지사항 표시에 이용
    private List<Notice> notices = new ArrayList<>();
    private ClassNoticeManagement noticeManager;  // Notice 관리 클래스
    JPanel noticeContentWrapper;
    
    private JLabel imageLabel;
    private int imageIndex = 0;
    private final String[] images = {
        "/images/mainBanner/image1.png",
        "/images/mainBanner/image2.png",
        "/images/mainBanner/image3.png"
    };
    
    private JScrollPane scrollPane;
    private JPanel mainContentWrapper;
    
    private void setImage(int index) {
        ImageIcon imageIcon = new ImageIcon(MainPage.class.getResource(images[index]));
        Image image = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }
    
	static User loginUser = null;

	// 접근자 메서드 제공
	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		MainPage.loginUser = loginUser;
	}

	
	public static void main(String[] args) {
		// 새로운 사용자 생성
        User newUser1 = new User("cyaein", "choi0026", "최예인", "숙명초등학교", "2", 
        		"1", "2003.10.13", "", "학생", "", "");
        User newUser2 = new User("yyi", "yoonyi4444", "윤용익", "숙명초등학교", "2", 
        		"1", "2003.10.13", "", "교사", "", "");
        
        // UserDataSet 싱글톤 인스턴스 가져오기
        UserDataSet users = UserDataSet.getUserDataSetInstance();
        // 사용자 추가
        users.addUser(newUser1);
        users.addUser(newUser2);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPage window = MainPage.getInstance();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	// 싱글톤 패턴을 위한 getInstance() 메서드
    public static MainPage getInstance() {
        if (mainPageInstance == null) {
        	mainPageInstance = new MainPage();
        }
        return mainPageInstance;
    }

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}
	
	public static void close(Window window) {
		WindowEvent closeWindow = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1300, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
		setVisible(true);
		
		// 창이 정 가운데에서 뜨도록 해줌
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int leftTopX = centerPoint.x - getWidth()/2;
		int leftTopY = centerPoint.y - getHeight()/2;
		setLocation(leftTopX, leftTopY);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// 상단 gnb 부분
		JPanel gnb = new JPanel();
		gnb.setBackground(Color.WHITE);
		getContentPane().add(gnb, BorderLayout.NORTH);
		
		// gnb의 Layout 설정(gridBagLayout을 선택함)
		GridBagLayout gbl_gnb = new GridBagLayout();
		gbl_gnb.columnWidths = new int[]{360, 360, 360, 360, 0};
		gbl_gnb.rowHeights = new int[]{100, 100, 0};
		gbl_gnb.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_gnb.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gnb.setLayout(gbl_gnb);
		
		// gnb에 들어가는 로고 이미지 크기 조절(크기 조절한 아이콘을 아래 goToMain 라벨에 넣을 예정)
		ImageIcon icon = new ImageIcon(MyPage.class.getResource("/images/logo.png"));
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		// Main 로고 부분 JPanel로 구현		
		JPanel mainBtnWrapper = new JPanel();
		mainBtnWrapper.setBackground(Color.WHITE);
		mainBtnWrapper.setLayout(null);
		GridBagConstraints gbc_mainBtnWrapper = new GridBagConstraints();
		gbc_mainBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_mainBtnWrapper.insets = new Insets(0, 0, 5, 0);
		gbc_mainBtnWrapper.gridx = 0;
		gbc_mainBtnWrapper.gridy = 0;
		gnb.add(mainBtnWrapper, gbc_mainBtnWrapper);
		
		JButton mainBtn = new JButton("");
		mainBtn.setBackground(Color.WHITE);
		mainBtn.setIcon(updateIcon);
		mainBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(loginUser == null) {
            		return;
            	}
                showMainPage();
            }
        });
		mainBtn.setBounds(0, 0, 100, 100);
		mainBtnWrapper.add(mainBtn);
		
		JLabel mainName = new JLabel("북적북적");
		mainName.setForeground(new Color(153, 204, 0));
		mainName.setFont(new Font("HY헤드라인M", Font.PLAIN, 40));
		mainName.setBounds(110, 25, 196, 60);
		mainBtnWrapper.add(mainName);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JLabel temp1 = new JLabel("");
		GridBagConstraints gbc_temp1 = new GridBagConstraints();
		gbc_temp1.fill = GridBagConstraints.BOTH;
		gbc_temp1.insets = new Insets(0, 0, 5, 5);
		gbc_temp1.gridx = 1;
		gbc_temp1.gridy = 0;
		gnb.add(temp1, gbc_temp1);
		
		// sideBtnWrapper와 goToMain사이 빈 공간을 만들기 위한 Label임.
		JPanel temp2 = new JPanel();
		temp2.setBackground(Color.WHITE);
		temp2.setLayout(null);
		GridBagConstraints gbc_temp2 = new GridBagConstraints();
		gbc_temp2.fill = GridBagConstraints.BOTH;
		gbc_temp2.insets = new Insets(0, 0, 5, 0);
		gbc_temp2.gridx = 2;
		gbc_temp2.gridy = 0;
		gnb.add(temp2, gbc_temp2);
		
		
		// side button : goToQuizRegistration
		JButton goToQuizRegistration = new JButton("");
		goToQuizRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuizRegistration, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(loginUser.getUserType().equals("학생")) {
					JOptionPane.showMessageDialog(goToQuizRegistration, "교사만 이용 가능합니다.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				QuizRegistration quizRegistration = new QuizRegistration();
				quizRegistration.setVisible(true);
			}
		});
		goToQuizRegistration.setIcon(new ImageIcon(MyPage.class.getResource("/images/book.png")));
		goToQuizRegistration.setForeground(Color.WHITE);
		goToQuizRegistration.setBackground(Color.WHITE);
		goToQuizRegistration.setBounds(100, 0, 100, 100);
		icon = new ImageIcon(MyPage.class.getResource("/images/book.png"));
		img = icon.getImage();
		updateImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		updateIcon = new ImageIcon(updateImg);
		goToQuizRegistration.setIcon(updateIcon);
		temp2.add(goToQuizRegistration);
		
		
		// gnb의 side에 들어가는 로그인/마이페이지&로그아웃 버튼이 들어가는 JPanel
		
		sideBtnWrapper.setBackground(Color.WHITE);
		GridBagConstraints gbc_sideBtnWrapper = new GridBagConstraints();
		gbc_sideBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_sideBtnWrapper.insets = new Insets(0, 0, 5, 0);
		gbc_sideBtnWrapper.gridx = 3;
		gbc_sideBtnWrapper.gridy = 0;
		gnb.add(sideBtnWrapper, gbc_sideBtnWrapper);
		sideBtnWrapper.setLayout(null);

		userButton = new JButton();
        userButton.setBounds(0, 0, 180, 100);
        sideBtnWrapper.add(userButton);

        logoutButton = new JButton();
        logoutButton.setBounds(180, 0, 180, 100);
        sideBtnWrapper.add(logoutButton);
		
        updateLoginButtons();
		
		// gnb 하단에 들어가는 버튼들이 들어간 JPanel
		JPanel bottomBtnWrapper = new JPanel();
		bottomBtnWrapper.setBackground(Color.WHITE);
		bottomBtnWrapper.setLayout(null);
		GridBagConstraints gbc_bottomBtnWrapper = new GridBagConstraints();
		gbc_bottomBtnWrapper.gridwidth = 4;
		gbc_bottomBtnWrapper.fill = GridBagConstraints.BOTH;
		gbc_bottomBtnWrapper.insets = new Insets(0, 0, 0, 5);
		gbc_bottomBtnWrapper.gridx = 0;
		gbc_bottomBtnWrapper.gridy = 1;
		gnb.add(bottomBtnWrapper, gbc_bottomBtnWrapper);
		
		// 첫 번째 main button : goToQuiz
		JButton goToQuiz = new JButton("오늘의 퀴즈");
		goToQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuiz, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				TodayQuiz todayQuiz = new TodayQuiz();
				todayQuiz.setVisible(true);
			}
		});
		goToQuiz.setBackground(new Color(192, 236, 149));
		goToQuiz.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuiz.setBounds(10, 23, 221, 50);
		bottomBtnWrapper.add(goToQuiz);
		
		
		// 두 번째 main button : goToLearningCheck
		JButton goToLearningCheck = new JButton("학습 점검");
		goToLearningCheck.setBackground(new Color(192, 236, 149));
		
		goToLearningCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToLearningCheck, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				LearningCheck learningCheck = new LearningCheck();
				learningCheck.setVisible(true);
			}
		});
		
		goToLearningCheck.setFont(new Font("굴림", Font.PLAIN, 30));
		goToLearningCheck.setBounds(258, 23, 221, 50);
		bottomBtnWrapper.add(goToLearningCheck);
		
		// 세 번째 main button : goToBookMenu
		JButton goToBookMenu = new JButton("교과서 목록");
		goToBookMenu.setBackground(new Color(192, 236, 149));
		
		goToBookMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToBookMenu, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				BookMenu bookMenu = new BookMenu();
				bookMenu.setVisible(true);
			}
		});
		
		
		goToBookMenu.setFont(new Font("굴림", Font.PLAIN, 30));
		goToBookMenu.setBounds(512, 23, 221, 50);
		bottomBtnWrapper.add(goToBookMenu);
		
		// 네 번째 main button : goToQuizCaculator
		JButton goToQuizCalculator = new JButton("퀴즈 계산기");
		goToQuizCalculator.setFont(new Font("굴림", Font.PLAIN, 30));
		goToQuizCalculator.setBackground(new Color(192, 236, 149));
		goToQuizCalculator.setBounds(766, 23, 221, 50);
		
		goToQuizCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close(myPage);
				if(loginUser == null) {
					JOptionPane.showMessageDialog(goToQuizCalculator, "로그인 후 이용해주세요.", "이용 안내", JOptionPane.WARNING_MESSAGE);
					return;
				}
				QuizCalculator quizCalculator = new QuizCalculator();
				quizCalculator.setVisible(true);
			}
		});
		
		bottomBtnWrapper.add(goToQuizCalculator);
		
		//----------------------------------------------------------------------------------------------상단 gnb bar
		
		mainContentWrapper = new JPanel();
		mainContentWrapper.setBackground(Color.WHITE);
		mainContentWrapper.setLayout(null);
		mainContentWrapper.setPreferredSize(new Dimension(1200, 1000));
		
		scrollPane = new JScrollPane(mainContentWrapper);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		// Add image label
        imageLabel = new JLabel();
        imageLabel.setBounds(150, 50, 975, 350);
        setImage(imageIndex);
        mainContentWrapper.add(imageLabel);

        // Add left button
        JButton leftButton = new JButton("<");
        leftButton.setBounds(50, 200, 50, 50);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageIndex = (imageIndex - 1 + images.length) % images.length;
                setImage(imageIndex);
            }
        });
        mainContentWrapper.add(leftButton);

        // Add right button
        JButton rightButton = new JButton(">");
        rightButton.setBounds(1175, 200, 50, 50);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageIndex = (imageIndex + 1) % images.length;
                setImage(imageIndex);
            }
        });
        mainContentWrapper.add(rightButton);

        
        JPanel noticeWrapper = new JPanel();
        noticeWrapper.setBorder(new LineBorder(new Color(192, 236, 149), 2));
        noticeWrapper.setBackground(Color.WHITE);
        noticeWrapper.setBounds(50, 453, 602, 500);
        mainContentWrapper.add(noticeWrapper);
        noticeWrapper.setLayout(null);
        
        JLabel noticeContentTitle = new JLabel("학급 공지사항");
        noticeContentTitle.setFont(new Font("굴림", Font.BOLD, 35));
        noticeContentTitle.setBounds(47, 46, 360, 61);
        noticeWrapper.add(noticeContentTitle);
        
        noticeContentWrapper = new JPanel();
        noticeContentWrapper.setBounds(47, 127, 510, 350);
        noticeWrapper.add(noticeContentWrapper);
        
        if(loginUser == null) {
        	JLabel noticeLoginMessage = new JLabel("로그인 후 이용해주세요");
            noticeLoginMessage.setFont(new Font("굴림", Font.PLAIN, 25));
            noticeContentWrapper.add(noticeLoginMessage);
        }else {
        	
        }
        
        
        JPanel toDoWrapper = new JPanel();
        toDoWrapper.setBackground(new Color(192, 236, 149));
        toDoWrapper.setBounds(674, 453, 551, 500);
        mainContentWrapper.add(toDoWrapper);
        toDoWrapper.setLayout(null);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JLabel toDoContentTitle = new JLabel("오늘의 할 일");
        toDoContentTitle.setFont(new Font("굴림", Font.BOLD, 35));
        toDoContentTitle.setBounds(47, 46, 360, 61);
        toDoWrapper.add(toDoContentTitle);
        
        JPanel toDoContentWrapper = new JPanel();
        toDoContentWrapper.setBounds(47, 127, 455, 350);
        toDoWrapper.add(toDoContentWrapper);
        
        if(loginUser == null) {
        	JLabel toDoLoginMessage = new JLabel("로그인 후 이용해주세요");
            toDoLoginMessage.setFont(new Font("굴림", Font.PLAIN, 25));
            toDoContentWrapper.add(toDoLoginMessage);
        }

    }
	
	public void addNotice(Notice notice) {
        notices.add(0, notice); // 작성한 공지사항을 가장 위에 배치
        updateNotices();
    }

    private void updateNotices() {
          if (noticeContentWrapper == null) {
               // Just for safety, if noticeContentWrapper is somehow null, initialize it
               noticeContentWrapper = new JPanel();
               noticeContentWrapper.setLayout(new BoxLayout(noticeContentWrapper, BoxLayout.Y_AXIS));
           }

           noticeContentWrapper.removeAll();
           
           int displayCount = Math.min(notices.size(), 5);
           for (int i = 0; i < displayCount; i++) {
               Notice notice = notices.get(i);
               JLabel noticeLabel = new JLabel(notice.getTitle());
               noticeLabel.setFont(new Font("굴림", Font.PLAIN, 20));
               noticeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
               noticeLabel.addMouseListener(new MouseAdapter() {
                   @Override
                   public void mouseClicked(MouseEvent e) {
                       noticeManager.displayNoticeDetail(notice);
                   }
               });
               noticeContentWrapper.add(noticeLabel);
           }
           noticeContentWrapper.revalidate();
           noticeContentWrapper.repaint();
       }
	
		
	
	 public void updateLoginButtons() {
	        if (getLoginUser() == null) {
	            userButton.setText("로그인");
	            userButton.setBackground(Color.WHITE);
	            userButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : userButton.getActionListeners()) {
	                userButton.removeActionListener(al);
	            }
	            userButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    LoginForm loginForm = new LoginForm();
	                    loginForm.setVisible(true);
	                }
	            });
	            
	            logoutButton.setVisible(false);
	        } else {
	            userButton.setText(getLoginUser().getName() + "님");
	            userButton.setBackground(Color.WHITE);
	            userButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : userButton.getActionListeners()) {
	                userButton.removeActionListener(al);
	            } // 앞선 이벤트 리스터 삭제
	            userButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	if(loginUser != null) {
	                		displayMyPage();
	                	}
	                }
	            });
	            
	            logoutButton.setText("로그아웃");
	            logoutButton.setBackground(Color.WHITE);
	            logoutButton.setFont(new Font("굴림", Font.PLAIN, 30));
	            for (ActionListener al : logoutButton.getActionListeners()) {
	                logoutButton.removeActionListener(al);
	            }
	            logoutButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	// 순서 바꾸지 말 것
	                    showMainPage();
	                    setLoginUser(null);
	                    updateLoginButtons();
	                }
	            });
	            logoutButton.setVisible(true);
	        }
	        sideBtnWrapper.revalidate();
	        sideBtnWrapper.repaint();
	    }
	 
	 private void displayMyPage() {
	        getContentPane().remove(scrollPane);
	        MyPage myPage = MyPage.getInstance();
	        getContentPane().add(myPage, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	 }
	 
	 public void showMainPage() {
		 	MyPage myPage = MyPage.getInstance();
	        getContentPane().remove(myPage);
	        getContentPane().add(scrollPane, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	    }
	 
	 public static void deleteAccount() {
         if (loginUser != null) {
             UserDataSet.getUserDataSetInstance().deleteUser(loginUser.getUserId());
             performLogout();
         }
     }

     public static void performLogout() {
         if (mainPageInstance != null && mainPageInstance.logoutButton != null) {
             mainPageInstance.logoutButton.doClick();
     }
 }
}
