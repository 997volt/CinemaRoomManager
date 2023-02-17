package cinema;

import java.util.Scanner;

public class Cinema {
    private final int rowsCount;
    private final int seatsInRowCount;
    private final int totalSeatsNumber;
    private char[][] seatsArray;


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
    }

    public void printCinemaLayout() {
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


    private int calculateProfit(){
        int profit = 0;

        for (int row = 1; row <= rowsCount; row++){
            profit += getSeatPrice(row) * seatsInRowCount;
        }
        return profit;
    }

    public void printProfit(){
        System.out.println("Total income:");
        System.out.println("$" + Integer.toString(calculateProfit()));
    }

    public int sellTicket(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = in.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = in.nextInt();
        seatsArray[row-1][seat-1] = 'B';

        int price = getSeatPrice(row);
        System.out.println("Ticket price: $" + Integer.toString(price));
        return price;
    }
}