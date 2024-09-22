package cloud.util;

/**
 * Represents the status of a Cloud response.
 * Includes a corresponding colour hex code to be displayed.
 */
public enum ResponseStatus {
    OK("#61D183"),
    INPUT_ERROR("#ea7f7f"),
    DATE_ERROR("#eeb454"),
    STORAGE_ERROR("#EDC25E");

    private final String displayColourHex;

    ResponseStatus(String colourHex) {
        this.displayColourHex = colourHex;
    }

    public String getColourHex() {
        return this.displayColourHex;
    }
}
