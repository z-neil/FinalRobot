package AStar;

import java.lang.IllegalArgumentException;

public class FieldMap implements TypeMap {

   public byte[][] map;
   private int xSize;
   private int ySize;

   public FieldMap(int xRange, int yRange) {
      map = new byte[xRange][yRange];
      xSize = xRange;
      ySize = yRange;
   }

   public Types getType(int x, int y) {
      return Types.values()[map[x][y]];
   }

   public boolean isStyrofoam(int x, int y) {
      return getType(x,y) == Types.STYROFOAM;
   }

   public boolean isObstacle(int x, int y) {
      return getType(x,y) == Types.OBSTACLE;
   }

   public boolean isEmpty(int x, int y) {
      return getType(x,y) == Types.NONE;
   }

   public void set(int x, int y, Types type) {
      map[x][y] = type.getValue();
   }

   public boolean checkBounds(int x, int y) {
      return !( x > map.length || x < 0 || y > map[0].length || y < 0 );
   }
	/** fill the rectangle [x1, y1]--[x2, y2] with sq
	 @param x1
	 @param y1
	 @param x2
	 @param y2 the recangle (x1, y1)--(x2, y2)
	 @param sq what to set it to */
	public void fill(int x1, int y1, int x2, int y2, Types type) {
		if(x1 > x2 || y1 > y2 || x1 >= xSize || y1 >= ySize || x2 < 0 || y2 < 0) {
         //throw new IllegalArgumentException("Illegal Fill");
      }
		if(x1 < 0)      x1 = 0;
		if(y1 < 0)      y1 = 0;
		if(x2 >= xSize) x2 = xSize - 1;
		if(y2 >= ySize) y2 = ySize - 1;
		for(int y = y1; y <= y2; y++) {
			for(int x = x1; x <= x2; x++) {
				map[y][x] = type.getValue();
			}
		}
	}
}
