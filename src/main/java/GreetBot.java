import java.util.ArrayList;
import java.util.Scanner;

public class GreetBot {
    public static void main(String[] args) {
        new GreetBot().run();
    }

    /* solution below inspired by main function in:
    https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java
    */
    private void run() {
        System.out.println("Hello! I'm GreetBot");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();
        int counter = 0;

        while (scanner.hasNext()) {

            String currentCommand = scanner.nextLine();

            if (currentCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentCommand.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(String.format("%d.%s", i + 1, list.get(i)));
                }
            } else if (currentCommand.startsWith("mark")) {
                int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                list.get(index - 1).mark();
            } else if (currentCommand.startsWith("unmark")) {
                int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                list.get(index - 1).unmark();
            } else {



                list.add(Task.decideTask(currentCommand));


                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(counter));
                counter += 1;
                System.out.println(String.format("Now you have %s tasks in the list.", counter));
            }


        }

        scanner.close();

    }
}
