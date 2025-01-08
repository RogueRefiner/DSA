package com.queue;

public class Node {
    private int value;
    private Node nextNode;

    public Node(int value){
        this.value = value;
        this.nextNode = null;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        if (this.getNextNode() == null){
            return String.format("Value: %d, NextNode: null", this.getValue());
        } else {
            return String.format("Value: %d, NextNode: %s", this.getValue(), this.getNextNode().getValue());
        }
    }
}
