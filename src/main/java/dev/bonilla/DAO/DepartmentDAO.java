package dev.bonilla.DAO;

import java.util.List;

import dev.bonilla.model.Department;

public interface DepartmentDAO {
	// READ
	public Department getDepartment(int did);
	public Department getDepartment(String name);
	public int getDepartmentHead(int did);
	public List<Department> getAllDept();
	// CREATE
	public boolean createDepartment(int did, String name);
	// UPDATE
	public boolean updateDepartment(int did);
	// DELETE
	public boolean deleteDepartment(int did);
	
	
}
