import java.util.Scanner;

public class Barcus {
    public static void main(String[] args) {
        // fixed dialogue
        String intro =
                "╭━━╮\n" +
                "┃╭╮┃\n" +
                "┃╰╯╰┳━━┳━┳━━┳╮╭┳━━╮\n" +
                "┃╭━╮┃╭╮┃╭┫╭━┫┃┃┃━━┫\n" +
                "┃╰━╯┃╭╮┃┃┃╰━┫╰╯┣━━┃\n" +
                "╰━━━┻╯╰┻╯╰━━┻━━┻━━╯\n" +
                "Beep bop! Hello I am Barcus, ready to be of assistance!\n" +
                "Write 'bye' to leave!\n";
        String goodbye = "Alright, good talk!\n";

        // scanner object
        Scanner scanner = new Scanner(System.in);

        // list to save info
        String[] tasks = new String[100];
        int curr = 0;

        // start
        System.out.println(intro);

        boolean exit = false;

        while (!exit) {
            String reply = receive(scanner);

            if (reply.equals("bye")) {
                exit = true;
                talk(goodbye);
            } else if (reply.equals("list")) {
                // for add list
                talk("");
                for (int i = 0; i < curr; i++) {
                    System.out.println(String.valueOf(i+1) + ". " + tasks[i]);
                }
            } else {
                // for echo
//                talk(reply);
                tasks[curr] = reply;
                curr++;
                talk("Added task '" + reply + "'");
            }
        }

    }

    private static void talk(String s) {
        System.out.println("Barcus: " + s);
    }

    private static String receive(Scanner scanner) {
        System.out.print("You: ");
        return scanner.nextLine();
    }
}

