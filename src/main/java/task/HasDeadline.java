package task;

import java.time.format.DateTimeFormatter;

/**
 * HasDeadline Interface
 */
public interface HasDeadline {
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    String getDeadLine();
}
