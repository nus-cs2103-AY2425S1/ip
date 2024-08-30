package makima.command;

import makima.io.*;
import makima.task.Deadline;
import makima.task.Event;
import makima.task.Task;
import makima.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Makima {

    public static final String LINE_SEPERATOR = "__________________";
    public static final Scanner SC = new Scanner(System.in);
    private boolean isRunning = true;
    private boolean editedTasks;
    private ArrayList<Task> tasks = new ArrayList<>();
    
    private void greeting() {
        System.out.println("Yahallo! I'm your friendly chatbot, makima.Makima!");
        System.out.println("What can I do for you? *_*");
        System.out.println(LINE_SEPERATOR);
    }

    private void farewell() {
        System.out.println("Baibai. Hope to see you soon! ^_^");
        System.out.println(LINE_SEPERATOR);
    }

    private void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ":" + tasks.get(i));
        }
    }

    /**
     * Prints a prompt and returns a valid list index.
     *
     * @param prompt
     * @return list index
     */
    private int getListIndex(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getListIndex();
    }

    /**
     * Returns a 1-indexed list index.
     * Repeatedly scans user input until a valid index is written.
     *
     * @return list index
     */
    private int getListIndex() {
        while (true) {
            String userInput = Parser.getInput();
            try {
                int index = Integer.parseInt(userInput);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("Invalid index");
                } else {
                    return index-1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    /**
     * Returns a non-empty user input.
     *
     * @param prompt
     * @return user input
     */
    private String getInput(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return Parser.getInput();
    }

    /**
     * Returns a local date time.
     * Repeatedly scans user input until a valid date time is written.
     * Date time is in the format YYYY-MM-DDTHH:MM
     *
     * @param prompt
     * @return date time
     */
    private LocalDateTime getDate(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return Parser.getDate();
    }

    /**
     * Returns a local date time that is AFTER the specified date.
     *
     * @param date
     * @param prompt
     * @return date time
     */
    private LocalDateTime getDateAfter(LocalDateTime date, String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return Parser.getDateAfter(date);
    }

    /**
     * Prints a confirmation for completion of commands which
     * edit the tasks array.
     */
    private void done() {
        editedTasks = true;
        System.out.println("Done!");
        System.out.println(LINE_SEPERATOR);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a string representing the lists of current tasks
     * for writing to save file
     *
     * @return current tasks
     */
    public String convertTaskstoFileString() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toFileString());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        greeting();

        isRunning = FileManager.loadFile(this);

        while (isRunning) {

            editedTasks = false;

            switch (Parser.getInput()) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    displayList();
                    System.out.println(LINE_SEPERATOR);
                    break;

                case "mark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).mark();
                    done();
                    break;

                case "unmark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).unmark();
                    done();
                    break;

                case "todo":
                    tasks.add(new ToDo(getInput("What is the task name?")));
                    done();
                    break;

                case "deadline":
                    tasks.add(new Deadline(
                            getInput("What is the task name?"),
                            getDate("When is it due?")
                    ));
                    done();
                    break;

                case "event":
                    String name = getInput("What is the task name?");
                    LocalDateTime startTime = getDate("When does it start?");
                    LocalDateTime endTime = getDateAfter(startTime, "When does it end?");
                    tasks.add(new Event(
                            name, startTime, endTime
                    ));
                    done();
                    break;

                case "delete":
                    tasks.remove(getListIndex("Which item would you like to delete?"));
                    done();
                    break;

                default:
                    System.out.println("Unknown command!");
                    System.out.println(LINE_SEPERATOR);
            }

            if (editedTasks) {
                FileManager.saveFile(this);
            }
        }

        farewell();
    }

}
