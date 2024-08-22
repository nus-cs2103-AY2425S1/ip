import java.util.Scanner;

public class Talker {
    public static void main(String[] args) {
        String name = "Talker";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);

        Task[] list = new Task[100];
        int pointer = 0;

        while (true) {
            // read user input
            String input = reader.nextLine();

            // parse the input by " "
            String[] parsed = input.split(" ");

            System.out.println(line);

            // if command "bye" entered, exit
            if (input.equals("bye")) {
                break;
            // if command "list" entered, print all tasks in list
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < pointer; i++) {
                    System.out.printf("%d.%s\n", i + 1, list[i]);
                }
                System.out.println(line);
            // if first word after parsing is mark, mark task as complete
            } else if (parsed[0].equals("mark")) {
                System.out.println(list[Integer.parseInt(parsed[1]) - 1].mark());
            // if first word after parsing is unmark, mark task as incomplete
            } else if (parsed[0].equals("unmark")){
                System.out.println(list[Integer.parseInt(parsed[1]) - 1].unmark());
            // Save input as new task
            } else {
                list[pointer] = new Task(input);
                pointer++;
                System.out.printf("added: %s\n", input);
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}

