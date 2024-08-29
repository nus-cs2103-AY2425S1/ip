package evelyn.command;

import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Task;
import evelyn.task.Todo;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> list;
    public TaskList(Storage storage) {
        this.list = new ArrayList(100);
        try {
            this.storage = storage;
            if (!this.storage.fileExists()) {
                this.storage.createNewDataFile();
            } else {
                BufferedReader br = new BufferedReader(this.storage.getFileReader());
                String line;
                while ((line = br.readLine()) != null) {
                    fileDataToList(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating new file / restoring file data: " + e);
        }
    }

    public void listTask() {
        System.out.println(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.listSize(); i++) {
            System.out.println((i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println(Ui.horizontalLine);
    }

    public void removeTask(int index) {
        Task task = (Task) list.get(index);
        list.remove(index);
        System.out.println(Ui.horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(this.listSize() > 1 ? "Now you have " + this.listSize() + " tasks in this list"
                : "Now you have " + this.listSize() + " task in this list");
        System.out.println(Ui.horizontalLine);
    }

    public void addTask(Task task) {
        try {
            list.add(task);
            storage.writeToFile(task);
            System.out.println(Ui.horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.toString());
            System.out.println(this.listSize() > 1 ? "Now you have " + this.listSize() + " tasks in this list"
                    : "Now you have " + this.listSize() + " task in this list");
            System.out.println(Ui.horizontalLine);
        } catch (IOException e) {
            System.err.println("Error reading/writing to file: " + e);
        }
    }

    public Task getTask(int index) {
        Task task = (Task) list.get(index);
        return task;
    }

    public void markTask(int index) {
        Task task = (Task) list.get(index);
        System.out.println(Ui.horizontalLine);
        task.mark();
        System.out.println(Ui.horizontalLine);
    }

    public void unmarkTask(int index) {
        Task task = (Task) list.get(index);
        System.out.println(Ui.horizontalLine);
        task.unmark();
        System.out.println(Ui.horizontalLine);
    }
    public int listSize() {
        return list.size();
    }

    public void saveTasks() {
        try {
            this.storage.saveData();
            for (int i = 0; i < this.listSize(); i++) {
                Task task = (Task) this.getTask(i);
                storage.writeToFile(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e);
        }
    }

    private  void fileDataToList(String data) throws IOException {
        boolean isMarked;
        if (data.startsWith("[T]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            System.out.println(isMarked ? "True" : "False");
            String description = data.substring(7);
            Todo newTodo = new Todo(description, isMarked);
            this.list.add(newTodo);
        } else if (data.startsWith("[D]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            String descAndDate = data.substring(7);
            String[] parts = descAndDate.split(" \\(by: ");
            String description = parts[0];
            String deadline = parts[1].substring(0, parts[1].length() - 1);
            Deadline newDeadline = new Deadline(description, deadline, isMarked);
            this.list.add(newDeadline);
        } else if (data.startsWith("[E]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            String descAndDate = data.substring(7);
            String[] parts1 = descAndDate.split(" \\(from: ");
            String description = parts1[0];
            String[] parts2 = parts1[1].split(" to: ");
            String start = parts2[0];
            String end = parts2[1].substring(0, parts2[1].length() - 1);

            Event newEvent = new Event(description, start, end, isMarked);
            this.list.add(newEvent);
        } else {
            throw new IOException();
        }
    }

}