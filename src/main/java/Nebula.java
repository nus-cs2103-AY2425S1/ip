import java.util.Scanner;

public class Nebula {
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.greeting());

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(ui.goodbye());
                break;
            }
            String echoCommand = ui.echo(command);
            System.out.println(echoCommand);
        }


    }
}
