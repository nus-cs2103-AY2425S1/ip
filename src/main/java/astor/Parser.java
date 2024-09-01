package astor;

import astor.command.*;
import astor.exception.*;

/**
 * Parses user input and categorises the command to take.
 */
public class Parser {

    /**
     * The list of the available commands.
     */
    private enum Action {
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        EMPTY,
        DEFAULT
    }

    public Parser() {
    }

    /**
     * Scans the user input for the first keyword to deduce the command given.
     *
     * @param input the user input string to categorise
     * @return the action categorised from the input
     */
    private static Action categorise(String input) {
        if (input.equals("bye")) {
            return Action.BYE;
        } else if (input.startsWith("mark")) {
            return Action.MARK;
        } else if (input.startsWith("unmark")) {
            return Action.UNMARK;
        } else if (input.equals("list")) {
            return Action.LIST;
        } else if (input.startsWith("todo")) {
            return Action.TODO;
        } else if (input.startsWith("deadline")) {
            return Action.DEADLINE;
        } else if (input.startsWith("event")) {
            return Action.EVENT;
        } else if (input.startsWith("delete")) {
            return Action.DELETE;
        } else if (input.isEmpty()) {
            return Action.EMPTY;
        }
        return Action.DEFAULT;
    }

    /**
     * Processes the user input and returns the corresponding Command object.
     *
     * @param input the user input string to process
     * @return the Command object corresponding to the input
     * @throws AstorException if the input is invalid or results in an error
     * @throws EmptyInputException if the input is empty
     * @throws UnspecificTaskException if the input does not match any known command
     */
    public static Command process(String input) throws AstorException {
        Action action = categorise(input);
        switch (action) {
        case MARK:
            return new MarkCommand(input);
        case UNMARK:
            return new UnmarkCommand(input);
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(input);
        case TODO:
            return new TodoCommand(input);
        case DEADLINE:
            return new DeadlineCommand(input);
        case EVENT:
            return new EventCommand(input);
        case EMPTY:
            throw new EmptyInputException();
        case BYE:
            return new ExitCommand();
        case DEFAULT:
            throw new UnspecificTaskException();
        }
        return null;
    }


        /*

        switch (action) {
        case BYE:
            return "Bye. Hope to see you again!";
        case LIST:
            String reply = "Here are the tasks in your list:";
            int indexA = 1;
            reply += taskList.getTaskList();
            return reply;
        case MARK:
            int indexB;
            try {
                String formattedString = input.substring(4).trim();
                indexB = Integer.parseInt(formattedString);
            } catch (NumberFormatException e) {
                throw new MarkingTaskNotANumberException();
            }

            if (indexB >= 1 && indexB <= listOfTasks.size()) {
                astor.task.Task astor.task = listOfTasks.get(indexB - 1);
                if (astor.task.isDone()) {
                    return "This astor.task is already done:\n" +
                            astor.task;
                } else {
                    astor.task.markDone();
                    storage.updateData(listOfTasks);
                    return "Nice! I've marked this astor.task as done:\n" +
                            astor.task;
                }
            } else {
                throw new MarkTaskOutOfRangeException(listOfTasks.size());
            }
        case UNMARK:
            int index = -1;
            try {
                String formattedString = input.substring(6).trim();
                index = Integer.parseInt(formattedString);
            } catch (NumberFormatException e) {
                throw new MarkingTaskNotANumberException();
            }

            if (index >= 1 && index <= listOfTasks.size()) {
                astor.task.Task astor.task = listOfTasks.get(index - 1);
                if (astor.task.isDone()) {
                    astor.task.markUndone();
                    this.storage.updateData(listOfTasks);
                    return "OK, I've marked this astor.task as not done yet:\n" +
                            astor.task;
                } else {
                    return "This astor.task is already marked as uncompleted:\n" +
                            astor.task;
                }
            } else {
                throw new MarkTaskOutOfRangeException(listOfTasks.size());
            }
        case EMPTY:
            throw new EmptyInputException();
        case TODO:
            String s1 = input.substring(4).trim();
            if (s1.isEmpty()) {
                throw new EmptyTaskInfoException();
            } else {
                astor.task.Task astor.task = new astor.task.Todo(s1);
                try {
                    storage.appendToFile(astor.task.dataDescription());
                    listOfTasks.add(astor.task);
                    return "Got it. I've added this astor.task:\n  " +
                            astor.task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                }
            }
        case DEADLINE:
            String s = input.substring(8).trim();
            if (s.isEmpty()) {
                throw new EmptyTaskInfoException();
            } else {
                String[] stringArr = s.split("/by");
                if (stringArr.length != 2) {
                    throw new EmptyDeadlineException();
                }
                astor.task.Task astor.task = new astor.task.Deadline(stringArr[0].trim(), stringArr[1].trim());
                try {
                    storage.appendToFile(astor.task.dataDescription());
                    listOfTasks.add(astor.task);
                    return "Got it. I've added this astor.task:\n  " +
                            astor.task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                }
            }
        case EVENT:
            String s2 = input.substring(5).trim();
            if (s2.isEmpty()) {
                throw new EmptyTaskInfoException();
            } else {
                String[] stringArr = s2.split("/from");
                if (stringArr.length != 2) {
                    throw new EmptyTimeException();
                }
                String[] stringArr2 = stringArr[1].split("/to");
                if (stringArr2.length != 2) {
                    throw new EmptyTimeException();
                }
                astor.task.Task astor.task = new astor.task.Event(stringArr[0].trim(), stringArr2[0].trim(), stringArr2[1].trim());
                try {
                    storage.appendToFile(astor.task.dataDescription());
                    listOfTasks.add(astor.task);
                    return "Got it. I've added this astor.task:\n  " +
                            astor.task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                }
            }
        case DELETE:
            int indexC = -1;
            if (listOfTasks.isEmpty()) {
                throw DeleteTaskOutOfRangeException.noTaskToDelete();
            }
            try {
                String formattedString = input.substring(6).trim();
                indexC = Integer.parseInt(formattedString);
            } catch (NumberFormatException e) {
                throw new DeleteTaskNumberException();
            }
            if (indexC >= 1 && indexC <= listOfTasks.size()) {
                astor.task.Task astor.task = listOfTasks.remove(indexC - 1);
                storage.updateData(listOfTasks);
                return "Noted. I've removed this astor.task:\n  " +
                        astor.task + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
            } else {
                throw DeleteTaskOutOfRangeException.outOfRangeTaskToDelete(listOfTasks.size());
            }
        case DEFAULT:
            throw new UnspecificTaskException();
        }
        return "";

         */

}
