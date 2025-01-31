public class Store {
    private long id;
    private String name;
    private Element[] elements;

    // Constructor
    public Store(long id, String name) {
        this.id = id;
        this.name = name;

        this.elements = new Element[0];
    }

    public Store(String storeFromString) {

        String[] subStrings = storeFromString.split(";");

        id = Long.parseLong(subStrings[0]);
        name = subStrings[1];

        this.elements = new Element[0];
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getElementsCount() {
        return this.elements.length;
    }

    public Element[] getElements() {
        return elements;
    }

    public Element getElement(int index) {

        if (elements.length == 0) {
            throw new IndexOutOfBoundsException("Elements don't set.");
        }

        if (index >= 0 && index < elements.length) {
            return elements[index];
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(elements.length-1));
        }
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElements(Element[] elements) {

        // It creates an array filled null
        Element[] tempElements = new Element[elements.length];
        
        // Fill partially (or entire) tempElements with elements
        for (int i = 0; i < elements.length; i += 1) {
            tempElements[i] = elements[i];
        }

        this.elements = tempElements;
    }

    // Method to set an Element at a specific index
    public void setElement(int index, Element element) {
        if (index >= 0 && index < elements.length) {
            elements[index] = element;
        } else {
            throw new IndexOutOfBoundsException("Index must be between 0 and "+(elements.length - 1));
        }
    }

    // Method to add Element at the end of array. It reallocs elements.
    public void pushElement(Element element) {

        Element[] tempElements = new Element[elements.length+1];

        for(int i = 0; i < elements.length; i++) {
            tempElements[i] = elements[i];
        }

        tempElements[elements.length] = element;

        elements = tempElements;

    }

    // Method to remove Element at the index of array. It reallocs elements.
    public void deleteElement(int index) {

        if (elements.length == 0) {
            throw new IndexOutOfBoundsException("Elements don't set.");
        }

        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Element[] tempElements = new Element[elements.length - 1];

        for(int i = 0; i < index; i++) {
            tempElements[i] = elements[i];
        }

        for(int i = index+1; i < elements.length; i++) {
            tempElements[i-1] = elements[i];
        }

        elements = tempElements;

    }

    @Override
    public String toString() {
        return id+";"+name;
    }

    
}
