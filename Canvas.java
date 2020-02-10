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
 * This class represents an ASCII-art image.
 * 
 * @author Zhengjia Mao
 *
 */
public class Canvas {

  /**
   * private fields shared in this class
   */
  private final int width; // width of the canvas
  private final int height; // height of the canvas
  private char[][] drawingArray; // 2D character array to store the drawing
  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  /**
   * Constructor creates a new canvas which is initially blank (use the default value for char type
   * or you can use spaces)
   * 
   * @param width  of the 2d array
   * @param height of the 2d array
   * @throws IllegalArgumentException with a descriptive error message if width or height is 0 or
   *                                  negative.
   */
  public Canvas(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("unrealistic canvas");
    }
    this.width = width;
    this.height = height;
    undoStack = new DrawingStack();
    redoStack = new DrawingStack();
    drawingArray = new char[height][width];
  }

  /**
   * Draw a character at the given position drawingArray[row][col]
   * 
   * @param row - position parameter
   * @param col - position parameter
   * @param c   - new character object to put in
   * @throws IllegalArgumentException if the drawing position is outside the canvas
   */
  public void draw(int row, int col, char c) {
    // If that position is already marked with a different character, overwrite it.
    if ((row >= height) || (row < 0) || (col >= width) || (col < 0) ) {
      throw new IllegalArgumentException("out of range");
    }
    char prevChar = drawingArray[row][col];
    drawingArray[row][col] = c;
    // After making a new change, add a matching DrawingChange to the undoStack
    // so that we can undo if needed.
    undoStack.push(new DrawingChange(row, col, prevChar, c));
    // After making a new change, the redoStack should be empty (meaning that
    // you should clear the redoStack if it is not already empty).
    while (!redoStack.isEmpty()) {
      redoStack.pop();
    }
  }

  /**
   * Undo the most recent drawing change.
   * 
   * @return true if undo successfully. False otherwise.
   */
  public boolean undo() {
    try {
      DrawingChange history = undoStack.peek();
      // An undone DrawingChange should be popped off the undoStack.
      undoStack.pop();
      // An undone DrawingChange should be added to the redoStack so that
      // we can redo if needed.
      redoStack.push(history);
      // The content of the drawingArray should be updated accordingly to this change.
      drawingArray[history.row][history.col] = history.prevChar;
    } catch (EmptyStackException e) { // detect the exception
      return false;
    }
    return true;
  }

  /**
   * Redo the most recent undone drawing change.
   * 
   * @return return true if redo successfully. False otherwise.
   */
  public boolean redo() {
    try {
      DrawingChange history = redoStack.peek();
      // A redone DrawingChange should be popped off the redoStack.
      redoStack.pop();
      // A redone DrawingChange should be added (back) to the undoStack so that
      // we can undo again if needed.
      undoStack.push(history);
      // The content of the drawingArray should be updated accordingly to this change.
      drawingArray[history.row][history.col] = history.newChar;
    } catch (EmptyStackException e) { // detect the exception
      return false;
    }
    return true;
  }


  /**
   * Return a printable string version of the Canvas.
   * 
   * @return a String contains all the elements organized by rows and columns
   */
  public String toString() {
    /*
     * Format example: [_ is blank. Use System.lineSeparator() to put a newline character between
     * rows] X___X _X_X_ __X__ _X_X_ X___X
     */
    String canvas = "";
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (drawingArray[i][j] != '\0') {
          // fills in the character in the position
          canvas += drawingArray[i][j];
        } else {
          // if empty, fills in _
          canvas += "_";
        }
      }
      // switch lines
      canvas += System.lineSeparator();
    }
    return canvas;
  }

  /**
   * prints the Canvas’s string representation
   */
  public void printDrawing() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (drawingArray[i][j] != '\0') {
          // fills in the character in the position
          System.out.print(drawingArray[i][j]);
        } else {
          // if empty, fills in _
          System.out.print("_");
        }
      }
      // switch lines
      System.out.println();
    }
  }

  /**
   * prints a record of the changes that are stored on the undoStack.
   */
  public void printHistory() {
    // create a new DrawingStackIterator
    Iterator<DrawingChange> stackIterator = undoStack.iterator();
    int i = 0;
    while (stackIterator.hasNext()) {
      DrawingChange item = stackIterator.next();
      System.out.print(undoStack.size() - i); // print the order
      System.out
          .println(". draw '" + item.newChar + "' on " + "(" + item.row + "," + item.col + ")");
      i++;
    }
  }

  /**
   * print out the sample canvas
   * 
   * @param args
   */
  public static void main(String[] args) {

  }
}
