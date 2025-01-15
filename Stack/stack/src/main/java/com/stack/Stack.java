package com.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> stack;

    public Stack() {
        this.stack = new ArrayList<T>();
    }

    public void push(T item){
        stack.add(item);
    }

    public T pop() throws EmptyStackException {
        if (this.stack.isEmpty()) {
            throw new EmptyStackException("Stack is empty. Can not pop item from stack.");
        }
        return stack.remove(this.stack.size() - 1);
    }

    public T peek() throws EmptyStackException {
        if (this.stack.isEmpty()) {
            throw new EmptyStackException("Stack is empty. Can not look at the top object of the stack.");
        }
        return this.stack.get(this.stack.size() - 1);
    }

    public boolean isEmpty() {
        return this.stack.size() == 0;
    } 

    public int search(T itemToSearch) {
        int pos = -1;
        
        if (this.stack.isEmpty()) {
            return pos;
        }

        for(int i = this.stack.size() - 1; i >= 0; i--){
            if(this.stack.get(i).equals(itemToSearch)) {
                return this.stack.size() - 1 - i;
            }
        }
        return pos;
    }

    @Override
    public String toString() {
        if(this.stack.size() == 0){
            return "The Stack is empty.";
        }
        
        StringBuilder sb = new StringBuilder();
        int pos = 0;
        for(int i = this.stack.size() - 1; i >= 0; i--){
            if(i > 0) {
                sb.append(String.format("pos in the stack: %d, value: %s\n", pos, this.stack.get(i)));
            } else {
                sb.append(String.format("pos in the stack: %d, value: %s", pos, this.stack.get(i)));
            }
            pos += 1;
        }
        return sb.toString();
    }

}
