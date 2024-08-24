import Task.Task;
import Task.TaskManager;

import java.io.PrintStream;
import java.util.List;

public class AVA {

    /**
     * Current user input being processed by AVA.
     */
    private String currentInput;

    /**
     * Task.TaskManager for AVA
     * its initialized on ava's creation
     *
     * final to avoid unnecessary modifications
     */
    private final TaskManager taskManager;

    /**
     * default constructor for AVA
     */
    public AVA() {
        taskManager = new TaskManager();
    }

    /**
     * Main function which decides if AVA is running or not
     * //TODO: switch to a state based system like Operating System Threads
     * @return <span color="green">true</span> if AVA is running <span color="red">false</span> otherwise
     */
    public final boolean isRunning(){
        return !currentInput.equals("bye");
    }

    /**
     * updates the currentInput with the user input
     * @param s the user input
     */
    public void tellAva(String s) {
        currentInput = s;
    }

    //todo:have a non printstream version
    /**
     * method to print AVA's response to given PrintStream
     * @param out PrintStream to print AVA's response to
     * //TODO:refactor mark and unmark to remove redundancy
     */
    public void respond(PrintStream out){
        if(currentInput.equals("list")){
            List<Task> list = taskManager.getTasks();
            out.println("Here are your tasks:");
            out.println(list);
        } else if(currentInput.startsWith("mark")){
            int taskId;
            try{
                taskId = Integer.parseInt(currentInput.substring(5));
            } catch(NumberFormatException e){
                out.println("I am sorry, but you need to provide me a task id to mark or unmark something.");
                return;
            }
            Task task = taskManager.getTasks().get(taskId-1);
                task.markDone();
                //TODO: use string format
                out.println("Alright I have marked this task as done");
                out.println(taskId +". " + task);
            }
         else if(currentInput.startsWith("unmark")){
            int taskId;
            try{
                taskId = Integer.parseInt(currentInput.substring(5));
            } catch(NumberFormatException e){
                out.println("I am sorry, but you need to provide me a task id to mark or unmark something.");
                return;
            }
            Task task = taskManager.getTasks().get(taskId-1);
            task.markNotDone();
            //TODO: use string format
            out.println("Alright I have marked this task as not done");
            out.println(taskId +". " + task);
        } else if(currentInput.startsWith("delete")){
            int taskId;
            try{
                taskId = Integer.parseInt(currentInput.substring(7));
            } catch(NumberFormatException e){
                out.println("I am sorry, but you need to provide me a task id to delete something.");
                return;
            }
            Task task = taskManager.getTasks().get(taskId-1);
            taskManager.removeTask(taskId);
            //TODO: use string format
            out.println("Alright I have deleted this task");
            out.println(taskId +". " + task);
        }
        else{
            taskManager.addTask(currentInput);
            out.println("----------------------------------------------------------------");
            out.println("Added " + currentInput);
            out.println("----------------------------------------------------------------");
        }
    }

    /**
     * Main Driver method running AVA
     */
    public static void main(String[] args) {
        TextUI.run();
    }
}

