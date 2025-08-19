import java.time.LocalDateTime;
import java.util.ArrayList;

public class Shift_Report implements Reportable {
    private String employee;
    private ArrayList<Short> openingNum;
    private ArrayList<Short> closingNum;
    private LocalDateTime shiftClosedTime;

    public Shift_Report(String employee, ArrayList<Short> openingNum, ArrayList<Short> closingNum, LocalDateTime shiftClosedTime) {
        this.employee = employee;
        this.openingNum = openingNum;
        this.closingNum = closingNum;
        this.shiftClosedTime = shiftClosedTime;
    }

    public Shift_Report() {
        this.employee = "0";
        this.openingNum = new ArrayList<>();
        this.closingNum = new ArrayList<>();
        this.shiftClosedTime = LocalDateTime.now();
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public ArrayList<Short> getOpeningNumber() {
        return openingNum;
    }

    public void setOpeningNumber(ArrayList<Short> openingNum) {
        this.openingNum = openingNum;
    }

    public ArrayList<Short> getClosingNumber() {
        return closingNum;
    }

    public void setClosingNumber(ArrayList<Short> closingNum) {
        this.closingNum = closingNum;
    }

    public LocalDateTime getShiftClosedTime() {
        return shiftClosedTime;
    }

    public void setShiftClosedTime(LocalDateTime shiftClosedTime) {
        this.shiftClosedTime = shiftClosedTime;
    }

    @Override
    public short individualSold(int index) {
        return (short)(closingNum.get(index) - openingNum.get(index));
    }

    @Override
    public double individualProfit(Lottery ticket, int index) {
        return ticket.getPrice() * individualSold(index);
    }

    @Override
    public short ticketLeft(Lottery ticket, int index) {
        return closingNum.get(index);
    }

    @Override
    public double totalProfit(ArrayList<Lottery> tickets) {
        double total = 0.0;
        for (int i = 0; i < tickets.size(); i++) {
            total += tickets.get(i).getPrice() * individualSold(i);
        }
        return total;
    }

    @Override
    public short totalSold() {
        short totalSold = 0;
        for (int i = 0; i < openingNum.size(); i++) {
            totalSold += individualSold(i);
        }
        return totalSold;
    }
}