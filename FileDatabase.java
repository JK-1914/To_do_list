package database;

import model.Task;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileDatabase {

    private final String FILE = "tasks.txt";

    public int getNextId() {
        return loadAllTasks().size() + 1;
    }

    public void saveTask(Task task) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(task.getId() + "|" + task.getTitle() + "|" +
                     task.getDescription() + "|" +
                     task.getDueDate() + "|" + task.isCompleted());
            fw.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> loadAllTasks() {
        List<Task> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                Task t = new Task(
                        Integer.parseInt(p[0]),
                        p[1], p[2],
                        LocalDate.parse(p[3])
                );
                if (p[4].equals("true")) t.markCompleted();
                list.add(t);
            }
        } catch (Exception e) {}

        return list;
    }

    public void updateTaskStatus(int id, boolean completed) {
        List<Task> tasks = loadAllTasks();
        tasks.forEach(t -> { if (t.getId() == id) t.markCompleted(); });
        rewriteFile(tasks);
    }

    public void deleteTask(int id) {
        List<Task> tasks = loadAllTasks();
        tasks.removeIf(t -> t.getId() == id);
        rewriteFile(tasks);
    }

    private void rewriteFile(List<Task> tasks) {
        try (FileWriter fw = new FileWriter(FILE)) {
            for (Task t : tasks) {
                fw.write(t.getId() + "|" + t.getTitle() + "|" +
                         t.getDescription() + "|" +
                         t.getDueDate() + "|" + t.isCompleted());
                fw.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
