package Homework570;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if (x < 0 || x >= maze.getNCols() || y < 0 || y >= maze.getNRows()) {
            return false;
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        } else {
            maze.recolor(x, y, PATH);
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
                return true;
            } else {
                maze.recolor(x, y, TEMPORARY); // Dead end.
                return false;
            }
        }
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        maze.recolor(PATH, NON_BACKGROUND);
        maze.recolor(TEMPORARY, NON_BACKGROUND);
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        if (result.size() == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        return result;
    }
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        if (x < 0 || x >= maze.getNCols() || y < 0 || y >= maze.getNRows()) {
            return;
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return;
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> cur = new ArrayList<>();
            cur.addAll(trace);
            result.add(cur);
            return;
        } else {
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            if ((x - 1) >= 0 && (x - 1) < maze.getNCols() && y >= 0 && y < maze.getNRows()) {
                if (maze.getColor(x - 1, y).equals(NON_BACKGROUND)) {
                    findMazePathStackBased(x - 1, y, result, trace);
                    trace.pop();
                    maze.recolor(x - 1, y, NON_BACKGROUND);
                }
            }
            if ((x + 1) >= 0 && (x + 1) < maze.getNCols() && y >= 0 && y < maze.getNRows()) {
                if (maze.getColor(x + 1, y).equals(NON_BACKGROUND)) {
                    findMazePathStackBased(x + 1, y, result, trace);
                    trace.pop();
                    maze.recolor(x + 1, y, NON_BACKGROUND);
                }
            }
            if (x >= 0 && x < maze.getNCols() && (y - 1) >= 0 && (y - 1) < maze.getNRows()) {
                if (maze.getColor(x, y - 1).equals(NON_BACKGROUND)) {
                    findMazePathStackBased(x, y - 1, result, trace);
                    trace.pop();
                    maze.recolor(x, y - 1, NON_BACKGROUND);
                }
            }
            if (x >= 0 && x < maze.getNCols() && (y + 1) >= 0 && (y + 1) < maze.getNRows()) {
                if (maze.getColor(x, y + 1).equals(NON_BACKGROUND)) {
                    findMazePathStackBased(x, y + 1, result, trace);
                    trace.pop();
                    maze.recolor(x, y + 1, NON_BACKGROUND);
                }
            }
        }

    }


    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList<PairInt> result = new ArrayList<>();
        ArrayList<ArrayList<PairInt>> allPath = findAllMazePaths(x, y);
        int min = allPath.get(0).size();
        result = allPath.get(0);
        for (int i = 0; i < allPath.size(); ++i) {
            if (allPath.get(i).size() < min) {
                min = allPath.get(i).size();
                result = allPath.get(i);
            }
        }
        return result;
    }


    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/

