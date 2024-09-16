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

    private ArrayList<String> getLoanCommandDesc(String userInput) throws NuffleException {
        ArrayList<String> loanDesc = new ArrayList<>();
        String dueDateFormat = getDueDate();
        if (!isValidateDateTimeFormat(dueDateFormat)) {
            throw NuffleException.checkDateTimeFormat();
        }

        // Get the description of the loan task
        String commandPattern = "loan /B (\\w+) /L (\\w+) /amt (\\d+(?:\\.\\d{1,2})?) /due (\\d{4}-\\d{2}-\\d{2} \\d{4})";
        Pattern pattern = Pattern.compile(commandPattern);

        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            String borrower = matcher.group(1);
            loanDesc.add(borrower);
            String lender = matcher.group(2);
            loanDesc.add(lender);
            String amount = matcher.group(3);
            loanDesc.add(amount);
            String dueDate = matcher.group(4);

            loanDesc.add(dueDate);
        }

        return loanDesc;

    }

    public String getDueDate() {
        // Get the date and check if the date format is correct
        int dueIndex = userInput.indexOf("/due");

        if (dueIndex != -1) {
            // Extract the part after "/due"
            return userInput.substring(dueIndex + "/due".length()).trim();
        } else {
            return "-1";
        }
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
        // ensure that to /B /L /due and /amt has an input after it
        if (desc.get(0).trim().isEmpty() || desc.get(1).trim().isEmpty() ||
                desc.get(2).trim().isEmpty() || desc.get(3).trim().isEmpty()) {
            throw NuffleException.checkLoanParams();
        }

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
