public class Lottery extends Item {
    private short lotteryBox;
    private short price;
    private int number;
    private String gameNumber;
    private short amount;

    public Lottery(short lotteryBox, String name, short price, int number, String gameNumber, short amount) {
        super(name);
        this.lotteryBox = lotteryBox;
        this.price = price;
        this.number = number;
        this.gameNumber = gameNumber;
        this.amount = amount;
    }

    public Lottery() {
        super("");
        this.lotteryBox = 0;
        this.price = 0;
        this.number = 0;
        this.gameNumber = "";
        this.amount = 0;
    }

    public short getLotteryBox() {
        return lotteryBox;
    }

    public void setLotteryBox(short lotteryBox) {
        this.lotteryBox = lotteryBox;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(String gameNumber) {
        this.gameNumber = gameNumber;
    }

    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    @Override
    public void display() {
        System.out.printf("%-12d %-20s $%-7d %-10d %-15s %-8d\n",
                lotteryBox, name, price, number, gameNumber, amount);
    }
}