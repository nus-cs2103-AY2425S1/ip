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
    }

    private void handleMarkOfTask(String[] parsedInput) {
        // data validation
        if (parsedInput.length < 2) {
            System.out.println("Please provide a second argument when marking or unmarking a task");
            return;
        }
        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            System.out.println("There is no task of that number in the current list");
            return;
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

    public void start() {
        introduce();
        Scanner userScanner = new Scanner(System.in);
        String userCommand = userScanner.nextLine();

        while (!userCommand.equals("bye")) {

            String[] parsedInput = userCommand.split(" ");
            String route = parsedInput[0];

            switch (route) {
                case "list":
                    showList();
                    break;
                case "mark":
                case "unmark":
                    handleMarkOfTask(parsedInput);
                    break;
                default:
                    addTask(new Task(userCommand));
                    break;
            }

            userCommand = userScanner.nextLine();
        }
        conclude();
    }
    public static void main(String[] args) {
        new Bill().start();
    }
}
