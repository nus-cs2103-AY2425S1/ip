import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Kira {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        File file = new File("data/kira.txt");
        List list = retrieve(file);
        String line = "____________________________________________________________\n";

        System.out.println(line +
                " Hello! I'm Kira\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(list.displayList());
                continue;
            }

            try {

                String[] strings = userInput.split("\\s+", 2);
                String firstWord = strings[0];


                switch (firstWord) {
                    case "mark" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("mark");
                        }
                        String restOfWords = strings[1];
                        int index = Integer.parseInt(restOfWords) - 1;
                        Task task = list.getTask(index);
                        task.markAsDone();
                        System.out.println(task.markedNotification());
                    }
                    case "unmark" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("unmark");
                        }
                        String restOfWords = strings[1];
                        int index = Integer.parseInt(restOfWords) - 1;
                        Task task = list.getTask(index);
                        task.markAsUndone();
                    }
                    case "todo" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("todo");
                        }
                        String restOfWords = strings[1];
                        Task task = new ToDo(restOfWords);
                        list.addTaskToList(task);
                        System.out.println(list.addedNotification(task));
                    }
                    case "deadline" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("deadline");
                        }
                        String restOfWords = strings[1];
                        String deadline = restOfWords.split("/by")[1];
                        String input = restOfWords.split("/by")[0];
                        Task task = new Deadline(input, deadline);
                        list.addTaskToList(task);
                        System.out.println(list.addedNotification(task));
                    }
                    case "event" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("event");
                        }
                        String restOfWords = strings[1];
                        String input = restOfWords.split("/from")[0];
                        String period = restOfWords.split("/from")[1];
                        String start = period.split("/to")[0];
                        String end = period.split("/to")[1];
                        Task task = new Event(input, start, end);
                        list.addTaskToList(task);
                        System.out.println(list.addedNotification(task));
                    }
                    case "delete" -> {
                        if (strings.length < 2) {
                            throw new EmptyException("delete");
                        }
                        String restOfWords = strings[1];
                        int index = Integer.parseInt(restOfWords) - 1;
                        list.deleteTask(index);
                    }
                    default -> {
                        throw new UnreadableException();
                    }
                }
                save(list, file);
            } catch (UnreadableException | EmptyException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("File cannot be saved" + e.getMessage());
            }
        }
    }

    public static void save(List list, File file) throws IOException {

        FileWriter filewriter = new FileWriter(file, false);

        for (int i=0; i < list.getTasks().size(); i++) {
            String line = list.getTasks().get(i).displayTask();
            filewriter.write(line);
        }
        filewriter.close();
    }

    public static List retrieve(File file) {
        try {
            Scanner s = new Scanner(file);
            List list = new List();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] strings = line.split("\\[");
                String type = strings[1].split("]")[0];
                String description = strings[2].split("] ")[1];
                String check = strings[2].split("] ")[0];
                Task task = Task.intepreteTask(description, type);
                if (Objects.equals(check, "X")) {
                    task.markAsDone();
                }
                list.addTaskToList(task);
            }
            return list;
        } catch (FileNotFoundException e) {     // user is new
            return new List();
        }
    }
}
