import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Murphy {
    private Storage storage;
    private TaskList tasks;

    public Murphy(String filepath) {
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (MurphyException e) {
            System.err.printf("Error loading Murphy up: %s\n", e.getMessage());
        }
    }
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
                try {
                    Murphy.writeSave();
                } catch (IOException e) {
                    System.err.println("Error writing to save file: " + e.getMessage());
                }
                scanner.close();
                return;
            }

            if (input.equals("list")) {
                Murphy.list();
            }

            else if (input.startsWith("mark ")) {
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.err.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.err.println("mark usage: \"mark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.tasks.size() || index <= 0) {
                    System.err.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.markItem(index);
            }

            else if (input.startsWith("unmark ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.err.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.err.println("unmark usage: \"unmark [task number]\"");
                    prLine();
                    continue;
                }
                if (index > Murphy.tasks.size() || index <= 0) {
                    System.err.println("The task number you chose is outside of the range of tasks!");
                    prLine();
                    continue;
                }
                Murphy.unmarkItem(index);
            }

            else if (input.startsWith("delete ")){
                String[] split = input.split(" ");
                if (split.length > 2) {
                    System.err.println("delete usage: \"delete [task number]\"");
                    prLine();
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    System.err.println("delete usage: \"delete [task number]\"");
                    prLine();
                    continue;
                }
                try {
                    Murphy.deleteItem(index);
                } catch (MurphyException e) {
                    System.err.println(e.getMessage());
                    prLine();
                }
            }

            else if(input.startsWith("todo ")){
                try {
                    Task todo = new Todo(input.substring(5));
                    Murphy.addItem(todo);
                } catch (MurphyException e) {
                    System.err.println(e.getMessage());
                    System.err.println("todo usage: \"todo [description]\"");
                    prLine();
                }
            }

            else if(input.startsWith("deadline ")) {
                if (!input.contains("/by ")) {
                    System.err.println("deadline usage: \"deadline [description] /by [date]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/by ");
                try {
                    Task deadline = new Deadline(split[0].substring(9).trim(), split[1]);
                    Murphy.addItem(deadline);
                } catch (MurphyException e) {
                    System.err.println(e.getMessage());
                    System.err.println("deadline usage: \"deadline [description] /by [date]\"");
                    prLine();
                }
            }

            else if (input.startsWith("event ")) {
                if (!input.contains("/from ") || !input.contains("/to ")) {
                    System.err.println("event usage: \"event [description] /from [date] /to [date]\"");
                    prLine();
                    continue;
                }
                String[] split = input.split("/from ");
                String[] split2 = split[1].split("/to ");
                try {
                    Task event = new Event(split[0].substring(6).trim(), split2[0].trim(), split2[1]);
                    Murphy.addItem(event);
                } catch (MurphyException e) {
                    System.err.println(e.getMessage());
                    System.err.println("event usage: \"event [description] /from [date] /to [date]\"");
                    prLine();
                }
            }

            else {
                System.err.println("Command not found");
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

    private static void writeSave() throws IOException {
        FileWriter fw = new FileWriter("./data/murphy.txt");
        for (Task task : Murphy.tasks) {
            fw.write(task.toSaveString() + System.lineSeparator());
        }
        fw.close();
    }
}