import java.util.ArrayList;
import java.util.Scanner;

public class Primo {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean ended = false;
    private static ArrayList<Task> list = new ArrayList<>();
    private enum CommandType {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private String command;
        CommandType(String command) {
            this.command = command;
        }

        public static CommandType getCommandType(String s) throws PrimoException {
            for (CommandType type : values()) {
                if (type.command.equals(s)) {
                    return type;
                }
            }
            throw new PrimoException("Invalid command!\n(Expected Commands: todo, deadline, event, mark, unmark, list, bye)\n");
        }
    }

    private static void printList() {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = String.valueOf(i + 1) + "." + list.get(i);
            System.out.println(output);
        }
    }

    private static void sayBye() {
        String byeMessage = "\nEl Primo:\n" +
                            "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        ended = true;
    }

    private static void assessInput(String input) throws PrimoException{
        String[] words = input.split(" ");
        CommandType type = CommandType.getCommandType(words[0]);
        switch (type) {
            case BYE:
                sayBye();
                break;
            case LIST:
                System.out.println("\nEl Primo:");
                System.out.println("Here are the tasks in your list:");
                printList();
                break;
            case MARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("mark <integer> expected");
                }
                int markIndex = Integer.valueOf(words[1]) - 1;
                if (markIndex >= list.size() || markIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                list.get(markIndex).markAsDone();
                System.out.println("\nEl Primo:");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(markIndex));
                break;
            case UNMARK:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("unmark <integer> expected");
                }
                int unmarkIndex = Integer.valueOf(words[1]) - 1;
                if (unmarkIndex >= list.size() || unmarkIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                list.get(unmarkIndex).markAsUndone();
                System.out.println("\nEl Primo:");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(unmarkIndex));
                break;
            case TODO:
                int todoFromIndex = input.indexOf("todo ") + 5;
                String todoDescription = input.substring(todoFromIndex).trim();
                if (todoDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected: todo <string>");
                }
                Task newToDoTask = new ToDoTask(todoDescription);
                list.add(newToDoTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newToDoTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                break;
            case DEADLINE:
                if (!input.contains("/by")) {
                    throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
                }
                int deadlineFromIndex = input.indexOf("deadline ") + 9;
                int deadlineToIndex = input.indexOf("/by");
                String deadlineDescription = input.substring(deadlineFromIndex, deadlineToIndex).trim();
                if (deadlineDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
                }
                String dueTime = input.substring(deadlineToIndex + 3).trim();
                if (dueTime.isEmpty()) {
                    throw new PrimoException("deadline time cannot be empty! Expected deadline <string> /by <string>");
                }
                Task newDeadlineTask = new DeadlineTask(deadlineDescription, dueTime);
                list.add(newDeadlineTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadlineTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                break;
            case EVENT:
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
                }
                int eventFromIndex = input.indexOf("event ") + 6;
                int eventToIndex = input.indexOf("/from");
                int eventFinalIndex = input.indexOf("/to");
                String eventDescription = input.substring(eventFromIndex, eventToIndex).trim();
                if (eventDescription.isEmpty()) {
                    throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
                }
                String from = input.substring(eventToIndex + 5, eventFinalIndex).trim();
                if (from.isEmpty()) {
                    throw new PrimoException("'From' parameter cannot be empty! Expected deadline <string> /by <string>");
                }
                String to = input.substring(eventFinalIndex + 3).trim();
                Task newEventTask = new EventTask(eventDescription, from, to);
                if (to.isEmpty()) {
                    throw new PrimoException("'To' parameter cannot be empty! Expected deadline <string> /by <string>");
                }
                list.add(newEventTask);
                System.out.println("\nEl Primo:");
                System.out.println("Got it. I've added this task:");
                System.out.println(newEventTask);
                System.out.printf("Now you have %d tasks in the list.%n", list.size());
                break;
            case DELETE:
                try {
                    Integer.valueOf(words[1]);
                } catch (NumberFormatException e) {
                    throw new PrimoException("delete <integer> expected");
                }
                int deleteIndex = Integer.valueOf(words[1]) - 1;
                if (deleteIndex >= list.size() || deleteIndex + 1 <= 0) {
                    throw new PrimoException("Please select within the indexes of the tasklist!");
                }
                System.out.println("\nEl Primo:");
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(deleteIndex));
                list.remove(deleteIndex);
        }
    }
    private static void readInput() {
        System.out.println("\nMe:");
        String input = scanner.nextLine();
        try {
            assessInput(input);
        } catch (PrimoException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("""
                El Primo:
                Hello! I'm El Primo!!
                What can I do for you?""");

        while (!ended) {
            readInput();
        }
    }
}
