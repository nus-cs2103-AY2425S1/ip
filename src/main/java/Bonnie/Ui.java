package Bonnie;

import java.util.Scanner;

public class Ui {
    protected static String username;

    public Ui () {
    }

    /**
     * Initializes the chat bot and starts the conversation with the user.
     * Prompts the user for their name and provides a list of available commands.
     * Continuously prompts the user for input and calls the Parser to process the input.
     *
     * @throws DeadlineFormatException     if the format of the deadline task is incorrect
     * @throws EmptyTodoException          if the body of a todo task is empty
     * @throws UnknownCommandException     if the input command is unknown
     */
    protected static void init() throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        System.out.println("Hello I'm Bonnie, what is your name?");

        Scanner scannerObj = new Scanner(System.in);
        String myUsername = scannerObj.nextLine();
        System.out.println(String.format(
            "Hey %s! Welcome to the Bonnie chat bot! Please input a command to continue!\n" +
                    "1. bye : leaves the conversation with Bonnie\n" +
                    "2. list: displays the list of tasks that you have, with the task number\n" +
                    "3. mark/unmark {task number}: marks or unmarks that task as done\n" +
                    "4. todo {task name}: Bonnie adds a todo task into your task list\n" +
                    "5. deadline {task name} /by {YYYY-MM-DD}: Bonnie adds a task with a deadline to your task list.\n" +
                    "6. event {task name} /from {start} /to {end}: Bonnie adds an event with a start/end time to your task list.\n" +
                    "7. delete {task number}: Bonnie deletes the task with that number from your task list.\n" +
                    "8. find {keyword}: Bonnie find all tasks containing that keyword from your task list.\n" +
                    "Bonnie wants to remind you that you should substitute items with curly braces with the actual information.\n" +
                    "Also, do remember to use the forward slashes! \"/from\" is valid but \"from\" is NOT valid!\n" +
                    "Example: \"event clean floor /from 18th September 5pm /to 18th September 6pm\" is a valid command\n", myUsername));
        username = myUsername;

        // Ui needs to continuously call Parser to parse tasks
        while (true) {
            String input = scannerObj.nextLine();
            Parser.parseInput(input);
        }
    }

    protected static void generateMessage(String message) {
        System.out.println(message);
    }
}
