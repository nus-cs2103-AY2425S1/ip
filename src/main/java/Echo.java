import java.util.ArrayList;
import java.util.Scanner;

public class Echo {
    private String separator;
    private String message = "";

    private String indent = "      ";

    private ArrayList<String> list = new ArrayList<String>();


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
            if (this.message.strip().equalsIgnoreCase("list")) {
                this.getTasks();
                continue;
            }
            String outputMessage = this.indent + this.separator + "\n" + this.indent + "added: " + this.message + "\n" + this.indent  + this.separator;
            System.out.println(outputMessage);
            list.add(this.message);
        }
    }

    public void getTasks() {
        System.out.println(this.indent + this.separator);
        for (int i = 0; i < list.size(); i++) {
            String number = String.valueOf(i+1);
            String format = this.indent + number + ". " + list.get(i);
            System.out.println(format);
        }
        System.out.println(this.indent + this.separator);
    }

}
