package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;
     Seat() {

    }
     Seat(int row, int column) {
        this.row = row;
        this.column = column;
        if(row<=4){
            this.price = 10;
        }else{
            this.price = 8;
        }
    }
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        if(row<=4){
            this.price = 10;
        }else{
            this.price = 8;
        }
         this.row = row;
    }



    public int compareTo(Seat seat){
         if(this.row== seat.row&&this.column== seat.column){
             return 0;
         }else{
             if(this.row> seat.row){
                 return 1;
             }else{
                 if(this.row==seat.row){
                     if(this.column>seat.column){
                         return 1;
                     }else{
                         return -1;
                     }
                 }else{
                     return -1;
                 }
             }
         }
    }

    public boolean equals(Seat seat) {
        if(this.row==seat.row&&this.column==seat.column){
            return true;
        }else{
            return false;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
