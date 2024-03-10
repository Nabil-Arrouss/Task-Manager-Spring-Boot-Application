package manage.taskmanagement.repository;

import java.util.List;
import manage.taskmanagement.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author nabil
 */
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

    @Query("SELECT t FROM Tasks t WHERE t.deadline < CURRENT_DATE AND t.status != 'Completed'")
    List<Tasks> getOverdueTasks();

    @Query("SELECT t FROM Tasks t WHERE t.userId = :userId AND t.status = :status")
    List<Tasks> getTasksByUserAndStatus(@Param("userId") Integer userId, @Param("status") String status);

}
