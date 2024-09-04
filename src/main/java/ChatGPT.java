import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatGPT {
    private final static String NAME = "ChatGPT";
    private final static String LINE = "________________________________________________";
    private static ArrayList<Task> list = new ArrayList<Task>();
    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE;

        public static Commands getCommand(String s) throws NoSuchMethodException {
            try {
                return Commands.valueOf(s);
            } catch (IllegalArgumentException e) {
                throw new NoSuchMethodException("\t Oops!! I don't understand what that means :((");
            }
        }
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        list = readData();
        sendGreeting();
        do {
            try {
                switch(Commands.getCommand(userInput.next().toUpperCase())) {
                case BYE:
                    sendExit();
                    return;

                case LIST:
                    printList();
                    break;

                case TODO:
                    String input = userInput.nextLine();

                    Task newTask = new ToDos(input);
                    list.add(newTask);
                    sendAddTaskMsg(newTask);
                    break;

                case DEADLINE:
                    input = userInput.nextLine();

                    String task = input.split("/by")[0];
                    String deadline = input.split("/by")[1];
                    newTask = new Deadlines(task, deadline);

                    list.add(newTask);
                    sendAddTaskMsg(newTask);
                    break;

                case EVENT:
                    input = userInput.nextLine();
                    task = input.split("/from")[0];

                    String date = input.split("/from")[1];
                    String startDate = date.split("/to")[0];
                    String endDate = date.split("/to")[1];
                    newTask = new Events(task, startDate, endDate);

                    list.add(newTask);
                    sendAddTaskMsg(newTask);
                    break;

                case MARK:
                    int index = userInput.nextInt();
                    System.out.println("\t" + LINE);
                    System.out.println(list.get(index-1).setCompleted(true));
                    System.out.println("\t" + LINE);
                    break;

                case UNMARK:
                    index = userInput.nextInt();
                    System.out.println("\t" + LINE);
                    System.out.println(list.get(index-1).setCompleted(false));
                    System.out.println("\t" + LINE);
                    break;

                case DELETE:
                    index = userInput.nextInt();
                    Task deletedTask = list.get(index-1);
                    list.remove(index-1);
                    sendDeleteMsg(deletedTask);
                    break;
                }
            } catch (IllegalArgumentException | NoSuchMethodException e) {
                System.out.println("\t" + LINE);
                System.out.println(e.getMessage());
                System.out.println("\t" + LINE);
            } catch (Exception e) {
                System.out.println("\t" + LINE);
                System.out.println("\t There seems to have been a problem with your inputs Q.Q" +
                        "\n\t Please try again with a valid command and inputs");
                System.out.println("\t" + LINE);
            }
        } while (true);
    }

    private static void sendGreeting() {
        System.out.println("\t"+LINE);
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t"+LINE);
    }

    private static void sendExit() {
        System.out.println("\t"+LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t"+LINE);
    }

    private static void sendAddTaskMsg(Task task) {
        System.out.println("\t"+LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in your list.");
        System.out.println("\t"+LINE);
    }

    private static void sendDeleteMsg(Task task) {
        System.out.println("\t"+LINE);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  "+ task.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in your list.");
        System.out.println("\t"+LINE);
    }

    private static void printList() {
        System.out.println("\t"+LINE);
        if (list.size() == 0) {
            System.out.println("\tNothing has been added");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < list.size(); i++){
            System.out.println("\t" + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println("\t"+LINE);
    }

    private static ArrayList<Task> readData() {
        File currentDir = new File(".");
        File dataDir = new File (currentDir.getAbsolutePath() + "\\data");
        File data = new File (dataDir.getAbsolutePath() + "\\data.txt");
        ArrayList<Task> result = new ArrayList<>();

        if (!dataDir.exists()) {
            dataDir.mkdir();
            return result;
        }
        if (!data.exists()) {
            return result;
        }

        try {
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNext()) {
                switch(Commands.getCommand(fileReader.next().toUpperCase())) {
                case TODO:
                    String input = fileReader.nextLine();

                    Boolean isCompleted = input.split("\\|")[1].equals("1");
                    String task = input.split("\\|")[2];

                    Task newTask = new ToDos(task, isCompleted);
                    result.add(newTask);
                    break;

                case DEADLINE:
                    input = fileReader.nextLine();

                    isCompleted = input.split("\\|")[1].equals("1");
                    task = input.split("\\|")[2];
                    String deadline = input.split("\\|")[3];

                    newTask = new Deadlines(task, deadline, isCompleted);
                    result.add(newTask);
                    break;

                case EVENT:
                    input = fileReader.nextLine();
                    isCompleted = input.split("\\|")[1].equals("1");
                    task = input.split("\\|")[2];
                    String startDate = input.split("\\|")[3];
                    String endDate = input.split("\\|")[4];

                    newTask = new Events(task, startDate, endDate, isCompleted);
                    result.add(newTask);
                    break;
                }
            }
            return result;
        } catch (IOException | NoSuchMethodException e) {
            System.out.println("\t" + LINE);
            System.out.println("\t!! There was a problem reading the file !!");
            System.out.println("\tPlease fix the file and restart or start from scratch");
            System.out.println("\t" + LINE);
            return new ArrayList<>();
        }
    }
}
