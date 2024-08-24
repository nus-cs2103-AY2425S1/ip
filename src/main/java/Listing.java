import exceptions.InvalidInputException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;

public class Listing {
    private List<Task> lst = new LinkedList<>();
    private String tag;

    public Listing() {}

    public Listing(String tag) {
        this.tag = tag;
    }

    public void addItem(Task task) {
        lst.add(task);
    }

    public String printList() {
        if (this.lst.isEmpty()) {
            return SigmaBot.HR_LINE + "\tNo items found in current List\n" + SigmaBot.HR_LINE;
        }
        StringBuilder result = new StringBuilder();
        if (tag != null) {
            result.append(tag).append(":\n");
        }
        int index = 1;

        for (Task task : lst) {
            result.append(index++).append(". ").append(task.toString()).append("\n");
        }
        return SigmaBot.HR_LINE + result.toString() + SigmaBot.HR_LINE;
    }

    public String printAndClearList() {
        if (this.lst.isEmpty()) {
            return SigmaBot.HR_LINE_IN + "\tNo items found in current List\n" + SigmaBot.HR_LINE_OUT;
        }
        StringBuilder result = new StringBuilder();
        if (tag != null) {
            result.append(tag).append(":\n");
        }
        for (int index = 0; index < lst.size(); index++) {
            result.append(index + 1).append(". ").append(lst.get(index).toString()).append("\n");
        }
        lst.clear();
        return result.toString();
    }

    public void clearList() {
        lst.clear();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Task> getList() {
        return lst;
    }

    public void setList(List<Task> lst) {
        this.lst = lst;
    }

    @Override
    public String toString() {
        return printList();
    }

    public static Listing createListEventChain(Scanner sc, Map<String, Listing> lsts) {
        System.out.println(SigmaBot.HR_LINE + "\tEnter a tag for the new list (or press Enter to skip):\n" + SigmaBot.HR_LINE);
        String tag = sc.nextLine().trim();
        if (tag.isEmpty()) {
            int defaultTagIndex = 1;
            while (lsts.containsKey("list_" + defaultTagIndex)) {
                defaultTagIndex++;
            }
            tag = "list_" + defaultTagIndex;
            System.out.println(SigmaBot.HR_LINE + "\tNew list created with default tag: " + tag + "\n" + SigmaBot.HR_LINE);
        } else if (lsts.containsKey(tag)) {
            System.out.println(SigmaBot.HR_LINE + "\tTag already exists. Please choose a different tag.\n" + SigmaBot.HR_LINE);
            return createListEventChain(sc, lsts);
        } else {
            System.out.println(SigmaBot.HR_LINE + "\tNew list \"" + tag + "\" created\n" + SigmaBot.HR_LINE);
        }
        return new Listing(tag);
    }

    public void addItemEventChain(Scanner sc) throws InvalidInputException {
        System.out.println(SigmaBot.HR_LINE + "\tAdd tasks to list: (Stop by entering \"EXIT\")");
        while (true) {
            System.out.println("\tEnter task type and name (format: [type] name, e.g., 'todo Buy milk') or EXIT to finish:\n" + SigmaBot.HR_LINE_DOUBLE);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println(SigmaBot.HR_LINE + "\tComplete\n" + SigmaBot.HR_LINE_OUT);
                break;
            }

            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                throw new InvalidInputException("Invalid response");
            }

            String type = parts[0].toLowerCase();
            String name = parts[1];

            System.out.println(SigmaBot.HR_LINE + "\tEnter task description:\n" + SigmaBot.HR_LINE);
            String description = sc.nextLine().trim();

            Task task;
            switch (type) {
                case "todo":
                    task = new Todo(name, description);
                    break;
                case "event":
                    System.out.println(SigmaBot.HR_LINE + "\tEnter event start time:\n" + SigmaBot.HR_LINE);
                    String startTime = sc.nextLine().trim();

                    System.out.println(SigmaBot.HR_LINE + "\tEnter event end time:\n" + SigmaBot.HR_LINE);
                    String endTime = sc.nextLine().trim();

                    System.out.println(SigmaBot.HR_LINE + "\tEnter event location:\n" + SigmaBot.HR_LINE);
                    String location = sc.nextLine().trim();

                    task = new Event(name, description, startTime, endTime, location);
                    break;
                case "deadline":
                    System.out.println(SigmaBot.HR_LINE + "\tEnter deadline time:\n" + SigmaBot.HR_LINE);
                    String byTime = sc.nextLine().trim();

                    task = new Deadline(name, description, byTime);
                    break;
                default:
                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid task type. Please enter 'todo', 'event', or 'deadline'.\n" + SigmaBot.HR_LINE_OUT);
                    continue;
            }

            addItem(task);
            System.out.println(SigmaBot.HR_LINE + "\tTask added successfully. Total tasks in the list: " + getList().size() + "\n" + SigmaBot.HR_LINE);
        }
    }


    public void markDone(int idx) {
        if (idx < 0 || idx >= lst.size()) {
            System.out.println(SigmaBot.HR_LINE + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
            return;
        }

        Task task = lst.get(idx);
        if (!task.isDone()) {
            task.markDone();
            System.out.println(SigmaBot.HR_LINE_IN + "\tTask marked as done: " + task.getName() + "\n" + SigmaBot.HR_LINE_OUT);
        } else {
            System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: Task already marked as done\n" + SigmaBot.HR_LINE_OUT);
        }
    }

    public void markDoneEventChain(Scanner scanner) {
        System.out.println(SigmaBot.HR_LINE_IN + "\tMark tasks as done in list: \"" + tag + "\". Enter -1 to stop or exit.\n" + SigmaBot.HR_LINE_OUT);
        while (true) {
            try {
                int idx = scanner.nextInt();
                scanner.nextLine();
                if (idx == -1) {
                    return;
                }
                if (idx - 1 < 0 || idx - 1 >= lst.size()) {
                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
                    continue;
                } else {
                    this.markDone(idx - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println(SigmaBot.HR_LINE_IN + "\tPlease enter a valid number.\n" + SigmaBot.HR_LINE_OUT);
                scanner.nextLine();
            }
        }
    }

    public void deleteItemEventChain(Scanner scanner) {
        System.out.println(SigmaBot.HR_LINE_IN + "\tDelete tasks from list: \"" + tag + "\". Enter -1 to stop or exit.\n" + SigmaBot.HR_LINE_OUT);
        while (true) {
            try {
                int idx = scanner.nextInt();
                scanner.nextLine();
                if (idx == -1) {
                    return;
                }
                if (idx - 1 < 0 || idx - 1 >= lst.size()) {
                    System.out.println(SigmaBot.HR_LINE_IN + "\tInvalid response: Index out of bounds\n" + SigmaBot.HR_LINE_OUT);
                    continue;
                } else {
                    Task removedTask = lst.remove(idx - 1);
                    System.out.println(SigmaBot.HR_LINE_IN + "\tTask deleted: " + removedTask.getName() + "\n" + SigmaBot.HR_LINE_OUT);
                }
            } catch (InputMismatchException e) {
                System.out.println(SigmaBot.HR_LINE_IN + "\tPlease enter a valid number.\n" + SigmaBot.HR_LINE_OUT);
                scanner.nextLine();
            }
        }
    }

}
