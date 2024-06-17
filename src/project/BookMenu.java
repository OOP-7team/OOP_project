package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class BookMenu extends JFrame{

   private static final long serialVersionUID = 1L;
 
   private JFrame frame;


   /**
    * Create the application.
    */
   public BookMenu() {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      // JFrame의 기본 속성을 설정
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Point centerPoint = ge.getCenterPoint();
//        int leftTopX = centerPoint.x - 450 / 2;
//        int leftTopY = centerPoint.y - 300 / 2;
//        setLocation(leftTopX, leftTopY);
//        getContentPane().setLayout(new BorderLayout(0, 0));
//        setBounds(100, 100, 1300, 800);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosed(WindowEvent e) {
//                super.windowClosed(e);
//            }
//        });
//        setVisible(true);
//        
      // JFrame의 기본 속성을 설정
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int leftTopX = centerPoint.x - 450 / 2;
        int leftTopY = centerPoint.y - 300 / 2;
        setLocation(leftTopX, leftTopY);
        setBounds(100, 100, 1300, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프레임 닫을 때만 종료
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
      
      JPanel content = new JPanel();
      content.setBackground(Color.WHITE);
      //getContentPane().add(content, BorderLayout.CENTER);
      content.setLayout(null);
      content.setPreferredSize(new java.awt.Dimension(1280, 1500));
      
      // 스크롤
      // Create a JScrollPane and add the content panel to it
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
      
      JLabel contentTitle = new JLabel("> 전체 교과서 목록");
      contentTitle.setFont(new Font("굴림", Font.PLAIN, 30));
      contentTitle.setBounds(40, 24, 305, 35);
      content.add(contentTitle);
   
      //교과서 1번 
      JButton textbook1 = new JButton();
      textbook1.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Kim.jpg")));
      textbook1.setBounds(96, 69, 125, 176);
      content.add(textbook1);
      
      textbook1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 열고자 하는 URL
                    URI uri = new URI("https://webdt.edunet.net/url/u8dj7iqnfzua");
                    // 데스크탑 객체 생성
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        if (desktop.isSupported(Desktop.Action.BROWSE)) {
                            desktop.browse(uri);
                        }
                    }
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
      JLabel textbookLabel1 = new JLabel("초등 3-1 과학 (김영사)");
      textbookLabel1.setHorizontalAlignment(SwingConstants.CENTER);
      textbookLabel1.setFont(new Font("굴림", Font.PLAIN, 20));
      textbookLabel1.setBounds(53, 255, 180, 25);
      content.add(textbookLabel1);
      
      JButton addbook1 = new JButton("담기");   /*나의 교과서로 담기*/
      addbook1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
             BookState bookState1 = BookState.getInstance();
              if (!bookState1.addBook1()) {
                   // 오류 메시지 표시
                   JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               // 새로운 버튼 나의 교과서에 생성
               JButton addMybook1 = new JButton();
               addMybook1.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Kim.jpg")));
               addMybook1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      try {                 
                          URI uri = new URI("https://webdt.edunet.net/url/u8dj7iqnfzua");
                          if (Desktop.isDesktopSupported()) {
                              Desktop desktop = Desktop.getDesktop();
                              if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                  desktop.browse(uri);
                              }
                          }
                      } catch (IOException | URISyntaxException ex) {
                          ex.printStackTrace();
                      }
                  }
              });
               
               MyPage.myBook.add(addMybook1); 
               // 패널의 레이아웃을 새로고침
               MyPage.myBook.revalidate();
               MyPage.myBook.repaint();  
           }
       });

      addbook1.setBounds(232, 256, 68, 26);
      content.add(addbook1);
         
         
         //교과서 2번
         JButton textbook2 = new JButton();
         textbook2.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Mirae.jpg")));
         textbook2.setBounds(405, 69, 125, 176);
         content.add(textbook2);
         
         textbook2.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/1iuys0sx16l9y");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel2 = new JLabel("초등 3-1 과학 (미래엔)");
         textbookLabel2.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel2.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel2.setBounds(382, 254, 180, 25);
         content.add(textbookLabel2);
         
         JButton addbook2 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook2.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState2 = BookState.getInstance();
                 if (!bookState2.addBook2()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook2 = new JButton();
                  addMybook2.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Mirae.jpg")));
                  addMybook2.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/1iuys0sx16l9y");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook2); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook2.setBounds(561, 255, 68, 26);
         content.add(addbook2);
         
         //교과서 3번
         JButton textbook3 = new JButton();
         textbook3.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Icecream.jpg")));
         textbook3.setBounds(705, 69, 125, 176);
         content.add(textbook3);
         
         textbook3.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/1hrucp2h79flc");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel3 = new JLabel("초등 3-1 과학 (아이스크림)");
         textbookLabel3.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel3.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel3.setBounds(683, 254, 180, 25);
         content.add(textbookLabel3);
         
         JButton addbook3 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook3.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState3 = BookState.getInstance();
                 if (!bookState3.addBook3()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook3 = new JButton();
                  addMybook3.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Icecream.jpg")));
                  addMybook3.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/1hrucp2h79flc");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook3); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook3.setBounds(862, 256, 68, 26);
         content.add(addbook3);
         
         //교과서 4번
         JButton textbook4 = new JButton();
         textbook4.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Jihak.jpg")));
         textbook4.setBounds(96, 301, 125, 176);
         content.add(textbook4);
         
         textbook4.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/1hrucp2h79flc");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel4 = new JLabel("초등 3-1 과학 (지학사)");
         textbookLabel4.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel4.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel4.setBounds(72, 487, 180, 25);
         content.add(textbookLabel4);
         
         JButton addbook4 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook4.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState4 = BookState.getInstance();
                 if (!bookState4.addBook4()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook4 = new JButton();
                  addMybook4.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Jihak.jpg")));
                  addMybook4.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/1hrucp2h79flc");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook4); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook4.setBounds(232, 488, 68, 26);
         content.add(addbook4);
         
         //교과서 5번
         JButton textbook5 = new JButton();
         textbook5.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Cheonjae.jpg")));
         textbook5.setBounds(405, 301, 125, 176);
         content.add(textbook5);
         
         textbook5.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/1hv5i821h3ubd");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel5 = new JLabel("초등 과학 3-1 (천재)");
         textbookLabel5.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel5.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel5.setBounds(382, 487, 180, 23);
         content.add(textbookLabel5);
         
         JButton addbook5 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook5.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState5 = BookState.getInstance();
                 if (!bookState5.addBook5()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook5 = new JButton();
                  addMybook5.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-1_Cheonjae.jpg")));
                  addMybook5.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/1hv5i821h3ubd");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook5); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook5.setBounds(561, 488, 68, 26);
         content.add(addbook5);
         
         JScrollPane scrollPane_1 = new JScrollPane();
         scrollPane_1.setBounds(0, 0, 2, 2);
         content.add(scrollPane_1);
         
         //교과서 6번
         JButton textbook6 = new JButton();
         textbook6.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Kim.jpg")));
         textbook6.setBounds(705, 301, 125, 176);
         content.add(textbook6);
         
         textbook6.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/rhdti84noe9i");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         
         JLabel textbookLabel6 = new JLabel("초등 3-2 과학 (김영사)");
         textbookLabel6.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel6.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel6.setBounds(683, 487, 180, 25);
         content.add(textbookLabel6);
         
         JButton addbook6 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook6.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState6 = BookState.getInstance();
                 if (!bookState6.addBook6()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook6 = new JButton();
                  addMybook6.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Kim.jpg")));
                  addMybook6.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/rhdti84noe9i");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook6); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook6.setBounds(862, 488, 68, 26);
         content.add(addbook6);
         
         //교과서 7번
         JButton textbook7 = new JButton();
         textbook7.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Dong.png")));
         textbook7.setBounds(96, 551, 125, 176);
         content.add(textbook7);
         
         textbook7.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/vbq8amb3ho3d");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel7 = new JLabel("초등 3-2 과학 (동아)");
         textbookLabel7.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel7.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel7.setBounds(72, 737, 180, 25);
         content.add(textbookLabel7);
         
         JButton addbook7 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook7.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState7 = BookState.getInstance();
                 if (!bookState7.addBook7()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook7 = new JButton();
                  addMybook7.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Dong.png")));
                  addMybook7.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("\"https://webdt.edunet.net/url/vbq8amb3ho3d");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook7); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook7.setBounds(232, 738, 68, 26);
         content.add(addbook7);
         
         //교과서 8번
         JButton textbook8 = new JButton();
         textbook8.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Jihak.jpg")));
         textbook8.setBounds(405, 551, 125, 176);
         content.add(textbook8);
         
         textbook8.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/t4ner5vk9fsg");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel8 = new JLabel("초등 3-2 과학 (지학사)");
         textbookLabel8.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel8.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel8.setBounds(382, 737, 180, 25);
         content.add(textbookLabel8);
         
         JButton addbook8 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook8.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState8 = BookState.getInstance();
                 if (!bookState8.addBook8()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook8 = new JButton();
                  addMybook8.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Jihak.jpg")));
                  addMybook8.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/t4ner5vk9fsg");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook8); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook8.setBounds(561, 738, 68, 26);
         content.add(addbook8);
         
         //교과서 9번
         JButton textbook9 = new JButton();
         textbook9.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Cheonjae.jpg")));
         textbook9.setBounds(705, 551, 125, 176);
         content.add(textbook9);

         textbook9.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // 열고자 하는 URL
                       URI uri = new URI("https://webdt.edunet.net/url/s50ni8607i9d");
                       // 데스크탑 객체 생성
                       if (Desktop.isDesktopSupported()) {
                           Desktop desktop = Desktop.getDesktop();
                           if (desktop.isSupported(Desktop.Action.BROWSE)) {
                               desktop.browse(uri);
                           }
                       }
                   } catch (IOException | URISyntaxException ex) {
                       ex.printStackTrace();
                   }
               }
           });
         
         JLabel textbookLabel9 = new JLabel("초등 3-2 과학 (천재)");
         textbookLabel9.setHorizontalAlignment(SwingConstants.CENTER);
         textbookLabel9.setFont(new Font("굴림", Font.PLAIN, 20));
         textbookLabel9.setBounds(683, 737, 180, 25);
         content.add(textbookLabel9);
         
         JButton addbook9 = new JButton("담기");   /*나의 교과서로 담기*/
         addbook9.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 BookState bookState9 = BookState.getInstance();
                 if (!bookState9.addBook9()) {
                      // 오류 메시지 표시
                      JOptionPane.showMessageDialog(null, "이미 담긴 교과서입니다", "오류", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  // 새로운 버튼 나의 교과서에 생성
                  JButton addMybook9 = new JButton();
                  addMybook9.setIcon(new ImageIcon(BookMenu.class.getResource("/images/textbook/science_3-2_Cheonjae.jpg")));
                  addMybook9.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         try {                 
                             URI uri = new URI("https://webdt.edunet.net/url/s50ni8607i9d");
                             if (Desktop.isDesktopSupported()) {
                                 Desktop desktop = Desktop.getDesktop();
                                 if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                     desktop.browse(uri);
                                 }
                             }
                         } catch (IOException | URISyntaxException ex) {
                             ex.printStackTrace();
                         }
                     }
                 });
                  
                  MyPage.myBook.add(addMybook9); 
                  // 패널의 레이아웃을 새로고침
                  MyPage.myBook.revalidate();
                  MyPage.myBook.repaint();
              }
          });

         addbook9.setBounds(862, 738, 68, 26);
         content.add(addbook9);
      
      
        
   }
}
