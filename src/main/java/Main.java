import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * Throws JanetException when,
     * 1. mark/unmark/delete X, where X cannot be parsed into an Integer.
     * 2. mark/unmark/delete X, where X can be parsed into an Integer but, is <= 0 or > number of tasks in list.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void validateCommand(String[] commandDetails, int numOfTasksInList) throws JanetException {
        // when mark/unmark/delete X, where X is too big (out or bounds) OR <= 0 OR when the list is empty.
        if ((commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete")) && commandDetails.length > 1) {
            // when the command is mark/unmark X OR delete, where X is an invalid num (too big or <= 0)
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(commandDetails[1]);   // commandDetails[1] could be a string
            } catch (NumberFormatException e) {
                throw new JanetException("WHOOPS! Please provide an integer value task number!");
            }
            if (taskNumber <= 0) {
                // still need to handle case when taskNumber >= taskIndex + 1 (unable to access janet.getTaskIndex())
                throw new JanetException("WHOOPS! Your task number cannot be negative or 0!");
            } else if (taskNumber > numOfTasksInList) {
                throw new JanetException("WHOOPS! You don't have a task of this number!");
            }
        }
    }


    /**
     * Throws JanetException when,
     * 1. first word in command is not todo/event/deadline/mark/unmark/delete.
     * 2. mark/unmark/delete and the task number is not specified.
     * 3. todo/event/deadline and the description is not stated.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void checkInaccurateCommand(String[] commandDetails) throws JanetException {
        // checks for inaccurate commands 1. rubbish, 2. without any task description, 3. no number for mark/unmark/delete.
        if (!(commandDetails[0].equals("todo") || commandDetails[0].equals("deadline") || commandDetails[0].equals("event")
                || commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete"))) {
            // when the command is gibberish and NOT one of the commands (todo, deadline, event, mark, unmark, delete)
            throw new JanetException("WHOOPS! I'm only a chatbot, so I don't know what that means...");
        } else if (commandDetails.length == 1) {
            if (commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete")) {
                // when the command is either (mark, unmark, delete), BUT there is no task specified
                throw new JanetException("WHOOPS! I don't know which task you are referring to...");
            } else {
                // when the command is either (todo, deadline, todo), BUT there is no task description
                throw new JanetException("WHOOPS! You can't leave out the task's description!");
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Janet janet;

        // storage checks if the current directory has a janet.txt file
        Storage storage = new Storage("./janet.txt");

        if (Files.exists(Paths.get("./janet.txt"))) {
            // if current directory already has a janet.txt file, storage will load this into the arraylist listOfTasks
            janet = new Janet(storage.textFileToArrayList());
        } else {
            janet = new Janet();
        }

        System.out.println(janet.greet());

        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                // say bye and exit the program
                String exitMessage = janet.exit();
                System.out.println(exitMessage);
                break;
            } else if (command.equals("list")) {
                // show all the tasks inside the list of task
                String currentListOfTasks = janet.showList();
                System.out.println(currentListOfTasks);
            } else {
                String[] commandDetails = command.split(" ");   // an array containing each word of the command
                // handle exceptions
                try {
                    // validateCommand and checkInaccurateCommand will throw out a JanetException
                    validateCommand(commandDetails, janet.getTaskIndex());
                    checkInaccurateCommand(commandDetails);
                } catch (JanetException e) {
                    // print the error message and allow the program to continue (don't exit the program)
                    System.out.println(e.getMessage());
                    continue;
                }
                CommandType commandType = CommandType.valueOf(commandDetails[0].toUpperCase());  // pass user input to be CommandType enum
                switch (commandType) {
                    case MARK -> {
                        // mark the task as done
                        String markSuccess = janet.markAsDone(Integer.parseInt(commandDetails[1]));
                        System.out.println(markSuccess);
                                  }
                    case UNMARK -> {
                        // unmark the task
                        String unmarkSuccess = janet.unmark(Integer.parseInt(commandDetails[1]));
                        System.out.println(unmarkSuccess);
                    }
                    case DELETE -> {
                        // delete specified task
                        String deleteSuccess = janet.deleteTask(Integer.parseInt(commandDetails[1]));
                        System.out.println(deleteSuccess);
                    }
                    case TODO -> {
                        // get the todo description and create a new Todo object
                        String[] todoItem = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
                        String todoDescription = String.join(" ", todoItem);
                        Task todo = new ToDo(todoDescription, "T");
                        String addTaskSuccess = janet.addTaskToList(todo);
                        System.out.println(addTaskSuccess);
                    }
                    case DEADLINE -> {
                        // get the details of the deadline task and create a new Deadline object
                        Task deadline = Parser.createDeadlineCommand(command);
                        String addTaskSuccess = janet.addTaskToList(deadline);
                        System.out.println(addTaskSuccess);
                    }
                    case EVENT -> {
                        // get the details of the event task and create a new Event object
                        Task event = Parser.createEventCommand(command);
                        String addTaskSuccess = janet.addTaskToList(event);
                        System.out.println(addTaskSuccess);
                    }
                }
            }
        }

        // once the program exits (user types in 'bye'), save the elements in janet.listOfTasks to the text file
        storage.saveToJanetTextFile(janet.getListOfTasks());
    }
}
