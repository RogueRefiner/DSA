package com.stack;

import static org.junit.Assert.assertThrows;


import org.junit.Assert;
import org.junit.Test;

public class StackTests 
{
    @Test
    public void push_AddElementToStack_CorrectBehaviour() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        intStack.push(84);
        stringStack.push("Hello");

        Assert.assertEquals(intStack.isEmpty(), false);
        Assert.assertEquals(stringStack.isEmpty(), false);
    } 

    @Test
    public void pop_NonEmptyStack_RetrieveCorrectElement() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        intStack.push(42);
        intStack.push(84);
        stringStack.push("Hello");
        stringStack.push("World");

        try {
            Assert.assertEquals(intStack.pop(), Integer.valueOf(84));
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals(stringStack.pop(), "World");
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pop_EmptyStack_EmptyStackException() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        Throwable exception = assertThrows(EmptyStackException.class, () -> intStack.pop());
        Throwable stringException = assertThrows(EmptyStackException.class, () -> stringStack.pop());

        Assert.assertEquals("Stack is empty. Can not pop item from stack.", exception.getMessage());
        Assert.assertEquals("Stack is empty. Can not pop item from stack.", stringException.getMessage());        
    }

    @Test
    public void peek_EmptyStack_EmptyStackException() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        Throwable intException = assertThrows(EmptyStackException.class, () -> intStack.peek());
        Throwable stringException = assertThrows(EmptyStackException.class, () -> stringStack.peek());
        
        Assert.assertEquals("Stack is empty. Can not look at the top object of the stack.", intException.getMessage());
        Assert.assertEquals("Stack is empty. Can not look at the top object of the stack.", stringException.getMessage());
    }

    @Test 
    public void peek_RetrieveCorrectItemWithoutAlteringTheStack_True() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        intStack.push(42);
        stringStack.push("World");

        try {
            Assert.assertEquals(intStack.peek(), Integer.valueOf(42));
            Assert.assertEquals(intStack.isEmpty(), false);
            Assert.assertEquals(intStack.peek(), Integer.valueOf(42));

            Assert.assertEquals(stringStack.peek(), "World");
            Assert.assertEquals(stringStack.isEmpty(), false);
            Assert.assertEquals(stringStack.peek(), "World");
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void isEmpty_EmptyStack_True() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        Assert.assertEquals(intStack.isEmpty(), true);
        Assert.assertEquals(stringStack.isEmpty(), true);
    }

    @Test 
    public void isEmpty_NonEmptyStack_False() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        intStack.push(42);
        stringStack.push("World");

        Assert.assertEquals(intStack.isEmpty(), false);
        Assert.assertEquals(stringStack.isEmpty(), false);
    }

    @Test 
    public void search_EmptyStack_ReturnNotFound() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        Assert.assertEquals(intStack.search(42), -1);
        Assert.assertEquals(stringStack.search("Hello"), -1);
    }

    public void search_ElementNotInStack_ReturnNotFound() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        intStack.push(42);
        intStack.push(84);
        stringStack.push("Hello");
        stringStack.push("World");

        Assert.assertEquals(intStack.search(1337), -1);
        Assert.assertEquals(stringStack.search("!"), -1);
    }

    public void search_ElementInStack_ReturnDistanceToTopOfStack() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();
        
        intStack.push(42);
        intStack.push(84);
        stringStack.push("Hello");
        stringStack.push("World");

        Assert.assertEquals(intStack.search(42), 1);
        Assert.assertEquals(intStack.search(84), 0);
        Assert.assertEquals(stringStack.search("World"), 0);
        Assert.assertEquals(stringStack.search("Hello"), 1);
    }

    @Test
    public void toString_PrintStackCorrectlyToConsole_True() {
        Stack<Integer> intStack = new Stack<Integer>();
        Stack<String> stringStack = new Stack<String>();

        Assert.assertEquals(intStack.toString(), "The Stack is empty.");
        Assert.assertEquals(stringStack.toString(), "The Stack is empty.");

        intStack.push(42);
        intStack.push(84);
        stringStack.push("Hello");
        stringStack.push("World");

        Assert.assertEquals(intStack.toString(), "pos in the stack: 0, value: 84\npos in the stack: 1, value: 42");
        String expectedString = "pos in the stack: 0, value: World\npos in the stack: 1, value: Hello";
        Assert.assertEquals(stringStack.toString(), expectedString);
    }
}
