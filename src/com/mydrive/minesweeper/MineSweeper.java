/**
 * 
 */
package com.mydrive.minesweeper;

import java.util.*;

/**
 * @author craigtubb
 * 
 */
public class MineSweeper {

  final static int    MIN_GRID_SIZE    = 5;
  final static int    MAX_GRID_SIZE    = 20;
  final static double MAX_BOMB_PERCENT = 0.5;

  /**
   * Use this to see a visual representation of MineSweeper
   * 
   * - grid Size is generated randomly
   * 
   * - Bombs are generated randomly
   * 
   * - Minimum and Maximum grid Size is specified above
   * 
   * - Maximum Bomb distribution (ratio of bombs to area) is specified above
   * 
   * @param args
   */
  public static void main(String[] args) {
    Random rand = new Random();

    final int gridSize = rand.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE) + MIN_GRID_SIZE;
    final int area = gridSize * gridSize;

    int bombsOnGrid = rand.nextInt(area);
    bombsOnGrid = Math.min(bombsOnGrid, (int) (area * MAX_BOMB_PERCENT));

    /** what is the bomb distribution? (ratio of bombs to area) **/
    double bombPercent = (double) bombsOnGrid / (double) area;
    bombPercent = Math.round(bombPercent * 100);

    /** When populated, this array will look like: { { 2, 2 }, { 2, 3 } }; **/
    final int[][] bombs = new int[bombsOnGrid][2];

    /** populate the bomb array, making sure not to create duplicates **/
    final HashSet<String> uniqueBombs = new HashSet<String>();
    for (int i = 0; i < bombsOnGrid; i++) {
      int x = -1;
      int y = -1;
      String key = x + "," + y;
      while (x == -1 || y == -1 || uniqueBombs.contains(key)) {
        x = rand.nextInt(gridSize);
        y = rand.nextInt(gridSize);
        key = x + "," + y;
      }
      bombs[i][0] = x;
      bombs[i][1] = y;
      uniqueBombs.add(key);
    }

    /** create the grid **/
    Grid grid = new Grid(gridSize, bombs);

    /** print a visual of the grid to standard out **/
    StringBuffer separBuffer = new StringBuffer(" ");
    for (int i = 0; i < gridSize; i++) {
      separBuffer.append("--- ");
    }
    System.out.println(separBuffer.toString());

    for (int y = 0; y < gridSize; y++) {
      StringBuffer rowBuffer = new StringBuffer("|");
      for (int x = 0; x < gridSize; x++) {

        /** the getCell method used here is the answer to the MyDrive test **/
        int bombCount = grid.getCell(x, y);

        String square = " " + bombCount + " |";
        if (bombCount == -1) {
          square = " X |";
        } else if (bombCount == 0) {
          square = "   |";
        }
        rowBuffer.append(square);
      }
      System.out.println(rowBuffer.toString());
      System.out.println(separBuffer.toString());
    }

    /** print some stats **/
    System.out.println("gridSize = " + gridSize);
    System.out.println("area = " + area);
    System.out.println("bombsOnGrid = " + bombsOnGrid);
    System.out.println("bombPercent = " + (int) bombPercent + "%");
  }
}
