import java.util.Scanner;

public class Echo {
    private String separator;
    private String message = "";

    private String indent = "      ";

    public Echo(String separator) {
        this.separator = separator;
    }


    public void echoMessage() {
        while (!this.message.strip().equalsIgnoreCase("bye")) {
            System.out.println("Enter your message: ");
            Scanner scanner = new Scanner(System.in);
            this.message = scanner.nextLine();
            if (this.message.strip().equalsIgnoreCase("bye")) {
                break;
            }
            String outputMessage = this.indent + this.separator + "\n" + this.indent + this.message + "\n" + this.indent  + this.separator;
            System.out.println(outputMessage);
        }
    }

}
