import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class JournalEvents {
    static int index;
    static String[][] events;
    static boolean[] booleans;
    static String line;

    public static void main(String[] args) throws IOException {
        String fName = "C:/users/legion/desktop/journalEvents_ru.csv";
        int size = returnArraySize(fName);
        events = new String[size][];
        booleans = new boolean[size];
        Scanner myFileReader = new Scanner(new File(fName));
        int i = 0;
        while (myFileReader.hasNextLine()) {
            line = myFileReader.nextLine();
            events[i++] = line.split(line.substring(line.lastIndexOf(",", line.lastIndexOf(","))));
            if (line.contains("false")) {
                booleans[index] = false;
                index++;
            } else {
                booleans[index] = true;
                index++;
            }
        }
        out.println(Arrays.toString(booleans));
        out.println(Arrays.deepToString(events));

        myFileReader.close();
    }

    public static int returnArraySize(String string) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(string), "UTF-8"));
        int lineCount = 0;
        while ((string = br.readLine()) != null) {
            if (string.length() > 1) {
                string.trim();
                lineCount++;
            }
        }
        br.close();
        return lineCount;
    }
}

