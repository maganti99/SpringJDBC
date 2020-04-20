package com.nagesh.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.nagesh.jdbc.model.Empl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component; 
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Component
public class EmplDAOImpl implements EmplDAO {
	
	//private DriverManagerDataSource driverManagerDataSource;
	
	private DataSource driverManagerDataSource;

	
	/*public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
		this.driverManagerDataSource = driverManagerDataSource;
	}*/
	
	public EmplDAOImpl()
	{
		System.out.println("EmplDAOImpl component created");
	}
	
	@Autowired
	public void setDriverManagerDataSource(DataSource driverManagerDataSource) {
		this.driverManagerDataSource = driverManagerDataSource;
	}

	public List<Empl> getAll() {
		String query = "SELECT EMP_id, Emp_name, Emp_dept, Emp_sal FROM Employee";
		JdbcTemplate jdbctemplate = new JdbcTemplate(driverManagerDataSource);
		
		List<Empl> empList = new ArrayList<Empl>();
		
		List<Map<String, Object>> emprows =   jdbctemplate.queryForList(query);
		
		for(Map<String, Object> emprow: emprows)
		{
			Empl empl = new Empl();
			empl.setId((Short)emprow.get("Emp_id"));
			empl.setName(emprow.get("Emp_name").toString());
			empl.setDept(emprow.get("Emp_dept").toString());
			empl.setSal((Integer)emprow.get("Emp_sal"));
			
			empList.add(empl);
			
		}				
		return empList;
	}

	public Empl getById(int id) {
		String query = "SELECT Emp_name, Emp_dept, Emp_sal FROM Employee where EMP_id = ?";
		JdbcTemplate jdbctemplate = new JdbcTemplate(driverManagerDataSource);
		
		//2nd way - creating object fo RowMapper interface
		RowMapper<Empl> myClass = new RowMapper<Empl>()
				{
			public Empl mapRow(ResultSet rs, int rowNum) throws SQLException {
				Empl emp = new Empl();
				emp.setName(rs.getString("Emp_name"));
				emp.setDept(rs.getString("Emp_dept"));
				emp.setSal(rs.getInt("Emp_sal"));
							
				return emp;
			
				};
			};
		
		//Empl emp = jdbctemplate.queryForObject(query, new Object[]{id}, myClass);			
			
		//3rd way using Ananomus class of RowMapper					
			Empl emp = jdbctemplate.queryForObject(query, new Object[]{id}, new RowMapper<Empl>(){
		     public Empl mapRow(ResultSet rs, int rowNum) throws SQLException {
			 Empl emp = new Empl();
			 emp.setName(rs.getString("Emp_name"));
			 emp.setDept(rs.getString("Emp_dept"));
			 emp.setSal(rs.getInt("Emp_sal"));
			 System.out.println("rowNum :" + rowNum);
			 return emp;		
			}});	
			
		return emp;
	}
	
	//Count query
	public int getCount()
	{
		JdbcTemplate jdbctemplate = new JdbcTemplate(driverManagerDataSource);
		return jdbctemplate.queryForObject("SELECT count(*) FROM Employee", Integer.class);
				
	}
	
	//Save, Insert using Named Parameter
	public void save(Empl e)
	{
		NamedParameterJdbcTemplate namedParameterjdbctemplate = new NamedParameterJdbcTemplate(driverManagerDataSource);
		
		SqlParameterSource parms = new MapSqlParameterSource()
				.addValue("empid", e.getId())
				.addValue("empname", e.getName())
				.addValue("empdep", e.getDept())
				.addValue("empsal", e.getSal());
		
		namedParameterjdbctemplate.update("INSERT INTO Employee (Emp_id ,Emp_name, Emp_dept, Emp_sal) values (:empid, :empname, :empdep, :empsal)" 
				, parms);
		
	}
	


//Select using named parmeters
public Empl getById2(int id, int sal) {
	String query = "SELECT Emp_name, Emp_dept, Emp_sal FROM Employee where EMP_id = :empid and Emp_sal > :empsal";
	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(driverManagerDataSource);
	
	Map<String,Integer> map=new HashMap<String,Integer>();  
	map.put("empid",id);
	map.put("empsal",sal);
	
			
		Empl emp = namedParameterJdbcTemplate.queryForObject(query, map, new RowMapper<Empl>(){
	     public Empl mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Empl emp = new Empl();
		 emp.setName(rs.getString("Emp_name"));
		 emp.setDept(rs.getString("Emp_dept"));
		 emp.setSal(rs.getInt("Emp_sal"));
		 System.out.println("rowNum :" + rowNum);
		 return emp;		
		}});	
		
	return emp;
}
}

//1st way - Breaking down the code, separate class
class MyClass implements RowMapper<Empl>
{		
		public Empl mapRow(ResultSet rs, int rowNum) throws SQLException {
			Empl emp = new Empl();
			emp.setName(rs.getString("Emp_name"));
			emp.setDept(rs.getString("Emp_dept"));
			emp.setSal(rs.getInt("Emp_sal"));
			return emp;
		} 
	
}