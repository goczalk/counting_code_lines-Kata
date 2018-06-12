import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


//http://codekata.com/kata/kata13-counting-code-lines/

public class Main {
    private static final String FILE_NAME = "TestFile.java";

    public static void main(String arg[]){
        int linesCountTrimSplitRegex = 0;
        int linesCountStream = 0;

        linesCountTrimSplitRegex = bufferedReaderMethod();
        System.out.println("Trim and split method: " + linesCountTrimSplitRegex);

        linesCountStream = streamMethod();
        System.out.println("Stream method: " + linesCountStream);
    }

    private static int streamMethod() {
        long linesCountStream = 0;
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            linesCountStream = stream
                                .filter(l -> !l.matches("\\s*"))
                                .filter(l -> !l.matches("\\s*\\*.*"))
                                .filter(l -> !l.matches("\\s*/\\*.*"))
                                .filter(l -> !l.matches("\\s*//.*"))
                                .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return (int)linesCountStream;
        }
    }

    private static int bufferedReaderMethod() {
        int linesCountTrimSplitRegex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesCountTrimSplitRegex = trimSplitRegexMethod(linesCountTrimSplitRegex, line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesCountTrimSplitRegex;
    }

    private static int trimSplitRegexMethod(int linesCount, String line) {
        line = line.trim();

        if(!line.startsWith("//") && !line.startsWith("/*") && !line.startsWith("*")){

            String[] lineAfterSplit = line.split("\\s");
            if(lineAfterSplit.length != 1) {
                linesCount++;
            }
            else{
                if(lineAfterSplit[0].matches(".")){
                linesCount++;
                }
            };
        }
        return linesCount;
    }

}
