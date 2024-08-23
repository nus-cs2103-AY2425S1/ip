import java.util.Scanner;

public class Him {

    private static final HimList list = new HimList();

    private static void greet() {
        System.out.println("Him: Hello! I'm Him\n     What can I do for you?\n");
    }

    private static void complete(int index) {
        try {
            list.complete(index);
            System.out.println("\nHim: LET'S GOOOOO! " + list.taskAt(index) + " has been completed!\n");
        } catch (AlreadyCompletedException | TaskDoesNotExistException e) {
            System.out.println("\nHim: " + e.getMessage() + "\n");
        }
    }

    private static void exit() {
        System.out.println("\nHim: WAIT NO! DON'T LEAVE ME ALON-\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        System.out.print("User: ");
        String[] input = scanner.nextLine().split(" ", 2);
        String command = input[0];
        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    System.out.println("\nHim: Sure! Here's your list!\n\n" + list);
                    break;
                case "mark":
                    complete(Integer.parseInt(input[1]) - 1);
                    break;
                case "todo": {
                    try {
                        ToDo newToDo = new ToDo(input[1]);
                        list.add(newToDo);
                        System.out.println("\nHim: added \"" + newToDo + "\" to list\n");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\nHim: ToDos need a description!\n");
                    } finally {
                        break;
                    }
                }
                case "deadline": {
                    try {
                        String[] details = input[1].split("/by");
                        Deadline newDeadline = new Deadline(details[0].trim(), details[1].trim());
                        list.add(newDeadline);
                        System.out.println("\nHim: added \"" + newDeadline + "\" to list\n");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\nHim: Deadlines need a description and a due date!\n");
                    } finally {
                        break;
                    }
                }
                case "event": {
                    try {
                        String[] details = input[1].split("/");
                        Event newEvent = new Event(details[0].trim(), details[1].substring(details[1].indexOf(" ")).trim(),
                                details[2].substring(details[2].indexOf(" ")).trim());
                        list.add(newEvent);
                        System.out.println("\nHim: added \"" + newEvent + "\" to list\n");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\nHim: Events need a description, a start time and an end time!\n");
                    } finally {
                        break;
                    }
                }
            }
            System.out.print("User: ");
            input = scanner.nextLine().split(" ", 2);
            command = input[0];
        }
        exit();
    }
}
