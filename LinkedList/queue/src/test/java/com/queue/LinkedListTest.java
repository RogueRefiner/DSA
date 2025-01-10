package com.queue;

import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
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
        linkedList.insertNodeAtStart(new Node(42));
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
        Assert.assertEquals("Index 2 out of range", exception.getMessage());
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
        Assert.assertEquals("The LinkedList is empty. There is no head node.", exception.getMessage());
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

    @Test
    public void deleteValue_EmptyList_InvalidNodeException() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidNodeException.class, () -> linkedList.deleteValue(42));
        Assert.assertEquals("The LinkedList is empty. There is no node to delete.", exception.getMessage());
    }

    @Test 
    public void deleteValue_NonEmptyListDeleteHead_true() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        try {
            Assert.assertEquals(linkedList.deleteValue(42), true);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(linkedList.getSize(), 0);
    }

    @Test 
    public void deleteValue_NonEmptyList_true() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(48));
        try {
            Assert.assertEquals(linkedList.deleteValue(48), true);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(linkedList.getSize(), 3);
    }

    @Test 
    public void deleteValue_NonEmptyList_false() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(48));
        try {
            Assert.assertEquals(linkedList.deleteValue(1337), false);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(linkedList.getSize(), 4);
    }

    @Test
    public void deleteIndex_EmptyLinkedList_InvalidNodeExcpetion() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidNodeException.class, () -> linkedList.deleteIndex(7));
        Assert.assertEquals("The LinkedList is empty. There is no index to delete.", exception.getMessage());
    }

    @Test
    public void deleteIndex_NonEmptyLinkedList_InvalidIndexExcpetion() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Throwable exception = assertThrows(InvalidIndexException.class, () -> linkedList.deleteIndex(7));
        Assert.assertEquals("Index 7 out of range.", exception.getMessage());
    }

    @Test
    public void deleteIndex_NonEmptyLinkedListIndex0_SuccessfulDeletion() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(0));
        try {
            linkedList.deleteIndex(0);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(linkedList.getHeadNode(), null);
    }

    @Test
    public void deleteIndex_NonEmptyLinkedList_SuccessfulDeletion() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(0));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(42));

        try {
            linkedList.deleteIndex(1);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(linkedList.getNodeAtIndex(1).getValue(), 42);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void contains_EmptyLinkedList_false() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.contains(42), false);
    }

    @Test
    public void contains_NonEmptyLinkedListHeadNode_true() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        linkedList.insertNodeAtStart(new Node(84));
        Assert.assertEquals(linkedList.contains(84), true);
    }

    @Test
    public void contains_NonEmptyLinkedListNode_true() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        linkedList.insertNodeAtStart(new Node(84));
        Assert.assertEquals(linkedList.contains(42), true);
    }

    @Test
    public void contains_NonEmptyLinkedList_false() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        linkedList.insertNodeAtStart(new Node(84));
        Assert.assertEquals(linkedList.contains(56), false);
    }


    @Test
    public void getSize_EmptyLinkedList_0() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.getSize(), 0);
    }

    @Test
    public void getSize_NonEmptyLinkedList_2() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        linkedList.insertNodeAtStart(new Node(84));
        Assert.assertEquals(linkedList.getSize(), 2);
    }


    @Test
    public void getFirst_EmptyLinkedList_InvalidNodeExcpetion() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidNodeException.class, () -> linkedList.getFirst());
        Assert.assertEquals("The LinkedList is empty. There is no head node.", exception.getMessage());
    }

    @Test
    public void getFirst_NonLinkedList_HeadLastNode() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));        
        try {
            Assert.assertEquals(linkedList.getFirst().getValue(), 42);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        } 
        
    }

    @Test
    public void getLast_EmptyLinkedList_InvalidNodeExcpetion() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidNodeException.class, () -> linkedList.getLast());
        Assert.assertEquals("The LinkedList is empty. There is no last node.", exception.getMessage());
    }

    @Test
    public void getLast_NonLinkedList_HeadLastNode() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));        
        try {
            Assert.assertEquals(linkedList.getLast().getValue(), 42);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        } 
        
    }

    @Test
    public void getLast_NonLinkedList_FindCorrectLastNode() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));        
        linkedList.insertNodeAtStart(new Node(84)); 
        linkedList.insertNodeAtStart(new Node(1337));
        try {
            Assert.assertEquals(linkedList.getLast().getValue(), 42);
        } catch (InvalidNodeException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void getNodeAtIndex_EmptyLinkedList_InvalidIndexExcpetion() {
        LinkedList linkedList = new LinkedList();
        Throwable exception = assertThrows(InvalidIndexException.class, () -> linkedList.getNodeAtIndex(7));
        Assert.assertEquals("Index 7 is out of range.", exception.getMessage());
    }

    @Test
    public void getNodeAtIndex_NonEmptyLinkedList_InvalidIndexExcpetion() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(24));
        Throwable exception = assertThrows(InvalidIndexException.class, () -> linkedList.getNodeAtIndex(7));
        Assert.assertEquals("Index 7 is out of range.", exception.getMessage());
    }

    @Test 
    public void getNodeAtIndex_NonEmptyLinkedList_SuccessfullyGetNode() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(24));
        try {
            Assert.assertEquals(linkedList.getNodeAtIndex(0).getValue(), 42);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
        try {
            Assert.assertEquals(linkedList.getNodeAtIndex(2).getValue(), 24);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void indexOf_EmptyLinkedList_NotFound() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.lastIndexOf(42), -1);
    }

    @Test
    public void indexOf_NonEmptyLinkedList_NotFound() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.lastIndexOf(84), -1);
    }

    @Test
    public void indexOf_NonEmptyLinkedListSingleNode_CorrectIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.indexOf(42), 0);
    }

    @Test
    public void indexOf_NonEmptyLinkedListMultipleNodes_CorrectIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(84));
        Assert.assertEquals(linkedList.indexOf(84), 1);
    }

    @Test
    public void lastIndexOf_EmptyLinkedList_NotFound() {
        LinkedList linkedList = new LinkedList();
        Assert.assertEquals(linkedList.lastIndexOf(42), -1);
    }

    @Test
    public void lastIndexOf_NonEmptyLinkedList_NotFound() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.lastIndexOf(84), -1);
    }

    @Test
    public void lastIndexOf_NonEmptyLinkedListSingleNode_CorrectIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.lastIndexOf(42), 0);
    }

    @Test
    public void lastIndexOf_NonEmptyLinkedListMultipleNodes_CorrectIndex() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtStart(new Node(42));
        linkedList.insertNodeAtStart(new Node(84));
        linkedList.insertNodeAtStart(new Node(24));
        linkedList.insertNodeAtStart(new Node(42));
        Assert.assertEquals(linkedList.lastIndexOf(42), 3);
    }

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
    public void toList_EmptyLinkedList_ReturnsCorrectList() {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNodeAtEnd(new Node(42));
        linkedList.insertNodeAtEnd(new Node(84));
        linkedList.insertNodeAtEnd(new Node(24));
        linkedList.insertNodeAtEnd(new Node(48));
        Assert.assertEquals(linkedList.toList(), new ArrayList<>(){{
            add(42);
            add(84);
            add(24);
            add(48);
        }});

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
