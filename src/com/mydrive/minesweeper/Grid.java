/**
 * 
 */
package com.mydrive.minesweeper;

import java.util.HashMap;

/**
 * @author craigtubb
 * 
 */
public class Grid {

  private int                         mSize;
  private int[][]                     mBombs;

  private HashMap<String, GridSquare> mGridSquares;

  Grid(int size, int[][] bombs) {
    mSize = size;
    mBombs = bombs;

    mGridSquares = new HashMap<String, GridSquare>();

    placeBombs(mGridSquares, mBombs);
  }

  /**
   * ** ANSWER TO THE MYDRIVE TEST **
   * 
   * Gets the bomb count in the cell specified by the given coordinates (from top-left)
   * 
   * (-1 denotes a bomb)
   * 
   * @param x
   *          zero-indexed horizontal coordinate from top left
   * @param y
   *          zero-indexed vertical coordinate from top left
   * @return Number of bombs in the cell
   */
  public int getCell(int x, int y) {
    int bombCount = 0;
    String key = x + "," + y;
    GridSquare gridSquare = mGridSquares.get(key);
    if (gridSquare != null) {
      bombCount = gridSquare.getBombCount();
    }
    return bombCount;
  }

  /**
   * Get the GridSquare instance at the specified coordinates (from top-left)
   * 
   * @param x
   *          zero-indexed horizontal coordinate from top left
   * @param y
   *          zero-indexed vertical coordinate from top left
   * @return The GridSquare instance at the specified coordinates
   * @throws IndexOutOfBoundsException
   *           if the specified coordinate lies outside the grid
   */
  GridSquare getGridSquare(int x, int y) throws IndexOutOfBoundsException {
    GridSquare gridSquare = null;
    if (!isIndexWithinGrid(x) || !isIndexWithinGrid(y)) {
      throw new IndexOutOfBoundsException("Specified grid square is outside the grid (" + x + ", " + y + ")");
    }
    String key = x + "," + y;
    gridSquare = mGridSquares.get(key);
    if (gridSquare == null) {
      gridSquare = new GridSquare(x, y);
      mGridSquares.put(key, gridSquare);
    }
    return gridSquare;
  }

  /**
   * Internal method called only by the Grid constructor to place bombs in the grid
   * 
   * @param gridSquares
   *          HashMap of GridSquare instances, indexes by a String key in the form: "x,y"
   * @param bombs
   *          The bombs to place as an array in the form: { {x, y}, {x, y} }
   * @return The HashMap of GridSquare instances, now populated with bombs
   * @throws IndexOutOfBoundsException
   *           if the specified coordinate lies outside the grid
   */
  private HashMap<String, GridSquare> placeBombs(HashMap<String, GridSquare> gridSquares, int[][] bombs) throws IndexOutOfBoundsException {
    for (int i = 0; i < bombs.length; i++) {
      int x = bombs[i][0];
      int y = bombs[i][1];
      GridSquare bombSquare = getGridSquare(x, y);
      bombSquare.setBomb();

      for (int xx = x - 1; xx <= x + 1 && isIndexWithinGrid(xx); xx++) {
        for (int yy = y - 1; yy <= y + 1 && isIndexWithinGrid(yy); yy++) {
          GridSquare borderSquare = getGridSquare(xx, yy);
          if (!borderSquare.getHasBomb()) {
            borderSquare.setBombCount(borderSquare.getBombCount() + 1);
          }
        }
      }
    }
    return gridSquares;
  }

  /**
   * Checks if the specified coordinate component lies outside the grid
   * 
   * @param index
   *          The coordinate component (x or y)
   * @return true if the specified coordinate component lies in the grid, otherwise false
   */
  boolean isIndexWithinGrid(int index) {
    return index >= 0 && index < mSize;
  }
}