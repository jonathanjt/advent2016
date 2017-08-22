import java.awt.Point;
import java.util.HashSet;
import java.util.Set;


/**
 *
 */
public class SolutionOne {
	private static String input_path =
		"L5, R1, R4, L5, L4, R3, R1, L1, R4, R5, L1, L3, R4, L2, L4, R2, L4, L1, R3, R1, " +
			"R1, L1, R1, L5, R5, R2, L5, R2, R1, L2, L4, L4, R191, R2, R5, R1, L1, L2, R5, " +
			"L2, L3, R4, L1, L1, R1, R50, L1, R1, R76, R5, R4, R2, L5, L3, L5, R2, R1, L1, " +
			"R2, L3, R4, R2, L1, L1, R4, L1, L1, R185, R1, L5, L4, L5, L3, R2, R3, R1, L5, " +
			"R1, L3, L2, L2, R5, L1, L1, L3, R1, R4, L2, L1, L1, L3, L4, R5, L2, R3, R5, " +
			"R1, L4, R5, L3, R3, R3, R1, R1, R5, R2, L2, R5, L5, L4, R4, R3, R5, R1, L3, " +
			"R1, L2, L2, R3, R4, L1, R4, L1, R4, R3, L1, L4, L1, L5, L2, R2, L1, R1, L5, " +
			"L3, R4, L1, R5, L5, L5, L1, L3, R1, R5, L2, L4, L5, L1, L1, L2, R5, R5, L4, " +
			"R3, L2, L1, L3, L4, L5, L5, L2, R4, R3, L5, R4, R2, R1, L5";

	enum Direction {
		NORTH(0,-1), EAST(1,0), SOUTH(0,1), WEST(-1,0);

		int dx, dy;
		Direction left, right;

		static {
			NORTH.left = WEST;
			NORTH.right = EAST;
			EAST.left = NORTH;
			EAST.right = SOUTH;
			SOUTH.left = EAST;
			SOUTH.right = WEST;
			WEST.left = SOUTH;
			WEST.right = NORTH;
		}
		Direction (int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}

	private static int manhattanDistance( int x2, int y2 ) {
		return Math.abs( 0 - x2) + Math.abs( 0 - y2);
	}


	public static void solve(String[] args) {

		Set<Point> visited = new HashSet<>();
		int x = 0;
		int y = 0;
		visited.add(new Point(x, y));
		Direction current = Direction.NORTH;

		for (String each : input_path.split( ", " ) ) {
			Boolean right = each.startsWith("R");
			int dist = Integer.parseInt( each.substring(1) );

			if (right) {
				current = current.right;
			} else {
				current = current.left;
			}

			for (int i = 0; i < dist; i++) {
				x += current.dx;
				y += current.dy;
				Point p = new Point(x, y);
				if (visited.contains(p)) {
					System.out.println("Duplicate: " + manhattanDistance( x, y));
				} else {
					visited.add(p);
				}
			}
		}

		System.out.println("Distance: " + manhattanDistance( x, y));
	}
}
