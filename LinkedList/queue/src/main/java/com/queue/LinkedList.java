package com.queue;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    public Node head;

    public LinkedList(){
    }


    public Node getHeadNode() {
        return this.head;
    }

    public void insertNodeAtEnd(Node newNode) {
        if (this.checkIsEmpty()) {
            this.head = newNode;
            return;
        }

        Node currentNode = this.head;
        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }

        currentNode.setNextNode(newNode);
    }

    public void insertNodeAtStart(Node newNode) {
        if (this.checkIsEmpty()) {
            this.head = newNode;
            return;
        }

        Node currentHead = this.head;
        this.head = newNode;
        this.head.setNextNode(currentHead);
    }

    public void insertNode(int index, Node newNode) throws InvalidIndexException {
        if (this.getSize() < index) {
            throw new InvalidIndexException(String.format("Index %d out of range", index));
        }
        
        if (index == 0) {
            this.insertNodeAtStart(newNode);
            return;
        }

        Node currentNode = this.head;
        for(int i = 0; i <= index; i++) {
            if (i == index-1) {
                Node oldNextNode = currentNode.getNextNode();
                currentNode.setNextNode(newNode);
                currentNode.getNextNode().setNextNode(oldNextNode);
                return;
            } else if (currentNode.getNextNode() != null && i < index) {
                currentNode = currentNode.getNextNode();
            }
        }
    }

    public void deleteHead() throws InvalidNodeException{
        if (this.checkIsEmpty()) {
            throw new InvalidNodeException("The LinkedList is empty. There is no head node.");
        } 

        if (this.head.getNextNode() != null) {
            this.head = this.head.getNextNode();
        } else {
            this.head = null;
        }
    }

    public boolean deleteValue(int valueToCheck) throws InvalidNodeException {
        if (this.checkIsEmpty()) {
            throw new InvalidNodeException("The LinkedList is empty. There is no node to delete");
        }

        Node currentNode = this.head;
        if (currentNode.getValue() == valueToCheck){
            this.deleteHead();
            return true;
        }

        while (currentNode.getNextNode() != null) {
            if (currentNode.getNextNode().getValue() == valueToCheck){
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    public void deleteIndex(int index) throws InvalidIndexException, InvalidNodeException {
        if (this.checkIsEmpty()) {
            throw new InvalidNodeException("The LinkedList is empty. There is no index to delete");
        } else if (index >= this.getSize()) {
            throw new InvalidIndexException(String.format("Index %d out of range", index));
        }
        
        if (index == 0){
            this.deleteHead();
            return;
        }

        Node currentNode = this.head;
        for (int i = 0; i <= index; i++){
            if (i == index-1){
                currentNode.setNextNode(currentNode.getNextNode().getNextNode());
                return;
            }
            currentNode = currentNode.getNextNode();
        }
    }

    public boolean contains(int valueToCheck) {
        if (this.checkIsEmpty()) {
            return false;
        }
        Node currentNode = this.head;
        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            if (currentNode.getValue() == valueToCheck) {
                return true;
            }
        } 
        return false;
    }

    public int getSize() {
        if (this.checkIsEmpty()) {
            return 0;
        }

        int size = 1;
        Node currentNode = this.head;
        while(currentNode.getNextNode() != null) {
            size += 1;
            currentNode = currentNode.getNextNode();
        }
        return size;
    }

    public Node getFirst() throws InvalidNodeException {
        if (this.checkIsEmpty()) {
            throw new InvalidNodeException(String.format("The LinkedList is empty. There is no head node."));
        } else {
            return this.head;
        }
    }

    public Node getLast() throws InvalidNodeException {
        if (this.checkIsEmpty()) {
            throw new InvalidNodeException("The LinkedList is empty. There is no last node");
        } else if (this.getSize() == 1){
            return this.head;
        } else {
            Node currentNode = this.head;
            while(currentNode.getNextNode() != null){
                currentNode = currentNode.getNextNode();
            }
            return currentNode;
        }
    }

    public Node getNodeAtIndex(int index) throws InvalidIndexException {
        if (this.checkIsEmpty() || index >= this.getSize() ) {
            throw new InvalidIndexException(String.format("Index %d is out of range", index));
        }
        Node currentNode = this.head;
        for (int i = 0; i <= index; i++){
            if (i == index){
                return currentNode;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }
        return null;
    }

    public int indexOf(int valueToCheck) {
        if (this.checkIsEmpty()) {
            return -1;
        }
        int index = 0;
        Node currentNode = this.head;

        if (this.head.getValue() == valueToCheck) {
            return index;
        }

        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            index += 1;
            if (currentNode.getValue() == valueToCheck) {
                return index;
            }
        }
        return -1;
    }

    public int lastIndexOf(int valueToCheck) {
        if (this.checkIsEmpty()) {
            return -1;
        }

        int index = 0;
        int lastValidIndex = -1;
        Node currentNode = this.head;

        if (this.head.getValue() == valueToCheck) {
            lastValidIndex = 0;
        }        

        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            index += 1;
            if (currentNode.getValue() == valueToCheck) {
                lastValidIndex = index;
            }
        }

        return lastValidIndex;
    }

    public List<Integer> toList() {
        ArrayList<Integer> returnList = new ArrayList<>();
        if (this.checkIsEmpty()) {
            return returnList;
        }
        
        Node currentNode = this.head;
        returnList.add(currentNode.getValue());

        while(currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            returnList.add(currentNode.getValue());
        }

        return returnList;        
    }

    protected boolean checkIsEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.checkIsEmpty()) {
            return "LinkedList is empty";
        }

        StringBuilder sb = new StringBuilder();
        Node currentNode = this.head;
        if (this.getSize() == 1){
            sb.append(String.format("Value: %d", currentNode.getValue()));
        } else {
            sb.append(String.format("Value: %d -> ", currentNode.getValue()));
        }

        while(currentNode.getNextNode() != null){
            currentNode = currentNode.getNextNode();
            if (currentNode.getNextNode() == null) {
                sb.append(String.format("Value: %d", currentNode.getValue()));
            } else {
                sb.append(String.format("Value: %d -> ", currentNode.getValue()));
            }
        }
        return sb.toString();
    }
}
