import java.util.ArrayList;
import java.util.Scanner;

public class Colress {
    private static ArrayList<Task> toDoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static String spacer = "______________________________________\n";
    private static String greetingMessage = "Hello! My name is Colress.\n"
            + "What can I do for you?";
    private static String listEmptyMessage = "I'm sorry, but it appears you have not added anything to your list.";
    private static String invalidCommandMessage = "I'm sorry, but I do not recognise that command.";
    private static String farewellMessage = "Well then, I hope to see you around soon!";
    private static String addCommand = "add";
    private static String checkCommand = "check";
    private static String uncheckCommand = "uncheck";
    private static String listCommand = "list";
    private static String exitCommand = "bye";
    public static void print(String s) {
        System.out.println(spacer + s + "\n" + spacer);
    }

    public static void getInput() {
        Colress.input = Colress.scanner.nextLine();
    }

    public static void makeList() {
        Colress.getInput();
        while (!Colress.input.equals(exitCommand)) {
            if (Colress.input.equals(addCommand)) {
                Colress.addTask();
            } else if (Colress.input.equals(checkCommand)) {
                Colress.editTask("check");
            } else if (Colress.input.equals(uncheckCommand)) {
                Colress.editTask("uncheck");
            } else if (Colress.input.equals(listCommand)) {
                Colress.print("Here is your list:" + Colress.printList());
            } else {
                Colress.print(invalidCommandMessage);
            }
            Colress.getInput();
        }
    }

    public static void addTask() {
        Colress.print("Please enter the task description");
        Colress.getInput();
        Task currTask = new Task(Colress.input);
        toDoList.add(currTask);
        Colress.print("Okay. I have added this task to your list:\n" + toDoList.size() + "." + currTask);
    }

    public static void editTask(String action) {
        if (toDoList.isEmpty()) {
            Colress.print(listEmptyMessage);
        } else {
            Colress.print("Please enter the task number");
            int index = Integer.parseInt(scanner.nextLine());
            Task currTask = toDoList.get(index - 1);
            switch (action) {
                case "check":
                    currTask.check();
                    Colress.print("Sure. I have marked this task on your list as done:\n"
                            + index + "." + currTask);
                    break;
                case "uncheck":
                    currTask.uncheck();
                    Colress.print("Right. I have marked this task on your list as not done:\n"
                            + index + "." + currTask);
                    break;
                default:
                    Colress.print(invalidCommandMessage);
                    break;
            }
        }
    }
    public static String printList() {
        String result = "";
        for(int i = 0; i < toDoList.size(); i++) {
            result += String.format("\n%d. " + toDoList.get(i), i + 1);
        }
        if(result.isEmpty()) {
            return listEmptyMessage;
        }
        return result;
    }
    public static void main(String[] args) {
        print(greetingMessage);
        Colress.makeList();
        print(farewellMessage);
    }
}
