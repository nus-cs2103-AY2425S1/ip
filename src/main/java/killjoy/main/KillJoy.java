package killjoy.main;

import killjoy.task.Deadline;
import killjoy.task.Event;
import killjoy.task.Task;
import killjoy.task.Todo;
import killjoy.processing.ProcessTasks;
import killjoy.processing.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main class of the KillJoy application.
 * Contains the main method to run the application.
 */
public class KillJoy {
    private ProcessTasks processTasks;
    private Storage saveAndLoad;
    private UserInterface ui;
    private ArrayList<Task> taskList;
    private int taskCount = 0;

    public KillJoy() {
        this.ui = new UserInterface(this);
        this.processTasks = new ProcessTasks(this, ui);
        this.saveAndLoad = new Storage(this, processTasks);
        this.taskList = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Increases the task count by 1.
     */
    public void increaseTaskCount() {
        this.taskCount++;
    }

    /**
     * Decreases the task count by 1.
     */
    public void decreaseTaskCount() {
        this.taskCount--;
    }

    /**
     * Adds a task to the task list.
     * @param description
     */
    public void addTask(String description) {
        taskList.add(new Todo(description));
        this.increaseTaskCount();
    }

    /**
     * Adds a task to the task list.
     * @param description
     * @param by
     */
    public void addTask(String description, LocalDateTime by) {
        taskList.add(new Deadline(description, by));
        this.increaseTaskCount();
    }

    /**
     * Adds a task to the task list.
     * @param description
     * @param from
     * @param to
     */
    public void addTask(String description, LocalDateTime from, LocalDateTime to) {
        taskList.add(new Event(description, from, to));
        this.increaseTaskCount();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Removes a task from the task list.
     * @param taskIndex
     */
    public void removeTask(int taskIndex) {
        this.taskList.remove(taskIndex);
        this.decreaseTaskCount();
    }

    /**
     * Starts the KillJoy application.
     */
    public void start() {
        System.out.println(ui.getLogoString());
        System.out.println(ui.getWelcomeString());
        Scanner user = new Scanner(System.in);

        saveAndLoad.loadTasks();

        while(true) {
            String input = user.nextLine();
            if (input.equals("")) {
                ui.displayNoStringMessage();
                continue;
            }

            String[] inputAsList = input.split(" ");

            if (inputAsList[0].equals("bye")) {
                saveAndLoad.saveTasks(this.taskList);
                System.out.println(ui.getExitString());
                break;
            } else if (inputAsList[0].equals("list")){
                ui.printTaskList();
            } else if (inputAsList[0].equals("mark") || inputAsList[0].equals("unmark")
                    || inputAsList[0].equals("delete")) {
                processTasks.markOrDelete(input);
            } else {
                processTasks.processUserInput(input);
            }
        }
        user.close();
    }

    public static void main(String[] args) {
        KillJoy kj = new KillJoy();
        kj.start();
    }
}
