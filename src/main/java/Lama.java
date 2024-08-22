import java.util.Scanner;

public class Lama {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String bar = "____________________________________________________________";
        Task[] list = new Task[100];
        int count = 0;

        System.out.println(bar);
        System.out.println("Hello! I'm Lama");
        System.out.println("What can I do for you?");
        System.out.println(bar + "\n");

        label:
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                String[] words = input.split(" ", 2);

                switch (words[0]) {

                    case "bye":
                        System.out.println(bar);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(bar);
                        break label;
                    case "list":
                        System.out.println(bar);
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + "." + list[i]);
                        }
                        System.out.println(bar + "\n");
                        break;

                    case "mark": {
                        if (words.length < 2) {
                            throw new LamaException("Please specify the number that wanted to be marked as done!");
                        }

                        int n = Integer.parseInt(words[1]) - 1;

                        if (Integer.parseInt(words[1]) > count || Integer.parseInt(words[1]) <= 0) {
                            throw new LamaException("Sorry, the number given exceed the bound of list");
                        }
                        list[n].markAsDone();
                        System.out.println(bar);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + list[n]);
                        System.out.println(bar + "\n");
                        break;
                    }

                    case "unmark": {
                        if (words.length < 2) {
                            throw new LamaException("Please specify the number that wanted to be unmarked!");
                        }

                        int n = Integer.parseInt(words[1]) - 1;
                        if (Integer.parseInt(words[1]) > count || Integer.parseInt(words[1]) <= 0) {
                            throw new LamaException("Sorry, the number given exceed the bound of list");
                        }

                        list[n].markAsUnDone();
                        System.out.println(bar);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + list[n]);
                        System.out.println(bar + "\n");
                        break;
                    }

                    case "todo":
                        if (words.length < 2) {
                            throw new LamaException("Please specify the description of TODO!");
                        }
                        list[count] = new Todo(words[1]);
                        count++;
                        System.out.println(bar);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + list[count - 1]);
                        System.out.println("Now you have " + count + " tasks in the list.");
                        System.out.println(bar + "\n");

                        break;

                    case "deadline":
                        if (words.length < 2) {
                            throw new LamaException("Please specify the description of deadline!");
                        }
                        String[] half = words[1].split(" /by ");

                        if (half.length < 2) {
                            throw new LamaException("Please specify the date of deadline in the format of:\n" +
                                    "deadline [description] /by [date]");
                        }
                        list[count] = new Deadline(half[0], half[1]);
                        count++;
                        System.out.println(bar);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + list[count - 1]);
                        System.out.println("Now you have " + count + " tasks in the list.");
                        System.out.println(bar + "\n");
                        break;

                    case "event":
                        if (words.length < 2) {
                            throw new LamaException("Please specify the description of event in the format of:\n" +
                                    "event [description] /from [start time] /to [end time]");
                        }

                        String[] first = words[1].split(" /from ");

                        if (first.length < 2) {
                            throw new LamaException("Please specify the start time of event in the format of:\n" +
                                    "event [description] /from [start time] /to [end time]");
                        }

                        String[] time = first[1].split(" /to ");

                        if (time.length < 2) {
                            throw new LamaException("Please specify the start time of event in the format of:\n" +
                                    "event [description] /from [start time] /to [end time]");
                        }

                        list[count] = new Event(first[0], time[0], time[1]);
                        count++;
                        System.out.println(bar);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + list[count - 1]);
                        System.out.println("Now you have " + count + " tasks in the list.");
                        System.out.println(bar + "\n");
                        break;

                    default:
                        throw new LamaException("Sorry, I don't know what you want to do!\n" +
                                "You can either choose to use:\n" +
                                "1. todo [Your TODO]\n" +
                                "2. deadline [Your TODO] /by [date of deadline]\n" +
                                "3. event [Your event] /from [start time] /to [end time]\n" +
                                "4. list\n" +
                                "5. mark [number of todo in the list]\n" +
                                "6. unmark [number of todo in the list]\n" +
                                "7. bye");
                }
            } catch (LamaException e) {
                System.out.println(bar);
                System.out.println(e.getMessage());
                System.out.println(bar + "\n");
            }

        }

        scanner.close();

    }
}
