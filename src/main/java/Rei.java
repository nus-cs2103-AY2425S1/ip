import java.util.Scanner;

public class Rei {
    public static void main(String[] args) {
        String logo = "  ____  _____ ___ \n"
                + " |  _ \\| ____|_ _|\n"
                + " | |_) |  _|  | | \n"
                + " |  _ <| |___ | | \n"
                + " |_| \\_\\_____|___|\n";

        System.out.println("Annyeong! I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("-----------YOU------------");

        Scanner scanner = new Scanner(System.in);
        String prompt = scanner.nextLine();
        while (!prompt.equals("annyeong")  && !prompt.equals("Annyeong")) {
            System.out.println("-----------REI♥-----------");
            System.out.println(prompt);
            System.out.println("-----------YOU------------");
            prompt = scanner.nextLine();
        }
        scanner.close();

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }
}
