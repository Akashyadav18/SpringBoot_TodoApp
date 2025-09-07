package in.sp.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entity.Task;
import in.sp.main.entity.User;
import in.sp.main.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task createTask(String title, User user) {
		Task task = new Task();
		task.setCompleted(false);
		task.setTitle(title);
		task.setUser(user);
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public void toggleTask(Long id) {
		Task task = taskRepository.findById(id).orElse(null);
		task.setCompleted(!task.isCompleted());
		taskRepository.save(task);
	}

	@Override
	public Task updatetask(Long id, String title) {
		Task task = taskRepository.findById(id).orElse(null);
		task.setTitle(title);
		return taskRepository.save(task);
	}

	@Override
	public Task getTask(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

	@Override
	public List<Task> getTaskByUser(User user) {
		return taskRepository.findByUser(user);
	}

//	@Override
//	public Task createTask(Task task) {
//		return taskRepository.save(task);
//	}
	
	

	
}
