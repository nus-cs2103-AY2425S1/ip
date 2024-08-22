import java.util.ArrayList;
import java.util.Scanner;

public class Genji {
    private static String NAME = "Genji";
    private static String LINE = "________________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> list = new ArrayList();

    public static void addList(Task t) {
        list.add(t);
    }

    public static void showList() {
        int index = 1;
        for(Task task : list) {
            System.out.println(String.format("%d. ", index)+ task);
            index++;
        }
    }

    public static void echo(String echo) {
        System.out.println(echo);
    }

    public static void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?");
        System.out.println(LINE);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (input.length() > 4) {
                    System.out.println("Please don't add things after \"list\"");
                } else {
                    showList();
                }
            } else if (input.startsWith("mark")) {
                if (input.length() < 6) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        list.get(index).mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index).toString());
                    } catch (NumberFormatException n) {
                        System.out.println("Please input a integer after \"mark\"");
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Please input a integer smaller than the number of tasks");
                    }
                }
            } else if (input.startsWith("unmark")) {
                if (input.length() < 8) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        list.get(index).unmark();
                        System.out.println("OK! I've marked this task as not done yet:");
                        System.out.println(list.get(index).toString());
                    } catch (NumberFormatException n) {
                        System.out.println("Please input a integer after \"mark\"");
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Please input a integer smaller than the number of tasks");
                    }
                }
            } else if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    System.out.println("No descriptions detected, try again");
                } else {
                        ToDo td = new ToDo(input.substring(5));
                        addList(td);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(td);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() < 10) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        String name = input.substring(9, input.lastIndexOf("/"));
                        String time = input.substring(input.lastIndexOf("/") + 4);
                        Deadline ddl = new Deadline(name, time);
                        addList(ddl);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(ddl);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (StringIndexOutOfBoundsException s) {
                        System.out.println("Due date not provided or not in the proper form");
                    }
                }
            } else if (input.startsWith("event")) {
                if (input.length() < 7) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        String name = input.substring(6, input.indexOf("/from"));
                        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to"));
                        String to = input.substring(input.indexOf("/to") + 4);
                        Event evt = new Event(name, from, to);
                        addList(evt);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(evt);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (StringIndexOutOfBoundsException s) {
                        System.out.println("Time period not provided or not in the proper form");
                    }
                }
            } else {
                System.out.println("Invalid command, try to start with \"todo\" \"deadline\"" +
                        " \"event\", type \"list\", or type \"bye\" to end");
            }
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
