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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator class of DrawingChange objects
 * 
 * @author Zhengjia Mao
 *
 */
public class DrawingStackIterator implements Iterator<DrawingChange> {

  /**
   * private fields shared in this class
   */
  private LinkedNode<DrawingChange> node;

  /**
   * Constructor method that passes in the top node of the stack and stores it.
   * 
   * @param top - the node on the top
   */
  public DrawingStackIterator(LinkedNode<DrawingChange> top) {
    node = top;
  }

  /**
   * check whether has the next node(the passed-in node) if the node is null, it means the end
   * 
   */
  @Override
  public boolean hasNext() {
    return node != null;
  }

  /**
   * This method returns the passed-in node if there is one move to the next node
   * 
   * @return the passed-in node
   * @throws NoSuchElementException if there is no more node
   */
  @Override
  public DrawingChange next() {
    // detect the exception of having no more node
    if (!hasNext())
      throw new NoSuchElementException("nore more");
    else {
      DrawingChange returnData = node.getData();
      node = node.getNext();
      return returnData;
    }
  }

}
