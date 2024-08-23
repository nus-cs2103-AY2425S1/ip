import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class List {
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising List Bot...
                Special commands
                'List' -> Show full list
                'Mark n' -> marks nth task as complete
                'Unmark n' -> marks nth task as incomplete
                '/by z' -> used to speficy a deadline z
                '/from x /to y' -> used to specify bounds of an event from x to y
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = RapGod.scanner;
        String input = "";
        ArrayList<Task> list = new ArrayList<Task>();

        while (true) {
            System.out.print("Task:\n");
            input = scanner.nextLine();

            try {
                if (input == null || input.trim().isEmpty()) {
                    throw new NoInputException();
                } else if (RapGod.RUDE_WORDS.contains(input)) {
                    throw new RudeInputException();
                }

                if (input.equalsIgnoreCase("List")) {
                    System.out.println("-----------------------------------------------");
                    System.out.println("Displaying List:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, list.get(i));
                    }
                    System.out.println("-----------------------------------------------");
                } else if (input.length() > 5 && input.substring(0, 5).equalsIgnoreCase("mark ")) {
                    int index = Integer.parseInt(input.substring(5));
                    list.get(index- 1).setIsDone(true);
                    System.out.println("-----------------------------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index - 1));
                    System.out.println("-----------------------------------------------");
                } else if (input.length() > 7 && input.substring(0, 7).equalsIgnoreCase("unmark ")) {
                    int index = Integer.parseInt(input.substring(7));
                    list.get(index - 1).setIsDone(false);
                    System.out.println("-----------------------------------------------");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index - 1));
                    System.out.println("-----------------------------------------------");
                } else if (input.length() > 7 && input.substring(0, 7).equalsIgnoreCase("delete ")) {
                    int index = Integer.parseInt(input.substring(7));
                    System.out.println("-----------------------------------------------");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(list.get(index - 1));
                    list.remove(index - 1);
                    System.out.printf("Now you have %d tasks in the list\n", list.size());
                    System.out.println("-----------------------------------------------");
                } else if (input.toLowerCase().contains("/from") ^ input.toLowerCase().contains("/to")) {
                    System.out.println("Please add both /from and /to.");
                } else if (input.equalsIgnoreCase("Bye")) {
                    break;
                } else {

                    if (input.toLowerCase().contains("/from") && input.toLowerCase().contains("/to")) {
                        String description  = input.substring(0, input.toLowerCase().indexOf("/from"));
                        String from = input.substring(input.toLowerCase().indexOf("/from") + 6, input.toLowerCase().indexOf("/to"));
                        String to = input.substring(input.toLowerCase().indexOf("/to") + 4);
                        list.add(new Event(description, from, to));
                    } else if (input.toLowerCase().contains("/by")) {
                        String description = input.substring(0, input.toLowerCase().indexOf("/by"));
                        String due = input.substring(input.toLowerCase().indexOf("/by") + 4);
                        list.add(new Deadline(description, due));
                    } else {
                        String description = input;
                        list.add(new ToDo(description));
                    }

                    System.out.println("-----------------------------------------------");
                    System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
                    System.out.printf("Now you have %d tasks in the list\n", list.size());
                    System.out.println("-----------------------------------------------");

                }
            } catch (NumberFormatException exc) {
                System.out.println("Enter a valid number after 'Mark ' or 'Unmark '. Eg. Mark 4");
            } catch (IndexOutOfBoundsException exc) {
                System.out.println("No such task exists.");
            } catch (NoInputException | RudeInputException exc) {
                System.out.println("-----------------------------------------------");
                System.out.println("RapGod:\n" + exc.getMessage());
                System.out.println("-----------------------------------------------");
            }


        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
