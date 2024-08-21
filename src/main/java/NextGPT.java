import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class NextGPT {
    public static void main(String[] args) {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm NextGPT and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";

        System.out.println(greeting);
        List<Task> todo_list = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {

                System.out.println("_______________________________________________________\n");
                for (int i = 0; i <todo_list.size() ; i++) {
                    System.out.println(i+1 + "." + " " + "[" + todo_list.get(i).getStatusIcon() + "]" + " "  + todo_list.get(i).description);
                }
                System.out.println("_______________________________________________________\n");
            } else if (input.length() >= 6 && input.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(input.substring(input.length() - 1));
                Task task = todo_list.get(index - 1);
                task.mark();
                System.out.println("_______________________________________________________\n " +
                        "Nice! I've marked this task as done:\n   " + "[" + task.getStatusIcon() + "]" + " "+ task.description +
                        "\n_______________________________________________________\n");
            }
            else if (input.length() >= 8 && input.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(input.substring(input.length() - 1));
                Task task = todo_list.get(index - 1);
                task.unmark();
                System.out.println("_______________________________________________________\n " +
                        "Ok, I've marked this task as not done yet:\n   " + "[" + task.getStatusIcon() + "]" + " " + task.description +
                        "\n_______________________________________________________\n");
            } else {

                /** create a task here **/
                Task new_task = new Task(input);
                todo_list.add(new_task);
                System.out.println(
                        input + "\n" +
                                "_______________________________________________________\n"
                                + "added: " + new_task.description + "\n"
                                + "_______________________________________________________\n"
                );
            }
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);

    }

}
