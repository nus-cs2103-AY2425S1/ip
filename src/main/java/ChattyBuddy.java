import java.util.Scanner;
import java.util.ArrayList;
public class ChattyBuddy {
    public static void main(String[] args) {
        ArrayList<Task> inputList = new ArrayList<>();
        String breakLine = "——————————————————————————";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(breakLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(breakLine);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        String[] slicedStr = response.split(" ");
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println(breakLine);
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.printf("%d.[%s] %s%n", i + 1,inputList.get(i).getStatusIcon(), inputList.get(i).description);
                }
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");
            } else if (slicedStr.length == 2 && slicedStr[0].equals("mark")) {
                if (Integer.parseInt(slicedStr[1]) <= inputList.size() && Integer.parseInt(slicedStr[1]) > 0) {
                    Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                    target.setMarkStatus(true);
                    System.out.println(breakLine);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("[%s] %s%n", target.getStatusIcon(), target.description);
                    System.out.println(breakLine);
                } else {
                    System.out.println(breakLine);
                    System.out.println("Oh no! No such task is found!");
                    System.out.println(breakLine);
                }
                response = userInput.nextLine();
                slicedStr = response.split(" ");
            } else if (slicedStr.length == 2 && slicedStr[0].equals("unmark")) {
                if (Integer.parseInt(slicedStr[1]) <= inputList.size() && Integer.parseInt(slicedStr[1]) > 0) {
                    Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                    target.setMarkStatus(false);
                    System.out.println(breakLine);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf("[%s] %s%n", target.getStatusIcon(), target.description);
                    System.out.println(breakLine);
                } else {
                    System.out.println(breakLine);
                    System.out.println("Oh no! No such task is found!");
                    System.out.println(breakLine);
                }
                response = userInput.nextLine();
                slicedStr = response.split(" ");
            } else {
                inputList.add(inputList.size(), new Task(response));
                System.out.println(breakLine);
                System.out.printf("added: %s%n", response);
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");
            }
        }
        System.out.println(breakLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(breakLine);
    }
}
