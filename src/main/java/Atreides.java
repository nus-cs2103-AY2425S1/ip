import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

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

        ArrayList<Task> list = new ArrayList<>();

        String filePathName = "src/main/Atreides.txt";

        File file = new File(filePathName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task newTask = getTask(parts);
                list.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println(new Response(e.getMessage()));
        }

        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();

        Set<String> commands = Set.of("mark", "unmark", "delete", "todo", "event", "deadline");
        while (!(msg.equalsIgnoreCase("bye"))) {
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
                try {
                    String[] words = msg.split(" ");
                    if (commands.contains(words[0]) && words.length < 2) {
                        throw new AtreidesException("Description of " + words[0] + " cannot be empty");
                    }
                    if (words[0].equals("mark")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= list.size()) {
                            throw new AtreidesException("list does not have the index present");
                        }
                        list.get(index).markDone(true);

                        String response = "Thank you, one task completed: \n"
                                + list.get(index);
                        System.out.println(new Response(response));
                    } else if (words[0].equals("unmark")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= list.size()) {
                            throw new AtreidesException("list does not have the index present");
                        }
                        list.get(index).markDone(false);

                        String response = "Noted, this task has been unmarked\n"
                                + list.get(index);
                        System.out.println(new Response(response));
                    } else if (words[0].equals("delete")) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= list.size()) {
                            throw new AtreidesException("list does not have the index present");
                        }
                        Task remove = list.remove(index);
                        String plural = list.size() == 1 ? " task" : " tasks";
                        String response = "Task removed: \n" +
                                remove.toString().indent(2) +
                                + list.size() + plural + " in list\n";
                        System.out.println(new Response(response));


                    } else {
                        Task newTask = getTask(words, msg);
                        list.add(newTask);
                        String plural = list.size() == 1 ? " task" : " tasks";
                        String response = "Task added\n"
                                + newTask.toString().indent(2)
                                + list.size() + plural + " in list\n";
                        System.out.println(new Response(response));
                    }
                }
                catch (AtreidesException e) {
                    System.out.println(new Response(e.getDescription()));
                }
            }
            msg = scanner.nextLine();
        }
        try {
            PrintWriter pw = new PrintWriter(filePathName);
            String tasks = "";
            for (int i = 0; i < list.size(); i++) {
                String writeLine = list.get(i).write();
                if (i != list.size() - 1) {
                    writeLine += "\n";
                }
                pw.print(writeLine);
            }
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(new Response(e.getMessage()));
        }

        String outro = "Praise be Muad Dib\n"
                       + "GoodBye\n";
        System.out.println(new Response(outro));
        scanner.close();
    }

    private static Task getTask(String[] words, String msg) throws AtreidesException {
        Task newTask;
        if (words[0].equals("todo")) {
            newTask = new ToDo(msg.substring(5));
        } else if (words[0].equals("deadline")) {
            String[] parts = msg.split(" /by ");
            String by = parts[parts.length - 1];
            String description = parts[0].split("deadline ")[1];
            newTask = new Deadline(description, by);
        } else if (words[0].equals("event")) {
            String[] parts = msg.split(" /from ");
            String[] startEnd = parts[parts.length - 1].split(" /to ");
            String description = parts[0].split("event ")[1];
            newTask = new Events(description, startEnd);
        } else {
            throw new AtreidesException("I dont know what u mean, please give me a todo, event or deadline");
        }
        return newTask;
    }

    private static Task getTask(String[] words) {
        Task newTask = new Task();
        Boolean isDone = words[1].equals("1");
        if (words[0].equals("T")) {
            newTask = new ToDo(words[2]);
        } else if (words[0].equals("D")) {;
            String description = words[2];
            String by = words[3];
            newTask = new Deadline(description, by);
        } else if (words[0].equals("E")) {
            String description = words[2];
            String[] startEnd = words[3].split("-");
            newTask = new Events(description, startEnd);
        }
        newTask.markDone(isDone);
        return newTask;
    }
}