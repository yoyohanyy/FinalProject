
public class Todo{
    private String title;
    public Todo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDDay(){
        return 0;
    }

    public String getInfo(){return title;}

    @Override
    public String toString() {
        return "- " + title;
    }
}

