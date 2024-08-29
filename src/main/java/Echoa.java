import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Echoa is a class that simulates a task checker.
 */

public class Echoa {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount;
    private static final String[] INSTRUCTION_LIST = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

    /**
     * Sets ups the file to be used.
     * If the filepath exists, the file will be extracted and returned.
     * Else, any necessary folders and files will be created, and returned.
     * Returns the file to input data into.
     *
     * @return File
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
     * @param f
     * @throws FileNotFoundException
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
                String date = content[3];
                taskList.add(new Deadline(description, isDone, date));
                taskCount++;
                break;
            case "E":
                String startTime = content[3];
                String endTime = content[4];
                taskList.add(new Event(description, isDone, startTime, endTime));
                taskCount++;
                break;
            }
        }
    }

    /**
     * The method loops through the taskList and updates the given file.
     *
     * @param f
     * @throws IOException
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
     * The method starts up a scanner and allows inputs from the command line by the user.
     * It also handles any exceptions and errors thrown within the program
     *
     * @param f
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
                    taskList.get(index).markAsDone();
                    System.out.println("Task marked :)");
                    System.out.println(taskList.get(index).toString() + "\n");
                    handleChange(f);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.get(index).unmarkAsUndone();
                    System.out.println("Task unmarked :(");
                    System.out.println(taskList.get(index).toString() + "\n");
                    handleChange(f);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    taskList.remove(index);
                    taskCount--;
                    System.out.println("Task deleted :/");
                    System.out.println(taskList.get(index).toString() + "\n");
                    System.out.println("Now you have " + (taskCount) + " task(s).\n");
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
                            String[] taskArray = task.split(" /", 2);
                            if (taskArray.length != 2) {
                                throw new InvalidDeadlineContentException();
                            }
                            String taskDescription = taskArray[0];
                            String taskDate = taskArray[1];
                            taskList.add(new Deadline(taskDescription, taskDate));
                            break;
                        }
                        case "event": {
                            String[] taskArray = task.split(" /", 3);
                            if (taskArray.length != 3) {
                                throw new InvalidEventContentException();
                            }
                            String taskDescription = taskArray[0];
                            String taskStart = taskArray[1];
                            String taskEnd = taskArray[2];
                            taskList.add(new Event(taskDescription, taskStart, taskEnd));
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
     * @param args
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
        }
    }
}
