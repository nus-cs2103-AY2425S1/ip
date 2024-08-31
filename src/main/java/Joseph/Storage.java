package Joseph;

import Joseph.Tasks.Deadline;
import Joseph.Tasks.JEvent;
import Joseph.Tasks.Task;
import Joseph.Tasks.ToDo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    private static String taskToString(Task task) {
        String taskType = "";
        String status = task.getDone().equals("X") ? "1" : "0";
        String desc = task.getDesc();

        if (task instanceof ToDo) {
            taskType = "T";
        } else if (task instanceof Deadline) {
            taskType = "D";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            desc += " /" + ((Deadline) task).getDue().format(formatter);
        } else if (task instanceof JEvent) {
            taskType = "E";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            desc += " /" + ((JEvent) task).getStart().format(formatter) +
                    " /" + ((JEvent) task).getEnd().format(formatter);
        }

        return taskType + " | " + status + " | " + desc;
    }


    // used ChatGPT for saveTasks and loadTasks with following questions
    // how to write/read from a file
    // differences between FileWriter/Reader and their Buffered versions
    public void saveTasks(ArrayList<Task> list) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : list) {
                writer.write(taskToString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Uh-oh, I couldn't save the tasks!");
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing task file found. We'll create one from scratch!");
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(desc);
                        if (isDone) {
                            todo.setDone();
                        }
                        list.add(todo);
                        break;
                    case "D":
                        String[] deadlineDesc = desc.split(" /");
                        Deadline deadline = new Deadline(deadlineDesc[0], deadlineDesc[1]);
                        if (isDone) {
                            deadline.setDone();
                        }
                        list.add(deadline);
                        break;
                    case "E":
                        String[] eventDesc = desc.split(" /");
                        JEvent event = new JEvent(eventDesc[0], eventDesc[1], eventDesc[2]);
                        if (isDone) {
                            event.setDone();
                        }
                        list.add(event);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Uh-oh, I couldn't load the tasks!");
        }

        return list;
    }
}
