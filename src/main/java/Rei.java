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

        String[] list = new String[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String prompt = scanner.nextLine();
        while (!prompt.equals("annyeong")  && !prompt.equals("Annyeong")) {
            if (prompt.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println(i + ". " + list[i]);
                }
            } else {
                System.out.println("-----------REI♥-----------");
                System.out.println("added: " + prompt);
                System.out.println("-----------YOU------------");
                list[count++] = prompt;
            }


            prompt = scanner.nextLine();
        }
        scanner.close();

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }
}
