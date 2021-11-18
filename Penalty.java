import java.util.ArrayList;

public class Penalty
{

    public static double countPenalty(ArrayList<String> student, int[] timeslot){
        double penalty = 0;

        //membaca course yang diambil tiap student
        for (String s : student) {
            String[] courseTaken = s.split(" ");
            if (courseTaken.length > 1) {
                for (int i = 0; i < courseTaken.length; i++) {
                    for (int j = 0; j < courseTaken.length; j++) {
                        if (i != j) {
                            int timeslotCourse1 = timeslot[i];
                            int timeslotCourse2 = timeslot[j];
                            //menghitung jarak timeslot antara course 1 dan course 2 yang diambil oleh student
                            int jarak = Math.abs(timeslotCourse1 - timeslotCourse2);
                            //apabila jarak lebih dari 4 maka dianggap tidak ada penalti
                            if(jarak < 5){
                                penalty= penalty+(Math.pow(2,(4-jarak)));
                            }
                        }
                    }
                }
            }
        }
        return (penalty/student.size());
    }
}