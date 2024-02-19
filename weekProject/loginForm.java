package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class loginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPasswordField passwordField;
	private JTextField textField;
	private JTextField txtLogin;

	// 메인 실행창
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm frame = new loginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 로그인 폼
	public loginForm() {
		contentPane = new JPanel(); // 필드에 정의된 변수
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // contentPane에 대한 외각 여백을 지정하는 부분
		

		setContentPane(contentPane); // 현제 프레임의 내용으로 사용될 패널을 contentPane으로 지정
		contentPane.setLayout(null); // 해당 패널의 레이아웃 매니저를 null로 설정하여 컴포넌트들의 위치와 크기를 직접 지정 가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 왼쪽 상단 닫기버튼
		setBounds(100, 100, 850, 450); // 위치 좌표로 지정, 창 크기 지정
		setLocationRelativeTo(null);

		
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(contentPane) ;
		}catch(Exception e){
			System.out.println("LookAndFeel 설정 실패");
		}
		
		//RoundJButton btnNewButton = new RoundJButton("로그인"); // 버튼 텍스트 지정
		JButton btnNewButton = new RoundedButton("로그인"); // 버튼 텍스트 지정
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setOpaque(true);
		btnNewButton.setFocusPainted(false);
		
		contentPane.add(btnNewButton); // 버튼 추가
//		btnNewButton.setBackground(new Color(0, 128, 255));
//		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(337, 223, 191, 23);

		// 버튼을 클릭할 경우 실행하는 부분
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {// 로그인 메소드 실행
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		RoundJButton btnNewButton_1 = new RoundJButton("계정 찾기");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(0, 128, 255));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(147, 224, 87, 23);
		contentPane.add(btnNewButton_1); // 버튼 추가
		btnNewButton_1.setBounds(337, 256, 87, 23);

		RoundJButton btnNewButton_2 = new RoundJButton("회원가입");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBackground(new Color(0, 128, 255));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBounds(251, 224, 87, 23);
		contentPane.add(btnNewButton_2); // 버튼 추가
		btnNewButton_2.setBounds(441, 256, 87, 23);
		
		// "회원가입" 버튼에 대한 ActionListener 추가
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        openSignUpForm(); // 회원가입 폼 열기
		    }
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        FindAccount jFrame1234 = new FindAccount();
		        jFrame1234.setVisible(true);
		        dispose();
		    }
		});

		passwordField = new RoundJPasswordField(20);
		passwordField.setBackground(SystemColor.control);
		contentPane.add(passwordField); // 비밀번호 필드 추가
		passwordField.setText("****"); // 비밀번호 초기 입력값
		passwordField.setBounds(337, 181, 191, 21);
		passwordField.setForeground(Color.GRAY);
		
		// 비밀번호 포커스 이벤트
		passwordField.addFocusListener(new FocusAdapter() {
			
			// 포커스를 얻었을때 실행
			@Override
			public void focusGained(FocusEvent e) {
				// "****"가 입력되어 있다면 공백으로 바꿔줌
				if (String.valueOf(passwordField.getPassword()).equals("****")) {
					passwordField.setText("");
					passwordField.setForeground(Color.GRAY);
				}
			}

			// 포커스를 잃었을때 실행
			@Override
			public void focusLost(FocusEvent e) {
				// 빈칸이라면 "****"로 바꿔줌
				if (String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setText("****");
					passwordField.setForeground(Color.GRAY);
				}
			}
		});

		textField = new RoundJTextField(20);
		textField.setBackground(SystemColor.control);
		contentPane.add(textField); // 텍스트 필드 추가
		textField.setText("아이디를 입력하세요."); // 텍스트 필드 초기값
		textField.setBounds(337, 150, 191, 21);
		textField.setForeground(Color.GRAY);
		//textField.setColumns(10);
		txtLogin = new JTextField("Login") {
			@Override
			public void setBorder(Border border) { // 테두리 없음
			}
		};
		txtLogin.setBackground(Color.white);
		//txtLogin.setHorizontalAlignment(SwingConstants.LEFT);
		txtLogin.setEditable(false);
		txtLogin.setForeground(new Color(0, 128, 255));
		txtLogin.setFont(new Font("Dialog", Font.BOLD, 34));
		txtLogin.setBounds(337, 80, 191, 77);
		txtLogin.setColumns(10);
		contentPane.add(txtLogin);
		// 포커스 이벤트
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals("아이디를 입력하세요.")) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText("아이디를 입력하세요.");
					textField.setForeground(Color.GRAY);
				}
			}
		});
		// 처음 실행할 시 패스워드에 포커스를 둠
		//SwingUtilities.invokeLater(() -> passwordField.requestFocusInWindow());
	}
	// 로그인 메소드
	private void login() throws ClassNotFoundException, SQLException {
		String userId = textField.getText();
		char[] userPw = passwordField.getPassword();
		
		BusDatabase busDataBase = new BusDatabase();
		String pw =  new String(passwordField.getPassword());
		busDataBase.dataBaseLogin(userId,userPw,pw);
		dispose();
	}
	public void openReservationPage() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FORM frame = new FORM();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	// loginForm 클래스에 추가할 openSignUpForm 메소드
	private void openSignUpForm() {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                SignUpFrame signUpFrame = new SignUpFrame();
	                signUpFrame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	
	public class RoundJButton extends JButton {
	    private Shape shape;

	    public RoundJButton(String label) {
	        super(label);
	        setOpaque(false); // 투명 배경 설정
	    }

	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // 15는 둥근 경계의 반지름
	        super.paintComponent(g);
	    }

	    protected void paintBorder(Graphics g) {
	        // 아무것도 그리지 않음 (테두리를 그리지 않음)
	    }

	    public boolean contains(int x, int y) {
	        if (shape == null || !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	        }
	        return shape.contains(x, y);
	    }
	}
	
	public class RoundJPasswordField extends JPasswordField {
	    private Shape shape;

	    public RoundJPasswordField(int size) {
	        super(size);
	        setOpaque(false); // 투명 배경 설정
	    }

	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10); // 15는 둥근 경계의 반지름
	        super.paintComponent(g);
	    }
	    
	    protected void paintBorder(Graphics g) {
	        // 아무것도 그리지 않음 (테두리를 그리지 않음)
	    	
	    }


	    public boolean contains(int x, int y) {
	        if (shape == null || !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	        }
	        return shape.contains(x, y);
	    }
	}
	public class RoundJTextField extends JTextField {
	    private Shape shape;

	    public RoundJTextField(int size) {
	        super(size);
	        setOpaque(false); // 투명 배경 설정
	    }

	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10); // 15는 둥근 경계의 반지름
	        super.paintComponent(g);
	    }

	    protected void paintBorder(Graphics g) {
	        // 아무것도 그리지 않음 (테두리를 그리지 않음)
	    }
	    
	    public boolean contains(int x, int y) {
	        if (shape == null || !shape.getBounds().equals(getBounds())) {
	            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	        }
	        return shape.contains(x, y);
	    }
	}
}
