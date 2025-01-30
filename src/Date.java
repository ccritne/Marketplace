public class Date {

    // Constant for Standar ISO8601
    private static int standardLength = 10;
    private static int standardYearLength = 4;
    
    private int year;
    private int month;
    private int day;

    public static boolean checkValidityDate(int year, int month, int day) {
        
        // Year is valid with every value from 0 and 10^(standardYearLength) - 1
        // For year >= 10^(standardYearLength) change the standardLenght and standardYearLength as you desired. 

        if (year >= 0 && year < Math.pow(10, standardYearLength)) {
            // Year is a leap year if it is divisible by 400 (secular) or divisible by 4 but not by 100 (no secular)
            boolean leapYear = (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));

            if(month > 0 && month < 13){
                int[] lengthMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                if (leapYear) {
                    lengthMonth[1] = 29;
                }

                if(day > 0 && day <= lengthMonth[month - 1])
                    return true;
            }
        }

        return false;
    }

    public static boolean isISO8601(String date) {
        String[] subStrings = date.split("-");

        // Check date format
        if(subStrings.length == 3 && date.length() == standardLength){
            
            String digits = "0123456789";
            // Check if there are alphabetic character in subStrings
            int countDigits = 0;

            // Map of lengths
            int[] lengths = {standardYearLength, 2, 2};

            // This is a O(3*3*10) ~ O(1)
            for(int i = 0; i < 3; i++){

                if(subStrings[i].length() == lengths[i]){

                    for (int j = 0; j < lengths[i]; j++){
                    
                        for (int k = 0; k < 10; k++) {
                            if(subStrings[i].charAt(j) == digits.charAt(k)){
                                countDigits += 1;
                            }
                        }
                    
                    }

                }
            }

            if(countDigits == 8){
                // expirationDate contains only digits

                int year = Integer.parseInt(subStrings[0]);
                int month = Integer.parseInt(subStrings[1]);
                int day = Integer.parseInt(subStrings[2]);

                return checkValidityDate(year, month, day);
            } 
        }


        return false;
    }

    // Constructors
    Date(String date){
        if (isISO8601(date)) {
            
            String[] subStrings = date.split("-");

            year = Integer.parseInt(subStrings[0]);
            month = Integer.parseInt(subStrings[1]);
            day = Integer.parseInt(subStrings[2]);
        }else {
            throw new IllegalArgumentException(date+" is not a valid date.");
        }
        
    }

    Date(int year, int month, int day){
        if (checkValidityDate(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
        }else {
            throw new IllegalArgumentException(year+"-"+month+"-"+day+" is not a valid date.");
        }
    }

    // Getters

    public int getYear() {
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }


}
