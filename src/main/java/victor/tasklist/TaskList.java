package victor.tasklist;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

import victor.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private Path filePath;

    public TaskList(Path filePath) {
        this.tasks = new ArrayList<Task>();
        this.filePath = filePath;
    }

    public String[] addTask(Task task) {
        this.tasks.add(task);
        return new String[] {"  ~  Cool! I added this task:",
            "  ~  " + task,
            "  ~  You now have " + this.size() + ((this.size() == 1) ? " task" : " tasks") + " in your list."};
    }

    public String[] deleteTask(int taskNumber) {
        try {
            Task removed = tasks.remove(taskNumber - 1);
            return new String[] {"  ~  Deleting the task below now!",
                "  ~  " + removed, "  ~  You now have " + this.size() + ((this.size() == 1) ? " task" : " tasks")
                    + " in your list."};
        } catch (NumberFormatException e) {
            return new String[] {"  ~  Sorry, I don't think you entered a number for which task to delete!"};
        } catch (IndexOutOfBoundsException e) {
            return new String[] {"  ~  I don't think there's a task with that number!"};
        }
    }

    public String[] markDone(int taskNumber) {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markDone();
            return new String[] {"  ~  You finished a task! Well done! I marked this task as done:",
                "  ~  " + task};
        } catch (IndexOutOfBoundsException e) {
            return new String[] {"  ~  I don't think there's a task with that number!"};
        }
    }

    public String[] unmarkDone(int taskNumber) {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markUndone();
            return new String[] {"  ~  Oops, I guess you didn't finish the task! I marked this task as undone:",
                "  ~  " + task};
        } catch (IndexOutOfBoundsException e) {
            return new String[] {"  ~  I don't think there's a task with that number!"};
        }
    }

    public void writeToFile(Path filePath, boolean shouldOverwrite) {
        try {
            // Clear output file if overwriting
            if (shouldOverwrite) {
                PrintWriter writer = new PrintWriter(String.valueOf(filePath));
                writer.print("");
                writer.close();
            }
            FileWriter fw = new FileWriter(String.valueOf(filePath), shouldOverwrite);
            for (Task task : tasks) {
                task.writeToFile(fw);
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] enumerateTasks() {
        String[] outputList = new String[tasks.size() + 1];
        outputList[0] = "  ~  Sure! Here are all of your tasks:";
        for (int i = 0; i < tasks.size(); i++) {
            outputList[i + 1] = "     " + (i + 1) + ". " + tasks.get(i);
        }
        return outputList;
    }

    public String[] findMatches(String toMatch) {
        ArrayList<String> matches = new ArrayList<String>();
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().contains(toMatch)) {
                matches.add("     " + count + ". " + task);
                count += 1;
            }
        }
        if (count > 1) {
            String[] matchesArray = new String[matches.size()];
            for (int i = 0; i < matches.size(); i++) {
                matchesArray[i] = matches.get(i);
            }
            return matchesArray;
        } else {
            return new String[] {};
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
