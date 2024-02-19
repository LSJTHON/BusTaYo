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
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ResetPassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_check;

	public ResetPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		passwordField = new RoundJPasswordField(20);
		passwordField.setBackground(SystemColor.control);
		passwordField.setFont(new Font("굴림", Font.PLAIN, 20));
		passwordField.setBounds(415, 150, 110, 25);
		contentPane.add(passwordField);
		
		passwordField_check = new RoundJPasswordField(20);
		passwordField_check.setBackground(SystemColor.control);
		passwordField_check.setFont(new Font("굴림", Font.PLAIN, 20));
		passwordField_check.setBounds(415, 180, 110, 25);
		contentPane.add(passwordField_check);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(325, 150, 80, 25);
		contentPane.add(passwordLabel);
		
		JLabel passwordLabel_check = new JLabel("비밀번호 확인");
		passwordLabel_check.setBounds(325, 180, 80, 25);
		contentPane.add(passwordLabel_check);
		
		JLabel resetTitle = new JLabel("비밀번호 재설정");
		resetTitle.setForeground(new Color(0, 128, 255));
		resetTitle.setFont(new Font("Dialog", Font.BOLD, 26));
		resetTitle.setBounds(325, 100, 200, 50);
		contentPane.add(resetTitle);
		
		JButton resetButton = new RoundJButton("재설정");
		resetButton.setBorderPainted(false);
		resetButton.setBackground(new Color(0, 128, 255));
		resetButton.setForeground(Color.WHITE);
		resetButton.setFont(new Font("굴림", Font.PLAIN, 20));
		resetButton.setBounds(325, 225, 200, 35);
		contentPane.add(resetButton);
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;  // Connection 변수를 try 블록 밖에서 선언
				if(passwordField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}else if(passwordField_check.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}else if(passwordField.getText().equals(passwordField_check.getText())) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");

						conn = DriverManager.getConnection("jdbc:mysql://222.119.100.89:3382/bustickets",
								"bustickets", "1234");

						// SQL 쿼리 실행
						String sql = "UPDATE users SET password = '" + passwordField.getText() + "' WHERE username = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, FindAccount.getUserId());
						pstmt.executeUpdate();
						
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
					JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.", "", 3);
					loginForm frame = new loginForm();
					frame.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
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

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPassword frame = new ResetPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
