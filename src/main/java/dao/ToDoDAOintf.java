package dao;
import java.util.List;
import beans.Register;
import beans.Task;
public interface ToDoDAOintf {
	public abstract int register(Register register);
	int login(String email,String password);
	public int addTask(int regId,Task task);
	public List<Task> findAllTasksByRegId(int regId);
	public boolean markTaskCompleted(int regId,int taskId);
	public String getFtNameByRegId(int regId);
	
	/*
	 * in java interface all
	 * variables are public static final by dafault and
	 * methods are public abstract 
	 */
}
