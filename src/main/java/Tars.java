import java.util.ArrayList;
import java.util.Scanner;

class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    //void since method does not return anything
    public void mark(){
        this.isDone = true;
    }
    public void unmark(){
        this.isDone = false;
    }
}

public class Tars {
    public static void main(String[] args) {
        //welcome/introduction message
        System.out.println("    _____________________________________________");
        System.out.println("    Hello! I'm Tars\n" + "    What can I do for you");
        System.out.println("    _____________________________________________");

        Scanner scanner = new Scanner(System.in); //initalising scanner to read inputs from user
        String entry = scanner.nextLine(); //storing string input in a variable
        String[] entryParts = entry.split(" "); //when mark/unmark is given as input with TASK number

        ArrayList<Task> itemsList = new ArrayList<>(); //store all input entries

        //while loop to ensure termination of programme only when "bye" input
        while(!entryParts[0].equals("bye")) {
            System.out.println("    _____________________________________________");

            //using for loop to list all entries from arraylist
            if(entryParts[0].equals("list")) {
                for (int i = 0; i < itemsList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + "[" + itemsList.get(i).getStatusIcon()
                            + "] " + itemsList.get(i).description);
                }
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine();
                entryParts = entry.split(" "); //updating variable to next input entryParts
            } else if(entryParts[0].equals("mark")){
                Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //to convert string format of number to Integer
                itemsList.get(index - 1).mark(); //marking TASK as done

                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    [" + itemsList.get(index - 1).getStatusIcon() + "] " + itemsList.get(index - 1).description);
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine();
                entryParts = entry.split(" "); //updating variable to next input entryParts
            } else if(entryParts[0].equals("unmark")){
                Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //to convert string format of number to Integer
                itemsList.get(index - 1).unmark(); //unmarking TASK as not done

                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    [" + itemsList.get(index - 1).getStatusIcon() + "] " + itemsList.get(index - 1).description);
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine();
                entryParts = entry.split(" "); //updating variable to next input entryParts
            }
            else {
                Task t = new Task(entry);
                itemsList.add(t); //adding Task to list

                System.out.println("    added: " + entry);
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine();
                entryParts = entry.split(" "); //updating variable to next input entryParts
            }
        }

        //exit message when given input "bye"
        System.out.println("    _____________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________________");
    }
}
