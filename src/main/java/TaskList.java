import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private final String saveFileDirectory = "data/testSaveFile.txt";
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
        loadTasks(saveFileDirectory);
    }

    public boolean addTask(Task task) {
        if (tasks.add(task)) {
            saveTasks();
            return true;
        }
        return false;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public boolean markTask(int index) {
        return tasks.get(index).completeTask();
    }

    public boolean unmarkTask(int index) {
        return tasks.get(index).uncompleteTask();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int taskCount() {
        return tasks.size();
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFileDirectory))) {
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    writer.write("D" + '/' + task.getTaskStatus() + "/" + task.getTaskName() + "/" + ((Deadline) task).getDate());
                }
                else if (task instanceof Event) {
                    writer.write("E" +"/" + task.getTaskStatus() + "/" +task.getTaskName() + "/" + ((Event) task).getDate());
                }
                else {
                    writer.write("T" + "/" + task.getTaskStatus() + "/" + task.getTaskName());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks(String fileName) {
        ArrayList<String> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String task;
            while ((task = reader.readLine()) != null) {
                String[] splitTask = task.split("/");
                if (splitTask[0].equals("T")) {
                    ToDo newToDo = new ToDo();
                    newToDo.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newToDo.setTaskName(splitTask[2]);
                    tasks.add(newToDo);
                }
                if (splitTask[0].equals("D")) {
                    Deadline newDeadline = new Deadline();
                    newDeadline.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newDeadline.setTaskName(splitTask[2]);
                    newDeadline.setDate(splitTask[3]);
                    tasks.add(newDeadline);
                }
                if (splitTask[0].equals("E")) {
                    Event newEvent = new Event();
                    newEvent.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newEvent.setTaskName(splitTask[2]);
                    newEvent.setDate(splitTask[3]);
                    tasks.add(newEvent);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res = res + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return res;
    }
}
