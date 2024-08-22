import java.util.Scanner;
import java.util.ArrayList;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final ArrayList<Task> taskList = new ArrayList<>(100);

    // Inserts line indentation in response messages
    private static void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    private static void addTask(String type, String parameter) {
        Task newTask;
        String[] parameters;
        switch (type) {
        case "todo":
            newTask = new Todo(parameter);
            break;
        case "deadline":
            parameters = parameter.split("\\s*/by\\s*");
            if (parameters.length == 2 && !parameters[0].isEmpty()) {
                newTask = new Deadline(parameters[0], parameters[1]);
            } else {
                say(" I need one description and deadline using \"/by\"\n"
                        + " e.g. deadline CS2103T project /by Dec 31st");
                return;
            }
            break;
        case "event":
            parameters = parameter.split("\\s*/from\\s* | \\s*/to\\s*");
            if (parameters.length == 3 &&
                    !(parameters[0].isEmpty() || parameters[1].isEmpty())) {
                String description = parameters[0];
                String startTime = parameters[1];
                String endTime = parameters[2];
                newTask = new Event(description, startTime, endTime);
            } else {
                say(" I need one description, start and end time using \"/from\" and \"/to\"\n"
                        + " e.g. event project meeting /from Mon 2pm /to 4pm");
                return;
            }
            break;
        default:
            say(" The type of task is invalid! Something went wrong :(");
            return;
        }

        taskList.add(newTask);
        say(String.format(" Task added successfully!\n   %s\n Now you have %d tasks in the list",
                newTask, taskList.size()));
    }

    private static void listTask() {
        int listSize = taskList.size();
        String listInString = "";
        for (int i = 0; i < listSize; i += 1) {
            String taskString = String.format(" %d.%s", i + 1, taskList.get(i));
            listInString += taskString;
            if (i < listSize - 1) {
                listInString += "\n";
            }
        }
        say(listInString);
    }

    public static void main(String[] args) {
        say(GREETING_MESSAGE);
        boolean conversationIsOngoing = true;
        Scanner userInputReader = new Scanner(System.in);

        do {
            String userInput = userInputReader.nextLine();
            String[] processedInput = userInput.trim().split("\\s+", 2);
            String command = processedInput[0];

            switch (command) {
            case "bye":
                conversationIsOngoing = false;
                userInputReader.close();
                say(GOODBYE_MESSAGE);
                break;
            case "list":
                listTask();
                break;
            case "mark":
            case "unmark":
                try {
                    if (processedInput.length > 1) {
                        int taskNumber = Integer.parseInt(processedInput[1]);
                        Task task = taskList.get(taskNumber - 1);

                        switch (command) {
                        case "mark":
                            task.markAsDone();
                            say("Nice! I have marked this task as done:\n   " + task);
                            break;
                        case "unmark":
                            task.markAsNotDone();
                            say("OK, I've marked this task as not done yet:\n   " + task);
                            break;
                        }
                    } else {
                        say(" You have to give me a valid task number!\n e.g. mark 2");
                    }
                } catch (NumberFormatException e) {
                    // if the string after the mark command is not an integer
                    say(" You have to give me a valid task number!\n e.g. mark 2");
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = String.format(" There is no such task!\n "
                            + "You currently have %d tasks in your list", taskList.size());
                    say(errorMessage);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (processedInput.length > 1) {
                    addTask(command, processedInput[1]);
                } else {
                    say(" You need to give a description when adding tasks!");
                }
                break;
            default:
                say("Hmm... I'm not sure what you're trying to do :(");
            }
        } while (conversationIsOngoing);
    }
}
