import java.sql.SQLOutput;

public class Muffin {
    public static void main(String[] args) {
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _  \n" +
                "| |\\/| | || |  _|  _| | ' \\ \n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I'm Muffin \n" +
                "What can I do for you?";

        String byeMsg = "Goodbye~ Hope to see you again soon!";

        System.out.println(logo + "\n" + helloMsg);

        System.out.println("\n" + byeMsg);
    }
}
