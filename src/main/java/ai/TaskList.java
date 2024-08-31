package ai;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import ai.exception.AiException;
import ai.task.Deadline;
import ai.task.Task;

/**
 * Manages the task list.
 */
public class TaskList {
    private Ui ui = new Ui();
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Saves the task list for the next time user boots up Ai.
     *
     * @param filePath Path where the task list is to be written.
     */
    public void save(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.stringData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Prints a list of Deadline Tasks that is due on the given date.
     *
     * @param date Date to be matched.
     */
    public void isDue(LocalDate date) {
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (temp instanceof Deadline) {
                Deadline tempDeadline = (Deadline) temp;

                if (tempDeadline.isEqual(date)) {
                    System.out.println(temp);
                }
            }
        }
    }

    /**
     * Prints all the tasks stored.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int i) throws AiException {
        tasks.remove(i);
    }
}
