package Parser;

import Commands.*;

public class Parser {

    public Parser() {
    }

    /**
     * Makes sense of input and creates relevant objects
     * if userinput does not match print deafult message
     *
     * @param userInput string input of user
     *
     */
    public static Command makeSenseOfUserInput(String userInput) {

        // Split the string by spaces
        String[] words = userInput.split(" ");

        //if user inputs mark, mark task and update file
        if (words[0].equals("mark")) {
            return new MarkCommand(userInput);
        }

        //if user inputs unmark, unmark task and update file
        else if (words[0].equals("unmark")) {
            return new UnmarkCommand(userInput);
        }

        //if user inputs list, display tasks and update file
        else if (userInput.equals("list")) {
            return new ListCommand(userInput);
        }

        //if user inputs delete, delete task and update file
        else if (words[0].equals("delete")) {
            return new DeleteCommand(userInput);
        }

        //if user inputs to do, add item of type to do and update file
        else if (words[0].equals("todo")) {
            return new ToDoCommand(userInput);

            //if user inputs deadline, add item of type deadline and update file
        } else if (words[0].equals("deadline")) {
            return new DeadlineCommand(userInput);

            //if user inputs event, add item of type event and update file
        } else if (words[0].equals("event")) {
            return new EventCommand(userInput);

            //if user inputs due, list of tasks due on date is given
        } else if (words[0].equals("due")) {
            return new DueCommand(userInput);

            //if user inputs bye
        } else if (words[0].equals("bye")) {
            return new ExitCommand(userInput);

            //if user inputs find
        } else if (words[0].equals("find")) {
            return new FindCommand(userInput);

            //if user inputs hi
        } else if (words[0].equals("hi")) {
            return new IntroCommand(userInput);

            //if user inputs update
        } else if (words[0].equals("update")) {
            return new UpdateCommand(userInput);

        } else {
            return new DefaultCommand("     OOPS!!! Sorry leh, but IDK what that means :-");
        }
    }
}
