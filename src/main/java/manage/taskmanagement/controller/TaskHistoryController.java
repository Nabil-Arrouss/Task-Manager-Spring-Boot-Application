package manage.taskmanagement.controller;

import java.util.List;
import java.util.NoSuchElementException;
import manage.taskmanagement.model.Taskshistory;
import manage.taskmanagement.service.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping("/taskHistoryOperations")
public class TaskHistoryController {

    @Autowired
    private TaskHistoryService taskHistoryService;

    // Retrieve all tasks history records JPA 
    @GetMapping(value = "/taskHistory/getAllTasksHistoryJPA")
    public List<Taskshistory> getAllTasksHistoryJPA() {
        return taskHistoryService.getAllTasksHistoryJPA();
    }

    // Retrieve task history record by id JPA 
    @GetMapping(value = "/taskHistory/getTaskHistoryByIdJPA/{id}")
    public ResponseEntity<Taskshistory> getTaskHistory(@PathVariable Integer id) {
        try {
            Taskshistory tasksHistory = taskHistoryService.getTaskHistoryByID(id);
            return new ResponseEntity<>(tasksHistory, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  
    // Additional method: Retreive all tasks history records SPQ
    @GetMapping(value = "/taskHistory/getAllTasksHistorySPQ")
    public List<Taskshistory> getAllTasksHistorySPQ() {
        return taskHistoryService.getAllTasksHistorySPQ();
    }

    // Additional method: Get Task History by Assigned User
    @GetMapping(value = "/taskHistory/{userId}")
    public ResponseEntity<List<Taskshistory>> getTaskHistoryByAssignedUser(@PathVariable("userId") Integer assignedToUserId) {
        List<Taskshistory> taskHistory = taskHistoryService.getTaskHistoryByAssignedUser(assignedToUserId);
        return ResponseEntity.ok(taskHistory);
    }

}
