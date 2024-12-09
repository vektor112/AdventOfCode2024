package advent.code.task3;

import advent.code.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Task3 implements Task {
    public void calculate(List<List<String>> lines) {
        var input = lines.stream()
                .map(line -> String.join("", line))
                .collect(Collectors.joining(""));

        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    public String getSplitRegex() {
        return "";
    }

    private Integer part2(String input) {
        var mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        var mulPattern = Pattern.compile(mulRegex);
        var mulMatcher = mulPattern.matcher(input);

        var enablerRegex = "do\\(\\)|don't\\(\\)";

        var enablerPattern = Pattern.compile(enablerRegex);
        var enablerMatcher = enablerPattern.matcher(input);

        var sum = 0;

        var enablerMap = new HashMap<Integer, Boolean>();

        while (enablerMatcher.find()) {
            System.out.println("Start: " + enablerMatcher.start());
            System.out.println("Do|Dont match found: " + enablerMatcher.group(0));
            enablerMap.put(enablerMatcher.start(), Objects.equals(enablerMatcher.group(0), "do()"));
        }

        while (mulMatcher.find()) {
            System.out.println("Start: " + mulMatcher.start());
            System.out.println("Match found: " + mulMatcher.group(0));
            System.out.println("First number: " + mulMatcher.group(1));
            System.out.println("Second number: " + mulMatcher.group(2));

            if (shouldNotCalc(mulMatcher.start(), enablerMap)) {
                continue;
            }

            var mulRes = Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
            System.out.println("Mul result: " + mulRes);
            sum = sum + mulRes;
        }

        return sum;
    }

    private Boolean shouldNotCalc(int start, Map<Integer, Boolean> enablerMap) {
        for (int i = start; i >= 0; i--) {
            var enablerVal = enablerMap.get(i);
            if (enablerVal != null) {
                return !enablerVal;
            }
        }

        return false;
    }

    private Integer part1(String input) {
        var regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(input);

        var sum = 0;

        while (matcher.find()) {
            System.out.println("Match found: " + matcher.group(0));
            System.out.println("First number: " + matcher.group(1));
            System.out.println("Second number: " + matcher.group(2));
            var mulRes = Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            System.out.println("Mul result: " + mulRes);
            sum = sum + mulRes;
        }

        return sum;
    }
}
