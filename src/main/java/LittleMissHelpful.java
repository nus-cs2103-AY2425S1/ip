import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LittleMissHelpful {
    public static void main(String[] args) {
        String name = "Little Miss Helpful";
        String lineBreak = "___________________________________";
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
                String[] inputList = input.split(" ");
                String command = inputList[0];

                // Check if the user command is "bye" -> exit
                if (command.equalsIgnoreCase("bye")) {
                    System.out.println(exitLine);
                    System.out.println(lineBreak);
                    break; // Exit the loop
                }

                // Check if the user command is "list" -> print list
                if (command.equalsIgnoreCase("list")) {
                    System.out.println(lineBreak);
                    System.out.println("This is your current task list!");
                    for (int i = 0 ; i < counter ; i++) {
                        int listNumber = i + 1;
                        Task listItem = list[i];
                        System.out.println(listNumber + ". " + listItem.toString());
                    }
                    System.out.println(lineBreak);
                    continue;
                }

                    // Check if user command is "mark" / "unmark" -> mark / unmark task
                    if (command.equalsIgnoreCase("mark")) {
                        //mark task
                        try {
                            int listIndex = Integer.parseInt(inputList[1]) - 1;
                            Task curTask = list[listIndex];
                            list[listIndex] = curTask.markTask();
                            System.out.println(lineBreak);
                            System.out.println("Nice! I have marked this task as done: " + curTask.toString());
                            System.out.println(lineBreak);
                            continue;
                        } catch (Exception e) {
                            System.out.println(lineBreak);
                            System.out.println("Please retry with a valid task number.");
                            System.out.println(lineBreak);
                            continue;
                        }

                    } else if (command.equalsIgnoreCase("unmark")) {
                        //unmark task
                        try {
                            int listIndex = Integer.parseInt(inputList[1]) - 1;
                            Task curTask = list[listIndex];
                            list[listIndex] = curTask.unmarkTask();
                            System.out.println(lineBreak);
                            System.out.println("Ok, I have marked this task as not done yet: " + curTask.toString());
                            System.out.println(lineBreak);
                            continue;
                        } catch (Exception e) {
                            System.out.println(lineBreak);
                            System.out.println("Please retry with a valid task number.");
                            System.out.println(lineBreak);
                            continue;
                        }

                    }
                        // Adds commands to list
                        Task newTask = new Task(input);
                        list[counter] = newTask;
                        System.out.println(lineBreak);
                        System.out.println("Added to list: " + input);
                        System.out.println(lineBreak);
                        counter++; 
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        }
    }
}

