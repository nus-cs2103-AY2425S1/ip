import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sinatra {
    

    private ArrayList<Task> Tasks;

    public Sinatra() {
        this.Tasks = new ArrayList<Task>();
        this.printIntro();
     
        this.sinatraScanner();
    }
    private void printIntro() {
        String initial = "Hello! I'm Sinatra \nWhat can I do for you? ";
       
        //print intro
        System.out.println(initial);
    }

    public static void main(String[] args) {
        new Sinatra();

      

    }

    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            handleInputs(input);
        }
      
    }

    private void handleInputs(String message) {
        if (message.equals("list")) {
       
            for (int i = 0; i < Tasks.size(); i++) {
                int count = i + 1;
             
    
                System.out.println(count + "."+ Tasks.get(i).toString());

            }
        } else if (message.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (message.length()>4 && message.substring(0, 4).equals("mark")) {
            Task currTask = Tasks.get(Integer.parseInt(message.substring(5)) - 1);
             currTask.setStatus(true);
            System.out.println("Nice! I've marked this task as done: ");
              System.out.println(currTask);
        
        }else if (message.length()>6 && message.substring(0, 6).equals("unmark")) {
            Task currTask = Tasks.get(Integer.parseInt(message.substring(7)) - 1);
            currTask.setStatus(false);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(currTask);

        }
        else {
            Tasks.add(new Task(message, false));
            System.out.println("added: " + message);
        }
        
    }
}
