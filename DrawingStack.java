//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 Ascii Art
// Files: DrawingChange.java, DrawingStack.java, DrawingStackIterator.java, AsciiArtTester.java,
//////////////////// Canvas.java
// Course: CS 300
//
// Author: Zhengjia Mao
// Email: zmao27@wisc.edu
// Lecturer's Name: Gary DAHL
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _YES__ Write-up states that pair programming is allowed for this assignment.
// _YES__ We have both read and understand the course Pair Programming Policy.
// _YES__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * This class stacks the DrawingChange objects as LinkedNodes
 * 
 * @author Zhengjia Mao
 *
 */
public class DrawingStack implements Iterable<DrawingChange>, StackADT<DrawingChange> {

  /**
   * private fields shared in this class
   */
  private LinkedNode<DrawingChange> top;
  private int size;

  /**
   * Constructor method that initializes the top stack and the size.
   */
  public DrawingStack() {
    top = null;
    size = 0;
  }

  /**
   * Iterator method
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(top);
  }

  /**
   * Add an element to this stack
   * 
   * @param element an element to be added
   * @throws java.util.IllegalArgumentException with a descriptive error message if the input
   *         element is null
   */
  @Override
  public void push(DrawingChange element) {
    // when the element is null, throws a IllegalArgumentException
    if (element == null) {
      throw new IllegalArgumentException("null element");
    } else {
      // store the element as a LinkedNode<DrawingChange>
      LinkedNode<DrawingChange> elementNode = new LinkedNode<DrawingChange>(element);
      if (top == null) { // if empty, set it as top
        top = elementNode;
        size++;
      } else {
        elementNode.setNext(top);
        top = elementNode;
        size++;
      }
    }
  }

  /**
   * Remove the element on the top of this stack and return it
   * 
   * @return the element removed from the top of the stack
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange pop() {
    // when stack is empty, throws a EmptyStackException
    if (top == null) {
      throw new EmptyStackException();
    } else {
      // save the top element data for return
      DrawingChange returnElement = top.getData();
      if (top.getNext() == null) { // if only one element left, empty it
        top = null;
        size = 0;
        return returnElement;
      } else {
        top = top.getNext();
        size--;
        return returnElement;
      }
    }
  }

  /**
   * Get the element on the top of this stack
   * 
   * @return the element on the stack top
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange peek() {
    if (isEmpty()) {
      throw new EmptyStackException();
    } else {
      return top.getData();
    }
  }

  /**
   * Check whether this stack is empty or not
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * Get the number of elements in this stack
   * 
   * @return the size of the stack
   */
  @Override
  public int size() {
    return size;
  }


}
