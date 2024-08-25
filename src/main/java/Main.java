import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
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
        String[] dueDateArray = Arrays.copyOfRange(commandDetails, indexOfBy + 1, commandDetails.length);
        String dueDate = String.join(" ", dueDateArray);
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
        // get the start date
        String[] startDateArray = Arrays.copyOfRange(commandDetails, indexOfFrom + 1, indexOfTo);
        String startDate = String.join(" ", startDateArray);
        // get the end date
        String[] endDateArray = Arrays.copyOfRange(commandDetails, indexOfTo + 1, commandDetails.length);
        String endDate = String.join(" ", endDateArray);
        return new String[]{description, startDate, endDate};
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
                        String deadlineDescription = line.substring(8, line.indexOf("|", 8) - 1);
                        String dueDate = line.substring(line.indexOf("|", 8) + 2);
                        task = new Deadline(deadlineDescription, "D", dueDate);
                        task.setDone(isDone);
                    }
                    case 'E' -> {
                        // event object
                        String eventDescription = line.substring(8, line.indexOf("|", 8) - 1);
                        String startDate = line.substring(line.indexOf("|", 8) + 2, line.indexOf("-", line.indexOf("|", 8)));
                        String endDate = line.substring(line.indexOf("-", line.indexOf("|", 8)) + 1);
                        task = new Event(eventDescription, "E", startDate, endDate);
                        task.setDone(isDone);
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
                        Task deadline = new Deadline(deadlineDetails[0], "D", deadlineDetails[1]);
                        String addTaskSuccess = janet.addTaskToList(deadline);
                        System.out.println(addTaskSuccess);
                    }
                    case EVENT -> {
                        // get the details of the event task and create a new Event object
                        String[] eventDetails = findEventDetails(commandDetails);
                        Task event = new Event(eventDetails[0], "E", eventDetails[1], eventDetails[2]);
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
