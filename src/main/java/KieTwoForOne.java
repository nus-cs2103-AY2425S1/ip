import java.util.ArrayList;
import java.util.Scanner;

public class KieTwoForOne {

    private static ArrayList<Task> tasks = new ArrayList<>(100);
    static String separationLine = "_________________________________________";
    static String chatBotName = "KieTwoForOne";

    public enum Instructions {
        LIST, MARK, UNMARK, BYE, TODO, EVENT, DEADLINE, DELETE
    }

    public static void addTasks(Task newTask) {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        System.out.println(separationLine);
    }

    public static void deleteTask(int position) {
        Task removedTask = tasks.get(position - 1);
        System.out.println("Noted. I've removed the task");
        System.out.println("    " + removedTask);
        tasks.remove(position - 1);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        System.out.println(separationLine);
    }
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        System.out.println(separationLine);
    }

    public static void markTask(int position) {
        Task markedTask = tasks.get(position - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + markedTask.markTask());
        System.out.println(separationLine);
    }

    public static void unmarkTask(int position) {
        Task unmarkedTask = tasks.get(position - 1);
        System.out.println("Ok. I've marked this task as not done yet:");
        System.out.println("    " + unmarkedTask.unmarkTask());
        System.out.println(separationLine);
    }

    public static boolean isCompleteInput(String[] input) throws KieTwoForOneException {
        if (input.length <= 1 && !input[0].equalsIgnoreCase("list") && !input[0]. equalsIgnoreCase("bye")) {
            throw new KieTwoForOneException();
        }
        return true;
    }

    public static boolean isCompleteEventInput(String[] input) throws KieTwoForOneException {
        if (input.length != 3) {
            throw new KieTwoForOneException();
        }
        return true;
    }

    public static boolean isCompleteDeadlineInput(String[] input) throws KieTwoForOneException {
        if (input.length != 2) {
            throw new KieTwoForOneException();
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(separationLine);
        System.out.println("Hello! I'm " + chatBotName + ".");
        System.out.println("What can I do for you?");
        System.out.println(separationLine);

        while(true) {
            String input = scanner.nextLine();
            String[] instruction = input.split(" ", 2);
            String[] taskDetails = new String[0];

            try {
                Instructions.valueOf(instruction[0].toUpperCase());
                isCompleteInput(instruction);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
                System.out.println(separationLine);
                continue;
            } catch (KieTwoForOneException e) {
                System.out.println("Your instruction is incomplete!");
                System.out.println(separationLine);
                continue;
            }

            if (instruction.length > 1) {
                taskDetails = instruction[1].split(" /", 0);
            }

            switch (Instructions.valueOf(instruction[0].toUpperCase())) {
                case LIST:
                    KieTwoForOne.listTasks();
                    break;
                case BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(separationLine);
                    break;
                case MARK:
                    try {
                        KieTwoForOne.markTask(Integer.valueOf(instruction[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist!");
                        System.out.println(separationLine);
                        break;
                    }
                    break;
                case UNMARK:
                    try {
                        KieTwoForOne.unmarkTask(Integer.valueOf(instruction[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist!");
                        System.out.println(separationLine);
                        break;
                    }
                    break;
                case TODO:
                    KieTwoForOne.addTasks(new Todo(instruction[1]));
                    break;
                case EVENT:
                    try  {
                        isCompleteEventInput(taskDetails);
                    } catch (KieTwoForOneException e){
                        System.out.println("Please input a start and end time!");
                        System.out.println(separationLine);
                        break;
                    }
                    KieTwoForOne.addTasks(new Event(taskDetails[0], taskDetails[1], taskDetails[2]));
                    break;
                case DEADLINE:
                    try {
                        isCompleteDeadlineInput(taskDetails);
                    } catch (KieTwoForOneException e) {
                        System.out.println("Please input a deadline!");
                        System.out.println(separationLine);
                        break;
                    }
                    KieTwoForOne.addTasks(new Deadline(taskDetails[0], taskDetails[1]));
                    break;
                case DELETE:
                    try {
                        deleteTask(Integer.valueOf(instruction[1]));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist!");
                        System.out.println(separationLine);
                        break;
                    }
                    break;
                default:
                    break;
            }
            if (instruction[0].equalsIgnoreCase("bye")) {
                break;
            }
        }

        scanner.close();
    }
}
