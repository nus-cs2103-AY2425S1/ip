package ui;

import task.KorolevList;

/**
 * Represents the mechanism that interacts with users.
 */
public class KorolevUI {
    private KorolevList repo = new KorolevList();

    /**
     * Reads user input.
     *
     * @param input user input.
     * @return command execution results.
     */
    public String getResponse(String input) {
        KorolevCommand c = new KorolevCommand(input, repo);
        return c.executeCommand();
    }

    public void loadEvent() {
        this.repo.loadEvent();
    }
}
