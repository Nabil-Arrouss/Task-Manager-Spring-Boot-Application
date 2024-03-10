package manage.taskmanagement.controller;

import java.util.List;
import java.util.NoSuchElementException;
import manage.taskmanagement.model.Tasks;
import manage.taskmanagement.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nabil
 */
@RestController
@RequestMapping("/tasksOperations")
public class TasksController {

    @Autowired
    private TasksService taskService;

    // CRUD JPA
    // Retrieve all tasks JPA 
    @GetMapping(value = "/tasks/getAllTasksJPA")
    public List<Tasks> getAllTasksJPA() {
        return taskService.getAllTasksJPA();
    }

    // Retrieve task by id JPA 
    @GetMapping(value = "/tasks/getTaskByIdJPA/{id}")
    public ResponseEntity<Tasks> getTask(@PathVariable Integer id) {
        try {
            Tasks task = taskService.getTaskByID(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE a new task JPA 
    @PostMapping(value = "/tasks/addTaskJPA")
    public void addTask(@RequestBody Tasks task) {

        taskService.createTaskJPA(task);
    }

    // DELETE a task JPA 
    @DeleteMapping(value = "/tasks/deleteTaskByIdJPA/{id}")
    public void deleteTaskJPA(@PathVariable Integer id) {
        taskService.deleteTaskJPA(id);
    }

    // UPDATE a task JPA 
    @PutMapping(value = "/tasks/updateTaskJPA/{id}")
    public ResponseEntity<Tasks> updateTaskJPA(@RequestBody Tasks task, @PathVariable Integer id) {
        Tasks updatedTask = taskService.updateTaskJPA(id, task);

        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CRUD SPQ
    // Retreive all tasks SPQ
    @GetMapping(value = "/tasks/getAllTasksSPQ")
    public List<Tasks> getAllTasksSPQ() {
        return taskService.getAllTasksSPQ();
    }

    // Create a new task SPQ
    @PostMapping(value = "/tasks/addTaskSPQ")
    public void addTaskSPQ(@RequestBody Tasks task) {
        taskService.addTaskSPQ(task);
    }

    // Delete a tasks SPQ
    @DeleteMapping(value = "/tasks/deleteTaskByIdSPQ/{id}")
    public void deleteTaskSPQ(@PathVariable Integer id) {
        taskService.deleteTaskSPQ(id);
    }

    // Update a task SPQ
    @PutMapping(value = "/tasks/updateTaskSPQ/{id}")
    public void updateTaskSPQ(@RequestBody Tasks task, @PathVariable Integer id) {
        taskService.editTaskSPQ(task, id);
    }

    // Additional method: Get Tasks Over Due
    @GetMapping(value = "/tasks/GetTasksOverdue")
    public ResponseEntity<List<Tasks>> getOverdueTasks() {
        List<Tasks> overdueTasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(overdueTasks);
    }

    // Additional method: Get Tasks by User and Status
    @GetMapping(value = "/tasks/byUserAndStatus")
    public ResponseEntity<List<Tasks>> getTasksByUserAndStatus(@RequestParam Integer userId, @RequestParam String status) {
        List<Tasks> tasks = taskService.getTasksByUserAndStatus(userId, status);
        return ResponseEntity.ok(tasks);
    }
}
