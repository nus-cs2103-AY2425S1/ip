import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LittleMissHelpful {
    public static void main(String[] args) {
        String name = "Little Miss Helpful";
        String lineBreak = "---------------------------------";
        String greeting = "Hello! I'm " + name + ".\nWhat can I do for you?";
        String exitLine = "Bye. Hope to see you again soon!";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Print greeting
        System.out.println(lineBreak);
        System.out.println(greeting);
        System.out.println(lineBreak);

        Task[] list = new Task[100];

        try {
            int counter = 0;
            while (true) { //there is continuous input
                String input = br.readLine();

                // Split into command and description/arguments
                String[] inputList = input.split(" ", 2);
                String command = inputList[0];

                if (inputList.length < 2) {
                    // Check if the user command is "bye" -> exit
                    if (command.equalsIgnoreCase("bye")) {
                        System.out.println(lineBreak);
                        System.out.println(exitLine);
                        System.out.println(lineBreak);
                        break; // Exit the loop
                    }

                    // Check if the user command is "list" -> print list
                    if (command.equalsIgnoreCase("list")) {
                        System.out.println(lineBreak);

                        if (counter == 0) {
                            System.out.println("No tasks in list at the moment.");
                        } else {
                            System.out.println("This is your current task list!");
                            for (int i = 0 ; i < counter ; i++) {
                                int listNumber = i + 1;
                                Task listItem = list[i];
                                System.out.println(listNumber + ". " + listItem.toString());
                            }
                        }
                        System.out.println(lineBreak);
                        continue;
                    }

                    //Invalid command --> print warning
                    System.out.println(lineBreak);
                    System.out.println("Invalid input format. Please provide a valid command.");
                    System.out.println(lineBreak);
                    continue;
                }

                String item = inputList[1];

                // Check if user command is "mark" / "unmark" -> mark / unmark task
                if (command.equalsIgnoreCase("mark")) {
                    //mark task
                    try {
                        int listIndex = Integer.parseInt(inputList[1]) - 1;
                        Task curTask = list[listIndex];
                        list[listIndex] = curTask.markTask();
                        System.out.println(lineBreak);
                        System.out.println("Nice! You have marked this task as done: " + curTask.toString());
                        System.out.println(lineBreak);
                    } catch (Exception e) {
                        System.out.println(lineBreak);
                        System.out.println("Please retry with a valid task number.");
                        System.out.println(lineBreak);
                    }
                    continue;

                } else if (command.equalsIgnoreCase("unmark")) {
                    //unmark task
                    try {
                        int listIndex = Integer.parseInt(inputList[1]) - 1;
                        Task curTask = list[listIndex];
                        list[listIndex] = curTask.unmarkTask();
                        System.out.println(lineBreak);
                        System.out.println("Ok, You have marked this task as not done yet: " + curTask.toString());
                        System.out.println(lineBreak);
                    } catch (Exception e) {
                        System.out.println(lineBreak);
                        System.out.println("Please retry with a valid task number.");
                        System.out.println(lineBreak);
                    }
                    continue;
                }
                    
                // Adds tasks to list
                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    Task newTask = null;

                    if (command.equalsIgnoreCase("todo")) {
                        newTask = new Todo(item);

                    } else if (command.equalsIgnoreCase("deadline")) {
                        String[] itemList = item.split("/by", 2);
                        String desc = itemList[0].trim();
                        String by = itemList[1].trim();

                        newTask = new Deadline(desc, by);

                    } else if (command.equalsIgnoreCase("event")) {
                        String[] itemList = item.split("/from | /to", 3);
                        String desc = itemList[0].trim();
                        String from = itemList[1].trim();
                        String to = itemList[2].trim();

                        newTask = new Event(desc, from, to);
                    }
                    
                    list[counter] = newTask;
                    System.out.println(lineBreak);
                    System.out.println("Added to list: " + newTask.toString());
                    System.out.println("Now you have " + (counter + 1) + " tasks in your list.");
                    System.out.println(lineBreak);
                    counter++; 
                } else {
                    System.out.println(lineBreak);
                    System.out.println("Invalid input format. Please provide a valid command.");
                    System.out.println(lineBreak);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        }
    }
}

