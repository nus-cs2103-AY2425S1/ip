import java.util.Scanner;

public class Barcus {
    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);

        // start
        System.out.println(intro);

        boolean exit = false;

        while (!exit) {
            String reply = receive(scanner);

            if (reply.equals("bye")) {
                exit = true;
                talk(goodbye);
            } else {
                talk(reply);
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

