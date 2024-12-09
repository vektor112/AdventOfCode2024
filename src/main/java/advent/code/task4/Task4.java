package advent.code.task4;

import advent.code.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class Task4 implements Task {
    private final String XMAS = "XMAS";

    public void calculate(List<List<String>> lines) {
        part1(lines);
        part2(lines);
    }

    private void part2(List<List<String>> lines) {
        var finalResult = 0;
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).size(); j++) {

                boolean rightMinimumRequirement = j + 2 <= lines.get(i).size();
                boolean leftMinimumRequirement = j > 0;
                boolean downMinimumRequirement = i + 2 <= lines.size();
                boolean upMinimumRequirement = i > 0;

                if (rightMinimumRequirement && leftMinimumRequirement && downMinimumRequirement && upMinimumRequirement) {
                    var upLeft = lines.get(i - 1).get(j - 1);
                    var upRight = lines.get(i - 1).get(j + 1);
                    var middle = lines.get(i).get(j);
                    var downLeft = lines.get(i + 1).get(j - 1);
                    var downRight = lines.get(i + 1).get(j + 1);

                    if (Objects.equals(middle, "A") &&
                            ((Objects.equals(upLeft, "S") && Objects.equals(downRight, "M") || Objects.equals(upLeft, "M") && Objects.equals(downRight, "S"))) &&
                            ((Objects.equals(upRight, "S") && Objects.equals(downLeft, "M") || Objects.equals(upRight, "M") && Objects.equals(downLeft, "S")))
                    ) {
                        finalResult++;
                    }
                }
            }
        }

        System.out.println(finalResult);
    }

    private void part1(List<List<String>> lines) {
        var finalResult = 0;
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).size(); j++) {

                boolean rightMinimumRequirement = j + XMAS.length() <= lines.get(i).size();
                boolean leftMinimumRequirement = j > XMAS.length() - 2;
                boolean downMinimumRequirement = i + XMAS.length() <= lines.size();
                boolean upMinimumRequirement = i > XMAS.length() - 2;

                // Right
                if (rightMinimumRequirement) {
                    StringBuilder toRight = new StringBuilder();
                    for(int col = j; col < j + XMAS.length(); col++) {
                        toRight.append(lines.get(i).get(col));
                    }
                    if (XMAS.contentEquals(toRight)) finalResult++;
                }

                // Left
                if (leftMinimumRequirement) {
                    StringBuilder toLeft = new StringBuilder();
                    for(int col = j; col > j - XMAS.length(); col--) {
                        toLeft.append(lines.get(i).get(col));
                    }
                    if (XMAS.contentEquals(toLeft)) finalResult++;
                }

                // Down
                if (downMinimumRequirement) {
                    StringBuilder toDown = new StringBuilder();
                    for(int row = i; row < i + XMAS.length(); row++) {
                        toDown.append(lines.get(row).get(j));
                    }
                    if (XMAS.contentEquals(toDown)) finalResult++;
                }

                // Up
                if (upMinimumRequirement) {
                    StringBuilder toLeft = new StringBuilder();
                    for(int row = i; row > i - XMAS.length(); row--) {
                        toLeft.append(lines.get(row).get(j));
                    }
                    if (XMAS.contentEquals(toLeft)) finalResult++;
                }

                // Up-Right
                if (upMinimumRequirement && rightMinimumRequirement) {
                    StringBuilder toUpRight = new StringBuilder();
                    for (int row = i, col = j; row >= i - 4 && col < j + XMAS.length(); row--, col++) {
                        toUpRight.append(lines.get(row).get(col));
                    }
                    if (XMAS.contentEquals(toUpRight)) finalResult++;
                }

                // Up-Left
                if (upMinimumRequirement && leftMinimumRequirement) {
                    StringBuilder toUpLeft = new StringBuilder();
                    for (int row = i, col = j; row >= i - 4 && col >= j - XMAS.length() - 1; row--, col--) {
                        toUpLeft.append(lines.get(row).get(col));
                    }
                    if (XMAS.contentEquals(toUpLeft)) finalResult++;
                }

                // Down-Right
                if (downMinimumRequirement && rightMinimumRequirement) {
                    StringBuilder toDownRight = new StringBuilder();
                    for (int row = i, col = j; row < i + 4 && col < j + XMAS.length(); row++, col++) {
                        toDownRight.append(lines.get(row).get(col));
                    }
                    if (XMAS.contentEquals(toDownRight)) finalResult++;
                }

                // Down-Left
                if (downMinimumRequirement && leftMinimumRequirement) {
                    StringBuilder toDownLeft = new StringBuilder();
                    for (int row = i, col = j; row < i + 4 && col >= j - XMAS.length() - 1; row++, col--) {
                        toDownLeft.append(lines.get(row).get(col));
                    }
                    if (XMAS.contentEquals(toDownLeft)) finalResult++;
                }
            }
        }

        System.out.println(finalResult);
    }

    public String getSplitRegex() {
        return "";
    }
}
