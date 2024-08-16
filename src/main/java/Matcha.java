import java.util.Scanner;
import java.util.ArrayList;
public class Matcha {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String divider = "____________________________________________________________";
    public static void main(String[] args) {
        Matcha.greet(); //greet users
        while (true) {
            String input = scanner.nextLine(); //read user input
            if (input.equalsIgnoreCase("bye")) { //exit system if user inputs "bye"
                Matcha.bye();
                break;
            }
            String[] inputWords = input.split(" ", 2); //split user input
            String keyword = inputWords[0]; //get command of user input
            System.out.println(divider);
            try {
                switch (keyword.toLowerCase()) {
                case "list":
                    Matcha.listTasks();
                    break;
                case "mark":
                    Matcha.markTask(input.split(" "));
                    break;
                case "unmark":
                    Matcha.unmarkTask(input.split(" "));
                    break;
                case "todo":
                    Matcha.createTodo(inputWords);
                    break;
                case "deadline":
                    Matcha.createDeadline(inputWords);
                    break;
                case "event":
                    Matcha.createEvent(inputWords);
                    break;
                case "delete":
                    Matcha.deleteTask(input.split(" "));
                    break;
                default:
                    throw new DukeException("Hmm, I'm sorry but I am unfamiliar with this command :(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                System.out.println(divider);
            }
        }
        scanner.close(); //once user has exited program, close scanner
    }

    public static void greet() {
        System.out.println(divider);
        System.out.println(" Hi there! I am Matcha, your personal chatbot.");
        System.out.println(" How can I help you today?");
        System.out.println(divider);
    }

    public static void bye() {
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(divider);
    }

    public static void createTodo(String[] inputWords) throws DukeException {
        if (inputWords.length < 2) {
            throw new DukeException("Please specify the Todo description!");
        }
        System.out.println("Alright, I have added this Todo:");
        Todo todo = new Todo(inputWords[1]);
        tasks.add(todo);
        Matcha.printTask(todo);
    }

    public static void createDeadline(String[] inputWords) throws DukeException {
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

        System.out.println("Alright, I have added this Deadline:");
        Deadline deadline = new Deadline(deadlineDesc, by);
        tasks.add(deadline);
        Matcha.printTask(deadline);
    }

    public static void createEvent(String[] inputWords) throws DukeException {
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

        System.out.println("Alright, I have added this Event:");
        Event event = new Event(eventDesc, from, to);
        tasks.add(event);
        Matcha.printTask(event);
    }

    public static void markTask(String[] inputWords) throws DukeException {
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
        System.out.println("I have successfully marked this task as done:");
        System.out.println(tasks.get(taskNum).toString());
    }

    public static void unmarkTask(String[] inputWords) throws DukeException {
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
        System.out.println("Alright, I have marked this task as not done yet:");
        System.out.println(tasks.get(taskNum).toString());
    }

    public static void deleteTask(String[] inputWords) throws DukeException {
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

        System.out.println("Alright, I have removed this task for you:");
        Task taskToRemove = tasks.get(taskNum);
        tasks.remove(taskNum);
        Matcha.printTask(taskToRemove);
    }

    public static void printTask(Task task) {
        System.out.println(task);
        System.out.println("You have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            String task = (i + 1) + ". " + tasks.get(i);
            System.out.println("\t" + task);
        }
    }
}