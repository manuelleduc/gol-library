package fr.mleduc.mwdb.test.mwdb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mleduc on 16/06/16.
 */
public class Main1 {
    public static void main(String[] args) {
        GameOfLifeService gameOfLifeService = new GameOfLifeService();

        final ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0));
        cells.add(new Cell(0, 1));
        cells.add(new Cell(0, 2));
        cells.add(new Cell(1, 0));
        cells.add(new Cell(1, 2));
        cells.add(new Cell(2, 0));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        CellGrid cellGrid = new CellGrid(cells);
        for(int i=0; i<10; i++) {
            System.out.println(cellGrid);
            cellGrid = all(gameOfLifeService, cellGrid);
        }
    }

    private static CellGrid all(GameOfLifeService gameOfLifeService, CellGrid res) {
        final List<LifeOperation> lifeOperations = gameOfLifeService.doLife(res);
        return gameOfLifeService.proceedAction(res, lifeOperations);
    }
}
