package manage.taskmanagement.repository;

import java.util.List;
import manage.taskmanagement.model.Taskshistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author nabil
 */
public interface TaskHistoryRepository extends JpaRepository<Taskshistory, Integer> {

    @Query("SELECT th FROM Taskshistory th WHERE th.assignedToUserId = :assignedToUserId")
    List<Taskshistory> getTaskHistoryByAssignedUser(@Param("assignedToUserId") Integer assignedToUserId);

}
