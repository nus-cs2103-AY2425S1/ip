package sinatra;


/**
 * Represents the main Sinatra application.
 */
public class Sinatra {

    private Ui ui;
    private final Parser parser;

    /**
     * Constructs a new Sinatra object, initializes tasks, prints the introduction,
     * loads tasks from storage, and starts the scanner for user input.
     */
    public Sinatra() {

        ui = new Ui();
        ui.cacheIntro();
        parser = new Parser();
    }


    /**
     * handles querys from user to Sinatra
     *
     * @param input
     * @return response
     */
    public String handleQuery(String input) {
        String response = parser.getResponse(input);
        return response;
    }

    public boolean isStorageLoadOk() {
        return parser.isStorageLoadOk();
    }
}





