package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entity.Task;
import in.sp.main.entity.User;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> 
{
	List<Task> findByUser(User user);
}
