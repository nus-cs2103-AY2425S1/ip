import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;

public class Snowy {


    private static final String LINE = "-----------------------------------\n";
    private static final String GREETING = LINE
            + "Hello! My name is Snowy\n"
            + "What can I do for you?\n"
            + LINE;

    private static final String ENDING = "Bye! Hope to see you again soon!\n"
            + LINE;

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static File file;

    private static boolean isRunning = true;

    private static void initializeTask(String description) {
        String status;
        String name;

        String[] dataArray = description.split("[|]");
        String type = dataArray[0];
        switch (type) {
            case "T":
                status = dataArray[1];
                name = dataArray[2];
                Task newToDo = new ToDo(name);
                tasks.add(newToDo);
                if (status.equals("1")) {
                    newToDo.markComplete();
                }
                break;

            case "D":
                status = dataArray[1];
                name = dataArray[2];
                String dueDate = dataArray[3];
                Task newDateline = new Deadline(name, dueDate);
                tasks.add(newDateline);
                if (status.equals("1")) {
                    newDateline.markComplete();
                }
                break;

            case "E":
                status = dataArray[1];
                name = dataArray[2];
                String fromDate = dataArray[3];
                String toDate = dataArray[4];
                Task newEvent = new Event(name, fromDate, toDate);
                tasks.add(newEvent);
                if (status.equals("1")) {
                    newEvent.markComplete();
                }
                break;

            default:
                System.out.println("Error: task type not found");
        }
    }


    private static void initializeFile() {
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                initializeTask(nextLine);
            }

        } catch (IOException e) {
        }

    }

    private static void addToDo(String description) {
        if (description.isEmpty()) {
            System.out.println("Error: Description of todos cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        System.out.println("New todo task added:\n" + newTask);
    }

    private static void addDeadline(String description) {
        if (description.isEmpty()) {
            System.out.println("Error: Description of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }

        int byIndex = description.indexOf("/by ");

        if (byIndex == -1) {
            System.out.println("Error: Please include name, /by and deadline separated by space.");
            return;
        }
        String deadlineName = description.substring(0, byIndex).trim();
        String date = description.substring(byIndex + 4);

        if (deadlineName.isEmpty()) {
            System.out.println("Error: name of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }

        if (date.isEmpty()) {
            System.out.println("Error: dueDate of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        Task newTask = new Deadline(deadlineName, date);
        tasks.add(newTask);
        System.out.println("New Deadline task added:\n" + newTask);
    }

    private static void addEvent(String description) {
        if (description.isEmpty()) {
            System.out.println("Error: Description of events cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        int fromIndex = description.indexOf("/from ");
        int toIndex = description.indexOf("/to ");

        if (toIndex == -1 || fromIndex == -1) {
            System.out.println("Error: incorrect styling.\n"
                    + "Please include name, /from, fromDate, /to, toDate separated by space");
            return;
        }

        String eventName = description.substring(0, fromIndex);
        String fromDate = description.substring(fromIndex + 6, toIndex).trim();
        String toDate = description.substring(toIndex + 4);
        if (eventName.isEmpty()) {
            System.out.println("Error: eventName of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        if (fromDate.isEmpty()) {
            System.out.println("Error: fromDate of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        if (toDate.isEmpty()) {
            System.out.println("Error: toDate of deadlines cannot be empty.\n"
                    + "Please try again.");
            return;
        }
        Task newTask = new Event(eventName, fromDate, toDate);
        tasks.add(newTask);
        System.out.println("New Event task added:\n " + newTask);
    }

    private static void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toFileStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
        }

    }

    public static void main(String[] args) {
        Scanner fileScanner;
        Scanner scanner = new Scanner(System.in);

        file = new File("data/snowy.txt");
        initializeFile();


        System.out.print(GREETING);
        while (isRunning) {
            String lastInput = scanner.nextLine();

            int spaceIndex = lastInput.indexOf(" ");

            String command = (spaceIndex == -1 ? lastInput: lastInput.substring(0, spaceIndex)).toLowerCase();

            String description = spaceIndex == -1 ? "": lastInput.substring(spaceIndex + 1);

            Task newTask;

            switch (command) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(description);
                        tasks.get(index - 1).markComplete();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + tasks.get(index - 1).toString());
                    }catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(description);
                        tasks.get(index - 1).markIncomplete();
                        System.out.println("Ok, I've marked this task as not done yet:\n"
                                + tasks.get(index - 1).toString());
                    }catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                case "todo":
                    addToDo(description);
                    break;

                case "deadline":
                    addDeadline(description);
                    break;

                case "event":
                    addEvent(description);
                    break;

                case "delete":
                    try {
                        int index = Integer.parseInt(description);
                        Task removed = tasks.remove(index - 1);
                        System.out.println("Ok, I've removed this task:\n"
                                + removed.toString());
                    }catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid index provided. Please try again");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                default:
                    System.out.println("Command not recognized. Please try again");
                    break;
            }

            System.out.print(LINE);

        }

        updateFile();
        System.out.print(ENDING);
    }
}
