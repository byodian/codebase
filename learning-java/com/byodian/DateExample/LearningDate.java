import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LearningDate {

    public static void main(String[] args) {
        long timestamp = 1701841763705L;

        Instant instant = Instant.ofEpochMilli(timestamp);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(formatter);
        System.out.println(formattedDate);

    }
}
