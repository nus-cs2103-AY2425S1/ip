import exception.EchoBotException;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;

public class EchoBot {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        UI ui = new UI();
        ui.greeting();
        start(taskList, ui);
    }

    private static void start(TaskList taskList, UI ui) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String command = scan.nextLine();
            String[] commandSplit = command.split(" ");

            if ("bye".equals(command)) {
                ui.exit();
                break;
            }

            try {
                if ("list".equals(command)) {
                    ui.printTaskList(taskList.getTaskList());
                } else if (commandSplit.length == 2 && "mark".equals(commandSplit[0])) {
                    int taskIndex = Integer.parseInt(commandSplit[1]);
                    Task task = taskList.getTaskByIndex(taskIndex);
                    task.markDone();
                    ui.printTaskMarkedDone(task);
                } else if (commandSplit.length == 2 && "unmark".equals(commandSplit[0])) {
                    int taskIndex = Integer.parseInt(commandSplit[1]);
                    Task task = taskList.getTaskByIndex(taskIndex);
                    task.markUndone();
                    ui.printTaskMarkedUndone(task);
                } else if ("deadline".equals(commandSplit[0])) {
                    String[] commands = splitCommand(commandSplit, new String[]{"/by"});
                    Task task = new Deadline(commands[0], commands[1]);
                    taskList.addTask(task);
                    ui.printAddTaskFeedback(task, taskList.getTaskList().size());
                } else if ("todo".equals(commandSplit[0])) {
                    String[] commands = splitCommand(commandSplit, new String[]{});
                    Task task = new ToDo(commands[0]);
                    taskList.addTask(task);
                    ui.printAddTaskFeedback(task, taskList.getTaskList().size());
                } else if ("event".equals(commandSplit[0])) {
                    String[] commands = splitCommand(commandSplit, new String[]{"/from", "/to"});
                    Task task = new Event(commands[0], commands[1], commands[2]);
                    taskList.addTask(task);
                    ui.printAddTaskFeedback(task, taskList.getTaskList().size());
                } else {
                    throw new UnknownCommandException();
                }
            } catch (EchoBotException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    /**
     * This method splits strings in commandSplit starting from index 1 by keywords in splitKeywords.
     * Example: commandSplit = {"event", "project", "meeting", "/from", "Mon", "2pm", "/to", "4pm"}, splitKeywords = {"/from", "/to"}
     * will return {"project meeting", "Mon 2pm", "4pm"}
     *
     * @param commandSplit  commands to split
     * @param splitKeywords target keywords
     * @return an array of strings after being split by keywords.
     */
    private static String[] splitCommand(String[] commandSplit, String[] splitKeywords) {
        String[] ans = new String[splitKeywords.length + 1];
        int i = 1, j = 0;

        while (j < splitKeywords.length) {
            StringBuilder word = new StringBuilder();
            for (; i < commandSplit.length; i++) {
                if (splitKeywords[j].equals(commandSplit[i])) {
                    i++;
                    break;
                }
                word.append(commandSplit[i]);
                word.append(" ");
            }
            if (!word.isEmpty()) {
                word.deleteCharAt(word.length() - 1);
            }
            ans[j] = word.toString();
            j++;
        }

        StringBuilder word = new StringBuilder();
        for (; i < commandSplit.length; i++) {
            word.append(commandSplit[i]);
            word.append(" ");
        }
        if (!word.isEmpty()) {
            word.deleteCharAt(word.length() - 1);
        }
        ans[j] = word.toString();

        while (j++ < splitKeywords.length) {
            ans[j] = "";
        }
        return ans;
    }
}
