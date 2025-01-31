public class StringCheck {
    
    static boolean isAlphabetic(char character) {

        String validCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < validCharacters.length(); i++)
            if (character == validCharacters.charAt(i))
                return true;

        return false;
    }

    static boolean isDigit(char character) {
        String validDigits = "0123456789";

        for (int i = 0; i < validDigits.length(); i++)
            if (character == validDigits.charAt(i))
                return true;

        return false;
    }


    static boolean isValidString(String string){
        if (string.length() == 0 || string == null)
            return false;
        
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);

            boolean isDigit = isDigit(character);
            boolean isAlphabetic = isAlphabetic(character);

            if (!isDigit && !isAlphabetic)
                return false;
                
        }

        return true;
    }
}
