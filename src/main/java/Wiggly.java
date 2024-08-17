import java.util.Scanner;

public class Wiggly {
    public static void main(String[] args) {
        String logo = """
                #%@@@@@@@@@@@#####**********##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%@%#**@@@@@@@@@@@@@@@@@@@@@
                ##%@@@@@@@@@%**####**********#%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%##**%##@@@@@@@@@@@@@@@@@@@@@
                ###%@@@@@@@@##***##***********%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#**#%@@%#%@@@@@@@@@@@@@@@@@@@@@
                ####@@@@@@@@##@****##*********#@@@@@@%#**++*#%@@@@@@@@@@@@@@@@@@%#***#%@@@@@##@@@@@@@@@@@@@@@@@@@@@@
                ####%@@@@@@@##@@*****##*******#%@@@%**********#@@@@@@@@@@@@@@@%***#%@@@@@@@##@@@@@@@@@@@@@@@@@@@@@@@
                #####%@@@@@@%*%@@%*****##******%@@%********###*@@@@@@@@@@@@@#***%@@@@@@@@@##%@@@@@@@@@@@@@@@@@@@@@@@
                ######@@@@@@@**@@@@#******#****#@@******#*+**#%@@@@@@@@@@@@***%@@@@@@@@@%*#%@@@@@@@@@@@@@@@@@@@@%%@@
                #######@@@@@@%*#@@@@@#******##*#@@*******######%@@@@@@@@@@#*#@@@@@@@@@@%*#%@@@@@@@@@@@@@@@@@@@@%%%@@
                ##*####%@@@@@@#**@@@@@@%#*****##%@************###%%%@@@@@@+*@@@@@@@@@@#*#%@@@@@@@@@@@@@@@@@@@@%%%%@@
                ##***###%@@@@@@%**#@@@@@@@%#***#%@#***********##*#****##%%+%%@%@@@@%#*###%@@@@@@@@@@@@@@@@@@%%##%@@@
                ##****###@@@@@@@@#**#@@@@@@@@#*+*@%***********#************+**%##*###%#*#%@@@@@@@@@@@@@@@@@%###%@@@@
                **#*+**###@@@@@@@@@%**#%@@@@@@@#+**##***********+++******+***#%%%#**##**%@@@@@@@@@@@@@@@@@%###%@@@%%
                *****+**##%@@@@@@@@@@@##**#%%@%#***+*###********************#*%@#+*###*#%@@@@@@@@@@@@@@@@%#**#%@%%%@
                ******+**##@@@@@@@@@@@%#####**********######*****###%%%%#%#*#**%#**##**#%@@@@@@@@@@@@@@@%***#%@%%%@@
                ******++**#%@@@@@@@@@@@#####********####*#%####*#*####%%%%%*#**##%###*#%@@@@@@@@@@@@@@@%***#%%%#%%@@
                *******++**#@@@@@@@@@@@%##%**##########**%*****%*##******#*+%***##@#**%@@@@@@@@@@@@@@@%***#%%%#%%@@@
                #******+++*##@@@@@@@@@@@####%##########**@*****#%######%#**##+**###%*#%@@@@@@@@@@@@@@%#***%%%#%%@@@@
                %#***+**++**#%@@@@@@@@@@%###%####%%%%%#*#%++++++*%%%%%%%#%#*++**###%#%@@@@@@@@@@@@@@%#***%%###@@@@@@
                @@#***+**++**#@@@@@@@@@@@#####%###%%%####+++####++*#####*+++*+***###@@@@@@@@@@@@@@@%#***%%#*%@@@@@@@
                @@@#***+*+++**#@@@@@@@@@@%#%****######*++++#%%%%%#+++++++++*#+***###%@@@@@@@@@@@@@@#***#%#*%@@@@@@@@
                @@@@%***+++++*#%@@@@@@@@@@%%***+++++++++++++%#****%++++++++#++***####%@@@@@@@@@@@@##**#%#*#@@@@@@@@@
                @@@@@%***++++**#@@@@@@@@@@@#***+++++#+++++++##****%*++++++#*++**####%#@@@@@@@@@@@%##*###*#@@@@@@@@@@
                @@@@@@%****+++**%@@@@@@@@@%****++++%*++++++++*#####+++++++%++***####%#@@@@@@@@@@%########%@@@@@@@@@@
                @@@@@@@@#****+**#@@@@@@@@@*****+++##+++++++++++++++++++++*%++***###%##@@@@@@@@@%%%#####%@@@@@@@@@@@@
                @@@@@@@@@#****+**#@@@@@@@@*****++*%+***++++++++++++++++++*%++***##%%#*@@@@@@@@@%@@####%@@@@@@@@@@@@@
                %@@@@@@@@%#******#%##%%%%@%****+*%@%%%@@%%###%%######%%@@@@#+***#@@@@@%@%########%%#%%@@@@@@@@@@@@@@
                ##@@@@@@@@%##***%%##@%%%%@@%#**#@@@@%%%@@@##########@@%@%@@@@@@@@@@%@@%@@@#####%##%%@@@@@@@@@@@@@@@@
                ###%@@@@@@@%###%%###@@@@@@@@@@@@@@@@%%@@@%##########@@@%@@@@@@@@@@@%%%%@@%####%###@@@@@@@@@@@@@@@@@@
                """;
        System.out.println(
                """
                ____________________________________
                Hello! I'm Wiggly
                What can I do for you?
                ____________________________________
                """);

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        TaskList taskList = new TaskList();
        String[] parts = in.split(" ", 2);
        String command = parts[0];

        while (!command.equals("bye")) {

            switch (command) {
                case "list" -> System.out.println(taskList);
                case "mark" -> {

                    try {
                        int value = Integer.parseInt(parts[1]);
                        System.out.println(taskList.markDone(value));
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, invalid number format detected");
                    }
                }
                case "unmark" -> {

                    try {
                        int value = Integer.parseInt(parts[1]);
                        System.out.println(taskList.markUndone(value));
                    } catch (NumberFormatException e) {
                        System.out.println("Oops, invalid number format detected");
                    }
                }
                case "todo" -> taskList.addTask(new ToDo(parts[1]));
                case "deadline" -> {
                    parts = parts[1].split("/by ", 2);
                    String taskDescription = parts[0];
                    String by = parts[1];
                    taskList.addTask(new Deadline(taskDescription, by));
                }
                case "event" -> {
                    parts = parts[1].split("/from | /to ", 3);
                    String taskDescription = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    taskList.addTask(new Event(taskDescription, from, to));
                }
                default -> taskList.addTask(new Task(in));
            }

            in = sc.nextLine();
            parts = in.split(" ", 2);
            command = parts[0];
        }

        System.out.println(
                """
                ____________________________________
                Bye. Hope to see you again soon!
                ____________________________________
                """
        );

    }
}
