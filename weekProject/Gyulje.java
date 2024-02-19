package weekProject;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Gyulje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	ImageIcon a1 = new ImageIcon(Gyulje.class.getResource("image/nhAd.jpg"));
	ImageIcon a2 = new ImageIcon(Gyulje.class.getResource("image/loa.jpg"));
	ImageIcon a3 = new ImageIcon(Gyulje.class.getResource("image/rize.jpg"));
	ImageIcon a4 = new ImageIcon(Gyulje.class.getResource("image/lol3.jpg"));
	ImageIcon a5 = new ImageIcon(Gyulje.class.getResource("image/loveWinsAll.jpg"));

	Image a1Img = a1.getImage();
	Image a2Img = a2.getImage();
	Image a3Img = a3.getImage();
	Image a4Img = a4.getImage();
	Image a5Img = a5.getImage();

	int a1X = 0;
	int a2X = a1Img.getWidth(null);
	int a3X = a1Img.getWidth(null) + a2Img.getWidth(null);
	int a4X = a1Img.getWidth(null) + a2Img.getWidth(null) + a3Img.getWidth(null);
	int a5X = a1Img.getWidth(null) + a2Img.getWidth(null) + a3Img.getWidth(null) + a4Img.getWidth(null);

//	public static void main(String[] args) {
//		EventQueue.invokeLater(() -> {
//			try {
//				Gyulje frame = new Gyulje();
//				frame.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//	}

	public Gyulje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 400);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 결제 버튼
		JButton btnGyejoa = new JButton("계좌결제");
		btnGyejoa.setForeground(new Color(0, 128, 255));
		btnGyejoa.setIcon(new ImageIcon(Gyulje.class.getResource("image/Cash.png")));
		btnGyejoa.setFont(new Font("여기어때 잘난체 2 TTF", Font.BOLD, 35));
		btnGyejoa.setBorder(null);
		btnGyejoa.setBackground(null);
		btnGyejoa.setFocusPainted(false);
		btnGyejoa.setBounds(96, 291, 300, 70);
		contentPane.add(btnGyejoa);

		// 결제 버튼 클릭 시
		btnGyejoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 결제 화면으로 이동하는 코드
				// 계좌결제 1로 넘겨주는 코드
				Gyejoa_Gyulje frame = new Gyejoa_Gyulje();
				frame.setVisible(true); // Gyejoa_Gyulje1 창을 보이도록 설정
				dispose(); // 현재 창 닫기
			}
		});

		// 카드 결제 버튼
		JButton btnCard = new JButton("카드결제");
		btnCard.setForeground(new Color(0, 128, 255));
		btnCard.setIcon(new ImageIcon(Gyulje.class.getResource("image/Card.png")));
		btnCard.setFont(new Font("여기어때 잘난체 2 TTF", Font.BOLD, 35));
		btnCard.setBorder(null);
		btnCard.setBackground(null);
		btnCard.setFocusPainted(false);
		btnCard.setBounds(416, 291, 300, 70);
		contentPane.add(btnCard);

		// 카드 결제 버튼 클릭 시
		btnCard.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 기존 코드 생략
				// 카드결제 1로 넘겨주는 코드
				Card_Gyulje frame = new Card_Gyulje();
				frame.setVisible(true); // Gyejoa_Gyulje1 창을 보이도록 설정
				dispose();

			}
		});

		// 뒤로가기 버튼
		JButton backButton = new JButton("");
		backButton.setIcon(new ImageIcon(Gyulje.class.getResource("image/Arrow.png")));
		backButton.setForeground(new Color(60, 157, 255));
		backButton.setBorder(null);
		backButton.setBackground(null);
		backButton.setFocusPainted(false);
		backButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		backButton.setBounds(726, 7, 50, 30);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//좌석 선택으로 다시 넘겨주는 이벤트
				BusReservation nRserVation = new BusReservation();
				nRserVation.setVisible(true);
				dispose();
			}
		});
		
		contentPane.add(backButton);
		
		
		// 홈 버튼
		JButton homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(Gyulje.class.getResource("image/home.png")));
		homeButton.setForeground(new Color(60, 157, 255));
		homeButton.setBorder(null);
		homeButton.setBackground(null);
		homeButton.setFocusPainted(false);
		homeButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		homeButton.setBounds(774, 7, 50, 30);
		contentPane.add(homeButton);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); // 캔버스를 비워주는 메서드
				g.drawImage(a1Img, a1X, 0, this);
				g.drawImage(a2Img, a2X, 0, this);
				g.drawImage(a3Img, a3X, 0, this);
				g.drawImage(a4Img, a4X, 0, this);
				g.drawImage(a5Img, a5X, 0, this);
			}
		};
		panel.setBounds(15, 55, 810, 230);
		contentPane.add(panel);
		panel.setLayout(null);

		
		//"C:\\Users\\codepc\\eclipse-workspace\\jwshin\\src\\jwshin\\image\\tayo.jpg") 원본
		JLabel lblNewLabel = new JLabel("창원시외버스터미널");
		lblNewLabel
				.setIcon(new ImageIcon(Gyulje.class.getResource("image/tayo.jpg")));
		lblNewLabel.setForeground(new Color(0, 128, 255));
		lblNewLabel.setFont(new Font("여기어때 잘난체 2 TTF", Font.BOLD, 35));
		lblNewLabel.setBounds(15, 7, 428, 40);
		contentPane.add(lblNewLabel);

		// 이미지를 움직이는 부분을 Timer를 사용하여 처리합니다.
		Timer timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				a1X--;
				a2X--;
				a3X--;
				a4X--;
				a5X--;

				if (a1X < -a1Img.getWidth(null)) {
					a1X = a5X + a5Img.getWidth(null);
				}
				if (a2X < -a2Img.getWidth(null)) {
					a2X = a1X + a1Img.getWidth(null);
				}
				if (a3X < -a3Img.getWidth(null)) {
					a3X = a2X + a2Img.getWidth(null);
				}
				if (a4X < -a4Img.getWidth(null)) {
					a4X = a3X + a3Img.getWidth(null);
				}
				if (a5X < -a5Img.getWidth(null)) {
					a5X = a4X + a4Img.getWidth(null);
				}

				repaint();
			}
		});
		timer.start();
	}
}