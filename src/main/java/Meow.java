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

        } else {
            addTask(new Task(inputType));
        }
    }
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
}
