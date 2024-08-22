public class Hamyo {

    private static String line = "_______________________________________________________\n";
    private static String logo = " /$$   /$$\n" +
        "| $$  | $$\n" +
        "| $$  | $$  /$$$$$$  /$$$$$$/$$$$  /$$   /$$  /$$$$$$\n" +
        "| $$$$$$$$ |____  $$| $$_  $$_  $$| $$  | $$ /$$__  $$\n" +
        "| $$__  $$  /$$$$$$$| $$ \\ $$ \\ $$| $$  | $$| $$  \\ $$\n" +
        "| $$  | $$ /$$__  $$| $$ | $$ | $$| $$  | $$| $$  | $$\n" +
        "| $$  | $$|  $$$$$$$| $$ | $$ | $$|  $$$$$$$|  $$$$$$/\n" +
        "|__/  |__/ \\_______/|__/ |__/ |__/ \\____  $$ \\______/\n" +
        "                                   /$$  | $$\n" +
        "                                  |  $$$$$$/\n" +
        "                                   \\______/";

    public static void main(String[] args) {

        System.out.printf(Hamyo.line + Hamyo.logo + "\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?\n" + Hamyo.line);

        System.out.printf("Annyeong! Till we meet again. <3\n" + Hamyo.line);
    }
}
