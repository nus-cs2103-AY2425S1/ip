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

        ArrayList<String> list = new ArrayList<>();

        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                list.add(command);
                System.out.println(lineBreak + "added:" + command +"\n" + lineBreak);
            } else {
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
