import java.util.Scanner;

public class CustomScanner {
    
    private static Scanner scanner;

    CustomScanner(Scanner systemScanner) {
        scanner = systemScanner;
    }

    public void close() {
        scanner.close();
    }

    public int scanInt(String message, String messageError){
        int value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println(messageError);
            }

        } while(true);

        return value;
    }

    public int scanInt(String message, String messageError, int lowerBoundInclusive){
        int value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public int scanInt(String message, String messageError, int lowerBoundInclusive, int upperBoundInclusive){
        int value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive && value <= upperBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public long scanLong(String message, String messageError){
        long value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextLong();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println(messageError);
            }

        } while(true);

        return value;
    }

    public long scanLong(String message, String messageError, long lowerBoundInclusive){
        long value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextLong();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public long scanLong(String message, String messageError, long lowerBoundInclusive, long upperBoundInclusive){
        long value = 0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextLong();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive && value <= upperBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public double scanDouble(String message, String messageError){
        double value;

        do{
            System.out.print(message);
            try {
                value = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public double scanDouble(String message, String messageError, double lowerBoundInclusive){
        double value = 0.0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;

    }

    public double scanDouble(String message, String messageError, double lowerBoundInclusive, double upperBoundInclusive){
        double value = 0.0;

        do{
            System.out.print(message);
            try {
                value = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(messageError);
            }

            if (value >= lowerBoundInclusive && value <= upperBoundInclusive) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public String scanAlphanumericString(String message, String messageError) {
        String value = "";

        do{
            System.out.print(message);
            value = scanner.nextLine();

            if (StringCheck.isValidString(value)) {
                break;
            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
        

    }

    public String scanAlphanumericString(String message, String messageError, String[] acceptedValues) {
        String value = "";

        do{
            System.out.print(message);
            value = scanner.nextLine();

            if (StringCheck.isValidString(value)) {
                boolean isAccepted = false;
                for (int i = 0; i < acceptedValues.length; i++){
                    if(value.equals(acceptedValues[i]))
                        isAccepted = true;
                }

                if (isAccepted) {
                    break;
                }

            }else {
                System.out.println(messageError);
            }

        }while(true);

        return value;
    }

    public boolean scanYesNo(String message, String messageError) {
        
        String value = scanAlphanumericString(message, messageError, new String[]{"y", "yes", "n", "no"});

        switch (value) {
            case "y":
            case "yes":
                return true;
            case "n":
            case "no":
                return false;
        }
        
        return false;
    }

    public Date scanDate(String message, String messageError) {
        Date expirationDate;
        do {
            
            System.out.println(message);
            
            try {
                expirationDate = new Date(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(messageError);
            }
            
        } while(true);

        return expirationDate;
    }

    public void pressEnter() {
        System.out.print("Press enter...");
        scanner.nextLine();
    }
}
