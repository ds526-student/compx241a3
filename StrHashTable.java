public class StrHashTable {
    Node[] hashTable;
    
    int size, numFullEntries, numCollisions, numRehashes;


    //adds the string key/value pair k,v to the hash table at the appropriate index. Do not handle collisions, simply avoid inserting the new value.
    public void insert(String key, String value) {
        Node newNode = new Node(key, value);

        if (numFullEntries >= size * 0.8) {
            // rehash();
            numRehashes++;
        }
        
        // int i = 0;
        // while(hashTable[(getHash(key) + i) % size] != null) {
        //     i++;
        //     numCollisions++;
        // }
        // hashTable[(getHash(key) + i) % size] = newNode;
        // numFullEntries++;
    }
}   