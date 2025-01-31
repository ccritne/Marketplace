public class Tests {
    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";

    private static void testDates() {
        // Date tests
        System.out.println("Test Date");

        String[] wrongStringDates = {
            "2024-04-00", // Invalid day
            "2024-02-ab", // Invalid day
            "2024-02-30", // Invalid day for February
            "2024-04-31", // Invalid day for April
            "2024-06-31", // Invalid day for June
            "2024-02-31", // Invalid day for February
            "2024-12-32", // Invalid day for December
            "2024-11-31", // Invalid day for November
            "2024-13-01", // Invalid month
            "2024-13-32", // Invalid month and day
            "abcd-02-28", // Invalid year
            "2024-02-29-abc", // Invalid format
            "2024-02-31-", // Invalid format
            "2024-02-29 12:00" // Invalid format (extra time component)
        };

        System.out.println("\nWrong Dates");
        for (int i = 0; i < wrongStringDates.length; i += 1) {

            boolean result = Date.isISO8601(wrongStringDates[i]);

            String color = RED;
            String stringResult = "FAILED";

            if (result == false) {
                color = GREEN;
                stringResult = "PASSED.";
            }

            System.err.println(color+"Test "+(i+1)+" "+stringResult);

        }

        System.out.println(RESET);

        String[] validDates = {
            "2024-02-29", // Valid leap year date
            "2024-01-01", // Valid first day of the year
            "2024-12-31", // Valid last day of the year
            "2024-03-01", // Valid start of March
            "2024-04-30", // Valid end of April
            "2024-06-15", // Valid day in June
            "2024-09-30", // Valid day in September
            "2024-10-01", // Valid start of October
            "2024-11-30", // Valid end of November
            "2024-12-25", // Valid Christmas day
            "2024-05-20", // Valid day in May
            "2024-08-10", // Valid day in August
            "2024-02-28", // Valid day in February (non-leap year)
            "2024-03-15"  // Valid day in March
        };

        System.out.println("\nCorrect Dates");
        for (int i = 0; i < validDates.length; i += 1) {

            boolean result = Date.isISO8601(validDates[i]);

            String color = RED;
            String stringResult = "FAILED";

            if (result == true) {
                color = GREEN;
                stringResult = "PASSED.";
            }

            System.err.println(color+"Test "+(i+1)+" "+stringResult);

        }

        System.out.println(RESET);
    }

    public static void main(String[] args) {

        testDates();

    }
    
}
