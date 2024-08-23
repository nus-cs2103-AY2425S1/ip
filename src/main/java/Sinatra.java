import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sinatra {
    

    private List list;

    public Sinatra() {
        this.list = new List();
        this.printIntro();
     
        this.sinatraScanner();
    }
    private void printIntro() {
        String initial = "Hello! I'm Sinatra \nWhat can I do for you? ";
       
        //print intro
        System.out.println(initial);
    }

    public static void main(String[] args) {
        Sinatra sinatra = new Sinatra();

      

    }

    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            handleInputs(message);
        }
      
    }

    private void handleInputs(String message) {
        if (message.equals("list")) {
            ArrayList<String> contents = list.getItems();
            for (int i = 0; i < list.getItems().size(); i++) {
                int count = i + 1;
                System.out.println(count + ". " + contents.get(i));

            }
        } else if (message.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        
        }
        else {
            list.appendItem(message);
            System.out.println("added: " + message);
        }
    }
}
