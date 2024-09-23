package ava;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import ava.commands.Command;
import ava.commands.Parser;
import ava.task.Task;
import ava.task.TaskManager;


//CHECKSTYLE.OFF: AbbreviationAsWordInName
/**
 * Creates a model AVA which follows commands.
 */
public class AVA {

    private static final String USER_GUIDE_URL = "https://timenikhil.github.io/ip/";

    //CHECKSTYLE.ON: AbbreviationAsWordInName
    /**
     * Current user input being processed by AVA.
     */
    private String currentInput;

    /**
     * TaskManager for AVA.
     * <br>
     * Initialized on ava's creation.
     * <br><br>
     * <code>final</code> to avoid unnecessary modifications.
     */
    private final TaskManager taskManager;

    /**
     * default constructor for AVA.
     */
    public AVA() {
        taskManager = new TaskManager();
    }

    /**
     * Decides if AVA is running or not.
     *
     * @return <span color="green">true</span> if AVA is running <span color="red">false</span> otherwise.
     */
    public final boolean isRunning() {
        return !currentInput.equals("bye");
    }

    /**
     * Updates the currentInput with the user input.
     *
     * @param s the user input.
     */
    public void tellAva(String s) {
        currentInput = s;
    }

    /**
     * Prints AVA's response to given PrintStream.
     *
     * @param out PrintStream to print AVA's response to.
     */
    public void respond(PrintStream out) {
        Command userInput;
        try {
            userInput = Parser.parseCommand(currentInput);
        } catch (IllegalArgumentException e) {
            out.println("I am sorry, but I am not capable of doing that yet 😢.");
            return;
        }
        try {
            switch (userInput) {
            case HELP: {
                out.println("Here are the commands I understand for now:");
                out.println("List, Mark, Unmark, Delete, Todo, Event, Deadline, Find");
                out.println("Please visit the user guide at the following link for more commands.");
                out.println(USER_GUIDE_URL);
                break;
            }
            case LIST: {
                List<Task> list = taskManager.getTasks();
                out.println("Here are your tasks:");
                out.println(list);
                break;
            }
            case MARK: {
                int taskId = Parser.parseMark(currentInput);
                Task task = taskManager.getTasks().get(taskId - 1);
                task.markDone();
                out.println("Alright I have marked this task as done");
                out.printf("%d. %s %n", taskId, task);
                break;
            }
            case UNMARK: {
                int taskId = Parser.parseUnmark(currentInput);
                Task task = taskManager.getTasks().get(taskId - 1);
                task.markNotDone();
                out.println("Alright I have marked this task as not done");
                out.printf("%d. %s %n", taskId, task);
                break;
            }
            case DELETE: {
                int taskId = Parser.parseDelete(currentInput);
                Task task = taskManager.removeTask(taskId);
                out.println("Alright I have deleted this task");
                out.printf("%d. %s %n", taskId, task);
                break;
            }
            case TODO: {
                String todo = Parser.parseToDo(currentInput, taskManager);
                out.println("----------------------------------------------------------------");
                out.println("Added todo: " + todo);
                out.println("----------------------------------------------------------------");
                break;
            }
            case EVENT: {
                String event = Parser.parseEvent(currentInput, taskManager);
                out.println("----------------------------------------------------------------");
                out.println("Added event: " + event);
                out.println("----------------------------------------------------------------");
                break;
            }
            case DEADLINE: {
                String deadline = Parser.parseDeadline(currentInput, taskManager);
                out.println("----------------------------------------------------------------");
                out.println("Added deadline: " + deadline);
                out.println("----------------------------------------------------------------");
                break;
            }
            case FIND: {
                List<Task> tasks = Parser.parseFind(currentInput, taskManager);
                out.println("Here are the tasks which match your search:");
                out.println("----------------------------------------------------------------");
                out.println(tasks);
                out.println("----------------------------------------------------------------");
                break;
            }
            default: {
                taskManager.addTask(currentInput);

            }
            }
        } catch (IllegalArgumentException e) {
            /* The responsibility of handling the error
             * is delegated to the respective parser methods
             *
             * An IllegalArgumentException is thrown to exit the
             * switch case forcefully while maintaining encapsulation
             *
             * This is why this catch block is empty
             * as the error has already been handled
             */
            return;
        } catch (IndexOutOfBoundsException e) {
            out.println("I am sorry, but I couldn't find the task you were looking for.");
        } catch (IllegalStateException e) {
            out.println("Oops, something went wrong. Please restart me.");
        }
    }

    /**
     * Responds to the current input as a String.
     *
     * @return the response to the user input.
     */
    public String respond() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        respond(ps);
        // the output stream acts as a buffer to convert the response to
        // a string
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    //CHECKSTYLE.OFF: Regexp
    /**
     * Welcomes the user.
     *
     * @return welcome message for user.
     */
    public String welcomeUser() {
        return """
                       Hiii,  I'm AVA (Assimilated Virtual Assistant). 🎀
                       I am a virtual personal assistant created by Nikhil.
                                   Its sooo nice to meet you 🌸.
                                   
                        I am currently a toddler and can't do much 🙁 but
                          don't worry I should soon be very capable 💖.
                
                ----------------------------------------------------------------
                             I am here to help you and hope that'll
                               we'll have lots of fun together 🎀.
                               
                        If you get lost just say help and I'll help you out
                           but if you wanna leave you can just say bye. 🙂
                ----------------------------------------------------------------
                """;
    }


    /**
     * Says goodbye to the user.
     *
     * @return goodbye message for user.
     */
    public String bye() {
        return """
                       Byeee!! It was really nice talking to you.💖
                               Hope to see you again soon.
                """;
    }
    //CHECKSTYLE.ON: Regexp
}

