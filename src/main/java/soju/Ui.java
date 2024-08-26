package soju;

public class Ui {
    public void showLoadingError() {

    }
    public void printHorizontalLine() {
        System.out.println("-------------------------------------");
    }
    public void printError(SojuException e) {
        System.out.println(e.getMessage());
        printHorizontalLine();
    }
    public void greet() {
        printHorizontalLine();
        String greetingMessage = "Hello! I'm soju.Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
        printHorizontalLine();
    }
    public void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        printHorizontalLine();
    }
    public void printString(String string) {
        System.out.println(string);
    }
}
