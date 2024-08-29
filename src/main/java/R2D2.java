import java.util.Scanner;
import java.io.*;
public class R2D2 {
    private static final String FILE_PATH = "./data/R2D2.txt";
    public static void main(String[] args) {

        //Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);

        // init a new database and counter for memory recall
        Task[] database = new Task[100];
        int counter = 1;
        counter = loadTasks(database);

        // Main interaction
        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                database[index].setDone(true);
                saveTasks(database, counter);
                System.out.println(hline);
                System.out.println("Mission accomplished! *bzzt*");
                System.out.println(database[index].toString());
                System.out.println(hline);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                database[index].setDone(false);
                saveTasks(database, counter);
                System.out.println(hline);
                System.out.println("Argh next time! *bzzt*");
                System.out.println(database[index].toString());
                System.out.println(hline);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                Task task = database[index];
                for (int i = index; i < counter; i++) {
                    database[i] = database[i+1];
                }
                System.out.println(hline);
                System.out.println("*POOF* Circuits fried! Deleted the mission.");
                System.out.println(task.toString());
                counter--;
                saveTasks(database, counter);
                System.out.println("You currently have " + (counter - 1) + " missions available *reeeee* ");
                System.out.println(hline);
            } else if (input.equals("list")) {
                System.out.println(hline);
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + "." + database[i].toString());
                }
                System.out.println(hline);
            } else if (input.startsWith("todo")) {
                try {
                    Task task = new Todo(input.substring(5));
                    database[counter] = task;
                    saveTasks(database, counter + 1);
                    System.out.println("Understood boss. Added!");
                    System.out.println(task.toString());
                    System.out.println("You currently have " + counter + " missions available *reeeee* ");
                    System.out.println(hline);
                    counter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("*BEEP Description empty! *zzzz*");
                }
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split("/by");
                Task task = new Deadline(parts[0].substring(9), parts[1].substring(1));
                database[counter] = task;
                saveTasks(database, counter + 1);
                System.out.println("Understood boss. Added!");
                System.out.println(task.toString());
                System.out.println("You currently have " + counter + " missions available *reeeee* ");
                System.out.println(hline);
                counter++;
            } else if (input.startsWith("event")) {
                String[] parts = input.split("/");
                Task task = new Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
                database[counter] = task;
                saveTasks(database, counter + 1);
                System.out.println("Understood boss. Added!");
                System.out.println(task.toString());
                System.out.println("You currently have " + counter + " missions available *reeeee* ");
                System.out.println(hline);
                counter++;
            } else {
                System.out.println(hline);
                System.out.println("ERROR404 I do not know what you are saying *weewoo*");
                System.out.println(hline);
            }
        }

        // standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }

    private static void saveTasks(Task[] database, int counter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (int i = 1; i < counter; i++) {
                writer.println(database[i].toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    private static int loadTasks(Task[] database) {
        int counter = 1;
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        database[counter] = new Todo(parts[2]);
                        break;
                    case "D":
                        database[counter] = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        database[counter] = new Event(parts[2], parts[3], parts[4]);
                        break;
                }
                database[counter].setDone(parts[1].equals("1"));
                counter++;
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return counter;
    }
}
