package com.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {
    @Test
    public void insertNodeAtEnd_EmptyLinkedList_HeadNodeValue42() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        Assert.assertEquals(linkedList.getHeadNode().getValue(), 42);
    }

    @Test
    public void insertNodeAtEnd_NonEmptyLinkedList_SecondNodeValue42() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(1337));
        linkedList.insertNodeAtEnd(new Node(42));

        Assert.assertEquals(linkedList.getHeadNode().getNextNode().getValue(), 42);
    }

    public void insertNodeAtStart_EmptyLinkedList_HeadNodeValue42() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        Assert.assertEquals(linkedList.getHeadNode().getValue(), 42);
    }

    @Test
    public void insertNodeAtStart_NonEmptyLinkedList_HeadNodeValue1337() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtStart(new Node(1337));

        Assert.assertEquals(linkedList.getHeadNode().getValue(), 1337);
    }

    @Test
    public void insertNode_InvalidIndex_ThrowInvalidIndexEception() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidIndexException.class, () -> linkedList.insertNode(2, new Node(42)));
        assertEquals("Index 2 out of range", exception.getMessage());
    }

    @Test
    public void insertNode_NonEmptyLinkedList_NodeAtHeadValue1337() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(86));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(68));
        try {
            linkedList.insertNode(0, new Node(1337));
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(linkedList.getHeadNode().getValue(), 1337);
    }

    @Test
    public void insertNode_NonEmptyLinkedList_NodeAtIndex2Value1337() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(86));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(68));
        try {
            linkedList.insertNode(2, new Node(1337));
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertEquals(linkedList.getNodeAtIndex(2).getValue(), 1337);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteHead_InvalidNode_ThrowInvalidNodeEception() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidNodeException.class, () -> linkedList.deleteHead());
        assertEquals("The LinkedList is empty. There is no head node.", exception.getMessage());
    }

    @Test
    public void deleteHead_NonEmptyLinkedList_HeadNodeValue86() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(86));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(68));
        try {
            linkedList.deleteHead();
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(linkedList.getHeadNode().getValue(), 86);
    }

    // TODO: deleteValue
    // TODO: deleteIndex
    // TODO: contains
    // TODO: getSize
    // TODO: getFirst
    // TODO: getLast
    // TODO: getNodeAtIndex
    // TODO: indexOf
    // TODO: lastIndexOf
    

    @Test
    public void toList_EmptyLinkedList_ReturnsList() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.toList() instanceof List<Integer>, true);
    }

    @Test
    public void toList_NonEmptyLinkedList_ReturnsList() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        Assert.assertEquals(linkedList.toList() instanceof List<Integer>, true);
    }


    @Test
    public void checkIsEmpty_EmptyLinkedList_True() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.checkIsEmpty(), true);
    }

    @Test
    public void checkIsEmpty_NonEmptyLinkedList_False() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.checkIsEmpty(), false);
    }

}
