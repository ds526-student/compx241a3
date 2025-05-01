public class StrHashTableCollisions {
    Node[] hashTable; // creates the hash table
    
    // counters
    int size, numFullEntries, numRehashes;

    /**
     * constructor for the hash table
     * @param size the size of the hash table
     */
    public StrHashTableCollisions(int size) {
        this.size = size;
        hashTable = new Node[size]; // creates the hash table with the given size
        // set counters to 0
        numFullEntries = 0;
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
        
        int index = hashFunction(key); // calculates the index for the key
        Node node = hashTable[index]; // gets the node at the index

        // checks if the hash table is above 80% full, then rehashes
        if (numFullEntries >= size * 0.8) {
            rehash();
            numRehashes++;
        }
        
        // checks if the node is empty, if so insert
        if (node == null) {
            node = new Node(key, value);
            hashTable[index] = node;
            numFullEntries++;
        } else { // if it's not empty create a node and add it to the end of the linked list
            while (node.next != null) {
                node = node.next;
            }
            Node newNode = new Node(key, value);
            node.next = newNode;
            numFullEntries++;
        }
    }

    /**
     * deletes a key-value pair from the hash table
     * @param key the key to be deleted
     */
    public void delete(String key) {
        int index = hashFunction(key); // calculates the index for the key
        Node node = hashTable[index]; // gets the node at the index

        // checks if the node is empty, if so return
        while (node != null) {
            // checks if the key is found, if so delete it and the rest of the linked list
            if (node.key.equals(key)) {
                node.value = null;
                node = node.next;
                hashTable[index] = null;
                numFullEntries--;
            } else {
                node = node.next;
            }
        }
    }   
    
    /**
     * rehashes the hash table by doubling its size
     */
    public void rehash() {
        Node[] temp = hashTable;
        size *= 2;
        hashTable = new Node[size];
        numFullEntries = 0; 

        for (Node t : temp) { // for each node in the old hash table
            while (t != null) { // while the node is not null
                insert(t.key, t.value);
                t = t.next; 
            }
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
        int index = hashFunction(key); // calculates the index for the key
        Node node = hashTable[index]; // gets the node at the index
        while (node != null) { // while the node is not null, build the string to print 
            StringBuilder sb = new StringBuilder();
            sb.append(node.key).append(" : ");
            while (node != null) {
                sb.append(node.value);
                if (node.next != null) {
                    sb.append(", ");
                }
                node = node.next;
            }
            System.out.println(sb.toString());
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
        for (Node node : hashTable) { // for each node in the hash table
            if (node != null) { // checks if the node is not null, if so build the string to print
                StringBuilder sb = new StringBuilder();
                sb.append(node.key).append(" : ");
                while (node != null) {
                    sb.append(node.value);
                    if (node.next != null) {
                        sb.append(", ");
                    }
                    node = node.next;
                }
                System.out.println(sb.toString());
            } else { // else print null
                System.out.println("null");
            }
        }
    }
    
}
