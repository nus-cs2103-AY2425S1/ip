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
        command = scanner.nextLine();

        ArrayList<Task> list = new ArrayList<>();

        while (!command.equals("bye")) {
            if (command.startsWith("mark")) {
                String[] splitedString = command.split("\\s+");
                try {
                    Integer itemNumber = Integer.parseInt(splitedString[1]);
                    list.get(itemNumber-1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n"+list.get(itemNumber-1));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            } else if (command.startsWith("unmark")) {
                String[] splitedString = command.split("\\s+");
                try {
                    Integer itemNumber = Integer.parseInt(splitedString[1]);
                    list.get(itemNumber - 1).markAsUndone();
                    System.out.println("Nice! I've marked this task as done:\n"+list.get(itemNumber - 1));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
            else if (!command.equals("list")) {
                list.add(new Task(command));
                System.out.println(lineBreak + "added:" + command +"\n" + lineBreak);
            } else {
                //'list command'
                System.out.print(lineBreak);
                IntStream.range(0, list.size()).mapToObj(number -> number + 1 + ". " + list.get(number)).forEach(System.out::println);
                System.out.print(lineBreak);
            }

            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println(exit);
    }
}
