package advent.code;

import advent.code.task1.Task1;
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

	private final Map<String, Task> taskMap;

	@Autowired
	public AdventOfCode2024Application(Task1 task1) {
		// Mapping input numbers to corresponding services
		this.taskMap = Map.of("1", task1);
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
			Resource resource = resourceLoader.getResource("classpath:taskInputs/" + taskNumber + ".txt");
			List<String> lines = Files.readAllLines(resource.getFile().toPath());
			List<TwoElementsLine<String, String>> elemetsList = lines.stream().map(line -> {
				String[] parts = line.trim().split("\\s+");
				String element1 = parts[0];
				String element2 = parts[1];
				return new TwoElementsLine<>(element1, element2);
			}).toList();
			taskMap.get(taskNumber).calculate(elemetsList);
		} catch (NumberFormatException | IOException e) {
			System.err.println("Error processing file: " + e.getMessage());
		}
	}
}
