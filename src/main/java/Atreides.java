import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Atreides {
    public static void main(String[] args) {
        String logo =  "          _            _     _\n"
                    +  "    / \\  | |          (_)   | |\n"
                    +  "   /   \\ | |_ _ __ ___ _  __| | ___  ___\n"
                    +  "  / / \\ \\| __| '__/ _ \\ |/ _` |/ _ \\/ __|\n"
                    +  " / _____ \\ |_| | |  __/ | (_| |  __/\\__ \\ \n"
                    +  "/_/     \\_\\__|_|  \\___|_|\\__,_|\\___||___/\n";

        String intro = "Glory to house\n" + logo + "\n";
        System.out.println(new Response(intro));
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while (!(msg.toLowerCase().equals("bye"))) {
            if (msg.equals("list")) {
                String tasks = "";
                for (int i = 0; i < list.size(); i++) {
                    tasks += (i+1) + ". " + list.get(i);
                    if (i != list.size() - 1) {
                        System.out.println("adding delimiter");
                        tasks += "\n";
                    }
                }
                System.out.println(new Response(tasks));
            }
            else {
                list.add(msg);
                System.out.println(new Response("added: " + msg));
            }
            msg = scanner.nextLine();
        }
        String outro = "May thy knife chip and shatter.\n"
                       + "Praise be Muad Dib\n";
        System.out.println(new Response(outro));
        scanner.close();
    }
}