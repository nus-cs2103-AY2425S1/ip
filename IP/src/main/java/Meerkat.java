import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.io.File; // allows write and read to file
import java.io.FileWriter;
import java.io.IOException;

public class Meerkat {

    private static final String STORAGEFILEPATH = "Meerkat.txt";
    private static String lines = "____________________________________________________________";
    private static String greeting = """
                Hello! I'm a meerkat
                What can I do for you?
                """;
    private static String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;
    private static List<Task> listOfTasks = new ArrayList<>();


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        File f = new File(STORAGEFILEPATH);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static String taskListToString(List<Task> taskList) {
        StringBuilder s = new StringBuilder();
        for (Task task : taskList) {
            s.append(task.toString()).append("\n");
        }
        return s.toString();
    }

    private static void printList(List<Task> thisList) {
        System.out.println(lines);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < thisList.size() + 1; i++) {
            System.out.println(i + "." + thisList.get(i-1).toString());
        }
        System.out.println(lines);
    }

    private static void createDeadlineTask(String name, String duedate) throws IOException {
        Task thisTask = new Deadline(name, duedate);
        listOfTasks.add(thisTask);
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
    }

    private static void createTodoTask(String name) throws IOException {
        Task thisTask = new Todo(name);
        listOfTasks.add(thisTask);
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
    }

    private static void createEventTask(String name, String start, String end) throws IOException {
        Task thisTask = new Event(name, start, end);
        listOfTasks.add(thisTask);
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
        System.out.println(lines + "\nGot it. I've added this task:\n  " + thisTask + "\nNow you have " + listOfTasks.size() + " tasks in the list\n" + lines);
    }

    private static void markTaskAsDone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            listOfTasks.get(taskNum - 1).markAsCompleted();
            System.out.println(lines + "\nNice! I've marked this task as done:\n" + listOfTasks.get(taskNum-1) + "\n" + lines);
            // task number is not within range
        } else {
            System.out.println(lines + "\nThis task does not exist! Unable to mark.\n" + lines);
        }
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    private static void markTaskAsUndone(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            listOfTasks.get(taskNum - 1).markAsIncomplete();
            System.out.println(lines + "\nOK, I've marked this task as not done yet:\n" + listOfTasks.get(taskNum - 1) + "\n" + lines);
            // task number is not within range
        } else {
            System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
        }
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    private static void deleteTask(int taskNum) throws IOException {
        if (taskNum > 0 && taskNum <= listOfTasks.size()) {
            System.out.println(lines + "\nroger that sir, I've removed this task:\n" + listOfTasks.get(taskNum - 1) + "\n" + lines);
            listOfTasks.remove(taskNum - 1);
            // task number is not within range
        } else {
            System.out.println(lines + "\nThis task does not exist! Unable to delete.\n" + lines);
        }
        writeToFile(STORAGEFILEPATH, taskListToString(listOfTasks));
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println(lines + "\n" + greeting + lines);

        while (true) {
            // scans the new text for new info
            String taskName = sc.nextLine();
            // splits string based on space
            String[] strArray = taskName.split(" ", 2);

            // create new specific task with appropriate params
            if (strArray[0].equalsIgnoreCase("todo")) {
                try {
                    createTodoTask(strArray[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your todo task.\n" + lines);
                }

            // create new deadline task
            } else if (strArray[0].equalsIgnoreCase("deadline")) {
                try {
                    String[] todoStringArray = taskName.split(" /by ");
                    String duedate = todoStringArray[1];
                    String name = todoStringArray[0].split(" ", 2)[1];
                    createDeadlineTask(name, duedate);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your deadline task.\n" + lines);
                }


            // create new event task
            } else if (strArray[0].equalsIgnoreCase("event")) {
                try {
                    String[] eventStringArray = taskName.split(" /from ");
                    String[] duration = eventStringArray[1].split(" /to ");
                    String name = eventStringArray[0].split(" ", 2)[1];
                    createEventTask(name, duration[0], duration[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(lines + "\nbruh. i need more info to create your event task.\n" + lines);
                }
            }

            // to end program
            else if (taskName.equalsIgnoreCase("bye")) {
                System.out.println(lines + "\n" + bye + lines);
                break;

            // to display list of items
            } else if (taskName.equalsIgnoreCase("list")) {
                printList(listOfTasks);

            // mark task as done
            } else if (strArray.length == 2 && strArray[0].equals("mark")) {
                try {
                    int taskNum = Integer.parseInt(strArray[1]);
                    markTaskAsDone(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to mark.\n" + lines);
                }

            // mark item as not done
            } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
                try {
                    int taskNum = Integer.parseInt(strArray[1]);
                    markTaskAsUndone(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                }

            // delete item
            } else if (strArray.length == 2 && strArray[0].equals("delete")) {
                try {
                    int taskNum = Integer.parseInt(strArray[1]);
                    deleteTask(taskNum);
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to delete.\n" + lines);
                }
            } else {
                System.out.println(lines + "\ni have nooo idea what you are sayin\n" + lines);
            }
        }
        System.exit(0);
    }
}
