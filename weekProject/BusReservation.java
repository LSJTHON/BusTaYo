package weekProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
// java 프로그래밍 언어를 사용해 BusReservationSystem이라는 이름의 클래스를 정의하고있다.
// 이 클래스는 JFrame 클래스를 확장하고 있어서, 그래픽 사용자 인터페이스(GUI)를 구현하는 데 사용될 수 있다.
public class BusReservation extends JFrame {

	// java에서 제공되는 List 인터페이스를 이용하여 SeatComponent, Integer 객체들을 담는 리스트를 선언했다.
	// List를 사용하면 동적으로 크기가 조절되는 배열과 같은 자료구조를 사용할 수 있다.
	// 특정 좌석에 대한 정보를 검색하거나 업데이트하는 등의 작업을 쉽게 수행할 수 있게 된다.
	private List<SeatComponent> seatComponents; // SeatComponent를 변수로 가지는 리스트 선언 / 좌석 전체 객체
	private List<Integer> selectedSeats; // Integer를 변수로 가지는 리스트 선언 / 선택된 좌석 객체
	// public Set<Integer> seatsNum = new HashSet<Integer>();
	// private를 사용하는 이유는 같은 클래스 내부에서만 접근 가능하여 외부에서 직접적으로 상태를 수정하거나 조작하는 것을 방지하기
	// 위함이다.
	private int Price; // 좌석당 기본 가격
	private int numRows; // 좌석 행 수
	private int numCols; // 좌석 열 수
	private int busnum = 1; // 버스 넘버
	private String seatNumStr = null;
	private String busclass = "일반";

	// 이 코드는 BusReservationSystem 클래스 생성자를 정의하고 있다.
	// public 접근 제어자는 외부에서 이 생성자에 접근이 가능하다는 것을 의미한다.
	// 즉, 다른 클래스나 객체에서 new BusReservationSystem() 과 같이 이 생성자를 호출하여
	// BusReserabtionSystem 클래스의 객체를
	// 생성할 수 있다.
	public BusReservation() {
		busclass = BusClassInfo.bus1;
		if (busclass.equals("일반")) {
			Price = BusClassInfo.price;// 좌석당 기본 가격
			numRows = 10; // 좌석 행 수
			numCols = 4; // 좌석 열 수
			setTitle("일반 좌석 선택");// 윈도우 타이틀 지정
		} else if (busclass.equals("우등")) {
			Price = BusClassInfo.price; // 좌석당 기본 가격
			numRows = 7; // 좌석 행 수
			numCols = 4; // 좌석 열 수
			setTitle("우등 좌석 선택");// 윈도우 타이틀 지정
		} else if (busclass.equals("프리미엄")) {
			Price = BusClassInfo.price; // 좌석당 기본 가격
			numRows = 5; // 좌석 행 수
			numCols = 4; // 좌석 열 수
			setTitle("프리미엄 좌석 선택");// 윈도우 타이틀 지정
		}

		// 원하는 여백 크기로 EmptyBorder를 설정합니다.
		getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getRootPane().setBackground(Color.WHITE);

		// setTitle("일반 좌석 선택"); // 윈도우 타이틀 지정
		// JFrame 클래스의 메서드로 프레임이 닫힐 때 어떻게 동작할지를 설명하는 부분입니다. 여기서JFrame.EXIT_ON_CLOSE는
		// 프레임을 닫을 때
		// 프로그램을 종료시키도록 지정하는 상수입니다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 800); // 윈도우 사이즈를 지정

		// ArrayList 클래스를 이용해서 seatComponents, selectedSeats 변수를 리스트로 초기화하는 코드
		seatComponents = new ArrayList<>();
		selectedSeats = new ArrayList<>();

		// 좌석 및 통로 생성
		for (int row = 1; row <= numRows; row++) { // 행 1~5까지 생성
			for (int col = 1; col <= numCols; col++) { // 열 1~4까지 생성
				// 클래스 필드에서 선언한 리스트의 변수인 SeatComponent를 seatComponent변수로 선언
				SeatComponent seatComponent;

				// seatComponent변수에 row, col을 변수로 받는 SeatButton생성자를 할당한다.
				seatComponent = new SeatButton(row, col);
				// seatComponent를 SeatButton타입인지 확인하고 맞다면 객체를 SeatButton으로 형변환 하여
				// SeatButtonListener에 전달
				// addActionListener는 스윙에서 제공하는 메서드로 특정 컴포넌트 액션 이벤트를 처리하는 리스너를 등록하는데 사용된다.
				// SeatButtonListener 클래스의 새로운 인스턴트를 생성하고 전달받은 seatComponent를 변수로 이벤트를 처리한다.
				seatComponent.addActionListener(new SeatButtonListener((SeatButton) seatComponent));
				// seatComponent를 리스트에 추가
				seatComponents.add(seatComponent);
			}
		}

		// 좌석페널 seatPanel을 JPanel로 초기화 하면서 GridLayout(행, 열, 높이갭, 너비갭)로 지정한다.
		JPanel seatPanel = new JPanel(new GridLayout(numRows, numCols, 5, 5));
		// seatComponents 리스트에 있는 모든 SeatComponent 객체에 대해 반복합니다.
		seatPanel.setBackground(Color.WHITE);
		for (SeatComponent seatComponent : seatComponents) {
			seatPanel.add(seatComponent.getComponent()); // getComponent() 메서드는 해당 좌석을 나타내는 JComponent를 반환
		}

		// 가격 정보 출력 레이블
		JLabel priceLabel = new JLabel("선택한 좌석 수: 0 | 총 가격: 0원");

		// 뒤로가기 버튼 만들기
		RoundedButton backButton = new RoundedButton("뒤로가기");
		backButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 10));
		backButton.setFocusPainted(false);
		backButton.setBorder(null);
		backButton.setBackground(new Color(0, 128, 255));
		backButton.setForeground(new Color(255, 255, 255));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 뒤로가기 버튼 동작 설정
				MemoCalendar jFrame = new MemoCalendar();
				jFrame.mainFrame.setVisible(true);
				dispose();
			}
		});

		// 예매 버튼
		RoundedButton bookButton = new RoundedButton("   결            제   ");
		bookButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		bookButton.setFocusPainted(false);
		bookButton.setBorder(null);
		bookButton.setBackground(new Color(0, 128, 255));
		bookButton.setForeground(new Color(255, 255, 255));
		// 버튼 클릭했을 때 이벤트
		bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 토탈 금액을 선택된 좌석 갯수에 기본 가격을 곱해서 넣어준다.
				int totalPrice = selectedSeats.size() * BusClassInfo.price;

				// 예매완료와 총 가격을 알려준다
				JOptionPane.showMessageDialog(null, "결제 창으로 넘어가겠습니다. 총 가격: " + totalPrice + "원");
				Gyulje gyulje1 = new Gyulje();
				gyulje1.setVisible(true);
				dispose();

				// 선택된 좌석들을 false로 바꿔줌 이놈은 나중에 결제가 끝나고난 후에 실행해야만하는 메소드
				// reserveSelectedSeats();
				BusClassInfo.resultSeatNum = selectedSeats;
				// selectedSeats 초기화 재선언
				selectedSeats = new ArrayList<>();
			}
		});

		// 프레임에 컴포넌트 추가
		getContentPane().setLayout(new BorderLayout());

		// priceLabel과 buttonPanel을 포함한 패널을 만듭니다.
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(priceLabel, BorderLayout.WEST); // priceLabel을 왼쪽에 추가
		topPanel.add(backButton, BorderLayout.EAST); // buttonPanel을 오른쪽에 추가
		topPanel.setBackground(Color.WHITE);

		// 프레임에 컴포넌트 추가
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH); // priceLabel과 buttonPanel을 포함한 패널을 프레임의 NORTH에 추가
		getContentPane().add(seatPanel, BorderLayout.CENTER);
		getContentPane().add(bookButton, BorderLayout.SOUTH);

		setLocationRelativeTo(null); // 화면 정중앙에 표시
	}

	// 좌석 를 나타내는 컴포넌트의 기본 인터페이스
	private interface SeatComponent {
		JComponent getComponent();

		void addActionListener(ActionListener listener);
	}

	// 좌석을 나타내는 버튼 클래스
	private class SeatButton extends JButton implements SeatComponent {
		private int seatNumber; // 좌석번호
		private boolean available; // 좌석 예약여부 true = 사용가능, false = 사용불가

		// 좌석버튼에 시트넘버를 넣어주고 빈자리로 설정해준다.
		public SeatButton(int row, int col) {
			this.seatNumber = (row - 1) * 4 + col;
			setAvailabilityFromDatabase(); // 일반, 우등, 프리미엄 중 선택된 좌석에 예약상태에 따라 좌석을 보여준다.
			setIcon(new ImageIcon(BusReservation.class.getResource("image/emptySeat.png"))); // 추가
			setContentAreaFilled(false);
			setBorderPainted(false);
			setBorder(null);
			setBackground((Color) null); //
			setPreferredSize(new Dimension(50, 50)); // 가로, 높이
			setMargin(new Insets(5, 5, 5, 5)); // 마진 위에서부터 시계방향
			updateButton(); // 업데이트 메서드
		}

		// seatnum 컬럼 값에 따라 available 설정
		private void setAvailabilityFromDatabase() {
			String url = "jdbc:mysql://222.119.100.89:3382/bustickets";
			String user = "bustickets";
			String password = "1234";

			String choiceBus = null;

			// 일반, 우등, 프르미엄 중 일치하는 버스로 변수 대입
			if (busclass.equals("일반")) {
				choiceBus = "normalbus";
			} else if (busclass.equals("우등")) {
				choiceBus = "honorbus";
			} else if (busclass.equals("프리미엄")) {
				choiceBus = "premiumbus";
			}
			// System.out.println( choiceBus+" "+ busclass+" "+
			// BusClassInfo.selectEndRegion+" "+ BusClassInfo.selectDate+"
			// "+BusClassInfo.selectTime );
			// mysql에서 시트넘버가 0이면 예약가능 1이면 예약불가능으로 만들어줌
			try (Connection connection = DriverManager.getConnection(url, user, password)) {
				// System.out.println("위"+BusClassInfo.selectEndRegion);
				String selectQuery = "select seatnum FROM " + choiceBus
						+ " where busclass = ? and endregion = ? and nowdate =? and bustime = ?";

				PreparedStatement pstmt = connection.prepareStatement(selectQuery);
//				pstmt.setString(1, choiceBus);
				pstmt.setString(1, busclass);
				pstmt.setString(2, BusClassInfo.selectEndRegion);
				pstmt.setString(3, BusClassInfo.selectDate);
				pstmt.setString(4, BusClassInfo.selectTime);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					String seatNumStr1 = rs.getString("seatnum");
					char[] seatNumArray = seatNumStr1.toCharArray();
					seatNumStr = new String(seatNumArray);

					if (seatNumArray[seatNumber - 1] == '0') {
						available = true;
					} else {
						available = false;
					}
				}
				pstmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 예외 처리 (연결 오류 등)
			}
		}

		public int getSeatNumber() {
			return seatNumber;
		}

		public boolean isAvailable() {
			return available;
		}

		// available이 true면 그대로 false면 빨강으로 설정
		public void updateButton() {
			if (available) {
				setBackground(null);
			} else {
				setIcon(new ImageIcon(BusReservation.class.getResource("image/soldOutSeat1.png")));
				setContentAreaFilled(false);
//				setForeground(Color.WHITE);
//				setBorderPainted(true);
//				setBackground(new Color(200, 50, 50));
//				setOpaque(true);
				setFocusPainted(false);
			}
			setText(""); // 좌석 번호 텍스트를 입력
		}

		// available을 false로 업데이트 합니다.
		public void reserve() {
			available = false;
			updateButton();
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void addActionListener(ActionListener listener) {
			super.addActionListener(listener);
		}
	}

// 좌석 버튼에 대한 ActionListener
	private class SeatButtonListener implements ActionListener {
		private SeatButton seatButton;

		public SeatButtonListener(SeatButton seatButton) {
			this.seatButton = seatButton;
		}

		// 좌석 버튼을 클릭했을 때 이벤트
		@Override
		public void actionPerformed(ActionEvent e) {
			// 만약 좌석버튼 isAvailable이 true라면
			if (seatButton.isAvailable()) {
				// 선택된 좌석이라면 백그라운드를 null로 바꿔줌
				if (selectedSeats.contains(seatButton.getSeatNumber())) {

					BusClassInfo.seatsNum.remove(seatButton.getSeatNumber());
					seatButton.setIcon(new ImageIcon(BusReservation.class.getResource("image/emptySeat.png")));
					Iterator<Integer> iterator = BusClassInfo.seatsNum.iterator();
					while (iterator.hasNext()) {
						Integer element = iterator.next();

					}

					selectedSeats.remove(Integer.valueOf(seatButton.getSeatNumber()));
					// seatButton.setBackground(null);
					// seatButton.setBackground(null);
//					seatButton.setForeground(Color.BLACK);
//					seatButton.setBackground(new Color(255, 255, 255));
//					seatButton.setBorderPainted(true);
					seatButton.setFocusPainted(false);
					// seatButton.setBorder(new LineBorder(new Color(50,50,50), 1));
				} else { // 선택되지 않은 좌석이라면 백그라운드를 초록색으로 바꿔줌
					selectedSeats.add(seatButton.getSeatNumber());
					seatButton.setIcon(new ImageIcon(BusReservation.class.getResource("image/pressSeat.png")));
//					 seatButton.setBackground(Color.GREEN);

					BusClassInfo.seatsNum.add(seatButton.getSeatNumber());
					Iterator<Integer> iterator = BusClassInfo.seatsNum.iterator();
					while (iterator.hasNext()) {
						Integer element = iterator.next();
					}

//					seatButton.setContentAreaFilled(false);
//					seatButton.setForeground(Color.WHITE);
//					seatButton.setBackground(new Color(50, 100, 200)); // 클릭 시 파란색으로
//					seatButton.setOpaque(true);
//					seatButton.setBorderPainted(true);
//					seatButton.setFocusPainted(false);
				}
				// priceLabel을 업데이트 해주는 메서드
				updatePriceLabel();
			} else { // false일때 이미 예약된 좌석이라고 경고창을 띄워줌
				JOptionPane.showMessageDialog(null, "이미 예약된 좌석입니다.", "알림", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	// 예매가 완료되었을 때 선택된 좌석들을 예약 상태로 변경하는 메서드
	public void reserveSelectedSeats(List<Integer> selectedSeats) {
		for (SeatComponent seatComponent : seatComponents) {
			if (seatComponent instanceof SeatButton) {
				SeatButton seatButton = (SeatButton) seatComponent;
				int seatNumber = seatButton.getSeatNumber();
				// 선택된 좌석 리스트에 특정 시트넘버가 포함되어 있는지 확인하는 코드 즉, 선택된 좌석들을 false로 바꿔주는 코드
				if (selectedSeats.contains(seatNumber)) {
					updateDatabase(selectedSeats); // db에 업데이트
					seatButton.reserve(); // 시트버튼을 false로 설정
				}
			}
		}

		updatePriceLabel(); // 예약 후 가격 정보 업데이트
	}

	// 가격 정보 레이블 업데이트
	// 초록색 좌석 버튼 갯수를 읽어 업데이트 한다
	private void updatePriceLabel() {
		int greenSeatsCount = 0;
		for (Integer seatNumber : selectedSeats) {
			for (SeatComponent seatComponent : seatComponents) {
				if (seatComponent instanceof SeatButton) {
					SeatButton seatButton = (SeatButton) seatComponent;
					if (seatButton.getSeatNumber() == seatNumber && seatButton.isAvailable()) {
						greenSeatsCount++;
					}
				}
			}
		}
		String choiceBus1 = null;
		if (busclass.equals("일반")) {
			choiceBus1 = "normalbus";
		} else if (busclass.equals("우등")) {
			choiceBus1 = "honorbus";
		} else if (busclass.equals("프리미엄")) {
			choiceBus1 = "premiumbus";
		}
		BusDatabase busPrice = new BusDatabase();
		busPrice.busSeatInfo(choiceBus1);
		int totalPrice = greenSeatsCount * BusClassInfo.price;
		JPanel pricePanel = (JPanel) getContentPane().getComponent(0);
		JLabel priceLabel = (JLabel) pricePanel.getComponent(0);
		priceLabel.setText("선택한 좌석 수: " + greenSeatsCount + " | 총 가격: " + totalPrice + "원");
		BusClassInfo.totalPrice = totalPrice;
	}

	// 선택된 좌석 번호에 해당하는 MySQL 데이터베이스 테이블 컬럼을 업데이트하는 메서드
	private void updateDatabase(List<Integer> selectedSeats) {
		// 데이터가 정적필드에 잘 들어갔는가와 잘 불러와 지는가?
//		System.out.println(BusClassInfo.price+  
//				" "+BusClassInfo.selectDate+
//				" "+BusClassInfo.selectEndRegion+
//				" "+BusClassInfo.selectTime+
//				" "+BusClassInfo.totalPrice);
		String url = "jdbc:mysql://222.119.100.89:3382/bustickets";
		String user = "bustickets";
		String password = "1234";

		String choiceBus = null;

		if (busclass.equals("일반")) {
			choiceBus = "normalbus";
		} else if (busclass.equals("우등")) {
			choiceBus = "honorbus";
		} else if (busclass.equals("프리미엄")) {
			choiceBus = "premiumbus";
		}
		// System.out.println("try 전"+ choiceBus+" "+ busclass+" "+
		// BusClassInfo.selectEndRegion+" "+ BusClassInfo.selectDate+"
		// "+BusClassInfo.selectTime );
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			// 선택된 좌석 번호에 대해 반복하고 해당 컬럼을 업데이트
			for (Integer seatNumber : selectedSeats) {
				char[] seatNumArray = seatNumStr.toCharArray();
				seatNumArray[seatNumber - 1] = '1';
				seatNumStr = new String(seatNumArray);
			}
			// System.out.println(seatNumStr); 시트넘버의 배열을 콘솔창에 눈으로 확인용
			// System.out.println("예약 후 문자열"+seatNumStr);

			String updateQuery = "UPDATE " + choiceBus + " SET seatnum = '" + seatNumStr
					+ "' where busclass = ? and endregion = ? and nowdate =? and bustime = ?";
			// System.out.println("업데이트쪽"+ choiceBus+" "+ busclass+" "+
			// BusClassInfo.selectEndRegion+" "+ BusClassInfo.selectDate+"
			// "+BusClassInfo.selectTime );
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, busclass);
			pstmt.setString(2, BusClassInfo.selectEndRegion);
			pstmt.setString(3, BusClassInfo.selectDate);
			pstmt.setString(4, BusClassInfo.selectTime);
			try {
				// PreparedStatement를 사용하여 SQL 쿼리 실행
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 예외 처리 (오류 발생 시 출력)
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace(); // 예외 처리 (연결 오류 등)
		}

	}
}