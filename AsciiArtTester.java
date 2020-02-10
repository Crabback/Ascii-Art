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
import java.util.NoSuchElementException;

/**
 * This is the tester class that verify the methods in the classes.
 * 
 * @author Zhengjia Mao
 *
 */
public class AsciiArtTester {

  /**
   * Check the push and peek methods in the DrawingStack class
   * 
   * @return true if both the methods work correctly
   */
  public static boolean testStackPushPeek() {
    DrawingStack stack = new DrawingStack();
    DrawingChange b = new DrawingChange(1, 1, 'a', 'c');
    boolean check1 = false;
    boolean check2 = false;
    //check the first exception
    try {
      stack.peek();
    } catch (EmptyStackException e) {
      check1 = true;
    }
    //check the second exception
    try {
      stack.push(null);
    } catch (IllegalArgumentException e) {
      check2 = true;
    }
    //regular functions
    stack.push(b);
    if (stack.peek() != b) {
      check1 = false;
    }
    if (stack.size() != 1) {
      check1 = false;
    }
    //if both are true, the result will be true; false otherwise
    return check1 & check2;
  }

  /**
   * Check the pop method in DrawingStack class
   * 
   * @return true if the method works correctly
   */
  public static boolean testStackPop() {
    // create test samples
    DrawingStack stack = new DrawingStack();
    DrawingChange b = new DrawingChange(1, 1, 'a', 'c');
    boolean check = false;
    try {
      stack.pop();
    } catch (EmptyStackException e) {
      check = true;
    }
    stack.push(b);
    if (stack.pop() != b) {
      check = false;
    }
    if (stack.size() != 0) {
      check = false;
    }
    return check;
  }

  /**
   * It should return false if any of its component tests fail, and true if they all succeed.
   * 
   * @return true if all methods work correctly
   */
  public static boolean runAsciiArtTestSuite() {
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    
    // expected output string
    String checkString1 = "___" + System.lineSeparator() + "___" + System.lineSeparator() + "___"
        + System.lineSeparator();
    String checkString2 = "___" + System.lineSeparator() + "___" + System.lineSeparator() + "__" + 'a'
        + System.lineSeparator();
   
    // test[1] create the test sample canvas
    try {
      Canvas badSample = new Canvas(-3, 0);
    }catch(IllegalArgumentException e1) {
      check1 = true;
    }
    Canvas sample = new Canvas(3, 3);
    
    // test[5] show current canvas
    if (!sample.toString().equals(checkString1)) {
      return false;
    }
   
    // test[2] add character to the canvas
    try{
      sample.draw(4, 4, 'a');
    }catch(IllegalArgumentException e2) {
      check2 = true;
    }
    sample.draw(2, 2, 'a');
    
    // test[5] show current canvas
    if (!sample.toString().equals(checkString2)) {
      return false;
    }
    
    // test[3] undo
    check3 = sample.undo();
    if (!sample.toString().equals(checkString1)) {
      return false;
    }
    if(sample.undo()!=false) {
      return false;
    }
    
    // test[4] redo
    check4 = sample.redo();
    if (!sample.toString().equals(checkString2)) {
      return false;
    }
    if(sample.redo()!=false) {
      return false;
    }
    
    //test iterator
    DrawingStack stackSample = new DrawingStack();
    DrawingChange a = new DrawingChange(1, 1, 'c', 'a');
    DrawingChange b = new DrawingChange(1, 1, 'c', 'b');
    stackSample.push(a);
    stackSample.push(b);

    int count = 0;
    for(Object obj: stackSample) {
      stackSample.pop();
      count ++;
    }
    if(count!=2) {
      return false;
    }
    if(!stackSample.isEmpty()) {
      return false;
    }
    
    return check1 & check2 & check3 & check4;
  }

  /**
   * Print out the test results
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(testStackPushPeek());
    System.out.println(testStackPop());
    System.out.println(runAsciiArtTestSuite());
    
    Canvas sample = new Canvas(3, 3);
    sample.draw(2, 2, 'a');
    sample.draw(2, 1, 'b');
    System.out.println(sample);
    // test[6] print history
    sample.printHistory();
    sample.undo();
    System.out.println(sample);
    sample.printHistory();
    System.out.println(sample.undo());
    sample.printHistory();
    System.out.println(sample.undo());
  }

}
