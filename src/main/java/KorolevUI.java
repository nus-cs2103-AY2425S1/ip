import java.util.Scanner;

public class KorolevUI {
    private final Scanner scanner = new Scanner(System.in);
    private final String newLogo = """
            Hello! I'm DukeKorolev
            What can I do for you?
            """;
    private final String end = "Bye. Hope to see you again soon!";
    private final String divider = "--------------------";

    private KorolevList repo = new KorolevList();

    public void run() {
        System.out.println(newLogo);
        repo.loadEvent();
        this.readCommands();
        System.out.println(end);
    }

    private void readCommands() {
        String input = "";
        while (true) {
            input = scanner.nextLine();
            String[] target = input.split("\\s");
            System.out.println(divider);
            if (input.equals("bye")) {
                repo.saveEvent();
                break;
            } else if (input.equals("list")) {
                System.out.println(repo.displayList());
            } else if (target[0].equals("unmark")) {
                try {
                    repo.unmarkEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else if (target[0].equals("mark")) {
                try {
                    repo.markEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else if (target[0].equals("delete")) {
                try {
                    repo.removeEvent(Integer.parseInt(target[1]) - 1);
                } catch (DukeException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Error:" + e.getMessage());
                }
            } else {
                try {
                    repo.addEvent(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println(divider);
        }
    }
}
