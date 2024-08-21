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

            Bob.memory.add(new Task(response));
            Bob.print("Added: " + response);
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
