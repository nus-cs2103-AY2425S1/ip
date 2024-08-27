package utils;

import java.time.LocalDate;

public class ParsedInfo {
    private String description;
    private LocalDate[] dates;

    public ParsedInfo(String description, LocalDate[] dates) {
        this.description = description;
        this.dates = dates;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate[] getDates() {
        return this.dates;
    }
}