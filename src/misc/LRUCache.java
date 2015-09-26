package misc;

// Design and implement a data structure for Least Recently Used (LRU) cache.
// It should support the following operations: get and set.
//   get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
//       otherwise return -1.
//   set(key, value) - Set or insert the value if the key is not already present.
//       When the cache reached its capacity,
//       it should invalidate the least recently used item before inserting a new item.


import java.util.HashMap;


public class LRUCache {

    class Node {
        int key;
        int value;
        Node pre = null;
        Node next = null;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
    Node head = null;
    Node end = null;


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        return 0;
    }

    public void set(int key, int value) {

    }
}

