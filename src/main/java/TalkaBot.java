import java.util.Scanner;
import java.util.ArrayList;

public class TalkaBot {

    private Scanner sc;
    private String[] list;
    private int counter;

    public TalkaBot() {
        this.sc = new Scanner(System.in);
        this.list = new String[100];
        this.counter = 0;
    }

    private void Run() {
        boolean end = false;
        Message.Hello();
        while (!end) {
            String input = this.sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                end = true;
            } else if (input.equalsIgnoreCase("list")) {
                Message.DisplayList(this.list, counter);
            } else {
                this.list[this.counter] = input;
                Message.Echo(input);
                this.counter++;
            }
        }
        Message.Goodbye();
    }

    public static void main(String[] args) {
        new TalkaBot().Run();
    }
}
