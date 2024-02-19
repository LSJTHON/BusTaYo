package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FindAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxtField;
	private JTextField emailTxtField;
	private JLabel idLabel;
	private JLabel emailLabel;
	private JLabel findAccountLabel;
	private static String userId;
	private String userEmail = null;
	private JButton btnNewButton;

	public FindAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		idTxtField = new RoundJTextField(20);
		idTxtField.setBackground(SystemColor.control);
		idTxtField.setBounds(385, 150, 140, 25);
		idTxtField.setColumns(10);
		contentPane.add(idTxtField);

		emailTxtField = new RoundJTextField(20);
		emailTxtField.setBackground(SystemColor.control);
		emailTxtField.setBounds(385, 180, 140, 25);
		emailTxtField.setColumns(10);
		contentPane.add(emailTxtField);

		idLabel = new JLabel("아이디");
		idLabel.setBounds(325, 150, 50, 25);
		contentPane.add(idLabel);

		emailLabel = new JLabel("이메일");
		emailLabel.setBounds(325, 180, 50, 25);
		contentPane.add(emailLabel);

		findAccountLabel = new JLabel("계정 찾기");
		findAccountLabel.setForeground(new Color(0, 128, 255));
		findAccountLabel.setFont(new Font("Dialog", Font.BOLD, 34));
		findAccountLabel.setBounds(325, 90, 200, 50);
		contentPane.add(findAccountLabel);
		
		btnNewButton = new RoundJButton("뒤로가기");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(739, 10, 87, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginForm frame = new loginForm();
				frame.setVisible(true);
				dispose();
			}
		});

		JButton checkEmailNum = new RoundJButton("인증번호 받기");
		checkEmailNum.setBorderPainted(false);
		checkEmailNum.setBackground(new Color(0, 128, 255));
		checkEmailNum.setForeground(Color.WHITE);
		checkEmailNum.setBounds(325, 225, 200, 35);
		contentPane.add(checkEmailNum);
		contentPane.add(btnNewButton);
		checkEmailNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;  // Connection 변수를 try 블록 밖에서 선언
				
				if (idTxtField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Id를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
				} else if (emailTxtField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");

						conn = DriverManager.getConnection("jdbc:mysql://222.119.100.89:3382/bustickets",
								"bustickets", "1234");

						// SQL 쿼리 실행
						String sql = "SELECT username, email FROM users WHERE username = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, idTxtField.getText());
						ResultSet rs = pstmt.executeQuery();
						if (!rs.next()) {
							// 일치하는 아이디가 없을 때
							JOptionPane.showMessageDialog(null, "일치하는 아이디가 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
						} else {
							// 일치하는 아이디가 있을 때
							setUserId(rs.getString("username"));
							userEmail = rs.getString("email");
							if (emailTxtField.getText().equals(userEmail)) {
								JOptionPane.showMessageDialog(null, "인증번호를 보냈습니다.", "", 3);
								InputCheckEmailNum frame = new InputCheckEmailNum();
								frame.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "이메일 불일치", "", 3);
							}
						}
					} catch (ClassNotFoundException | SQLException ex) {
						ex.printStackTrace();
					} finally {
						try {
							if(conn!=null) {
								conn.close();
							}
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		FindAccount.userId = userId;
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindAccount frame = new FindAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
