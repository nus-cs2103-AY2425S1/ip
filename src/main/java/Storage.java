import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    public void printTasks(TaskList task_list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            System.out.println(line);
            // Process the line for tasks

            if (line.startsWith("[T]")) {
                String toDoTask = line.substring(7);

                if (line.substring(3).startsWith("[X]")) {
                    task_list.addTask(new ToDo(true, toDoTask));

                } else if (line.substring(3).startsWith("[ ]")) {
                    task_list.addTask(new ToDo(false, toDoTask));

                }
            } else if (line.startsWith("[D]")) {
                String dlTask = line.substring(7)
                        .split(" \\(by: ")[0];
                String time = line.substring(7)
                        .split(" \\(by: ")[1];
                time = time.substring(0, time.length() - 1);

                if (line.substring(3).startsWith("[X]")) {
                    task_list.addTask(new Deadline(
                            true, dlTask, time));

                } else if (line.substring(3).startsWith("[ ]")) {
                    task_list.addTask(new Deadline(
                            false, dlTask, time));
                }
            } else {
                String eventTask = line.substring(7)
                        .split(" \\(from: ")[0];
                String time_start = line.substring(7)
                        .split(" \\(from: ")[1]
                        .split(" to: ")[0];
                String time_end = line.substring(7)
                        .split(" \\(from: ")[1]
                        .split(" to: ")[1];
                time_end = time_end.substring(0, time_end.length() - 1);

                if (line.substring(3).startsWith("[X]")) {
                    task_list.addTask(new Event(
                            true, eventTask, time_start, time_end));

                } else if (line.substring(3).startsWith("[ ]")) {
                    task_list.addTask(new Event(
                            false, eventTask, time_start, time_end));

                }
            }
        }
        s.close();
    }

    public void writeTasks(String task) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(task);
        fw.close();

    }

}
