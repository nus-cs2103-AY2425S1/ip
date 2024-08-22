import java.util.ArrayList;
import java.util.Scanner;

public class Meow {
    static private String logo =  
    "     ___           ___           ___           ___ \n" +     
    "    /  /\\         /  /\\         /  /\\         /  /\\ \n" +   
    "   /  /::|       /  /::\\       /  /::\\       /  /:/_ \n" +  
    "  /  /:|:|      /  /:/\\:\\     /  /:/\\:\\     /  /:/ /\\ \n" +
    " /  /:/|:|__   /  /::\\ \\:\\   /  /:/  \\:\\   /  /:/ /:/_ \n" +
    "/__/:/_|::::\\ /__/:/\\:\\ \\:\\ /__/:/ \\__\\:\\ /__/:/ /:/ /\\ \n" +
    "\\__\\/  /~~/:/ \\  \\:\\ \\:\\_\\/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/ \n" +
    "      /  /:/   \\  \\:\\ \\:\\    \\  \\:\\  /:/   \\  \\::/ /:/  \n" +
    "     /  /:/     \\  \\:\\_\\/     \\  \\:\\/:/     \\  \\:\\/:/   \n" +
    "    /__/:/       \\  \\:\\        \\  \\::/       \\  \\::/    \n" +
    "    \\__\\/         \\__\\/         \\__\\/         \\__\\/     ";
    static private String openingMessage = logo + 
    "\n    _____________________________________________________________________" +   
    "\n    Welcome to the Meow Chatbot!!! I love meowing hue hue"
    + "\n    What can I do for you Meow?"
    + "\n    _____________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(openingMessage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        // User input cycle is here.

        while (!input.equals("bye")) {
            outputTask(input);
            input = sc.nextLine();
        }

        System.out.println("Fine just leave me like everyone does hmph");
    }

    static private void errorMsg(String msg) {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     MEOW ERROR: " + msg);
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    private static void outputTask(String inputType) {
        if (inputType.startsWith("mark")) {
            if (inputType.length() > 5) {
                System.out.println(inputType.substring(5).trim());
                System.out.println(inputType.charAt(4) == ' ');
                if (inputType.charAt(4) == ' ' && !inputType.substring(5).trim().isEmpty()) {
                    System.out.println("meow");
                    markTask(inputType);
                    return;
                }
                
            } 
            errorMsg("Meow meow you need to enter a task number to mark !!!");
            
            
        } else if (inputType.startsWith("unmark")) {
            if (inputType.length() > 7) {
                if (inputType.charAt(6) == ' ' && !inputType.substring(7).trim().isEmpty()) {
                    unmarkTask(inputType);
                    return;
                }
            }
            errorMsg("Meow meow you need to enter a task number to unmark !!!");
            
                
        } else if (inputType.equals("list")) { 

            displayList();

        } else if (inputType.startsWith("todo")) {
            // Cleaning data so that it identifies todo, "todo " and subsequent "todo         ...   "
            if (inputType.length() == 4) {
                errorMsg("Meow meow you need to enter a task in your todo !!!");
            } else if (inputType.length() == 5) {
                if (inputType.charAt(4) != ' ') {
                    errorMsg("No command exists like that silly goose");
                } else {
                    errorMsg("Meow meow you need to enter a task in your todo !!!");
                }
            } else {
                addTodoTask(inputType.substring(5)); // adds task but cleans out the "todo "
            }
            
        } else if (inputType.startsWith("deadline")) {
            // Cleaning data so that it identifies deadline correctly and the task.
            if (inputType.length() > 9) {
                if (inputType.charAt(8) == ' ') {
                    addDeadlineTask(inputType.substring(9));
                    return;
                }
            }
            errorMsg("Meow meow you need to enter a task in your deadline !!!");

            
        } else if (inputType.startsWith("event") && inputType.length() > 5) {
            // Lazy clean so nice for event. no way we introduce a new command called eventsmth...
            if (inputType.charAt(5) == ' ') { // this is okay as the input type has to have length 6 or more
                addEventTask(inputType.substring(6));
            }
            

        } else {
            System.out.println("    " + "MEOW MEOW ENTER A TASK OR ELSE ILL EAT U");
            System.out.println("    " + "_____________________________________________________________________\n");
            //addingMessage(inputType);
        }
    }

    static private void addTodoTask(String command) {
        if (command.trim().isEmpty()) {
            errorMsg("Your todo task can't be blank silly goose, write something NEOW MEOW");
            return;
        }
        else {
            taskList.add(new Todo (command));
            addingTaskMessage(command, taskList.get(taskList.size() - 1));
        }
    }

    static private void addDeadlineTask(String command) {
        if (command.contains("/by ")) {
            if (command.substring(command.indexOf("/by") + 3 ).trim().isEmpty()) {
                errorMsg("Meow meow you need to enter a deadline for your task");
                return;
            } else {
                String taskName = command.substring(0, command.indexOf("/by ") - 1);
                String by = command.substring(command.indexOf("/by ") + 4);
                taskList.add(new Deadline(taskName, by));
                addingTaskMessage(command, taskList.get(taskList.size() - 1));
            }
            
        } else {
            errorMsg("meow meow you need to enter a deadline for your task");
        }
        
    }

    static private void addEventTask(String command) {
        if (!command.contains("/from ") || !command.contains("/to ")) {
            errorMsg("Meow meow you need to enter a timeframe for your event");
            
            return;
        }
        else {
            if (command.substring(0, command.indexOf("/from")).trim().isEmpty()) {
                errorMsg("Meow meow you need to enter a event name!!!");
                return;
            } else if (command.substring(command.indexOf("/from"), command.indexOf("/to ")).trim().isEmpty()) {
                errorMsg("Meow meow you need to enter a start time for your event");
                return;
            } else {
                String taskName = command.substring(0, command.indexOf("/from") - 1);
                String timeframe = command.substring(command.indexOf("/from"));
                System.out.println(timeframe);
                taskList.add(new Event(taskName, timeframe));
                addingTaskMessage(command, taskList.get(taskList.size() - 1));

            }
            
        }
        
    }

    /*
     * This function is used to mark a task as done.
     * @param String: The command line containing index.
     * @output void, prints to console and marks task.
     */
    static private void markTask(String task) {
        String numberString = task.substring(task.indexOf(" ") + 1);
        int number = Integer.parseInt(numberString);
        if (number > taskList.size() || number <= 0) {
            System.out.println("    ERROR404: TASK NOT FOUND");
        }
        else { // check if the task is arleady marked and stuff...
            System.out.println("    " + "_____________________________________________________________________");
            if (taskList.get(number - 1).isDone()) {
                System.out.println("    Meow THIS TASK IS ALREADY MARKED !!! ");
            } else {
                taskList.get(number - 1).setDone(true);
                String icon = "[" + taskList.get(number - 1).getCompetionChar() +"]";
                System.out.println("    Meow has marked this task as done: \n        " + icon +taskList.get(number - 1).getTaskName());
            }
            
            System.out.println("    " + "_____________________________________________________________________\n");
            
        }
    }
    /*
     * This function is used to unmark a task as done.
     * @param String: The command line containing index.
     * @output void, prints to console and unmarks task.
     */
    static private void unmarkTask(String task) {
        String numberString = task.substring(task.indexOf(" ") + 1);
        int number = Integer.parseInt(numberString);
        if (number > taskList.size() || number <= 0) {
            System.out.println("    ERROR404: TASK NOT FOUND");
        }
        else { // Check if the task is already unmarked and stuff...  
            System.out.println("    " + "_____________________________________________________________________");
            if (!taskList.get(number - 1).isDone()) {
                System.out.println("    Meow THIS TASK IS ALREADY UNMARKED !!! ");
            } else {
                taskList.get(number - 1).setDone(false);
                String icon = "[" + taskList.get(number - 1).getCompetionChar() +"]";
                System.out.println("    Meow has unmarked this task as done: \n        " + icon +taskList.get(number - 1).getTaskName());
            }
            System.out.println("    " + "_____________________________________________________________________\n");
            
        }
    }

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("_____________________________________________________________________\n   Meow has added a task:\n     " + task.toString() + "\n_____________________________________________________________________");
    }

    private static void displayList() {
        System.out.println("_____________________________________________________________________\n   Meow here is your task list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + taskList.get(i));

        }
        System.out.println("_____________________________________________________________________");
    }

     /*
     * This function is used to add a task to the task list.
     * @param String: The task to be added.
     * @output void, prints to console and adds task.
     */
    static private void addingTaskMessage(String msg, Task t) {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("    Meow has added this task hehe: ");
        System.out.println("        " + t.toString());
        System.out.println("    Neow you have " + taskList.size() + " tasks in the list");
        System.out.println("    " + "_____________________________________________________________________\n");
    }
}
