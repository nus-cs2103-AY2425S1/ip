import java.util.Scanner;
import java.io.IOException;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String TASK_DATA_PATH = "src/data/tasks.txt";
    private final StorageManager storageManager;
    private TaskList tasks;

    public MrYapper(String filePath) {
        this.storageManager = new StorageManager(filePath);
        try {
            this.tasks = storageManager.retrieveData();
        } catch (IOException e) {
            System.out.println(" An error occurred when creating a new data file :(");
        }
    }

    // Inserts line indentation in response messages
    public void say(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    private void addTask(String type, String[] processedInput) throws InvalidTaskException {
        if (processedInput.length < 2) {
            throw new InvalidTaskException(type, " You need to provide the task details!");
        }
        String details = processedInput[1];
        Task newTask;
        String[] parameters;

        switch (type) {
        case "todo":
            newTask = new Todo(details);
            break;
        case "deadline":
            parameters = details.split("/by");
            String deadlineDescription = parameters[0].trim();
            String deadlineTime = parameters[1].trim();
            if (parameters.length != 2) {
                throw new InvalidTaskException(type, " I'll need you to format your details properly");
            } else if (deadlineDescription.isEmpty()) {
                throw new InvalidTaskException(type, " Your description cannot be empty!");
            } else if (deadlineTime.isEmpty()) {
                throw new InvalidTaskException(type, " Your deadline cannot be empty!");
            } else {
                newTask = new Deadline(deadlineDescription,
                        DateTimeParser.parseDateTime(deadlineTime));
            }
            break;
        case "event":
            parameters = details.split("/from");
            String eventDescription = parameters[0].trim();
            if (parameters.length != 2) {
                throw new InvalidTaskException(type, " I'll need you to format your details properly");
            } else if (eventDescription.isEmpty()) {
                throw new InvalidTaskException(type, " Your description cannot be empty!");
            }

            String[] timings = parameters[1].trim().split("/to");
            String eventStart = timings[0].trim();
            String eventEnd = timings[1].trim();

            if (timings.length != 2) {
                throw new InvalidTaskException(type, " I'll need you to format your details properly");
            } else if (timings[0].trim().isEmpty()) {
                throw new InvalidTaskException(type, " Your start time cannot be empty!");
            } else if (timings[1].trim().isEmpty()) {
                throw new InvalidTaskException(type, " Your end time cannot be empty!");
            } else {
                newTask = new Event(eventDescription,
                        DateTimeParser.parseDateTime(eventStart),
                        DateTimeParser.parseDateTime(eventEnd));
            }
            break;
        default:
            say(" The type of task is invalid! Something went wrong :(");
            return;
        }

        tasks.add(newTask);
        say(String.format(" Task added successfully!\n   %s\n Now you have %d tasks in the list",
                newTask, tasks.count()));
    }

    private void deleteTask(int taskNumber) {
        Task deletedTask = tasks.remove(taskNumber);
        say(String.format(" Task deleted successfully!\n   %s\n Now you have %d tasks in the list",
                deletedTask, tasks.count()));
    }

    public void run() {
        boolean conversationIsOngoing = false;
        if (tasks != null && storageManager != null) {
            say(GREETING_MESSAGE);
            conversationIsOngoing = true;
        }

        Scanner userInputReader = new Scanner(System.in);
        while (conversationIsOngoing) {
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
                say(tasks.toString());
                break;
            case "delete":
                try {
                    if (processedInput.length > 1) {
                        int taskNumber = Integer.parseInt(processedInput[1]);
                        deleteTask(taskNumber);
                    } else {
                        say(" You have to give me a valid task number!\n e.g. delete 2");
                    }
                } catch (NumberFormatException e) {
                    // if the string after the mark command is not an integer
                    say(" You have to give me a valid task number!\n e.g. delete 2");
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = String.format(" There is no such task!\n "
                            + "You currently have %d tasks in your list", tasks.count());
                    say(errorMessage);
                }
                break;
            case "mark":
            case "unmark":
                try {
                    if (processedInput.length > 1) {
                        int taskNumber = Integer.parseInt(processedInput[1]);

                        switch (command) {
                        case "mark":
                            say("Nice! I have marked this task as done:\n   "
                                    + tasks.mark(taskNumber));
                            break;
                        case "unmark":
                            tasks.unmark(taskNumber);
                            say("OK, I've marked this task as not done yet:\n   "
                                    + tasks.unmark(taskNumber));
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
                            + "You currently have %d tasks in your list", tasks.count());
                    say(errorMessage);
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    addTask(command, processedInput);
                } catch (InvalidTaskException e) {
                    say(e.getMessage());
                }
                break;
            default:
                say("Hmm... I'm not sure what you're trying to do :(");
            }
        }

        userInputReader.close();
    }

    public static void main(String[] args) {
        new MrYapper(TASK_DATA_PATH).run();
    }
}
