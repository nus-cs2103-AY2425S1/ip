import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MrYapper {
    private static final String GREETING_MESSAGE = " Hello! I'm MrYapper\n"
            + " What can I do for you?";
    private static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    private static final String TASK_DATA_PATH = "src/data/tasks.txt";
    private TaskList tasks;

    // Inserts line indentation in response messages
    private void say(String message) {
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

    // reads the task data from the data file and adds task into the task ArrayList
    private Task readTaskData(String taskData) throws InvalidDataFormatException {
        try {
            String[] processedData = taskData.split(" \\|\\|\\| ");
            Task task;
            String taskDescription = processedData[2];

            switch (processedData[0]) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription, processedData[3]);
                break;
            case "E":
                task = new Event(taskDescription, processedData[3], processedData[4]);
                break;
            default:
                throw new InvalidDataFormatException(taskData);
            }

            boolean taskIsDone;
            taskIsDone = Integer.parseInt(processedData[1]) > 0;
            if (taskIsDone) {
                task.markAsDone();
            }

            return task;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDataFormatException(taskData);
        }
    }

    public void run() {
        boolean conversationIsOngoing = false;

        // check if the tasks data file exists
        try {
            File taskData = new File(TASK_DATA_PATH);
            if (!taskData.exists()) {
                boolean fileCreationSuccessful = taskData.createNewFile();
                if (fileCreationSuccessful) {
                    System.out.println("Data file creation successful");
                }
                this.tasks = new TaskList();
            } else {
                ArrayList<Task> taskList = new ArrayList<>(100);
                Scanner dataFileReader = new Scanner(taskData);
                while (dataFileReader.hasNextLine()) {
                    taskList.add(readTaskData(dataFileReader.nextLine()));
                }
                dataFileReader.close();
                this.tasks = new TaskList(taskList);
            }

            say(GREETING_MESSAGE);
            conversationIsOngoing = true;
        } catch (IOException e) {
            say("It seems something is wrong when creating data file :(");
        } catch (InvalidDataFormatException e) {
            say(e.getMessage());
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
        tasks.saveToStorage();
    }

    public static void main(String[] args) {
        new MrYapper().run();
    }
}
