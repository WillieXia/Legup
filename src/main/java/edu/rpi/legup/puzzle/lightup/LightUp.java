package edu.rpi.legup.puzzle.lightup;

import edu.rpi.legup.model.Puzzle;
import edu.rpi.legup.model.RegisterPuzzle;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.ContradictionRule;
import edu.rpi.legup.model.tree.TreeTransition;
import edu.rpi.legup.puzzle.lightup.rules.*;

@RegisterPuzzle
public class LightUp extends Puzzle {

    public LightUp() {
        super();
        name = "ShortTruthTable";

        importer = new LightUpImporter(this);
        exporter = new LightUpExporter(this);

        factory = new LightUpCellFactory();
    }

    /**
     * Initializes the game board. Called by the invoker of the class
     */
    @Override
    public void initializeView() {
        boardView = new LightUpView((LightUpBoard) currentBoard);
    }

    /**
     * Generates a random edu.rpi.legup.puzzle based on the difficulty
     *
     * @param difficulty level of difficulty (1-10)
     * @return board of the random edu.rpi.legup.puzzle
     */
    @Override
    public Board generatePuzzle(int difficulty) {
        return null;
    }

    /**
     * Determines if the current board is a valid state
     *
     * @param board board to check for validity
     * @return true if board is valid, false otherwise
     */
    @Override
    public boolean isBoardComplete(Board board) {
        LightUpBoard lightUpBoard = (LightUpBoard) board;
        lightUpBoard.fillWithLight();

        for (ContradictionRule rule : contradictionRules) {
            if (rule.checkContradiction(lightUpBoard) == null) {
                return false;
            }
        }
        for (PuzzleElement data : lightUpBoard.getPuzzleElements()) {
            LightUpCell cell = (LightUpCell) data;
            if ((cell.getType() == LightUpCellType.UNKNOWN || cell.getType() == LightUpCellType.EMPTY) && !cell.isLite()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Callback for when the board puzzleElement changes
     *
     * @param board the board that has changed
     */
    @Override
    public void onBoardChange(Board board) {

    }
}
