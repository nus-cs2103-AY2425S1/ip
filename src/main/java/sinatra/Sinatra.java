package sinatra;


/**
 * Represents the main Sinatra application.
 */
public class Sinatra {

    private static final String FILE_PATH = "tasks.txt";


    private Ui ui;
    private Parser parser;

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
     * @return
     */
    public String handleQuery(String input) {
        String response = parser.getResponse(input);
        return response;
    }
}





