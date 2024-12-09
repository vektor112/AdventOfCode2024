package advent.code.task2;

import advent.code.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class Task2 implements Task {
    public void calculate(List<List<String>> lines) {
        var numberLines = lines
                .stream()
                .map(line -> line.stream().map(Integer::parseInt).toList())
                .toList();

        var part1 = numberLines.stream()
                .map(numberLine -> getSafeyInfoList(numberLine).contains(true) ? 0 : 1)
                .mapToInt(Integer::intValue)
                .sum();

        var part2 = numberLines.stream()
                .map(numberLine -> {
                    List<Boolean> safetyInfoList = getSafeyInfoList(numberLine);

                    if (safetyInfoList.stream().noneMatch(b -> b)) return 1;

                    for (int i = 0; i < safetyInfoList.size(); i++) {
                        if(safetyInfoList.get(i)) {
                            var copiedList = new ArrayList<>(numberLine);
                            copiedList.remove(i);
                            var modifiedList = getSafeyInfoList(copiedList);
                            if(!modifiedList.contains(true)) return 1;
                        }
                    }

                    return 0;
                })
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(part1);
        System.out.println(part2);
        // Part 2 is not completed
    }

    public String getSplitRegex() {
        return " ";
    }

    private List<Boolean> getSafeyInfoList(List<Integer> numberLine) {
        var sub = numberLine.get(0) - numberLine.get(1);

        if (sub == 0) {
            var unsafeList = new ArrayList<>(Collections.nCopies(numberLine.size() + 1, false));
            unsafeList.set(0, true);
            return unsafeList;
        };

        var isIncrease = sub < 0;

        // Skip the first element
        return IntStream.range(0, numberLine.size()).mapToObj(j -> {
            if(j > 0) {
                var currentElement = numberLine.get(j);
                var previousElement = numberLine.get(j - 1);

                if (isIncrease && previousElement > currentElement) {
                    return true;
                }

                if (!isIncrease && previousElement < currentElement) {
                    return true;
                }

                var distance = Math.abs(previousElement - currentElement);
                return distance < 1 || distance > 3;
            } else {
                var currentElement = numberLine.get(j);
                var nextElement = numberLine.get(j + 1);

                if (isIncrease && nextElement < currentElement) {
                    return true;
                }

                if (!isIncrease && nextElement > currentElement) {
                    return true;
                }

                var distance = Math.abs(nextElement - currentElement);
                return distance < 1 || distance > 3;
            }
        }).toList();
    }
}
