
public class StrHashTable {
    Node[] hashTable;
    
    int size, numFullEntries, numCollisions, numRehashes;

    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % size;
    }

    //adds the string key/value pair k,v to the hash table at the appropriate index. Do not handle collisions, simply avoid inserting the new value.
    public void insert(String key, String value) {
        Node newNode = new Node(key, value);

        if (numFullEntries >= size * 0.8) {
            rehash();
            numRehashes++;
        }
        
        // if(hashTable[hashFunction(key)] == null) {
        //     hashTable[hashFunction(key)] = newNode;
        //     numFullEntries++;
        // } else {
        //     System.out.println("Collision");
        //     numCollisions++;
        // }

        int i = 0;
        while(hashTable[(hashFunction(key) + i) % size] != null) {
            i++;
            numCollisions++;
        }
        hashTable[(hashFunction(key) + i) % size] = newNode;
        numFullEntries++;
    }

    public void delete(String key) {
        int index = hashFunction(key);
        if (hashTable[index] != null && hashTable[index].key.equals(key)) {
            hashTable[index] = null;
            numFullEntries--;
        } else {
            System.out.println("Key not found");
        }
    }   

    public void rehash() {
        Node[] temp = hashTable;
        size *= 2;
        hashTable = new Node[size];

        for (int i = 0; i < temp.length; i++) {
            Node t = temp[i];
            insert(t.key, t.value);
            numFullEntries--;
        }
    }

    public boolean contains(String key) {
        int index = hashFunction(key);
        if (hashTable[index] != null && hashTable[index].key.equals(key)) {
            return true;
        } else {
            return false;
        }
    }

    public void get(String key) {
        if (contains(key)) {
            int index = hashFunction(key);
            System.out.println(hashTable[index].value);
        } else {
            System.out.println("Key not found");
        }
    }

    public int count() {
        return numFullEntries;
    }

    public void dump() {
        for (Node hashTable1 : hashTable) {
            if (hashTable1 != null) {
                System.out.println(hashTable1.key + " : " + hashTable1.value);
            } else {
                System.out.println("null");
            }
        }
    }
}   