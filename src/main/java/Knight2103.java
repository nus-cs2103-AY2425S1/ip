import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

public class Knight2103 {

    public static Optional<Task> formatToTask(String lineInFile) {
        String[] inputArray = lineInFile.split(" \\| ");
        try {
            Task taskToAdd;
            switch (inputArray[0]) {
                case "T":
                    if (inputArray.length != 3) {
                        throw new FileContentsInvalid("Number of columns mismatch. There should be 3 for Todo");
                    }
                    taskToAdd = new Todo(inputArray[2]);
                    if (inputArray[1].equals("0")) {
                        return Optional.of(taskToAdd);
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return Optional.of(taskToAdd);
                    } else {
                        throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                    }
                case "D":
                    if (inputArray.length != 4) {
                        throw new FileContentsInvalid("Number of columns mismatch. There should be 4 for Deadline");
                    }
                    try {
                        taskToAdd = new Deadline(inputArray[2], inputArray[3]);
                        if (inputArray[1].equals("0")) {
                            return Optional.of(taskToAdd);
                        } else if (inputArray[1].equals("1")) {
                            taskToAdd.markDone();
                            return Optional.of(taskToAdd);
                        } else {
                            throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Deadline format is wrong in the file contents");
                    }
                    break;
                case "E":
                    if (inputArray.length != 5) {
                        throw new FileContentsInvalid("Number of columns mismatch. There should be 5 for Events");
                    }
                    taskToAdd = new Event(inputArray[2], inputArray[3], inputArray[4]);
                    if (inputArray[1].equals("0")) {
                        return Optional.of(taskToAdd);
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return Optional.of(taskToAdd);
                    } else {
                        throw new FileContentsInvalid("the value of the 2nd column should only be 1 or 2");
                    }
                    //break;
                default:
                    throw new FileContentsInvalid("Only T, E, D accepted but others found");
            }
        } catch (FileContentsInvalid e) {
            System.out.println(e);
        }
        return Optional.empty(); // code will be problematic
    }
    public static ArrayList<Task> loadTaskList(File fileInput) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(fileInput);
            while (scanner.hasNextLine()) {
                formatToTask(scanner.nextLine()).ifPresent(
                        item -> taskList.add(item)
                );
                System.out.println(taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return taskList;
    }

    public static String printList(ArrayList<Task> list, int length) {
        String stringToPrint = "";
        for (int i = 0; i < length; i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + list.get(i) + "\n";
        }
        return stringToPrint;
    }

    public static String writeToFile(ArrayList<Task> list, int length) {
        String stringToWrite = "";
        for (int i = 0; i < length; i++) {
            stringToWrite += list.get(i).saveToFileFormat() + "\n";
        }
        return stringToWrite;
    }

    public static void main(String[] args) {
        // Starting of bot programme
        String botName = "Knight2103";
        String horiLine = "_____________";
        String intro = horiLine + "\n"
                + "Hello! I'm " + botName + "\n"
                + "What can I do for you?\n"
                + horiLine + "\n";
        System.out.println(intro);

        File savedTaskList = new File("./savedTaskList.txt"); // in ip folder, not java folder
        System.out.println("huh");
        System.out.println(savedTaskList.exists());
        System.out.println(savedTaskList.canWrite());

        // Initialise Task list
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            if (!savedTaskList.createNewFile()) {
                System.out.println("ya");
                taskList = loadTaskList(savedTaskList);
            }

            // Enable Input
            Scanner scanObject = new Scanner(System.in);
            String input = scanObject.nextLine();

            while (!input.equals("bye")) {
                String[] inputArray = input.split(" ", 2);
                Task taskToAdd;
                try {
                    FileWriter taskListWriter;
                    switch (inputArray[0]) {
                        case "list":
                            String listContents = printList(taskList, taskList.size());
                            System.out.println(horiLine + "\n" + listContents + horiLine);
                            break;
                        case "todo":
                            taskToAdd = new Todo(inputArray[1]);
                            taskList.add(taskToAdd);
                            System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", true);
                            taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                            taskListWriter.close();
                            break;
                        case "deadline":
                            String[] deadlineArray = inputArray[1].split(" /by ");
                            try {
                                taskToAdd = new Deadline(deadlineArray[0], deadlineArray[1]);
                                taskList.add(taskToAdd);
                                System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                                taskListWriter = new FileWriter("./savedTaskList.txt", true);
                                taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                                taskListWriter.close();
                                break;
                            } catch (IOException e) {
                                System.out.println("Problems creating an instance of FileWriter");
                            } catch (DateTimeParseException e) {
                                System.out.println("Deadline format is wrong during input. Please follow yyyy-mm-dd format");
                            }
                        break;
                        case "event":
                            String[] eventArray = inputArray[1].split(" /from | /to ");
                            taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
                            taskList.add(taskToAdd);
                            System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", true);
                            taskListWriter.write("\n" + taskToAdd.saveToFileFormat());
                            taskListWriter.close();
                            break;
                        case "mark":
                            int taskMarkIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                            taskList.get(taskMarkIndex).markDone(); // need to check if it works
                            System.out.println(horiLine + "\nNice! I've marked this task as done:\n" + taskList.get(taskMarkIndex) + "\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", false);
                            taskListWriter.write(writeToFile(taskList, taskList.size()));
                            taskListWriter.close();
                            break;
                        case "unmark":
                            int taskUnmarkIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                            taskList.get(taskUnmarkIndex).unmarkDone();
                            System.out.println(horiLine + "\nOK, I've marked this task as not done yet:\n" + taskList.get(taskUnmarkIndex) + "\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", false);
                            taskListWriter.write(writeToFile(taskList, taskList.size()));
                            taskListWriter.close();
                            break;
                        case "delete":
                            int taskDeleteIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                            Task taskToDelete = taskList.get(taskDeleteIndex);
                            taskList.remove(taskDeleteIndex);
                            System.out.println(horiLine + "\nNoted. I've removed this task:\n" + taskToDelete + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                            taskListWriter = new FileWriter("./savedTaskList.txt", false);
                            taskListWriter.write(writeToFile(taskList, taskList.size()));
                            taskListWriter.close(); // ensure data is written on the spot
                            break;

                        default:
                            throw new InstructionInvalid();
                    }
                } catch (InstructionInvalid e) {
                    System.out.println("Invalid Instruction. Only valid Instructions are list, todo, deadline, event, mark, unmark, delete");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There's an issue in the instruction format");
                } catch (NullPointerException e) { // only happen in mark and unmark I think due to TaskList dynamic allocation specified
                    System.out.println("There aren't so many tasks. Please if the task number is keyed in correctly. To see all tasks, type list");
                } catch (NumberFormatException e) {
                    System.out.println("Please state the task number in INTEGER. Definitely not the task name");
                } catch (IOException e) {
                    System.out.println("Problems creating an instance of FileWriter");
                }
                input = scanObject.nextLine();
            }
            System.out.println(horiLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + horiLine);
            scanObject.close();

        } catch (IOException e) {
                System.out.println("issues executing createNewFile()");
            }

    }
}
