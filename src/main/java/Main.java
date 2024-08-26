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
     * Returns a String array based on user's commands.
     * First element = a String of Deadline's description.
     * Second element = a String of Deadline's dueDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = Deadline.description, second elem = Deadline.dueDate.
     */
    public static String[] findDeadlineDetails(String[] commandDetails) {
        int indexOfBy = 0;
        // first word in commandDetails must be deadline, so start from the i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/by")) {
                indexOfBy = i;
            }
        }
        // get description of Deadline
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfBy);
        String description = String.join(" ", descriptionArray);
        // get due date
        String inputDate  = commandDetails[commandDetails.length - 2];    // yyyy-mm-dd
        LocalDate date = LocalDate.parse(inputDate);
        String outputDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        // get time
        String inputTime = commandDetails[commandDetails.length - 1];
        LocalTime time = LocalTime.parse(inputTime);
        String outputTime = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String dueDate = outputDate + " " + outputTime;
        return new String[]{description, dueDate};
    }

    /**
     * Returns a String array based on user's commands.
     * First element = a String of Event's description.
     * Second element = a String of Event's startDate (MMM dd yyyy hh:mm a).
     * Third element = a String of Event's endDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = Event.description, second elem = Event.startDate, third elem = Event.endDate.
     */
    public static String[] findEventDetails(String[] commandDetails) {
        int indexOfFrom = 0;
        int indexOfTo = 0;
        // first word in commandDetails must be event, so start from i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/from")) {
                indexOfFrom = i;
            }
            if (commandDetails[i].equals("/to")) {
                indexOfTo = i;
            }
        }
        // get the description
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfFrom);
        String description = String.join(" ", descriptionArray);

        // get the start date (yyyy-mm-dd)
        LocalDate startDate = LocalDate.parse(commandDetails[indexOfFrom + 1]);
        String outputStartDate = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        // get the start time (hh:mm, 24hr format)
        LocalTime inputStartTime = LocalTime.parse(commandDetails[indexOfFrom + 2]);
        String outputStartTime = inputStartTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String startDateAndTime = outputStartDate + " " + outputStartTime;

        // get the end date (yyyy-mm-dd)
        LocalDate endDate = LocalDate.parse(commandDetails[indexOfTo + 1]);
        String outputEndDate = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        // get the end time
        LocalTime inputEndTime = LocalTime.parse(commandDetails[indexOfTo + 2]);
        String outputEndTime = inputEndTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String endDateAndTime = outputEndDate + " " + outputEndTime;
        return new String[]{description, startDateAndTime, endDateAndTime};
    }


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
        Storage storage = new Storage("./janet.txt");
        // janet.txt should be checked and loaded at the start of the program
        if (Files.exists(Paths.get("./janet.txt"))) {
            // current directory already has a janet.txt file, load this into the arraylist listOfTasks
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
                        String[] deadlineDetails = findDeadlineDetails(commandDetails);
                        String dateAndTimeString = deadlineDetails[1];    // MMM dd yyyy hh:mm a
                        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
                        LocalDateTime dateAndTime = LocalDateTime.parse(dateAndTimeString, stringToDateTime);
                        Task deadline = new Deadline(deadlineDetails[0], "D", dateAndTime);
                        String addTaskSuccess = janet.addTaskToList(deadline);
                        System.out.println(addTaskSuccess);
                    }
                    case EVENT -> {
                        // get the details of the event task and create a new Event object
                        String[] eventDetails = findEventDetails(commandDetails);
                        String startDateAndTimeString = eventDetails[1];
                        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
                        LocalDateTime startDateAndTime = LocalDateTime.parse(startDateAndTimeString, stringToDateTime);

                        String endDateAndTimeString = eventDetails[2];
                        LocalDateTime endDateAndTime = LocalDateTime.parse(endDateAndTimeString, stringToDateTime);
                        Task event = new Event(eventDetails[0], "E", startDateAndTime, endDateAndTime);
                        String addTaskSuccess = janet.addTaskToList(event);
                        System.out.println(addTaskSuccess);
                    }
                }
            }
        }

        // once the program exits (user types in 'bye'), save the elements in janet.listOfTasks to the text file
        // saveToJanetTextFile(janet.getListOfTasks());
        storage.saveToJanetTextFile(janet.getListOfTasks());
    }
}
