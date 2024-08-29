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




    public ActionTaskIndexTuple taskHandler(String input) {
        /*
         * Handles user input commands related to Mentos.task management.
         *
         * This method processes various Mentos.task-related commands such as listing tasks,
         * marking tasks as done, unmarking tasks, deleting tasks, and adding new tasks
         * (todo, deadline, event). It uses regular expressions to parse and validate
         * the input commands, and throws custom exceptions (`Mentos.MentosException.MentosException`)
         * when inputs are invalid.
         *
         * The following commands are supported:
         *   list - Lists all the current tasks.
         *   mark X- Marks the Mentos.task at index X as done.
         *   unmark X - Unmarks the Mentos.task at index X (marks it as not done).
         *   delete X< - Deletes the Mentos.task at index X.
         *   todo DESCRIPTION - Adds a new Mentos.task.ToDo Mentos.task with the given description.
         *   deadline DESCRIPTION /by DATETIME - Adds a new Mentos.task.Deadline Mentos.task with the given description and due date.
         *   event DESCRIPTION /from DATETIME /to DATETIME - Adds a new Mentos.task.Event Mentos.task with the given description and time range.
         *
         * If an unrecognized command is given, the method responds with a message indicating
         * that the command is not understood.
         *
         * @param input the user's input command as a string.
         * @throws Mentos.MentosException.MentosException if the input is invalid or if the specified Mentos.task index does not exist.
         */
        try {

            if (input.equals("list")) {
                return new ActionTaskIndexTuple("list",null,-1);
            }  else if (input.startsWith(MARKED)) {
                Matcher match = regexHandler(input, "mark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                return new ActionTaskIndexTuple(MARKED,null,index);

            } else if (input.startsWith(UNMARKED)) {
                Matcher match = regexHandler(input, "unmark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                return new ActionTaskIndexTuple(UNMARKED,null,index);
            } else if (input.startsWith(DELETE)){
                Matcher match = regexHandler(input,"delete (\\d+)$");
                if (match == null){
                    throw new MentosException("Invalid Delete input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                return new ActionTaskIndexTuple(DELETE,null,index);
            }
            else if (input.startsWith(TODO)) {
                Matcher match = regexHandler(input, "todo (.+)");
                if (match == null) {
                    throw new MentosException("Todo cannot be empty!");
                }
                String extracted = match.group(1);

                Task newTodo = new ToDo(extracted);
                return new ActionTaskIndexTuple(TODO,newTodo,-1);
            } else if (input.startsWith(DEADLINE)){
                Matcher match = regexHandler(input,"deadline (.+) \\/by (\\d{4}-\\d{2}-\\d{2} \\d{4})$");
                if (match == null){
                    throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime> in yyyy-mm-dd hhmm");
                }
                String deadline_desc = match.group(1);
                String by = match.group(2);
                Task newDeadline = new Deadline(deadline_desc,by);
                return new ActionTaskIndexTuple(DEADLINE,newDeadline,-1);
            } else if (input.startsWith(EVENT)){
                Matcher match = regexHandler(input,"event (.+) \\/from (\\d{4}-\\d{2}-\\d{2} \\d{4}) \\/to (\\d{4}-\\d{2}-\\d{2} \\d{4})$");
                if (match == null){
                    throw new MentosException("Invalid Mentos.task.Event input! usage:event <desc> /from <datetime> yyyy-mm-dd hhmm /to <datetime> yyyy-mm-dd hhmm" );
                }

                String eventDesc = match.group(1);
                String from = match.group(2);
                String to = match.group(3);
                Task newEvent = new Event(eventDesc,from,to);
                return new ActionTaskIndexTuple(EVENT,newEvent,-1);

            }

            else {
                System.out.println("____________________________");
                System.out.println("Sorry me no understand");
                System.out.println("____________________________");
                return null;
            }
        } catch (MentosException err){
            System.out.println(err);
        } catch (DateTimeException e){
            System.out.println("Invalid Datetime inputted");
        }
        return null;
    }

    public Matcher regexHandler(String input,String regex){
        /*
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
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher;
        }
        return null;
    }

}
