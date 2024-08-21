import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) throws LightException {
        String lineBreak = "____________________________________________________________\n";
        String intro = lineBreak + "Hello! I'm Light\nWhat can I do for you?\n" + lineBreak;
        String exit = lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak;
        System.out.println(intro);
        String command;

        Scanner scanner = new Scanner(System.in);
        String[] splitedBySpace;
        String[] splitedBySlash;
        Task event;
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            event = null;
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
                            try {
                                event = new Todo(command.replace("todo", ""));
                            } catch (LightException e) {
                                System.out.println(e);
                            }

                            break;
                        case "deadline":
                            try {
                                splitedBySlash = splitedBySpace[1].split("/");
                                event = new Deadline(splitedBySlash[0], splitedBySlash[1].replace("by", ""));
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Not enough arguments");
                            }
                            catch (LightException e) {
                                System.out.println(e);
                            }

                            break;
                        case "event":
                            try {
                                splitedBySlash = splitedBySpace[1].split("/");
                                event = new Event(splitedBySlash[0], splitedBySlash[1].replace("from", ""), splitedBySlash[2].replace("to", ""));
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Not enough arguments");
                            } catch (LightException e) {
                                System.out.println(e);
                            }

                            break;

                        default:
                            System.out.println(new LightException("Please key in a valid input"));
                    }
                    if (event != null && list.add(event)) {
                        System.out.println("Got it. I've added this task:\n" +
                                event +
                                "\nNow you have " + list.size() + " tasks in the list.");
                    }

            }
        }


    }
}
