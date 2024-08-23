import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sinatra {
    

    private ArrayList<Message> Messages;

    public Sinatra() {
        this.Messages = new ArrayList<Message>();
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
       
            for (int i = 0; i < Messages.size(); i++) {
                int count = i + 1;
             
    
                System.out.println(count + "."+ Messages.get(i).toString());

            }
        } else if (message.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (message.length()>4 && message.substring(0, 4).equals("mark")) {
            Message currMessage = Messages.get(Integer.parseInt(message.substring(5)) - 1);
             currMessage.setStatus(true);
            System.out.println("Nice! I've marked this task as done: ");
              System.out.println(currMessage);
        
        }else if (message.length()>6 && message.substring(0, 6).equals("unmark")) {
            Message currMessage = Messages.get(Integer.parseInt(message.substring(7)) - 1);
            currMessage.setStatus(false);
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(currMessage);

        }
        else {
            Messages.add(new Message(message, false));
            System.out.println("added: " + message);
        }
        
    }
}
