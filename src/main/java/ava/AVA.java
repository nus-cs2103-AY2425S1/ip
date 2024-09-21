package ava;

import ava.commands.Command;
import ava.commands.Parser;
import ava.task.Task;
import ava.task.TaskManager;

import java.io.PrintStream;
import java.util.List;

/**
 * Creates a model AVA which follows commands.
 */
public class AVA {

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
     * TODO: switch to a state based system like Operating System Threads
     *
     * @return <span color="green">true</span> if AVA is running <span color="red">false</span> otherwise.
     */
    public final boolean isRunning(){
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

    //todo:have a non printstream version
    /**
     * Prints AVA's response to given PrintStream.
     *
     * @param out PrintStream to print AVA's response to.
     * TODO:refactor mark and unmark to remove redundancy
     */
    public void respond(PrintStream out) {
        Command userInput;
        try {
             userInput = Parser.parseCommand(currentInput);
        } catch(IllegalArgumentException e){
            out.println("I am sorry, but I am not capable of doing that yet 😢.");
            return;
        }
        try {
            switch (userInput) {
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
                out.printf("%d. %s %n",taskId,task);
                break;
            }
            case UNMARK: {
                int taskId = Parser.parseUnmark(currentInput);
                Task task = taskManager.getTasks().get(taskId - 1);
                task.markNotDone();
                out.println("Alright I have marked this task as not done");
                out.printf("%d. %s %n",taskId,task);
                break;
            }
            case DELETE: {
                int taskId = Parser.parseDelete(currentInput);
                Task task = taskManager.removeTask(taskId);
                out.println("Alright I have deleted this task");
                out.printf("%d. %s %n",taskId,task);
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
                List<Task> tasks = Parser.parseFind(currentInput,taskManager);
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
        } catch (IllegalArgumentException e){
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
        }
    }


    public void streamResponse(){
        //TODO: implement
        //stream a response to output
    }

    /**
     *  Runs AVA.
     *  <br>
     *  Main driver method running AVA.
     */
    public static void main(String[] args) {
        TextUI.run();
    }
}

