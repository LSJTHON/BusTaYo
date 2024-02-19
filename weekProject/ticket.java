package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ticket extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ticket frame = new ticket();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public ticket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(450, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setToolTipText("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8
				.setIcon(new ImageIcon(Gyulje.class.getResource("image/arrow3.png")));
		lblNewLabel_8.setBackground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(202, 287, 30, 30);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_1_1 = new JLabel("1/1");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(381, 2, 50, 30);
		contentPane.add(lblNewLabel_1_1);

		JButton btnSave = new JButton("승차권 저장");
		btnSave.setBackground(new Color(0, 0, 64));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 10));
		btnSave.setBounds(339, 50, 90, 20);
		btnSave.setFocusPainted(false); // 포커스 표시 제거
		btnSave.setBorder(null);

		contentPane.add(btnSave);

		JLabel lblNewLabel = new JLabel("출발일시");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(35, 23, 50, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(BusClassInfo.selectDate+" "+BusClassInfo.selectTime+"시");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(35, 42, 250, 30);
		contentPane.add(lblNewLabel_1);

		
		
		
		UniqueNumberGenerator ddd = new UniqueNumberGenerator();
		BusClassInfo.ticketCode = ddd.generateUniqueNumber();
		JTextField busReservationNum = new JTextField(BusClassInfo.ticketCode);
		busReservationNum.setHorizontalAlignment(SwingConstants.CENTER);
		busReservationNum.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		busReservationNum.setBounds(80, 186, 280, 50);
		busReservationNum.setBorder(null);
		contentPane.add(busReservationNum);

		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("출발지");
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2.setBounds(47, 252, 50, 20);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("출발지");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(238, 252, 50, 20);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_4 = new JLabel("<html>창원</html>");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(57, 277, 120, 70);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel(BusClassInfo.selectEndRegion);
		lblNewLabel_4_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 25));
		lblNewLabel_4_1.setBounds(248, 277, 120, 70);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_2_2_1 = new JLabel("등급");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_2_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_2_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(67, 371, 50, 20);
		contentPane.add(lblNewLabel_2_2_1);

		JLabel lblNewLabel_4_2_1 = new JLabel(BusClassInfo.bus1);
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_4_2_1.setBounds(51, 383, 85, 40);
		contentPane.add(lblNewLabel_4_2_1);

		JLabel lblNewLabel_2_2_3 = new JLabel("좌석번호");
		lblNewLabel_2_2_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_2_3.setForeground(new Color(0, 128, 255));
		lblNewLabel_2_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2_2_3.setBounds(242, 371, 50, 20);
		contentPane.add(lblNewLabel_2_2_3);

		
		
		
		
		//좌석번호 예매 표에 띄어주기
		
		String str = BusClassInfo.resultSeatNum.toString().replaceAll("[^0-9 ]","");
		JLabel lblNewLabel_4_2_3 = new JLabel(str);
		lblNewLabel_4_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_4_2_3.setBounds(226, 386, 80, 40);
		contentPane.add(lblNewLabel_4_2_3);

		
		
		
		
		
		
		
	
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5
				.setIcon(new ImageIcon(Gyulje.class.getResource("image/ticket.png")));
		lblNewLabel_5.setBounds(344, 3, 30, 30);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6
				.setIcon(new ImageIcon(Gyulje.class.getResource("image/tayo1.jpg")));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(178, 98, 80, 80);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("창원시외버스터미널");
		lblNewLabel_7
				.setIcon(new ImageIcon(Gyulje.class.getResource("image/tayo.jpg")));
		lblNewLabel_7.setForeground(new Color(48, 128, 207));
		lblNewLabel_7.setFont(new Font("여기어때 잘난체 2 TTF", Font.BOLD, 30));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(20, 492, 400, 50);
		contentPane.add(lblNewLabel_7);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_3.setBackground(new Color(48, 128, 207));
		panel_3.setBounds(0, 0, 435, 81);
		contentPane.add(panel_3);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(29, 246, 190, 115);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(219, 246, 190, 115);
		contentPane.add(panel_1);
		
		JButton mainbutton = new RoundedButton("메 인 으 로");
		mainbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FORM mainform = new FORM();
				mainform.setVisible(true);
				dispose();
			}
		});
		mainbutton.setBounds(140, 550, 150, 50);
		contentPane.add(mainbutton);
		mainbutton.setContentAreaFilled(false);
		mainbutton.setForeground(Color.WHITE);
		mainbutton.setBackground(new Color(0, 128, 255));
		mainbutton.setOpaque(true);
		mainbutton.setFocusPainted(false);
		mainbutton.setFont(new Font("맑은 고딕", Font.BOLD, 20));

	}
}