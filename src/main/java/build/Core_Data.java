package build;

import java.io.*;
import java.util.*;

public class Core_Data {

    static HashMap<String,ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();

    public static void GenerateDatabase(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("WorkerShifts.txt"));
        while(scan.hasNext()) {
            String line = scan.nextLine().toLowerCase().toString();
            ArrayList<String> items = new ArrayList<String>(Arrays.asList(line.split("\\s*,\\s*")));
            ArrayList<Integer> shifts = new ArrayList<Integer>();
            for (int a = 1; a < items.size(); a++) {
                int shift = Integer.parseInt(items.get(a));
                shifts.add(shift);
            }
            map.put(items.get(0), shifts);
        }
    }

    public static ArrayList<String> GenerateWorkersList(String[] args) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
        Scanner scan = new Scanner(new File("Workers.txt"));
        while(scan.hasNext()) {
            String line = scan.nextLine().toLowerCase().toString();
            ArrayList<String> items = new ArrayList<String>(Arrays.asList(line.split("\\s*,\\s*")));
            for (int a = 0; a < items.size(); a++) {
                list.add(items.get(a));
            }
        }
        return list;
    }

    public static ArrayList<Integer> Database(String Worker, ArrayList<Integer> Shifts) {
        if (Shifts.size() > 0) {
            ArrayList<Integer> old = new ArrayList<Integer>(map.get(Worker));
            for (int a = 0; a < old.size(); a++) {
                Shifts.add(old.get(a));
            }
            map.put(Worker, Shifts);
        }
        ArrayList<Integer> New = new ArrayList<Integer>(map.get(Worker));
        return New;
    }

}