package advent.code.task1;

import advent.code.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class Task1 implements Task {
    public void calculate(List<List<String>> lines) {
        var lineSize = lines.size();
        var firstColumn = IntStream.range(0, lineSize)
                .mapToObj(i -> Integer.parseInt(lines.get(i).get(0)))
                .sorted()
                .toList();
        var secondColumn = IntStream.range(0, lineSize)
                .mapToObj(i -> Integer.parseInt(lines.get(i).get(1)))
                .sorted()
                .toList();

        part1(firstColumn, secondColumn, lineSize);
        part2(firstColumn, secondColumn, lineSize);
    }

    public String getSplitRegex() {
        return "\\s+";
    }

    private void part1(List<Integer> firstColumn, List<Integer> secondColumn, Integer lineSize) {
        Integer result = IntStream.range(0, lineSize)
                .map(i -> Math.abs(firstColumn.get(i) - secondColumn.get(i)))
                .sum();

        System.out.println("Part 1 result:");
        System.out.println(result);
    }

    private void part2(List<Integer> firstColumn, List<Integer> secondColumn, Integer lineSize) {
        Integer result = IntStream.range(0, lineSize)
                .map(i -> {
                    var number = firstColumn.get(i);
                    var frequency = IntStream.range(0, lineSize)
                            .map(j -> Objects.equals(secondColumn.get(j), number) ? 1 : 0)
                            .sum();
                    return number * frequency;
                })
                .sum();

        System.out.println("Part 2 result:");
        System.out.println(result);
    }
}
