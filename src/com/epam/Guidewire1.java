package com.epam;

public class Guidewire1 {
    private static final String SEAT_SEPARATOR = "\\s";

    public static void main(String[] args){
        int A = 123456;

        int rowNumber = getSeatNumber('A');
         rowNumber = getSeatNumber('H');
         rowNumber = getSeatNumber('K');
         rowNumber = getSeatNumber('J');
         rowNumber = getSeatNumber('J');

        int[][] allocatedSeats = getAllocatedSeats(2, "1A 2F 1C");

        int solution = solution(2, "1A 2F 1C");

        System.out.println("Solution is " + solution);

        solution = solution(1, "");

        System.out.println("Solution 1 row and empty string is " + solution);

    }

    public static int solution(int N, String S) {
        int output = 0;
        if (N <= 0) {
            return 0;
        }

        int[][] allocatedSeats = getAllocatedSeats(N, S);

        int numberOfSeatsAllocated = 0;

            for (int rowIndex = 1; rowIndex <= N; rowIndex++) {
                for (int colIndex = 1; colIndex < 11; colIndex++) {

                    numberOfSeatsAllocated = getNumberOfSeatsAllocated(numberOfSeatsAllocated, colIndex);

                    if (allocatedSeats[rowIndex][colIndex] != 1) {
                        numberOfSeatsAllocated = numberOfSeatsAllocated + 1;
                        if (numberOfSeatsAllocated == 4) {
                            output++;
                            numberOfSeatsAllocated = 0;
                        }
                    } else {
                        numberOfSeatsAllocated = 0;
                    }
                }
            }

        return output;
    }

    private static int getNumberOfSeatsAllocated(int numberOfSeatsAllocated, int colIndex) {
        if (colIndex == 4 || colIndex == 8 || colIndex == 1) {
            if(colIndex == 1){
                numberOfSeatsAllocated = 0;
            } else {
                numberOfSeatsAllocated = numberOfSeatsAllocated >= 2 ? 2: 0;
            }
        }
        return numberOfSeatsAllocated;
    }

    private static int[][] getAllocatedSeats(int N, String S) {
        int[][] allocatedSeats = new int[N + 1][11];

        if (S == null || S.isEmpty()) {
            return allocatedSeats;
        }

        String[] seats = S.split(SEAT_SEPARATOR);

        for (String seat : seats) {
            int rowNumber = Integer.parseInt(seat.substring(0, seat.length() - 1));
            int seatNumber = getSeatNumber(seat.charAt(seat.length() - 1));

            if (rowNumber <= N && seatNumber < 11) {
                allocatedSeats[rowNumber][seatNumber] = 1;
            }
        }

        return allocatedSeats;
    }

    private static int getSeatNumber(char seatRow) {
        if(seatRow >= 'A' && seatRow <= 'H'){
            return seatRow - 'A' + 1;
        }

        if(seatRow == 'J'){
            return 9;
        }

        if(seatRow == 'K'){
            return 10;
        }
        return -1;
    }

}
