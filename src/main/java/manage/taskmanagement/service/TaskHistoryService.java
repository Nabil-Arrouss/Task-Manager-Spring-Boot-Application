package manage.taskmanagement.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import manage.taskmanagement.model.Taskshistory;
import manage.taskmanagement.repository.TaskHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 */
@Service
@Transactional
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepo;

    //To manage or search data in relational database
    @PersistenceContext
    private EntityManager em;

    // Retrieve all tasks history JPA  
    public List<Taskshistory> getAllTasksHistoryJPA() {
        return taskHistoryRepo.findAll();
    }

    // Retrieve task history record by id JPA 
    public Taskshistory getTaskHistoryByID(Integer id) {
        return taskHistoryRepo.findById(id).get();
    }

    // Retreive all tasks history records SPQ
    public List<Taskshistory> getAllTasksHistorySPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllTasksHistory");
        return spq.getResultList();
    }

    // Additional method: Get Task History by Assigned User
    public List<Taskshistory> getTaskHistoryByAssignedUser(Integer userId) {
        return taskHistoryRepo.getTaskHistoryByAssignedUser(userId);
    }

}
