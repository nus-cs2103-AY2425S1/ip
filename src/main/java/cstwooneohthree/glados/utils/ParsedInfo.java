package cstwooneohthree.glados.utils;

import java.time.LocalDate;

/**
 * ParsedInfo class to store parsed data
 *
 * @author jayjay19630
 */
public class ParsedInfo {

    /* Result of parsed data */
    private String description;
    private LocalDate[] dates;

    /**
     * Constructs new ParsedInfo object and sets parsed data.
     *
     * @param description Task description.
     * @param dates Dates of corresponding task.
     */
    public ParsedInfo(String description, LocalDate[] dates) {
        this.description = description;
        this.dates = dates;
    }

    /**
     * Gets description of task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets local data of corresponding task.
     *
     * @return Array of local dates.
     */
    public LocalDate[] getDates() {
        return this.dates;
    }
}
