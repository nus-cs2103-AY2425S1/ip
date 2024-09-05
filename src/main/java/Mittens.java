import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Mittens {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    
    public Mittens(String storageFilePath) {
        this.ui = new TextUi();
        this.storage = new Storage(storageFilePath);
        this.taskList = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            this.taskList = this.storage.load();
        } catch (StorageFileException e) {
            e.echo();
            
            System.out.print("Would you like to continue with a new list instead? (y/n)\n> ");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                this.taskList = new TaskList();
            } else {
                return;
            }
        } catch (Exception e) {
            InitializationException newException = new InitializationException(e.getMessage());
            newException.echo();
            return;
        }
        
        ui.showGreetingMessage();

        while (true) {
            String input = ui.getUserInput();
            
            Command command = null;
            try {
                if (input.equals("bye")) {
                    command = new ExitCommand();
                } else if (input.equals("list")) {
                    command = new ListCommand();
                } else if (input.startsWith("mark")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]);
                        command = new MarkCommand(index);
                    } catch (NumberFormatException e) {
                        throw new BadInputException("Argument for command 'mark' must be a number");
                    }
                } else if (input.startsWith("unmark")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]);
                        command = new UnmarkCommand(index);
                    } catch (NumberFormatException e) {
                        throw new BadInputException("Argument for command 'mark' must be a number");
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]);
                        command = new DeleteCommand(index);
                    } catch (NumberFormatException e) {
                        throw new BadInputException("Argument for command 'delete' must be a number");
                    }
                } else if (input.startsWith("todo")) {
                    String description = input.substring(5);

                    Todo newTodo = new Todo(description);
                    command = new AddCommand(newTodo);
                } else if (input.startsWith("deadline")) {
                    // Separate the inputs so that the first element contains the description while
                    // the rest contains flags.
                    String[] inputs = input.split(" /");
                    String description = inputs[0].substring(9);
                    
                    LocalDate by = null;
                    for (int i = 1; i < inputs.length; i++) {
                        String[] flagWords = inputs[i].split(" ");
                        if (flagWords[0].equals("by")) {
                            if (by == null) {
                                try {
                                    by = LocalDate.parse(inputs[i].substring(3));
                                } catch (DateTimeParseException e) {
                                    throw new BadInputException("Invalid date format for 'by' flag");
                                }
                            } else {
                                throw new BadInputException("Found duplicate of 'by' flag");
                            }
                        } else {
                            throw new BadInputException("'%s' is not a known flag".formatted(flagWords[0]));
                        }
                    }
                    
                    if (by == null) {
                        throw new BadInputException("Command 'deadline' must have a 'by' flag");
                    }

                    Deadline newDeadline = new Deadline(description, by);
                    command = new AddCommand(newDeadline);
                } else if (input.startsWith("event")) {
                    // Separate the inputs so that the first element contains the description while
                    // the rest contains flags.
                    String[] inputs = input.split(" /");
                    String description = inputs[0].substring(6);

                    LocalDate from = null;
                    LocalDate to = null;
                    for (int i = 1; i < inputs.length; i++) {
                        String[] flagWords = inputs[i].split(" ");
                        if (flagWords[0].equals("from")) {
                            if (from == null) {
                                try {
                                    from = LocalDate.parse(inputs[i].substring(5));
                                } catch (DateTimeParseException e) {
                                    throw new BadInputException("Invalid date format for 'from' flag");
                                }
                            } else {
                                throw new BadInputException("Found duplicate of 'from' flag");
                            }
                        } else if (flagWords[0].equals("to")) {
                            if (to == null) {
                                try {
                                    to = LocalDate.parse(inputs[i].substring(3));
                                } catch (DateTimeParseException e) {
                                    throw new BadInputException("Invalid date format for 'to' flag");
                                }
                            } else {
                                throw new BadInputException("Found duplicate of 'to' flag");
                            }
                        } else {
                            throw new BadInputException("'%s' is not a known flag".formatted(flagWords[0]));
                        }
                    }

                    if (from == null) {
                        throw new BadInputException("Command 'event' must have a 'from' flag");
                    }

                    if (to == null) {
                        throw new BadInputException("Command 'event' must have a 'to' flag");
                    }

                    Event newEvent = new Event(description, from, to);
                    command = new AddCommand(newEvent);
                } else {
                    throw new BadInputException("'%s' is not a known command".formatted(input));
                }
            } catch (MittensException e) {
                e.echo();
            } catch (Exception e) {
                UnknownException newException = new UnknownException(e.getMessage());
                newException.echo();
            }

            if (command != null) {
                // This should always happen; the if statement is just to remove the NullPointerException warning.
                command.execute(this.taskList, this.ui, this.storage);
                if (command.isExit()) {
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Mittens mittens = new Mittens("data/data.txt");
        mittens.run();
    }
}
