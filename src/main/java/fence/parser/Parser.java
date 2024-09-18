package fence.parser;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import fence.task.Deadline;
import fence.task.Event;
import fence.task.Task;
import fence.task.Todo;

public class Parser {

    private Task taskResult;
    private int intResult;
    private String strResult;
    private StringTokenizer st;

    /**
     * Parses the fields for the todo task and initialises it in taskResult.
     */
    public void initialiseTodo() {
        String description = st.nextToken();
        while (st.hasMoreTokens()) {
            description = description + " " + st.nextToken();
        }

        taskResult = new Todo(description);
    }

    /**
     * Sets the status of the current task being initialised.
     * @param status Status of the current task.
     */
    public void initialiseStatus(String status) {
        if (status.equals("(DONE)")) {
            taskResult.complete();
        } else if (status.equals("(UNDONE)")) {
            assert !taskResult.isComplete() : "taskResult should be undone by default";
        } else {
            assert false : "status should have been saved as either default statuses";
        }
    }

    /**
     * Parses the fields for the deadline task and initialises it in taskResult.
     * @throws NoSuchElementException If any fields are missing.
     */
    public void initialiseDeadlineWithExceptions() {
        String description = st.nextToken();
        String by = "";
        boolean hasCompleteDescription = false;

        if (description.equals("/by")) {
            throw new NoSuchElementException();
        }

        while (st.hasMoreTokens()) {
            String nextWord = st.nextToken();
            if (nextWord.equals("/by")) {
                hasCompleteDescription = true;
                by = st.nextToken();
                continue;
            }

            if (hasCompleteDescription) {
                by = by + " " + nextWord;
            } else {
                description = description + " " + nextWord;
            }
        }

        if (!hasCompleteDescription) {
            throw new NoSuchElementException();
        }

        taskResult = new Deadline(description, LocalDate.parse(by));
    }

    /**
     * Parses the fields for the event task and initialises it in taskResult.
     * @throws NoSuchElementException If any fields are missing.
     */
    public void initialiseEventWithExceptions() {
        String description = st.nextToken();
        String from = "";
        String to = "";
        boolean hasCompleteDescription = false;
        boolean hasCompleteFrom = false;

        if (description.equals("/to") || description.equals("/from")) {
            throw new NoSuchElementException();
        }

        while (st.hasMoreTokens()) {
            String nextWord = st.nextToken();
            if (nextWord.equals("/from")) {
                hasCompleteDescription = true;
                from = st.nextToken();
                continue;
            }
            if (nextWord.equals("/to")) {
                if (!hasCompleteDescription) {
                    throw new NoSuchElementException();
                }
                hasCompleteFrom = true;
                to = st.nextToken();
                continue;
            }

            if (hasCompleteFrom) {
                to = to + " " + nextWord;
            } else if (hasCompleteDescription) {
                from = from + " " + nextWord;
            } else {
                description = description + " " + nextWord;
            }
        }

        if (!(hasCompleteDescription && hasCompleteFrom)) {
            throw new NoSuchElementException();
        }

        taskResult = new Event(description, from, to);
    }

    /**
     * Parses the index of the relevant task and initialises it in intResult.
     */
    public void readIndex() {
        intResult = Integer.parseInt(st.nextToken());
    }

    /**
     * Parses the relevant string to be found and initialises it in strResult.
     */
    public void readString() {
        strResult = st.nextToken();
        while (st.hasMoreTokens()) {
            strResult = strResult + " " + st.nextToken();
        }
    }

    public Task parseTxt(String command) {
        st = new StringTokenizer(command);
        String firstWord = st.nextToken();
        String status = st.nextToken();

        if (firstWord.equals("TODO")) {
            initialiseTodo();
        } else if (firstWord.equals("DEADLINE")) {
            initialiseDeadlineWithExceptions();
        } else if (firstWord.equals("EVENT")) {
            initialiseEventWithExceptions();
        } else {
            assert false : "firstWord should have been saved as one of the default task types";
        }
        initialiseStatus(status);
        return taskResult;
    }

    public String parseInput(String command) {
        st = new StringTokenizer(command);
        String firstWord = st.nextToken();

        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return "list";
        } else if (firstWord.equals("todo")) {
            initialiseTodo();
            return "task";
        } else if (firstWord.equals("deadline")) {
            initialiseDeadlineWithExceptions();
            return "task";
        } else if (firstWord.equals("event")) {
            initialiseEventWithExceptions();
            return "task";
        } else if (firstWord.equals("mark")) {
            readIndex();
            return "mark";
        } else if (firstWord.equals("unmark")) {
            readIndex();
            return "unmark";
        } else if (firstWord.equals("delete")) {
            readIndex();
            return "delete";
        } else if (firstWord.equals("find")) {
            readString();
            return "find";
        } else {
            return "unknown command";
        }
    }

    public Task getTask() {
        return this.taskResult;
    }

    public int getIndex() {
        return this.intResult;
    }

    public String getString() {
        return this.strResult;
    }
}
