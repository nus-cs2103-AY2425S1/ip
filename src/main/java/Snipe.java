import java.util.ArrayList;
import java.util.Scanner;
public class Snipe {
    private static final String NAME = "Snipe";
    private static final String LOGO
            = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    private static ArrayList<Task> list = new ArrayList<Task>();
    public void initChat() {
        greetUser();
        handleUserInput();
    }

    private void greetUser() {
        String OPENING_MESSAGE = "Hello! I'm\n" + NAME +"\nWhat can I do for you?";
        printWithLines(OPENING_MESSAGE);
    }
    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                exitChat(scanner);
                break;
            }
            handleSpecialInputs(userInput, scanner);
        }
    }
    private boolean isValidInput(String userInput) {
        String[] validInputs = {
                "list",
                "mark",
                "unmark",
                "todo",
                "deadline",
                "event"
        };
        for (String validInput : validInputs) {
            if (userInput.startsWith(validInput)) {
                return true;
            }
        }
        return false;
    }
    private void handleSpecialInputs(String userInput, Scanner scanner) {
        try {
            if (isValidInput(userInput)) {
                if (userInput.equalsIgnoreCase("list")) {
                    returnList();
                } else if (userInput.startsWith("mark")) {
                    String[] split = userInput.split(" ");
                    int index = Integer.valueOf(split[1]) - 1;
                    if (list.get(index).getStatus()) {
                        printWithLines("This task is already marked done!");
                    } else {
                        list.get(index).changeStatus();
                        printWithLines("Nice! I've marked this task as done:\n" +
                                list.get(index).toString());
                    }
                } else if (userInput.startsWith("unmark")) {
                    String[] split = userInput.split(" ");
                    int index = Integer.valueOf(split[1]) - 1;
                    if (!list.get(index).getStatus()) {
                        printWithLines("This task is currently not done yet!");
                    } else {
                        list.get(index).changeStatus();
                        printWithLines("OK, I've marked this task as not done yet:\n" +
                                list.get(index).toString());
                    }
                } else {
                    Task newTask = null;
                    String[] split = userInput.split(" ", 2);
                    if (userInput.startsWith("todo")) {
                        newTask = addToDoTask(split);
                    } else if (userInput.startsWith("deadline")) {
                        String[] toSplit = split[1].split(" /by ", 2);
                        String description = toSplit[0];
                        String deadline = toSplit[1];
                        newTask = new Deadline(description, deadline);
                    } else if (userInput.startsWith("event")) {
                        String[] toSplit = split[1].split(" /from | /to ");
                        String description = toSplit[0];
                        String start = toSplit[1];
                        String end = toSplit[2];
                        newTask = new Event(description, start, end);
                    }
                    list.add(newTask);
                    int listSize = list.size();
                    String message = " Got it. I've added this task:\n  "
                            + newTask.toString()
                            + String.format("\n Now you have %d %s in the list.", listSize, listSize == 1 ? "task" : "tasks");
                    printWithLines(message);
                }
            } else {
                throw new SnipeException("That is not a valid input! Try again!");
            }
        } catch(SnipeException e) {
            printWithLines(e.getMessage());
        }
    }
    private Task addToDoTask(String[] split) throws SnipeException {
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new SnipeException("NOOOOO!! The description of a todo cannot be empty.");
        } else {
            return new ToDo(split[1]);
        }
    }
    private void returnList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            String item = String.format("%d. ", i + 1) + list.get(i).toString();
            System.out.println(item);
        }
        System.out.println(HORIZONTAL_LINE);
    }
    private void printWithLines(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
    private void exitChat(Scanner scanner) {
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        printWithLines(CLOSING_MESSAGE);
        scanner.close();
    }
    public static void main(String[] args) {
        Snipe snipe = new Snipe();
        snipe.initChat();
    }
}


