package manage.taskmanagement.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import manage.taskmanagement.model.Tasks;
import manage.taskmanagement.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 */
@Service
@Transactional
public class TasksService {

    @Autowired
    private TasksRepository taskRepo;

    //To manage or search data in relational database
    @PersistenceContext
    private EntityManager em;

    // CRUD JPA
    // Retrieve all tasks JPA 
    public List<Tasks> getAllTasksJPA() {
        return taskRepo.findAll();
    }

    // Retrieve task by id JPA 
    public Tasks getTaskByID(Integer id) {
        return taskRepo.findById(id).get();
    }

    // CREATE a new task JPA 
    public Tasks createTaskJPA(Tasks task) {
        return taskRepo.save(task); // repo is your JPA repository!
    }

    // DELETE a task JPA 
    public void deleteTaskJPA(Integer id) {
        taskRepo.deleteById(id);
    }

    // UPDATE a task JPA 
    public Tasks updateTaskJPA(Integer id, Tasks updatedTask) {
        Tasks existingTask = getTaskByID(id);
        if (existingTask != null) {
            // Update the specific attributes
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setDeadline(updatedTask.getDeadline());
            existingTask.setUserId(updatedTask.getUserId());

            // Save the updated task
            return taskRepo.save(existingTask);
        }
        return null;
    }

    // CRUD SPQ
    // Retreive all tasks SPQ
    public List<Tasks> getAllTasksSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllTasks");
        return spq.getResultList();
    }

    // Create task SPQ
    public void addTaskSPQ(Tasks tasks) {
        javax.persistence.StoredProcedureQuery spq = em.createStoredProcedureQuery("addTask");

        spq.registerStoredProcedureParameter("titleIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("creation_dateIN", Date.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("deadlineIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("user_idIN", Integer.class, ParameterMode.IN);

        //set values for each parameter
        spq.setParameter("titleIN", tasks.getTitle());
        spq.setParameter("descriptionIN", tasks.getDescription());
        spq.setParameter("statusIN", tasks.getStatus());
        spq.setParameter("creation_dateIN", tasks.getCreationDate());
        spq.setParameter("deadlineIN", tasks.getDeadline());
        spq.setParameter("user_idIN", tasks.getUserId());

        spq.execute();
    }

    // Delete a task SPQ
    public void deleteTaskSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteTask");

        spq.registerStoredProcedureParameter("task_idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("task_idIN", id);

        spq.execute();
    }

    // Update a task SPQ
    public void editTaskSPQ(Tasks tasks, Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateTask");

        spq.registerStoredProcedureParameter("titleIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("statusIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("deadlineIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("user_idIN", Integer.class, ParameterMode.IN);

        spq.registerStoredProcedureParameter("task_idIN", Integer.class, ParameterMode.IN);

        //set values for each parameter
        spq.setParameter("titleIN", tasks.getTitle());
        spq.setParameter("descriptionIN", tasks.getDescription());
        spq.setParameter("statusIN", tasks.getStatus());
        spq.setParameter("deadlineIN", tasks.getDeadline());
        spq.setParameter("user_idIN", tasks.getUserId());

        spq.setParameter("task_idIN", id);

        spq.execute();
    }

    // Additional method: Get Overdue Tasks
    public List<Tasks> getOverdueTasks() {
        return taskRepo.getOverdueTasks();
    }

    // Additional method: Get Tasks by User and Status
    public List<Tasks> getTasksByUserAndStatus(Integer userId, String status) {
        return taskRepo.getTasksByUserAndStatus(userId, status);
    }
}
