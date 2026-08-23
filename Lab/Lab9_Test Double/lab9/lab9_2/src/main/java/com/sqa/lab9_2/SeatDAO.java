package com.sqa.lab9_2;
/*
นายปฏิภาณ มะนิลทิพย์ 673380589-2 Sec 2
*/
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
	
	public List<String> fetchAvailableSeats() throws SQLException {
        List<String> availableSeats = new ArrayList<String>();
        Connection conn = DriverManager.getConnection("DATABASE_URL");
        Statement statement = conn.createStatement();
        ResultSet rs;
        rs = statement.executeQuery("SELECT * FROM ROOMS WHERE AVAILABLE like '1'");
        while(rs.next()){
            availableSeats.add(rs.getString("Seat name"));
        }
        return availableSeats;
    }
}