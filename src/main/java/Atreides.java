import java.util.Scanner;
public class Atreides {
    public static void main(String[] args) {
        String logo =  "          _            _     _\n"
                    +  "    / \\  | |          (_)   | |\n"
                    +  "   /   \\ | |_ _ __ ___ _  __| | ___  ___\n"
                    +  "  / / \\ \\| __| '__/ _ \\ |/ _` |/ _ \\/ __|\n"
                    +  " / _____ \\ |_| | |  __/ | (_| |  __/\\__ \\ \n"
                    +  "/_/     \\_\\__|_|  \\___|_|\\__,_|\\___||___/\n";

//        String line = "____________________________________________________________";
//        System.out.println(line);
//        System.out.println("Hello! I'm\n" + logo);
//        System.out.println(line);
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println(line);
        String intro = "Glory to house\n" + logo + "\n";
        System.out.println(new Response(intro));
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        while (!(msg.toLowerCase().equals("bye"))) {
            System.out.println(new Response(msg));
            msg = scanner.nextLine();
        }
        String outro = "May thy knife chip and shatter.";
        System.out.println(new Response(outro));
    }
}