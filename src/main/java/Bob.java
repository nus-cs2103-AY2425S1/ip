import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static List<Task> memory = new ArrayList<Task>();
    public static void main(String[] args) {
        Bob.print("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?\n\n" +
                Bob.HELP_MESSAGE);

        Scanner reader = new Scanner(System.in);
        String response;

        while (!(response = Bob.readUserInput(reader)).equals("bye")) {
            try {
            if (response.startsWith("list")) {
                if (response.equals("list")) {
                    Bob.print(String.format("These are your tasks:\n%s", Bob.memoryToString()));
                } else {
                    Bob.print("Did you mean 'list'?");
                }
                continue;
            }

            if (response.startsWith("mark")) {
                try {
                    int taskNumber = Integer.parseInt(response.substring(5));
                    Task task = memory.get(taskNumber - 1);
                    task.mark();
                    Bob.print(String.format("Nice! I've marked this task as done:\n\t%s", task));
                } catch (IndexOutOfBoundsException e) {
                    Bob.print("Nice try but there's no such task.");
                }
                continue;
            }

            if (response.startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(response.substring(7));
                    Task task = memory.get(taskNumber - 1);
                    task.unmark();
                    Bob.print(String.format("Oh well, this task has been marked undone:\n\t%s", task));
                } catch (IndexOutOfBoundsException e) {
                    Bob.print("There's no such task!");
                }
                continue;
            }

            if (response.equals("I need help.")) {
                Bob.print("Here are the list of commands:\n" +
                        "1. list\n\t- lists tasks\n" +
                        "2. mark <task number>\n\t- marks the task as done\n" +
                        "3. unmark <task number>\n\t- unmarks the task\n" +
                        "4. deadline <task description> /by <by>\n\t- Creates a deadline\n" +
                        "5. todo <task description>\n\t- Creates a todo\n" +
                        "6. event <task description> /from <from> /to <to>\n\t- Creates an event\n" +
                        "7. bye\n\t- exits the program");
                continue;
            }

            Task task;
            if (response.startsWith("deadline")) {
                String by = response.substring(response.indexOf("/by") + "/by".length()).trim();
                String description = response.substring(
                        "deadline ".length(),
                        response.indexOf("/by")).trim();
                task = new Deadline(description, by);
            } else if (response.startsWith("todo")) {
                String description = response.substring(
                        "todo ".length()).trim();
                task = new ToDo(description);
            } else if (response.startsWith("event")) {
                String from = response.substring(
                        response.indexOf("/from") + "/from".length(),
                        response.indexOf("/to")).trim();
                String to = response.substring(response.indexOf("/to") + "/to".length()).trim();
                String description = response.substring(
                        "event ".length(),
                        response.indexOf("/from")).trim();
                task = new Event(description, from, to);
            } else {
                throw new InvalidCommandException();
            }
            Bob.memory.add(task);
            Bob.print(
                    String.format("Here's the added task:\n" +
                    "\t%s\n" +
                    "Now you have %s tasks in the list.", task, Bob.memory.size())
            );
            } catch(InvalidCommandException e) {
                Bob.print("I don't recognise that command :( Try again.\n" +
                        Bob.HELP_MESSAGE
                );
            } catch(NumberFormatException e) {
                Bob.print("Seems like at least one of the arguments to this command was\n" +
                        "not a number when it should have been.\n" + Bob.HELP_MESSAGE);
            } catch(StringIndexOutOfBoundsException e) {
                Bob.print("Seems like the command keyed wasn't appropriately used. You may have\n" +
                        "given insufficient information. Also check that the order in which\n" +
                        "the information was given is correct.\n" + Bob.HELP_MESSAGE);
            }
        }
        Bob.print("Bye.");
    }

    private static String readUserInput(Scanner s) {
        System.out.print("> ");
        return s.nextLine();
    }

    private static void print(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }

    private static String memoryToString() {
        String list = "";
        for (int i = 0; i < Bob.memory.size(); i++){
            list += (i + 1) +":" + Bob.memory.get(i);
            if (i < Bob.memory.size() - 1) {
                 list += "\n";
            }
        }
        return list.isEmpty() ? "No tasks :(" : list;
    }

    private static final String HELP_MESSAGE = "Key in \"I need help.\" for additional help.";
}
