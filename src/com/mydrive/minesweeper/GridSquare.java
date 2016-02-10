/**
 * 
 */
package com.mydrive.minesweeper;

/**
 * @author craigtubb
 * 
 */
public class GridSquare {

  private int     mX;
  private int     mY;
  private int     mBombCount;
  private boolean mHasBomb;

  GridSquare(int x, int y) {
    mX = x;
    mY = y;
    mBombCount = 0;
  }

  int getX() {
    return mX;
  }

  int getY() {
    return mY;
  }

  int getBombCount() {
    return mBombCount;
  }

  void setBombCount(int bombCount) {
    mBombCount = bombCount;
  }

  boolean getHasBomb() {
    return mHasBomb;
  }

  /**
   * 
   */
  void setBomb() {
    mHasBomb = true;
    setBombCount(-1);
  }
}
