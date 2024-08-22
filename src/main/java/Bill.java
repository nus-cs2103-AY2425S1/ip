import java.util.Scanner;
import java.util.ArrayList;

public class Bill {
    private ArrayList<Task> userList;

    public Bill() {
        userList = new ArrayList<>();
    }

    private void introduce() {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");
    }

    private void conclude() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void showList() {
        if (userList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + "." + userList.get(i));
            }
        }
    }

    private void addTask(Task newTask){
        userList.add(newTask);
        System.out.println("added: " + newTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
    }

    private void handleMarkOfTask(String[] parsedInput) throws BillException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when marking or unmarking a task");
        }
        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            throw new BillException("There is no task of that number in the current list");
        }

        int targetTaskNumber = Integer.parseInt(parsedInput[1]) - 1;
        Task targetTask = userList.get(targetTaskNumber);

        if (parsedInput[0].equals("mark")) {
            targetTask.mark();
        } else {
            // unmarked
            targetTask.unmark();
        }
        System.out.println(targetTask);
    }

    private void handleToDo(String userCommand) throws BillException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument for the todo, such as a description");
        }
        String trimmedUserCommand = userCommand.replaceFirst("todo", "").trim();
        addTask(new ToDo(trimmedUserCommand));
    }

    private void handleDeadline(String userCommand) throws BillException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 4) {
            throw new BillException("4 Arguments needed minimum for deadline command, following the format: deadline <task> /by <date>, where <> suggest user input");
        }
        if (!userCommand.contains(" /by ")) {
            throw new BillException("Missing /by with spaces around it, ensure to follow the format: deadline <task> /by <date> where <> suggest user input");
        }
        // data parsing
        // remove deadline, trim white spaces and delimit by /by
        try {
            String[] trimmedUserCommand = userCommand.replaceFirst("deadline", "").trim().split(" /by ");
            String deadlineDescription = trimmedUserCommand[0];
            String deadlineBy = trimmedUserCommand[1];

            addTask(new Deadline(deadlineDescription, deadlineBy));
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format: deadline <task> /by <date> where <> suggest user input.");
        }
    }

    private void handleEvent(String userCommand) throws BillException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 6) {
            throw new BillException("6 Arguments needed minimum for deadline command, following the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /from ")) {
            throw new BillException("Missing /from with spaces around it, ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /to ")) {
            throw new BillException("Missing /to with spaces around it, ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        // data parsing
        // remove event, trim white spaces and delimit by /from and /to
        try {
            String[] trimmedUserCommand = userCommand.replaceFirst("event", "").trim().split(" /from ");
            String eventDescription = trimmedUserCommand[0];
            String[] furtherTrimmedUserCommand = trimmedUserCommand[1].trim().split(" /to ");
            String eventFrom = furtherTrimmedUserCommand[0];
            String eventTo = furtherTrimmedUserCommand[1];

            addTask(new Event(eventDescription, eventFrom, eventTo));
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
    }

    public void start() {
        introduce();
        Scanner userScanner = new Scanner(System.in);
        // remove leading and trailing whitespaces
        String userCommand = userScanner.nextLine().trim();

        while (!userCommand.equals("bye")) {
            String[] parsedInput = userCommand.split(" ");
            String route = parsedInput[0];

            try {
                switch (route) {
                    case "list":
                        showList();
                        break;
                    case "mark":
                    case "unmark":
                        handleMarkOfTask(parsedInput);
                        break;
                    case "todo":
                        handleToDo(userCommand);
                        break;
                    case "deadline":
                        handleDeadline(userCommand);
                        break;
                    case "event":
                        handleEvent(userCommand);
                        break;
                    default:
                        System.out.println("Not a recognised command, please try again");
                        break;
                }
            } catch (BillException ex) {
                System.out.println(ex.getMessage());
            }

            userCommand = userScanner.nextLine();
        }
        conclude();
    }

    public static void main(String[] args) {
        new Bill().start();
    }
}
