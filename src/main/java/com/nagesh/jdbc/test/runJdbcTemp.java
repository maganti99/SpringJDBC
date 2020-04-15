package com.nagesh.jdbc.test;



import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.nagesh.jdbc.dao.EmplDAOImpl;
import com.nagesh.jdbc.model.Empl;

public class runJdbcTemp {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		EmplDAOImpl emplDAOImpl = context.getBean(EmplDAOImpl.class);
		
		List<Empl> empList = emplDAOImpl.getAll();
		
		for(Empl emp : empList)
		{
			System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getDept() + " " + emp.getSal());
		}
		
		context.close();

	}

}
