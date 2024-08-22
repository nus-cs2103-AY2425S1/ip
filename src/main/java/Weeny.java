import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            try {
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
                        throw new IndexOutOfBoundsException("Just a reminder. You can't mark tasks that don't exist!");
                    }
                    data.get(val).mark();
                    line();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(data.get(val).toString());
                    line();
                } else if (command.equals("unmark")) {
                    int val = endnum(input) - 1;
                    if (val >= data.size() || val < 0) {
                        throw new IndexOutOfBoundsException("Just a reminder. You can't mark tasks that don't exist!");
                    }
                    data.get(val).unmark();
                    line();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(data.get(val).toString());
                    line();
                } else if (command.equals("todo")) {
                    if (input.length() <= 5) {
                        throw new IllegalArgumentException("Are you going to add nothing as your To-Do? >:(");
                    }
                    line();
                    Task current = new Todo(input.substring(5).trim());
                    data.add(current);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list");
                    line();
                } else if (command.equals("event")) {
                    if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
                        throw new IllegalArgumentException("What kind of event has no name or time? >:(");
                    }
                    Task current = new Events(input.substring(6, input.indexOf("/from") - 1).trim(),
                            input_event(input)[0],
                            input_event(input)[1]);
                    data.add(current);
                    line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                } else if (command.equals("deadline")) {
                    if (input.length() <= 9 || !input.contains("/by")) {
                        throw new IllegalArgumentException("What is a deadline without a date or a Task? >:(");
                    }
                    Task current = new Deadlines(input.substring(9, input.indexOf("/by") - 1).trim(),
                            input_deadline(input));
                    data.add(current);
                    line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(current.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                } else if (command.equals("delete")) {
                    int val = endnum(input) - 1;
                    if (val >= data.size() || val < 0) {
                        throw new IndexOutOfBoundsException("Just a reminder. You can't delete tasks that don't exist!");
                    }
                    Task temp = data.get(val);
                    data.remove(val);
                    line();
                    System.out.println("Spooof! The task magically disappeared");
                    System.out.println(temp.toString());
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                    line();
                } else {
                    throw new UnsupportedOperationException("Oopsies we don't understand that command");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                line();
                System.out.println("Error: " + e.getMessage());
                line();
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
