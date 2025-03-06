package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Register;
import beans.Task;
import factory.DBConn;

public class ToDoDAOImpl implements ToDoDAOintf {
	Connection con;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt1,pstmt2,pstmt3,pstmt4,pstmt5;
	private ToDoDAOImpl() {
		try {
			con=DBConn.con();
			stmt=con.createStatement();
			pstmt1=con.prepareStatement("Insert into register values(?,?,?,?,?,?,?)");
			pstmt2=con.prepareStatement("Insert into tasks values(?,?,?,?,?)");
			pstmt3=con.prepareStatement("Insert into taskid_pks values(?,?)");
			pstmt4=con.prepareStatement("Update taskid_pks set taskId=? where regId=?");
			pstmt5=con.prepareStatement("Update tasks set taskStatus=3 where regId=? and taskId=?");	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	static ToDoDAOintf dao=null;
	public static ToDoDAOintf getInstance() {
		if(dao==null)
			dao=new ToDoDAOImpl();
		return dao;
	}

	@Override
	public int register(Register register) {
		int regId=0;
		try {
			rs=stmt.executeQuery("select max(regId) from register");
			if(rs.next()) {
				regId=rs.getInt(1);
			}
			regId++;
			pstmt1.setInt(1,  regId);
			pstmt1.setString(2,register.getFtName());
			pstmt1.setString(3,register.getlName());
			pstmt1.setString(4,register.getEmail());
			pstmt1.setString(5,register.getPassword());
			pstmt1.setLong(6,register.getPhone());
			pstmt1.setString(7,register.getAddress());
			int i=pstmt1.executeUpdate();
			if(i==1) {
				System.out.println("register Inserted");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return regId;
	}
	

	@Override
	public int login(String email, String pass) {
		int regId=0;
		try {
			rs=stmt.executeQuery("select regId from register where email='"+email+"' and pass='"+pass+"'");
			if(rs.next()) {
				regId=rs.getInt(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return regId;
	}

	@Override
	public int addTask(int regId, Task task) {
		int taskId=0;
		boolean isNew=true;
		try {
			rs=stmt.executeQuery("select taskId from taskId_pks where regid="+regId);
			if(rs.next()) {
				taskId=rs.getInt(1);
				isNew=false;
			}
			taskId++;
			int i,j=0;
			con.setAutoCommit(false);
			pstmt2.setInt(1, taskId);
			pstmt2.setString(2, task.getTaskName());
			pstmt2.setString(3, task.getTaskDate());
			pstmt2.setInt(4,task.getTaskStatus());
			pstmt2.setInt(5, task.getRegId());
			i=pstmt2.executeUpdate();
			if(isNew==true) {
				pstmt3.setInt(1, regId);
				pstmt3.setInt(2,taskId);
				j=pstmt3.executeUpdate();
			}
			else {
				pstmt4.setInt(1,taskId);
				pstmt4.setInt(2, regId);
				j=pstmt4.executeUpdate();
			}
			if(i==1 && j==1) {
				con.commit();
				System.out.println("TX successfully inserted");
				
			}
			else {
				con.rollback();
				System.out.println("TX failed");
			}
			}
		catch(Exception e) {
				e.printStackTrace();
		}
		return taskId;
	}

	@Override
	public List<Task> findAllTasksByRegId(int regId) {
		List<Task> taskList=new ArrayList<Task>();
		try {
			rs=stmt.executeQuery("Select * from tasks where regId="+regId);
			while(rs.next()) {
				Task task=new Task(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
				taskList.add(task);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	@Override
	public boolean markTaskCompleted(int regId, int taskId) {
		boolean flag=false;
		try { 
			pstmt5.setInt(1,regId);
			pstmt5.setInt(2, taskId);
			pstmt5.executeUpdate();
			flag=true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public String getFtNameByRegId(int regId) {
		String fname=null;
		try {
			rs=stmt.executeQuery("select fname from register where regid="+regId);
			if(rs.next()) {
				fname=rs.getString(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		return fname;
	}
	

}
