package build;

import java.io.*;
import java.util.*;

public class Core_Main {

    public static void main(String[] args) throws IOException {
        Core_Data.GenerateDatabase(args);
        ArrayList<String> WorkersPriorityList = Core_Data.GenerateWorkersList(args);
        ArrayList<String> SortedGeneralWorkers = new ArrayList<String>(Core_DataSort.ScheduleGeneralWorkersList(WorkersPriorityList));
        ArrayList<ArrayList<String>> SortedSpecialWorkers = new ArrayList<ArrayList<String>>(Core_DataSort.CheckFreeShifts(WorkersPriorityList));
        ArrayList<ArrayList<String>> Schedule = new ArrayList<ArrayList<String>>();
        for (int a = 0; a < 21; a++) {
            Schedule.add(new ArrayList<String>());
        }
        if (SortedGeneralWorkers.size() == 25) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 5,6,4,5,6,4,5,6,4,5,6,4,5,6,3,5,5,3,5,5,3);
        } else if (SortedGeneralWorkers.size() == 24) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 5,5,3,5,6,4,5,6,4,5,5,4,5,5,3,5,5,3,5,5,3);
        } else if (SortedGeneralWorkers.size() == 23) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 5,5,3,5,5,4,5,5,4,5,5,3,5,5,3,5,5,3,4,5,3);
        } else if (SortedGeneralWorkers.size() == 22) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,5,3,5,5,3,5,5,4,5,5,3,4,5,3,4,5,3,4,5,3);
        } else if (SortedGeneralWorkers.size() == 21) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,5,3,4,5,3,4,5,3,4,5,3,4,5,3,4,5,3,4,5,3);
        } else if (SortedGeneralWorkers.size() == 20) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,5,3,4,5,3,4,5,3,4,5,3,4,4,3,4,4,3,4,4,2);
        } else if (SortedGeneralWorkers.size() == 19) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,4,3,4,4,3,4,5,3,4,4,3,4,4,3,4,4,2,4,4,2);
        } else if (SortedGeneralWorkers.size() == 18) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,4,3,4,4,3,4,4,3,4,4,3,4,4,2,3,4,2,3,4,2);
        } else if (SortedGeneralWorkers.size() == 17) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 4,4,2,4,4,3,4,4,3,4,4,3,3,4,2,3,3,2,3,3,2);
        } else if (SortedGeneralWorkers.size() == 16) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 3,4,2,3,4,2,4,4,3,3,4,3,3,4,2,3,3,2,3,3,2);
        } else if (SortedGeneralWorkers.size() == 15) {
            Schedule = Core_DataSort.ScheduleWorkerDayShifts(Schedule, SortedGeneralWorkers, 3,3,2,3,4,2,3,4,3,3,4,2,3,3,2,3,3,2,3,3,2);
        }
        if (SortedSpecialWorkers.get(0).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerDayFreeShifts(Schedule, SortedSpecialWorkers.get(0), 0, 4);
        }
        if (SortedSpecialWorkers.get(1).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerNightFreeShifts(Schedule, SortedSpecialWorkers.get(1), 2, 0);
        }
        if (SortedSpecialWorkers.get(2).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerNightFreeShifts(Schedule, SortedSpecialWorkers.get(2), 1, 2);
        }
        if (SortedSpecialWorkers.get(3).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerDayFreeShifts(Schedule, SortedSpecialWorkers.get(3), 0, 3);
        }
        if (SortedSpecialWorkers.get(4).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerNightFreeShifts(Schedule, SortedSpecialWorkers.get(4), 1, 1);
        }
        if (SortedSpecialWorkers.get(5).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerDayFreeShifts(Schedule, SortedSpecialWorkers.get(5), 0, 2);
        }
        if (SortedSpecialWorkers.get(6).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerNightFreeShifts(Schedule, SortedSpecialWorkers.get(6), 1, 0);
        }
        if (SortedSpecialWorkers.get(7).size() > 0) {
            Schedule = Core_DataSort.ScheduleWorkerDayFreeShifts(Schedule, SortedSpecialWorkers.get(7), 0, 1);
        }
        ArrayList<ArrayList<String>> FinalSchedule = new ArrayList<ArrayList<String>>();
        for (int a = 0; a < 21; a++) {
            FinalSchedule.add(new ArrayList<String>());
        }
        for (int a = 0; a < Schedule.size(); a++) {
            for (int b = 0; b < WorkersPriorityList.size(); b++) {
                if (Schedule.get(a).contains(WorkersPriorityList.get(b))) {
                    FinalSchedule.get(a).add(WorkersPriorityList.get(b));
                }
            }
        }
        BufferedWriter Writer = new BufferedWriter(new FileWriter("Schdule.txt"));
        for (int a = 0; a < FinalSchedule.size(); a++) {
            for(String String: FinalSchedule.get(a)) {
                Writer.write(String + "/");
            }
            Writer.write("\n");
        }
        Writer.close();
    }

}