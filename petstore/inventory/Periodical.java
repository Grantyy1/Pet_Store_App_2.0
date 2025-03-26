package petstore.inventory;

public class Periodical extends Item {
    private String publisher;
    private HabitatType category;

    public Periodical(String title, String dateReceived, String publisher, HabitatType category) throws Exception {
        super(title, dateReceived);
        setPublisher(publisher);
        setCategory(category);
    }

    public Periodical(int id, String title, String dateReceived, String publisher, HabitatType category) throws Exception {
        super(id, title, dateReceived);
        setPublisher(publisher);
        setCategory(category);
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) throws Exception {
        publisher = publisher.trim();

        if (publisher.isBlank())
            throw new Exception("Invalid data! Author can not be empty.");

        this.publisher = publisher;
    }

    public HabitatType getCategory() { return category; }

    public void setCategory(HabitatType category) {
        this.category = category;
    }

    @Override
    public void displayItem(){
        super.displayItem();
        System.out.printf(" %-15s %-10s\n", publisher, category);
    }
}
