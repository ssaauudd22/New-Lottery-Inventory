public abstract class Item {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display(); // Abstract method for polymorphism
}