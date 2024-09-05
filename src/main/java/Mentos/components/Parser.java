package Mentos.components;

import Mentos.MentosException.MentosException;
import Mentos.task.Deadline;
import Mentos.task.Event;
import Mentos.task.Task;
import Mentos.task.ToDo;

import java.time.DateTimeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String MARKED = "mark";
    private final String UNMARKED = "unmark";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String DELETE = "delete";
    private final String FIND = "find";


    /**
     * Handles various task-related actions based on the input command.
     * <p>
     * This method processes user input, identifies the type of task action (e.g., list, mark, unmark, delete, todo, deadline, event, or find),
     * and returns an {@code ActionTaskIndexTuple} representing the action, task, and index (if applicable). It uses regular expressions
     * to parse commands and throws an exception for invalid input.
     *
     * @param input The user command string, which should start with a keyword such as "list", "mark", "unmark", "delete", "todo", "deadline", "event", or "find".
     * @return An {@code ActionTaskIndexTuple} containing the action type, task object (if applicable), and index (if applicable). Returns {@code null} if the input is not recognized.
     * @throws Exception If the input command is invalid or doesn't follow the expected format.
     */

    public ActionTaskIndexTuple taskHandler(String input) throws Exception {

        if (input.equals("list")) {
            return new ActionTaskIndexTuple("list", null, -1);
        } else if (input.startsWith(MARKED)) {
            Matcher match = regexHandler(input, "mark (\\d+)");
            if (match == null) {
                throw new MentosException("Invalid input!");
            }
            String extracted = match.group(1);
            int index = Integer.parseInt(extracted);
            return new ActionTaskIndexTuple(MARKED, null, index);

        } else if (input.startsWith(UNMARKED)) {
            Matcher match = regexHandler(input, "unmark (\\d+)");
            if (match == null) {
                throw new MentosException("Invalid input!");
            }
            String extracted = match.group(1);
            int index = Integer.parseInt(extracted);
            return new ActionTaskIndexTuple(UNMARKED, null, index);
        } else if (input.startsWith(DELETE)) {
            Matcher match = regexHandler(input, "delete (\\d+)$");
            if (match == null) {
                throw new MentosException("Invalid Delete input!");
            }
            String extracted = match.group(1);
            int index = Integer.parseInt(extracted);
            return new ActionTaskIndexTuple(DELETE, null, index);
        } else if (input.startsWith(TODO)) {
            Matcher match = regexHandler(input, "todo (.+)");
            if (match == null) {
                throw new MentosException("Todo cannot be empty!");
            }
            String extracted = match.group(1);

            Task newTodo = new ToDo(extracted);
            return new ActionTaskIndexTuple(TODO, newTodo, -1);
        } else if (input.startsWith(DEADLINE)) {
            Matcher match = regexHandler(input, "deadline (.+) \\/by (\\d{4}-\\d{2}-\\d{2} \\d{4})$");
            if (match == null) {
                throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime> in yyyy-mm-dd hhmm");
            }
            String deadline_desc = match.group(1);
            String by = match.group(2);
            Task newDeadline = new Deadline(deadline_desc, by);
            return new ActionTaskIndexTuple(DEADLINE, newDeadline, -1);
        } else if (input.startsWith(EVENT)) {
            Matcher match = regexHandler(input, "event (.+) \\/from (\\d{4}-\\d{2}-\\d{2} \\d{4}) \\/to " +
                    "(\\d{4}-\\d{2}-\\d{2} \\d{4})$");
            if (match == null) {
                throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> " +
                        "yyyy-mm-dd hhmm /to <datetime> yyyy-mm-dd hhmm");
            }

            String eventDesc = match.group(1);
            String from = match.group(2);
            String to = match.group(3);
            Task newEvent = new Event(eventDesc, from, to);
            return new ActionTaskIndexTuple(EVENT, newEvent, -1);

        } else if (input.startsWith(FIND)) {
            Matcher match = regexHandler(input, "find (\\w+)");
            if (match == null) {
                throw new MentosException("Invalid find");
            }
            String keyword = match.group(1);
            Task findKeyword = new ToDo(keyword);
            return new ActionTaskIndexTuple(FIND, findKeyword, -1);
        } else {
            System.out.println("____________________________");
            System.out.println("Sorry me no understand");
            System.out.println("____________________________");
            return null;
        }
    }


    /**
     * Handles regular expression matching on a given input string.
     * This method compiles the provided regular expression (regex) and
     * attempts to find a match within the input string. If a match is found,
     * it returns the `Matcher` object, allowing further operations like
     * extracting matched groups. If no match is found, it returns `null`.
     *
     * @param input the string to be matched against the regular expression.
     * @param regex the regular expression used for matching.
     * @return `Matcher` object if a match is found; `null` otherwise.
     */
    public Matcher regexHandler(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher;
        }
        return null;
    }

}
