import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.out;

public class JournalEvents {
    static int index;
    static String[][] events = new String[90][];
    static boolean[] booleans = new boolean[90];
    static String line;
    static String lane;

    public static void main(String[] args) throws IOException {

        String fName = "C:/users/legion/desktop/journalEvents_ru.csv";
        Scanner myFileReader = new Scanner(new File(fName));
        int i = 0;
        while (myFileReader.hasNextLine()) {
            line = myFileReader.nextLine();
            if (line.contains("false")) {
                booleans[index] = false;

                events[i++] = line.split(",false");

                index++;
            }
                if (line.contains("true")) {
                    booleans[index] = true;;
                    events[i++] = line.split(",true");
                    index++;
                }

            }
            out.println(Arrays.toString(booleans));
            out.println(Arrays.deepToString(events));


            myFileReader.close();


        }
    }

