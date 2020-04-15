package com.nagesh.jdbc.test;

import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class ClientApplication {

	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	 static final String DB_URL = "jdbc:sqlserver://L4754344:1433;DatabaseName=nagdb;integratedSecurity=true;";
	//   static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=master";
	//   static final String DB_URL = "jdbc:sqlserver://localhost;integratedSecurity=true";
	   
	//  Database credentials
	   static final String USER = "";
	   static final String PASS = "";   
	   
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
		      //STEP 2: Register JDBC driver
		     // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL);
		      
		      System.out.println("Connection succesfull...");

		      //STEP 4: Execute a query
		      
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT Emp_id, Emp_name FROM Employee";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("Emp_id");
		         
		         String name = rs.getString("Emp_name");
		         

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Name: " + name);
		         System.out.println();
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		
		
		
	}

}
