
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoobyDoo {
    public static final String name = "Scooby-Doo";
    public static TaskList taskList = new TaskList();

    public static final String FILE_NAME ="data/tasks.txt";

    public static void main(String[] args) {
        //greeting
        printFormattedResponse(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        try {
            ArrayList<String> stringList = FileReadWrite.readFile(FILE_NAME);
            taskList = new TaskList(FileReadWrite.getTaskListFromFile(stringList));
        } catch (FileNotFoundException e) {
            FileReadWrite.createFile(FILE_NAME);
        }
        //loop
        String input;
        Scanner scanIn = new Scanner(System.in);
        while (true) {
            input = scanIn.nextLine();
            if (input.equals("bye")) {
                printFormattedResponse("Bye. Hope to see you again soon!");
                FileReadWrite.writeFile(ScoobyDoo.FILE_NAME, taskList.toFileFormatString());
                break;
            }

            if (input.equals("list")) {
                printFormattedResponse(taskList.printList());
                continue;
            }

            if (Todo.matchTodo(input)) {
                try {
                    printFormattedResponse(taskList.addTask(new Todo(input)));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }
                continue;
            }

            if (Deadline.matchDeadline(input)) {
                try {
                    printFormattedResponse(taskList.addTask(new Deadline(input)));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }

                continue;
            }

            if (Event.matchEvent(input)) {
                try {
                    printFormattedResponse(taskList.addTask(new Event(input)));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("mark")) {
                try {
                    int num = Task.matchesMark(input);
                    printFormattedResponse(taskList.markTask(num));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("unmark")) {
                try {
                    int num = Task.matchesUnmark(input);
                    printFormattedResponse(taskList.unmarkTask(num));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }
                continue;
            }

            if (input.startsWith("delete")) {
                try {
                    int i = TaskList.getDeleteNumber(input);
                    printFormattedResponse(taskList.deleteTask(i));
                } catch (InputFormatException e) {
                    printFormattedResponse(e.getMessage());
                }
                continue;
            }

            else {
                printFormattedResponse("The available inputs are\n deadline\n event\n todo\n mark\n unmark\n list\n delete\n bye");
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

