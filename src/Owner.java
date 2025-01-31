public class Owner extends User{

    private Store[] stores;

    // Constructors
    Owner(long id, String username, String password) {
        super(id, username, password);
        stores = new Store[0];
    }

    Owner(String ownerFromString) {
        super(ownerFromString);
        try {
            String[] subStrings = ownerFromString.split("#");

            if (subStrings.length > 2) {
                String[] idStringStores = subStrings[2].split(",");

                if (idStringStores.length > 0) {
                    // Take all stores from db
                    Store[] allStores = App.getListStores();

                    // Filtered
                    Store[] tempStores = new Store[idStringStores.length];

                    for (int i = 0; i < idStringStores.length; i++) {
                        boolean find = false;
                        long id = Integer.parseInt(idStringStores[i]);
                        for (int j = 0; j < allStores.length; j++) {
                            if (allStores[j].getId() == id){
                                tempStores[i] = allStores[j];
                                find = true;
                                break;
                            }
                        }

                        if (!find)
                            throw new Exception("Store "+id+" not found! File is corrupted.");
                    }
                    this.stores = tempStores;
                }else {
                    this.stores = new Store[0];
                }

            } else {
                this.stores = new Store[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Format error! "+ownerFromString);
        }
    }

    // Getter
    public int getNumberStores() {
        return this.stores.length;
    }

    public Store[] getStores() {
        return this.stores;
    }

    public Store getStore(int index) {

        if (stores.length == 0) {
            throw new IndexOutOfBoundsException("Stores don't set.");
        }

        if (index >= 0 && index < stores.length) {
            return stores[index];
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(stores.length-1));
        }
    }

    // Setters
    public void setStores(Store[] stores) {
        this.stores = stores;
    }

    public void setStore(int index, Store store) {
        if (index >= 0 && index < stores.length) {
            stores[index] = store;
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(stores.length - 1));
        }
    }

    public void pushStore(Store store) {

        Store[] tempStores = new Store[stores.length+1];
        
        for(int i = 0; i < stores.length; i++) {
            tempStores[i] = stores[i];
        }

        tempStores[stores.length] = store;

        stores = tempStores;
        
    }

    public void deleteStore(Store store) {

        Store[] tempStores = new Store[stores.length - 1];
        
        int iterator = 0;
        for(int i = 0; i < stores.length; i++) {   
            if (stores[i].getId() != store.getId()){       
                tempStores[iterator] = stores[i];
                iterator++;
            }
        }

        stores = tempStores;
        
    }

    public String getType() {
        return "Owner";
    }

    @Override
    public String toString() {

        String idStores = "";
        for (int i = 0; i < stores.length - 1; i++) {
            idStores += stores[i].getId()+ ",";
        }

        if (stores.length > 0) {
            idStores += stores[stores.length - 1].getId();
        }

        return super.toString()+"#"+idStores;
    }

    
}
