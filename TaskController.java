package controller;


import model.Task;
import database.FileDatabase;
import service.TaskService;
import utils.ValidationUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class TaskController {
    private Scanner sc = new Scanner(System.in);
    private TaskService service = new TaskService();

    public void start() {
        while (true) {
            System.out.println("\n--- SMART TO-DO LIST ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: completeTask(); break;
                case 4: deleteTask(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addTask() {
        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Due Date (YYYY-MM-DD): ");
        String input = sc.nextLine();
        LocalDate date = ValidationUtil.parseDate(input);

        service.addTask(title, desc, date);
        System.out.println("Task Added!");
    }

    private void viewTasks() {
        service.getAllTasks().forEach(System.out::println);
    }

    private void completeTask() {
        System.out.print("Enter task ID: ");
        int id = sc.nextInt();
        service.markTaskCompleted(id);
    }

    private void deleteTask() {
        System.out.print("Enter task ID: ");
        int id = sc.nextInt();
        service.deleteTask(id);
    }
}

