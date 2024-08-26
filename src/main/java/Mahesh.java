import java.io.File;
import java.io.FileWriter;
import java.lang.IndexOutOfBoundsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mahesh {

    
    /**
     * List to store tasks.
     */
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Counter to keep track of the number of tasks.
     */
    private static int taskCount = 0;

    public static void main(String[] args) {
        Mahesh.initializeList();
        Ui.printStartupMessage();

        
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
            String commandString = tokenizedInput.nextToken();
            Task task;
            try {
                Command command = Command.fromString(commandString);
                switch (command) {
                case LIST:
                    try {
                        Mahesh.printList();
                    } catch (MaheshException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case BYE:
                    Ui.printExitMessage();
                    exit = true;
                    break;
                case MARK:
                    try {
                        task = list.get(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                        task.markAsDone();
                        Ui.printMarkedAsDone(task);
                    } catch (IndexOutOfBoundsException err) {
                        Ui.printNoSuchTaskErr(taskCount);
                    }
                    break;
                case UNMARK:
                    try {
                        task = list.get(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                        task.unmarkAsDone();
                        Ui.printUnmarkedAsDone(task);
                    } catch (IndexOutOfBoundsException err) {
                        Ui.printNoSuchTaskErr(taskCount);
                    }
                    break;
                case TODO:
                    try {
                        Mahesh.addToList(Todo.parseTodo(tokenizedInput));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case DEADLINE:
                    try { 
                        Mahesh.addToList(Deadline.parseDeadline(tokenizedInput));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case EVENT: 
                    try {
                        Mahesh.addToList((Event.parseEvent(tokenizedInput)));
                    } catch (MaheshException err) {
                        Ui.printIncompleteCommandErr(err);
                    }
                    break;
                case DELETE:
                    try {
                        Mahesh.deleteFromList(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                    } catch (IndexOutOfBoundsException err) {
                        Ui.printNoSuchTaskErr(taskCount);
                    }
                    break;
                }
            } catch (MaheshException err) {
                System.out.println(err.getMessage());
            }
            Mahesh.saveListToFile();
        }

        scan.close();
    }

    /**
     * Adds a task to the list and increments the task count.
     *
     * @param task The task to be added to the list.
     */
    private static void addToList(Task task) {
        Mahesh.list.add(task);
        Mahesh.taskCount++;
        Ui.printTaskAdded(task, taskCount);
    }
    
    /**
     * Deletes a task from the list at the specified index and decrements the task count.
     *
     * @param index The index of the task to be removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    private static void deleteFromList(int index) throws IndexOutOfBoundsException {
        Task task = Mahesh.list.get(index);
        Mahesh.list.remove(index);
        Mahesh.taskCount--;
        Ui.printTaskDeleted(task, index);
    }
    
    /**
     * Prints all tasks in the list. Throws an exception if the list is empty.
     *
     * @throws MaheshException If there are no tasks in the list.
     */
    private static void printList() throws MaheshException {
        if (Mahesh.taskCount == 0) {
            throw new MaheshException("You have no tasks! Add a few tasks (todo, deadline or event)");
        }
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }

    /**
     * Initializes the task list by reading from a data file.
     * If the file or its directories do not exist, they are created.
     * The method reads each line from the file, parses it, and adds the corresponding task to the list.
     * If the file contains corrupted data, a message is printed to the console.
     */
    private static void initializeList() {
        try {
            File dataFile = new File("../../../data/mahesh.txt");
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            Scanner fileScanner = new Scanner(dataFile);
            boolean isCorrupted = false;
            while (fileScanner.hasNextLine()) {
                try {
                    String[] dataItem = fileScanner.nextLine().split("/");
                    String type = dataItem[0];
                    boolean isDone = Boolean.parseBoolean(dataItem[1]);
                    switch (type) {
                    case "T":
                        Mahesh.list.add(new Todo(dataItem[2], isDone));
                        Mahesh.taskCount++;
                        break;
                    case "D":
                        Mahesh.list.add(new Deadline(dataItem[2], LocalDateTime.parse(dataItem[3]), isDone));
                        Mahesh.taskCount++;
                        break;
                    case "E":
                        Mahesh.list.add(new Event(dataItem[2], LocalDateTime.parse(dataItem[3]), LocalDateTime.parse(dataItem[4]), isDone));
                        Mahesh.taskCount++;
                        break;
                    default:
                        isCorrupted = true;
                    }
                } catch (Exception e) {
                    isCorrupted = true;
                }
            }
            if (isCorrupted) {
                System.out.println("Some tasks may not have loaded correctly due to corrupted data file.");
            }
            fileScanner.close();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
    
    /**
     * Saves the current task list to a data file.
     * If the file or its directories do not exist, they are created.
     * Each task is written to the file in a specific format.
     */
    private static void saveListToFile() {
        try {
            File dataFile = new File("../../../data/mahesh.txt");
            if (!dataFile.exists()) {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(dataFile);
            for (Task task : Mahesh.list) {
                fileWriter.write(task.toFileEntry() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

}
