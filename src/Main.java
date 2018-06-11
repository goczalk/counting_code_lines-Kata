import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//http://codekata.com/kata/kata13-counting-code-lines/

public class Main {
    public static void main(String arg[]){
        int linesCountTrimSplitRegex = 0;

        linesCountTrimSplitRegex = bufferedReaderMethod();
        System.out.println(linesCountTrimSplitRegex);
    }

    private static int bufferedReaderMethod() {
        int linesCountTrimSplitRegex = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("TestFile.java"))) {
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
