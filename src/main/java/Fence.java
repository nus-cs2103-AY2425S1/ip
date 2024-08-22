import java.util.Scanner;

public class Fence {

    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    public void exit() {
        System.out.println("have good day :)");
    }

    public void echo(String command) {
        System.out.println(command);
    }

    public static void main(String[] args) {
        Fence fence = new Fence();
        fence.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                fence.exit();
                break;
            }
            fence.echo(command);
        }
    }
}
