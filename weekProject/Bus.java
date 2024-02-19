package weekProject;

import java.util.Date;
import lombok.Data;


@Data
public class Bus {
	private int busnum;
	private String endregion;
	private String company;
	private String accountName;
	private String accountPassword;
	private String busclass;
	private int seats;
	private int price;
	private String nowdate;
	private int busTime;
	private String accountcompany;
	private int money;
//	private boolean[] seatnum = new boolean[50];
	
//	public void Set_seatNum(boolean[] flag) {
//		this.seatnum = flag;
//	}
//	
//	
//	public boolean Get_seatNum() {
//		
//		return this.seatnum != null;
//		
//		
//	}
	
}
