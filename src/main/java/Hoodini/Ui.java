package Hoodini;
public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("Hello! I'm Hoodini\nHow may I assist you?");
    }

    public void showGoodbye() {
        System.out.println("Bye! Come back to Hoodini soon!");
    }

    public void noFileFound() {
        System.out.println("File not found, I will create a new file for you!");
    }

    public void store(Input input, int counter) {
        System.out.println("Noted. I have added this task:");
        System.out.println(input);
        System.out.println("You have " + counter + " tasks in the list.");
    }

    public void delete(Input input, int counter) {
        System.out.println("Noted. I have deleted this task:");
        System.out.println(input);
        System.out.println("You have " + counter + " tasks in the list.");
    }

    public void invalidInput() {
        System.out.println("Invalid number, enter a valid number");
    }

    public void invalidTask() {
        System.out.println("Invalid task, please enter a valid task");
    }

}
