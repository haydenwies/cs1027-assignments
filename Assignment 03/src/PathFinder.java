/**
 * Path finder object used to traverse a given pyramid of type Map.
 * @author Hayden Wies
 */
public class PathFinder<T> {
    
    private Map pyramidMap;


    public PathFinder(String fileName) {
        try {
            this.pyramidMap = new Map(fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Algorithm that traverses the Map to collect all pieces of treasure while following the rules of exploration.
     * @return Stack containing the chambers traversed during the exploration process.
     */
    public DLStack<Chamber> path() {

        int treasureCount = this.pyramidMap.getNumTreasures();
        int foundTreasure = 0;
        DLStack<Chamber> path = new DLStack<Chamber>();

        path.push(this.pyramidMap.getEntrance());
        path.getTop().getElement().markPushed();

        // While the stack is empty, look for the best chamber and move to it.
        while (!path.isEmpty()) {
            Chamber current = path.peek();
            if (current.isTreasure()) {
                foundTreasure++;
                if (foundTreasure == treasureCount) {
                    // If last treasure is found, leave the loop.
                    break;
                }
            }
            Chamber next = this.bestChamber(current);
            if (next != null) {
                // If available next chamber, move to it.
                path.push(next);
                next.markPushed();
            } else {
                // Otherwise remove current chamber from stack, backtrack and retry loop.
                path.pop();
                current.markPopped();
            }
        }

        return path;

    }


    /**
     * Returns the Map of the current PathFinder object.
     * @return Map of the PathFinder in question.
     */
    public Map getMap() {
        return this.pyramidMap;
    }


    /**
     * Checks to determine if the current chamber is dim.
     * @param currentChamber Chamber representing current location of the explorer.
     * @return Value of whether or not the chamber is dim.
     */
    public boolean isDim(Chamber currentChamber) {
        if (currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted()) {
            // If conditions, loop through all neighbour chambers and check if one is lighted.
            boolean neighbourLighted = false;
            for (int i = 0; i <= 5; i++) {
                Chamber neighbour = currentChamber.getNeighbour(i);
                if (neighbour != null) {
                    if (neighbour.isLighted()) {
                        // If true, adjust marker variable and leave loop.
                        neighbourLighted = true;
                        break;
                    }
                }
            }
            if (neighbourLighted) {
                return true;
            } else {
                return false;
            }
        } else {
            // If conditions not met return false.
            return false;
        }
    }


    /**
     * Determines the best chamber to move to from the current position
     * @param currentChamber Chamber representing current location of the explorer.
     * @return The chamber which is best to move to next (if any).
     */
    public Chamber bestChamber(Chamber currentChamber) {

        final int TREASURE = 3;
        final int LIGHTED = 2;
        final int DIM = 1;
        int bestType = 0;
        // Array to store neighbour values
        int[] adjacentTypes = new int[6];
        

        for (int i = 0; i <= 5; i++) {
            Chamber neighbour = currentChamber.getNeighbour(i);
            int neighbourType = 0;
            // Loop through all neighbour chambers and mark them by what type they are.
            if (neighbour != null) {
                if (!neighbour.isMarked()) {
                    if (neighbour.isTreasure()) {
                        adjacentTypes[i] = TREASURE;
                        neighbourType = TREASURE;
                    } else if (neighbour.isLighted()) {
                        adjacentTypes[i] = LIGHTED;
                        neighbourType = LIGHTED;
                    } else if (this.isDim(neighbour)) {
                        adjacentTypes[i] = DIM;
                        neighbourType = DIM;
                    }
                    // If current chamber is better than any previous chamber, update marker variable.
                    if (bestType < neighbourType) {
                        bestType = neighbourType;
                    }
                }
            }
        }

        int i = 0;
        if (bestType != 0) {
            // After determining which chamber type is best, loop through neighbours again and return the first chamber with the same type.
            while (adjacentTypes[i] != bestType) {
                i++;
            }
            return currentChamber.getNeighbour(i);
        } else {
            // If no best chamber exists return null.
            return null;
        }

    }

}
