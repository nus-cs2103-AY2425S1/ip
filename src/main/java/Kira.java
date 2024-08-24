import java.util.Scanner;

public class Kira {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List list = new List();
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
                    }
                }
                throw new UnreadableException();
            } catch (UnreadableException | EmptyException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
