public class Inventory extends Item {
    private short quantity;

    public Inventory(String packName, short quantity) {
        super(packName);
        this.quantity = quantity;
    }

    public Inventory() {
        super("");
        this.quantity = 0;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    @Override
    public void display() {
        System.out.printf("%-25s %-10d\n", name, quantity);
    }
}