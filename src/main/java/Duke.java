import java.util.Scanner;
public class Duke {

    private String username;
    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner username = new Scanner(System.in);
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot!", username.nextLine()));
    }
}
