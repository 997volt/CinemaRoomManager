package cinema;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Cinema:");
        for (int row = 0; row < 8; row++){
            for (int column = 0; column < 9; column++){
                if (row == 0 && column == 0){
                    System.out.print("  ");
                } else if (row == 0){
                    System.out.print(Integer.toString(column) + ' ');
                } else if (column == 0) {
                    System.out.print(Integer.toString(row) + ' ');
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }
}