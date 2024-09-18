package Parser;

import Commands.*;

public class Parser {

    public Parser() {
    }

    /**
     * Makes sense of input and creates relevant objects
     * if user input does not match print deafult message
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
            return new DefaultCommand(" OOPS!!! Sorry leh, but IDK what that means :-\n" +
                    "\nThese are the list of commands you can use:)\n" +
                    "\n- todo [desc] (to add a todo task)\n" +
                    "- deadline [desc] /by [yyyy-MM-dd or dd/MM/yyy 16:00] (to add a deadline task)\n" +
                    "- event [desc] /from [16:00] /to [18:00] /on [yyyy-MM-dd or dd/MM/yyyy] (to add an event)\n" +
                    "- mark [index num] (to mark task as completed)\n" +
                    "- unmark [index num] (to unmark completed task)\n" +
                    "- delete [index num] (to delete a task)\n" +
                    "- list (to view all tasks)\n" +
                    "- find [keywords] (to find tasks with keyword)\n" +
                    "- bye\n" +
                    "- hi\n" +
                    "- due [yyyy-MM-dd or dd/MM/yyyy] (find tasks due on date)\n" +
                    "- update <num> <desc, date, startTime, endTime, time> /to <newValue> (to update task info)\n");
        }
    }
}
