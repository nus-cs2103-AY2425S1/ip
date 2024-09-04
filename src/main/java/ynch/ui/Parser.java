/**
 * Parses String input from the user and returns ValidCommand
 *
 */

import java.util.Scanner;

class Parser {

    ValidCommand processInput(String userInput) {
        String commandKeyword = userInput.split(" ")[0];
        switch (commandKeyword) {
            case "list":
                return ValidCommand.list;
            case "mark":
                return ValidCommand.mark;
            case "unmark":
                return ValidCommand.unmark;
            case "todo":
                return ValidCommand.todo;
            case "deadline":
                return ValidCommand.deadline;
            case "event":
                return ValidCommand.event;
            case "delete":
                return ValidCommand.delete;       
        }
        return ValidCommand.list;
    }

/**
        if (userInput.equals("list")) {
            System.out.println(this.list());
        } else if (userInput.startsWith("mark")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.mark(i));
        } else if (userInput.startsWith("unmark")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.unmark(i));
        } else if (userInput.startsWith("todo")) {
            System.out.println(this.addTodo(userInput.split(" ", 2)[1]));
        } else if (userInput.startsWith("deadline")) {
            userInput = userInput.split(" ", 2)[1];
            String task = userInput.split("/by")[0];
            String deadline = userInput.split("/by")[1];
            System.out.println(this.addDeadline(task, deadline));
        } else if (userInput.startsWith("event")) {
            userInput = userInput.split(" ", 2)[1];
            String task = userInput.split("/from")[0];
            String fromAndTo = userInput.split("/from")[1];
            String from = fromAndTo.split("/to")[0];
            String to = fromAndTo.split("/to")[1];
            System.out.println(this.addEvent(task, from, to));
        } else if (userInput.startsWith("delete")) {
            int i = Integer.valueOf(userInput.split(" ")[1]);
            System.out.println(this.delete(i));
        }
    }
*/
}
