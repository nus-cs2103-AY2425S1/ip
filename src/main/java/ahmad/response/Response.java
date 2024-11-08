package ahmad.response;

import java.util.List;

/**
 * Response encapsulation class.
 */
public class Response {
    private final List<String> response;
    private final boolean shouldExit;
    private final boolean shouldRecord;

    /**
     * Constructs a response instance based on given arguments.
     *
     * @param response     A list of string to be printed to output.
     * @param shouldExit         Whether program should exit.
     * @param shouldRecord Whether command is to be saved.
     */
    public Response(List<String> response, boolean shouldExit, boolean shouldRecord) {
        this.response = response;
        this.shouldExit = shouldExit;
        this.shouldRecord = shouldRecord;
    }

    /**
     * Constructs a response instance based on given arguments.
     * ShouldRecord defaults to false.
     *
     * @param response A list of string to be printed to output.
     * @param shouldExit     Whether program should exit.
     */
    public Response(List<String> response, boolean shouldExit) {
        this.response = response;
        this.shouldExit = shouldExit;
        this.shouldRecord = false;
    }

    /**
     * Constructs a response instance based on given arguments.
     * ShouldRecord defaults to false.
     * Exit defaults to false.
     *
     * @param response A list of string to be printed to output.
     */
    public Response(List<String> response) {
        this.response = response;
        this.shouldExit = false;
        this.shouldRecord = false;
    }

    /**
     * Returns responses.
     *
     * @return List of responses to be printed.
     */
    public List<String> getResponse() {
        return this.response;
    }

    /**
     * Returns whether program should exit.
     *
     * @return ShouldExit boolean.
     */
    public boolean checkShouldExit() {
        return this.shouldExit;
    }

    /**
     * Returns whether command should be recorded.
     *
     * @return ShouldRecord boolean.
     */
    public boolean checkShouldRecord() {
        return this.shouldRecord;
    }
}

