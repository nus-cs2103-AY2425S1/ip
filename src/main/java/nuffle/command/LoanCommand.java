package nuffle.command;

import nuffle.exception.NuffleException;
import nuffle.storage.Storage;
import nuffle.task.*;
import nuffle.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nuffle.parser.Parser.isValidateDateTimeFormat;

public class LoanCommand extends Command {
    private final String userInput;

    //command example: loan /b name /l name /amt 100 /due date

    public LoanCommand(String userInput) {
        this.userInput = userInput;
    }
    
    private ArrayList<String> getLoanCommandDesc(String userInput) {
        ArrayList<String> loanDesc = new ArrayList<>();
        // Get the description of the loan task
        String commandPattern = "loan /B (\\w+) /L (\\w+) /amt (\\d+(?:\\.\\d{1,2})?) /due (\\d{4}-\\d{2}-\\d{2} \\d{4})";
        Pattern pattern = Pattern.compile(commandPattern);
        Matcher matcher = pattern.matcher(userInput);
        System.out.println(matcher);
        if (matcher.matches()) {
            String borrower = matcher.group(1);  // Group 1: Borrower's name
            loanDesc.add(borrower);
            String lender = matcher.group(2);    // Group 2: Lender's name
            loanDesc.add(lender);
            String amount = matcher.group(3);    // Group 3: Amount
            loanDesc.add(amount);
            String dueDate = matcher.group(4);   // Group 4: Due date
            loanDesc.add(dueDate);
        }
        return loanDesc;

    }

    @Override
    public String execute(TaskList loans, Storage storage, Ui ui) throws NuffleException {
        assert userInput != null && !userInput.trim().isEmpty() : "Command should not be null or empty";
        System.out.println(userInput);
        // Program will add an event task to the list

        if (!(userInput.contains("/B") && userInput.contains("/L") && userInput.contains("/amt") && userInput.contains("/due"))) {
            throw NuffleException.checkLoanFormat();
        }

        ArrayList<String> desc = getLoanCommandDesc(userInput);
        System.out.println(desc.get(0));
        System.out.println(desc.get(1));
        System.out.println(desc.get(2));
        System.out.println(desc.get(3));
        // ensure that to /B /L /due and /amt has an input after it
        if (desc.get(0).trim().isEmpty() || desc.get(1).trim().isEmpty() ||
                desc.get(2).trim().isEmpty() || desc.get(3).trim().isEmpty()) {
            throw NuffleException.checkLoanParams();
        }

        // check that the date input is of the correct format (yyyy-mm-dd hhmm)
        if (!isValidateDateTimeFormat(desc.get(3))) {
            throw NuffleException.checkDateTimeFormat();
        }
        // Parse the amount
        double amount = Double.parseDouble(desc.get(2));
        // Parse the date and time

        String dueDateTime = desc.get(3).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(dueDateTime, formatter);
        Task loanTask = new Loan(desc.get(0).trim(), desc.get(1).trim(), amount, from);
        System.out.println(loanTask.getDescription());

        loans.addTask(loanTask);
        return ui.addTaskMessage(loanTask, loans.getSize());
    }

    public boolean isTerminateProgram() {
        return false;
    }
}
