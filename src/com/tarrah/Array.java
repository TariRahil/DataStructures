package com.tarrah;

public class Array {
    private int[] items;
    private int count;

    public Array(int length) {
        items = new int[length];
    }

    public void print() {
        for (int i=0; i<count; i++){
            System.out.println(items[i]);
        }
    }

    public void insert(int item) {
        //Check array is full or not
        checkForResize();

        //Add the new item at the end
        items[count++] = item;
    }

    private void checkForResize() {
        //If the array is full, resize it
        if (items.length == count){
            //Create a new array (twice the size)
            int[] newItem = new int[count * 2];
            //Copy all the existing item
            for (int i=0 ; i<count; i++)
                newItem[i] = items[i];
            //Set "items" to this new array
            items = newItem;
        }
    }

    public void insertAt(int item, int index) {
        if (index < 0 || index > count)
            throw new IllegalArgumentException();

        // Note that I've extracted the logic for
        // resizing the array into this private
        // method so we can reuse in insert() and
        // insertAt() methods.
        //
        // This also made our code cleaner and
        // more readable.
        checkForResize();

        for (int i = count - 1; i >= index; i--)
            items[i + 1] = items[i];

        items[index] = item;
        count++;
    }


    public void reverse() {
        int[] reverseArray = new int[count];
        for (int i=0 ; i<count ; i++){
            reverseArray[i] = items[count-i-1];
        }
        items = reverseArray;
    }

    public int max() {
        int max = 0;
        for (int item: items)
            if (item > max)
                max = item;

        return max;
    }

    public Array intersect(Array other) {
        var intersection = new Array(count);

        for (int item : items)
            if (other.indexOf(item) >= 0)
                intersection.insert(item);

        return intersection;
    }

    public int indexOf(int item) {
        //If we find it, return index
        //Otherwise return -1
        //Best case: the item is the first item in array : O(1)
        //Worst case: the item is the end of the array: O(n)
        for (int i=0; i<count; i++){
            if (items[i] == item)
                return i;
        }
        return -1;
    }


    public void removeAt(int index) {
        //Validate the index
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();
        //Shift the items to the left to fill the hole
        //[10, 20, 30, 40]
        //index: 1
        //1 <- 2
        //2 <- 3
        for (int i=index ; i<count ; i++)
            items[i] = items[i+1];

        count--;
    }
}
