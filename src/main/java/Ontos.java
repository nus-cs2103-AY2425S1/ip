import java.util.Scanner;

public class Ontos {
    public static String hello = " Hello! I'm Ontos \n"
                   + " What can I do for you?\n";
    public static String line = "____________________________________________________________\n";
    public static String bye = " Bye. Hope to see you again soon!\n";
    public static int maxInput = 100;
    public static String listPrompt = " Here are the tasks in your list:";
    public static String completeTaskPrompt = " Nice! I've marked this task as done:\n   ";
    public static String uncompleteTaskPrompt = " OK, I've marked this task as not done yet:\n   ";
    public static String taskAdded = " Got it. I've added this task:\n";

    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        
        Scanner sc = new Scanner(System.in);
        Task[] inputs = new Task[maxInput];
        int elements = 0;

        System.out.println(line + hello + line);

        while (true){
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + bye + line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line + listPrompt);
                for (int i = 0; i < elements; i++) {
                    int j = i + 1;
                    System.out.println(" " + j + ". " + inputs[i].toString());
                }
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int index = 0;
                try {
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                } catch (Exception e) {
                    System.out.println(line + "The correct usage of 'mark' is: mark n, where n is a natural number (ℕ)." + line);
                    continue;
                }

                try {
                    inputs[index].completeTask();
                } catch (Exception e) {
                    System.out.println(line + "I'm sorry, but this task doesn't exist.\n" + line);
                    continue;
                }
                System.out.println(line + completeTaskPrompt + inputs[index].toString() + "\n" + line); 
            } else if (input.startsWith("unmark")) {
                int index = 0;
                try {
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                } catch (Exception e) {
                    System.out.println(line + "To use 'unmark' correctly, you should format it as: unmark n, where n belongs to the set of natural numbers (ℕ)." + line);
                    continue;
                }

                try {
                    inputs[index].uncompleteTask();
                } catch (Exception e) {
                    System.out.println(line + "I'm sorry, but this task doesn't exist.\n" + line);
                    continue;
                }
                System.out.println(line + uncompleteTaskPrompt + inputs[index].toString() + "\n" + line); 
            } else if (input.startsWith("todo")) {
                try {
                    inputs[elements] = Task.toDo(input.split(" ", 2)[1]);
                } catch (Exception e) {
                    System.out.println(line + " OOPS!!! The description of a todo cannot be empty." + line);
                    continue;
                }

                elements++;
                System.out.println(line 
                + taskAdded 
                + " " + inputs[elements - 1].toString() + "\n" 
                + " Now you have" + elements + " tasks in the list.\n" 
                + line);
            } else if (input.startsWith("deadline")) {
                try {
                    int startOfDesc = input.indexOf(" ");
                    int endOfDesc = input.indexOf(" /by");

                    String description = input.substring(startOfDesc, endOfDesc).trim();
                    String dueBy = input.substring(endOfDesc + 4).trim();

                    inputs[elements] = Task.deadline(description, dueBy);
                } catch (Exception e) {
                    System.out.println(line + " OOPS!!! The description of a deadline cannot be empty." + line);
                    continue;
                }

                elements++;
                System.out.println(line 
                + taskAdded 
                + " " + inputs[elements - 1].toString() + "\n" 
                + " Now you have" + elements + " tasks in the list.\n" 
                + line);
            } else if (input.startsWith("event")) {
                try {
                    int startOfDesc = input.indexOf(" ");
                    int endOfDesc = input.indexOf(" /from");
                    int endOfFrom = input.indexOf(" /to");

                    String description = input.substring(startOfDesc, endOfDesc).trim();
                    String start = input.substring(endOfDesc + 6, endOfFrom).trim();
                    String end = input.substring(endOfFrom + 4).trim();

                    inputs[elements] = Task.event(description, start, end);
                } catch (Exception e) {
                System.out.println(line + " OOPS!!! The description of a event cannot be empty." + line);
                continue;
                }

                elements++;
                System.out.println(line 
                + taskAdded 
                + " " + inputs[elements - 1].toString() + "\n" 
                + " Now you have" + elements + " tasks in the list.\n" 
                + line);
            } else {
                System.out.println("placeholder text before level-5");
            }
        }
        sc.close();
    }
}
