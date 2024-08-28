package bro;

import bro.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bro.ui.UI.reply;

enum ChatCommand {
    bye,
    list,
    mark,
    unmark,
    todo,
    deadline,
    event,
    delete
}

public class bro.Bro {
    final static String GREETING_MESSAGE = """
                 Hello! I'm bro.Bro
                 What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";

    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm")
    );

    // Desired output format
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");


    public static void main(String[] args) {
        reply(GREETING_MESSAGE);
        TaskList taskList = new TaskList();
        // Start conversation
        Scanner sc = new Scanner(System.in);
        boolean isConversing = true;
        while (isConversing) {
            // Read from standard input
            String input = sc.nextLine();
            String[] inputArgs = input.split(" ", 2);
            ChatCommand cmd = null;
            try {
                cmd = ChatCommand.valueOf(inputArgs[0]);
            } catch (IllegalArgumentException e) {
                reply("bro.Bro. I don't understand that");
                continue;
            }
            String secondArg = "";
            if (inputArgs.length > 1) {
                secondArg = inputArgs[1];
            }

            switch (cmd) {
                case bye:
                    isConversing = false;
                    break;
                case list:
                    taskList.printAllTasks();
                    break;
                case mark:
                    try {
                        int taskId = Integer.parseInt(secondArg);
                        Task task = taskList.markTask(taskId);
                        reply("Nice bro! I've marked this task as done:\n" + task);
                        onListChange(taskList);
                        break;
                    } catch (Exception e) {
                        reply("Error: " + e.getMessage());
                        break;
                    }
                case unmark:
                    try {
                        int taskId = Integer.parseInt(secondArg);
                        Task task = taskList.unmarkTask(taskId);
                        reply("Ok bro! I've marked this task as undone:\n" + task);
                        onListChange(taskList);
                        break;
                    } catch (Exception e) {
                        reply("Error: " + e.getMessage());
                        break;
                    }
                case todo:
                    if (secondArg.isEmpty()) {
                        reply("bro.Bro I can't add a empty task");
                        break;
                    }
                    Task todoTask = taskList.addTask(new TodoTask(secondArg));
                    onListChange(taskList);
                    addTaskReply(todoTask, taskList.getNumberOfTask());
                    break;
                case deadline:
                    // Input validation
                    if (secondArg.isEmpty()) {
                        reply("bro.Bro I Can't add a empty task");
                        break;
                    }
                    if (!secondArg.contains("/by")) {
                        reply("Wrong usage of deadline command");
                        reply("Usage: deadline <task> /by <deadline>");
                        break;
                    }
                    String[] deadlineInputs = secondArg.split("/by");
                    String taskContent = deadlineInputs[0].trim();
                    String deadline = deadlineInputs[1].trim();

                    // Parse deadline
                    String parsedDeadline = deadline;
                    for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
                        try {
                            LocalDateTime parsedDate = LocalDateTime.parse(deadline, formatter);
                            parsedDeadline = parsedDate.format(OUTPUT_FORMATTER);
                            break;
                        } catch (Exception ignored) {
                        }
                    }
                    Task deadlineTask = taskList.addTask(new DeadlineTask(taskContent, parsedDeadline));
                    onListChange(taskList);
                    addTaskReply(deadlineTask, taskList.getNumberOfTask());
                    break;
                case event:
                    // Input validation
                    if (secondArg.isEmpty()) {
                        reply("bro.Bro I Can't add a empty task");
                        break;
                    }
                    if (!secondArg.contains("/from") || !secondArg.contains("/to")) {
                        reply("Wrong usage of event command");
                        reply("Usage: deadline <task> /from <startTime> /to <endTime>");
                        break;
                    }

                    try {
                        String[] eventInputs = secondArg.split("/from");
                        String eventName = eventInputs[0].trim();

                        String[] durationInputs = eventInputs[1].split("/to");
                        String startTime = durationInputs[0].trim();
                        String endTime = durationInputs[1].trim();
                        Task eventTask = taskList.addTask(new EventTask(eventName, startTime, endTime));
                        onListChange(taskList);
                        addTaskReply(eventTask, taskList.getNumberOfTask());
                        break;
                    } catch (Exception e) {
                        reply("Wrong usage of event command");
                        reply("Usage: deadline <task> /from <startTime> /to <endTime>");
                        break;
                    }
                case delete:
                    try {
                        int taskId = Integer.parseInt(secondArg);
                        Task task = taskList.deleteTask(taskId);
                        reply("Noted. Removed this task:\n" + task);
                        onListChange(taskList);
                        break;
                    } catch (Exception e) {
                        reply("There was an error bro.");
                        break;
                    }
            }
        }
        reply(GOODBYE_MESSAGE);
    }

    // Prints an adds to list on standard output
    public static void reply(String content) {
        String replyStr = String.format("""
                ____________________________________________________________
                %s
                bro.Bro
                ____________________________________________________________
                """, content);
        System.out.print(replyStr);
    }

    // Prints an add task reply
    public static void addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                bro.Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
    }

    // Handles change to list
    public static void onListChange(TaskList taskList) {
        taskList.writeAllTasks(); // Write to file system
    }
}
