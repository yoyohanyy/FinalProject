import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class TodoList  implements iCRUD{
    private String day;
    private ArrayList<Todo> items;

    public TodoList(String day){
        this.day = day;
        this.items = new ArrayList<Todo>();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Todo> getItems() {
        return items;
    }

    public void setItems(ArrayList<Todo> items) {
        this.items = items;
    }

    @Override
    public int addItem(String type) {
        String title;
        String deadline;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the title: ");
        title = sc.nextLine().trim();
        switch (type) {
            case "todo":
                Todo t = new Todo(title);
                this.items.add(t);
                break;
            case "deadline":
                System.out.print("Enter the deadline: ");
                deadline = sc.nextLine();
                Deadline d = new Deadline(title, deadline);
                this.items.add(d);
                break;
            case "weekly":
                Weekly w = new Weekly(title);
                this.items.add(w);
                break;
        }

        System.out.println("item added.");
        return 0;
    }

    @Override
    public int addTodo(String title) {
        Todo t = new Todo(title);
        this.items.add(t);
        return 0;
    }

    @Override
    public int addDeadline(String title, String deadline) {
        Deadline d = new Deadline(title, deadline);
        this.items.add(d);
        return 0;
    }

    @Override
    public int addWeekly(String title) {
        Weekly w = new Weekly(title);
        this.items.add(w);
        return 0;
    }
    @Override
    public int updateItem() {
        String title;
        int check = 0;
        int index = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the title of the item to edit: ");

        title = sc.nextLine().trim();
        for(int i = 0; i < items.size(); i++){
            if(title.equals(items.get(i).getTitle())){
                check = 1;
                index = i;
                break;
            }
        }

        Todo temp = items.get(index);

        if (check == 0) {
            System.out.println("Not Found.");
            return 1;
        }

        System.out.print("Enter the change: ");
        title = sc.nextLine().trim();
        temp.setTitle(title);

        items.remove(index);
        items.add(index, temp);
        System.out.println("item updated.");
        return 0;
    }
    @Override
    public int deleteItem() {
        String title;
        int check = 0;
        int index = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the title of the item to edit: ");

        title = sc.nextLine().trim();
        for(int i = 0; i < items.size(); i++){
            if(title.equals(items.get(i).getTitle())){
                check = 1;
                index = i;
                break;
            }
        }

        if (check == 0) {
            System.out.println("Not Found.");
            return 1;
        }

        items.remove(index);
        System.out.println("item deleted.");
        return 0;

    }

    @Override
    public int searchList(){
        String title;
        int check = 0;
        int index = 0;
        long dDay;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the title of the item to search: ");

        title = sc.nextLine();
        for(int i = 0; i < items.size(); i++){
            if(title.equals(items.get(i).getTitle())){
                check = 1;
                index = i;
                break;
            }
        }

        if (check == 0) {
            System.out.println("Not Found.");
            return 1;
        }

        dDay = items.get(index).getDDay();
        System.out.println("The number of days left until " + title + "'s deadline is " + dDay + " days");
        return 0;
    }
    @Override
    public void printList(){
        System.out.println(day);
        for(Todo item : items){
            System.out.println(item.toString());
        }
        System.out.println();
    }

    @Override
    public void saveToFile(PrintWriter writer) {
        for (Todo items : items) {
            writer.println(day + " " + items.getInfo());
        }
    }
}
