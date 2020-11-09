package oit.is.z1631.kaizi.janken.model;

public class Janken {
  public static final int Gu = 0;
  public static final int Choki = 1;
  public static final int Pa = 2;

  public static final int win = 2;
  public static final int lose = 1;
  public static final int draw = 0;

  public static int playjanken(int myhand) {
    int cpuhand = 0;
    int result = (myhand - cpuhand + 3) % 3;
    return result;
  }
}
