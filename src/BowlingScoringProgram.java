import java.util.Scanner;

public class BowlingScoringProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] rolls = new int[10][3];
        BowlingScoringProgram bowlingScoringProgram = new BowlingScoringProgram();
        bowlingScoringProgram.playGame(scanner, rolls);
        int totalScore = calculateTotalScore(rolls);
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
    public void playGame(Scanner scanner, int[][] rolls) {
        for (int frame = 0; frame < 10; frame++) {
            System.out.println("Frame " + (frame + 1));
            int firstRoll = getRoll(scanner, "first");
            rolls[frame][0] = firstRoll;

            if (firstRoll < 10) {
                int secondRoll = getRoll(scanner, "second");
                rolls[frame][1] = secondRoll;
            }

            if (isExtraRoll(frame, firstRoll, rolls)) {
                if (firstRoll == 10) {
                    int secondRoll = getRoll(scanner, "second");
                    rolls[frame][1] = secondRoll;
                }
                int thirdRoll = getRoll(scanner, "third");
                rolls[frame][2] = thirdRoll;
            }

            printFrameStatus(firstRoll, rolls, frame);
            System.out.println();
        }
    }

    private static int getRoll(Scanner scanner, String rollType) {
        System.out.print("Enter number of pins knocked down on " + rollType + " roll: ");
        return scanner.nextInt();
    }

    private static boolean isExtraRoll(int frame, int firstRoll, int[][] rolls) {
        return frame == 9 && (firstRoll == 10 || rolls[frame][0] + rolls[frame][1] == 10);
    }

    private static void printFrameStatus(int firstRoll, int[][] rolls, int frame) {
        if (firstRoll == 10) {
            System.out.println("STRIKE (X)");
        } else if (rolls[frame][0] + rolls[frame][1] == 10) {
            System.out.println("SPARE (/)");
        }
    }

    public static int calculateTotalScore(int[][] rolls) {
        int totalScore = 0;

        for (int frame = 0; frame < 10; frame++) {
            int frameScore = rolls[frame][0] + rolls[frame][1] + rolls[frame][2];
            totalScore += frameScore;

            if (isStrike(frame, rolls)) {
                totalScore += getStrikeBonus(frame, rolls);
            } else if (isSpare(frame, rolls)) {
                totalScore += rolls[frame + 1][0];
            }

            // Check for extra rolls in the tenth frame
            if (frame == 9 && (isStrike(frame, rolls) || isSpare(frame, rolls))) {
                totalScore += rolls[frame + 1][0];
            }
        }

        return totalScore;
    }

    private static boolean isStrike(int frame, int[][] rolls) {
        return rolls[frame][0] == 10 && frame < 9;
    }

    private static boolean isSpare(int frame, int[][] rolls) {
        return rolls[frame][0] + rolls[frame][1] == 10 && frame < 9;
    }

    private static int getStrikeBonus(int frame, int[][] rolls) {
        int bonus = rolls[frame + 1][0] + rolls[frame + 1][1];
        if (isStrike(frame + 1, rolls)) {
            bonus += rolls[frame + 2][0];
        }
        return bonus;
    }
}