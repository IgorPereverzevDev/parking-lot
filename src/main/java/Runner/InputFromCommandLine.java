package Runner;

import java.util.Iterator;
import java.util.Scanner;

public class InputFromCommandLine implements Iterable<String> {


    @Override
    public Iterator<String> iterator() {
        Scanner scanner = new Scanner(System.in);
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return scanner.hasNext();
            }

            @Override
            public String next() {
                return scanner.nextLine();
            }
        };
    }
}
