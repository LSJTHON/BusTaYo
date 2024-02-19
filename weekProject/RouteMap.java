package weekProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class RouteMap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton normalRadioButton;
	private JRadioButton honorRadioButton;
	private JRadioButton premiumRadioButton;

	public RouteMap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);
		setContentPane(contentPane);

		RoundJButton backButton = new RoundJButton("뒤로가기");
		backButton.setBorderPainted(false);
		backButton.setBackground(new Color(0, 128, 255));
		backButton.setForeground(Color.WHITE);
		backButton.setBounds(739, 10, 87, 23);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FORM frame = new FORM();
				frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(backButton);

		JLabel mainTitleLabel = new JLabel("노선표");
		mainTitleLabel.setFont(new Font("Dialog", Font.BOLD, 34));
		mainTitleLabel.setForeground(new Color(0, 128, 255));
		mainTitleLabel.setBounds(10, 10, 110, 59);
		contentPane.add(mainTitleLabel);

		JComboBox startregionComboBox = new JComboBox();
		startregionComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "창원", "부산", "서울", "울산", "거제", "대구", "인천", "광주", "대전" }));
		startregionComboBox.setBounds(66, 75, 50, 23);
		contentPane.add(startregionComboBox);

		JLabel startregionLabel = new JLabel("출발지 :");
		startregionLabel.setBounds(10, 79, 46, 15);
		contentPane.add(startregionLabel);

		JLabel endregionLabel = new JLabel("도착지 :");
		endregionLabel.setBounds(126, 79, 46, 15);
		contentPane.add(endregionLabel);

		JLabel startTimeLabel = new JLabel("출발시간 :");
		startTimeLabel.setBounds(584, 79, 61, 15);
		contentPane.add(startTimeLabel);

		JComboBox endregionComboBox = new JComboBox();
		endregionComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "창원", "부산", "서울", "울산", "거제", "대구", "인천", "광주", "대전" }));
		endregionComboBox.setBounds(182, 75, 50, 23);
		contentPane.add(endregionComboBox);

		ButtonGroup radioButtonGroup = new ButtonGroup();

		normalRadioButton = new JRadioButton("일반");
		normalRadioButton.setBounds(249, 75, 50, 23);
		normalRadioButton.setBackground(new Color(255, 255, 255));
		radioButtonGroup.add(normalRadioButton);
		normalRadioButton.setSelected(true);
		contentPane.add(normalRadioButton);

		honorRadioButton = new JRadioButton("우등");
		honorRadioButton.setBounds(301, 75, 50, 23);
		honorRadioButton.setBackground(new Color(255, 255, 255));
		radioButtonGroup.add(honorRadioButton);
		contentPane.add(honorRadioButton);

		premiumRadioButton = new JRadioButton("프리미엄");
		premiumRadioButton.setBounds(353, 75, 77, 23);
		premiumRadioButton.setBackground(new Color(255, 255, 255));
		radioButtonGroup.add(premiumRadioButton);
		contentPane.add(premiumRadioButton);

		JLabel dateLabel = new JLabel("날짜 :");
		dateLabel.setBounds(436, 79, 38, 15);
		contentPane.add(dateLabel);

		JTextField dateTextField = new JTextField("20230101");
		dateTextField.setForeground(Color.GRAY);
		dateTextField.setBounds(485, 76, 89, 21);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

		// KeyListener 추가
		dateTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
				// 길이가 8이 되도록 제한
				if (dateTextField.getText().length() >= 8) {
					e.consume();
				}
			}
		});

		// 포커스 이벤트
		dateTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (dateTextField.getText().equals("20230101")) {
					dateTextField.setText("");
					dateTextField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dateTextField.getText().isEmpty()) {
					dateTextField.setText("20230101");
					dateTextField.setForeground(Color.GRAY);
				}
			}
		});

		JComboBox startTimeComboBox = new JComboBox();
		startTimeComboBox.setModel(new DefaultComboBoxModel(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00",
				"05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
				"16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));
		startTimeComboBox.setBounds(655, 75, 61, 23);
		contentPane.add(startTimeComboBox);

		// 추가된 부분: 데이터 모델
		DefaultListModel<String> busListModel = new DefaultListModel<>();

		// JList에 모델 설정
		JList<String> busReservationlist = new JList<>(busListModel);
		busReservationlist.setBounds(10, 104, 816, 249);
		busReservationlist.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 필요에 따라 색상을 사용자 정의할 수 있습니다
		// 셀 렌더러 설정
        busReservationlist.setCellRenderer(new CustomListCellRenderer());
		contentPane.add(busReservationlist);

		JButton selectButton = new RoundedButton("검색");
		selectButton.setContentAreaFilled(false);
		selectButton.setOpaque(true);
		selectButton.setFocusPainted(false);
		
		selectButton.setBackground(new Color(0, 128, 255));
		selectButton.setForeground(Color.WHITE);
		selectButton.setBounds(726, 75, 87, 23);
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null; // Connection 변수를 try 블록 밖에서 선언
				// Get user input
				String region = startregionComboBox.getSelectedItem().toString();
				System.out.println(region);
				String endregion = endregionComboBox.getSelectedItem().toString();
				System.out.println(endregion);
				String bustype = getSelectedBusType();
				System.out.println(bustype);
				String dateString = dateTextField.getText();
				String formattedDate = convertToFormattedDate(dateString);
				System.out.println(formattedDate);
				String startTimeString = startTimeComboBox.getSelectedItem().toString();
				String formattedStartTime = extractHourFromTime(startTimeString);
				System.out.println(formattedStartTime);

				try {
					busListModel.removeAllElements();

					conn = DriverManager.getConnection("jdbc:mysql://222.119.100.89:3382/bustickets", "bustickets",
							"1234");

					// Prepare SQL query
					String query = "SELECT * FROM " + bustype
							+ " WHERE region = ? AND endregion = ? AND nowdate = ? AND bustime >= ?";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					preparedStatement.setString(1, region);
					preparedStatement.setString(2, endregion);
					preparedStatement.setString(3, formattedDate);
					preparedStatement.setString(4, formattedStartTime);

					ResultSet resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						String busInfo_1 = resultSet.getString("busnum");
						System.out.println(busInfo_1);
						String busInfo_2 = resultSet.getString("region");
						System.out.println(busInfo_2);
						String busInfo_3 = resultSet.getString("endregion");
						System.out.println(busInfo_3);
						String busInfo_4 = resultSet.getString("company");
						System.out.println(busInfo_4);
						String busInfo_5 = resultSet.getString("busclass");
						System.out.println(busInfo_5);
						String busInfo_6 = resultSet.getString("price");
						System.out.println(busInfo_6);
						String busInfo_7 = resultSet.getString("nowdate");
						System.out.println(busInfo_7);
						String busInfo_8 = resultSet.getString("bustime");
						System.out.println(busInfo_8);
						String busInfo_9 = resultSet.getString("seatnum");
						System.out.println(busInfo_9);

						// JList에 데이터 추가
						busListModel.addElement("버스 번호: " + busInfo_1 + " | 출발지: " + busInfo_2 + " | 도착지: " + busInfo_3 + " | 좌석: " + busInfo_4
								+ " | 등급: " + busInfo_5 + " | 가격: " + busInfo_6 + " 원 | 날짜: " + busInfo_7 + " | 출발시간: "
								+ busInfo_8 + " 시" /*" | 좌석번호: " + busInfo_9 */);
					}

					// Close resources
					resultSet.close();
					preparedStatement.close();

				} catch (SQLException ex) {
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
		contentPane.add(selectButton);
	}

    // CustomListCellRenderer 클래스 추가
    private class CustomListCellRenderer extends JLabel implements ListCellRenderer<String> {

        private static final long serialVersionUID = 1L;

        public CustomListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(value);
            setFont(new Font("Dialog", Font.BOLD, 14)); // 원하는 폰트 설정
            setForeground(Color.BLACK); // 원하는 텍스트 색상 설정

            if (isSelected) {
                setBackground(new Color(0, 128, 255)); // 선택된 항목 배경색 설정
                setForeground(Color.WHITE); // 선택된 항목의 텍스트 색상 설정
            } else {
                setBackground(Color.WHITE); // 선택되지 않은 항목 배경색 설정
            }

            return this;
        }
    }

	private String convertToFormattedDate(String inputDate) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = inputFormat.parse(inputDate);

			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = outputFormat.format(date);

			// 월과 일에 0을 뺌
			String[] parts = formattedDate.split("-");
			int month = Integer.parseInt(parts[1]);
			int day = Integer.parseInt(parts[2]);
			formattedDate = parts[0] + "-" + month + "-" + day;

			return formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return ""; // Handle parsing error
		}
	}

	private String extractHourFromTime(String inputTime) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
			Date time = inputFormat.parse(inputTime);

			SimpleDateFormat outputFormat = new SimpleDateFormat("H");
			return outputFormat.format(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return ""; // Handle parsing error
		}
	}

	private String getSelectedBusType() {
		if (normalRadioButton.isSelected()) {
			return "normalbus";
		} else if (honorRadioButton.isSelected()) {
			return "honorbus";
		} else if (premiumRadioButton.isSelected()) {
			return "premiumbus";
		} else {
			return "";
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

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RouteMap frame = new RouteMap();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//	}
	}