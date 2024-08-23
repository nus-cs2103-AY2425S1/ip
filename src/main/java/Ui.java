public class Ui {

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

    public void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Goodbye!");
    }
}
