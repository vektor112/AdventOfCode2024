package advent.code;

import advent.code.task1.Task1;
import advent.code.task2.Task2;
import advent.code.task3.Task3;
import advent.code.task4.Task4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AdventOfCode2024Application implements CommandLineRunner {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private Task1 task1;

	@Autowired
	private Task2 task2;

	@Autowired
	private Task3 task3;

	@Autowired
	private Task4 task4;

	private final Map<String, Task> taskMap;

	@Autowired
	public AdventOfCode2024Application(
			Task1 task1,
			Task2 task2,
			Task3 task3,
			Task4 task4
	) {
		this.taskMap = Map.of(
				"1", task1,
				"2", task2,
				"3", task3,
				"4", task4
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(AdventOfCode2024Application.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length < 1) {
			System.exit(1);
		}

		String taskNumber = args[0];

		try {
			var task = taskMap.get(taskNumber);
			Resource resource = resourceLoader.getResource("classpath:taskInputs/" + taskNumber + ".txt");
			List<String> lines = Files.readAllLines(resource.getFile().toPath());
			List<List<String>> elemetsList = lines.stream().map(line -> {
				String[] parts = line.trim().split(task.getSplitRegex());
				return List.of(parts);
			}).toList();
			task.calculate(elemetsList);
		} catch (NumberFormatException | IOException e) {
			System.err.println("Error processing file: " + e.getMessage());
		}
	}
}
