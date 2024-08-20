import java.util.ArrayList;
import java.util.Scanner;

public class Colress {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static String spacer = "____________________________________________________________________\n";
    private static String greetingMessage = "Hello! My name is Colress.\n"
            + "What brings you here?";
    private static String listEmptyMessage = "It appears you have not added anything to your list.";
    private static String farewellMessage = "Well then, I hope to see you around soon!";
    private static String addCommand = "add";
    private static String checkCommand = "check";
    private static String uncheckCommand = "uncheck";
    private static String deleteCommand = "delete";
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
            try {
                if (Colress.input.equals(addCommand)) {
                    Colress.addTask();
                } else if (Colress.input.equals(checkCommand)) {
                    Colress.editList("check");
                } else if (Colress.input.equals(uncheckCommand)) {
                    Colress.editList("uncheck");
                } else if (Colress.input.equals(deleteCommand)) {
                    Colress.editList("delete");
                } else if (Colress.input.equals(listCommand)) {
                    Colress.print(Colress.printList());
                } else {
                    throw new UnknownCommandException();
                }
            } catch (UnknownCommandException e) {
                print(String.valueOf(e));
            }
            Colress.getInput();
        }
    }

    public static void addTask() {

        Task currTask = null;
        String description;
        String date;
        String from;
        String to;

        while (currTask == null) {
            try {
                print("Enter the type of task you wish to add to your list");
                Colress.getInput();

                switch (Colress.input) {
                    case "todo":
                        Colress.print("Enter the description of the task");
                        description = Colress.scanner.nextLine();

                        currTask = new ToDo(description);
                        Colress.taskList.add(currTask);
                        Colress.print("Okay. I have added this task to your list:\n"
                                + Colress.taskList.size() + "." + currTask);
                        break;
                    case "deadline":
                        Colress.print("Enter the description of the task");
                        description = Colress.scanner.nextLine();

                        Colress.print("Enter the deadline");
                        date = Colress.scanner.nextLine();

                        currTask = new Deadline(description, date);
                        Colress.taskList.add(currTask);
                        Colress.print("Okay. I have added this task to your list:\n"
                                + Colress.taskList.size() + "." + currTask);
                        break;
                    case "event":
                        Colress.print("Enter the description of the event");
                        description = Colress.scanner.nextLine();

                        Colress.print("Enter the date of the event");
                        date = Colress.scanner.nextLine();

                        Colress.print("Enter the starting time of the event");
                        from = Colress.scanner.nextLine();

                        Colress.print("Enter the ending time of the event");
                        to = Colress.scanner.nextLine();

                        currTask = new Event(description, date, from, to);
                        Colress.taskList.add(currTask);
                        Colress.print("Okay. I have added this task to your list:\n"
                                + Colress.taskList.size() + "." + currTask);
                        break;
                    default:
                        throw new UnknownTaskTypeException();
                }
            } catch (UnknownTaskTypeException e) {
                print(String.valueOf(e));
            }
        }

    }

    public static void editList(String action) throws UnknownCommandException {
        if (Colress.taskList.isEmpty()) {
            Colress.print(Colress.listEmptyMessage);
        } else {
            int index = -1;
            Task currTask = null;
            while (currTask == null) {
                Colress.print("Enter the task number");
                try {
                    index = Integer.parseInt(Colress.scanner.nextLine());
                    currTask = Colress.taskList.get(index - 1);
                } catch (NumberFormatException e) {
                    print("You did not seem to have entered a number. Try Again.");
                } catch (IndexOutOfBoundsException e) {
                    print("You did not seem to have entered a valid number. Try Again.");
                }
            }
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
                case "delete":
                    taskList.remove(index - 1);
                    Colress.print("Got it. I have removed the task from your list\n" + printList());
                    break;
                default:
                    throw new UnknownCommandException();
            }
        }
    }
    public static String printList() {
        String result = "";
        for(int i = 0; i < Colress.taskList.size(); i++) {
            result += String.format("\n%d. " + Colress.taskList.get(i), i + 1);
        }
        if(result.isEmpty()) {
            return Colress.listEmptyMessage;
        }
        return "Here is your list:" + result;
    }
    public static void main(String[] args) {
        Colress.print(Colress.greetingMessage);
        Colress.makeList();
        Colress.print(Colress.farewellMessage);
    }
}
