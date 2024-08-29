import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Echoa is a class that simulates a task checker.
 */

public class Echoa {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount;
    private static final String[] INSTRUCTION_LIST = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    /**
     * Sets ups the file to be used.
     * If the filepath exists, the file will be extracted and returned.
     * Else, any necessary folders and files will be created, and returned.
     * Returns the file to input data into.
     *
     * @return File that is going to be read and written into.
     */
    public static File setUpFile() {
        String filePath = "./data/echoa.txt";
        File f = new File(filePath);

        File parentDir = f.getParentFile();
        if (!parentDir.exists()) {
            if (!parentDir.mkdirs()) {
               return null;
            }
        }

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                return null;
            }
        } else {
            System.out.println("File exists");
        }

        return f;
    }

    /**
     * The method loads the relevant tasks into the taskList
     * based on the given file.
     *
     * @param f File that is read and written into.
     * @throws FileNotFoundException when the File is not found.
     */
    public static void loadInformation(File f) throws FileNotFoundException {

        Scanner fileReader = new Scanner(f);

        while (fileReader.hasNextLine()) {
            String t = fileReader.nextLine();
            String[] content = t.split(" \\| ");

            String type = content[0];
            boolean isDone = content[1].equals("1");
            String description = content[2];

            switch (type) {
            case "T":
                taskList.add(new ToDo(description, isDone));
                taskCount++;
                break;
            case "D":
                String[] dateAndTime = content[3].split(" ", 2);
                String date = dateAndTime[0];
                String time = dateAndTime[1];
                taskList.add(new Deadline(description, isDone, createDateTime(date, time)));
                taskCount++;
                break;
            case "E":
                String[] startDateAndTime = content[3].split(" ", 2);
                String startDate = startDateAndTime[0];
                String startTime = startDateAndTime[1];
                String[] endDateAndTime = content[4].split(" ", 2);
                String endDate = endDateAndTime[0];
                String endTime = endDateAndTime[1];
                taskList.add(new Event(description, isDone, createDateTime(startDate, startTime), createDateTime(endDate,  endTime)));
                taskCount++;
                break;
            }
        }
    }

    /**
     * The method loops through the taskList and updates the given file.
     *
     * @param f File that is written into.
     * @throws IOException when there are issues writing into the file.
     */
    public static void handleChange(File f) throws IOException {
        FileWriter fw1 = new FileWriter(f);
        fw1.write(taskList.get(0).toText() + System.lineSeparator());
        fw1.close();

        FileWriter fw2 = new FileWriter(f, true);
        for (int i = 1; i < taskList.size(); i++) {
            fw2.write(taskList.get(i).toText() + System.lineSeparator());

        }
        fw2.close();
    }

    /**
     * The method creates a LocalDateTime with the given dateString and timeString.
     *
     * @param dateString String representation of date.
     * @param timeString Time representation of date.
     * @return LocalDateTime of the dateString and timeString.
     * @throws DateTimeParseException when dateString and timeString are not in the correct format.
     */
    public static LocalDateTime createDateTime(String dateString, String timeString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return LocalDateTime.of(date, time);
    }

    /**
     * The method removes excess white space in all the Strings within the commandArray.
     *
     * @param commandArray Array containing the different Strings of commands.
     */
    public static void trimSplitCommands(String[] commandArray) {
        for (int i = 0; i < commandArray.length; i++) {
            commandArray[i] = commandArray[i].trim();
        }
    }


    /**
     * The method starts up a scanner and allows inputs from the command line by the user.
     * It also handles any exceptions and errors thrown within the program
     *
     * @param f File that is read and written into by the program.
     */
    public static void start(File f) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, I'm Echoa!");
        System.out.println("What can I do for you?\n");

        try {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();

                if (command.isBlank()) {
                    throw new InvalidInstructionException("Blank");
                } else if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("My Task List");
                    if (taskCount == 0) {
                        System.out.println("No tasks yet :o");
                    } else {
                        for (int i = 0; i < taskCount; i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + taskList.get(i).toString());
                        }
                    }
                    System.out.println();
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    System.out.println("Task marked :)");
                    System.out.println(taskList.get(index).toString() + "\n");
                    taskList.get(index).markAsDone();
                    handleChange(f);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    System.out.println("Task unmarked :(");
                    System.out.println(taskList.get(index).toString() + "\n");
                    taskList.get(index).unmarkAsUndone();
                    handleChange(f);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    System.out.println("Task deleted :/");
                    System.out.println(taskList.get(index).toString() + "\n");
                    System.out.println("Now you have " + (taskCount) + " task(s).\n");
                    taskList.remove(index);
                    taskCount--;
                    handleChange(f);
                } else {
                    String[] commandArr = command.split(" ", 2);

                    if (commandArr.length != 2) {
                        throw new InvalidTaskContentException();
                    }

                    String type = commandArr[0];
                    String task = commandArr[1];

                    switch (type) {
                        case "todo":
                            taskList.add(new ToDo(task));
                            break;
                        case "deadline": {
                            String[] taskArray = task.split("/", 2);
                            trimSplitCommands(taskArray);
                            if (taskArray.length != 2) {
                                throw new InvalidDeadlineContentException();
                            }
                            String taskDescription = taskArray[0];
                            String taskDate = taskArray[1];

                            String[] taskDateArray = taskArray[1].split(" ", 2);
                            if (taskDateArray.length != 2) {
                                throw new InvalidDeadlineContentException();
                            }
                            String date = taskDateArray[0];
                            String time = taskDateArray[1];
                            LocalDateTime dateAndTime = createDateTime(date, time);
                            taskList.add(new Deadline(taskDescription, dateAndTime));
                            break;
                        }
                        case "event": {
                            String[] taskArray = task.split("/", 3);
                            trimSplitCommands(taskArray);
                            if (taskArray.length != 3) {
                                throw new InvalidEventContentException();
                            }
                            String taskDescription = taskArray[0];
                            String taskStart = taskArray[1];
                            String taskEnd = taskArray[2];

                            String[] taskStartArray = taskStart.split(" ", 2);
                            String[] taskEndArray = taskEnd.split(" ", 2);

                            if (taskStartArray.length != 2 || taskEndArray.length != 2) {
                                throw new InvalidEventContentException();
                            }

                            String startDate = taskStartArray[0];
                            String startTime = taskStartArray[1];
                            LocalDateTime startDateAndTime = createDateTime(startDate, startTime);

                            String endDate = taskEndArray[0];
                            String endTime = taskEndArray[1];
                            LocalDateTime endDateAndTime = createDateTime(endDate, endTime);

                            taskList.add(new Event(taskDescription, startDateAndTime, endDateAndTime));
                            break;
                        }
                        default:
                            throw new InvalidInstructionException(type);
                    }
                    taskList.get(taskCount).unmarkAsUndone();
                    System.out.println("Task added!");
                    System.out.println(taskList.get(taskCount).toString());
                    System.out.println("Now you have " + (taskCount + 1) + " task(s).\n");
                    taskCount++;
                    handleChange(f);
                }
            }
        } catch (InvalidInstructionException e) {
            System.out.println(e.toString());
            System.out.println("Here are the valid instructions: ");
            for (String i : INSTRUCTION_LIST) {
                System.out.print("- ");
                System.out.println(i);
            }
            System.out.println("Please try again.");
        } catch (InvalidTaskContentException e) {
            System.out.println(e.toString());
            System.out.println("Please try again.");
        } finally {
            scanner.close();
        }
    }

    /**
     * The main method is the entry point to the application.
     * It catches any file related exception and handles them.
     *
     * @param args Arguments inputted into the command line interface.
     */
    public static void main(String[] args) {

        File f = setUpFile();

        // File is not available
        if (f == null) {
            System.out.println("No file");
            return;
        }

        try {
            loadInformation(f);
            start(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("An error has occurred when writing to the file");
        } catch (DateTimeParseException e) {
            System.out.println("Date or time is inputted in the wrong format!");
        }
    }
}
