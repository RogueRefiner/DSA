package com.customqueue;

import org.junit.Assert;
import org.junit.Test;

public class QueueTests {

    @Test
    public void add_addElementToQueue_True(){
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        integerQueue.add(42);
        stringQueue.add("Hello");

        Assert.assertEquals(integerQueue.size(), 1);
        Assert.assertEquals(stringQueue.size(), 1);
    }

    @Test
    public void element_RetrieveElementFromEmptyQueue_EmptyQueueException() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        Throwable integerException = Assert.assertThrows(EmptyQueueException.class, () -> integerQueue.element());
        Throwable stringException = Assert.assertThrows(EmptyQueueException.class, () -> stringQueue.element());

        String expectedErrorMessage = "The queue is empty. No element can be retrieved.";
        Assert.assertEquals(expectedErrorMessage, integerException.getMessage());
        Assert.assertEquals(expectedErrorMessage, stringException.getMessage());
    }

    @Test
    public void element_RetrieveElementFromQueue_True() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        integerQueue.add(42);
        stringQueue.add("Hello");

        Integer retrievedInt = null;
        String retrievedString = null;
        try {
            retrievedInt = integerQueue.element();
            retrievedString = stringQueue.element();
        } catch (EmptyQueueException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(integerQueue.size(), 1);
        Assert.assertEquals(stringQueue.size(), 1);
        try {
            Assert.assertEquals(retrievedInt, integerQueue.element());
            Assert.assertEquals(retrievedString, stringQueue.element());
        } catch (EmptyQueueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void peek_RetrieveElementFromEmptyQueue_Null() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        Assert.assertEquals(integerQueue.peek(), null);
        Assert.assertEquals(stringQueue.peek(), null);
    }

    @Test
    public void peek_RetrieveElementFromQueue_True() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        integerQueue.add(42);
        stringQueue.add("Hello");

        Integer retrievedInt = integerQueue.peek();
        String retrievedString = stringQueue.peek();

        Assert.assertEquals(integerQueue.size(), 1);
        Assert.assertEquals(retrievedInt, integerQueue.peek());

        Assert.assertEquals(stringQueue.size(), 1);
        Assert.assertEquals(retrievedString, stringQueue.peek());
    }

    @Test
    public void poll_RetrieveElementFromEmptyQueue_Null() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        Assert.assertEquals(integerQueue.poll(), null);
        Assert.assertEquals(stringQueue.poll(), null);
    }

    @Test
    public void poll_RetrieveElement_True() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        integerQueue.add(42);
        stringQueue.add("Hello");

        Integer retrievedInteger = integerQueue.poll();
        String retrievedString = stringQueue.poll();

        Assert.assertEquals(integerQueue.size(), 0);
        Assert.assertEquals(retrievedInteger, Integer.valueOf(42));
    
        Assert.assertEquals(stringQueue.size(), 0);
        Assert.assertEquals(retrievedString, "Hello");
    }

    @Test 
    public void remove_RemoveElementFromEmptyQueue_EmptyQueueException() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        Throwable integerException = Assert.assertThrows(EmptyQueueException.class, () -> integerQueue.remove());
        Throwable stringException =  Assert.assertThrows(EmptyQueueException.class, () -> stringQueue.remove());

        String expectedMessage = "The queue is empty. No element can be removed.";
        Assert.assertEquals(expectedMessage, integerException.getMessage());
        Assert.assertEquals(expectedMessage, stringException.getMessage());
    }

    @Test
    public void remove_RemoveElementFromQueue_True() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        integerQueue.add(42);
        stringQueue.add("Hello");

        Integer removedInteger = null;
        String removedString = null; 
        try {
            removedInteger = integerQueue.remove();
            removedString = stringQueue.remove();            
        } catch (EmptyQueueException e){
            e.printStackTrace();
        }
        
        Assert.assertEquals(integerQueue.size(), 0);
        Assert.assertEquals(removedInteger, Integer.valueOf(42));

        Assert.assertEquals(stringQueue.size(),0);
        Assert.assertEquals(removedString, "Hello");
    }

    @Test
    public void toString_PrintQueueCorrectly_True() {
        Queue<Integer> integerQueue = new Queue<>();
        Queue<String> stringQueue = new Queue<>();

        Assert.assertEquals(integerQueue.toString(), "The queue is empty.");
        Assert.assertEquals(stringQueue.toString(), "The queue is empty.");

        integerQueue.add(42);
        stringQueue.add("Hello");

        Assert.assertEquals(integerQueue.toString(), "Head: 42");
        Assert.assertEquals(stringQueue.toString(), "Head: Hello");

        integerQueue.add(84);
        stringQueue.add("World");

        Assert.assertEquals(integerQueue.toString(), "Head: 42 -> 84");
        Assert.assertEquals(stringQueue.toString(), "Head: Hello -> World");
    }
}
