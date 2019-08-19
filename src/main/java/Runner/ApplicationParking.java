package Runner;

import Orchestrator.Orchestrator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationParking {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            Orchestrator.run(new InputFromCommandLine());
        } else {
            runFromFile(args[0]);
        }
    }

    private static void runFromFile(String arg) throws IOException {
        Path path = Paths.get(arg);
        if (Files.isRegularFile(path)) {
            Orchestrator.run(new InputFromFile(path));
        } else {
            throw new IllegalStateException("Incorrect path!");
        }
    }

}
