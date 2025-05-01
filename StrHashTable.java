
public class StrHashTable {
    Node[] hashTable; // creates the hash table
    
    // counters
    int size, numFullEntries, numCollisions, numRehashes;

    /**
     * constructor for the hash table
     * @param size the size of the hash table
     */
    public StrHashTable(int size) {
        this.size = size;
        hashTable = new Node[size]; // creates the hash table with the given size
        // set counters to 0
        numFullEntries = 0;
        numCollisions = 0;
        numRehashes = 0;
    }

    /**
     * calculates the index for the given key
     * @param key the key to be hashed
     * @return the index for the key
     */
    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) { // for each character in the key
            hash += key.charAt(i); // adds the ASCII values to generate a hash value
        }
        return hash % size;
    }

    /**
     * inserts a new key-value pair into the hash table
     * @param key the key to be inserted
     * @param value the value to be inserted
     */
    public void insert(String key, String value) {
        Node newNode = new Node(key, value);

        // checks if the hash table is above 80% full, then rehashes
        if (numFullEntries >= size * 0.8) {
            rehash();
            numRehashes++;
        }
        
        // checks if the index is empty, if so, inserts the new node
        if(hashTable[hashFunction(key)] == null) {
            hashTable[hashFunction(key)] = newNode;
            numFullEntries++;
        } else { // if not emppty, there is a collision
            System.out.println("Collision with key: " + key + " at index: " + hashFunction(key));
            numCollisions++;
        }
    }

    /**
     * deletes a key-value pair from the hash table
     * @param key the key to be deleted
     */
    public void delete(String key) {
        int index = hashFunction(key);
        // checks if the index is empty and if the key is the correct key
        if (hashTable[index] != null && hashTable[index].key.equals(key)) {
            hashTable[index] = null;
            numFullEntries--;
        } else {
            System.out.println("Key not found");
        }
    }   

    /**
     * rehashes the hash table by doubling its size
     */
    public void rehash() {
        Node[] temp = hashTable;
        size *= 2;
        hashTable = new Node[size];

        for (Node t : temp) { // for each node in the old hash table
            if (t == null) continue;
            insert(t.key, t.value);
            numFullEntries--;
        }
    }

    /**
     * checks if the hash table contains a key
     * @param key the key to be checked
     * @return true if the key is found
     */
    public boolean contains(String key) {
        int index = hashFunction(key);
        // checks if the key is the correct key
        if (hashTable[index] != null && hashTable[index].key.equals(key)) { 
            return true;
        } else {
            return false;
        }
    }

    /**
     * retrieves the value for a given key
     * @param key the key to be retrieved
     * @return the value for the key
     */
    public void get(String key) {
        if (contains(key)) { // checks if the key exists
            int index = hashFunction(key);
            System.out.println("The value for key " + key + " = " + hashTable[index].value);
        } else {
            System.out.println("Key '" + key + "' not found");
        }
    }

    /**
     * counts the number of entries in the hash table
     * @return the number of entries in the hash table, this is different from the size
     */
    public int count() {
        return numFullEntries;
    }

    /**
     * prints all the entries in the hash table
     */
    public void dump() {
        for (Node node : hashTable) {
            if (node != null) {
                System.out.println(node.key + " : " + node.value);
            } else {
                System.out.println("null");
            }
        }
    }
}   