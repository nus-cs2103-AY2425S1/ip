import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Murphy {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        /*
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        */
        prLine();
        System.out.println("Hello! I'm Murphy");
        System.out.println("What can I do for you?");
        prLine();
        try {
            loadSave();
        } catch (IOException | MurphyException e) {
            System.err.println("Error loading save file: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Murphy.bye();
                scanner.close();
                return;
            }

            if (input.equals("list")) {
                Murphy.list();
            }

            else if (input.startsWith("mark ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.out.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.out.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.tasks.size() || index <= 0) {
                    System.out.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.markItem(index);
            }

            else if (input.startsWith("unmark ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.out.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.out.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.tasks.size() || index <= 0) {
                    System.out.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.unmarkItem(index);
            }

            else if (input.startsWith("delete ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.out.println("delete usage: \"delete [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.out.println("delete usage: \"delete [task number]\"");
                    prLine();
                    continue;
                }
                try {
                    Murphy.deleteItem(index);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    prLine();
                }
            }

            else if(input.startsWith("todo ")){
                try {
                    Task todo = new Todo(input.substring(5));
                    Murphy.addItem(todo);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("todo usage: \"todo [description]\"");
                    prLine();
                }
            }

            else if(input.startsWith("deadline ")) {
                if (!input.contains("/by ")) {
                    System.out.println("deadline usage: \"deadline [description] /by [by when]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/by ");
                try {
                    Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
                    Murphy.addItem(deadline);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("deadline usage: \"deadline [description] /by [by when]\"");
                    prLine();
                }
            }

            else if (input.startsWith("event ")) {
                if (!input.contains("/from ") || !input.contains("/to ")) {
                    System.out.println("event usage: \"event [description] /from [by when] /to [to when]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/from ");
                String[] split2 = split[1].split("/to ");
                try {
                    Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
                    Murphy.addItem(event);
                } catch (MurphyException e) {
                    System.out.println(e.getMessage());
                    System.out.println("event usage: \"event [description] /from [by when] /to [to when]\"");
                    prLine();
                }
            }

            else {
                System.out.println("Command not found");
                prLine();
            }
        }
    }

    private static void prLine() {
        System.out.println("____________________");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        prLine();
    }

    private static void list() {
        int sz = Murphy.tasks.size();
        if (sz == 0) {
            System.out.println("Your list is currently empty. Add some tasks to get started!");
            prLine();
            return;
        }
        for (int i = 0; i < sz; i++) {
            System.out.println((i+1) + ". " + Murphy.tasks.get(i));
        }
        prLine();
    }

    private static void addItem(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        Murphy.tasks.add(task);
        System.out.println("Now you have " + Murphy.tasks.size() + " task(s) in the list.");
        prLine();
    }

    private static void markItem(int index) {
        Murphy.tasks.get(index - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Murphy.tasks.get(index - 1));
        prLine();
    }

    private static void unmarkItem(int index) {
        Murphy.tasks.get(index - 1).unmark();
        System.out.println("I've unmarked this task. Guess Murphy struck?");
        System.out.println(Murphy.tasks.get(index - 1));
        prLine();
    }

    private static void deleteItem(int index) throws MurphyException {
        if (index <= 0 || index > Murphy.tasks.size()) {
            throw new MurphyException("The task number you chose is out of the range of tasks!");
        }
        Task task = Murphy.tasks.remove(index - 1);
        System.out.println("Got it. I've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + Murphy.tasks.size() + " task(s) in the list.");
        prLine();
    }

    private static void loadSave() throws IOException, MurphyException {
        File file = new File("./data/murphy.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String nextTask = scanner.nextLine();
            String[] taskDetails = nextTask.split("\\|");
            if (Objects.equals(taskDetails[0], "T") && taskDetails.length == 3) {
                Task task = new Todo(taskDetails[2], Boolean.parseBoolean(taskDetails[1]));
                Murphy.tasks.add(task);
            } else if (Objects.equals(taskDetails[0], "D") && taskDetails.length == 4) {
                Task task = new Deadline(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                                         taskDetails[3]);
                Murphy.tasks.add(task);
            } else if (Objects.equals(taskDetails[0], "E") && taskDetails.length == 5) {
                Task task = new Event(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                                      taskDetails[3], taskDetails[4]);
                Murphy.tasks.add(task);
            } else {
                FileWriter fw = new FileWriter("./data/murphy.txt");
                fw.close();
                throw new MurphyException("Save file seems to be corrupted. Overriding save.");
            }
        }
    }
}