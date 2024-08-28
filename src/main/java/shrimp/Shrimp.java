package shrimp;

import shrimp.exception.ShrimpException;
import shrimp.task.*;
import shrimp.utility.AnsiCode;
import shrimp.utility.Parser;
import shrimp.utility.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Shrimp {

    private static final Boolean NEW_EVENT_NOT_DONE = false;

    public static void main(String[] args) {
        //program initialise
        programStart();
    }

    static void programStart() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3\n";
        String logo = """
                
                       .__          .__               \s
                  _____|  |_________|__| _____ ______ \s
                 /  ___/  |  \\_  __ \\  |/     \\\\____ \\\s
                 \\___ \\|   Y  \\  | \\/  |  Y Y  \\  |_> >
                /____  >___|  /__|  |__|__|_|  /   __/\s
                     \\/     \\/               \\/|__|   \s
                                                      \s
                """;
        String output = greetings + logo;
        System.out.println(AnsiCode.CYAN + output);
        chatBotRun();
    }

    static void chatBotRun() {
        //scans for user's command
        Scanner sc = new Scanner(System.in);
        String userInput, output;
        TaskList taskList;

        try {
            taskList = Storage.loadTasks();
        } catch (IOException | ShrimpException e) {
            System.out.println("Oh nyoo~ Couldn't load tasks... Starting with an empty list.");
            taskList = new TaskList();
        }


        while (true) {
            try {
                userInput = sc.nextLine();  //read the next line of user input
                if (userInput == null || userInput.isEmpty()) {
                    throw new ShrimpException.InvalidCommandException();
                }

                Parser.CommandType commandType = Parser.parseCommand(userInput);

                switch (commandType) {
                    case BYE: //exits the program
                        programExit();
                        return;

                    case LIST:
                        if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        System.out.println("Gotchaaa~ Here's the list so far:");
                        for (int i = 0; i < taskList.getCount(); i++) {
                            Task task = taskList.getTask(i);
                            output = String.format("    %s.%s", i + 1, task);
                            System.out.println(AnsiCode.PURPLE + output + AnsiCode.CYAN);
                        }
                        System.out.printf("Lemme count~ You now have %s item(s) in your list!%n", taskList.getCount());
                        break;

                    case MARK:
                        int indexMark = getTaskNumber(userInput, commandType);
                        if (indexMark > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        Task oldTaskMark = taskList.getTask(indexMark);
                        Task updatedTaskMark = oldTaskMark.markAsDone();
                        taskList.replaceTask(indexMark, updatedTaskMark);
                        output = "heya~ I've checked this task as complete! Feels good, right?";
                        System.out.println(output);
                        System.out.println("    " + updatedTaskMark);
                        break;

                    case UNMARK:
                        int indexUnmark = getTaskNumber(userInput, commandType);
                        if (indexUnmark > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        Task oldTaskUnmark = taskList.getTask(indexUnmark);
                        Task updatedTaskUnmark = oldTaskUnmark.markAsNotDone();
                        taskList.replaceTask(indexUnmark, updatedTaskUnmark);
                        output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
                        System.out.println(output);
                        System.out.println("    " + updatedTaskUnmark);
                        break;

                    case DELETE:
                        int indexDelete = getTaskNumber(userInput, commandType);
                        if (indexDelete > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        Task taskDeleted = taskList.getTask(indexDelete);
                        taskList.deleteTask(indexDelete);
                        output = "Done! The task has been deleted!";
                        System.out.println(output);
                        System.out.println("    " + taskDeleted);
                        System.out.printf("You now have %s item(s) in your list!%n", taskList.getCount());
                        break;

                    case ADD:
                        if (userInput.length() <= 5) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String input = userInput.substring(5);
                        Todo newTodo = new Todo(input, NEW_EVENT_NOT_DONE); //creates a new Task.Task object
                        taskList.addTask(newTodo);
                        output = "rawr! '" + input + "' has been added to the list~";
                        System.out.println(output);
                        break;

                    case DEADLINE:
                        if (userInput.length() <= 9 || !userInput.contains("/by")) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String[] deadlineDetails = userInput.split("/by ");
                        String deadlineDescription = deadlineDetails[0].substring(9); // Extracting the task description
                        LocalDateTime by = getDateTime(deadlineDetails[1].trim());
                        Task newDeadline = new Deadline(deadlineDescription, by, NEW_EVENT_NOT_DONE);
                        taskList.addTask(newDeadline);
                        System.out.println("Gotchaa~ I've added this shrimp.task:");
                        System.out.println("    " + newDeadline);
                        System.out.println("You now have " + taskList.getCount() + " task(s) in the list~");
                        break;

                    case EVENT:
                        if (userInput.length() <= 6 || !userInput.contains("/from") || !userInput.contains("/to")) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String[] eventDetails = userInput.split("/from | /to ");
                        String eventDescription = eventDetails[0].substring(6); // Extracting the task description
                        LocalDateTime from = getDateTime(eventDetails[1].trim());
                        LocalDateTime to = getDateTime(eventDetails[2].trim());
                        Task newEvent = new Event(eventDescription, from, to, NEW_EVENT_NOT_DONE);
                        taskList.addTask(newEvent);
                        System.out.println("Gotchaa~ I've added this task:");
                        System.out.println("    " + newEvent);
                        System.out.println("You now have " + taskList.getCount() + " task(s) in the list~");
                        break;

                    default:
                        throw new ShrimpException.InvalidCommandException();
                }

                Storage.saveTasks(taskList);

            } catch (ShrimpException e) {
                System.out.println(AnsiCode.RED + e.getMessage() + String.format(" (%s)", e.getErrorCode()) + AnsiCode.CYAN);
            } catch (Exception e) {
                System.out.println(AnsiCode.RED + "Oh nyoo~ Something went wrong... Try again!" + AnsiCode.CYAN);
            }
        }
    }

    static void programExit() {
        String output = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(output);
    }

    // Helper method to extract shrimp.task number for MARK/UNMARK/DELETE
    private static int getTaskNumber(String userInput, Parser.CommandType type) throws ShrimpException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ShrimpException.MissingArgumentException(type);
        } catch (NumberFormatException e) {
            throw new ShrimpException.NumberFormatException();
        }
    }

    private static LocalDateTime getDateTime(String input) throws ShrimpException {
        try {
            return LocalDateTime.parse(input, Parser.PATTERN);
        } catch (DateTimeParseException e) {
            throw new ShrimpException.InvalidDateTimeException();
        }
    }
}
