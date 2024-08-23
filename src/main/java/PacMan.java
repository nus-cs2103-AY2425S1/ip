import java.util.ArrayList;
import java.util.Scanner;

public class PacMan {
    private static final ArrayList<Task> list = new ArrayList<>();

    private static void echo(String text) {
        System.out.println("  " + text);
    }

    private static void greet() {
        echo("Hello! I'm PacMan");
        echo("How can I help you?");
    }

    private static void exit() {
        echo("Good bye. Hope to see you soon!");
    }

    private static void addList(Task newTask) {
        echo("Got it. I've added this task:");
        echo("  " + newTask);
        list.add(newTask);
        echo("Now you have " + list.size() + " tasks in the list.");
    }

    private static void printList() {
        echo("Here are the tasks in your list:");
        for (int index = 1; index <= list.size(); index = index + 1) {
            echo(index + "." + list.get(index - 1));
        }
    }

    private static void markTask(int index) {
        echo("Nice! I've marked this task done");
        list.get(index - 1).setMarkDone(true);
        echo("  " + list.get(index - 1));
    }

    private static void unmarkTask(int index) {
        echo("Ok! I've marked this task as not done yet");
        list.get(index - 1).setMarkDone(false);
        echo("  " + list.get(index - 1));
    }

    private static void addTodo(String task) {
        addList(new Todo(task));
    }

    private static void addDeadline(String task) {
        String[] splitter = task.split("/", 2);
        String taskName = splitter[0];
        String from = splitter[1].split(" ", 2)[1];
        addList(new Deadline(taskName, from));
    }

    private static void addEvent(String task) {
        String[] splitter = task.split("/", 3);
        String taskName = splitter[0];
        String from = splitter[1].split(" ", 2)[1];
        String to = splitter[2].split(" ", 2)[1];
        addList(new Event(taskName, from, to));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        label:
        while (true) {
            String input = scanner.nextLine();
            String type = input.split(" ", 2)[0];
            switch (type) {
                case "bye":
                    break label;
                case "list":
                    printList();
                    break;
                case "mark":
                    try {
                        markTask(Integer.parseInt(input.split(" ")[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        echo("I'm sorry, but I can't find the index number :(");
                    } catch (NumberFormatException e) {
                        echo("I'm sorry, but it's invalid index :(");
                    }
                    break;
                case "unmark":
                    try {
                        unmarkTask(Integer.parseInt(input.split(" ")[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        echo("I'm sorry, but I can't find the index number :(");
                    } catch (NumberFormatException e) {
                        echo("I'm sorry, but it's invalid index :(");
                    }
                    break;
                case "todo": {
                    String task = input.split(" ", 2)[1];
                    addTodo(task);
                    break;
                }
                case "deadline": {
                    String task = input.split(" ", 2)[1];
                    addDeadline(task);
                    break;
                }
                case "event": {
                    String task = input.split(" ", 2)[1];
                    addEvent(task);
                    break;
                }
                default:
                    echo("I'm sorry, but I can't understand what you ask :(");
                    break;
            }
        }
        exit();
    }
}
