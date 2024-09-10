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
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasksList = new TaskList(storage);

        ui.greet();
        this.chatLoop();
    }

    /**
     * Main method for Snah chatbot
     * @param args
     */
    public static void main(String[] args) {
        new Snah();
    }

    /**
     * Main chat loop for the chatbot
     */
    public void chatLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean continueChat = true;

        while (continueChat) {
            String userInput = scanner.nextLine();
            Parser.Command currentCommand = Parser.getCommand(userInput);
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
                int taskIndex = Parser.getTaskIndex(userInput);

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
                int taskIndex = Parser.getTaskIndex(userInput);

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
                String[] deadlinePayload = Parser.getDeadlinePayload(userInput);

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
                String[] eventPayload = Parser.getEventPayload(userInput);

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
                String[] todoPayload = Parser.getTodoPayload(userInput);

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
                String keyword = Parser.getSearchQuery(userInput);

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
                int taskIndex = Parser.getTaskIndex(userInput);

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
                String invalidCommand = Parser.getRawCommand(userInput);
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
