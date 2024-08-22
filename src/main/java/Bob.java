import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static List<Task> memory = new ArrayList<Task>();
    public static void main(String[] args) {
        Bob.print("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?");

        Scanner reader = new Scanner(System.in);
        String response;

        while (!(response = Bob.readUserInput(reader)).equals("bye")) {

            if (response.equals("list")) {
                Bob.print(String.format("These are your tasks:\n%s", Bob.memoryToString()));
                continue;
            }

            if (response.startsWith("mark")) {
                int taskNumber = Integer.parseInt(response.substring(5));
                Task task = memory.get(taskNumber - 1);
                task.mark();
                Bob.print(String.format("Nice! I've marked this task as done: \n\t %s", task));
                continue;
            }

            if (response.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(response.substring(7));
                Task task = memory.get(taskNumber - 1);
                task.unmark();
                Bob.print(String.format("Oh well, this task has been marked undone: \n\t %s", task));
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
                Bob.print("I don't recognise that command :(\n" +
                        "Try again.");
                continue;
            }
            Bob.memory.add(task);
            Bob.print(
                    String.format("Here's the added task:\n" +
                    "\t%s\n" +
                    "Now you have %s tasks in the list.", task, Bob.memory.size())
            );
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
        for (int i = 0; i < Bob.memory.size() - 1; i++){
            list += (i + 1) +":" + Bob.memory.get(i) + "\n";
        }
        list += Bob.memory.size() + ":" + Bob.memory.get(Bob.memory.size() - 1);
        return list;
    }

}
