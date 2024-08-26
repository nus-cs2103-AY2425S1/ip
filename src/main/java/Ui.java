package ip.derrick ;
public class Ui {

    /**
     * Show the list of commands that the chatbot can perform.
     */
    public void showCommands() {
        System.out.println("_____________________________________________________________________________");
        System.out.println("List of Commands available : ");
        int counter = 1;
        for (Commands command : Commands.values()) {
            System.out.println(counter + ". " + command.toString());
            counter += 1;
        }
        System.out.println("_____________________________________________________________________________");
    }

    /**
     * Greets the user and introduce the chatbot to the user.
     */
    public void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }

    /**
     * Bids the user goodbye.
     */
    public void exit() {
        System.out.println("Goodbye!");
    }
}
