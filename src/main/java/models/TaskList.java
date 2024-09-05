package models;
import java.util.ArrayList;
import java.util.Objects;

import lib.ActiveRecord;
import lib.DbDriverInterface;
import lib.FileDbDriver;

public class TaskList extends ActiveRecord {

    private ArrayList<Task> tasks;

    public TaskList(DbDriverInterface dbDriver) {
        super(dbDriver);
        init();
    }

    private void init() {
        // deserialise text and save into tasks;
        this.tasks = new ArrayList<>();
        try {
            deserialiseRawData();
        } catch (Exception e) {
            // reset DB
            resetDB();
        }
    }

    public void resetDB() {
        this.dbDriver.save("");
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveToDb();
    }

    public int getSize() {return tasks.size();}

    public void markTask(int index) {
        tasks.get(index).mark();
        saveToDb();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
        saveToDb();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        Task returnTask = this.tasks.remove(index-1);
        saveToDb();
        return returnTask;
    }

    public void saveToDb() {
        String serializedString = this.serialiseTaskList();
        this.dbDriver.save(serializedString);
    }

    public String serialiseTaskList() {
        if (tasks.isEmpty()) {
            return "";
        }
        StringBuilder list = new StringBuilder();
        for (Task task : tasks) {
            list.append(task.serialize()).append("\n");
        }
//        System.out.println("This is serialised raw string");
//        System.out.println(list);
        return list.toString();
    }

    public void deserialiseRawData() {
        String rawData = this.dbDriver.read();
        String[] tasks = rawData.split("\n");

        for (String taskLine : tasks) {
            String[] sections = taskLine.split("\\|");

            String taskType = sections[0];

//            System.out.println(sections[2]);

            if (taskType.equals("T")) {
                Todo task = new Todo(sections[2], sections[1].equals("1") ? true : false);
                this.tasks.add(task);
            } else if (taskType.equals("E")) {
                Event event = new Event(sections[2],sections[1].equals("1") ? true : false,
                        sections[3], sections[4]);
                this.tasks.add(event);
            } else if (taskType.equals("D")) {
                Deadline deadline = new Deadline(sections[2],sections[1].equals("1") ? true : false, sections[3]);
                this.tasks.add(deadline);
            }
        }

    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "List is empty!";
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString();
    }
}
