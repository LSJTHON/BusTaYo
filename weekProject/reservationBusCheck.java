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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import weekProject.InputCheckEmailNum.RoundJButton;
import weekProject.InputCheckEmailNum.RoundJPasswordField;

public class reservationBusCheck extends JFrame {

	private JPanel contentPane;

	public reservationBusCheck() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 400);
		setLocationRelativeTo(null);

		setContentPane(contentPane);

		JLabel busCheckLabel = new JLabel("예매번호 입력");
		busCheckLabel.setForeground(new Color(0, 128, 255));
		busCheckLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		busCheckLabel.setBounds(315, 100, 220, 30);
		contentPane.add(busCheckLabel);

		RoundJTextField busCheckNum = new RoundJTextField(20);
		busCheckNum.setBackground(SystemColor.control);
		busCheckNum.setFont(new Font("굴림", Font.PLAIN, 20));
		busCheckNum.setBounds(315, 153, 220, 30);
		contentPane.add(busCheckNum);
		busCheckNum.setColumns(10);

		JButton busCheckButton = new RoundJButton("확인");
		busCheckButton.setBorderPainted(false);
		busCheckButton.setBackground(new Color(0, 128, 255));
		busCheckButton.setForeground(Color.WHITE);
		busCheckButton.setFont(new Font("굴림", Font.PLAIN, 20));
		busCheckButton.setBounds(356, 208, 138, 38);
		contentPane.add(busCheckButton);

		RoundJButton backButton = new RoundJButton("뒤로가기");
		backButton.setBorderPainted(false);
		backButton.setBackground(new Color(0, 128, 255));
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("굴림", Font.PLAIN, 12));
		backButton.setBounds(739, 10, 87, 23);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FORM frame = new FORM();
				frame.setVisible(true);
				dispose();
			}
		});
		
		busCheckButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null; // Connection 변수를 try 블록 밖에서 선언
				if (busCheckNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "예매번호를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					conn = DriverManager.getConnection("jdbc:mysql://222.119.100.89:3382/bustickets", "bustickets",
							"1234");

					// SQL 쿼리 실행
					String sql = "select * from reservation where bookcode = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, busCheckNum.getText());
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "버스 정보를 조회하겠습니다.", "", 3);
						System.out.println(rs.getString("nowdate"));
						System.out.println(rs.getInt("bustime"));
						System.out.println(rs.getString("busclass"));
						System.out.println(rs.getString("endregion"));
						System.out.println(rs.getString("region"));
						System.out.println(rs.getString("bookcode"));
						System.out.println(rs.getString("myseat"));
						BusClassInfo.selectEndRegion =rs.getString("endregion");
						BusClassInfo.bus1 = rs.getString("busclass");
						BusClassInfo.selectDate = rs.getString("nowdate");
						BusClassInfo.selectTime = Integer.toString(rs.getInt("bustime"));
						BusClassInfo.ticketCode =rs.getString("bookcode");
						BusClassInfo.searchSeats = rs.getString("myseat");
						
						ResultTicket rt = new ResultTicket();
						rt.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "예매번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
						
					}
				} catch (ClassNotFoundException | SQLException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservationBusCheck frame = new reservationBusCheck();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
