import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {  
        String logo = "     _  ____  ___   _  ____  _      \n"
                    + "    | || ___|| _ \\ | || ___|| |     \n"
                    + "    | |||___ ||_> || |||___ | |     \n"
                    + " _  | || ___||  _/ | || ___|| |     \n"
                    + "| |_| |||___ ||\\\\  | |||___ | |____ \n"
                    + " \\___/ |____||| \\\\ |_||____||______|\n";
        System.out.println("Hello from\n" + logo);

        // System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jeriel");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        Task[] tasks = new Task[100];
        int taskCount = 0;


        while (true) {
            input = scanner.nextLine(); 
            // System.out.println("____________________________________________________________");
            // System.out.println(" " + input);
            // System.out.println("____________________________________________________________");

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");

                    break;

                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");


                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    tasks[taskNumber].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[taskNumber]);
                    System.out.println("____________________________________________________________");


                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    tasks[taskNumber].markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[taskNumber]);
                    System.out.println("____________________________________________________________");


                } else if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new DukeException("The description and due date of a deadline cannot be empty.");
                    }
                    String description = parts[0];
                    String by = parts[1];
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1]);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                
                // } else {
                //     // tasks[taskCount] = new Task(input);
                //     // taskCount++;
                //     System.out.println("____________________________________________________________");
                //     System.out.println(" added: " + input);
                //     System.out.println("____________________________________________________________");

                // 
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Something went wrong: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

}
