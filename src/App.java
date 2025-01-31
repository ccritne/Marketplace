import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    private static CustomScanner scanner = new CustomScanner(new Scanner(System.in));

    private static void deleteFile(String path) {
        File file = new File(path);
        if (!file.delete()) {
            System.out.println("Failed to delete "+path+".");
        }
    }

    private static void createFileIfNotExists(String path) {

        File file = new File(path);

        if (!file.exists()) {
            try{
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error to creation "+path+" file.");
                e.printStackTrace();
            }
        }
    }

    private static void createFolderIfNotExists(String path) {

        File folder = new File(path);

        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                System.out.println("Error to creation "+path+" file.");
                e.printStackTrace();
            }
        }
    }

    private static void createNecessaryFilesIfNotExist() {

        createFileIfNotExists("database/users.csv");
        createFileIfNotExists("database/stores.csv");

        createFolderIfNotExists("database/stores");
        createFolderIfNotExists("database/inventories");

        try {
            BufferedReader br = new BufferedReader(new FileReader("database/stores.csv"));
                        
            String line;

            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;

                String name = line.split(";")[1];
                createFileIfNotExists("database/stores/"+name+".csv");
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading database/stores.csv file.");
            e.printStackTrace();
        }
        
    }

    private static int getSizeOfFile(String path){
        int counter = 0;

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;

            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;

                counter++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error reading "+path+"file.");
            e.printStackTrace();
        }

        return counter;
    }

    static Store[] getListStores() {
        
        // File path
        String path = "database/stores.csv";

        createFileIfNotExists(path);

        try {

            int size = getSizeOfFile(path);
            
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            String line;

            Store[] stores = new Store[size];

            int counter = 0;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;

                Store temp = new Store(line);
                
                Element[] elements = getElementsOf("database/stores/"+temp.getName()+".csv");

                temp.setElements(elements);

                stores[counter] = temp;

                counter++;
            }

            br.close();

            return stores;
                
        } catch (IOException e) {
            System.out.println("Error reading database/stores.csv file.");
            e.printStackTrace();
        }
        
        return new Store[0];
    }

    private static Element[] getElementsOf(String path) {

        try {
    
            int size = getSizeOfFile(path);

            Element[] elements = new Element[size];
            
            BufferedReader br = new BufferedReader(new FileReader(path));
    
            String line;

            int counter = 0;
            // Read file line by line
            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;

                String[] subStrings = line.split("#");

                Element temp;

                switch (subStrings[0]) {
                    case "Food":
                        temp = new Food(line);
                        break;
                    case "Computer":
                        temp = new Computer(line);
                        break;
                    case "Laptop":
                        temp = new Laptop(line);
                        break;
                    case "Server":
                        temp = new Server(line);
                        break;
                    default:
                        temp = new Element(line);
                        break;
                }

                elements[counter] = temp;
                counter++;
            }

            br.close();

            return elements;
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading exceptions
        }
        
        return new Element[0];
        
    }

    private static User[] getUsers() {

        // File path
        String path = "database/users.csv";

        // Check if the file exists. If the file doesn't exists created.
        try {

            int size = getSizeOfFile(path);
            
            BufferedReader br = new BufferedReader(new FileReader(path));
            
            String line;

            User[] users = new User[size];

            int counter = 0;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;
                
                String[] subStrings = line.split("#");
                User temp;

                switch (subStrings[0]) {
                    case "Owner":
                        temp = new Owner(line);
                        break;
                    default: 
                        temp = new User(line);
                }

                users[counter] = temp;

                counter++;
            }

            br.close();

            return users;
                
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading database/users.csv file.");
        }
        
        return new User[0];
    }

    private static void writeToFile(String path, String content, boolean appendMode) {

        try (FileWriter writer = new FileWriter(path, appendMode)) {
            writer.write(content+"\n");
        } catch (IOException e) {
            System.out.println("Error writing to "+path+" file: " + e.getMessage());
        }
    }


    private static User login() {

        User[] users = getUsers();

        for (int i = 0; i < users.length; i++) {
            if (!(users[i] instanceof Owner)) {   
                createFileIfNotExists("database/inventories/"+users[i].getUsername()+".csv");
            }
        }

        boolean findUser = false;

        User user = null;

        do {
            String username = scanner.scanAlphanumericString("Username: ", "Insert only alphanumeric values! ");
            
            for(int i = 0; i < users.length; i++) {
                if (users[i].getUsername().equals(username)){   
                    findUser = true; 
                    user = users[i];
                }
            }

            if (!findUser) {
                
                boolean response = scanner.scanYesNo("This user doesn't exist. Do you want to create? (yes/no): ", "Insert right word.");

                if (response) {

                        String password = scanner.scanAlphanumericString("Password: ", "Insert only alphanumeric values! ");

                        String type = scanner.scanAlphanumericString("User or Owner?: ", "Select User or Owner.", new String[]{"User", "Owner"});
                            
                        User newUser;
                        
                        if(type.equals("Owner")) {
                            newUser = new Owner(users.length + 1, username, password);
                        }else {
                            newUser = new User(users.length + 1, username, password);
                            createFileIfNotExists("database/inventories/"+username+".csv");
                        }
                        
                        writeToFile("database/users.csv", newUser.toString(), true);
                        
                        User[] newUsers = new User[users.length + 1];

                        for(int i = 0; i < users.length; i++) {
                            newUsers[i] = users[i];
                        }

                        newUsers[users.length] = newUser;

                        users = newUsers;

                        user = newUser;

                        findUser = true;
                }

            }else {
                do {
                    String password = scanner.scanAlphanumericString("Password: ", "Insert only alphanumeric values! ");
                    findUser = user.getPassword().equals(password);
                    if(!findUser){
                        System.out.println("Wrong password! ");
                    }
                }while(!findUser);
            }

        } while (!findUser);

        return user;

    }

    private static void principalMenu(User user) {

        String menuSelection;

        String[] acceptedValues = new String[]{
            "s", "store",
            "q", "quit"
        };

        do {
            System.out.println();
            System.out.println("####################################");
            System.out.println("Menu': ");
            System.out.println();
            System.out.println("s(tore)] Select store.");
            if (!(user instanceof Owner)){
                acceptedValues = new String[]{
                    "s", "store",
                    "i", "inventory",
                    "q", "quit"
                };
                System.out.println("i(nventory)] Open inventory.");
            } 
            System.out.println("q(uit)] Quit.");
            System.out.println("####################################");
            System.out.println();
            
            menuSelection = scanner.scanAlphanumericString(
                "Your selection: ",
                "Insert the right word.",
                acceptedValues
            );

            switch (menuSelection) {
                case "s":
                case "store":
                    storeSelection(user);
                    scanner.pressEnter();
                    break;
                case "i":
                case "inventory":
                    seeInventory(user);
                    scanner.pressEnter();
                    break;
                default:
                    menuSelection = "q";
                    break;
            }


        } while (menuSelection != "q");

    }

    private static void storeSelection(User user) {
        
        System.out.println();
        
        System.out.println("####################################");
        System.out.println("Stores: ");
        System.out.println();
        
        Store[] listStores = null;

        if (user instanceof Owner) {
            listStores = ((Owner)user).getStores();
        }else {
            listStores = getListStores();
        }
        
        if(listStores.length > 0) {
            for (int i = 0; i < listStores.length; i++) {
                System.out.println((i+1)+"] "+listStores[i].getName());
            }

            if (user instanceof Owner) {
                System.out.println();
                System.out.println("Menu'");
                System.out.println();
                System.out.println("a(dd)] Add store.");
                System.out.println("s(elect)] Select store.");
                System.out.println("q(uit)] Quit.");
                System.out.println();

                String selection = scanner.scanAlphanumericString("Your selection: ", "Insert the right word.", new String[]{
                    "a", "add",
                    "s", "select",
                    "q", "quit"
                });
                switch (selection) {
                    case "a":
                    case "add":
                        Store newStore = createNewStore();
                        ((Owner)user).pushStore(newStore);
                        updateStoreOf((Owner) user);
                        break;
                    case "s":
                    case "select":
                        int storeSelectionNumber = scanner.scanInt("Index: ", "Insert a valid integer number in 1 <= x <= "+listStores.length+". ",1, listStores.length) - 1;
                        ownerMenuStore(listStores[storeSelectionNumber], (Owner) user);
                        break;
                    default:
                        break;
                }
                
            }else {
                int storeSelectionNumber = scanner.scanInt("Index: ", "Insert a valid integer number in 1 <= x <= "+listStores.length+". ",1, listStores.length) - 1;
                seeStore(listStores[storeSelectionNumber], user);
            }
        }else {
            
            System.out.println("No stores found.");
            if (user instanceof Owner) {
            
                boolean response = scanner.scanYesNo("Do you want to create? (yes/no) ", "Insert the right word.");
                if(response) {
                    Store newStore = createNewStore();
                    ((Owner)user).pushStore(newStore);
                    updateStoreOf((Owner) user);
                    seeStore(newStore, user);
                }
            }
        }
        
        System.out.println("####################################");
        System.out.println();

    }

    private static Store createNewStore() {
        
        Store[] stores = getListStores();

        boolean validString = false;
        String name = "";
        
        do {
            name = scanner.scanAlphanumericString("Name: ", "Insert only alphanumeric characters. ");
            
            validString = true;
            // Check if there is store with same name
            for (int i = 0; i < stores.length; i++)
                if (stores[i].getName().equals(name)) {
                    System.out.println("Already exists a store with this name.");
                    validString = false;
                    break;
                }

        } while(!validString);

        long id = stores.length + 1;

        Store newStore = new Store(id, name);

        writeToFile("database/stores.csv", newStore.toString(), true);

        createFileIfNotExists("database/stores/"+name+".csv");
        
        return newStore;
    }

    private static Element createNewElementOf(Store store) {

        System.out.println();
        System.out.println("Type: ");
        System.out.println();
        System.out.println("g(eneric)");
        System.out.println("f(ood)");
        System.out.println("c(omputer)");
        System.out.println("l(aptop)");
        System.out.println("s(erver)");
        System.out.println();

        String elementType = scanner.scanAlphanumericString(
            "Your selection: ",
            "Insert the right word.", 
            new String[] {
                "g", "generic",
                "f", "food",
                "c", "computer",
                "l", "laptop",
                "s", "server"
            }    
        );

        ElementType type;
        switch (elementType) {
            case "f":
            case "food":
                type = ElementType.FOOD;
                break;
            case "c":
            case "computer":
                type = ElementType.COMPUTER;
                break;
            case "l":
            case "laptop":
                type = ElementType.LAPTOP;
                break;
            case "s":
            case "server":
                type = ElementType.SERVER;
                break;
            default:
                type = ElementType.GENERIC;
                break;
        }

        return createNewElementOf(store, type);
    }

    private static Element createNewElementOf(Store store, ElementType type) {

        Element[] elements = getElementsOf("database/stores/"+store.getName()+".csv");

        boolean validString = false;

        long id = elements.length + 1;
        String name = "";
        

        do {
            name = scanner.scanAlphanumericString("Name: ", "Insert only alphanumeric characters. ");
            
            validString = true;
            // Check if there is store with same name
            for (int i = 0; i < elements.length; i++)
                if (elements[i].getName().equals(name)) {
                    System.out.println("Already exists an element with this name.");
                    validString = false;
                    break;
                }

        } while(!validString);

        double price = scanner.scanDouble("Price: ", "Insert a valid real number >= 0.", 0);
        long ean = scanner.scanLong("EAN: ", "Insert a valid integer number >= 0.", 0);
        String description = scanner.scanAlphanumericString("Description: ", "Insert only alphanumeric characters.");
        int stars = scanner.scanInt("Stars (0/5): ", "Insert a valid integer number between 0 and 5", 0, 5);
        double weight = 0;
        do {
            weight = scanner.scanDouble("Weight: ", "Insert a valid weight > 0.", 0);
            if (weight == 0) 
                System.out.println("Insert a valid weight > 0.");
        } while (weight == 0);
        
        Element newElement = null;

        if(type == ElementType.GENERIC)
            newElement = new Element(id, name, price, ean, description, stars, weight);
        else if(type == ElementType.FOOD) {
            Date expirationDate = scanner.scanDate("Expiration date (YYYY-MM-DD): ", "Insert a valid date in this format YYYY-MM-DD.");

            double kCal = scanner.scanDouble("kCal: ", "Insert a valid real number >= 0", 0);
            double carbs = scanner.scanDouble("Carbs: ", "Insert a valid real number >= 0", 0);
            double proteins = scanner.scanDouble("Proteins: ", "Insert a valid real number >= 0", 0);
            double fats = scanner.scanDouble("Fats: ", "Insert a valid real number >= 0", 0);

            Nutritional values = new Nutritional(kCal, carbs, proteins, fats);

            newElement = new Food(id, name, price, ean, description, stars, weight, expirationDate, values);
        } else {
            String processor = scanner.scanAlphanumericString("Processor: ", "Insert only alphanumeric characters.");
            String gpu = scanner.scanAlphanumericString("Gpu: ", "Insert only alphanumeric characters.");
            String motherboard = scanner.scanAlphanumericString("Motherboard: ", "Insert only alphanumeric characters.");
            long ramBytes = scanner.scanLong("ramBytes: ", "Insert a valid integer number >= 1.", 1);

            newElement = new Computer(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes);

            if (type == ElementType.LAPTOP) {
                int inchesScreen = scanner.scanInt("Inches screen: ", "Insert a valid integer number > 0.", 1);
                newElement = new Laptop(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes, inchesScreen);
            }

            if (type == ElementType.SERVER) {
                String endpoint = scanner.scanAlphanumericString("Endpoint: ", "Insert only alphanumeric characters.");
                newElement = new Server(id, name, price, ean, description, stars, weight, processor, gpu, motherboard, ramBytes, endpoint);
            }
        }
        
        return newElement;
    }



    private static void deleteStore(Store store){
        Store[] stores = getListStores();
        
        String newStores = "";

        for(int i = 0; i < stores.length - 1; i++) {
            if (stores[i].getId() != store.getId()) {
                newStores += stores[i].toString() + "\n";
            }
        }
        if (stores.length > 0 && stores[stores.length - 1].getId() != store.getId()) {
                newStores += stores[stores.length - 1].toString();
        }

        writeToFile("database/stores.csv", newStores, false);

        deleteFile("database/stores/"+store.getName()+".csv");

    }

    private static void updateStoreOf(Owner owner) {

        User[] users = getUsers();
        
        String stringUsers = "";
        for(int i = 0; i < users.length - 1; i++) {
            if (users[i].getId() == owner.getId()) 
                stringUsers += owner.toString() + "\n";
            else 
                stringUsers += users[i].toString() + "\n";
        }

        if (users[users.length - 1].getId() == owner.getId()) 
            stringUsers += owner.toString();
        else 
            stringUsers += users[users.length - 1].toString();

        writeToFile("database/users.csv", stringUsers, false);
    }

    private static void updateStoreElements(Store store) {

        Element[] elements = store.getElements();

        String stringElements = "";
        for(int i = 0; i < elements.length; i++) {
            stringElements += elements[i].toString() + "\n";
        }

        writeToFile("database/stores/"+store.getName()+".csv", stringElements, false);

    }

    private static void ownerMenuStore(Store store, Owner owner) {
        System.out.println("Menu' of "+store.getName()+": ");
        System.out.println();
        System.out.println("a(dd)] Add element.");
        System.out.println("l(ist)] List elements.");
        System.out.println("d(elete)] Delete store.");
        System.out.println("q(uit)] Quit.");
        System.out.println();

        String ownerSelection = scanner.scanAlphanumericString(
            "Your selection: ",
            "Insert the right word.",
            new String[]{
                "a", "add",
                "l", "list",
                "d", "delete",
                "q", "quit"
            }
        );

        switch (ownerSelection) {
            case "a":
            case "add":
                Element newElement = createNewElementOf(store);
                store.pushElement(newElement);

                createFileIfNotExists("database/stores/"+store.getName()+".csv");
                writeToFile("database/stores/"+store.getName()+".csv", newElement.toString(), true);

                
                break;
            case "d":
            case "delete":
                owner.deleteStore(store);
                updateStoreOf(owner);
                deleteStore(store);
                break;
            case "l":
            case "list":
                seeStore(store, owner);
                break;
            default:
                break;
        }
    }

    private static void seeStore(Store store, User user) {

        System.out.println();
        int elementsCount = store.getElementsCount();

        System.out.println("Elements of "+store.getName()+": ");
        
        if(elementsCount > 0) {
            elementSelection(store, user);
        }else {   
            System.out.println("No elements found.");
            if (user instanceof Owner) {
            
                boolean response = scanner.scanYesNo("Do you want to create? (yes/no) ", "Insert the right word.");
                if(response) {
                    Element newElement = createNewElementOf(store);
                    store.pushElement(newElement);
                    updateStoreElements(store);
                }
            }
        }
    }

    private static void seeElement(Element element) {
        System.out.println();
        System.out.println("####################################");
        System.out.println("Name: "+element.getName());
        System.out.println("Price: "+element.getPrice());
        System.out.println("EAN: "+element.getEan());
        System.out.println("Description: "+element.getDescription());
        System.out.println("Stars: "+element.getStars());
        System.out.println("Weight: "+element.getWeight());
        
        if (element instanceof Food) {
            Food food = ((Food) element);

            System.out.println("Expiration Date: "+food.getExpirationDate().toString());
            System.out.println("Values: ");

            Nutritional values = food.getNutrionalValues();

            System.out.println("kCal: "+values.getKcal());
            System.out.println("Carbs: "+values.getCarbs());
            System.out.println("Proteins: "+values.getProteins());
            System.out.println("Fats: "+values.getFats());
        }else if (element instanceof Computer) {
            Computer computer = ((Computer) element);

            System.out.println("Processor: "+computer.getProcessor());
            System.out.println("GPU: "+computer.getGpu());
            System.out.println("Motherboard: "+computer.getMotherboard());
            System.out.println("RamBytes: "+computer.getRamBytes());

            if (element instanceof Laptop) {
                System.out.println("Inches Screen: "+((Laptop) computer).getInchesScreen());
                
            }

            if (element instanceof Server) {
                System.out.println("Endpoint: "+((Server) computer).getEndpoint());
            }
        }

        System.out.println("####################################");

    }

    private static String[] generateFieldNames(Element element) {

        String[] acceptedValues = new String[] {
            "name", "price", "ean", "description", "stars", "weight" 
        };

        if (element instanceof Food) {

            acceptedValues = new String[] {
                "name", "price", "ean", "description", "stars", "weight", "expirationDate", "kCal", "carbs", "proteins", "fats"
            };
        }else if (element instanceof Computer) {
            
            acceptedValues = new String[] {
                "name", "price", "ean", "description", "stars", "weight", "processor", "gpu", "motherboard", "rambytes"
            };

            if (element instanceof Laptop) {
                acceptedValues = new String[] {
                    "name", "price", "ean", "description", "stars", "weight", "processor", "gpu", "motherboard", "rambytes", "inches"
                };
            }

            if (element instanceof Server) {
                acceptedValues = new String[] {
                    "name", "price", "ean", "description", "stars", "weight", "processor", "gpu", "motherboard", "rambytes", "endpoint"
                };
            }
        }

        return acceptedValues;
    }

    private static void updateElementField(Element[] elements, Element element, String field) {
        switch (field) {
            case "name":
                String newName = "";
                boolean validString = false;
                do {
                    newName = scanner.scanAlphanumericString("Name: ", "Insert only alphanumeric characters: ");
                    // Check if there is element with same name
                    validString = true;
                    for (int i = 0; i < elements.length; i++)
                        if (elements[i].getName().equals(newName)) {
                            System.out.println("Already exists an element with this name.");
                            validString = false;
                            break;
                        }
                } while (!validString);
                element.setName(newName);
                break;
            case "price":
                element.setPrice(scanner.scanDouble("Price: ", "Insert a valid real number >= 0", 0));
                break;
            case "ean":
                element.setEan(scanner.scanLong("EAN: ", "Insert a valid integer number >= 0", 0));
                break;
            case "description":
                element.setDescription(scanner.scanAlphanumericString("Description: ", "Insert only alphanumeric characters: "));
                break;
            case "stars":
                element.setStars(scanner.scanInt("Stars (0/5): ", "Insert a valid integer number 0 <= x <= 5", 0, 5));
                break;
            case "weight":
                double weight = 0;
                do {
                    weight = scanner.scanDouble("Weight: ", "Insert a valid weight > 0.", 0);
                    if (weight == 0) 
                        System.out.println("Insert a valid weight > 0.");
                } while (weight == 0);
                element.setWeight(weight);
                break;
            case "expirationDate":
                ((Food) element).setExpirationDate(scanner.scanDate("Date (YYYY-MM-DD): ", "Insert a valid date: "));
                break;
            case "kCal":         
                Nutritional valuesKcal = ((Food) element).getNutrionalValues();
                valuesKcal.setKcal(scanner.scanDouble("kCal: ", "Insert a valid real number >= 0", 0));
                ((Food)element).setNutrionalValues(valuesKcal);
                break;
            case "carbs":
                Nutritional valuesCarbs = ((Food) element).getNutrionalValues();
                valuesCarbs.setCarbs(scanner.scanDouble("Carbs: ", "Insert a valid real number >= 0", 0));
                ((Food)element).setNutrionalValues(valuesCarbs);
                break;
            case "proteins":
                Nutritional valuesPro = ((Food) element).getNutrionalValues();
                valuesPro.setProteins(scanner.scanDouble("Proteins: ", "Insert a valid real number >= 0", 0));
                ((Food)element).setNutrionalValues(valuesPro); 
                break;
            case "fats": 
                Nutritional valuesFats = ((Food) element).getNutrionalValues();
                valuesFats.setProteins(scanner.scanDouble("Fats: ", "Insert a valid real number >= 0", 0));
                ((Food)element).setNutrionalValues(valuesFats);
                break;
            case "processor":
                ((Computer)element).setProcessor(scanner.scanAlphanumericString("Processor: ", "Insert only alphanumeric characters"));
                break;
            case "gpu":
                ((Computer)element).setGpu(scanner.scanAlphanumericString("Gpu: ", "Insert only alphanumeric characters"));
                break;
            case "motherboard":
                ((Computer)element).setMotherboard(scanner.scanAlphanumericString("Motherboard: ", "Insert only alphanumeric characters"));
                break;
            case "rambytes":
                ((Computer)element).setRamBytes(scanner.scanLong("Ram Bytes: ", "Insert a valid long number >= 1", 1));
                break;
            case "inches":
                ((Laptop)element).setInchesScreen(scanner.scanInt("Inches screen: ", "Insert a valid integer number >= 1", 1));
                break;
            case "endpoint":
                ((Server)element).setEndpoint(scanner.scanAlphanumericString("Endpoint: ", "Insert only alphanumeric characters"));
                break;
        
            default:
                break;
        }

    }

    private static void elementSelection(Store store, User user) {

        Element[] elements = store.getElements();

        int elementsCount = elements.length;
        
        for (int i = 0; i < elements.length; i++) {
            System.out.println((i+1)+"] "+elements[i].getName());
        }

        int elementSelectionNumber = scanner.scanInt("Index element: ", "Insert a valid integer number between 1 and "+elementsCount+".", 1, elementsCount);
            
        Element element = elements[elementSelectionNumber - 1];

        String[] actions = new String[]{
            "p", "purchase",
            "b", "back"
        };
            
        System.out.println("Recap: ");
        System.out.println();
        
        seeElement(element);

        System.out.println();
        System.out.println("What do you want to do? ");
        System.out.println();
        if (user instanceof Owner){
            actions = new String[]{
                "u", "update",
                "d", "delete",
                "b", "back"
            };
            System.out.println("u(pdate)] Update a field.");
            System.out.println("d(elete)] Delete.");
        } else {
            System.out.println("p(urchase)] Purchase.");
        }
        System.out.println("b(ack)] Back.");

        String action = scanner.scanAlphanumericString("Your selection: ", "Insert the right word.", actions);

        switch (action) {
            case "u":
            case "update":
                do {

                    String[] acceptedValues = generateFieldNames(element);

                    String infoText = "Insert one value from this list: \n";
                    infoText += "[";
                    for(int i = 0; i < acceptedValues.length; i++)
                        infoText += "\t" +acceptedValues[i] + "\n";
                    infoText += "]";

                    System.out.println(infoText);
                    String selection = scanner.scanAlphanumericString("Your selection: ", "Insert the right word.", acceptedValues);

                    updateElementField(elements, element, selection);
                    updateStoreElements(store);
                }while (scanner.scanYesNo("Update another field? (yes/no) ", "Insert the right word.")); 
                break;
            case "d":
            case "delete":
                store.deleteElement(elementSelectionNumber-1);
                updateStoreElements(store);
                break;
            case "p":
            case "purchase":
                Element[] userElements = getElementsOf("database/inventories/"+user.getUsername()+".csv");
                boolean validPurchase = true;

                for (int i = 0; i < userElements.length; i++) {
                    if (userElements[i].getName().equals(element.getName())){
                        validPurchase = false;
                        break;
                    }
                }

                if(!validPurchase){
                    System.out.println("You have this item! Select another.");
                }else {
                    // Add to user inventory
                    writeToFile("database/inventories/"+user.getUsername()+".csv", element.toString(), true);
                    
                    // Remove from store
                    store.deleteElement(elementSelectionNumber - 1);
                    updateStoreElements(store);
                }
                break;
            default:
                break;
        }
        
    }

    private static void seeInventory(User user) {
        
       
        Element[] elements = getElementsOf("database/inventories/"+user.getUsername()+".csv");

        if (elements.length > 0) {
            int index = 0;
            do {
                System.out.println();
                System.out.println("\nInventory: ");
                System.out.println();

                for (int i = 0; i < elements.length; i++) {
                    System.out.println((i+1)+"] "+elements[i].getName());
                }

                System.out.println();
                index = scanner.scanInt("Index (0 to close): ", "Insert an integer number >= 0. 0 close inventory", 0, elements.length) - 1;
                if (index != -1)
                    seeElement(elements[index]);
            } while(index != -1);
        } else {
            System.out.println();
            System.out.println("\nInventory: ");
            System.out.println();
            System.out.println("No elements found.");
        }
        System.out.println();
        
    }

    public static void main(String[] args) throws Exception {
        
        createNecessaryFilesIfNotExist();

        User user = login();

        principalMenu(user);
        
        scanner.close();

    }

    
}