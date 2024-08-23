import java.io.PrintStream;
import java.util.Scanner;
import java.util.List;

public class AVA {

    /**
     * Current user input being processed by AVA.
     * Note this requires there be only 1 AVA instance
     */
    private String currentInput;

    /**
     * TaskManager for AVA
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

    /**
     * method to print AVA's response to given PrintStream
     * @param out PrintStream to print AVA's response to
     * //TODO:shift tasklist display to tasklist
     * //TODO:refactor mark and unmark to remove redundancy
     */
    public void respond(PrintStream out){
        if(currentInput.equals("list")){
            List<Task> list = taskManager.getTasks();
            int count = list.size();
            for(int i = 1; i <=count;i++){
                out.println(i + ". " + list.get(i-1));
            }

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

        Scanner scanner = new Scanner(System.in);
        AVA ava = new AVA(); //default AVA object

        // Greet the user
        System.out.println("""
                          Hi,  I'm AVA (Artificial Virtual Assistant).
                       I am a virtual personal assistant created by Nikhil.
                        I am currently an infant and can't do much 🙁 but
                          don't worry I should soon be very capable 😉.
                
                ----------------------------------------------------------------
                       I'll add what you say to a list and show it to you when
                                          you say list,
                          but if you want to leave you can just say bye .🙂
                            
                ----------------------------------------------------------------
                """);
        ava.tellAva(scanner.nextLine());
        // Process user input until user says bye
        while(ava.isRunning()){
            ava.respond(System.out);
            ava.tellAva(scanner.nextLine());
        }
        //Exit
        System.out.println("Byee!! It was nice talking to you. Hope to see you again soon.");
    }
}

