package dota.game.utils.location;

import dota.game.board.Cell;
import dota.game.objects.map.Lane;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LanePositionTest extends TestCase {
    private Lane lane;
    private Cell[] laneCells;

    @Before
    public void setUp() {
        laneCells = new Cell[]{
                new Cell(0, 0),
                new Cell(0, 1),
                new Cell(1, 1),
                new Cell(1, 0)
        };
        lane = new Lane(laneCells);
    }

    @Test
    public void testLaneCreation() {
        for (int i = 0; i < laneCells.length; i++)
            assertEquals(laneCells[i], lane.getCell(i));
    }


    @Test
    public void testForwards() {
        LanePosition lanePosition = new LanePosition(lane, LaneDirection.FORWARDS);

        assertEquals(lanePosition.getHeadIndex(), 0);
        assertEquals(lanePosition.getTailIndex(), laneCells.length - 1);

        assertEquals(lanePosition.getCell(), laneCells[0]);
        assertEquals(lanePosition.getNextCell(), laneCells[1]);

        for (int i = 0; i < laneCells.length; i++) {
            assertEquals(lanePosition.getCell(), laneCells[i]);
            assertEquals(lanePosition.progressValue(), i);

            try {
                lanePosition.moveNext();
            } catch (Exception e) {
                assertEquals(i, laneCells.length - 1);
            }
        }
    }


    @Test
    public void testBackwards() throws Exception {
        LanePosition lanePosition = new LanePosition(lane, LaneDirection.BACKWARDS);

        assertEquals(lanePosition.getTailIndex(), 0);
        assertEquals(lanePosition.getHeadIndex(), laneCells.length - 1);

        assertEquals(lanePosition.getCell(), laneCells[laneCells.length - 1]);
        assertEquals(lanePosition.getNextCell(), laneCells[laneCells.length - 2]);

        for (int i = 0; i < laneCells.length; i++) {
            assertEquals(lanePosition.getCell(), laneCells[laneCells.length - 1 - i]);
            assertEquals(lanePosition.progressValue(), i);

            try {
                lanePosition.moveNext();
            } catch (Exception e) {
                assertEquals(i, laneCells.length - 1);
            }
        }
    }

    @After
    public void wrapUp() {
        lane = null;
    }
}
