/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empmgmt.dao;

import empmgmt.dbutil.DBConnection;
import empmgmt.pojo.EmpPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Amit
 */
public class EmpDAO {
     public static boolean addEmp(EmpPojo e)throws SQLException
    {
        
           
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps =conn.prepareStatement("insert into emp values(?,?,?)");
    ps.setInt(1,e.getEmpno());
    ps.setString(2, e.getEname());
    ps.setDouble(3,e.getSal());
    int count=ps.executeUpdate();
    if(count>0)
   
        return true;
        else 
        return false;
  } 
     
     public static ArrayList<EmpPojo> getAllemp() throws SQLException
{
  Connection conn=DBConnection.getConnection();
  Statement st=conn.createStatement();
  ResultSet rs=st.executeQuery("Select * from emp");
  ArrayList<EmpPojo> empList=new ArrayList<>();
  while(rs.next())
  {
      int eno=rs.getInt(1);
      String ename=rs.getString(2);
      double sal=rs.getDouble(3);
       EmpPojo obj=new EmpPojo(eno,ename,sal);
      empList.add(obj);
  }
  return empList;
}
     public static EmpPojo findEmpBId(int eno) throws SQLException
     {
        Connection conn=DBConnection.getConnection();
  PreparedStatement ps=conn.prepareStatement("select * from emp where eno=?");
  ps.setInt(1, eno);
  ResultSet rs= ps.executeQuery();
  EmpPojo e=null;
  if(rs.next())
  {
    e=new EmpPojo();
   e.setEname(rs.getString(2)); 
   e.setSal(rs.getDouble(3));
   e.setEmpno(eno);
  }
  return e;
     }
     public static boolean UpdateEmp(EmpPojo e)throws SQLException

    {
             Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement("Update emp set ename=?, esal=? where eno=?");
        ps.setString(1, e.getEname());

        ps.setDouble(2, e.getSal());

        ps.setInt(3,e.getEmpno());
        int count=ps.executeUpdate();

        if(count==0)

            return false;

        else

            return true;

    }
     public static boolean deleteEmp(int i)throws SQLException

    {

        Connection con=DBConnection.getConnection();

        PreparedStatement ps=con.prepareStatement("delete from emp where eno=?");

        ps.setInt(1,i);

        int count=ps.executeUpdate();

        if(count==0)

            return false;

        else

            return true;
    }
}
