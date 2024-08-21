import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Barney {
    private static String LONG_LINE = "____________________________________________________________";
    private static String SAVE_FILE_PATH = "list.txt";
    static String SAVE_FILE_DELIMITER = "###";

    // File I/O
    private static ArrayList<Task> readFile() throws FileNotFoundException, InvalidSaveFormatException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        File listFile = new File(SAVE_FILE_PATH);
        Scanner FILE_IN = new Scanner(listFile);
        while (FILE_IN.hasNext()) {
            String line = FILE_IN.nextLine();
            String[] taskData = line.split(SAVE_FILE_DELIMITER);
            Task newTask;
            switch (taskData[0]) {
                case "T":
                    newTask = new Todo(taskData[2]);
                    break;
                case "D":
                    newTask = new Deadline(taskData[2], taskData[3]);
                case "E":
                    newTask = new Event(taskData[2], taskData[3], taskData[4]);
                    break;
                default:
                    throw new InvalidSaveFormatException("Invalid task type in the file: " + taskData[0]);
            }

            switch (taskData[1]) {
                case "1":
                    newTask.mark();
                    break;
                case "0":
                    newTask.unmark();
                    break;
                default:
                    throw new InvalidSaveFormatException("Invalid task status in the file: " + taskData[1]);
            }

            taskList.add(newTask);
        }
        FILE_IN.close();
        return taskList;
    }

    private static ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            taskList = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("No save file found, starting with an empty list.");
        } catch (InvalidSaveFormatException e) {
            System.out.println("Invalid save file format: " + e.getMessage() + "\n" + "Starting with an empty list.");
        } catch (Exception e) {
            System.out.println("Error reading save file: " + e.getMessage() + "\n" + "Starting with an empty list.");
        }
        return taskList;
    }

    private static void writeFile(ArrayList<Task> taskList) throws FileNotFoundException, IOException {
        FileWriter FILE_OUT = new FileWriter(SAVE_FILE_PATH);
        for (Task task : taskList) {
            FILE_OUT.write(task.toSaveString() + "\n");
        }
        FILE_OUT.close();
    }

    private static boolean writeData(ArrayList<Task> taskList) {
        try {
            writeFile(taskList);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error saving data: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        // Welcome text
        String welcomeText = "Hello, I am Barney <RAWR>, what can I do for you?";
        System.out.println(welcomeText);
        System.out.println(LONG_LINE);

        ArrayList<Task> taskList = loadData();

        Scanner STD_IN = new Scanner(System.in);
        String command;

        String taskDescription;
        Boolean isChatting = true;
        while (isChatting) {
            System.out.println(">>>");
            command = STD_IN.next();
            System.out.println("<<<");

            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
                    }
                    System.out.println(LONG_LINE);
                    break;
                case "mark":
                    String markStr = STD_IN.nextLine().trim();
                    if (!markStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        System.out.println(markStr);
                        break;
                    }

                    int markIndex = Integer.parseInt(markStr) - 1;

                    if (markIndex >= taskList.size() || markIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    taskList.get(markIndex).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(markIndex).toString());

                    STD_IN.nextLine();
                    break;
                case "unmark":
                    String unmarkStr = STD_IN.nextLine().trim();
                    if (!unmarkStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        break;
                    }

                    int unmarkIndex = Integer.parseInt(unmarkStr) - 1;

                    if (unmarkIndex >= taskList.size() || unmarkIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    taskList.get(unmarkIndex).unmark();
                    System.out.println("OK, I've unmarked this task as not done yet:");
                    System.out.println(taskList.get(unmarkIndex).toString());
                    break;
                case "todo":
                    taskDescription = STD_IN.nextLine().trim();
                    if (taskDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a task description");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Todo(taskDescription));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
                    System.out.println(LONG_LINE);
                    break;
                case "deadline":
                    String deadlineDescription = "";
                    String deadlineBy = "";
                    String upcoming = STD_IN.next();
                    while (!upcoming.equals("/by")) {
                        deadlineDescription += " " + upcoming;
                        upcoming = STD_IN.next();
                    }

                    deadlineDescription = deadlineDescription.trim();
                    if (deadlineDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a description");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    deadlineBy = STD_IN.nextLine().trim();
                    if (deadlineBy.equals("")) {
                        System.out.println("Empty deadline: Please add in a deadline");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Deadline(deadlineDescription, deadlineBy));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
                    System.out.println(LONG_LINE);
                    break;
                case "event":
                    String eventDescription = "";
                    String eventAtStr = "";
                    String n = STD_IN.next();
                    while (!n.equals("/from")) {
                        eventDescription += " " + n;
                        n = STD_IN.next();
                    }
                    n = STD_IN.next();
                    while (!n.equals("/to")) {
                        eventAtStr += " " + n;
                        n = STD_IN.next();
                    }
                    String eventToStr = STD_IN.nextLine();

                    eventDescription = eventDescription.trim();
                    if (eventDescription.equals("")) {
                        System.out.println("Empty task description: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    eventAtStr = eventAtStr.trim();
                    if (eventAtStr.equals("")) {
                        System.out.println("Empty /from time: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    eventToStr = eventToStr.trim();
                    if (eventToStr.equals("")) {
                        System.out.println("Empty /to time: Please add in a time");
                        System.out.println(LONG_LINE);
                        break;
                    }

                    taskList.add(new Event(eventDescription, eventAtStr, eventToStr));

                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
                    System.out.println(LONG_LINE);

                    break;
                case "delete":
                    String deleteStr = STD_IN.nextLine().trim();
                    if (!deleteStr.matches("\\d+")) {
                        System.out.println("Invalid task number: Please add in a number from 1 to " + taskList.size());
                        break;
                    }

                    int deleteIndex = Integer.parseInt(deleteStr) - 1;

                    if (deleteIndex >= taskList.size() || deleteIndex < 0) {
                        System.out.println(
                                "Task number out of range. Please choose a number from 1 to " + taskList.size());
                        break;
                    }

                    Task deletedTask = taskList.remove(deleteIndex);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask.toString());
                    System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));

                    break;
                case "bye":
                    isChatting = false;
                    break;
                default:
                    System.out.println("invalid command");
            }

            boolean saveSuccess = writeData(taskList);
            if (saveSuccess) {
                System.out.println("Data saved successfully.");
            } else {
                System.out.println("Error saving data.");
                break;
            }
        }

        // Ending text
        String endingText = "Goodbye, I am Barney <RAWR>, see you next time!";
        System.out.println(endingText);
        System.out.println(LONG_LINE);

        STD_IN.close();
    }
}
