import java.util.ArrayList;
import java.util.Scanner;

public class Snah {
    private final static String START_DIVIDER = "___________________________________________";
    private final static String END_DIVIDER = "___________________________________________\n";
    private final static String CHAT_NAME = "Snah";

    public static void main(String[] args) {
        Storage storage = new Storage();
        greet();
        chatLoop(storage);
    }

    public static void chatbotPrint(String message) {
        System.out.printf("\t%s\n", message);
    }

    public static void greet() {
        chatbotPrint(START_DIVIDER);
        chatbotPrint(String.format("Hello! I'm %s", CHAT_NAME));
        chatbotPrint("What can I do for you?");
        chatbotPrint(END_DIVIDER);
    }

    public static void chatLoop(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        ArrayList<Task> tasksList = storage.getTaskLists();
        boolean continueChat = true;

        while (continueChat) {
            userInput = scanner.nextLine();
            Parser.Command currentCommand = Parser.getCommand(userInput);
            chatbotPrint(START_DIVIDER);

            switch (currentCommand) {
                case BYE: {
                    chatbotPrint("Goodbye! See you sooooonnn!");
                    continueChat = false;
                    break;
                }
                case LIST: {
                    chatbotPrint("Here are the tasks in your list:");
                    for (int i = 0; i < tasksList.size(); i++) {
                        chatbotPrint(String.format("%d. %s", i + 1, tasksList.get(i)));
                    }
                    break;
                }
                case MARK: {
                    int taskIndex = Parser.getTaskIndex(userInput);

                    if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                        chatbotPrint("Oi, you're trying to mark a task that doesn't exist");
                        continue;
                    }

                    tasksList.get(taskIndex).markAsDone();

                    chatbotPrint(String.format("Alright, I will mark the task as done"));
                    chatbotPrint(String.format("  %s", tasksList.get(taskIndex)));
                    break;
                }
                case UNMARK: {
                    int taskIndex = Parser.getTaskIndex(userInput);

                    if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                        chatbotPrint("Oi, you're trying to unmark a task that doesn't exist");
                        continue;
                    }

                    tasksList.get(taskIndex).unmarkAsDone();

                    chatbotPrint(String.format("Walao, why you press wrong; Will mark the task as NOT done"));
                    chatbotPrint(String.format("  %s", tasksList.get(taskIndex)));
                    break;
                }
                case DEADLINE: {
                    String[] deadlinePayload = Parser.getDeadlinePayload(userInput);

                    if (deadlinePayload == null) {
                        chatbotPrint("Oi, you need to provide a description and a deadline for the deadline");
                        chatbotPrint("Format as such:");
                        chatbotPrint("  deadline <description> /by <deadline>");
                        continue;
                    }

                    tasksList.add(new Deadline(deadlinePayload[0], deadlinePayload[1]));
                    chatbotPrint("Added deadline to list");
                    chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
                    break;
                }
                case EVENT: {
                    String[] eventPayload = Parser.getEventPayload(userInput);

                    if (eventPayload == null) {
                        chatbotPrint(
                                "Oi, you need to provide a description, a start time and an end time for the event");
                        chatbotPrint("Format as such:");
                        chatbotPrint("  event <description> /from <start time> /to <end time>");
                        continue;
                    }

                    tasksList.add(new Event(eventPayload[0], eventPayload[1], eventPayload[2]));
                    chatbotPrint("Added event to list");
                    chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
                    break;
                }
                case TODO: {
                    String[] todoPayload = Parser.getTodoPayload(userInput);

                    if (todoPayload == null) {
                        chatbotPrint("Oi, you need to provide a description for the todo");
                        chatbotPrint("Format as such:");
                        chatbotPrint("  todo <description>");
                        continue;
                    }

                    tasksList.add(new ToDo(todoPayload[0]));
                    chatbotPrint("Added todo to list");
                    chatbotPrint(String.format("  %s", tasksList.get(tasksList.size() - 1)));
                    break;
                }
                case DELETE: {
                    int taskIndex = Parser.getTaskIndex(userInput);

                    if (taskIndex < 0 || taskIndex >= tasksList.size()) {
                        chatbotPrint("Oi, you're trying to delete a task that doesn't exist");
                        continue;
                    }

                    Task deletedTask = tasksList.remove(taskIndex);

                    chatbotPrint(String.format("Alright, task is removed"));
                    chatbotPrint(String.format("  %s", deletedTask));
                    break;
                }
                case CLEAR: {
                    tasksList.clear();
                    chatbotPrint("Tasks cleared");
                    break;
                }
                case INVALID: {
                    String invalidCommand = Parser.getRawCommand(userInput);
                    chatbotPrint(String.format("Oi, no such command \"%s\". Try these instead", invalidCommand));
                    for (Parser.Command command : Parser.Command.values()) {
                        if (command == Parser.Command.INVALID) {
                            continue;
                        }
                        chatbotPrint(String.format("- %s", command.toString()));
                    }
                    break;
                }
            }

            chatbotPrint(END_DIVIDER);
            storage.saveTaskList(tasksList);

        }
        scanner.close();
    }
}
