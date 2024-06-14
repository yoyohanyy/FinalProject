import java.time.LocalDate;

public class Weekly extends Todo{
    private String title;

    public Weekly(String title) {
        super(title);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo(){
        return super.getInfo() + " (weekly)";
    }

    @Override
    public String toString() {
        return super.toString() + " (weekly)";
    }
}

