import java.util.Scanner;

public class TalkaBot {

    private Scanner sc;

    public TalkaBot() {
        this.sc = new Scanner(System.in);
    }

    private void Run() {
        boolean end = false;
        Message.Hello();
        while (!end) {
            String input = this.sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                end = true;
            } else {
                Message.Echo(input);
            }
        }
        Message.Goodbye();
    }

    public static void main(String[] args) {
        new TalkaBot().Run();
    }
}
