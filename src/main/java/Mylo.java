import level2.TaskList;
import level2.TaskModel;

import java.util.Scanner;

public class Mylo {
    private static void separator() {
        System.out.println("--------------------------------");
    }
    public static void main(String[] args) {
        TaskList list = new TaskList();

        String name = "Mylo";
        String greet = "Hello! Thanks for using " + name + ".";
        String opening_query = "What can I help you?";
        String goodbye = "Goodbye. Have a nice day ahead!";

        System.out.println(greet);
        System.out.println(opening_query);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            String[] keys = input.split(" ");
            switch(keys[0]) {
                case "list":
                    separator();
                    System.out.println(list);
                    separator();
                    break;
                case "mark":
                    separator();
                    list.markTaskAsDone(Integer.parseInt(keys[1]));
                    separator();
                    break;
                case "unmark":
                    separator();
                    list.markTaskAsUndone(Integer.parseInt(keys[1]));
                    separator();
                    break;
                case "who are you":
                    separator();
                    System.out.println("I'm " + name);
                    separator();
                    break;
                default:
                    separator();
                    list.addTask(input);
                    separator();
            }
            input = scanner.nextLine();
        }

        separator();
        System.out.println(goodbye);
        separator();
        scanner.close();
    }
}
