import java.io.PrintWriter;
public interface iCRUD {
    public int addItem(String type);
    public int addTodo(String title);
    public int addDeadline(String title, String deadline);
    public int addWeekly(String title);
    public int updateItem();
    public int deleteItem();
    public int searchList();
    public void printList();
    public void saveToFile(PrintWriter writer);
}
