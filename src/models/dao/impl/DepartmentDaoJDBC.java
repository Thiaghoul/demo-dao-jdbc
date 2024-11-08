package models.dao.impl;

import db.DB;
import db.DbException;
import models.dao.DepartmentDao;
import models.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection connection) {
        conn = DB.getConnection();
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("INSERT INTO department" +
                            "(Name)" +
                            "VALUES " +
                            "(?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setID(id);
                }
                DB.closeResultSet(rs);

            }else{
                throw new DbException("Insert failed! Name of the department already exists.");

            }

        }catch(SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);

        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE department\n" +
                    "SET Name = ?" +
                    "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setInt(2, obj.getID());

            st.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);

        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM department "+
                    "WHERE Id = ?");
            st.setInt(1, id);

            int rows = st.executeUpdate();

            if(rows == 0){
                throw new DbException("Id not found! No rows affected!");
            }

        }catch(SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);

        }
    }

    @Override
    public Department findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * from department\n" +
                    "WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){

                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;

        }catch(SQLException e){
            throw new DbException(e.getMessage());

        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement("SELECT * FROM department");

            rs = st.executeQuery();

            List<Department> deps = new ArrayList<>();

            while(rs.next()){
                deps.add(instantiateDepartment(rs));
            }
            return deps;

        }catch (SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setID(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
