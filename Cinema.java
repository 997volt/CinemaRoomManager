package cinema;

import java.util.Scanner;

public class Cinema {
    private final int rowsCount;
    private final int seatsInRowCount;
    private final int totalSeatsNumber;
    private char[][] seatsArray;
    private int sold = 0;
    private int currentIncome = 0;


    public Cinema() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rowsCount = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInRowCount = in.nextInt();
        totalSeatsNumber = rowsCount * seatsInRowCount;
        seatsArray = new char[rowsCount][seatsInRowCount];
        for (int row = 0; row < rowsCount; row++){
            for (int seat = 0; seat < seatsInRowCount; seat++) {
                seatsArray[row][seat] = 'S';
            }
        }
        System.out.println();
    }

    public void userUI() {
        Scanner in = new Scanner(System.in);
        int userInput = -1;
        while(userInput != 0){
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            userInput = in.nextInt();
            System.out.println();

            switch (userInput){
                case 1:
                    printCinemaLayout();
                    break;
                case 2:
                    sellTicket();
                    break;
                case 3:
                    printStatistics();
                    break;
            }
        }
    }

    private void printCinemaLayout() {
        System.out.println("Cinema:");
        for (int row = 0; row < rowsCount + 1; row++){
            for (int seat = 0; seat < seatsInRowCount + 1; seat++){
                if (row == 0 && seat == 0){
                    System.out.print("  ");
                } else if (row == 0){
                    System.out.print(Integer.toString(seat) + ' ');
                } else if (seat == 0) {
                    System.out.print(Integer.toString(row) + ' ');
                } else {
                    char seatState = seatsArray[row-1][seat-1];
                    System.out.print(Character.toString(seatState) + ' ');
                }
            }
            System.out.print("\n");
        }
    }

    private int getSeatPrice(int row) {
        int price = 0;
        if (totalSeatsNumber > 60 && row > rowsCount / 2){
            price = 8;
        } else {
            price = 10;
        }
        return price;
    }


    private int getTotalProfit(){
        int profit = 0;

        for (int row = 1; row <= rowsCount; row++){
            profit += getSeatPrice(row) * seatsInRowCount;
        }
        return profit;
    }

    private void sellTicket() {
        Scanner in = new Scanner(System.in);
        boolean finished = false;

        while(!finished){
            System.out.println("Enter a row number:");
            int row = in.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = in.nextInt();
            System.out.println();

            if (row > rowsCount || seat > seatsInRowCount) {
                System.out.println("Wrong input!");
            } else if (seatsArray[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                sold++;
                seatsArray[row - 1][seat - 1] = 'B';
                int ticketPrice = getSeatPrice(row);
                currentIncome += ticketPrice;
                System.out.println("Ticket price: $" + Integer.toString(ticketPrice));
                finished = true;
            }
            System.out.println();
        }


    }


    private void printStatistics()
    {
        double percentageSold = 100.0 * sold / totalSeatsNumber;
        System.out.println("Number of purchased tickets: " + Integer.toString(sold));
        System.out.println("Percentage: " + String.format("%.2f", percentageSold) + "%");
        System.out.println("Current income: $" + Integer.toString(currentIncome));
        System.out.println("Total income: $" + Integer.toString(getTotalProfit()));
        System.out.println();
    }

}