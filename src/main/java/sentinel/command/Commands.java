package sentinel.command;

import java.time.LocalDate;

import sentinel.Sentinel;
import sentinel.SentinelException;
import sentinel.parser.Parser;

/**
 * Enumeration for Sentinel's commands. The enums contain the
 * bulk of the code that parses input and handles commands.
 * The file is structured this way to help modularize the code.
 */
public enum Commands {
    LIST_TASKS {
        /**
         * @inheritDoc
         *
         *     Command for listing all tasks.
         */
        @Override
        public void run(Sentinel sentinel, String args) {
            sentinel.outputTaskList();
        }
    },
    MARK_DONE {
        /**
         * @inheritDoc
         *
         *     Command for marking tasks as done.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a valid number!");
            }

            try {
                int taskNumber = Integer.parseInt(parsedArgs[1]);
                sentinel.markDone(taskNumber);
            } catch (NumberFormatException e) {
                throw new SentinelException("Invalid number!");
            }
        }
    },
    MARK_UNDONE {
        /**
         * @inheritDoc
         *
         *     Command for marking tasks as undone.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a valid number!");
            }

            try {
                int taskNumber = Integer.parseInt(parsedArgs[1]);
                sentinel.markUndone(taskNumber);
            } catch (NumberFormatException e) {
                throw new SentinelException("Invalid number!");
            }
        }
    },
    ADD_TODO {
        /**
         * @inheritDoc
         *
         *     Command for adding todos.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You did not add a todo name!");
            }

            sentinel.addTodo(parsedArgs[1]);
        }
    },
    ADD_EVENT {
        /**
         * @inheritDoc
         *
         *     Command for adding events.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You did not add an event name!");
            }

            String[] parts = parsedArgs[1].split("/from|/to");

            if (parts.length < 3) {
                throw new SentinelException("You did not put in a timeframe!");
            }

            String[] startDateAndTime = parts[1].trim().split("\\s+", 2);
            String[] endDateAndTime = parts[2].trim().split("\\s+", 2);

            sentinel.addEvent(parts[0].trim(), Parser.parseStringToDate(startDateAndTime[0]),
                    Parser.parseStringToDate(endDateAndTime[0]));
        }
    },
    ADD_DEADLINE {
        /**
         * @inheritDoc
         *
         *     Command for adding deadlines.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You did not add a deadline name!");
            }

            String[] parts = parsedArgs[1].split("/by");

            if (parts.length < 2) {
                throw new SentinelException("You did not put in a date!");
            }

            String[] dateAndTime = parts[1].trim().split("\\s+", 2);

            LocalDate date = Parser.parseStringToDate(dateAndTime[0]);
            sentinel.addDeadline(parts[0].trim(), date);
        }
    },
    DELETE_TASK {
        /**
         * @inheritDoc
         *
         *     Command for deleting tasks.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a valid number!");
            }

            try {
                int taskNumber = Integer.parseInt(parsedArgs[1]);
                sentinel.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                throw new SentinelException("Invalid number!");
            }
        }
    },
    FIND_TASK {
        /**
         * @inheritDoc
         *
         *     Command for finding tasks.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a search string!");
            }

            String searchTerm = parsedArgs[1].trim();
            sentinel.outputMatchingTaskList(searchTerm);
        }
    },
    ADD_CUSTOMER {
        /**
         * @inheritDoc
         *
         *     Command for adding customers.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You did not add a customer name!");
            }

            String[] parts = parsedArgs[1].split("/num");

            if (parts.length < 2) {
                throw new SentinelException("You did not put in a phone number!");
            }
            sentinel.addCustomer(parts[0].trim(), parts[1].trim());
        }
    },
    LIST_CUSTOMERS {
        /**
         * @inheritDoc
         *
         *     Command for listing all customers.
         */
        @Override
        public void run(Sentinel sentinel, String args) {
            sentinel.outputCustomerList();
        }
    },
    FIND_CUSTOMERS {
        /**
         * @inheritDoc
         *
         *     Command for finding tasks.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a search string!");
            }

            String searchTerm = parsedArgs[1].trim();
            sentinel.outputMatchingCustomerList(searchTerm);
        }
    },
    DELETE_CUSTOMER {
        /**
         * @inheritDoc
         *
         *     Command for deleting tasks.
         */
        @Override
        public void run(Sentinel sentinel, String args) throws SentinelException {
            String[] parsedArgs = args.split("\\s+", 2);

            if (parsedArgs.length < 2) {
                throw new SentinelException("You didn't put in a valid number!");
            }

            try {
                int customerNumber = Integer.parseInt(parsedArgs[1]);
                sentinel.deleteCustomer(customerNumber);
            } catch (NumberFormatException e) {
                throw new SentinelException("Invalid number!");
            }
        }
    };

    /**
     * Abstract run command method.
     *
     * @param sentinel Sentinel to act on.
     * @param args Arguments sent in.
     * @throws SentinelException if an error occurs.
     */
    public abstract void run(Sentinel sentinel, String args) throws SentinelException;

    /**
     * Method to map command strings to the command methods.
     *
     * @param commandString Command string received.
     */
    public static Commands getCommand(String commandString) {
        return switch (commandString) {
        case "list" -> LIST_TASKS;
        case "mark" -> MARK_DONE;
        case "unmark" -> MARK_UNDONE;
        case "todo" -> ADD_TODO;
        case "deadline" -> ADD_DEADLINE;
        case "event" -> ADD_EVENT;
        case "delete" -> DELETE_TASK;
        case "find" -> FIND_TASK;
        case "customer" -> ADD_CUSTOMER;
        case "listc" -> LIST_CUSTOMERS;
        case "findc" -> FIND_CUSTOMERS;
        case "deletec" ->DELETE_CUSTOMER;
        default -> null;
        };
    }
}


