package library.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {

    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    protected final int id;
    protected String title;
    protected LocalDate dateReceived;
    protected String description;

    public Item(String title, String dateReceived) throws Exception {
        this.id = ++Item.lastId;
        setTitle(title);
        setDateReceived(dateReceived);
    }

    public Item(int id, String title, String dateReceived) throws Exception {
        this.id = id;
        setTitle(title);
        setDateReceived(dateReceived);
    }

    public static void setLastId(int lastId){ Item.lastId = lastId;  }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        title = title.trim();

        if (title.isBlank()){
            throw new Exception("Invalid! Title can not be empty.");
        }

        this.title = title;
    }

    public String getDateReceived() {
        return dateReceived.format(Item.formatter);
    }

    public void setDateReceived(String dateReceived) throws Exception {
        try {
            this.dateReceived = LocalDate.parse(dateReceived, Item.formatter);
        } catch (Exception e){
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void displayItem(){
        System.out.printf("%3d %-15s %10s", id, title, getDateReceived());
    }

    @Override
    public String toString(){
        return id + " " + title + " " + getDateReceived();
    }

}
