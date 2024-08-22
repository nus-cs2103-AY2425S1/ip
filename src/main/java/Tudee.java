import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Tudee {
    public static void main(String[] args) {
        String logo = "____________________________________________________________ \n"
                + "Hello! I'm Tudee, your chatbot bestie! \n"
                + "How can I help you today? :) \n"
                + "____________________________________________________________ \n";
        System.out.println(logo);
        String input;
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        while (true) {
            String output = "";
            input = sc.nextLine();
            Task currentTask = new Task(input, false);
            if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________ \n");
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getStatus()) {
                        System.out.println(i+1 + ". [X] " + list.get(i).getString() + "\n");
                    }
                    else {
                        System.out.println(i+1 + ". [ ] " + list.get(i).getString() + "\n");
                    }
                }
                System.out.println("____________________________________________________________ \n");
            }
            else if (input.equalsIgnoreCase("bye")){
                output = "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! Don't forget about me :( \n"
                        + "____________________________________________________________";
                System.out.println(output);
                break;
            }
            else if (input.contains("mark")) {
                String[] inputArray = input.split(" ");
                int index = Integer.parseInt(inputArray[1]) - 1;
                String target = list.get(index).getString();
                if (inputArray[0].equals("mark")) {
                    list.set(index, new Task(target, true));
                    System.out.println("____________________________________________________________ \n");
                    System.out.println("Nice! I've marked this task as done: \n");
                    System.out.println("  [X] " + target);
                    System.out.println("____________________________________________________________ \n");
                }
                else if (inputArray[0].equals("unmark")) {
                    list.set(index, new Task(target, false));
                    System.out.println("____________________________________________________________ \n");
                    System.out.println("Ok, I've marked this task as not done yet: \n");
                    System.out.println("  [ ] " + target);
                    System.out.println("____________________________________________________________ \n");
                }
            }
            else {
                list.add(currentTask);
                System.out.println("____________________________________________________________ \n");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________ \n");
            }
        }
        sc.close();
    }
}
