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
            // if first word is mark, mark task as complete
            } else if (input.startsWith("mark")) {
                System.out.println(list[Integer.parseInt(parsed[1]) - 1].mark());
            // if first word is unmark, mark task as incomplete
            } else if (input.startsWith("unmark")){
                System.out.println(list[Integer.parseInt(parsed[1]) - 1].unmark());
            // Save input as new task
            } else {
                System.out.println("Got it. I've added this task:");

                Task newTask;

                if (input.startsWith("todo")) {
                    String[] split = input.split(" ", 2);

                    newTask = new ToDo(split[1]);
                } else if (input.startsWith("deadline")) {
                    String[] split1 = input.split(" ", 2);
                    String[] split2 = split1[1].split(" /by ");

                    newTask = new Deadline(split2[0], split2[1]);
                } else if (input.startsWith("event")) {
                    String[] split1 = input.split(" ", 2);
                    String[] split2 = split1[1].split(" /from ");
                    String[] split3 = split2[1].split(" /to ");

                    newTask = new Event(split2[0], split3[0], split3[1]);
                } else {
                    newTask = new Task(input);
                }


                list[pointer] = newTask;
                System.out.println(newTask);
                pointer++;
                System.out.printf("Now you have %d tasks in the list. \n", pointer);
            }
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}

