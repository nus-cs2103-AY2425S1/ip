//import sigmabot.exceptions.InvalidInputException;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.*;
//import java.io.FileInputStream;
//public class Listing extends Command{
//    private LinkedList<sigmabot.task.Task> lst = new LinkedList<>();
//    private String tag;
//    private static int index = 1;
//    public Listing() {}
//
//    public Listing(String tag) {
//        this.tag = tag;
//    }
//
//    public Listing(LinkedList<sigmabot.task.Task> lst) {
//        this.lst = lst;
//    }
//
//    public void addItem(sigmabot.task.Task task) {
//        this.lst.add(task);
//    }
//
//    public void clearList() {
//        lst.clear();
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
//
//    public LinkedList<sigmabot.task.Task> getList() {
//        return lst;
//    }
//
//    public void setList(LinkedList<sigmabot.task.Task> lst) {
//        this.lst = lst;
//    }
//
//    @Override
//    public String toString() {
//        return printList();
//    }
//
//    public void writeListingToFile(File filePath) {
//
//        if (!filePath.getParentFile().exists()) {
//            filePath.getParentFile().mkdirs();
//        }
//
//        try (FileWriter writer = new FileWriter(filePath, true)) {
//            String content = printList() + "\n";
//            writer.write(content);
//            System.out.println("File written successfully to " + filePath.getAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Error writing to file: " + filePath.getAbsolutePath());
//            e.printStackTrace();
//        }
//    }
//
//    public static void clearCache(String filePath) {
//        File file = new File(filePath);
//        try (FileWriter writer = new FileWriter(file, false)) {
//
//            writer.write("");
//            System.out.println("File cleared successfully: " + file.getAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Error clearing file: " + file.getAbsolutePath());
//            e.printStackTrace();
//        }
//    }
//
//    public static Map<String, Listing> readListingFromFile(File filePath) {
//        Map<String, Listing> listings = new HashMap<>();
//        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
//            Listing currentListing = null;
//            String currentTag = null;
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine().trim();
//                if (line.startsWith("——————————————————————————————————————————————————————————————————————————————")) {
//                    continue;
//                }
//                if (line.endsWith(":")) {
//                    if (currentListing != null && currentTag != null) {
//
//                        listings.put(currentTag, currentListing);
//                    }
//                    currentTag = line.substring(0, line.length() - 1).trim();
//                    currentListing = new Listing(currentTag);
//                } else if (line.startsWith("No items found")) {
//                    if (currentListing != null && currentTag != null) {
//                        listings.put(currentTag, currentListing);
//                    }
//                    currentListing = null;
//                    currentTag = null;
//                } else if (line.matches("\\d+\\. \\[.*\\] .*")) {
//                    if (currentListing != null) {
//                        sigmabot.task.Task task = parseTask(line, scanner);
//                        currentListing.addItem(task);
//                    }
//                }
//            }
//            if (currentListing != null && currentTag != null) {
//                listings.put(currentTag, currentListing);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading from file: " + filePath.getAbsolutePath());
//            e.printStackTrace();
//        }
//        return listings;
//    }
//
//    public static String toNumeric(String abbrev) {
//        switch (abbrev.toUpperCase()) {
//            case "JAN" -> {
//                return "01";
//            }
//            case "FEB" -> {
//                return "02";
//            }
//            case "MAR" -> {
//                return "03";
//            }
//            case "APR" -> {
//                return "04";
//            }
//            case "MAY" -> {
//                return "05";
//            }
//            case "JUN" -> {
//                return "06";
//            }
//            case "JUL" -> {
//                return "07";
//            }
//            case "AUG" -> {
//                return "08";
//            }
//            case "SEP" -> {
//                return "09";
//            }
//            case "OCT" -> {
//                return "10";
//            }
//            case "NOV" -> {
//                return "11";
//            }
//            case "DEC" -> {
//                return "12";
//            }
//            default -> {
//                throw new IllegalArgumentException("Invalid month abbreviation: " + abbrev);
//            }
//        }
//    }
//
//
//    private static sigmabot.task.Task parseTask(String taskLine, Scanner scanner) {
//        String[] parts = taskLine.split(" ", 3);
//        String typeIndicator = parts[1].substring(1, 2);
//        String nameDescription = parts[2].split("\n\tDescription: ")[0].substring(4).trim();
//        String description = scanner.nextLine().trim().replace("Description: ", "");
//        switch (typeIndicator) {
//            case "T":
//                return new sigmabot.task.Todo(nameDescription, description);
//            case "E":
//                String startTime = "", endTime = "", location = "";
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine().trim();
//                    if (line.startsWith("Start Time:")) {
//                        startTime = line.substring(11).trim();
//                    } else if (line.startsWith("End Time:")) {
//                        endTime = line.substring(9).trim();
//                    } else if (line.startsWith("Location:")) {
//                        location = line.substring(9).trim();
//                    } else if (line.startsWith("——————————————————————————————————————————————————————————————————————————————")) {
//                        break;
//                    }
//                }
//                return new sigmabot.task.Event(nameDescription, description, startTime, endTime, location);
//            case "D":
//                String byTime = "";
//                while (scanner.hasNextLine()) {
//                    String line = scanner.nextLine().trim();
//                    if (line.startsWith("By:")) {
//                        byTime = line.substring(3).trim();
//                    } else if (line.startsWith("——————————————————————————————————————————————————————————————————————————————")) {
//                        break;
//                    }
//                }
//                String[] parsedDate = byTime.split("/");
//                String month = toNumeric(parsedDate[1]);
//                String day = parsedDate[0];
//                String year = parsedDate[2];
//                String date = year + "-" + month + "-" + day;
//                LocalDate formattedDate = LocalDate.parse(date);
//                return new sigmabot.task.Deadline(nameDescription, description, formattedDate);
//            default:
//                throw new IllegalArgumentException("Unknown task type: " + typeIndicator);
//        }
//    }
//    public String printList() {
//        if (this.lst.isEmpty()) {
//            return SigmaBot.HR_LINE + tag + ":\n" + "\tNo items found in current List\n" + SigmaBot.HR_LINE;
//        }
//        StringBuilder result = new StringBuilder();
//        if (tag != null) {
//            result.append(tag).append(":\n");
//        }
//        index = 1;
//
//        for (sigmabot.task.Task task : lst) {
//            result.append(index++).append(". ").append(task.toString()).append("\n");
//        }
//        return SigmaBot.HR_LINE + result.toString() + SigmaBot.HR_LINE;
//    }
//
//    public String printAndClearList() {
//        if (this.lst.isEmpty()) {
//            return SigmaBot.HR_LINE_IN + "\tNo items found in current List\n" + SigmaBot.HR_LINE_OUT;
//        }
//        StringBuilder result = new StringBuilder();
//        if (tag != null) {
//            result.append(tag).append(":\n");
//        }
//        for (int index = 0; index < lst.size(); index++) {
//            result.append(index + 1).append(". ").append(lst.get(index).toString()).append("\n");
//        }
//        lst.clear();
//        return result.toString();
//    }
//
//
//    public static void createNewList(Scanner scanner, Map<String, Listing> lsts) throws InvalidInputException {
//        Listing newList = Listing.createListEventChain(scanner, lsts);
//        lsts.put(newList.getTag(), newList);
//        newList.addItemEventChain(scanner);
//    }
//
//    public static void queryExistingList(Scanner scanner, Map<String, Listing> lsts) {
//        if (lsts.isEmpty()) {
//            System.out.println(SigmaBot.HR_LINE + SigmaBot.RED + "\tNo lists available.\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//            return;
//        }
//
//        System.out.println(SigmaBot.HR_LINE + SigmaBot.CYAN + "\tAvailable lists by tag:" + SigmaBot.RESET);
//        lsts.keySet().forEach(tag -> System.out.println("\t\t" + tag));
//        System.out.println(SigmaBot.HR_LINE);
//        System.out.println(SigmaBot.HR_LINE + SigmaBot.CYAN + "\tEnter the tag of the list you want to query:\n" + SigmaBot.RESET + SigmaBot.HR_LINE);
//        String tag = scanner.nextLine().trim();
//
//        if (lsts.containsKey(tag)) {
//            Listing listing = lsts.get(tag);
//            System.out.println(listing.printList());
//
//            System.out.println(SigmaBot.HR_LINE_IN + "\tWould you like to [a] Mark tasks as done or [b] Delete tasks?\n" + SigmaBot.HR_LINE_OUT);
//            String action = scanner.nextLine().trim().toLowerCase();
//
//            if (action.equals("a")) {
//                listing.markDoneEventChain(scanner);
//            } else if (action.equals("b")) {
//                listing.deleteItemEventChain(scanner, tag);
//            } else {
//                System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.RED + "\tInvalid response. Returning to main menu.\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//            }
//        } else {
//            System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.RED + "\tNo list found with the tag \"" + tag + "\".\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//        }
//    }
//
//    public static void handleRemoveList(Scanner scanner, Map<String, Listing> lsts) {
//        if (lsts.isEmpty()) {
//            System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.RED + "\tNo lists available to remove.\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//            return;
//        }
//
//        System.out.println(SigmaBot.HR_LINE + SigmaBot.CYAN + "\tAvailable lists by tag:" + SigmaBot.RESET);
//        lsts.keySet().forEach(tag -> System.out.println("\t\t" + tag));
//
//        System.out.println(SigmaBot.HR_LINE + SigmaBot.CYAN + "\tEnter the tag of the list you want to remove:\n" + SigmaBot.RESET + SigmaBot.HR_LINE);
//        String tag = scanner.nextLine().trim();
//
//        if (lsts.containsKey(tag)) {
//            lsts.remove(tag);
//            System.out.println(SigmaBot.HR_LINE + SigmaBot.CYAN + "\tList with tag \"" + tag + "\" has been removed.\n" + SigmaBot.RESET + SigmaBot.HR_LINE);
//        } else {
//            System.out.println(SigmaBot.HR_LINE + SigmaBot.RED + "\tNo list found with the tag \"" + tag + "\".\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//        }
//    }
//
//    public static Map<String, Listing> handleSortLists(Scanner scanner, Map<String, Listing> lsts) {
//        if (lsts.isEmpty()) {
//            System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.RED + "\tNo lists available to sort.\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//            return lsts;
//        }
//
//        System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.CYAN + "\tSort by [a] Tag or [b] Length?\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//        String sortOption = scanner.nextLine().trim();
//
//        List<Map.Entry<String, Listing>> sortedList = new LinkedList<>(lsts.entrySet());
//
//
//        if (sortOption.equalsIgnoreCase("a")) {
//            sortedList.sort(Map.Entry.comparingByKey());
//        } else if (sortOption.equalsIgnoreCase("b")) {
//            sortedList.sort(Comparator.comparingInt(entry -> entry.getValue().getList().size()));
//        } else {
//            System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.RED + "\tInvalid sorting option.\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//            return lsts;
//        }
//
//        System.out.println(SigmaBot.HR_LINE_IN + SigmaBot.CYAN + "\tSorted lists:\n" + SigmaBot.RESET + SigmaBot.HR_LINE_OUT);
//
//
//        Map<String, Listing> sortedMap = new LinkedHashMap<>();
//        for (Map.Entry<String, Listing> entry : sortedList) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//
//
//        clearCache("../data/tasks.txt");
//
//
//        for (Listing listing : sortedMap.values()) {
//            listing.writeListingToFile(new File("../data/tasks.txt"));
//        }
//
//        System.out.println(SigmaBot.HR_LINE_OUT);
//
//        return sortedMap;
//    }
//
//    public static Listing createListEventChain(Scanner sc, Map<String, Listing> lsts) {
//        System.out.println(SigmaBot.HR_LINE + "\tEnter a tag for the new list (or press Enter to skip):\n" + SigmaBot.HR_LINE);
//        String tag = sc.nextLine().trim();
//        if (tag.isEmpty()) {
//            int defaultTagIndex = 1;
//            while (lsts.containsKey("list_" + defaultTagIndex)) {
//                defaultTagIndex++;
//            }
//            tag = "list_" + defaultTagIndex;
//            System.out.println(SigmaBot.HR_LINE + "\tNew list created with default tag: " + tag + "\n" + SigmaBot.HR_LINE);
//        } else if (lsts.containsKey(tag)) {
//            System.out.println(SigmaBot.HR_LINE + "\tTag already exists. Please choose a different tag.\n" + SigmaBot.HR_LINE);
//            return createListEventChain(sc, lsts);
//        } else {
//            System.out.println(SigmaBot.HR_LINE + "\tNew list \"" + tag + "\" created\n" + SigmaBot.HR_LINE);
//        }
//        return new Listing(tag);
//    }
//
//    public void addItemEventChain(Scanner sc) throws InvalidInputException {
//        System.out.println(SigmaBot.HR_LINE + "\tAdd tasks to list: (Stop by entering \"EXIT\")");
//        while (true) {
//            System.out.println("\tEnter task type and name (format: [type] name, e.g., 'todo Buy milk') or EXIT to finish:\n" + SigmaBot.HR_LINE_DOUBLE);
//            String input = sc.nextLine().trim();
//            if (input.equalsIgnoreCase("EXIT")) {
//                writeListingToFile(new File("../data/tasks.txt"));
//                System.out.println(SigmaBot.HR_LINE + "\tComplete\n" + SigmaBot.HR_LINE_OUT);
//                break;
//            }
//
//            String[] parts = input.split(" ", 2);
//            if (parts.length < 2) {
//                System.out.println(SigmaBot.HR_LINE + "\tInvalid format\n" + SigmaBot.HR_LINE_OUT);
//                continue;
//            }
//
//            String type = parts[0].toLowerCase();
//            String name = parts[1];
//
//            System.out.println(SigmaBot.HR_LINE + "\tEnter task description:\n" + SigmaBot.HR_LINE);
//            String description = sc.nextLine().trim();
//
//            sigmabot.task.Task task;
//            switch (type) {
//                case "todo":
//                    task = new sigmabot.task.Todo(name, description);
//                    break;
//                case "event":
//                    System.out.println(SigmaBot.HR_LINE + "\tEnter event start time:\n" + SigmaBot.HR_LINE);
//                    String startTime = sc.nextLine().trim();
//
//                    System.out.println(SigmaBot.HR_LINE + "\tEnter event end time:\n" + SigmaBot.HR_LINE);
//                    String endTime = sc.nextLine().trim();
//
//                    System.out.println(SigmaBot.HR_LINE + "\tEnter event location:\n" + SigmaBot.HR_LINE);
//                    String location = sc.nextLine().trim();
//
//                    task = new sigmabot.task.Event(name, description, startTime, endTime, location);
//                    break;
//                case "deadline":
//                    System.out.println(SigmaBot.HR_LINE + "\tEnter deadline time: (yyyy-mm-dd)\n" + SigmaBot.HR_LINE);
//                    String byTime = sc.nextLine().trim();
//                    task = new sigmabot.task.Deadline(name, description, LocalDate.parse(byTime));
//                    break;
//                default:
//                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid task type. Please enter 'todo', 'event', or 'deadline'.\n" + SigmaBot.HR_LINE_OUT);
//                    continue;
//            }
//            addItem(task);
//            System.out.println(SigmaBot.HR_LINE + "\tTask added successfully. Total tasks in the list: " + getList().size() + "\n" + SigmaBot.HR_LINE);
//        }
//    }
//    public void markDone(int idx) {
//        if (idx < 0 || idx >= lst.size()) {
//            System.out.println(SigmaBot.HR_LINE + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
//            return;
//        }
//        sigmabot.task.Task task = lst.get(idx);
//        if (!task.isDone()) {
//            task.markDone();
//            System.out.println(SigmaBot.HR_LINE_IN + "\tTask marked as done: " + task.getName() + "\n" + SigmaBot.HR_LINE_OUT);
//        } else {
//            System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: sigmabot.task.Task already marked as done\n" + SigmaBot.HR_LINE_OUT);
//        }
//    }
//
//    public void markDoneEventChain(Scanner scanner) {
//        System.out.println(SigmaBot.HR_LINE_IN + "\tMark tasks as done in list: \"" + tag + "\". Enter -1 to stop or exit.\n" + SigmaBot.HR_LINE_OUT);
//        while (true) {
//            try {
//                int idx = scanner.nextInt();
//                scanner.nextLine();
//                if (idx == -1) {
//                    return;
//                }
//                if (idx - 1 < 0 || idx - 1 >= lst.size()) {
//                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
//                    continue;
//                } else {
//                    this.markDone(idx - 1);
//                }
//            } catch (InputMismatchException e) {
//                System.out.println(SigmaBot.HR_LINE_IN + "\tPlease enter a valid number.\n" + SigmaBot.HR_LINE_OUT);
//                scanner.nextLine();
//            }
//        }
//    }
//    public void deleteItemEventChain(Scanner scanner, String tag) {
//        System.out.println(SigmaBot.HR_LINE_IN + "\tDelete tasks from list: \"" + tag + "\". Enter -1 to stop or exit.\n" + SigmaBot.HR_LINE_OUT);
//        while (true) {
//            try {
//                int idx = scanner.nextInt();
//                scanner.nextLine();
//                if (idx == -1) {
//                    return;
//                }
//                if (idx - 1 < 0 || idx - 1 >= lst.size()) {
//                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
//                    continue;
//                } else {
//                    sigmabot.task.Task removedTask = lst.remove(idx - 1);
//                    System.out.println(SigmaBot.HR_LINE_IN + "\tTask deleted: " + removedTask.getName() + "\n" + SigmaBot.HR_LINE_OUT);
//                }
//            } catch (InputMismatchException e) {
//                System.out.println(SigmaBot.HR_LINE_IN + "\tPlease enter a valid number.\n" + SigmaBot.HR_LINE_OUT);
//                scanner.nextLine();
//            }
//        }
//    }
//
//    private void updateDataFileAfterDeletion() {
//        File filePath = new File("../data/tasks.txt");
//        StringBuilder newContent = new StringBuilder();
//        boolean inCurrentList = false;
//        try (Scanner fileScanner = new Scanner(new FileInputStream(filePath))) {
//            while (fileScanner.hasNextLine()) {
//                String line = fileScanner.nextLine();
//                if (line.trim().equals(tag + ":")) {
//                    inCurrentList = true;
//                    newContent.append(line).append("\n");
//                    continue;
//                }
//                if (line.startsWith("——————————————————————————————————————————————————————————————————————————————") && inCurrentList) {
//                    newContent.append(line).append("\n");
//                    newContent.append(printList());
//                    inCurrentList = false;
//                    continue;
//                }
//                if (!inCurrentList) {
//                    newContent.append(line).append("\n");
//                }
//            }
//            try (FileWriter writer = new FileWriter(filePath, false)) {
//                writer.write(newContent.toString());
//            }
//        } catch (IOException e) {
//            System.err.println("Error updating file: " + filePath.getAbsolutePath());
//            e.printStackTrace();
//        }
//    }
//}
