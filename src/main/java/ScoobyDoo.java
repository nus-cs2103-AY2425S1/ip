
import java.util.Scanner;

public class ScoobyDoo {
    public static final String name = "Scooby-Doo";
    public static final TaskList taskList = new TaskList(100);

    public static void main(String[] args) {
        //greeting
        printFormattedResponse(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        //loop
        String input;
        Scanner scanIn = new Scanner(System.in);
        while (true) {
            input = scanIn.nextLine();
            if (input.equals("bye")) {
                printFormattedResponse("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                printFormattedResponse(taskList.printList());
                continue;
            }

            if (Todo.matchTodo(input)) {
                printFormattedResponse(taskList.addTask(new Todo(input)));
                continue;
            }

            if (Deadline.matchDeadline(input)) {
                printFormattedResponse(taskList.addTask(new Deadline(input)));
                continue;
            }

            if (Event.matchEvent(input)) {
                printFormattedResponse(taskList.addTask(new Event(input)));
                continue;
            }

            if (Task.matchesMark(input) != 0) {
                int num = Task.matchesMark(input);
                printFormattedResponse(taskList.getTask(num - 1).markAsDone());
                continue;
            }

            if (Task.matchesUnmark(input) != 0) {
                int num = Task.matchesUnmark(input);
                printFormattedResponse(taskList.getTask(num - 1).markAsUndone());
            }
        }
        scanIn.close();
    }

    //will auto break
    public static void printFormattedResponse(String response) {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
        System.out.println(response);
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println("\n");

    }





}

