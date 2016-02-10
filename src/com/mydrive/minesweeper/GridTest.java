/**
 * 
 */
package com.mydrive.minesweeper;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author craigtubb
 * 
 */
public class GridTest {

  private Grid newGrid(int size, int[][] bombs) {
    return new Grid(size, bombs);
  }

  /**
   * 
   */
  @Test
  public void testGetGridSquare() {
    final int SIZE = 3;
    Grid grid = newGrid(SIZE, new int[0][0]);

    for (int x = 0; x < SIZE; x++) {
      for (int y = 0; y < SIZE; y++) {
        GridSquare gs = grid.getGridSquare(x, y);
        assertEquals(gs.getX(), x);
        assertEquals(gs.getY(), y);
      }
    }
  }

  /**
   * 
   */
  @Test
  public void testIsIndexWithinGrid() {
    final int SIZE = 3;
    Grid grid = newGrid(SIZE, new int[0][0]);

    assertEquals(grid.isIndexWithinGrid(0), true);
    assertEquals(grid.isIndexWithinGrid(1), true);
    assertEquals(grid.isIndexWithinGrid(2), true);
    assertEquals(grid.isIndexWithinGrid(3), false);
  }

  /**
   * 
   */
  @Test
  public void testGetCell() {
    final int SIZE = 3;
    Grid grid = newGrid(SIZE, new int[][] { { 2, 1 } });

    assertEquals(grid.getCell(0, 0), 0);
    assertEquals(grid.getCell(0, 1), 0);
    assertEquals(grid.getCell(0, 2), 0);
    assertEquals(grid.getCell(1, 0), 1);
    assertEquals(grid.getCell(1, 1), 1);
    assertEquals(grid.getCell(1, 2), 1);
    assertEquals(grid.getCell(2, 0), 1);
    assertEquals(grid.getCell(2, 1), -1);
    assertEquals(grid.getCell(2, 2), 1);
  }
}
