import java.util.ArrayList;
import java.util.Scanner;

public class Colress {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String input = "";
    private static final String spacer = "____________________________________________________________________\n";
    private static final String greetingMessage = "Hello! My name is Colress.\n"
            + "What brings you here?";
    private static final String taskTypePrompt = "Enter the type of task you wish to add to your list.";
    private static final String taskDescriptionPrompt = "Enter the description of the task.";
    private static final String eventDescriptionPrompt = "Enter the description of the event.";
    private static final String deadlinePrompt = "Enter the deadline.";
    private static final String eventDatePrompt = "Enter the date of the event.";
    private static final String eventStartTimePrompt = "Enter the starting time of the event.";
    private static final String eventEndTimePrompt = "Enter the ending time of the event.";
    private static final String taskNumberPrompt = "Enter the task number.";
    private static final String addConfirmationMessage = "Okay. I have added this task to your list:";
    private static final String checkConfirmationMessage = "Sure. I have marked this task on your list as done:";
    private static final String uncheckConfirmationMessage = "Right. I have marked this task on your list as not done:";
    private static final String deleteConfirmationMessage = "Got it. I have removed the task from your list.";
    private static final String notANumberMessage = "You did not seem to have entered a number. Try Again.";
    private static final String notAValidNumberMessage = "You did not seem to have entered a valid number. Try Again.";
    private static final String listEmptyMessage = "Your list is empty.";
    private static final String farewellMessage = "Well then, I hope to see you around soon!";
    private static final String addCommand = "add";
    private static final String checkCommand = "check";
    private static final String uncheckCommand = "uncheck";
    private static final String deleteCommand = "delete";
    private static final String listCommand = "list";
    private static final String exitCommand = "bye";
    public static void print(String s) {
        System.out.println(Colress.spacer + s + "\n" + Colress.spacer);
    }

    public static void getInput() {
        Colress.input = Colress.scanner.nextLine();
    }

    public static void makeList() {
        Colress.getInput();
        while (!Colress.input.equals(Colress.exitCommand)) {
            try {
                switch (Colress.input) {
                    case Colress.addCommand:
                        Colress.addTask();
                        break;
                    case Colress.checkCommand, Colress.uncheckCommand, Colress.deleteCommand:
                        Colress.editList(Colress.input);
                        break;
                    case Colress.listCommand:
                        Colress.print(Colress.printList());
                        break;
                    default:
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
                print(Colress.taskTypePrompt);
                Colress.getInput();

                switch (Colress.input) {
                    case "todo":
                        Colress.print(Colress.taskDescriptionPrompt);
                        description = Colress.scanner.nextLine();

                        currTask = new ToDo(description);
                        Colress.taskList.add(currTask);
                        Colress.print(Colress.addConfirmationMessage
                                + "\n"
                                + Colress.taskList.size()
                                + "."
                                + currTask);
                        break;
                    case "deadline":
                        Colress.print(Colress.taskDescriptionPrompt);
                        description = Colress.scanner.nextLine();

                        Colress.print(Colress.deadlinePrompt);
                        date = Colress.scanner.nextLine();

                        currTask = new Deadline(description, date);
                        Colress.taskList.add(currTask);
                        Colress.print(Colress.addConfirmationMessage
                                + "\n"
                                + Colress.taskList.size()
                                + "."
                                + currTask);
                        break;
                    case "event":
                        Colress.print(Colress.eventDescriptionPrompt);
                        description = Colress.scanner.nextLine();

                        Colress.print(Colress.eventDatePrompt);
                        date = Colress.scanner.nextLine();

                        Colress.print(Colress.eventStartTimePrompt);
                        from = Colress.scanner.nextLine();

                        Colress.print(Colress.eventEndTimePrompt);
                        to = Colress.scanner.nextLine();

                        currTask = new Event(description, date, from, to);
                        Colress.taskList.add(currTask);
                        Colress.print(Colress.addConfirmationMessage
                                + "\n"
                                + Colress.taskList.size()
                                + "."
                                + currTask);
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
                Colress.print(Colress.taskNumberPrompt);
                try {
                    index = Integer.parseInt(Colress.scanner.nextLine());
                    currTask = Colress.taskList.get(index - 1);
                } catch (NumberFormatException e) {
                    print(Colress.notANumberMessage);
                } catch (IndexOutOfBoundsException e) {
                    print(Colress.notAValidNumberMessage);
                }
            }
            switch (action) {
                case "check":
                    currTask.check();
                    Colress.print(Colress.checkConfirmationMessage + "\n" + index + "." + currTask);
                    break;
                case "uncheck":
                    currTask.uncheck();
                    Colress.print(Colress.uncheckConfirmationMessage + "\n" + index + "." + currTask);
                    break;
                case "delete":
                    taskList.remove(index - 1);
                    Colress.print(Colress.deleteConfirmationMessage + "\n" + printList());
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
