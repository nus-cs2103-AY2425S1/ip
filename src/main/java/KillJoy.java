package src.main.java;

import src.main.java.ProcessTasks;
import src.main.java.Task;
import src.main.java.Todo;
import src.main.java.Event;
import src.main.java.Deadline;
import src.main.java.SaveAndLoad;

import java.util.ArrayList;
import java.util.Scanner;


public class KillJoy {
    private ProcessTasks processTasks;
    private SaveAndLoad saveAndLoad;

    private String welcomeString = "    Hello! I'm KillJoy\n    I'm GENIUS!!!\n    What can I do for you?\n" +
            "    ------------------------------------";
    private String exitString = "    ------------------------------------\n" +
            "    Bubyyeee & Don't Stwesszz. Time to hide now!!\n" +
            "    ------------------------------------";
    private String logoString = "    ------------------------------------\n" +
            "     _  ___ _ _      _\n" +
            "    | |/ (_) | |    | |\n" +
            "    | ' / _| | |    | | ___  _   _\n" +
            "    |  < | | | |_   | |/ _ \\| | | |\n" +
            "    | . \\| | | | |__| | (_) | |_| |\n" +
            "    |_|\\_\\_|_|_|\\____/ \\___/ \\__, |\n" +
            "                              __/ |\n" +
            "                             |___/\n" +
            "    ------------------------------------";
    private String markString = "Ayee Yooo! I've marked this task okaayyyyy:";
    private String unmarkString = "Yo big guy! I've unmarked this for you:";
    private String deleteString = "Okay! I'll remove this task:";
    private ArrayList<Task> taskList = new ArrayList<>();
    private int taskCount = 0;

    public KillJoy() {
        this.processTasks = new ProcessTasks(this);
        this.saveAndLoad = new SaveAndLoad(this, processTasks);
    }

    public String getMarkString() {
        return markString;
    }

    public String getUnmarkString() {
        return unmarkString;
    }

    public String getDeleteString() {
        return deleteString;
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
        System.out.println(this.logoString);
        System.out.println(this.welcomeString);
        Scanner user = new Scanner(System.in);

        saveAndLoad.loadTasks();

        while(true) {
            String input = user.nextLine();
            if (input.equals("")) {
                processTasks.printLine();
                System.out.println("    WHHHAHAHAHHA!! You didn't enter anything siaaa!!!");
                processTasks.printLine();
                continue;
            }

            String[] inputAsList = input.split(" ");

            if (inputAsList[0].equals("bye")) {
                saveAndLoad.saveTasks(this.taskList);
                System.out.println(this.exitString);
                break;
            } else if (inputAsList[0].equals("list")){
                processTasks.printTaskList();
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
