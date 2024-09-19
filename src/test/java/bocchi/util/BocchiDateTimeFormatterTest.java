package bocchi.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A comprehensive test for BocchiDateTimeFormatter.
 */
public class BocchiDateTimeFormatterTest {

    private static final List<Map<String, LocalDateTime>> DATE_FORMATS = List.of(
            Map.ofEntries(
                    Map.entry(
                            "2020-1-1",
                            LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    ),
                    Map.entry(
                            "2020/1/1",
                            LocalDateTime.of(2020, 1, 1, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "2028-11-10",
                            LocalDateTime.of(2028, 11, 10, 0, 0, 0)
                    ),
                    Map.entry(
                            "2028/11/10",
                            LocalDateTime.of(2028, 11, 10, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "1969-Sep-10",
                            LocalDateTime.of(1969, 9, 10, 0, 0, 0)
                    ),
                    Map.entry(
                            "1969/Sep/10",
                            LocalDateTime.of(1969, 9, 10, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "2019-20-Mar",
                            LocalDateTime.of(2019, 3, 20, 0, 0, 0)
                    ),
                    Map.entry(
                            "2019/20/Mar",
                            LocalDateTime.of(2019, 3, 20, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "1-1",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0, 0)
                    ),
                    Map.entry(
                            "1/1",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "11-10",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 11, 10, 0, 0, 0)
                    ),
                    Map.entry(
                            "11/10",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 11, 10, 0, 0, 0)
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "Sep 10",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 9, 10, 0, 0, 0)
                    ),
                    Map.entry(
                            "10 Sep",
                            LocalDateTime.of(LocalDateTime.now().getYear(), 9, 10, 0, 0, 0)
                    )
            )
    );
    private static final List<Map<String, LocalDateTime>> TIME_FORMATS = List.of(
            Map.ofEntries(
                    Map.entry(
                            "00:00:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0))
                    ),
                    Map.entry(
                            "12:00:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0, 0))
                    ),
                    Map.entry(
                            "23:59:59",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59))
                    ),
                    Map.entry(
                            "11:12:49",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 12, 49))
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "00:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0))
                    ),
                    Map.entry(
                            "12:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0, 0))
                    ),
                    Map.entry(
                            "23:59",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 0))
                    ),
                    Map.entry(
                            "11:12",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 12, 0))
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "9:20:10",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 20, 10))
                    ),
                    Map.entry(
                            "0:10:59",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 10, 59))
                    ),
                    Map.entry(
                            "8:00:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0, 0))
                    ),
                    Map.entry(
                            "1:01:01",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(1, 1, 1))
                    )
            ),
            Map.ofEntries(
                    Map.entry(
                            "9:20",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 20, 0))
                    ),
                    Map.entry(
                            "0:10",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 10, 0))
                    ),
                    Map.entry(
                            "8:00",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0, 0))
                    ),
                    Map.entry(
                            "1:01",
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(1, 1, 0))
                    )
            )
    );

    @Test
    void parse_dateFormats_success() {
        DATE_FORMATS.forEach((maps) -> {
            // for each format
            maps.forEach((input, expected) -> {
                // for each input
                LocalDateTime actual = BocchiDateTimeFormatter.parse(input);
                assertEquals(expected, actual);
            });
        });
    }

    @Test
    void parse_timeFormats_success() {
        TIME_FORMATS.forEach((maps) -> {
            // for each format
            maps.forEach((input, expected) -> {
                // for each input
                LocalDateTime actual = BocchiDateTimeFormatter.parse(input);
                assertEquals(expected, actual);
            });
        });
    }

    @Test
    void parse_dateTimeFormats_success() {
        DATE_FORMATS.forEach((dateMaps) -> {
            TIME_FORMATS.forEach((timeMaps) -> {
                // for each date format
                dateMaps.forEach((dateInput, dateExpected) -> {
                    // for each time format
                    timeMaps.forEach((timeInput, timeExpected) -> {
                        // for each input
                        LocalDateTime expected = LocalDateTime.of(
                                dateExpected.toLocalDate(),
                                timeExpected.toLocalTime()
                        );
                        LocalDateTime actual = BocchiDateTimeFormatter.parse(dateInput + " " + timeInput);
                        assertEquals(expected, actual);
                    });
                });
            });
        });
    }
}