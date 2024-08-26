public class Ui {
    public void showLoadingError() {

    }
    public void printHorizontalLine() {
        System.out.println("-------------------------------------");
    }
    public void greet() {
        printHorizontalLine();
        String greetingMessage = "Hello! I'm Soju\nWhat can I do for you?";
        System.out.println(greetingMessage);
        printHorizontalLine();
    }
    public void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
        printHorizontalLine();
    }
}
