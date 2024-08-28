package yapper.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private String filePath;

    public TaskList(ArrayList<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        String[] texts = {
                "Task has been added:",
                "  " + task,
                "A total of " + getSize() + " " + taskPlural() + " are on the list."
        };
        writeToFile();
        Ui.wrapText(texts);
    }

    public String taskPlural() {
        String taskMessage = "task";
        if (getSize() != 1) {
            taskMessage += "s";
        }
        return taskMessage;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void listTasks() {
        Ui.showLine();
        System.out.println("Your task list currently has " + getSize() + " tasks");
        for (int i = 1; i <= getSize(); i++) {
            System.out.println(i + "." + getTask(i - 1));
        }
        Ui.showLine();
    }

    public void deleteTask(String taskNumber) {
        Task task = null;
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            task = this.taskList.get(taskIndex - 1);
            this.taskList.remove(taskIndex - 1);
            writeToFile();
        } catch (NumberFormatException e) {
            Ui.wrapText("That was NOT a valid number.");
            return;
        } catch (IndexOutOfBoundsException e) {
            Ui.wrapText("That task is not here, sorry! :(");
            return;
        }
        String[] texts = {
                "The following task has been removed form the list:",
                "  " + task,
                "A total of " + getSize() + " " + taskPlural() + " are still left."
        };
        Ui.wrapText(texts);
    }

    public void markTask(String command, String taskNumber) {
        Task task = null;
        try {
            int taskIndex = Integer.parseInt(taskNumber);
            task = getTask(taskIndex - 1);
        } catch (NumberFormatException e) {
            Ui.wrapText("That was NOT a valid number.");
            return;
        } catch (IndexOutOfBoundsException e) {
            Ui.wrapText("Oopsie! Couldn't find that one! :)");
            return;
        }
        String message = "";
        if (command.equals("mark")) {
            message = "This task has been marked as completed:";
            task.mark();
        } else {
            message = "This task has been reopened:";
            task.unmark();
        }
        writeToFile();
        String[] texts = {
                message,
                " " + task,
        };
        Ui.wrapText(texts);
    }

    public void writeToFile() {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task t : this.taskList) {
                fileWriter.write(t.getDesc() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
