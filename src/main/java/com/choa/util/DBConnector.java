package com.choa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {	
	public static void disConnect(PreparedStatement st, Connection con){
		try{
			st.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void disConnect(ResultSet rs, PreparedStatement st, Connection con){
		try{
			rs.close();
			disConnect(st, con);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
