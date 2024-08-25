package papadom;

public class Ui {
    private String openingLine = "____________________________________________________________\n";
    private String closingLine = "\n____________________________________________________________";
    public void output(String message) {
        System.out.println(openingLine + message + closingLine);
    }
    public void welcomeMessage() {
        System.out.println(openingLine + " Hello! Hellooeo I'm Papadom\n What can I do for you?" + closingLine);
    }
    public void exitMessage() {
        System.out.println(openingLine + " Bye. Hope to see you again soon!" + closingLine);
    }
}

