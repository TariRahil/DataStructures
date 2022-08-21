package com.tarrah;

import java.util.NoSuchElementException;

public class LinkedList {

    private class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }
    private Node first;
    private Node last;
    private int size;

    public void addFirst(int item){
        var node = new Node(item);

        if (first == null)
            first = last = node;
        else{
            node.next = first;
            first = node;
        }
        size++;
    }

    public void addLast(int item){
        var node = new Node(item);

        if (first == null)
            first = last = node;
        else{
            last.next = node;
            last = node;
        }
        size++;
    }

    public boolean isEmpty(){
            return first == null;
    }

    public int indexOf(int item){
        int index = 0;
        var current = first;
        while (current != null){
            if (current.value == item)
                return index;
            current = current.next;
            index++;
        }
        //If could not find item
        return -1;
    }

    public boolean contains(int item){
        return indexOf(item) != -1;
    }

    public void removeFirst(){
        //If list is empty
        if(isEmpty())
            throw new NoSuchElementException();

        //If list has single node
        if (first == last)
            first = last = null;
        else{
            // first -> 10 -> 20 -> 30
            var secend = first.next;
            first.next = null;
            first = secend;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
            var previous = getPrevious(last);
            last = previous;
            last.next = null;
        }
        size--;
    }

    private Node getPrevious(Node node) {
        var current = first;
        while (current != null){
            if (current.next == last)
                return current;
            current = current.next;
        }
        return null;
    }

    public int size(){
        return size;
    }

    public int[] toArray(){
        var array = new int[size];
        int index = 0;
        var current = first;
        while (current != null){
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

    public void reverse(){
        if (isEmpty()) return;

        //f           l
        //10 -> 20 -> 30
        //p     c     n
        //      p     c     n
        //            p     c       n
        //n = c.next
        //c.next = p
        //l           f
        //10 <- 20 <- 30
        var previous = first;
        var current = first.next;
        while (current != null){
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k){
        if (isEmpty())
            throw new IllegalStateException();

        var firstPointer = first;
        var secondPointer = first;
        for (int i = 0 ; i < k-1 ; i++){
            secondPointer = secondPointer.next;
            if (secondPointer == null)
                throw new IllegalArgumentException();
        }
        while (secondPointer != last){
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }
        return firstPointer.value;
    }

    public void printMiddle(){
        if (isEmpty())
            throw new IllegalStateException();
        var firstPointer = first;
        var secondPointer = first;
        while (secondPointer != last && secondPointer.next != last){
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next.next;
        }
        if (secondPointer == last)
            System.out.println(firstPointer.value);
        else
            System.out.println(firstPointer.value + "  " + firstPointer.next.value);
    }

    public static LinkedList createWithLoop(){
        var list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        var node = list.last;

        list.addLast(40);
        list.addLast(50);

        list.last.next = node;

        return list;
    }

    public boolean hasLoop(){
        var slow = first;
        var fast = first;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }
}
