package chatbot.util;

import chatbot.util.ProcessTasks;
import chatbot.util.Task;
import chatbot.util.Todo;
import chatbot.util.Event;
import chatbot.util.Deadline;
import chatbot.util.Storage;
import chatbot.util.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;


public class KillJoy {
    private ProcessTasks processTasks;
    private Storage saveAndLoad;
    private Parser parser;
    private UserInterface ui;
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    public KillJoy() {
        this.ui = new UserInterface(this);
        this.processTasks = new ProcessTasks(this, ui);
        this.saveAndLoad = new Storage(this, processTasks);
        this.parser = new Parser(this, ui);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void increaseTaskCount() {
        this.taskCount++;
    }

    public void decreaseTaskCount() {
        this.taskCount--;
    }

    public void addTask(String description) {
        taskList.add(new Todo(description));
        this.increaseTaskCount();
    }

    public void addTask(String description, String by) {
        taskList.add(new Deadline(description, by));
        this.increaseTaskCount();
    }

    public void addTask(String description, String from, String to) {
        taskList.add(new Event(description, from, to));
        this.increaseTaskCount();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public void removeTask(int taskIndex) {
        this.taskList.remove(taskIndex);
    }

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
    }

    public static void main(String[] args) {
        KillJoy kj = new KillJoy();
        kj.start();
    }
}
