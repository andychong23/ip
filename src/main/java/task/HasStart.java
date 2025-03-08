package task;

import java.time.format.DateTimeFormatter;

/**
 * HasStart interface
 */
public interface HasStart {
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    String getStartDateTime();
}
