package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleById(Integer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			//SQL para realizar a consulta
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department On seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name"
					);
			//Seta a consulta
			st.setInt(1, department.getId());
			
			// Executa a consulta
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			//Por que foi usado a estrutura de Hash Map?
			Map<Integer, Department> map = new HashMap<> ();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				//Instancia um Departamento na memória para composição entre Seller e Department
				
				if(dep == null) { 
					 dep = instantiateDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), dep);
				}
				
				//Instancia Seller na memória para consulta
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
				
			}
			
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			//SQL para realizar a consulta
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department On seller.DepartmentId = department.Id WHERE seller.Id = ?"
					);
			//Seta a consulta
			st.setInt(1, id);
			
			// Executa a consulta
			rs = st.executeQuery();
			if(rs.next()) {
				
				//Instancia um Departamento na memória para composição entre Seller e Department
				Department dep = instantiateDepartment(rs);
				
				//Instancia Seller na memória para consulta
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException{
		
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
