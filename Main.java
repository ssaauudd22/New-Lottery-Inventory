import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDateTime;

public class Main {

    static void displayMenu() {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("1) Create Lottery");
        System.out.println("2) View Lottery");
        System.out.println("3) Print Shift Report");
        System.out.println("4) Update Inventory");
        System.out.println("5) View All Inventory");
        System.out.println("6) Exit");
        System.out.print("What would you like to do: ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean repeatMenu = true;

        ArrayList<Lottery> lotteries = new ArrayList<>();
        ArrayList<Inventory> inventories = new ArrayList<>();

        do {
            displayMenu();
            byte menuChoice;

            try {
                menuChoice = input.nextByte();
                input.nextLine(); // clear buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                input.nextLine(); // clear invalid input
                continue;
            }

            switch (menuChoice) {
                case 1:
                    System.out.print("\nWhat box number is the lottery in: ");
                    short lotteryBox = input.nextShort();
                    input.nextLine();

                    System.out.print("Ticket name: ");
                    String name = input.nextLine();

                    System.out.print("Price: $");
                    short price = input.nextShort();
                    input.nextLine();

                    System.out.print("The number it's on: ");
                    int number = input.nextInt();
                    input.nextLine();

                    System.out.print("What's the game pack number: ");
                    String gameNumber = input.nextLine();

                    System.out.print("What's the amount of tickets: ");
                    short amount = input.nextShort();
                    input.nextLine();

                    Lottery newTicket = new Lottery(lotteryBox, name, price, number, gameNumber, amount);
                    lotteries.add(newTicket);
                    System.out.println("Lottery added successfully!");
                    break;

                case 2:
                    if (lotteries.isEmpty()) {
                        System.out.println("\nNo tickets found.");
                    } else {
                        System.out.printf("%-12s %-20s %-8s %-10s %-15s %-8s\n", 
                            "Box #", "Name", "Price", "Number", "Game #", "Amount");
                        System.out.println("-----------------------------------------------------------------------");

                        for (Lottery t : lotteries) {
                            t.display();  // Polymorphic call
                        }
                    }
                    break;

                case 3:
                    if (lotteries.isEmpty()) {
                        System.out.println("No Lottery to generate shift report. Please add tickets first.");
                        break;
                    }

                    System.out.print("Employee Name: ");
                    String employee = input.nextLine();

                    ArrayList<Short> openingNum = new ArrayList<>();
                    ArrayList<Short> closingNum = new ArrayList<>();

                    for (Lottery ticket : lotteries) {
                        System.out.print("\nOpening Number for " + ticket.getName() + ": ");
                        openingNum.add(input.nextShort());
                        input.nextLine();

                        System.out.print("Closing Number for " + ticket.getName() + ": ");
                        closingNum.add(input.nextShort());
                        input.nextLine();
                    }

                    // Use interface reference
                    Reportable report = new Shift_Report(employee, openingNum, closingNum, LocalDateTime.now());

                    System.out.println("\n---------- Shift Report ----------");
                    for (int i = 0; i < lotteries.size(); i++) {
                        Lottery ticket = lotteries.get(i);
                        short sold = report.individualSold(i);
                        double profit = report.individualProfit(ticket, i);
                        short left = report.ticketLeft(ticket, i);

                        System.out.printf("Ticket: %-15s | Sold: %-4d | Profit: $%.2f | Remaining: %d\n",
                                ticket.getName(), sold, profit, left);
                    }

                    System.out.println("----------------------------------");
                    System.out.println("Total Tickets Sold: " + report.totalSold());
                    System.out.printf("Total Profit: $%.2f\n", report.totalProfit(lotteries));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm a");
                    System.out.println("Shift Closed At: " + ((Shift_Report) report).getShiftClosedTime().format(formatter));
                    System.out.println("By: " + ((Shift_Report) report).getEmployee());
                    break;

                case 4:
                    System.out.print("How many inventory items would you like to add? ");
                    int itemCount;

                    try {
                        itemCount = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Returning to menu.");
                        input.nextLine();
                        break;
                    }

                    for (int i = 0; i < itemCount; i++) {
                        System.out.println("\nItem #" + (i + 1));
                        System.out.print("Pack Name: ");
                        String packName = input.nextLine();

                        System.out.print("Quantity: ");
                        short quantity;

                        try {
                            quantity = input.nextShort();
                            input.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid quantity. Skipping this item.");
                            input.nextLine();
                            continue;
                        }

                        Inventory inventory = new Inventory(packName, quantity);
                        inventories.add(inventory);
                        System.out.println("Inventory item added successfully!");
                    }
                    break;

                case 5:
                    if (inventories.isEmpty()) {
                        System.out.println("No inventory items found.");
                    } else {
                        System.out.println("\n------ Inventory List ------");
                        System.out.printf("%-25s %-10s\n", "Pack Name", "Quantity");
                        System.out.println("----------------------------------------");

                        for (Inventory inv : inventories) {
                            inv.display();  // Polymorphic call
                        }
                    }
                    break;

                case 6:
                    input.close();
                    System.out.println("\nExiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please enter a value between 1-6.");
                    break;
            }

        } while (repeatMenu);
    }
}