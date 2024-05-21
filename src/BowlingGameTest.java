import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Scanner;

public class BowlingGameTest {
    @Test
    public void testPerfectGame() {
        Scanner scanner = new Scanner("10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n10\n");
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        assertEquals(300, BowlingScoringProgram.calculateTotalScore(rolls));
    }

    @Test
    public void testGutterGame() {
        Scanner scanner = new Scanner("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n");
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        assertEquals(0, BowlingScoringProgram.calculateTotalScore(rolls));
    }

    @Test
    public void testAllSpares() {
        Scanner scanner = new Scanner("5\n5\n3\n7\n2\n8\n5\n5\n2\n8\n3\n7\n4\n6\n0\n10\n9\n1\n8\n2\n10\n");
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        assertEquals(146, BowlingScoringProgram.calculateTotalScore(rolls));
    }

    @Test
    public void testExtraRollsInTenthFrame() {
        Scanner scanner = new Scanner("1\n4\n4\n5\n6\n4\n5\n3\n10\n1\n7\n3\n6\n4\n3\n2\n8\n6\n4\n8\n");
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        assertEquals(113, BowlingScoringProgram.calculateTotalScore(rolls));
    }

    @Test
    public void testNoStrikesOrSpares() {
        Scanner scanner = new Scanner("1\n4\n4\n5\n3\n4\n5\n3\n2\n0\n1\n7\n3\n6\n4\n3\n2\n7\n6\n3\n");
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        assertEquals(73, BowlingScoringProgram.calculateTotalScore(rolls));
    }

}