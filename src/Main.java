import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
    private ArrayList<TodoList> lists;
    Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "todoList.txt";
    public static void main(String[] args) {
        Main a = new Main();
        long currentWeek = a.initializeLists();
        if(currentWeek == 0) currentWeek = a.initialize();
        a.manager(currentWeek);
    }
    private long initializeLists() {
        lists = new ArrayList<>();
        String[] days = {"Deadline", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            lists.add(new TodoList(day));
        }

        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // Read the current week
                String line = reader.readLine();
                if (line != null) {
                    long currentWeek = Long.parseLong(line);

                    // Read the todo list items
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(" ");
                        String day = parts[0];
                        String item = "";
                        if(day.equals("Deadline")){
                            int i = 1;
                            while(true){
                                item += parts[i];
                                i++;
                                if(i == parts.length - 1) break;
                                item += " ";
                            }
                            TodoList a = lists.get(0);
                            a.addDeadline(item, parts[parts.length - 1]);
                        }
                        else if(day.equals("week")){
                            boolean exists = false;
                            for (TodoList b : lists) {
                                if (b.getDay().equals(day + " " + parts[1])) {
                                    exists = true;
                                    break;
                                }
                            }
                            if(!exists) lists.add(new TodoList(day + " " + parts[1]));
                            int i = 1;
                            while(true){
                                item += parts[i];
                                i++;
                                if(i == parts.length) break;
                                item += " ";
                            }
                            for (TodoList list : lists) {
                                if (list.getDay().equals(day + " " + parts[1])) {
                                    list.addTodo(item);
                                    break;
                                }
                            }
                        } else{
                            if(parts[parts.length - 1].equals("(weekly)")){
                                int i = 1;
                                while(true){
                                    item += parts[i];
                                    i++;
                                    if(i == parts.length - 1) break;
                                    item += " ";
                                }
                                for (TodoList list : lists) {
                                    if (list.getDay().equals(day)) {
                                        list.addWeekly(item);
                                        break;
                                    }
                                }
                            }
                            else{
                                int i = 1;
                                while(true){
                                    item += parts[i];
                                    i++;
                                    if(i == parts.length) break;
                                    item += " ";
                                }
                                for (TodoList list : lists) {
                                    if (list.getDay().equals(day)) {
                                        list.addTodo(item);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    System.out.println("Data loaded successfully.");
                    return currentWeek;
                }
            } catch (IOException e) {
                System.err.println("Error loading data: " + e.getMessage());
            }
        }

        return 0;
    }

    long initialize(){
        System.out.print("Enter the date of the first day of the semester in yyyymmdd format: ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate = LocalDate.parse(input, formatter);

        LocalDate today = LocalDate.now();

        long weeksBetween = ChronoUnit.WEEKS.between(startDate, today);

        long currentWeek = weeksBetween + 1;

        return currentWeek;
    }

    void manager(long currentWeek){
        boolean quit = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println("========================================");
            System.out.println("WEEK " + currentWeek);
            printLists();
            System.out.println("========================================");
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String choice = parts[0];
            switch (choice) {
                case "add":
                    addToList(parts);
                    break;
                case "delete":
                    deleteFromList(parts);
                    break;
                case "edit":
                    editList(parts);
                    break;
                case "search":
                    searchList(parts);
                    break;
                case "save":
                    saveList(currentWeek);
                    break;
                case "exit":
                    quit = true;
                    break;
            }
        } while (!quit);
    }

    public void printLists() {
        System.out.println();
        for(TodoList list : lists){
            list.printList();
        }
    }

    public void addToList(String[] parts){
        String type = parts[1];
        String day = "";
        String week = "";
        if (parts.length >= 3) {
            day = parts[2];
        }
        if (parts.length >= 4) {
            week = parts[3];
        }
        TodoList a = null;
        if(type.equals("deadline")){
            a = lists.get(0);
            a.addItem(type);
        }
        switch (day) {
            case "Monday":
                a = lists.get(1);
                a.addItem(type);
                break;
            case "Tuesday":
                a = lists.get(2);
                a.addItem(type);
                break;
            case "Wednesday":
                a = lists.get(3);
                a.addItem(type);
                break;
            case "Thursday":
                a = lists.get(4);
                a.addItem(type);
                break;
            case "Friday":
                a = lists.get(5);
                a.addItem(type);
                break;
            case "Saturday":
                a = lists.get(6);
                a.addItem(type);
                break;
            case "Sunday":
                a = lists.get(7);
                a.addItem(type);
                break;
            case "week":
                boolean exists = false;
                for (TodoList b : lists) {
                    if (b.getDay().equals("week " + week)) {
                        exists = true;
                        break;
                    }
                }
                if(!exists) lists.add(new TodoList("week " + week));
                for (TodoList b : lists) {
                    if (b.getDay().equals("week " + week)) {
                        a = b;
                        break;
                    }
                }
                a.addItem(type);
                System.out.println("done");
                break;
        }
    }

    public void deleteFromList(String[] parts){
        String day = parts[2];
        TodoList a = null;
        for (TodoList b : lists) {
            if (b.getDay().equals(day)) {
                a = b;
                break;
            }
        }
        a.deleteItem();
    }

    public void editList(String[] parts){
        String day = parts[1];
        TodoList a = null;
        for (TodoList b : lists) {
            if (b.getDay().equals(day)) {
                a = b;
                break;
            }
        }
        a.updateItem();
    }

    public void searchList(String[] parts){
        TodoList a = lists.get(0);
        a.searchList();
    }

    public void saveList(long currentWeek) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Save the current week
            writer.println(currentWeek);

            // Save the todo list items
            for (TodoList list : lists) {
                list.saveToFile(writer);
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}