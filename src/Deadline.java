import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.Year;

public class Deadline extends Todo{
    private String title;
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getInfo(){
        return super.getInfo() + " " + deadline;
    }

    public long getDDay(){
        int currentYear = Year.now().getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String deadlineWithYear = currentYear + deadline;
        LocalDate inputDate = LocalDate.parse(deadlineWithYear, formatter);
        LocalDate today = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(today, inputDate);
        return super.getDDay() + daysLeft;
    }

    @Override
    public String toString() {
        try {
            int currentYear = Year.now().getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String deadlineWithYear = currentYear + deadline;
            LocalDate inputDate = LocalDate.parse(deadlineWithYear, formatter);
            LocalDate today = LocalDate.now();
            long daysLeft = ChronoUnit.DAYS.between(today, inputDate);
            return super.toString() + "(" + deadline + ")" + " D-" + daysLeft;
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing deadline: " + e.getMessage());
            return ""; // Or handle the error in an appropriate way
        }
    }
}

