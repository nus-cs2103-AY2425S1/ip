
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
                String listString = "";
                for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                    listString = listString.concat(taskList.getTask(i).getTaskString(i + 1));
                }
                printFormattedResponse(listString);
                continue;
            }

            if (matchesMark(input) != 0) {
                int num = matchesMark(input);
                taskList.getTask(num - 1).markAsDone();
                String response = String.format("Nice! I've marked this task as done:\n [x] %s",
                        taskList.getTask(num - 1).getDescription());
                printFormattedResponse(response);
                continue;
            }

            if (matchesUnmark(input) != 0) {
                int num = matchesUnmark(input);
                taskList.getTask(num - 1).markAsUndone();
                String response = String.format("OK, I've marked this task as not done yet:\n [ ] %s",
                        taskList.getTask(num - 1).getDescription());
                printFormattedResponse(response);
                continue;
            }
            if (taskList.addTask(input)) {
                printFormattedResponse("Added: " + input);
            } else printFormattedResponse("Too many tasks !!!");
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

    //if returns 0 means no matches
    private static int matchesMark(String input) {
        if (input.startsWith("mark")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 2) {
                try {
                    return Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e){
                    return 0;
                }
            }
        }
      return 0;
    }

    private static int matchesUnmark(String input) {
        if (input.startsWith("unmark")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length == 2) {
                try {
                    return Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e){
                    return 0;
                }
            }
        }
        return 0;
    }
}

