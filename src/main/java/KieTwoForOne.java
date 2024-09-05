import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException;

public class KieTwoForOne {

    private static ArrayList<Task> tasks = new ArrayList<>(100);
    private static String separationLine = "_________________________________________";
    private static String chatBotName = "KieTwoForOne";
    private static String filePath = "data/tasks.txt";

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
    public static void printTasks() {
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

    public static void saveToFile() {
        try {
            ObjectOutputStream fileSaver = new ObjectOutputStream(new FileOutputStream(filePath));
            for (int i = 0; i < tasks.size(); i++) {
                fileSaver.writeObject(tasks.get(i));
            }
            fileSaver.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public static void main(String[] args) {
        try {
            ObjectInputStream fileLoader = new ObjectInputStream(new FileInputStream(filePath));
            while (true) {
                try {
                    Task newTask = (Task) fileLoader.readObject();
                    tasks.add(newTask);
                } catch (EOFException e) {
                    break;
                }
            }
            fileLoader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        } catch (ClassNotFoundException e) {
            System.out.println("Not a Task!");
        }
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
                    KieTwoForOne.printTasks();
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
        KieTwoForOne.saveToFile();
        scanner.close();
    }
}
