package manage.taskmanagement.repository;

import java.util.List;
import manage.taskmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author nabil
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.role = :role")
    List<Users> getUsersByRole(@Param("role") String role);

    @Query("SELECT DISTINCT u FROM Users u JOIN u.tasksCollection t WHERE t.status != 'Completed'")
    List<Users> getUsersWithIncompleteTasks();

}
