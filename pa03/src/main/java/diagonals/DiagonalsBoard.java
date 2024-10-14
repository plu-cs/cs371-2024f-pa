package diagonals;

import java.util.*;

public class DiagonalsBoard {

    /**
     * The graph representation of the game board.  This is stored as an
     * adjacency list.  The key is the corner number, and the value is the
     * set of connected vertices.
     */
    private final Map<Integer, Set<Integer>> graph;

    /**
     * The requirements on some of the vertices.  The key is the corner number,
     * and the value is the required number of edges.  If a vertex is not found
     * in the Map, it has no requirement.
     */
    private final Map<Integer, Integer> requirements;

    /** The size of the game board */
    private final int size;

    /**
     * Create a board using the provided data.
     * @param data the data for the board.
     */
    public DiagonalsBoard(String data) {
        Scanner scan = new Scanner(data);

        size = scan.nextInt();
        char[][] grid = new char[size+1][size+1];
        for( int i = 0; i < size+1; i++ ) {
            grid[i] = scan.next().toCharArray();
        }

        int n = size + 1;
        graph = new HashMap<>();
        requirements = new HashMap<>();
        for( int row = 0; row < n; row++ ) {
            for( int col = 0; col < n; col++ ) {
                int location = row * n + col;
                graph.put(location, new HashSet<>());

                char c = grid[row][col];
                if( Character.isDigit(c) ) {
                    requirements.put(location, c - '0');
                }
            }
        }
    }

    /**
     * @return the size of this game board - the number of cells in each dimension.
     */
    public int getSize() { return size; }

    /**
     * Sets a diagonal edge for a cell on the game board.  If there exists a
     * diagonal in the opposite direction, that edge is removed. 
     *
     * @param row the row in cell coordinates
     * @param col the column in cell coordinates
     * @param direction the diagonal direction - one of the following:
     *                  '/' for lower left to upper right
     *                  '\' for upper left to lower right
     *                  ' ' clears both diagonals
     */
    public void setDiagonal(int row, int col, char direction) {
        if( direction != '/' && direction != '\\' && direction != ' ' )
            throw new IllegalArgumentException("Invalid direction: '" + direction + "'");

        int n = size + 1;
        int ul = row * n + col;
        int ur = row * n + col + 1;
        int ll = (row + 1) * n + col;
        int lr = (row + 1) * n + col + 1;
        if( direction == ' ') {
            graph.get(ul).remove(lr);
            graph.get(lr).remove(ul);
            graph.get(ll).remove(ur);
            graph.get(ur).remove(ll);
        } else if( direction == '/' ) {
            graph.get(ul).remove(lr);
            graph.get(lr).remove(ul);
            graph.get(ll).add(ur);
            graph.get(ur).add(ll);
        } else {
            graph.get(ul).add(lr);
            graph.get(lr).add(ul);
            graph.get(ll).remove(ur);
            graph.get(ur).remove(ll);
        }
    }

    /**
     * Returns whether a cell does not have any diagonal.
     *
     * @param row the row in cell coordinates
     * @param col the column in cell coordinates
     * @return true if there is no diagonal in this cell.
     */
    public boolean cellIsEmpty(int row, int col) {
        int n = size + 1;
        int ul = row * n + col;
        int ur = row * n + col + 1;
        int ll = (row + 1) * n + col;
        int lr = (row + 1) * n + col + 1;
        return ( ! graph.get(ul).contains(lr) &&
            ! graph.get(lr).contains(ul) &&
            ! graph.get(ll).contains(ur) &&
            ! graph.get(ur).contains(ll) );
    }

    /**
     * Returns whether all neighboring cells to a corner have a diagonal.
     *
     * @param corner the corner number
     * @return true if all cells that share this corner have a diagonal
     */
    public boolean cornerIsComplete( int corner ) {
        int cRow = corner / (size + 1);
        int cCol = corner % (size + 1);
        if ( cRow < size && cCol < size && cellIsEmpty(cRow, cCol) ) return false;
        if ( cRow < size && cCol > 0    && cellIsEmpty(cRow,cCol - 1)) return false;
        if ( cRow > 0    && cCol < size && cellIsEmpty(cRow - 1, cCol)) return false;
        if ( cRow > 0    && cCol > 0    && cellIsEmpty(cRow - 1, cCol - 1)  ) return false;
        return true;
    }

    /**
     * Returns whether the board meets all requirements at this point.
     * This checks both the corner edge constraints and the acyclic
     * constraint.  When checking corner edge constraints, this method will
     * only consider corners that are complete, so that it can be used during
     * the solving process to determine if the in progress solution is correct
     * so far.
     *
     * @return true if the board meets all edge constraints, ignoring any edge constraints
     * for corners that are not complete, and the corresponding graph is acyclic.
     */
    public boolean meetsRequirements() {

        // TODO: Implement this method
        // You can make any private helper methods that you may need.

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = size + 1;
        for( int row = 0; row < size; row ++ ) {
            for( int col = 0; col < size; col++ ) {
                int ul = row * n + col;
                int ur = row * n + col + 1;
                int ll = (row + 1) * n + col;
                int lr = (row + 1) * n + col + 1;

                if( graph.get(ul).contains(lr) ) sb.append('\\');
                else if( graph.get(ll).contains(ur) ) sb.append('/');
                else sb.append('_');
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
