# Task Management System

## Project Overview

### Project Name
Task Management System

### Database Name
'task_management_db'

### Database Tables

#### 1. Users Table
- Fields:
  - `user_id` (Primary Key)
  - `username`  (varchar)
  - `password`  (varchar)
  - `full_name`  (varchar)
  - `email`  (varchar)
  - `role`  (varchar)

#### 2. Tasks Table
- Fields:
  - `task_id` (Primary Key)
  - `title`  (varchar)
  - `description`  (TEXT)
  - `status`  (varchar)
  - `creation_date`  (datetime)
  - `deadline`  (varchar)
  - `user_id` (Foreign key)

#### 3. TasksHistory
- Fields:
  - `task_history_id` (Primary Key)
  - `task_id`  (Foreign key)
  - `assigned_to_user_id` (Foreign key)
  - `assigned_by_user_id`  (Foreign key)
  - `assignment_date`    (datetime)

### Relationships
1. Users Table and Tasks Table: ONE-TO-MANY relationship
2. Tasks Table and TasksHistory: ONE-TO-MANY relationship
3. Users Table and TasksHistory: MANY-TO-ONE relationship

## Project Description

This Spring Boot project effectively manages and keeps track of tasks within a team or company. The components of the project are organized into different packages, including services, controllers, models, and repositories. The three main models represent the database entities: Users, Tasks, and TaskHistory. Following the MVC Architecture ensures a structured and organized approach to handling operations, promoting modularity and maintainability.

### Workflow

- Users register by providing their username, password, full_name, email, and role.
- When a User creates a task, they must assign it to themselves or another user.
- Users can view or update their profile details.
- Users can reassign tasks to other users by updating the `user_id` in the Tasks Table (a new record is registered automatically in TaskHistory).
- Creating, updating, or deleting a task results in a new record in TaskHistory, keeping track of tasks, assignments, and involved users.
- TaskHistory Table maintains a record of task assignment history.

## Entity Model Roles

1. Users can create new tasks by specifying details such as title, description, status, creation_date, and deadline.
2. Tasks are associated with users through the `user_id` foreign key, indicating responsibility for each task.
3. Tasks are assigned to users through the `user_id` in the Tasks Table.
4. Users can assign tasks to themselves or to other users.
5. Users can update the status of tasks, reflecting their progress.
6. Users can reassign tasks to other users by updating the `user_id` in the Tasks Table.
7. When a reassignment occurs, it is recorded in the TasksHistory Table automatically.

## Dependencies

- Spring Boot
- Swagger 2
- Swagger UI
- Persistence
- MySQL Connector J
- Spring AOP
- Security
- PHPMyAdmin (for database management)

## Application Implementation Steps

1. Created the database with 3 Models (users, tasks, and taskshistory) in PHPMyAdmin.
2. Created Controller classes for each model to handle API endpoints and added CRUD operations (JPA, SPQ) with 5 additional methods.
3. Created Service classes for each model to be called by the Controllers to implement the business logic behind CRUD operations and the additional 5 methods (2 for users entity, 2 for task entity, and 1 for taskshistory).
4. Created repositories for each model to handle database queries and interact with it (controllers, services, repositories, logging aspect are stored in different packages).
5. Established a connection between the program and the database to interact with it and create models from the database directly.
6. Added the necessary dependencies (Spring Boot, Swagger 2, Swagger UI, persistence, mysql-connector-j, spring AOP, and security).
7. Added a logging aspect for security. (Username and password are in the application.properties [Username: TaskManager || password: 0000]).
8. Added 2 triggers in the database to handle automatically added records in the taskshistory table.
9. Exported the database.

**Note:** Use Swagger UI to test your functions.
