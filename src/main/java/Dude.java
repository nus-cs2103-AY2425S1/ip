import java.util.Scanner;

public class Dude {
    Scanner scanner;
    String line = "____________________________________________________________";
    String botName = "Dude";

    public Dude(){
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println(line);
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        System.out.println(line);

        echo(scanner);
    }

    public void echo(Scanner scanner){
        String input = "";

        while(!input.equals("bye")){
            input = scanner.nextLine();

            if(input.equals("bye")){
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
            }
            else{
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        Dude dude = new Dude();
        dude.start();
    }
}
