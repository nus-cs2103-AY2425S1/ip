package snah;

import java.util.ArrayList;
import java.util.Scanner;

import snah.task.Deadline;
import snah.task.Event;
import snah.task.Task;
import snah.task.ToDo;
import snah.util.Parser;
import snah.util.Storage;
import snah.util.Ui;

/**
 * Main class for Snah chatbot
 */
public class Snah {

    private Storage storage;
    private TaskList tasksList;
    private Ui ui;

    /**
     * Constructor for Snah chatbot
     */
    public Snah() {
        storage = new Storage();
        tasksList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Main method for Snah chatbot
     * @param args
     */
    public static void main(String[] args) {
    }

    public String getResponse(String input) {
        Parser.Command currentCommand = Parser.getCommand(input);
        switch (currentCommand) {
        case BYE: {
            return "Goodbye! See you sooooonnn!";
        }
        case LIST: {
            String response = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasksList.size(); i++) {
                response += String.format("%d. %s\n", i + 1, tasksList.get(i));
            }
            return response;
        }
        case MARK: {
            int taskIndex = Parser.getTaskIndex(input);

            if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                return "Oi, you're trying to mark a task that doesn't exist";
            }

            tasksList.get(taskIndex).markAsDone();
            tasksList.save(storage);

            return String.format("Alright, I will mark the task as done\n  %s", tasksList.get(taskIndex));
        }
        case UNMARK: {
            int taskIndex = Parser.getTaskIndex(input);

            if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                return "Oi, you're trying to unmark a task that doesn't exist";
            }

            tasksList.get(taskIndex).unmarkAsDone();
            tasksList.save(storage);

            return String.format("Walao, why you press wrong; Will mark the task as NOT done\n  %s",
                    tasksList.get(taskIndex));
        }
        case DEADLINE: {
            String[] deadlinePayload = Parser.getDeadlinePayload(input);

            if (deadlinePayload == null) {

                return String.format("Oi, you need to provide a description and a deadline for the deadline \n"
                        + "Format as such: \n deadline <description> /by <deadline>");
            }

            tasksList.add(new Deadline(deadlinePayload[0], deadlinePayload[1]));
            tasksList.save(storage);
            return String.format("Added deadline to list\n  %s", tasksList.get(tasksList.size() - 1));
        }
        case EVENT: {
            String[] eventPayload = Parser.getEventPayload(input);

            if (eventPayload == null) {
                return "Oi, you need to provide a description, a start time and an end time for the event\n"
                        + "Format as such:\n event <description> /from <start time> /to <end time>";
            }

            tasksList.add(new Event(eventPayload[0], eventPayload[1], eventPayload[2]));
            tasksList.save(storage);
            return String.format("Added event to list\n  %s", tasksList.get(tasksList.size() - 1));
        }
        case TODO: {
            String[] todoPayload = Parser.getTodoPayload(input);

            if (todoPayload == null) {
                return "Oi, you need to provide a description for the todo\n" + "Format as such:\n todo <description>";
            }

            tasksList.add(new ToDo(todoPayload[0]));
            tasksList.save(storage);
            return String.format("Added todo to list\n  %s", tasksList.get(tasksList.size() - 1));
        }
        case FIND: {
            String keyword = Parser.getSearchQuery(input);

            if (keyword == null) {
                return "Oi, you need to provide a keyword to search for\n" + "Format as such:\n search <keyword>";
            }

            ArrayList<Task> searchResults = tasksList.search(keyword);

            if (searchResults.isEmpty()) {
                return "No tasks found with the keyword";
            }

            String response = "Here are the tasks in your list:\n";
            for (int i = 0; i < searchResults.size(); i++) {
                response += String.format("%d. %s\n", i + 1, searchResults.get(i));
            }
            return response;
        }
        case DELETE: {
            int taskIndex = Parser.getTaskIndex(input);

            if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                return "Oi, you're trying to delete a task that doesn't exist";
            }

            Task deletedTask = tasksList.remove(taskIndex);
            tasksList.save(storage);

            return String.format("Alright, task is removed\n  %s", deletedTask);
        }
        case CLEAR: {
            tasksList.clear();
            tasksList.save(storage);
            return "Tasks cleared";
        }
        case INVALID: {
            String invalidCommand = Parser.getRawCommand(input);
            String response = String.format("Oi, no such command \"%s\". Try these instead\n", invalidCommand);
            for (Parser.Command command : Parser.Command.values()) {
                if (command == Parser.Command.INVALID) {
                    continue;
                }
                response += String.format("- %s\n", command.toString());
            }
            return response;
        }
        default: {
            return "Oi, something went wrong";
        }
        }
    }

    /**
     * Main chat loop for the chatbot
     */
    public void chatLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean continueChat = true;

        while (continueChat) {
            String input = scanner.nextLine();
            Parser.Command currentCommand = Parser.getCommand(input);
            ui.start();
            switch (currentCommand) {
            case BYE: {
                ui.print("Goodbye! See you sooooonnn!");
                continueChat = false;
                break;
            }
            case LIST: {
                ui.print("Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    ui.printf("%d. %s", i + 1, tasksList.get(i));
                }
                break;
            }
            case MARK: {
                int taskIndex = Parser.getTaskIndex(input);

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    ui.print("Oi, you're trying to mark a task that doesn't exist");
                    continue;
                }

                tasksList.get(taskIndex).markAsDone();

                ui.printf("Alright, I will mark the task as done");
                ui.printf("  %s", tasksList.get(taskIndex));
                break;
            }
            case UNMARK: {
                int taskIndex = Parser.getTaskIndex(input);

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    ui.print("Oi, you're trying to unmark a task that doesn't exist");
                    continue;
                }

                tasksList.get(taskIndex).unmarkAsDone();

                ui.printf("Walao, why you press wrong; Will mark the task as NOT done");
                ui.printf("  %s", tasksList.get(taskIndex));
                break;
            }
            case DEADLINE: {
                String[] deadlinePayload = Parser.getDeadlinePayload(input);

                if (deadlinePayload == null) {
                    ui.print("Oi, you need to provide a description and a deadline for the deadline");
                    ui.print("Format as such:");
                    ui.print("  deadline <description> /by <deadline>");
                    continue;
                }

                tasksList.add(new Deadline(deadlinePayload[0], deadlinePayload[1]));
                ui.print("Added deadline to list");
                ui.printf("  %s", tasksList.get(tasksList.size() - 1));
                break;
            }
            case EVENT: {
                String[] eventPayload = Parser.getEventPayload(input);

                if (eventPayload == null) {
                    ui.print("Oi, you need to provide a description, a start time and an end time for the event");
                    ui.print("Format as such:");
                    ui.print("  event <description> /from <start time> /to <end time>");
                    continue;
                }

                tasksList.add(new Event(eventPayload[0], eventPayload[1], eventPayload[2]));
                ui.print("Added event to list");
                ui.printf("  %s", tasksList.get(tasksList.size() - 1));
                break;
            }
            case TODO: {
                String[] todoPayload = Parser.getTodoPayload(input);

                if (todoPayload == null) {
                    ui.print("Oi, you need to provide a description for the todo");
                    ui.print("Format as such:");
                    ui.print("  todo <description>");
                    continue;
                }

                tasksList.add(new ToDo(todoPayload[0]));
                ui.print("Added todo to list");
                ui.printf("  %s", tasksList.get(tasksList.size() - 1));
                break;
            }
            case FIND: {
                String keyword = Parser.getSearchQuery(input);

                if (keyword == null) {
                    ui.print("Oi, you need to provide a keyword to search for");
                    ui.print("Format as such:");
                    ui.print("  search <keyword>");
                    continue;
                }

                ArrayList<Task> searchResults = tasksList.search(keyword);

                if (searchResults.isEmpty()) {
                    ui.print("No tasks found with the keyword");
                    continue;
                }

                ui.print("Here are the tasks in your list:");
                for (int i = 0; i < searchResults.size(); i++) {
                    ui.printf("%d. %s", i + 1, searchResults.get(i));
                }
                break;
            }
            case DELETE: {
                int taskIndex = Parser.getTaskIndex(input);

                if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                    ui.print("Oi, you're trying to delete a task that doesn't exist");
                    continue;
                }

                Task deletedTask = tasksList.remove(taskIndex);

                ui.printf("Alright, task is removed");
                ui.printf("  %s", deletedTask);
                break;
            }
            case CLEAR: {
                tasksList.clear();
                ui.print("Tasks cleared");
                break;
            }
            case INVALID: {
                String invalidCommand = Parser.getRawCommand(input);
                ui.printf("Oi, no such command \"%s\". Try these instead", invalidCommand);
                for (Parser.Command command : Parser.Command.values()) {
                    if (command == Parser.Command.INVALID) {
                        continue;
                    }
                    ui.printf("- %s", command.toString());
                }
                break;
            }
            default: {
                ui.print("Oi, something went wrong");
                break;
            }
            }
            ui.end();
            tasksList.save(storage);
        }
        scanner.close();
    }
}
