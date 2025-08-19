import java.util.ArrayList;

public interface Reportable {
    short individualSold(int index);
    double individualProfit(Lottery ticket, int index);
    short ticketLeft(Lottery ticket, int index);
    double totalProfit(ArrayList<Lottery> tickets);
    short totalSold();
}