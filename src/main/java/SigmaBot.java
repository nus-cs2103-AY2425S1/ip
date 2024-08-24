import java.util.*;
import exceptions.InvalidInputException;
import exceptions.ListNotFoundException;
import exceptions.TaskAlreadyDoneException;
import exceptions.InvalidTaskTypeException;
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
            } else if (inputString.equalsIgnoreCase("remove")) {
                handleRemoveList(scanner, lsts);
            } else if (inputString.equalsIgnoreCase("sort")) {
                handleSortLists(scanner, lsts);
            } else {
                Echo.echoMsg(inputString);
            }
        }
    }

    private static void handleListInput(Scanner scanner, Map<String, Listing> lsts) {
        try {
            if (lsts.isEmpty()) {
                System.out.println(HR_LINE + CYAN + "\tNo existing list found, would you like to start a new list?\n" +
                        "\t\t[y/n]\n" + RESET + HR_LINE);
                String response = scanner.nextLine().trim();
                if (response.equalsIgnoreCase("y")) {
                    createNewList(scanner, lsts);
                } else if (!response.equalsIgnoreCase("n")) {
                    throw new InvalidInputException("Invalid response");
                } else {
                    System.out.println(HR_LINE + CYAN + "\tOK.\n" + RESET + HR_LINE_OUT);
                }
            } else {
                System.out.println(HR_LINE + CYAN + "\tCreate new list? [a],\n" +
                        "\tQuery an existing list? [b],\n" +
                        "\tDelete an existing list? [c],\n" +
                        "\tor Sort lists? [d]\n" + RESET + HR_LINE);
                String response = scanner.nextLine().trim();
                if (response.equalsIgnoreCase("a")) {
                    createNewList(scanner, lsts);
                } else if (response.equalsIgnoreCase("b")) {
                    queryExistingList(scanner, lsts);
                } else if (response.equalsIgnoreCase("c")) {
                    handleRemoveList(scanner, lsts);
                } else if (response.equalsIgnoreCase("d")) {
                    handleSortLists(scanner, lsts);
                } else {
                    throw new InvalidInputException("Invalid response");
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


    private static void createNewList(Scanner scanner, Map<String, Listing> lsts) throws InvalidInputException {
        Listing newList = Listing.createListEventChain(scanner, lsts);
        lsts.put(newList.getTag(), newList);
        newList.addItemEventChain(scanner);
    }

    private static void queryExistingList(Scanner scanner, Map<String, Listing> lsts) {
        if (lsts.isEmpty()) {
            System.out.println(SigmaBot.HR_LINE + RED + "\tNo lists available.\n" + RESET + SigmaBot.HR_LINE_OUT);
            return;
        }

        System.out.println(SigmaBot.HR_LINE + CYAN + "\tAvailable lists by tag:" + RESET);
        lsts.keySet().forEach(tag -> System.out.println("\t\t" + tag));
        System.out.println(SigmaBot.HR_LINE);
        System.out.println(SigmaBot.HR_LINE + CYAN + "\tEnter the tag of the list you want to query:\n" + RESET + SigmaBot.HR_LINE);
        String tag = scanner.nextLine().trim();

        if (lsts.containsKey(tag)) {
            Listing listing = lsts.get(tag);
            System.out.println(listing.printList());

            System.out.println(SigmaBot.HR_LINE_IN + "\tWould you like to [a] Mark tasks as done or [b] Delete tasks?\n" + SigmaBot.HR_LINE_OUT);
            String action = scanner.nextLine().trim().toLowerCase();

            if (action.equals("a")) {
                listing.markDoneEventChain(scanner);
            } else if (action.equals("b")) {
                listing.deleteItemEventChain(scanner);
            } else {
                System.out.println(SigmaBot.HR_LINE_IN + RED + "\tInvalid response. Returning to main menu.\n" + RESET + SigmaBot.HR_LINE_OUT);
            }
        } else {
            System.out.println(SigmaBot.HR_LINE_IN + RED + "\tNo list found with the tag \"" + tag + "\".\n" + RESET + SigmaBot.HR_LINE_OUT);
        }
    }

    private static void handleRemoveList(Scanner scanner, Map<String, Listing> lsts) {
        if (lsts.isEmpty()) {
            System.out.println(HR_LINE_IN + RED + "\tNo lists available to remove.\n" + RESET + HR_LINE_OUT);
            return;
        }

        System.out.println(HR_LINE + CYAN + "\tAvailable lists by tag:" + RESET);
        lsts.keySet().forEach(tag -> System.out.println("\t\t" + tag));

        System.out.println(HR_LINE + CYAN + "\tEnter the tag of the list you want to remove:\n" + RESET + HR_LINE);
        String tag = scanner.nextLine().trim();

        if (lsts.containsKey(tag)) {
            lsts.remove(tag);
            System.out.println(HR_LINE + CYAN + "\tList with tag \"" + tag + "\" has been removed.\n" + RESET + HR_LINE);
        } else {
            System.out.println(HR_LINE + RED + "\tNo list found with the tag \"" + tag + "\".\n" + RESET + HR_LINE_OUT);
        }
    }

    private static void handleSortLists(Scanner scanner, Map<String, Listing> lsts) {
        if (lsts.isEmpty()) {
            System.out.println(HR_LINE_IN + RED + "\tNo lists available to sort.\n" + RESET + HR_LINE_OUT);
            return;
        }

        System.out.println(HR_LINE_IN + CYAN + "\tSort by [a] Tag or [b] Length?\n" + RESET + HR_LINE_OUT);
        String sortOption = scanner.nextLine().trim();

        List<Map.Entry<String, Listing>> sortedList = new ArrayList<>(lsts.entrySet());

        if (sortOption.equalsIgnoreCase("a")) {
            sortedList.sort(Map.Entry.comparingByKey());
        } else if (sortOption.equalsIgnoreCase("b")) {
            sortedList.sort(Comparator.comparingInt(entry -> entry.getValue().getList().size()));
        } else {
            System.out.println(HR_LINE_IN + RED + "\tInvalid sorting option.\n" + RESET + HR_LINE_OUT);
            return;
        }

        System.out.println(HR_LINE_IN + CYAN + "\tSorted lists:\n" + RESET + HR_LINE_OUT);
        for (Map.Entry<String, Listing> entry : sortedList) {
            System.out.println(entry.getValue().printList());
        }
        System.out.println(HR_LINE_OUT);
    }
}
