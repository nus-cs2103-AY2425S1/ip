import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Zenith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println(Message.getGreeting());
        User user = new User();

        while (true) {

            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            switch (inputSplit[0]) {
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    list = user.getList();
                    Task markTask = list.get(parseInt(inputSplit[1]) - 1);
                    markTask.setStatus(true);
                    System.out.println(markTask.toString() + "\n");
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    list = user.getList();
                    Task unmarkTask = list.get(parseInt(inputSplit[1]) - 1);
                    unmarkTask.setStatus(false);
                    System.out.println(unmarkTask.toString() + "\n");
                    break;
                case "list":
                    list = user.getList();
                    int listCount = 1;
                    for (Task item: list) {
                        System.out.println(listCount + ". " + item.toString());
                        listCount++;
                    }
                    System.out.println();
                    break;
                case "bye":
                    System.out.println(Message.getExit());
                    break;
                default:
                    System.out.println("added: " + input + "\n");
                    user.addList(input);
                    break;
            }

            if (input.equals("bye")) {
                break;
            }

        }
    }
}
