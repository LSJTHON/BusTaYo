package weekProject;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Nosunpyo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nosunpyo frame = new Nosunpyo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Nosunpyo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(850, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 255, 255));
		separator.setForeground(new Color(192, 192, 192));
		separator.setBounds(12, 23, 377, 1);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("▶ 찾아오시는 길");
		
		lblNewLabel.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(12, 0, 812, 25);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(117, 27, 614, 219);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel bus = new JLabel("(주)창원시외버스터미널");
		bus.setBackground(new Color(255, 255, 255));
		bus.setHorizontalAlignment(SwingConstants.CENTER);
		bus.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		bus.setBounds(54, 68, 135, 15);
		panel.add(bus);

		JLabel ms1 = new JLabel("마산");
		ms1.setHorizontalAlignment(SwingConstants.CENTER);
		ms1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		ms1.setBounds(245, 187, 35, 15);
		panel.add(ms1);

		JLabel ms2 = new JLabel("▼");
		ms2.setHorizontalAlignment(SwingConstants.LEFT);
		ms2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		ms2.setBounds(256, 202, 15, 15);
		panel.add(ms2);

		JLabel cw1 = new JLabel("창원대로 ▶");
		cw1.setHorizontalAlignment(SwingConstants.LEFT);
		cw1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		cw1.setBounds(550, 156, 60, 15);
		panel.add(cw1);

		JEditorPane road5 = new JEditorPane();
		road5.setEditable(false);
		road5.setEnabled(false);
		road5.setBackground(new Color(224, 224, 224));
		road5.setBounds(0, 23, 614, 30);
		panel.add(road5);

		JEditorPane road6 = new JEditorPane();
		road6.setEnabled(false);
		road6.setEditable(false);
		road6.setBackground(new Color(224, 224, 224));
		road6.setBounds(0, 148, 614, 30);
		panel.add(road6);

		JEditorPane road1 = new JEditorPane();
		road1.setBackground(new Color(224, 224, 224));
		road1.setBounds(38, 1, 48, 219);
		panel.add(road1);

		JEditorPane road2 = new JEditorPane();
		road2.setBackground(new Color(224, 224, 224));
		road2.setBounds(252, 1, 20, 219);
		panel.add(road2);

		JEditorPane road3 = new JEditorPane();
		road3.setBackground(new Color(224, 224, 224));
		road3.setBounds(362, 1, 48, 219);
		panel.add(road3);

		JEditorPane road4 = new JEditorPane();
		road4.setBackground(new Color(224, 224, 224));
		road4.setBounds(507, 1, 48, 219);
		panel.add(road4);

		JEditorPane nkBuild = new JEditorPane();
		nkBuild.setBackground(new Color(128, 128, 128));
		nkBuild.setBounds(276, 56, 29, 18);
		panel.add(nkBuild);

		JEditorPane opBuild = new JEditorPane();
		opBuild.setBackground(Color.GRAY);
		opBuild.setBounds(414, 56, 29, 18);
		panel.add(opBuild);

		JLabel nk = new JLabel("새경남아파트");
		nk.setHorizontalAlignment(SwingConstants.LEFT);
		nk.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		nk.setBounds(275, 77, 74, 15);
		panel.add(nk);

		JLabel op = new JLabel("올림픽공원");
		op.setHorizontalAlignment(SwingConstants.LEFT);
		op.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		op.setBounds(413, 77, 74, 15);
		panel.add(op);

		JEditorPane ngBuild = new JEditorPane();
		ngBuild.setBackground(Color.GRAY);
		ngBuild.setBounds(414, 182, 29, 18);
		panel.add(ngBuild);

		JLabel ng = new JLabel("남광주유소");
		ng.setHorizontalAlignment(SwingConstants.LEFT);
		ng.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		ng.setBounds(413, 203, 74, 15);
		panel.add(ng);

		JEditorPane ggBuild = new JEditorPane();
		ggBuild.setBackground(Color.GRAY);
		ggBuild.setBounds(220, 181, 29, 18);
		panel.add(ggBuild);

		JLabel gg = new JLabel("기계공구상가");
		gg.setHorizontalAlignment(SwingConstants.RIGHT);
		gg.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		gg.setBounds(180, 203, 68, 15);
		panel.add(gg);

		JEditorPane cgvBuild = new JEditorPane();
		cgvBuild.setBackground(Color.GRAY);
		cgvBuild.setBounds(172, 128, 29, 18);
		panel.add(cgvBuild);

		JLabel cgv = new JLabel("CGV");
		cgv.setHorizontalAlignment(SwingConstants.CENTER);
		cgv.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		cgv.setBounds(172, 111, 29, 15);
		panel.add(cgv);

		JEditorPane hpBuild = new JEditorPane();
		hpBuild.setBackground(Color.GRAY);
		hpBuild.setBounds(234, 135, 15, 11);
		panel.add(hpBuild);

		JLabel hp = new JLabel("홈플러스");
		hp.setHorizontalAlignment(SwingConstants.CENTER);
		hp.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		hp.setBounds(207, 120, 42, 15);
		panel.add(hp);

		JEditorPane phBuild = new JEditorPane();
		phBuild.setBackground(Color.GRAY);
		phBuild.setBounds(234, 57, 15, 11);
		panel.add(phBuild);

		JLabel ph = new JLabel("파티마병원");
		ph.setHorizontalAlignment(SwingConstants.CENTER);
		ph.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		ph.setBounds(199, 69, 50, 15);
		panel.add(ph);

		JEditorPane busBuild = new JEditorPane();
		busBuild.setBackground(new Color(0, 128, 255));
		busBuild.setBounds(98, 86, 47, 60);
		panel.add(busBuild);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(24, 272, 377, 1);
		contentPane.add(separator_1);

		JLabel place = new JLabel("▶ 위치안내");
		place.setHorizontalAlignment(SwingConstants.LEFT);
		place.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 17));
		place.setBounds(24, 249, 812, 25);
		contentPane.add(place);

		JLabel place1 = new JLabel("주소 : 창원시 팔용동 35번지");
		place1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		place1.setBounds(34, 280, 353, 15);
		contentPane.add(place1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.LIGHT_GRAY);
		separator_1_1.setBackground(Color.WHITE);
		separator_1_1.setBounds(24, 321, 377, 1);
		contentPane.add(separator_1_1);

		JLabel information = new JLabel("▶ 교통안내");
		information.setHorizontalAlignment(SwingConstants.LEFT);
		information.setFont(new Font("여기어때 잘난체 2 TTF", Font.PLAIN, 17));
		information.setBounds(24, 298, 812, 25);
		contentPane.add(information);

		JLabel information1 = new JLabel(
				"<html>좌석 : 701, 703, 712 <br>일반 : 101, 106, 108, 111, 114, 210, 211, 150, 151, 58, 59</html>");
		information1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		information1.setForeground(new Color(0, 0, 0));
		information1.setBounds(32, 326, 792, 31);
		contentPane.add(information1);
		
		JButton btnNewButton_4 = new JButton("뒤로가기");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FORM f1 = new FORM();
				f1.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(735, 10, 80, 30);
		contentPane.add(btnNewButton_4);
	}
}