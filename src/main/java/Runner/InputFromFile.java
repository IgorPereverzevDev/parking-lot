package Runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class InputFromFile implements Iterable<String> {

    private List<String> list;
    private int currentSize;

    InputFromFile(Path path) throws IOException {
        this.list = Files.readAllLines(path);
        this.currentSize = list.size();

    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && list.get(currentIndex) != null;
            }

            @Override
            public String next() {
                return list.get(currentIndex++);
            }
        };
    }
}
