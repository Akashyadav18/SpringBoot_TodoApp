package in.sp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sp.main.entity.Task;
import in.sp.main.entity.User;
import in.sp.main.services.TaskService;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/taskPage")
	public String getAllTasks(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("sessionUser");
		if(loggedInUser == null)
		{
			return "redirect:/loginPage";
		}
		List<Task> tasks = taskService.getTaskByUser(loggedInUser);
		model.addAttribute("tasks", tasks);
//	   model.addAttribute("task", new Task()); //form k liye empty obj
		return "task";
	}

//	@PostMapping("/submitTask")
//	public String saveTask(@ModelAttribute("task") Task task) 
//	{
//		task.setCompleted(false);
//		taskService.createTask(task);
//		return "redirect:/taskPage";
//	}

	@PostMapping("/submitTask")
	public String saveTask(@RequestParam String title,HttpSession session, RedirectAttributes redirectAttributes) {
		try {
			User loggedInUser = (User) session.getAttribute("sessionUser");
			taskService.createTask(title, loggedInUser);
			redirectAttributes.addFlashAttribute("successMsg", "Task Added successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMsg", "Fail to Add Task");
		}
		return "redirect:/taskPage";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return "redirect:/taskPage";
	}

	@GetMapping("/toggle/{id}")
	public String toggleTask(@PathVariable Long id) {
		taskService.toggleTask(id);
		return "redirect:/taskPage";
	}

	@GetMapping("/updateForm/{id}")
	public String openEditPage(@PathVariable Long id, Model model) {
		Task task = taskService.getTask(id);
		if (task != null) {
			model.addAttribute("task", task);
			return "updateTask";
		} else {
			return "taskPage";
		}
	}

	@PostMapping("/update/{id}")
	public String updateTask(@PathVariable Long id, @RequestParam String title) {
		taskService.updatetask(id, title);
		return "redirect:/taskPage";
	}
}
