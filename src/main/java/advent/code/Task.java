package advent.code;

import java.util.List;

public interface Task {
    void calculate(List<List<String>> lines);
    String getSplitRegex();
}
