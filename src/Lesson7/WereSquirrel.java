package Lesson7;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class WereSquirrel {

    static String[] events;
    static boolean[] squirrel;
    final static String fileName = "C:/users/legion/desktop/journalEvents_ru.csv";
    static String[] soloEvents;
    static int index;
    static String frame;
    static String line;

    public static void main(String[] args) throws IOException {
        int size = returnArraySizeNew(fileName);
        events = new String[size];
        squirrel = new boolean[size];
        soloEvents = new String[size];
        Scanner myFileReader = new Scanner(new File(fileName));
        int i = 0;
        while (myFileReader.hasNextLine()) {
            line = myFileReader.nextLine();
            events[i++] = line;
            squirrel[index] = !line.contains("false");
            index++;
        }
        makeSoloEvent(fileName);
        removeDuplicate(soloEvents, soloEvents.length);
        removeEmptyStrings(soloEvents);
        System.out.println(Arrays.toString(soloEvents));

        for (String soloEvent : soloEvents) {
            double corellation = phi(tableFor(soloEvent, events));
            if (corellation > 0.3 || corellation < -0.3) {
                System.out.println(soloEvent + "    : " + corellation);
            }
        }
        returnPeanut(events);
        System.out.println("\nДля нового события:  арахис-зубы:   " + phi(tableFor("арахис-зубы", events)));
    }

    public static double phi(int[] table) {
        return (table[3] * table[0] - table[2] * table[1]) /
                Math.sqrt((table[2] + table[3]) *
                        (table[0] + table[1]) *
                        (table[1] + table[3]) *
                        (table[0] + table[2]));
    }

    public static int[] tableFor(String event, String[] journal) {
        int[] table = {0, 0, 0, 0};
        for (String entry : journal) {
            if (entry.contains(event) && entry.contains("false")) {
                table[1] += 1;

            }

            if (entry.contains("true") && entry.contains(event)) {
                table[3] += 1;

            }
            if (!entry.contains(event) && entry.contains("true")) {
                table[2] += 1;

            }
            if (entry.contains("false") && !entry.contains(event)) {
                table[0] += 1;

            }
        }
        return table;
    }

    public static String[] makeSoloEvent(String inputFile) throws IOException {
        int j = 0;
        Scanner myFileReader = new Scanner(new File(inputFile));
        while (myFileReader.hasNextLine()) {
            inputFile = myFileReader.nextLine();
            frame = inputFile.substring(0, inputFile.lastIndexOf(',', inputFile.lastIndexOf(',')));
            String[] values = frame.split(",");
            for (String value : values) {
                if(j >= soloEvents.length){
                    soloEvents = Arrays.copyOf(soloEvents,soloEvents.length+10);
                }
                soloEvents[j++] = value;

            }
        }
        return soloEvents;
    }

    public static int returnArraySizeNew(String string) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(string), StandardCharsets.UTF_8));
        int lineCount = 0;
        while ((string = br.readLine()) != null) {
            if (string.length() > 1) {
                lineCount++;
            }
        }
        br.close();
        return lineCount;
    }

    public static String[] removeDuplicate(String[] str, int size) {
        for (int i = 0; i < size - 1; i++) {
            if (str[i] != null) {
                for (int j = i + 1; j < size - 1; j++) {
                    if (str[i].equals(str[j])) {
                        str[j] = "";
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (str[i] == null)
                str[i] = "";
        }
        return str;

    }

    public static String[] removeEmptyStrings(String[] str) {
        if(str == null) {
        return str;
        }
        String [] result = new String [soloEvents.length];
        int validStrings = 0;
        for (int i = 0; i < soloEvents.length; i++) {
            if(!str[i].equals("")){
                result[validStrings++] = str[i];
            }

        }
        soloEvents = Arrays.copyOf(result,validStrings);
        return soloEvents;
    }

    public static String [] returnPeanut(String[]arr){
        int farIndex = 0;
        String [] result = new String[arr.length];
        for (String entry : arr) {
            if(!entry.contains("ел арахис") && !entry.contains("чистил зубы") | entry.contains("чистил зубы") ){
                result[farIndex++] = entry;
            }
            if (entry.contains("ел арахис") && !entry.contains("чистил зубы")) {
                result[farIndex++] = "арахис-зубы, true";
            }
        }
        events = Arrays.copyOf(result,farIndex);
        return events;
    }
}

