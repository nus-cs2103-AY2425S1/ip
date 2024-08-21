import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String intro = lineBreak + "Hello! I'm Light\nWhat can I do for you?\n" + lineBreak;
        String exit = lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak;
        System.out.println(intro);
        String command;

        Scanner scanner = new Scanner(System.in);
        String[] splitedBySpace;
        String[] splitedBySlash;
        ArrayList<Task> list = new ArrayList<>();
        Task event = null;
        while (true) {
            command = scanner.nextLine();
            splitedBySpace = command.split("\\s+", 2);
            switch (splitedBySpace[0]) {
                case "bye":
                    System.out.println(exit);
                    return;
                case "mark":
                    try {
                        Integer itemNumber = Integer.parseInt(splitedBySpace[1]);
                        list.get(itemNumber - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + list.get(itemNumber - 1));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    break;
                case "unmark":
                    try {
                        Integer itemNumber = Integer.parseInt(splitedBySpace[1]);
                        list.get(itemNumber - 1).markAsUndone();
                        System.out.println("Nice! I've marked this task as undone:\n" + list.get(itemNumber - 1));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    break;
                case "list":
                    System.out.print(lineBreak);
                    IntStream.range(0, list.size()).mapToObj(number -> number + 1 + ". " + list.get(number)).forEach(System.out::println);
                    System.out.print(lineBreak);
                    break;
                default:

                    switch (splitedBySpace[0]) {
                        case "todo":
                            event = new Todo(command);
                            break;
                        case "deadline":
                            splitedBySlash = splitedBySpace[1].split("/");
                            event = new Deadline(splitedBySlash[0], splitedBySlash[1].replace("by",""));
                            break;
                        case "event":
                            splitedBySlash = splitedBySpace[1].split("/");
                            event = new Event(splitedBySlash[0], splitedBySlash[1].replace("from",""), splitedBySlash[2].replace("to",""));
                            break;
                    }
                    if (event != null && list.add(event)) {
                        System.out.println("Got it. I've added this task:\n" +
                                event.toString() +
                                "\nNow you have " + list.size() + " tasks in the list.");
                    }

            }
        }


    }
}
