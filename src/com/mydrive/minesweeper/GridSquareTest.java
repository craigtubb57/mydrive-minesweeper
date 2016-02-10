/**
 * 
 */
package com.mydrive.minesweeper;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author craigtubb
 * 
 */
public class GridSquareTest {

  private GridSquare newGridSquare() {
    return new GridSquare(0, 0);
  }

  @Test
  public void testSetBombCount() {
    GridSquare gs = newGridSquare();

    assertEquals(gs.getBombCount(), 0);

    gs.setBombCount(4);
    assertEquals(gs.getBombCount(), 4);
  }

  @Test
  public void testSetBomb() {
    GridSquare gs = newGridSquare();

    assertFalse(gs.getHasBomb());

    gs.setBomb();
    assertTrue(gs.getHasBomb());
    assertEquals(gs.getBombCount(), -1);
  }
}
