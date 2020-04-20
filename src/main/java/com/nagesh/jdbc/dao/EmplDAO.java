package com.nagesh.jdbc.dao;

import java.util.List;
import com.nagesh.jdbc.model.Empl;

public interface EmplDAO {
	
	public List<Empl> getAll();
	public Empl getById(int id);

}
