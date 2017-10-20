package build;

import java.util.*;

public class Core_Calculation {

    public static ArrayList<Integer> CheckLegitShifts(ArrayList<Integer> arg1, ArrayList<Integer> arg2) {
        ArrayList<Integer> list = new ArrayList<Integer>(arg1);
        for (int a : arg2) {
            if (list.indexOf(a) >= 0) {
                list.remove(list.indexOf(a));
            }
        }
        return list;
    }

    public static ArrayList<Integer> PickRandomNightShift(ArrayList<Integer> arg1, int arg2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        Integer number = arg1.get(random.nextInt(arg1.size()));
        list.add(number);
        if (arg2 > 1) {
            int check = 0;
            while (check == 0) {
                number = arg1.get(random.nextInt(arg1.size()));
                if (list.get(0) != number || list.get(0) + 3 != number || list.get(0) + 3 != number || list.get(0) - 3 != number || list.get(0) - 3 != number) {
                    list.add(number);
                    check++;
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> PickRandomDayShifts(ArrayList<Integer> arg1, ArrayList<Integer> arg2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int counter = 0;
        while (list.size() < 3) {
            Collections.shuffle(arg1);
            Integer number = arg1.get(0);
            if (!(list.contains(number) || arg2.contains(number + 1) || arg2.contains(number + 2) || arg2.contains(number - 1) || arg2.contains(number - 2) || list.contains(number + 1) || list.contains(number + 2) || list.contains(number - 1) || list.contains(number - 2))) {
                list.add(number);
            } else {
                if (counter > 999) {
                    Collections.shuffle(list);
                    list.remove(0);
                    counter = 0;
                } else {
                    counter++;
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> SpecialCaseNightShifts(ArrayList<Integer> arg1, ArrayList<Integer> arg2, int arg3) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int counter = 0;
        while (list.size() < arg3) {
            Collections.shuffle(arg1);
            Integer number = arg1.get(0);
            if (!(list.contains(number) || arg2.contains(number + 1) || arg2.contains(number + 2) || arg2.contains(number - 1) || arg2.contains(number - 2) || list.contains(number + 1) || list.contains(number + 2) || list.contains(number - 1) || list.contains(number - 2))) {
                list.add(number);
            } else {
                if (counter > 999) {
                    Collections.shuffle(list);
                    list.remove(0);
                    counter = 0;
                } else {
                    counter++;
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> SpecialCaseDayShifts(ArrayList<Integer> arg1, int arg2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int counter = 0;
        while (list.size() < arg2) {
            Collections.shuffle(arg1);
            Integer number = arg1.get(0);
            if (!(list.contains(number) || list.contains(number + 1) || list.contains(number + 2) || list.contains(number - 1) || list.contains(number - 2))) {
                list.add(number);
            } else {
                if (counter > 999) {
                    Collections.shuffle(list);
                    list.remove(0);
                    counter = 0;
                } else {
                    counter++;
                }
            }
        }
        return list;
    }

}