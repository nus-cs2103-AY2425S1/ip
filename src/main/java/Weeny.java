import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Weeny {
    public static void main(String[] args) {
        boolean farewell = false;
        List<Task> data = new ArrayList<>();
        line();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        line();
        Scanner user_input = new Scanner(System.in);

        while (!farewell) {
            String input = user_input.nextLine();
            String command = firstword(input);

            if (input.equals("list")) {
                line();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < data.size(); i++) {
                    System.out.println((i + 1) + ". " + data.get(i).toString());
                }
                line();
            } else if (input.equals("bye")) {
                farewell = true;
            } else if (command.equals("mark")) {
                int val = endnum(input) - 1;
                if (val >= data.size() || val < 0) {
                    System.out.println("Task does not exist.");
                } else {
                    data.get(val).mark();
                    line();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(data.get(val).toString());
                    line();
                }
            } else if (command.equals("unmark")) {
                int val = endnum(input) - 1;
                if (val >= data.size() || val < 0) {
                    System.out.println("Task does not exist.");
                } else {
                    data.get(val).unmark();
                    line();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(data.get(val).toString());
                    line();
                }
            } else if (command.equals("todo")) {
                if (input.length() <= 5) {
                    System.out.println("The description of a todo cannot be empty.");
                } else {
                    line();
                    Task current = new Todo(input.substring(5).trim());
                    data.add(current);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                }
            } else if (command.equals("event")) {
                if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
                    System.out.println("Event must contain /from and /to.");
                } else {
                    String[] eventDetails = input_event(input);
                    Task current = new Events(input.substring(6, input.indexOf("/from")).trim(), eventDetails[0], eventDetails[1]);
                    data.add(current);
                    line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                }
            } else if (command.equals("deadline")) {
                if (input.length() <= 9 || !input.contains("/by")) {
                    System.out.println("Deadline must contain /by.");
                } else {
                    Task current = new Deadlines(input.substring(9, input.indexOf("/by")).trim(), input_deadline(input));
                    data.add(current);
                    line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                }
            } else if (command.equals("delete")) {
                int val = endnum(input) - 1;
                if (val >= data.size() || val < 0) {
                    System.out.println("Task does not exist.");
                } else {
                    Task temp = data.get(val);
                    data.remove(val);
                    line();
                    System.out.println("Spooof! The task magically disappeared");
                    System.out.println(temp.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                }
            } else {
                System.out.println("Unknown command.");
            }
        }
        line();
        System.out.println("Bye. Hope to see you soon!");
        line();
        user_input.close();
    }

    public static void line() {
        System.out.println("______________________________________________");
    }

    public static String firstword(String sentence) {
        int spaceind = sentence.indexOf(" ");
        return spaceind == -1 ? sentence : sentence.substring(0, spaceind);
    }

    public static int endnum(String sentence) {
        int spaceind = sentence.indexOf(" ");
        return Integer.parseInt(sentence.substring(spaceind + 1).trim());
    }

    public static String input_deadline(String sentence) {
        int index = sentence.indexOf("/by") + 4;
        return sentence.substring(index).trim();
    }

    public static String[] input_event(String sentence) {
        int index_1 = sentence.indexOf("/from") + 6;
        int index_2 = sentence.indexOf("/to");
        int index_3 = index_2 + 4;
        String[] pair = new String[2];
        pair[0] = sentence.substring(index_1, index_2 - 1).trim();
        pair[1] = sentence.substring(index_3).trim();
        return pair;
    }
}
