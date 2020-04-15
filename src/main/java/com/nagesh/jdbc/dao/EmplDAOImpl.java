package com.nagesh.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.nagesh.jdbc.model.Empl;
import org.springframework.jdbc.core.JdbcTemplate; 
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class EmplDAOImpl implements EmplDAO {
	
	//private DriverManagerDataSource driverManagerDataSource;
	
	private DataSource driverManagerDataSource;

	
	/*public void setDriverManagerDataSource(DriverManagerDataSource driverManagerDataSource) {
		this.driverManagerDataSource = driverManagerDataSource;
	}*/
	
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

	
}
