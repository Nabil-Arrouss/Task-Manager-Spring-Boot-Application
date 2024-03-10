package manage.taskmanagement.controller;

import manage.taskmanagement.service.UsersService;
import java.util.List;
import java.util.NoSuchElementException;
import manage.taskmanagement.model.Users;
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
@RequestMapping("/usersOperations")
public class UsersController {

    @Autowired
    private UsersService userService;

    // CRUD JPA
    // Retrieve all users 
    @GetMapping(value = "/users/getAllUsersJPA")
    public List<Users> getAllUsersJPA() {
        return userService.getAllUsersJPA();
    }

    // Retrieve user by id  
    @GetMapping(value = "/users/getUserByIdJPA/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        try {
            Users user = userService.getUserByID(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE a new user 
    @PostMapping(value = "/users/addUserjpa")
    public void addUserJPA(@RequestBody Users user) {
        userService.createUserJPA(user);
    }

    // DELETE a user
    @DeleteMapping(value = "/users/deleteUserByIdJPA/{id}")
    public void deleteUserJPA(@PathVariable Integer id) {
        userService.deleteUserJPA(id);
    }

    // UPDATE a user jpa  
    @PutMapping(value = "/users/updateUserJPA/{id}")
    public ResponseEntity<Users> updateUserJPA(@RequestBody Users user, @PathVariable Integer id) {

        Users updatedUser = userService.updateUserJPA(id, user);

        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // CRUD SPQ
    // Retreive all users SPQ
    @GetMapping(value = "/users/getAllUsersSPQ")
    public List<Users> getAllUsersSPQ() {
        return userService.getAllUsersSPQ();
    }

    // Create a new user SPQ
    @PostMapping(value = "/users/addUserSPQ")
    public void addUserSPQ(@RequestBody Users user) {
        userService.addUserSPQ(user);
    }

    // Delete a user SPQ
    @DeleteMapping(value = "/users/deleteUserByIdSPQ/{id}")
    public void deletUserSPQ(@PathVariable Integer id) {
        userService.deleteUserSPQ(id);
    }

    // Update a user SPQ
    @PutMapping(value = "/users/updateUserSPQ/{id}")
    public void updateUserSPQ(@RequestBody Users user, @PathVariable Integer id) {
        userService.editUserSPQ(user, id);
    }

    // Additional method: Get users by role
    @GetMapping(value = "/users/getUsersByRole")
    public ResponseEntity<List<Users>> getUserByRole(@RequestParam String role) {

        List<Users> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    // Additional method: Get users with Incompleted Tasks
    @GetMapping("/users/withIncompleteTasks")
    public ResponseEntity<List<Users>> getUsersWithIncompleteTasks() {
        List<Users> users = userService.getUsersWithIncompleteTasks();
        return ResponseEntity.ok(users);
    }
}
