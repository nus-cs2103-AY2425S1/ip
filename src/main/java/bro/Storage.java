package bro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String PATH;
    private final TaskList tasks;

    public Storage(String path, TaskList tasks) {
        this.PATH = path;
        this.tasks = tasks;
    }

    /**
     * Saves the current state of tasks to a file specified by the PATH constant.
     * The tasks are saved as a string representation.
     */
    public void saveToFile() {
        try {
            FileWriter f = new FileWriter(PATH);
            f.write(this.toString());
            f.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file specified by the PATH constant. The tasks are read line by line
     * and added to the task list. Each task's type and completion status are determined by the
     * contents of each line.
     *
     * @throws FileNotFoundException If the file specified by PATH does not exist.
     * @throws BroException          If there is an error in parsing the task details.
     */
    public void loadIn() throws FileNotFoundException, BroException {
        File f = new File(PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            switch (curr.charAt(3)) {
                case 'T':
                    assert (curr.contains("] ")) : "each task String must contain this";
                    tasks.addTodo(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                case 'E':
                    assert (curr.contains("] ")) : "each task String must contain this";
                    tasks.addEvent(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                case 'D':
                    assert (curr.contains("] ")) : "each task String must contain this";
                    tasks.addDeadline(curr.split("] ", 2)[1]);
                    if (curr.charAt(6) == 'X') {
                        tasks.get(tasks.size() - 1).mark();
                    }
                    break;
                default:
                    System.out.println("Error");
            }
        }
    }

    /**
     * Returns a string representation of the task list. Each task is represented on a new line
     * with its index and details formatted for saving/loading.
     *
     * @return A string that represents the current state of the task list, with each task formatted
     *         for storage.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            s.append(String.format("%d.%s\n", i + 1, tasks.get(i).toLoad()));
        }
        return s.toString();
    }
}
