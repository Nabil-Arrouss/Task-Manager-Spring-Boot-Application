package manage.taskmanagement.service;

import manage.taskmanagement.repository.UsersRepository;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import manage.taskmanagement.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nabil
 */
@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepository repo;

    //To manage or search data in relational database
    @PersistenceContext
    private EntityManager em;

    // CRUD JPA
    // Retrieve all users  
    public List<Users> getAllUsersJPA() {
        return repo.findAll();
    }

    // Retrieve user by id
    public Users getUserByID(Integer id) {
        return repo.findById(id).get();
    }

    // Create a user  
    public Users createUserJPA(Users user) {
        return repo.save(user); // repo is your JPA repository!
    }

    // DELETE a user  
    public void deleteUserJPA(Integer id) {
        repo.deleteById(id);
    }

    // UPDATE a user by id  
    public Users updateUserJPA(Integer id, Users updatedUser) {
        Users existingUser = getUserByID(id);
        if (existingUser != null) {
            // Update user properties
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRole(updatedUser.getRole());
            return repo.save(existingUser);
        }
        return null;
    }

    // CRUD SPQ
    // Retreive all users SPQ
    public List<Users> getAllUsersSPQ() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUsers");
        return spq.getResultList();
    }

    // Create a User SPQ
    public void addUserSPQ(Users users) {
        javax.persistence.StoredProcedureQuery spq = em.createStoredProcedureQuery("addUser");

        spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("full_nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("roleIN", String.class, ParameterMode.IN);

        //set values for each parameter
        spq.setParameter("usernameIN", users.getUsername());
        spq.setParameter("passwordIN", users.getPassword());
        spq.setParameter("full_nameIN", users.getFullName());
        spq.setParameter("emailIN", users.getEmail());
        spq.setParameter("roleIN", users.getRole());

        spq.execute();
    }

    // Delete a User SPQ
    public void deleteUserSPQ(Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteUser");

        spq.registerStoredProcedureParameter("user_idIN", Integer.class, ParameterMode.IN);
        spq.setParameter("user_idIN", id);

        spq.execute();
    }

    // Update a user SPQ
    public void editUserSPQ(Users user, Integer id) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUser");
        spq.registerStoredProcedureParameter("usernameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("full_nameIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("roleIN", String.class, ParameterMode.IN);

        spq.registerStoredProcedureParameter("user_idIN", Integer.class, ParameterMode.IN);

        spq.setParameter("usernameIN", user.getUsername());
        spq.setParameter("passwordIN", user.getPassword());
        spq.setParameter("full_nameIN", user.getFullName());
        spq.setParameter("emailIN", user.getEmail());
        spq.setParameter("roleIN", user.getRole());

        spq.setParameter("user_idIN", id);

        spq.execute();
    }

    // Additional method: Get users by role
    public List<Users> getUsersByRole(String role) {
        return repo.getUsersByRole(role);
    }

    // Additional method: Get users with Incompleted Tasks
    public List<Users> getUsersWithIncompleteTasks() {
        return repo.getUsersWithIncompleteTasks();
    }
}
