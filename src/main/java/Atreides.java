import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Atreides {
    public static void main(String[] args) {
        String logo =  "          _            _     _\n"
                    +  "    / \\  | |          (_)   | |\n"
                    +  "   /   \\ | |_ _ __ ___ _  __| | ___  ___\n"
                    +  "  / / \\ \\| __| '__/ _ \\ |/ _` |/ _ \\/ __|\n"
                    +  " / _____ \\ |_| | |  __/ | (_| |  __/\\__ \\\n"
                    +  "/_/     \\_\\__|_|  \\___|_|\\__,_|\\___||___/\n";

        String intro = "Glory to house\n" + logo;
        System.out.println(new Response(intro));
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while (!(msg.toLowerCase().equals("bye"))) {
            if (msg.equals("list")) {
                String tasks = "";
                for (int i = 0; i < list.size(); i++) {
                    tasks += (i+1) + "." + list.get(i);
                    if (i != list.size() - 1) {
                        tasks += "\n";
                    }
                }
                System.out.println(new Response(tasks));
            }
            else {
                String[] words = msg.split(" ");
                if (words[0].equals("mark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    list.get(index).markDone(true);

                    String response = "Thank you, one task completed: \n"
                                       + list.get(index);
                    System.out.println(new Response(response));
                }
                else if (words[0].equals("unmark")) {
                    int index = Integer.parseInt(words[1]) -1;
                    list.get(index).markDone(false);

                    String response = "Noted, this task has been unmarked\n"
                                      + list.get(index);
                    System.out.println(new Response(response));
                } else {
                    Task newTask = new Task("");
                    if (words[0].equals("todo")) {
                        newTask = new ToDo(msg.substring(5));
                    }
                    else if (words[0].equals("deadline")) {
                        String[] parts = msg.split(" /by ");
                        String by = parts[parts.length - 1];
                        String description = parts[0].split("deadline ")[1];
                        newTask = new Deadline(description, by);
                    } else {
                        String[] parts = msg.split(" /from ");
                        String[] startEnd = parts[parts.length - 1].split(" /to ");
                        String description = parts[0].split("event ")[1];
                        newTask = new Events(description, startEnd);
                    }
                    list.add(newTask);
                    String plural = list.size() == 1 ? " task" : " tasks";
                    String response = "Task added\n"
                                        + newTask + "\n"
                                        + list.size() + plural + " in list\n";
                    System.out.println(new Response(response));
                }
            }
            msg = scanner.nextLine();
        }
        String outro = "Praise be Muad Dib\n"
                       + "GoodBye\n";
        System.out.println(new Response(outro));
        scanner.close();
    }
}