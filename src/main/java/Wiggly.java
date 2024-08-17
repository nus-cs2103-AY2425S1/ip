import java.util.Scanner;

public class Wiggly {

    private enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN
    }

    public static void main(String[] args) {

        // unused logo
        /*
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
        */
        System.out.println(
                """
                ____________________________________
                Hello! I'm Wiggly
                What can I do for you?
                ____________________________________
                """);

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String in;
        String[] parts;
        Command command;

        boolean running = true;
        while (running) {

            in = sc.nextLine();
            parts = in.split(" ", 2);
            try {
                command = Command.valueOf(parts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                command = Command.UNKNOWN;
            }

            switch (command) {
                case LIST -> {
                    System.out.println(taskList);
                }
                case BYE -> {
                    System.out.println(
                            """
                            ____________________________________
                            Bye. Hope to see you again soon!
                            ____________________________________
                            """
                    );
                    running = false;
                }
                case MARK -> {

                    try {
                        int value = Integer.parseInt(parts[1]);
                        if (value > taskList.getSize() || value <= 0) {
                            System.out.println(
                                    """
                                    ____________________________________
                                    There's no such task number!
                                    ____________________________________
                                    """
                            );
                        } else {
                            System.out.println(taskList.markDone(value));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, invalid number format detected
                                ____________________________________
                                """
                        );
                    }
                }
                case UNMARK -> {

                    try {
                        int value = Integer.parseInt(parts[1]);
                        if (value > taskList.getSize() || value <= 0) {
                            System.out.println(
                                    """
                                    ____________________________________
                                    There's no such task number!
                                    ____________________________________
                                    """
                            );
                        } else {
                            System.out.println(taskList.markUndone(value));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, invalid number format detected
                                ____________________________________
                                """
                        );
                    }
                }
                case TODO -> {
                    try {
                        String taskDescription = parts[1];
                        System.out.println(taskList.addTask(new ToDo(taskDescription)));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, missing todo description
                                ____________________________________
                                """
                        );
                    }

                }
                case DEADLINE -> {
                    try {
                        parts = parts[1].split(" /by ", 2);
                        String taskDescription = parts[0];
                        String by = parts[1];
                        System.out.println(taskList.addTask(new Deadline(taskDescription, by)));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, missing deadline description or by
                                ____________________________________
                                """
                        );
                    }
                }
                case EVENT -> {
                    try {
                        parts = parts[1].split(" /from | /to ", 3);
                        String taskDescription = parts[0];
                        String from = parts[1];
                        String to = parts[2];
                        System.out.println(taskList.addTask(new Event(taskDescription, from, to)));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, missing event description, from or to
                                ____________________________________
                                """
                        );
                    }
                }
                case DELETE -> {

                    try {
                        int value = Integer.parseInt(parts[1]);
                        if (value > taskList.getSize() || value <= 0) {
                            System.out.println(
                                    """
                                    ____________________________________
                                    There's no such task number!
                                    ____________________________________
                                    """
                            );
                        } else {
                            System.out.println(taskList.deleteTask(value));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(
                                """
                                ____________________________________
                                Oops, invalid number format detected
                                ____________________________________
                                """
                        );
                    }
                }
                case UNKNOWN -> System.out.println(
                        """
                        ____________________________________
                        Sorry!! I don't know this command :(
                        ____________________________________
                        """
                );
            }
        }

    }

}
