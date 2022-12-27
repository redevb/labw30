import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private static final String LINE = ("*-------------*---------------------*---------------------*");
    private LocalDateTime beginOfTime;
    private LocalDateTime endOfTime;


    public Report(LocalDateTime beginOfTime) {
        this.beginOfTime = beginOfTime;
    }

    public LocalDateTime getBeginOfTime() {
        return beginOfTime;
    }

    public void setBeginOfTime(LocalDateTime beginOfTime) {
        this.beginOfTime = beginOfTime;
    }

    public LocalDateTime getEndOfTime() {
        return endOfTime;
    }

    public void setEndOfTime(LocalDateTime endOfTime) {
        this.endOfTime = endOfTime;
    }

    public void displayRecord() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
        String begin = String.format("%s", formatter.format(beginOfTime));
        String end;
        if (endOfTime != null) {
            end = String.format("%s", formatter.format(endOfTime));
        } else {
            end = "The car is still parked";
        }
        System.out.printf("%20s | %20s | %n", begin, end);
        StringBuilder l = new StringBuilder(LINE);
    }
}
