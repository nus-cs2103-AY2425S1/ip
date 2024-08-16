import java.util.Scanner;
import java.util.ArrayList;


public class Matcha {
    //scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {

        Matcha.greet();

        while (true) {

            //read user input
            String input = scanner.nextLine();

            //if user exits program
            if (input.equalsIgnoreCase("bye")) {
                Matcha.bye();
                break;
            }

            //split user input into keyword and action description
            String[] inputWords = input.split(" ", 2);

            //get first word of user input
            String keyword = inputWords[0];

            try {
                switch (keyword.toLowerCase()) {
                    case "list":
                        Matcha.list();
                        break;

                    case "mark":
                        Matcha.mark(input.split(" "));
                        break;

                    case "unmark":
                        Matcha.unmark(input.split(" "));
                        break;

                    case "todo":
                        Matcha.todo(inputWords);
                        break;

                    case "deadline":
                        Matcha.deadline(inputWords);
                        break;

                    case "event":
                        Matcha.event(inputWords);
                        break;

                    case "delete":
                        Matcha.delete(input.split(" "));
                        break;

                    default:
                        throw new DukeException("Hmm, I'm sorry but " +
                                "I am unfamiliar with this command.\nPlease try another command instead :(");
                }
            } catch (DukeException e) {
                Matcha.printError(e);
            }

        }
        //once user has exited program, close scanner
        scanner.close();
    }

    public static void greet() {
        printLine();
        System.out.println(" Hi there! I am Matcha, your personal chatbot.");
        System.out.println(" How can I help you today?");
        printLine();
    }

    public static void todo(String[] inputWords) throws DukeException {
        if (inputWords.length < 2) {
            throw new DukeException("Please specify the Todo description!");
        }
        printLine();
        System.out.println("Alright, I have added this Todo:");
        Todo todo = new Todo(inputWords[1]);
        tasks.add(todo);
        System.out.println(todo);
        Matcha.countTasks(tasks.size());
        printLine();
    }

    public static void deadline(String[] inputWords) throws DukeException {
        if (inputWords.length < 2) {
            throw new DukeException("Please include the Deadline details!");
        }

        if (!inputWords[1].contains(" /by ")) {
            throw new DukeException("Invalid format to add Deadline.\nPlease use '/by' to specify the " +
                    "time of the Deadline.");
        }

        String[] deadlineInfo = inputWords[1].split(" /by ", 2);
        String deadlineDesc = deadlineInfo[0].strip();
        String by = deadlineInfo[1].strip();

        printLine();
        System.out.println("Alright, I have added this Deadline:");
        Deadline deadline = new Deadline(deadlineDesc, by);
        tasks.add(deadline);
        System.out.println(deadline);
        Matcha.countTasks(tasks.size());
        printLine();
    }

    public static void event(String[] inputWords) throws DukeException {
        if (inputWords.length < 2) {
            throw new DukeException("Please include the Event details!");
        }

        if (!inputWords[1].contains(" /from ") || !inputWords[1].contains(" /to ")) {
            throw new DukeException("Invalid format to add Event.\nPlease use '/from' and '/to' to specify the " +
                    "Event duration.");
        }

        String eventDesc = inputWords[1].split(" /from")[0];
        String from = inputWords[1].split(" /from ")[1].split(" /to ")[0];
        String to = inputWords[1].split(" /to ")[1];
        printLine();
        System.out.println("Alright, I have added this Event:");
        Event event = new Event(eventDesc, from, to);
        tasks.add(event);
        System.out.println(event);
        Matcha.countTasks(tasks.size());
        printLine();
    }

    public static void mark(String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new DukeException("Please enter the task number of the task you want to\nmark as done.");
        }

        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number of the task you want to\nmark as done.");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("This task does not exist!");
        }

        tasks.get(taskNum).markDone();

        printLine();
        System.out.println("I have successfully marked this task as done:");
        System.out.println(tasks.get(taskNum).toString());
        printLine();

    }

    public static void unmark(String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new DukeException("Please enter the task number of the task you want to\nmark as not done.");
        }

        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number of the task you want to\nmark as not done.");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("This task does not exist!");
        }

        tasks.get(taskNum).markNotDone();

        printLine();
        System.out.println("Alright, I have marked this task as not done yet:");
        System.out.println(tasks.get(taskNum).toString());
        printLine();
    }

    public static void delete(String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new DukeException("Please enter the task number of the task you want to delete.");
        }
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number of the task you want to delete.");
        }

        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("This task does not exist!");
        }

        printLine();
        System.out.println("Alright, I have removed this task for you:");
        System.out.println(tasks.get(taskNum).toString());
        tasks.remove(taskNum);
        Matcha.countTasks(tasks.size());
        printLine();

    }

    public static void list() {
        if (tasks.isEmpty()) {
            printLine();
            System.out.println("You have no tasks currently.");
            printLine();
        } else {
            printLine();
            System.out.println("Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                String task = (i + 1) + ". " + tasks.get(i);
                System.out.println("\t" + task);
            }
            printLine();
        }
    }

    public static void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }

    public static void countTasks(int numOfTasks) {
        String task = numOfTasks == 1 ? "task" : "tasks";
        System.out.println("You have " + numOfTasks + " " + task + " in the list.");
    }

    public static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public static void printError(Exception e) {
        printLine();
        System.out.println(e);
        printLine();
    }
}


