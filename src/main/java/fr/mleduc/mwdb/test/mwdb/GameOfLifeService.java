package fr.mleduc.mwdb.test.mwdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 11/03/16.
 */
public class GameOfLifeService {
    public List<LifeOperation> doLife(final CellGrid cellGrid) {
        return cellGrid.nextTime.stream().flatMap(cell -> {
            final ArrayList<LifeOperation> lifeOperations = new ArrayList<>();
            long x = cell.getX();
            long y = cell.getY();
            if (cellGrid.isAlive(x, y)) {
                long cpt = cellGrid.countNeighbourAlive(x, y);
                if (cpt < 2 || cpt > 3) {
                    lifeOperations.add(LifeOperation.deadCell(cell.getX(), cell.getY()));
                }
            } else {
                long cpt = cellGrid.countNeighbourAlive(x, y);
                if (cpt == 3) {
                    lifeOperations.add(LifeOperation.newCell(x, y));
                }
            }
            return lifeOperations.stream();
        }).collect(Collectors.toList());
    }

    public CellGrid proceedAction(CellGrid cellGrid, List<LifeOperation> lifeOperations) {
        final Set<Cell> allCells = cellGrid.getCells();
        List<Cell> allCellsN = allCells.stream()
                .filter(cell -> {
                    final Boolean aBoolean = lifeOperations
                            .stream()
                            .filter(e -> e.type == LifeOperation.LifeOperationType.Dead
                                    && e.x == cell.getX()
                                    && e.y == cell.getY()).findFirst().map(elem -> false).orElse(true);
                    return aBoolean;
                }).collect(Collectors.toList());
        final List<Cell> allCells2 = lifeOperations.stream().filter(e -> e.type == LifeOperation.LifeOperationType.New).map(o -> new Cell(o.x, o.y)).collect(Collectors.toList());
        allCellsN.addAll(allCells2);
        return new CellGrid(allCellsN);
    }
}
