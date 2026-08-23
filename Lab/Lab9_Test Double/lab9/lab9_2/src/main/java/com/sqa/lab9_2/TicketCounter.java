package com.sqa.lab9_2;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
public class TicketCounter {
	
	private Integer noCheckinCustomer = 0;
	
	public void changeTicketStatus(boolean isValidTicket) {
		
		if (isValidTicket) {
			noCheckinCustomer++;
		}			
	}
	
	public int getNoCheckinCustomer() {
		return noCheckinCustomer;
	}
	
	public void resetNoCheckinCustomer() {
		noCheckinCustomer = 0;
	}

}
