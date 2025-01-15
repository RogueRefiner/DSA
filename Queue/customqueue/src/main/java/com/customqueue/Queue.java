package com.customqueue;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private List<T> queue;
    
    public Queue() {
        this.queue = new ArrayList<T>();
    }

    public void add(T item){
        this.queue.add(item);
    }

    public T element() throws EmptyQueueException {
        if (this.queue.isEmpty()) {
            throw new EmptyQueueException("The queue is empty. No element can be retrieved.");
        }

        return this.queue.get(0);
    }

    public T peek() {
        if (this.queue.isEmpty()) {
            return null;
        }

        return this.queue.get(0);
    }

    public T poll() {
        if (this.queue.isEmpty()){
            return null;
        }

        return this.queue.remove(0);
    }

    public T remove() throws EmptyQueueException {
        if (this.queue.isEmpty()) {
            throw new EmptyQueueException("The queue is empty. No element can be removed.");
        }

        return this.queue.remove(0);
    }

    public int size() {
        return this.queue.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.queue.isEmpty()) {
            return "The queue is empty.";
        }
        
        sb.append("Head: ");
        for(int i = 0; i < this.queue.size(); i++) {
            if (i == this.queue.size() - 1){
                sb.append(String.format("%s", this.queue.get(i)));
            } else {
                sb.append(String.format("%s -> ", this.queue.get(i)));
            }
        }

        return sb.toString();
    }
}
