package service;


import model.Task;
import database.FileDatabase;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private FileDatabase db;

    public TaskService() {
        db = new FileDatabase();
    }

    public void addTask(String title, String description, LocalDate dueDate) {
        Task task = new Task(db.getNextId(), title, description, dueDate);
        db.saveTask(task);
    }

    public List<Task> getAllTasks() {
        return db.loadAllTasks();
    }

    public void markTaskCompleted(int id) {
        db.updateTaskStatus(id, true);
    }

    public void deleteTask(int id) {
        db.deleteTask(id);
    }
}
