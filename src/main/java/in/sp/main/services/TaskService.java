package in.sp.main.services;

import java.util.List;

import in.sp.main.entity.Task;
import in.sp.main.entity.User;

public interface TaskService {
	
	public List<Task> getAllTasks();
	
//	public Task createTask(Task task);
	
	public Task createTask(String title, User user);
	
	public List<Task> getTaskByUser(User user);
	
	public void deleteTask(Long id);
	
	public void toggleTask(Long id);
	
	public Task updatetask(Long id, String title);
	
	public Task getTask(Long id);
}
