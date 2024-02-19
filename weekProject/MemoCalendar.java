package weekProject;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionEvent;

class CalendarDataManager{ // 6*7배열에 나타낼 달력 값을 구하는 class
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	
	String timeSelect="06";
	final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
	int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;
	
	public CalendarDataManager(){ 
		setToday(); 
	}
	public void setToday(){
		calYear = today.get(Calendar.YEAR);  //현재 년도 가져오기
		calMonth = today.get(Calendar.MONTH);  //현재 월 가져오기
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH); // 현재 월의 날짜
		makeCalData(today);
	}
	private void makeCalData(Calendar cal){
		// 1일의 위치와 마지막 날짜를 구함 
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
		if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else calLastDate = calLastDateOfMonth[calMonth];
		// 달력 배열 초기화
		for(int i = 0 ; i<CAL_HEIGHT ; i++){
			for(int j = 0 ; j<CAL_WIDTH ; j++){
				calDates[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
			if(i == 0) k = calStartingPos;
			else k = 0;
			for(int j = k ; j<CAL_WIDTH ; j++){
				if(num <= calLastDate) calDates[i][j]=num++;
			}
		}
	}
	private int leapCheck(int year){ // 윤년인지 확인하는 함수
		if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
		else return 0;
	}
	public void moveMonth(int mon){ // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
		calMonth += mon;
		if(calMonth>11) while(calMonth>11){ //한 해가 지났을 때 년을 +1  월을 -12
			calYear++;
			calMonth -= 12;
		} else if (calMonth<0) while(calMonth<0){  //지난 해일 때 년을 -1  월을 +12
			calYear--;
			calMonth += 12;
		}
		cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
		makeCalData(cal);
	}
}

public class MemoCalendar extends CalendarDataManager{ // UI
	// 창 구성요소와 배치도
	JFrame mainFrame;
		//ImageIcon icon = new ImageIcon ( Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
	//아이콘 설정
	
	JPanel calOpPanel;
		JButton resetBut; //리셋버튼
		JButton backBut;
		JComboBox startRegion; //지역선택 콤보박스 필드 지정
		JComboBox endRegion;
		String selectStartRegion="창원";
		String selectEndRegion="창원"; //클릭했을 시 저장할 변수명지정
		
		String selectBusClass= "우등"; //버스등급 라디오 버튼을 클릭했을 시 필드 지정 
		
		JRadioButton busClass1;
		JRadioButton busClass2;
		JRadioButton busClass3;
		
		
		String nowdate;
		JLabel todayLab;
		JButton lMonBut;
		JLabel curMMYYYYLab;
		JButton nMonBut;
		ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
	
	JPanel calPanel;
		JButton weekDaysName[];
		JButton dateButs[][] = new JButton[6][7];
		listenForDateButs lForDateButs = new listenForDateButs(); 
	
	JPanel infoPanel;
		JLabel infoClock;
	
	JPanel TimePanel;
		JLabel selectedDate;
		//JTextArea TimeArea;
		JButton TimeArea,TimeArea2,TimeArea3,TimeArea4,TimeArea5,TimeArea6,TimeArea7,TimeArea8,TimeArea9,
			TimeArea10,TimeArea11,TimeArea12,TimeArea13,TimeArea14,TimeArea15,TimeArea16,TimeArea17,TimeArea18,TimeArea19;
		JButton CompleteBtn;
		JScrollPane TimeAreaSP;
		JPanel TimeSubPanel;

	//상수, 메세지
	final String WEEK_DAY_NAME[] = { "월", "화", "수", "목", "금", "토", "일" };

//	public static void main(String[] args){
//		SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				new MemoCalendar();
//			}
//		});
//	}
	public MemoCalendar(){ //구성요소 순으로 정렬되어 있음. 각 판넬 사이에 빈줄로 구별
		
		mainFrame = new JFrame("달력띠");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(850,400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		//mainFrame.setIconImage(icon.getImage());
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(mainFrame) ;
		}catch(Exception e){
			System.out.println("LookAndFeel 설정 실패");
		}
		JLabel startName = new JLabel();
		startName.setText("    출발지 :     ");
		JLabel endName = new JLabel();
		endName.setText("도착지 :");
		calOpPanel = new JPanel();
			resetBut = new JButton("리셋"); //리셋 버튼 객체 생성
			resetBut.addActionListener(lForCalOpButtons); //리셋 버튼 이벤트 발생 시
			
			startRegion = new JComboBox();
			startRegion.setModel(new DefaultComboBoxModel(new String[] {"창원", "부산", "서울", "울산", "거제", "대구", "인천", "광주", "대전"}));
			startRegion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectStartRegion = startRegion.getSelectedItem().toString();
		
				}
			});
			endRegion = new JComboBox();
			endRegion.setModel(new DefaultComboBoxModel(new String[] {"창원", "부산", "서울", "울산", "거제", "대구", "인천", "광주", "대전"}));
			endRegion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectEndRegion = endRegion.getSelectedItem().toString();
					BusClassInfo.selectEndRegion = endRegion.getSelectedItem().toString();
				}
			});
			
			todayLab = new JLabel(); //원래 today.get(Calendar.YEAR)+"-"+today.get(Calendar.DAY_OF_MONTH)+"-"+today.get(Calendar.MONTH)+1 있었음
			lMonBut = new JButton("<");
			lMonBut.addActionListener(lForCalOpButtons);
			curMMYYYYLab = new JLabel(calYear+"-"+((calMonth+1)<10?"":"")+(calMonth+1));
			nMonBut = new JButton(">");
			nMonBut.addActionListener(lForCalOpButtons);
			calOpPanel.setLayout(new GridBagLayout());
			GridBagConstraints calOpGC = new GridBagConstraints();
			calOpGC.gridx = 1;
			calOpGC.gridy = 1;
			calOpGC.gridwidth = 4;
			calOpGC.gridheight = 1;
			calOpGC.weightx = 1;
			calOpGC.weighty = 1;
			calOpGC.insets = new Insets(5,5,0,0);
			calOpGC.anchor = GridBagConstraints.WEST;
			calOpGC.fill = GridBagConstraints.NONE;
			//calOpGC.gridwidth = GridBagConstraints.BOTH;
			calOpPanel.add(resetBut,calOpGC); //reset 버튼 추가
			calOpPanel.add(startName); //
			calOpPanel.add(startRegion);
			calOpPanel.add(endName);
			calOpPanel.add(endRegion);
			
			busClass1 = new JRadioButton("일반");
			busClass2 = new JRadioButton("우등");
			busClass3 = new JRadioButton("프리미엄");
			ButtonGroup  group = new ButtonGroup(); //라디오버튼 그룹화를 위한 버튼그룹 설정
            //같은 그룹끼리는 그룹중에 1개만
			group.add(busClass1);group.add(busClass2);group.add(busClass3);
			busClass1.addActionListener(radioActionListener); //라디오 버튼 클릭 시 실행
			busClass2.addActionListener(radioActionListener);
			busClass3.addActionListener(radioActionListener);
			calOpPanel.add(busClass1);
			calOpPanel.add(busClass2);
			calOpPanel.add(busClass3);
			
			backBut = new JButton("뒤로가기");
			backBut.addActionListener(backButListener);
			calOpPanel.add(backBut);
			
			
			calOpGC.gridwidth = 3;
			calOpGC.gridx = 2;
			calOpGC.gridy = 1;
			calOpPanel.add(todayLab,calOpGC);
			calOpGC.anchor = GridBagConstraints.CENTER;
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 2;
			calOpGC.gridy = 2;
			calOpPanel.add(lMonBut,calOpGC);
			calOpGC.gridwidth = 2;
			calOpGC.gridx = 3;
			calOpGC.gridy = 2;
			calOpPanel.add(curMMYYYYLab,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 5;
			calOpGC.gridy = 2;
			calOpPanel.add(nMonBut,calOpGC);

		calPanel=new JPanel();  //달력에 요일 표시
			weekDaysName = new JButton[7];
			for(int i=0 ; i<CAL_WIDTH ; i++){
				weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
				weekDaysName[i].setBorderPainted(false);
				weekDaysName[i].setContentAreaFilled(false);
				weekDaysName[i].setForeground(Color.WHITE);
				if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50)); // i의 index 0번째(일요일)에 빨강색으로 설정
				else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200)); //i의 index 6번째(토요일)에 파랑색으로 설정
				else weekDaysName[i].setBackground(new Color(150, 150, 150)); // 평일은 전부 회색으로
				weekDaysName[i].setOpaque(true);
				weekDaysName[i].setFocusPainted(false);
				calPanel.add(weekDaysName[i]);
			}
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					dateButs[i][j]=new JButton();
					dateButs[i][j].setBorderPainted(false);
					dateButs[i][j].setContentAreaFilled(false);
					dateButs[i][j].setBackground(Color.WHITE);
					dateButs[i][j].setOpaque(true);
					dateButs[i][j].addActionListener(lForDateButs);
					calPanel.add(dateButs[i][j]);
				}
			}
			calPanel.setLayout(new GridLayout(0,7,2,2));
			calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			showCal(); // 달력을 표시
						
		infoPanel = new JPanel();
			infoPanel.setLayout(new BorderLayout());
			infoClock = new JLabel("", SwingConstants.RIGHT);
			infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			infoPanel.add(infoClock, BorderLayout.NORTH);
			//날짜 초기값
			selectedDate = new JLabel("선택한 날짜 : "+(today.get(Calendar.YEAR)+
					"-"+today.get(Calendar.MONTH)+1)+
					"-"+today.get(Calendar.DAY_OF_MONTH)+
					" 선택한 시간 : "+calDayOfMon, SwingConstants.LEFT);
			
			selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
						
			
			//시간 패널 위치
			
			TimePanel=new JPanel();
			TimePanel.setBorder(BorderFactory.createTitledBorder("Ticketing Time")); 
			TimeArea = new JButton("06시");TimeArea2 = new JButton("07시");TimeArea3 = new JButton("08시");
			TimeArea4 = new JButton("09시");TimeArea5 = new JButton("10시");TimeArea6 = new JButton("11시");
			TimeArea7 = new JButton("12시");TimeArea8 = new JButton("13시");TimeArea9 = new JButton("14시");
			TimeArea10 = new JButton("15시");TimeArea11 = new JButton("16시");TimeArea12 = new JButton("17시");
			TimeArea13 = new JButton("18시");TimeArea14 = new JButton("19시");TimeArea15 = new JButton("20시");
			TimeArea16 = new JButton("21시");TimeArea17 = new JButton("22시");TimeArea18 = new JButton("23시");TimeArea19 = new JButton("24시");
			CompleteBtn = new RoundedButton("완료");
			
			CompleteBtn.setContentAreaFilled(false);
			CompleteBtn.setForeground(Color.WHITE);
			CompleteBtn.setBackground(new Color(50, 100, 200));
			CompleteBtn.setOpaque(true);
			CompleteBtn.setFocusPainted(false);
			
			TimeArea.addActionListener(actionListener);TimeArea2.addActionListener(actionListener);
			TimeArea3.addActionListener(actionListener);TimeArea4.addActionListener(actionListener);
			TimeArea5.addActionListener(actionListener);TimeArea6.addActionListener(actionListener);
			TimeArea7.addActionListener(actionListener);TimeArea8.addActionListener(actionListener);
			TimeArea9.addActionListener(actionListener);TimeArea10.addActionListener(actionListener);
			TimeArea11.addActionListener(actionListener);TimeArea12.addActionListener(actionListener);
			TimeArea13.addActionListener(actionListener);TimeArea14.addActionListener(actionListener);
			TimeArea15.addActionListener(actionListener);TimeArea16.addActionListener(actionListener);
			TimeArea17.addActionListener(actionListener);TimeArea18.addActionListener(actionListener);
			TimeArea19.addActionListener(actionListener);
			
			CompleteBtn.addActionListener(actionListener); //완료 버튼의 이벤트 발생 시
			
			
			TimePanel.add(selectedDate, BorderLayout.NORTH); //Ticketing Time 밑 선택한 날짜와 시간 선택을 북쪽에 표시
			TimePanel.setLayout(new GridLayout(2,1));  

			JPanel jp1 = new JPanel();
			
			jp1.setLayout(new GridLayout(4,2));
			TimePanel.add(jp1);
			jp1.add(TimeArea);jp1.add(TimeArea2);jp1.add(TimeArea3); jp1.add(TimeArea4); jp1.add(TimeArea5); jp1.add(TimeArea6);
			jp1.add(TimeArea7);jp1.add(TimeArea8);jp1.add(TimeArea9);jp1.add(TimeArea10);jp1.add(TimeArea11);jp1.add(TimeArea12);
			jp1.add(TimeArea13);jp1.add(TimeArea14);jp1.add(TimeArea15);jp1.add(TimeArea16);jp1.add(TimeArea17);jp1.add(TimeArea18);jp1.add(TimeArea19);
			jp1.add(CompleteBtn);
		//calOpPanel, calPanel을  frameSubPanelWest에 배치
		JPanel frameSubPanelWest = new JPanel();
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 100;
		calOpPanelSize.width = 200;
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel,BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel,BorderLayout.CENTER);
		//frameSubPanelWest.add(backBut); //뒤로가기 버튼이 달력에 덮어쓰기 되버림 쓰지말것

		//infoPanel, memoPanel을  frameSubPanelEast에 배치
		JPanel frameSubPanelEast = new JPanel();
		Dimension infoPanelSize=infoPanel.getPreferredSize();
		infoPanelSize.height = 65;
		infoPanel.setPreferredSize(infoPanelSize);
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(infoPanel,BorderLayout.NORTH);
		frameSubPanelEast.add(TimePanel,BorderLayout.CENTER);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 510;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
		
		//frame에 전부 배치
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
		mainFrame.add(frameSubPanelEast, BorderLayout.CENTER);
	
		//mainFrame.setVisible(true);

		focusToday(); //현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)

	}
	
	private ActionListener radioActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(busClass1.isSelected()) BusClassInfo.bus1 = "일반";
			else if(busClass2.isSelected()) BusClassInfo.bus1 = "우등";
			else if(busClass3.isSelected()) BusClassInfo.bus1 = "프리미엄";
			//System.out.println(selectBusClass);
		}
	};
	
	private ActionListener backButListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FORM jFrame = new FORM();
			jFrame.setVisible(true);
			mainFrame.setVisible(false);
		}
	};
	
	private ActionListener actionListener = new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {			
			  nowdate = String.valueOf(calYear)+"-"+String.valueOf(calMonth+1)+"-"+String.valueOf(calDayOfMon);
			  BusClassInfo.selectDate=nowdate;
			  BusClassInfo.selectEndRegion = selectEndRegion;
		      if(e.getSource() == TimeArea) {
		    	  timeSelect = "6"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea2){
		    	  timeSelect = "7"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea3) {
		    	  timeSelect = "8"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea4) {
		    	  timeSelect = "9"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea5) {
		    	  timeSelect = "10"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea6) {
		    	  timeSelect = "11"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea7) {
		    	  timeSelect = "12"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea8) {
		    	  timeSelect = "13"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea9) {
		    	  timeSelect = "14"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea10) {
		    	  timeSelect = "15"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea11) {
		    	  timeSelect = "16"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea12) {
		    	  timeSelect = "17"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea13) {
		    	  timeSelect = "18"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea14) {
		    	  timeSelect = "19"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea15) {
		    	  timeSelect = "20"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea16) {
		    	  timeSelect = "21"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea17) {
		    	  timeSelect = "22"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea18) {
		    	  timeSelect = "23"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == TimeArea19){
		    	  timeSelect = "24"; BusClassInfo.selectTime = timeSelect;}
		      else if(e.getSource() == CompleteBtn) {
		    	  nowdate = String.valueOf(calYear)+"-"+String.valueOf(calMonth+1)+"-"+String.valueOf(calDayOfMon);

		    	  BusDatabase data = new BusDatabase();
		    	  BusReservation busReservation = new BusReservation();
		    	  data.busSelect(selectEndRegion, BusClassInfo.bus1, nowdate, timeSelect);	
		    	  if (!BusClassInfo.selectBus) {
			            JOptionPane.showMessageDialog(null, "배차가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		    	  }else {
		    		  JOptionPane.showMessageDialog(null,"선택한 시간 : "+selectEndRegion+" "+BusClassInfo.bus1+" "+nowdate+" "+timeSelect+"시");
		    		  busReservation.setVisible(true);
			    	  mainFrame.setVisible(false);
		    	  }
		    	  //BusClassInfo.bus1= selectBusClass;
		    	  //System.out.println(BusClassInfo.bus1);
	
		    	 // busReservation.busClassInfo(selectBusClass);
		    	  //System.out.println("메모 쪽 print"+BusClassInfo.selectEndRegion+" "+ BusClassInfo.selectDate+" "+BusClassInfo.selectTime  );
		      };  
		      selectedDate.setText("선택한 날짜 : "+calYear+"-"+(calMonth+1)+"-"+calDayOfMon+" 선택한 시간 : "+timeSelect+"시");
		   }
	};
	public String getBusClass() {
		return selectBusClass;
	}
	
	private void focusToday(){//reset버튼을 클릭했을 시 현재날짜로 돌아옴
		if(today.get(Calendar.DAY_OF_WEEK) == 1) //현재요일이 일요일( DAY_OFWEEK == 1 )일때 
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
	}

	//날짜별로 토요일이면 폰트색 파랑 일요일이면 빨강 평일이면 검정
	private void showCal(){
		for(int i=0;i<CAL_HEIGHT;i++){
			for(int j=0;j<CAL_WIDTH;j++){
				String fontColor="black";
				if(j==0) fontColor="red";
				else if(j==6) fontColor="blue";
				dateButs[i][j].setText("<html><font color="+fontColor+">"+calDates[i][j]+"</font></html>");
				dateButs[i][j].removeAll();

				
				if(calDates[i][j] == 0) dateButs[i][j].setVisible(false);
				else dateButs[i][j].setVisible(true);
				
			}
		}
	}

	
	private class ListenForCalOpButtons implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == resetBut){  //클릭한 버튼의 필드 변수가 reset일 때
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
				timeSelect = "06";
				selectedDate.setText("선택한 날짜 : "+calYear+"-"+(calMonth+1)+"-"+calDayOfMon+" 선택한 시간 : "+timeSelect+"시");
			}
			//else if(e.getSource() == lYearBut) moveMonth(-12);
			else if(e.getSource() == lMonBut) moveMonth(-1);
			else if(e.getSource() == nMonBut) moveMonth(1);
			//else if(e.getSource() == nYearBut) moveMonth(12);
			curMMYYYYLab.setText(((calMonth+1)<10?"":"")+(calMonth+1)+" - "+calYear);
			showCal();
		}
	}
	private class listenForDateButs implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int k=0,l=0;
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					if(e.getSource() == dateButs[i][j]){ 
						k=i;
						l=j;
					}
				}
			}
			if(!(k ==0 && l == 0)) calDayOfMon = calDates[k][l]; //reset버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분
			selectedDate.setText("선택한 날짜 : "+calYear+"-"+(calMonth+1)+"-"+calDayOfMon+" 선택한 시간 : "+timeSelect+"시");	
		}
	}
}





















