import java.util.ArrayList;
import java.util.Scanner;

public class Meow {
    static private String logo =  
    "     ___           ___           ___           ___\n" +     
    "    /  /\\         /  /\\         /  /\\         /  /\\\n" +   
    "   /  /::|       /  /::\\       /  /::\\       /  /:/_\n" +  
    "  /  /:|:|      /  /:/\\:\\     /  /:/\\:\\     /  /:/ /\\\n" +
    " /  /:/|:|__   /  /::\\ \\:\\   /  /:/  \\:\\   /  /:/ /:/_\n" +
    "/__/:/_|::::\\ /__/:/\\:\\ \\:\\ /__/:/ \\__\\:\\ /__/:/ /:/ /\\\n" +
    "\\__\\/  /~~/:/ \\  \\:\\ \\:\\_\\/ \\  \\:\\ /  /:/ \\  \\:\\/:/ /:/\n" +
    "      /  /:/   \\  \\:\\ \\:\\    \\  \\:\\  /:/   \\  \\::/ /:/\n" +
    "     /  /:/     \\  \\:\\_\\/     \\  \\:\\/:/     \\  \\:\\/:/\n" +
    "    /__/:/       \\  \\:\\        \\  \\::/       \\  \\::/\n" +
    "    \\__\\/         \\__\\/         \\__\\/         \\__\\/";
    static private String openingMessage = logo + 
    "\n    _____________________________________________________________________" +   
    "\n    Welcome to the Meow Chatbot!!! I love meowing hue hue"
    + "\n    What can I do for you Meow?"
    + "\n    _____________________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, DELETE, MARK, UNMARK, BYE
    }
    public static void main(String[] args) throws Meowception {
        System.out.println(openingMessage);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        // User input cycle is here.

        while (!input.equals("bye")) {
            commandValidation(input);
            input = sc.nextLine();
        }

        System.out.println("Fine just leave me like everyone does hmph");
    }

    static private void errorMsg(String msg) {
        System.out.println("    " + "_____________________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    " + "_____________________________________________________________________\n");
    }

    private static void commandValidation(String inputType) throws Meowception {
        try {
            
            String[] parts = inputType.split(" ");

            System.out.println(parts[0]);
            Command userCommand = Command.valueOf(parts[0].toUpperCase());
            outputTask(userCommand, inputType);
        } catch (StringIndexOutOfBoundsException e) {
            Meowception err = new Meowception("404");
                errorMsg(err.toString());
        } catch (IllegalArgumentException e) {
            Meowception err = new Meowception("001");
            errorMsg(err.toString());
        }
    }

    private static void outputTask(Command cmd,String inputType) throws Meowception {
        

        switch (cmd) {
            case MARK:
                try {
                    if (!inputType.substring(5).trim().isEmpty()) {
                        markTask(inputType);
                        return;
                    } else {
                        throw new Meowception("100");
                    }
                } catch (Meowception err) {
                    errorMsg(err.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
                }
                // errorMsg("Meow meow you need to enter a task number to mark !!!");
                break;
                
            case UNMARK:
                try {
                    if (!inputType.substring(7).trim().isEmpty()) {
                        unmarkTask(inputType);
                        return;
                    } else {
                        throw new Meowception("100");
                    }
                } catch (Meowception err) {
                    errorMsg(err.toString());       
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
                }
                break;
                    
            case LIST:
    
                displayList();
                break;
    
            case TODO:
                // Cleaning data so that it identifies todo, "todo " and subsequent "todo         ...   "
                // if (inputType.length() == 4) {
                //     errorMsg("Meow meow you need to enter a task in your todo !!!");
                // } else if (inputType.length() == 5) {
                //     if (inputType.charAt(4) != ' ') {
                //         errorMsg("No command exists like that silly goose");
                //     } else {
                //         errorMsg("Meow meow you need to enter a task in your todo !!!");
                //     }
                // } else {
                //     addTodoTask(inputType.substring(5)); // adds task but cleans out the "todo "
                // }
                try {
                    addTodoTask(inputType.substring(5));
                } catch (Meowception e) {
                    errorMsg(e.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
                } 
                break;
                
            case DEADLINE:
                // Cleaning data so that it identifies deadline correctly and the task.
                try {
                    addDeadlineTask(inputType.substring(9));
                } catch (Meowception err) {
                    errorMsg(err.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
                } 
                break;
                
            case EVENT:
                // Lazy clean so nice for event. no way we introduce a new command called eventsmth...
                try {
                    addEventTask(inputType.substring(6));
                } catch (Meowception err) {
                    errorMsg(err.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
                }
                break;
                
    
            case DELETE:
                try {
                    if (!inputType.substring(6).trim().isEmpty()) {
                        rmvTask(inputType);
                    } else {
                        throw new Meowception("100");
                    }
                } catch (Meowception err) {
                    errorMsg(err.toString());
                } catch (StringIndexOutOfBoundsException e) {
                    Meowception err = new Meowception("100");
                    errorMsg(err.toString());
    
                }
                break;
                
            // case DEFAULT:
            //     errorMsg(new Meowception("001").toString());
            // }
        }



        
    }

    static private void addTodoTask(String command) throws Meowception{
        
        if (command.trim().isEmpty()) {
            throw new Meowception("100");
            //errorMsg("Your todo task can't be blank silly goose, write something NEOW MEOW");
        }
        else {
            taskList.add(new Todo (command));
            addingTaskMessage(command, taskList.get(taskList.size() - 1));
        }
    }

    static private void addDeadlineTask(String command) throws Meowception {
        if (command.contains("/by ")) {
            if (command.substring(command.indexOf("/by") + 3 ).trim().isEmpty()) {
                throw new Meowception("200");
                // errorMsg("Meow meow you need to enter a deadline for your task");
                // return;
            } else {
                String taskName = command.substring(0, command.indexOf("/by ") - 1);
                if (taskName.trim().isEmpty()) {
                    throw new Meowception("100");
                    // errorMsg("Meow meow you need to enter a task name for your deadline");
                    // return;
                }
                String by = command.substring(command.indexOf("/by ") + 4);
                
                taskList.add(new Deadline(taskName, by));
                addingTaskMessage(command, taskList.get(taskList.size() - 1));
            }
            
        } else {
           throw new Meowception("200");
        }
        
    }

    static private void addEventTask(String command) throws Meowception {
        if (!command.contains("/from ") || !command.contains("/to ")) {
            //errorMsg("Meow meow you need to enter a timeframe for your event");
            throw new Meowception("300");
        }
        else {
            if (command.substring(0, command.indexOf("/from")).trim().isEmpty()) {
                //errorMsg("Meow meow you need to enter a event name!!!");
                throw new Meowception("100");
            } else if (command.substring(command.indexOf("/from"), command.indexOf("/to ")).trim().isEmpty()) {
                //errorMsg("Meow meow you need to enter a start time for your event");
                //return;
                throw new Meowception("300");
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
                System.out.println("    Meow has marked this task as done:\n        " + icon +taskList.get(number - 1).getTaskName());
            }
            
            System.out.println("    " + "_____________________________________________________________________\n");
            
        }
    }
    /*
     * This function is used to unmark a task as done.
     * @param String: The command line containing index.
     * @output void, prints to console and unmarks task.
     */
    static private void unmarkTask(String task) throws Meowception {
        String numberString = task.substring(task.indexOf(" ") + 1);
        int number = Integer.parseInt(numberString);
        if (number > taskList.size() || number <= 0) {
            throw new Meowception("404");
        }
        else { // Check if the task is already unmarked and stuff...  
            System.out.println("    " + "_____________________________________________________________________");
            if (!taskList.get(number - 1).isDone()) {
                System.out.println("    Meow THIS TASK IS ALREADY UNMARKED !!! ");
            } else {
                taskList.get(number - 1).setDone(false);
                String icon = "[" + taskList.get(number - 1).getCompetionChar() +"]";
                System.out.println("    Meow has unmarked this task as done:\n      " + icon +taskList.get(number - 1).getTaskName());
            }
            System.out.println("    " + "_____________________________________________________________________\n");
            
        }
    }

    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println("_____________________________________________________________________\n     Meow has added a task:\n    " + task.toString() + "\n_____________________________________________________________________");
    }

    private static void displayList() {
        System.out.println("_____________________________________________________________________\n     Meow here is your task list:");
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
    static private void rmvTask(String task) throws Meowception {
        String numberString = task.substring(task.indexOf(" ") + 1);
        int number = Integer.parseInt(numberString);
        if (number > taskList.size() || number < 0) {
            throw new Meowception("404");
        } else { // remove the task now.      
            System.out.println("    " + "_____________________________________________________________________");
            String icon = "[" + taskList.get(number - 1).getCompetionChar() +"]";
            System.out.println("    Meow has REMOVED this task: \n        " + icon +taskList.get(number - 1).getTaskName());
            System.out.println("    " + "_____________________________________________________________________\n");
            taskList.remove(number - 1);
        }
    }
}
