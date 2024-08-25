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
     * input date and time format must be "yyyy-mm-dd HH:MM"
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
        String endDateAndTime = outputEndTime + " " + outputEndTime;
        return new String[]{description, startDateAndTime, endDateAndTime};
    }


    /**
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
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void checkInaccurateCommand(String[] commandDetails) throws JanetException {
        // checks for inaccurate commands 1. rubbish, 2. without any task description, 3. no number for mark/unmark/delete.
        if (!(commandDetails[0].equals("todo") || commandDetails[0].equals("deadline") || commandDetails[0].equals("event") || commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete"))) {
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


    /**
     * @param listOfTasks the list of tasks (ArrayList<Task>) that Janet has.
     * @throws IOException
     */
    public static void saveToJanetTextFile(ArrayList<Task> listOfTasks) throws IOException {
        // using FileWriter to write text into a text file (janet.txt).
        FileWriter fileWriter = new FileWriter("janet.txt");
        for (Task task : listOfTasks) {
            // task.symbol | task.isDone | task.description | if Deadline/Event, dueDate, start-end
            String marked = (task.isDone()) ? "1" : "0";
            String entry = task.getSymbol() + " | " + marked + " | " + task.getDescription();
            if (task instanceof Deadline) {
                entry = entry + " | " + ((Deadline) task).getDueDate();
            } else if (task instanceof Event) {
                entry = entry + " | " + ((Event) task).getStartDate() + "-" + ((Event) task).getEndDate();
            }
            fileWriter.write(entry + "\n");     // write the entry into the text file and save it
        }
        fileWriter.close();
    }


    /**
     * reads the content in janet.txt and loads them into an ArrayList<Task>, returns this ArrayList<Task>
     * @param path a String which represents the relative path to janet.txt in the directory.
     * @return an ArrayList<Task> listOfTasks, that contains Task objects
     */
    public static ArrayList<Task> textFileToArrayList(String path) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Task task = null;
                char taskSymbol = line.charAt(0);
                String done = String.valueOf(line.charAt(4));
                boolean isDone = (done.equals("1"));
                switch (taskSymbol) {
                    case 'T' -> {
                        // todo object
                        task = new ToDo(line.substring(8), "T");
                        task.setDone(isDone);
                    }
                    case 'D' -> {
                        // deadline object
                        task = createDeadlineFromJanetTextFile(line, isDone);
                    }
                    case 'E' -> {
                        // event object
                        task = createEventFromJanetTextFile(line, isDone);
                    }
                }
                listOfTasks.add(task);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfTasks;
    }


    /**
     * @param line a line of text from the janet.txt file.
     * @param isDone isDone = true if task has been marked else false.
     * @return a new Deadline object created using the parameters.
     */
    public static Deadline createDeadlineFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String deadlineDescription = line.substring(8, line.indexOf("|", 8) - 1);

        // get the due date and convert into LocalDateTime
        String dueDateAndTime = line.substring(line.indexOf("|", 8) + 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        LocalDateTime dueDate = LocalDateTime.parse(dueDateAndTime, formatter);

        Deadline deadline = new Deadline(deadlineDescription, "D", dueDate);
        deadline.setDone(isDone);
        return deadline;
    }


    /**
     * @param line a line of text from the janet.txt file
     * @param isDone isDone = true if task has been marked else false.
     * @return a new Event object created using the parameters.
     */
    public static Event createEventFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String eventDescription = line.substring(8, line.indexOf("|", 8) - 1);

        // get startDate and convert into LocalDateTime
        String startDateAndTime = line.substring(line.indexOf("|", 8) + 2, line.indexOf("-", line.indexOf("|", 8)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        LocalDateTime startDate = LocalDateTime.parse(startDateAndTime, formatter);

        // get the endDate and convert into LocalDateTime
        String endDateAndTime = line.substring(line.indexOf("-", line.indexOf("|", 8)) + 1);
        LocalDateTime endDate = LocalDateTime.parse(endDateAndTime, formatter);

        Event event = new Event(eventDescription, "E", startDate, endDate);
        event.setDone(isDone);
        return event;
    }


    public static void main(String[] args) throws IOException {
        Janet janet;
        // janet.txt should be checked and loaded at the start of the program
        if (Files.exists(Paths.get("./janet.txt"))) {
            // current directory already has a janet.txt file, load this into the arraylist listOfTasks
            janet = new Janet(textFileToArrayList("./janet.txt"));
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
        saveToJanetTextFile(janet.getListOfTasks());
    }
}
