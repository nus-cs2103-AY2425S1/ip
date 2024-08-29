import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import exceptions.InvalidInputException;

public class SigmaBot {
    public static final String HR_LINE_IN = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    public static final String HR_LINE_OUT = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n";
    public static final String HR_LINE = "——————————————————————————————————————————————————————————————————————————————\n";
    public static final String HR_LINE_DOUBLE = "==============================================================================\n";

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        new SigmaBot().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Greeting.greetingMsg();
        Map<String, Listing> lsts = new HashMap<>();

        while (true) {
            String inputString = scanner.nextLine().trim();

            if (inputString.equalsIgnoreCase("bye")) {
                Greeting.byeMsg();
                break;
            }

            if (inputString.equalsIgnoreCase("list")) {
                handleListInput(scanner, lsts);
            } else {
                Echo.echoMsg(inputString);
            }
        }
    }

    private void handleListInput(Scanner scanner, Map<String, Listing> lsts) {
        try {
            if (lsts.isEmpty()) {
                File file = new File("../data/datafile.txt");
                if (file.length() == 0) {
                    System.out.println(HR_LINE + CYAN + "\tNo existing list found, would you like to start a new list?\n" +
                            "\t\t[y/n]\n" + RESET + HR_LINE);
                    String response = scanner.nextLine().trim();
                    if (response.equalsIgnoreCase("y")) {
                        Listing.createNewList(scanner, lsts);
                    } else if (!response.equalsIgnoreCase("n")) {
                        throw new InvalidInputException("Invalid response");
                    } else {
                        System.out.println(HR_LINE + CYAN + "\tOK.\n" + RESET + HR_LINE_OUT);
                    }
                } else {
                    Map<String, Listing> cache = Listing.readListingFromFile(file);
                    System.out.println(HR_LINE + CYAN + "\tCreate new list? [a],\n" +
                            "\tQuery an existing list? [b],\n" +
                            "\tDelete an existing list? [c],\n" +
                            "\tor Sort lists? [d]\n" + RESET + HR_LINE);
                    String response = scanner.nextLine().trim();
                    switch (response.toLowerCase()) {
                        case "a" -> Listing.createNewList(scanner, cache);
                        case "b" -> Listing.queryExistingList(scanner, cache);
                        case "c" -> Listing.handleRemoveList(scanner, cache);
                        case "d" -> lsts = Listing.handleSortLists(scanner, cache);
                        default -> throw new InvalidInputException("Invalid response");
                    }
                }
            } else {
                System.out.println(HR_LINE + CYAN + "\tCreate new list? [a],\n" +
                        "\tQuery an existing list? [b],\n" +
                        "\tDelete an existing list? [c],\n" +
                        "\tor Sort lists? [d]\n" + RESET + HR_LINE);
                String response = scanner.nextLine().trim();
                switch (response.toLowerCase()) {
                    case "a" -> Listing.createNewList(scanner, lsts);
                    case "b" -> Listing.queryExistingList(scanner, lsts);
                    case "c" -> Listing.handleRemoveList(scanner, lsts);
                    case "d" -> lsts = Listing.handleSortLists(scanner, lsts);
                    default -> throw new InvalidInputException("Invalid response");
                }
            }
            Greeting.greetingMsg();
        } catch (InvalidInputException e) {
            System.out.println(HR_LINE + RED + "\tError: " + e.getMessage() + "\n" + RESET + HR_LINE_OUT);
        } catch (Exception e) {
            System.out.println(HR_LINE + RED + "\tUnexpected error occurred: " + e.getMessage() + "\n" + RESET + HR_LINE_OUT);
            e.printStackTrace();
        }
    }
}
