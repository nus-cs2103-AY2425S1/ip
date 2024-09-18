package kira;

import exceptions.EmptyException;
import exceptions.InvalidTaskException;
import exceptions.UnreadableException;
import tasks.*;

import java.util.Objects;

public class Parser {
    //private Kira kira;
    private List list;
    public enum CommandType {
        LIST, FIND, MARK, UNMARK, DELETE, BYE, EVENT, DEADLINE, TODO, PRIORITISE, UNPRIORITISE;
        }
    public Parser(List list) {
        //this.kira = kira;
        this.list = list;
    }

    /**
     * Returns the command type
     *
     * @param userInput User's input
     * @return command type
     */
    public CommandType intepreteCommand(String userInput) throws UnreadableException {
        if (userInput.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        }

        String[] strings = userInput.split("\\s+", 2);
        String firstWord = strings[0];

        if (Objects.equals(firstWord, "mark")) {
            return CommandType.MARK;
        } else if (Objects.equals(firstWord, "unmark")) {
            return CommandType.UNMARK;
        } else if (Objects.equals(firstWord, "todo")) {
            return CommandType.TODO;
        } else if (Objects.equals(firstWord, "deadline")) {
            return CommandType.DEADLINE;
        } else if (Objects.equals(firstWord, "event")) {
            return CommandType.EVENT;
        } else if (Objects.equals(firstWord, "delete")) {
            return CommandType.DELETE;
        } else if (Objects.equals(firstWord, "find")) {
            return CommandType.FIND;
        } else if (Objects.equals(firstWord, "bye")) {
            return CommandType.BYE;
        } else if (Objects.equals(firstWord, "prioritise")) {
            return CommandType.PRIORITISE;
        } else if (Objects.equals(firstWord, "unprioritise")) {
            return CommandType.UNPRIORITISE;
        } else {
            throw new UnreadableException();
        }
    }

    /**
     * Executes the command given by user
     *
     * @param command Command type
     * @param userInput User's input
     */
    public Task execute(CommandType command, String userInput) throws EmptyException, InvalidTaskException, UnreadableException {

        if (command == CommandType.LIST || command == CommandType.BYE) {
            return null;
        }

        String[] strings = userInput.split("\\s+", 2);

        if (strings.length < 2) {
            throw new EmptyException(command.toString().toLowerCase());
        }

        String detail = strings[1];

        switch (command) {
            case MARK -> {
                int index = Integer.parseInt(detail) - 1;
                Task task = list.getTask(index);
                task.markAsDone();
                return task;
            }
            case UNMARK -> {
                int index = Integer.parseInt(detail) - 1;
                Task task = list.getTask(index);
                task.markAsUndone();
                return task;
            }
            case TODO -> {
                Task task = new ToDo(detail);
                list.addTaskToList(task);
                return task;
            }
            case DEADLINE -> {
                String name = Deadline.extractName(detail);
                String deadline = Deadline.extractDate(detail);
                Task task = new Deadline(name, deadline);
                list.addTaskToList(task);
                return task;
            }
            case EVENT -> {
                String name = Event.extractName(detail);
                String start = Event.extractDate(detail, true);
                String end = Event.extractDate(detail, false);
                Task task = new Event(name, start, end);
                list.addTaskToList(task);
                return task;
            }
            case DELETE -> {
                int index = Integer.parseInt(detail) - 1;
                return list.deleteTask(index);
            }
            case FIND -> {
                return null;
            }
            case PRIORITISE -> {
                int index = Integer.parseInt(detail) - 1;
                Task task = list.getTask(index);
                task.setPriority(true);
                return task;
            }
            case UNPRIORITISE -> {
                int index = Integer.parseInt(detail) - 1;
                Task task = list.getTask(index);
                task.setPriority(false);
                return task;
            }
            default -> {
                throw new UnreadableException();
            }
        }
    }

    /**
     * Returns the response given by Kira corresponding to user input
     *
     * @param command Command type
     * @param detail User's input
     * @return Response by Kira
     */
    public String getResponse(CommandType command, String detail, Task task) {
        switch (command) {
            case LIST -> {
                String prefix = "Here are the tasks in your list:\n";
                return prefix + list.displayList();
            }
            case MARK -> {
                String prefix = "Nice! I've marked this task as done:\n";
                return prefix + task.displayTask();
            }
            case UNMARK -> {
                String prefix = "OK, I've marked this task as not done yet:\n";
                return prefix + task.displayTask();
            }
            case TODO, DEADLINE, EVENT -> {
                String prefix = "Got it. I've added this task:\n";
                String displayNumOfTasks;
                if (list.numOfTasks() < 2) {
                    displayNumOfTasks = "Now you have " + list.numOfTasks() + " task in the list.\n";
                } else {
                    displayNumOfTasks = "Now you have " + list.numOfTasks() + " tasks in the list.\n";
                }
                return prefix + task.displayTask() + displayNumOfTasks;
            }
            case DELETE -> {
                String prefix = "Noted. I've removed this task:\n";
                String displayNumOfTasks;
                if (list.numOfTasks() < 2) {
                    displayNumOfTasks = "Now you have " + list.numOfTasks() + " task in the list.\n";
                } else {
                    displayNumOfTasks = "Now you have " + list.numOfTasks() + " tasks in the list.\n";
                }
                return prefix + task.displayTask() + displayNumOfTasks;
            }
            case FIND -> {
                String[] strings = detail.split("\\s+", 2);
                String keyWord = strings[1];
                List filteredList = list.filterByKeyword(keyWord);
                String prefix = "Here are the matching tasks in your list:\n";
                return prefix + filteredList.displayList();
            }
            case BYE -> {
                return "Bye. Hope to see you again soon!\n";
            }
            case PRIORITISE -> {
                String prefix = "Gotchu, this is now a priority:\n";
                return prefix + task.displayTask();
            }
            case UNPRIORITISE -> {
                String prefix = "OK, this is no longer a priority:\n";
                return prefix + task.displayTask();
            }
        }
        return "";
    }
}
