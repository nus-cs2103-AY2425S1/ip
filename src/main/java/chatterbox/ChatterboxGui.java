package chatterbox;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import chatterboxexceptions.ChatterboxExceptions;
import gui.ChatterboxResponses;
import parser.Parser;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * main class that encapsulates all chatbot functionality
 */
public class ChatterboxGui {
    private final ChatterboxResponses guiResponses;
    private final Parser parser;
    private final Storage storage;

    private final TaskList tasks;

    /**
     * initiates Chatterbox with a prior history filepath
     * @param filepath contains the history of tasks
     */
    public ChatterboxGui(String filepath) {
        this.guiResponses = new ChatterboxResponses();
        this.parser = new Parser();
        this.storage = new Storage(filepath);
        ArrayList<Task> loaded = new ArrayList<>();
        try {
            loaded = storage.load(parser);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No history file found at path");
        }


        this.tasks = new TaskList(loaded);


    }

    /**
     * Initiates Chatterbox with no prior history
     */
    public ChatterboxGui() {
        this.guiResponses = new ChatterboxResponses();

        this.parser = new Parser();
        this.storage = new Storage();


        this.tasks = new TaskList(new ArrayList<Task>());

    }



    /**
     * Class UI used to handle the printing and formatting of text in the UI
     */

    protected static class TaskList {
        private final ArrayList<Task> userTasks;

        public TaskList(ArrayList <Task> userTasks) {
            this.userTasks = userTasks;
        }

        /**
         * gets the userTasks of TaskList object
         * @return an ArrayList userTasks
         */
        public ArrayList<Task> getTasks() {
            return userTasks;
        }


        /**
         * Marks task at index to be complete
         * @param index of task to be marked complete
         * @return returns the task that was marked
         */
        public Task markTask(int index) {
            userTasks.get(index).setStatus(true);
            return userTasks.get(index);
        }

        /**
         * Marks task at index to be not complete
         * @param index to be mark incomplete
         * @return the task that was unmarked
         */
        public Task unmarkTask(int index) {
            userTasks.get(index).setStatus(false);
            return userTasks.get(index);
        }

        public Todo addTodo(String desc) throws ChatterboxExceptions.ChatterBoxNoInput {
            Todo nextTodo = new Todo(desc);
            userTasks.add(nextTodo);
            return nextTodo;
        }

        public Deadline addDeadline(String desc, String endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
            Deadline nextDead = new Deadline(desc, endDate);
            userTasks.add(nextDead);
            return nextDead;
        }

        public Deadline addDeadline(String desc, LocalDateTime endDate) throws ChatterboxExceptions.ChatterBoxNoInput {
            Deadline nextDead = new Deadline(desc, endDate);
            userTasks.add(nextDead);
            return nextDead;
        }

        public Event addEvent(String desc, String startDate, String endDate)
                throws ChatterboxExceptions.ChatterBoxNoInput {
            Event nextEve = new Event(desc, startDate, endDate);
            userTasks.add(nextEve);
            return nextEve;
        }

        /**
         * Adds an Event to the Tasklist
         * @param desc description of event
         * @param startDate start date of event
         * @param endDate end date of event
         * @return the created event
         * @throws ChatterboxExceptions.ChatterBoxNoInput if no description was found
         */
        public Event addEvent(String desc, LocalDateTime startDate, LocalDateTime endDate)
                throws ChatterboxExceptions.ChatterBoxNoInput {
            Event nextEve = new Event(desc, startDate, endDate);
            userTasks.add(nextEve);
            return nextEve;
        }

        /**
         * Gets the task at index
         * @param index
         * @return Task at index
         */
        public Task getTask(int index) {
            return userTasks.get(index);
        }

        /**
         * Deletes task and index and returns it
         * @param index of task to be deleted
         * @return delted Task
         */
        public Task deleteTask(int index) {
            return userTasks.remove(index);
        }

        /**
         * returns the description of a task
         * @param index
         * @return
         */
        public String getTaskDescription(int index) {
            return userTasks.get(index).getDescription();
        }

        /**
         * Get size of task list
         * @return size of task list
         */
        public int size() {
            return userTasks.size();
        }

        /**
         * returns an ArrayList
         * @param keywords is a string of keywords that should appear
         * @return ArrayList with only tasks that have the keywords
         */
        public ArrayList<Task> findTasks(String keywords) {
            return userTasks.stream()
                    .filter(task -> task.getDescription().contains(keywords))
                    .collect(Collectors.toCollection(ArrayList::new));

        }

    }

    /**
     * Process input
     * @param input userinput from gui
     * @return Chatterbox response to the input
     */
    public String processInput(String input) {



        try {
            Parser.ValidCommand command = parser.parseCommand(input);
            System.out.println(command);


            int index;
            switch (command) {

            case BYE:
                return null;

            case LIST:

                return guiResponses.displayList(tasks.getTasks());

            case MARK:
                input = input.trim();
                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
                return guiResponses.markMsg(tasks.markTask(index));


            case UNMARK:
                input = input.trim();
                index = parser.extractNum(input) - 1; // -1 as the display  start from 1
                return guiResponses.unmarkMsg(tasks.unmarkTask(index));


            case TODO:


                tasks.addTodo(parser.parseTodo(input.trim()));
                return guiResponses.addTaskMsg("Todo", tasks.size());

            case DEADLINE:
                String[] parsed = parser.parseDeadline(input);


                LocalDateTime deadlineDate = parser.parseDateTime(parsed[1].substring(2));


                if (deadlineDate == null) {

                    tasks.addDeadline(parsed[0], parsed[1]);

                } else {
                    //add back by for string

                    tasks.addDeadline(parsed[0], deadlineDate);
                }

                return guiResponses.addTaskMsg("Deadline", tasks.size());


            case EVENT:
                String[] eventParsed = parser.parseEvent(input);

                LocalDateTime startDate = parser.parseDateTime(eventParsed[1].substring(4)); //from 4
                LocalDateTime endDate = parser.parseDateTime(eventParsed[2].substring(2));

                if (startDate == null || endDate == null) {

                    tasks.addEvent(eventParsed[0].trim(), eventParsed[1], eventParsed[2]);

                } else {
                    tasks.addEvent(eventParsed[0].trim(), startDate, endDate);
                }
                return guiResponses.addTaskMsg("Event", tasks.size());

            case DELETE:
                input = input.trim();
                int delIndex = parser.extractNum(input) - 1;
                return guiResponses.delTaskMsg(tasks.deleteTask(delIndex), tasks.size());

            case FIND:
                String keywords = parser.parseFind(input).trim();

                ArrayList<Task> matches = tasks.findTasks(keywords);
                return guiResponses.getSearchList(matches);


            default:
                ChatterboxExceptions.checkMessage(input);

                break;

            }
            storage.saveHistory(tasks.getTasks());
        } catch (ChatterboxExceptions.ChatterBoxError e) {
            return ("Sorry there was an error: " + e.getMessage());
        }
        return "Error occurring !!";
    }


    /**
     * Dummy echo testing
     * @return repeats the string with haha:
     */
    public String getResponse(String input) {
        return "haha: " + input;
    }

    /**
     * Gets the greeting string
     * @return greeting String
     */
    public String getGreeting() {
        return guiResponses.greeting();
    }
    public static void main(String[] args) {

        Chatterbox myChat = new Chatterbox(
                Paths.get(System.getProperty("user.dir"), "data" , "command1.txt").toString());
        myChat.run();


    }
}
