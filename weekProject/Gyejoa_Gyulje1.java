package weekProject;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Gyejoa_Gyulje1 extends JFrame {

	private static final long serialVersionUID = 2_1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textBank;
	private JTextField textBankUser;
	private JTextField textGongBack;
	private JTextField textBankNum;
	private JTextField textSulmyeong;
	private JTextField textBankPW;

	public Gyejoa_Gyulje1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(850, 400);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("TAYO결제서비스");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		lblNewLabel_1.setBounds(20, 20, 465, 45);
		contentPane.add(lblNewLabel_1);

		JSeparator separator_2_1_3 = new JSeparator();
		separator_2_1_3.setBackground(new Color(128, 128, 128));
		separator_2_1_3.setBounds(112, 252, 400, 2);
		contentPane.add(separator_2_1_3);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBackground(new Color(128, 128, 128));
		separator_2_1.setBounds(112, 166, 400, 2);
		contentPane.add(separator_2_1);

		JSeparator separator_2_1_1 = new JSeparator();
		separator_2_1_1.setBackground(new Color(128, 128, 128));
		separator_2_1_1.setBounds(112, 193, 400, 2);
		contentPane.add(separator_2_1_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(128, 128, 128));
		separator_2.setBounds(112, 136, 400, 2);
		contentPane.add(separator_2);

		JSeparator separator_2_1_2 = new JSeparator();
		separator_2_1_2.setBackground(new Color(128, 128, 128));
		separator_2_1_2.setBounds(112, 222, 400, 2);
		contentPane.add(separator_2_1_2);

		JLabel lblNewLabel_5 = new JLabel("TAYO결제서비스");
		lblNewLabel_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		lblNewLabel_5.setForeground(new Color(132, 197, 249));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(646, 17, 103, 15);
		contentPane.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 128, 128));
		separator.setBounds(654, 35, 170, 2);
		contentPane.add(separator);

		JLabel lblNewLabel_6 = new JLabel("상품명");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("한컴 고딕", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(655, 47, 52, 15);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_6_1 = new JLabel("상품가격");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_6_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_1.setFont(new Font("한컴 고딕", Font.PLAIN, 10));
		lblNewLabel_6_1.setBounds(654, 65, 52, 15);
		contentPane.add(lblNewLabel_6_1);

		JLabel lblNewLabel_6_2 = new JLabel("제공기간");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_6_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_2.setFont(new Font("한컴 고딕", Font.PLAIN, 10));
		lblNewLabel_6_2.setBounds(654, 82, 52, 15);
		contentPane.add(lblNewLabel_6_2);

		textField = new JTextField(Integer.toString(BusClassInfo.price));
		textField.setText("창원시외버스터미널");
		textField.setEditable(false);
		textField.setBackground(new Color(0, 128, 255));
		textField.setForeground(new Color(255, 255, 255));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("한컴 고딕", Font.BOLD, 10));
		textField.setBorder(null);
		textField.setBounds(693, 46, 131, 15);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBackground(new Color(0, 128, 255));
		textField_1.setForeground(new Color(255, 255, 255));
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setText("별도제공기간없음");
		textField_1.setFont(new Font("한컴 고딕", Font.BOLD, 10));
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		textField_1.setBounds(693, 82, 131, 15);
		contentPane.add(textField_1);

		
		//상품가격
		textField_2 = new JTextField(Integer.toString(BusClassInfo.price));
		textField_2.setEditable(false);
		textField_2.setBackground(new Color(0, 128, 255));
		textField_2.setForeground(new Color(255, 255, 255));
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setFont(new Font("한컴 고딕", Font.BOLD, 10));
		textField_2.setBorder(null);
		textField_2.setColumns(10);
		textField_2.setBounds(693, 64, 131, 15);
		contentPane.add(textField_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(654, 107, 170, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_6_2_1 = new JLabel("결제금액");
		lblNewLabel_6_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6_2_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_6_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_6_2_1.setFont(new Font("한컴 고딕", Font.BOLD, 10));

		lblNewLabel_6_2_1.setBounds(656, 122, 52, 15);
		contentPane.add(lblNewLabel_6_2_1);

		
		//결제금액
		textField_3 = new JTextField(Integer.toString(BusClassInfo.totalPrice));
		textField_3.setEditable(false);
		textField_3.setBackground(new Color(0, 128, 255));
		textField_3.setForeground(new Color(255, 255, 255));
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setFont(new Font("한컴 고딕", Font.PLAIN, 12));
		textField_3.setBorder(null);
		textField_3.setColumns(10);
		textField_3.setBounds(693, 119, 131, 23);
		contentPane.add(textField_3);

		JButton btnNext = new JButton("다음");
		btnNext.setBackground(new Color(255, 255, 255));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력값 가져오기
				String bankText = textBank.getText().trim();
				String bankUserText = textBankUser.getText().trim();
				String bankNumText = textBankNum.getText().trim();
				String bankPWText = textBankPW.getText().trim();

				// 입력값 검증
				if (bankText.isEmpty() || bankUserText.isEmpty() || bankNumText.isEmpty() || bankPWText.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					// 입력된 텍스트를 콘솔에 출력
					BusClassInfo.bankCompany=bankText;
					BusClassInfo.payName=bankUserText;
					BusClassInfo.payNumber=bankNumText;
					BusClassInfo.payPassword=bankPWText;
//					System.out.println("은행명: " + BusClassInfo.bankCompany);
//					System.out.println("예금주명: " + BusClassInfo.payName);
//					System.out.println("계좌번호: " + BusClassInfo.payNumber);
//					System.out.println("비밀번호: " + BusClassInfo.payPassword);
					BusDatabase busdb = new BusDatabase();
					busdb.bankInfo(BusClassInfo.bankCompany,BusClassInfo.payName,BusClassInfo.payNumber,BusClassInfo.payPassword,BusClassInfo.totalPrice);
								
					
					busdb.ticketInfo();
					dispose();
				}
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
		backButton.setBounds(538, 7, 50, 30);
		contentPane.add(backButton);

		// 뒤로 가기 버튼 클릭 시
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 기존 코드 생략
				// 뒤로 넘겨주는 코드
				Gyejoa_Gyulje frame = new Gyejoa_Gyulje();
				frame.setVisible(true); // Gyulje 창을 보이도록 설정
				dispose(); // 현재 창 닫기

			}
		});

		// 홈 버튼
		JButton homeButton = new JButton("");
		homeButton.setIcon(new ImageIcon(Gyulje.class.getResource("image/home.png")));
		homeButton.setForeground(new Color(60, 157, 255));
		homeButton.setBorder(null);
		homeButton.setBackground(null);
		homeButton.setFocusPainted(false);
		homeButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		homeButton.setBounds(588, 7, 50, 30);
		contentPane.add(homeButton);

		// 홈 버튼 클릭 시
		homeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 기존 코드 생략

				// 뒤로 넘겨주는 코드
				Gyulje frame = new Gyulje();
				frame.setVisible(true); // Gyulje 창을 보이도록 설정
				dispose(); // 현재 창 닫기

			}
		});

		btnNext.setForeground(new Color(0, 128, 255));
		btnNext.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		btnNext.setBounds(661, 292, 163, 48);
		contentPane.add(btnNext);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(646, -3, 190, 370);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("고객님의 결제계좌정보를 입력하여 주십시오.");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("한컴 고딕", Font.PLAIN, 12));
		lblNewLabel.setBounds(111, 113, 400, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("출금은행명");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(117, 143, 80, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("예금주명");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(118, 173, 80, 15);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("계좌번호");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(118, 201, 80, 15);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_2 = new JLabel("비밀번호");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(117, 231, 80, 15);
		contentPane.add(lblNewLabel_2_1_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(109, 136, 93, 117);
		contentPane.add(panel_1);

		textBank = new JTextField();
		textBank.setFont(new Font("한컴 고딕", Font.BOLD, 12));
		textBank.setBackground(new Color(255, 255, 255));
		textBank.setEditable(false);
		textBank.setBounds(210, 143, 106, 21);
		contentPane.add(textBank);
		textBank.setBorder(null);
		textBank.setColumns(10);

		textBankUser = new JTextField();

		textBankUser.setFont(new Font("한컴 고딕", Font.BOLD, 12));
		textBankUser.setColumns(10);
		textBankUser.setBounds(210, 170, 106, 21);
		contentPane.add(textBankUser);
		textBankUser.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
		// textBankUser의 DocumentFilter 설정
		((AbstractDocument) textBankUser.getDocument()).setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				// 입력 제한: 공백을 포함한 문자열을 입력하지 못하도록 함
				if (!text.contains(" ")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		textGongBack = new JTextField();
		textGongBack.setForeground(new Color(0, 0, 0));
		textGongBack.setBackground(new Color(255, 255, 255));
		textGongBack.setEditable(false);
		textGongBack.setFont(new Font("한컴 고딕", Font.PLAIN, 10));
		textGongBack.setText("공백없이 입력");
		textGongBack.setBorder(null);
		textGongBack.setColumns(10);
		textGongBack.setBounds(333, 170, 106, 21);
		contentPane.add(textGongBack);

		textBankNum = new JTextField();
		textBankNum.setFont(new Font("한컴 고딕", Font.BOLD, 12));
		textBankNum.setColumns(10);
		textBankNum.setBounds(210, 197, 106, 21);
		textBankNum.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

		// DocumentFilter를 사용하여 숫자만 입력 가능하도록 제한
		((AbstractDocument) textBankNum.getDocument()).setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text.matches("\\d*")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		contentPane.add(textBankNum);

		textSulmyeong = new JTextField();
		textSulmyeong.setEditable(false);
		textSulmyeong.setForeground(new Color(0, 0, 0));
		textSulmyeong.setBackground(new Color(255, 255, 255));
		textSulmyeong.setText("'-'없이 입력해주세요.");
		textSulmyeong.setFont(new Font("한컴 고딕", Font.PLAIN, 10));
		textSulmyeong.setBorder(null);
		textSulmyeong.setColumns(10);
		textSulmyeong.setBounds(333, 197, 106, 21);
		contentPane.add(textSulmyeong);

		textBankPW = new JPasswordField();
		textBankPW.setFont(new Font("한컴 고딕", Font.BOLD, 12));
		textBankPW.setColumns(10);
		textBankPW.setBounds(210, 227, 106, 21);
		textBankPW.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

		// DocumentFilter를 사용하여 텍스트 마스킹과 입력 제한 구현
		((AbstractDocument) textBankPW.getDocument()).setDocumentFilter(new DocumentFilter() {
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				// 입력 제한: 4글자까지만 입력 허용
				if (fb.getDocument().getLength() + text.length() - length <= 4 && text.matches("\\d*")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});

		contentPane.add(textBankPW);

	}

	public void setBankText(String bankValue) {
		textBank.setText(bankValue);
	}

}

