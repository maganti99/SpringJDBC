package com.nagesh.jdbc.test;



import java.util.List;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nagesh.jdbc.config.Config;
import com.nagesh.jdbc.dao.EmplDAOImpl;
import com.nagesh.jdbc.model.Empl;

public class runJdbcTemp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		//XML Config
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");*/
		EmplDAOImpl emplDAOImpl = context.getBean(EmplDAOImpl.class);
		
		List<Empl> empList = emplDAOImpl.getAll();
		
		for(Empl emp : empList)
		{
			System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getDept() + " " + emp.getSal());
		}
		
		context.close();
		
		Empl emp1 = emplDAOImpl.getById2(2, 3000);
		System.out.println(emp1.getName() + " " + emp1.getDept() + " " + emp1.getSal());
		
		
		System.out.println("count :" + emplDAOImpl.getCount());
		
		Empl e = new Empl();
		e.setId((short)5);
		e.setName("Harish");
		e.setDept("Fin");
		e.setSal(6700);
		        
		emplDAOImpl.save(e); 
				         
		
		
					
		

	}

}
