package build;

import java.util.*;

public class Core_DataSort {

    public static ArrayList<ArrayList<String>> ScheduleWorkerDayShifts(ArrayList<ArrayList<String>> Schedule, ArrayList<String> Workers, int MondayMorning, int MondayEvening, int MondayNight, int TuesdayMorning, int TuesdayEvening, int TuesdayNight, int WednesdayMorning, int WednesdayEvening, int WednesdayNight, int ThursdayMorning, int ThursdayEvening, int ThursdayNight, int FridayMorning, int FridayEvening, int FridayNight, int SaturdayMorning, int SaturdayEvening, int SaturdayNight, int SundayMorning, int SundayEvening, int SundayNight) {
        Schedule = Core_DataSort.ScheduleWorkerNightShift(Schedule, Workers, MondayNight, TuesdayNight, WednesdayNight, ThursdayNight, FridayNight, SaturdayNight, SundayNight);
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
            ArrayList<Integer> WorkerNightShift = new ArrayList<Integer>();
            ArrayList<Integer> DayShifts = new ArrayList<Integer>(Arrays.asList(1,2,4,5,7,8,10,11,13,14,16,17,19,20));
            ArrayList<Integer> LegitDayShifts = Core_Calculation.CheckLegitShifts(DayShifts, Core_Data.Database(Workers.get(a), AnchorShifts));
            for (int b = 0; b < Schedule.size(); b++) {
                if (Schedule.get(b).contains(Workers.get(a))) {
                    WorkerNightShift.add(1 + b);
                }
            }
            ArrayList<Integer> WorkerDayShifts = Core_Calculation.PickRandomDayShifts(LegitDayShifts, WorkerNightShift);
            int counter = 1;
            while (counter > 0) {
                if ((WorkerDayShifts.contains(1) && Schedule.get(0).size() >= MondayMorning) || (WorkerDayShifts.contains(2) && Schedule.get(1).size() >= MondayEvening) || (WorkerDayShifts.contains(4) && Schedule.get(3).size() >= TuesdayMorning) || (WorkerDayShifts.contains(5) && Schedule.get(4).size() >= TuesdayEvening) || (WorkerDayShifts.contains(7) && Schedule.get(6).size() >= WednesdayMorning) || (WorkerDayShifts.contains(8) && Schedule.get(7).size() >= WednesdayEvening) || (WorkerDayShifts.contains(10) && Schedule.get(9).size() >= ThursdayMorning) || (WorkerDayShifts.contains(11) && Schedule.get(10).size() >= ThursdayEvening) || (WorkerDayShifts.contains(13) && Schedule.get(12).size() >= FridayMorning) || (WorkerDayShifts.contains(14) && Schedule.get(13).size() >= FridayEvening) || (WorkerDayShifts.contains(16) && Schedule.get(15).size() >= SaturdayMorning) || (WorkerDayShifts.contains(17) && Schedule.get(16).size() >= SaturdayEvening) || (WorkerDayShifts.contains(19) && Schedule.get(18).size() >= SundayMorning) || (WorkerDayShifts.contains(20) && Schedule.get(19).size() >= SundayEvening)) {
                    WorkerDayShifts = Core_Calculation.PickRandomDayShifts(LegitDayShifts, WorkerNightShift);
                    counter++;
                    if (counter > 99) {
                        for (int c = 0; c < 21; c++) {
                            Schedule.get(c).clear();
                        }
                        a = -1;
                        Schedule = Core_DataSort.ScheduleWorkerNightShift(Schedule, Workers, MondayNight, TuesdayNight, WednesdayNight, ThursdayNight, FridayNight, SaturdayNight, SundayNight);
                        break;
                    }
                } else {
                    counter = 0;
                    for (int c = 0; c < WorkerDayShifts.size(); c++) {
                        if (WorkerDayShifts.get(c) == 1) {
                            Schedule.get(0).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 2) {
                            Schedule.get(1).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 4) {
                            Schedule.get(3).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 5) {
                            Schedule.get(4).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 7) {
                            Schedule.get(6).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 8) {
                            Schedule.get(7).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 10) {
                            Schedule.get(9).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 11) {
                            Schedule.get(10).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 13) {
                            Schedule.get(12).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 14) {
                            Schedule.get(13).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 16) {
                            Schedule.get(15).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 17) {
                            Schedule.get(16).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 19) {
                            Schedule.get(18).add(Workers.get(a));
                        } else if (WorkerDayShifts.get(c) == 20) {
                            Schedule.get(19).add(Workers.get(a));
                        }
                    }
                }
            }
        }
        return Schedule;
    }

    public static ArrayList<ArrayList<String>> ScheduleWorkerNightShift(ArrayList<ArrayList<String>> Schedule, ArrayList<String> Workers, int Monday, int Tuesday, int Wednesday, int Thursday, int Friday, int Saturday, int Sunday) {
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
            ArrayList<Integer> DayShifts = new ArrayList<Integer>(Arrays.asList(1,2,4,5,7,8,10,11,13,14,16,17,19,20));
            ArrayList<Integer> AllShifts = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
            ArrayList<Integer> LegitShifts = Core_Calculation.CheckLegitShifts(AllShifts, Core_Data.Database(Workers.get(a), AnchorShifts));
            ArrayList<Integer> LegitNightShifts = Core_Calculation.CheckLegitShifts(LegitShifts, DayShifts);
            ArrayList<Integer> WorkerNightShift = Core_Calculation.PickRandomNightShift(LegitNightShifts, 0);
            int counter = 1;
            while (counter > 0) {
                if (WorkerNightShift.get(0) == 3 && Schedule.get(2).size() < Monday) {
                    Schedule.get(2).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 6 && Schedule.get(5).size() < Tuesday) {
                    Schedule.get(5).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 9 && Schedule.get(8).size() < Wednesday) {
                    Schedule.get(8).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 12 && Schedule.get(11).size() < Thursday) {
                    Schedule.get(11).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 15 && Schedule.get(14).size() < Friday) {
                    Schedule.get(14).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 18 && Schedule.get(17).size() < Saturday) {
                    Schedule.get(17).add(Workers.get(a));
                    counter = 0;
                } else if (WorkerNightShift.get(0) == 21 && Schedule.get(20).size() < Sunday) {
                    Schedule.get(20).add(Workers.get(a));
                    counter = 0;
                } else if ((Schedule.get(2).size() >= Monday && Schedule.get(5).size() >= Tuesday && Schedule.get(8).size() >= Wednesday && Schedule.get(11).size() >= Thursday && Schedule.get(14).size() >= Friday && Schedule.get(17).size() >= Saturday && Schedule.get(20).size() >= Sunday) || counter > 99) {
                    for (int b = 2; b < 21; b = b + 3) {
                        Schedule.get(b).clear();
                    }
                    a = -1;
                    break;
                } else {
                    counter++;
                    WorkerNightShift = Core_Calculation.PickRandomNightShift(LegitNightShifts, 0);
                }
            }
        }
        return Schedule;
    }

    public static ArrayList<ArrayList<String>> ScheduleWorkerDayFreeShifts(ArrayList<ArrayList<String>> Schedule, ArrayList<String> Workers, int NightShifts, int Shifts) {
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
            ArrayList<Integer> DayShifts = new ArrayList<Integer>(Arrays.asList(1,2,4,5,7,8,10,11,13,14,16,17,19,20));
            ArrayList<Integer> LegitShifts = Core_Calculation.CheckLegitShifts(DayShifts, Core_Data.Database(Workers.get(a), AnchorShifts));
            ArrayList<Integer> WorkerSpecialShifts = Core_Calculation.SpecialCaseDayShifts(LegitShifts, Shifts);
            for (int b = 0; b < WorkerSpecialShifts.size(); b++) {
                if (WorkerSpecialShifts.get(b) == 1) {
                    Schedule.get(0).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 2) {
                    Schedule.get(1).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 4) {
                    Schedule.get(3).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 5) {
                    Schedule.get(4).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 7) {
                    Schedule.get(6).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 8) {
                    Schedule.get(7).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 10) {
                    Schedule.get(9).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 11) {
                    Schedule.get(10).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 13) {
                    Schedule.get(12).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 14) {
                    Schedule.get(13).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 16) {
                    Schedule.get(15).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 17) {
                    Schedule.get(16).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 19) {
                    Schedule.get(18).add(Workers.get(a));
                } else if (WorkerSpecialShifts.get(b) == 20) {
                    Schedule.get(19).add(Workers.get(a));
                }
            }
        }
        return Schedule;
    }

    public static ArrayList<ArrayList<String>> ScheduleWorkerNightFreeShifts(ArrayList<ArrayList<String>> Schedule, ArrayList<String> Workers, int NightShifts, int Shifts) {
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
            ArrayList<Integer> DayShifts = new ArrayList<Integer>(Arrays.asList(1,2,4,5,7,8,10,11,13,14,16,17,19,20));
            ArrayList<Integer> AllShifts = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
            ArrayList<Integer> LegitShifts = Core_Calculation.CheckLegitShifts(AllShifts, Core_Data.Database(Workers.get(a), AnchorShifts));
            ArrayList<Integer> LegitDayShifts = Core_Calculation.CheckLegitShifts(DayShifts, Core_Data.Database(Workers.get(a), AnchorShifts));
            ArrayList<Integer> LegitNightShifts = Core_Calculation.CheckLegitShifts(LegitShifts, DayShifts);
            ArrayList<Integer> WorkerNightShift = Core_Calculation.PickRandomNightShift(LegitNightShifts, NightShifts);
            ArrayList<Integer> WorkerDayShifts = Core_Calculation.SpecialCaseNightShifts(LegitDayShifts, WorkerNightShift, Shifts);
            for (int b = 0; b < WorkerDayShifts.size(); b++) {
                if (WorkerDayShifts.get(b) == 1) {
                    Schedule.get(0).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 2) {
                    Schedule.get(1).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 4) {
                    Schedule.get(3).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 5) {
                    Schedule.get(4).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 7) {
                    Schedule.get(6).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 8) {
                    Schedule.get(7).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 10) {
                    Schedule.get(9).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 11) {
                    Schedule.get(10).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 13) {
                    Schedule.get(12).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 14) {
                    Schedule.get(13).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 16) {
                    Schedule.get(15).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 17) {
                    Schedule.get(16).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 19) {
                    Schedule.get(18).add(Workers.get(a));
                } else if (WorkerDayShifts.get(b) == 20) {
                    Schedule.get(19).add(Workers.get(a));
                }
            }
            for (int b = 0; b < WorkerNightShift.size(); b++) {
                if (WorkerNightShift.get(b) == 3) {
                    Schedule.get(2).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 6) {
                    Schedule.get(5).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 9) {
                    Schedule.get(8).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 12) {
                    Schedule.get(11).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 15) {
                    Schedule.get(14).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 18) {
                    Schedule.get(17).add(Workers.get(a));
                } else if (WorkerNightShift.get(b) == 21) {
                    Schedule.get(20).add(Workers.get(a));
                }
            }
        }
        return Schedule;
    }

    public static ArrayList<ArrayList<String>> CheckFreeShifts(ArrayList<String> Workers) {
        ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> SpecialShiftSchedule = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> AcademicSchedule = new ArrayList<ArrayList<String>>();
        for (int a = 0; a < 8 ;a++) {
            SpecialShiftSchedule.add(new ArrayList<String>());
        }
        for (int a = 0; a < 5; a++) {
            AcademicSchedule.add(new ArrayList<String>());
        }
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> WorkerShifts = new ArrayList<Integer>(Core_Data.Database(Workers.get(a), AnchorShifts));
            if (WorkerShifts.contains(32)) {
                AcademicSchedule.get(0).add(Workers.get(a));
            }
            if (WorkerShifts.contains(33)) {
                AcademicSchedule.get(1).add(Workers.get(a));
            }
            if (WorkerShifts.contains(34)) {
                AcademicSchedule.get(2).add(Workers.get(a));
            }
            if (WorkerShifts.contains(35)) {
                AcademicSchedule.get(3).add(Workers.get(a));
            }
            if (WorkerShifts.contains(36)) {
                AcademicSchedule.get(4).add(Workers.get(a));
            }
        }
        for (int a = 0; a < Workers.size(); a++) {
            int counter = 0;
            for (int b = 0; b < AcademicSchedule.size(); b++) {
                if (AcademicSchedule.get(b).contains(Workers.get(a))) {
                    counter++;
                }
            }
            if (counter == 4) {
                SpecialShiftSchedule.get(7).add(Workers.get(a));
            }
            if (counter == 3) {
                //SpecialShiftSchedule.get(6).add(Workers.get(a));
                SpecialShiftSchedule.get(5).add(Workers.get(a));
            }
            if (counter == 2) {
                //SpecialShiftSchedule.get(4).add(Workers.get(a));
                SpecialShiftSchedule.get(3).add(Workers.get(a));
            }
            if (counter == 1) {
                //SpecialShiftSchedule.get(2).add(Workers.get(a));
                //SpecialShiftSchedule.get(1).add(Workers.get(a));
                SpecialShiftSchedule.get(0).add(Workers.get(a));
            }
        }
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> WorkerShifts = new ArrayList<Integer>(Core_Data.Database(Workers.get(a), AnchorShifts));
            if (WorkerShifts.contains(22)) {
                SpecialShiftSchedule.get(0).add(Workers.get(a));
            }
            if (WorkerShifts.contains(23)) {
                SpecialShiftSchedule.get(1).add(Workers.get(a));
            }
            if (WorkerShifts.contains(24)) {
                SpecialShiftSchedule.get(2).add(Workers.get(a));
            }
            if (WorkerShifts.contains(25)) {
                SpecialShiftSchedule.get(3).add(Workers.get(a));
            }
            if (WorkerShifts.contains(26)) {
                SpecialShiftSchedule.get(4).add(Workers.get(a));
            }
            if (WorkerShifts.contains(27)) {
                SpecialShiftSchedule.get(5).add(Workers.get(a));
            }
            if (WorkerShifts.contains(28)) {
                SpecialShiftSchedule.get(6).add(Workers.get(a));
            }
            if (WorkerShifts.contains(29)) {
                SpecialShiftSchedule.get(7).add(Workers.get(a));
            }
        }
        return SpecialShiftSchedule;
    }

    public static ArrayList<String> ScheduleGeneralWorkersList(ArrayList<String> Workers) {
        ArrayList<Integer> AnchorShifts = new ArrayList<Integer>();
        ArrayList<String> WorkersList = new ArrayList<String>(Workers);
        for (int a = 0; a < Workers.size(); a++) {
            ArrayList<Integer> WorkerShifts = new ArrayList<Integer>(Core_Data.Database(Workers.get(a), AnchorShifts));
            if (WorkerShifts.contains(0) || WorkerShifts.contains(22) || WorkerShifts.contains(23) || WorkerShifts.contains(24) || WorkerShifts.contains(25) || WorkerShifts.contains(26) || WorkerShifts.contains(27) || WorkerShifts.contains(28) || WorkerShifts.contains(29) || WorkerShifts.contains(32) || WorkerShifts.contains(33) || WorkerShifts.contains(34) || WorkerShifts.contains(35) || WorkerShifts.contains(36)) {
                WorkersList.remove(Workers.get(a));
                if (WorkerShifts.contains(32) && WorkerShifts.contains(33) && WorkerShifts.contains(34) && WorkerShifts.contains(35) && WorkerShifts.contains(36)) {
                    ArrayList<Integer> EveningAnchor = new ArrayList<Integer>(Arrays.asList(0));
                    Core_Data.Database(Workers.get(a), EveningAnchor);
                }
                if (WorkerShifts.contains(32)) {
                    ArrayList<Integer> AcademicMonday = new ArrayList<Integer>(Arrays.asList(1,2,3));
                    Core_Data.Database(Workers.get(a), AcademicMonday);
                }
                if (WorkerShifts.contains(33)) {
                    ArrayList<Integer> AcademicTuesday = new ArrayList<Integer>(Arrays.asList(4,5,6));
                    Core_Data.Database(Workers.get(a), AcademicTuesday);
                }
                if (WorkerShifts.contains(34)) {
                    ArrayList<Integer> AcademicWednesday = new ArrayList<Integer>(Arrays.asList(7,8,9));
                    Core_Data.Database(Workers.get(a), AcademicWednesday);
                }
                if (WorkerShifts.contains(35)) {
                    ArrayList<Integer> AcademicThursday = new ArrayList<Integer>(Arrays.asList(10,11,12));
                    Core_Data.Database(Workers.get(a), AcademicThursday);
                }
                if (WorkerShifts.contains(36)) {
                    ArrayList<Integer> AcademicFriday = new ArrayList<Integer>(Arrays.asList(13,14,15));
                    Core_Data.Database(Workers.get(a), AcademicFriday);
                }
            } else if (WorkerShifts.contains(30)) {
                ArrayList<Integer> EveningAnchor = new ArrayList<Integer>(Arrays.asList(1));
                Core_Data.Database(Workers.get(a), EveningAnchor);
            } else if (WorkerShifts.contains(31)) {
                ArrayList<Integer> NightAnchor = new ArrayList<Integer>(Arrays.asList(1,2,3));
                Core_Data.Database(Workers.get(a), NightAnchor);
            }
        }
        return WorkersList;
    }

}