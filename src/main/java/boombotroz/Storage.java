package boombotroz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Assigns file path to variable.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Prints all the tasks stored in the text file.
     *
     * @param taskList list of all the tasks.
     * @return List of all the tasks.
     * @throws FileNotFoundException If no such file in directory.
     */
    public String printTasks(TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        String s = "";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            s += line + "\n";
        }
        return s;
    }

    /**
     * Loads all the tasks stored in the text file into the task list.
     *
     * @param taskList list of all the tasks.
     * @throws FileNotFoundException If no such file in directory.
     */
    public void loadTasks(TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            // Process the line for tasks
            if (line.startsWith("[T]")) {
                String toDoTask = line.substring(10);
                int priority = Integer.parseInt(String.valueOf(line.charAt(7)));
                if (line.substring(3).startsWith("[X]")) {
                    taskList.addTask(new ToDo(true, toDoTask, priority));
                } else if (line.substring(3).startsWith("[ ]")) {
                    taskList.addTask(new ToDo(false, toDoTask, priority));
                }
            } else if (line.startsWith("[D]")) {
                String dlTask = line.substring(10).split(" \\(by: ")[0];
                String time = line.substring(10).split(" \\(by: ")[1];
                time = time.substring(0, time.length() - 1);
                int priority = Integer.parseInt(String.valueOf(line.charAt(7)));

                if (line.substring(3).startsWith("[X]")) {
                    taskList.addTask(new Deadline(true, dlTask, time, priority));

                } else if (line.substring(3).startsWith("[ ]")) {
                    taskList.addTask(new Deadline(false, dlTask, time, priority));
                }
            } else if (line.startsWith("[E]")) {
                String eventTask = line.substring(10)
                        .split(" \\(from: ")[0];
                String timeStart = line.substring(10)
                        .split(" \\(from: ")[1]
                        .split(" to: ")[0];
                String timeEnd = line.substring(10)
                        .split(" \\(from: ")[1]
                        .split(" to: ")[1];
                timeEnd = timeEnd.substring(0, timeEnd.length() - 1);
                int priority = Integer.parseInt(String.valueOf(line.charAt(7)));

                if (line.substring(3).startsWith("[X]")) {
                    taskList.addTask(new Event(true, eventTask, timeStart, timeEnd, priority));

                } else if (line.substring(3).startsWith("[ ]")) {
                    taskList.addTask(new Event(false, eventTask, timeStart, timeEnd, priority));

                }
            }
        }
        Collections.sort(taskList.getTaskList(), Comparator.comparingInt(Task::getPriority).reversed());
        scanner.close();
    }

    /**
     * Writes all the tasks into the text file.
     *
     * @param tasks tasks to be written
     * @throws IOException If writing to file has issue.
     */
    public void writeTasks(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();

    }

}
