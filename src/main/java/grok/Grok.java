package grok;

import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Grok {
    private static final String TEXT_FILE_DIRECTORY = "./data/duke.txt";
    private final Storage storage;

    public Grok() {
        storage = new Storage(TEXT_FILE_DIRECTORY);
        ArrayList<Task> tasks = storage.parseTextStorage();

        // If data changes, prompt a save to text file
        boolean hasUnsavedChanges = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println(padMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?"));

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                System.out.println(padMessage("Please enter your command."));
            } else if (userInput.contains("|")){
                // required so that | can be used to delimit different items in storage reliably.
                System.out.println(padMessage("Message cannot contain the restricted character '|'!"));
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(padMessage("There are no tasks in the list."));
                    continue;
                }

                StringBuilder listOfCommands = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    listOfCommands
                            .append(i + 1)
                            .append(". ")
                            .append(tasks.get(i))
                            .append("\n");
                }

                System.out.println(padMessage(
                        listOfCommands.substring(0, Math.max(0, listOfCommands.length() - 1))
                ));
            } else if (userInput.contains("unmark")) {
                if (userInput.length() < 8) {
                    System.out.println(padMessage("Please indicate the index to unmark: e.g. unmark 1"));
                    continue;
                }

                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(userInput.substring(7));
                } catch (NumberFormatException e) {
                    System.out.println(padMessage("Please enter the task index number to unmark."));
                    continue;
                }

                if (tasks.isEmpty() || taskIndex <= 0 || taskIndex > tasks.size()) {
                    System.out.println(padMessage("Please enter a valid task index to unmark."));
                    continue;
                }


                Task task = tasks.get(taskIndex - 1);
                task.markUndone();
                hasUnsavedChanges = true;
                System.out.println(padMessage("Ok, I've marked this task as not done yet:\n  " + task));
            } else if (userInput.contains("mark")) {
                if (userInput.length() < 6) {
                    System.out.println(padMessage("Please indicate the index to mark: e.g. mark 1"));
                    continue;
                }

                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(userInput.substring(5));
                } catch (NumberFormatException e) {
                    System.out.println(padMessage("Please enter the task index number to mark."));
                    continue;
                }

                if (tasks.isEmpty() || taskIndex <= 0 || taskIndex > tasks.size()) {
                    System.out.println(padMessage("Please enter a valid task index to mark."));
                    continue;
                }

                Task task = tasks.get(taskIndex - 1);
                task.markDone();
                hasUnsavedChanges = true;
                System.out.println(padMessage("Nice! I've marked this task as done:\n  " + task));
            } else if (userInput.contains("todo")) {
                if (userInput.length() < 6) {
                    System.out.println(padMessage("Todo command usage: todo (task description here)"));
                    continue;
                }
                Task newTask;
                try {
                    newTask = new Todo(userInput.substring(5));
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                hasUnsavedChanges = true;
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else if (userInput.contains("deadline")) {
                String deadlineUsage = padMessage("Deadline command usage: deadline (task description here) " +
                        "/by (due date and time)");
                if (userInput.length() < 10 || !userInput.contains("/by")) {
                    System.out.println(padMessage(deadlineUsage));
                    continue;
                }

                String[] components = userInput.split("/by");

                if (components.length != 2) {
                    System.out.println(padMessage(deadlineUsage));
                    continue;
                }
                String description = components[0].substring(9);
                String due = components[1].substring(1);

                Task newTask;
                try {
                    newTask = new Deadline(description, due);
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                hasUnsavedChanges = true;
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else if (userInput.contains("event")) {
                String eventUsage = padMessage("Event command usage: event (task description here) " +
                        "/from (start date and time) /to (end date and time)");
                if (userInput.length() < 7 || !userInput.contains("/from") || !userInput.contains("/to")) {
                    System.out.println(padMessage(eventUsage));
                    continue;
                }

                String[] components = userInput.split("/from");
                if (components.length != 2) {
                    System.out.println(padMessage(eventUsage));
                    continue;
                }

                String[] subcomponents = components[1].split("/to");
                if (subcomponents.length != 2) {
                    System.out.println(padMessage(eventUsage));
                    continue;
                }

                String description = components[0];
                String from = subcomponents[0].substring(1);
                String to = subcomponents[1].substring(1);

                Task newTask;
                try {
                    newTask = new Event(description, from, to);
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                hasUnsavedChanges = true;
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else if (userInput.contains("delete")) {
                if (userInput.length() < 8) {
                    System.out.println(padMessage(
                            "Delete command usage: delete (task index to delete)"
                    ));
                    continue;
                }

                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(userInput.substring(7));
                } catch (NumberFormatException e) {
                    System.out.println(padMessage("Please enter the task index number to delete."));
                    continue;
                }

                if (tasks.isEmpty() || taskIndex <= 0 || taskIndex > tasks.size()) {
                    System.out.println(padMessage("Please enter a valid task index to delete."));
                    continue;
                }

                Task task = tasks.remove(taskIndex - 1);
                hasUnsavedChanges = true;
                System.out.println(padMessage(
                        "Noted. I've removed this task:\n  "
                                + task
                                + "\nNow you have " + tasks.size() + " tasks in the list."
                ));
            } else {
                System.out.println(padMessage(
                        "OOPS! Sorry, I don't recognize your input :(\n" +
                                "Available commands are: " +
                                "bye, list, mark, unmark, todo, deadline, event, delete"
                ));
            }

            if (hasUnsavedChanges) {
                TextFileReader.writeToFile(TEXT_FILE_DIRECTORY, tasks);
            }
        }


        System.out.println(padMessage("Bye. Hope to see you again soon!"));
    }

    public static void main(String[] args) {
        new Grok();
    }
}