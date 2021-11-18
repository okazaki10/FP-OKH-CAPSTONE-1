import java.util.Arrays;

public class ExamScheduling {

    int[][] conflictMatrix;
    int[] timeslot;
    int indexts;
    public static boolean tampil = true;

    public ExamScheduling(int[][] conflictMatrix) {
        this.conflictMatrix = conflictMatrix;
    }

    public static boolean isTimeslotAvailableSorted(int jumlahCourse, int indexts, int[][] courseSorted,
            int[][] conflictMatrix, int[] timeslot) {
        for (int i = 0; i < courseSorted.length; i++) {
            if (conflictMatrix[courseSorted[jumlahCourse][0] - 1][i] != 0 && timeslot[i] == indexts)
                return false;
        }
        return true;
    }

    public int[] scheduleByDegree(int[][] courseSorted, int jumlahCourse) {
        this.timeslot = new int[jumlahCourse];
        indexts = 1;

        for (int i = 0; i < courseSorted.length; i++) {
            this.timeslot[i] = 0;
        }

        for (jumlahCourse = 0; jumlahCourse < courseSorted.length; jumlahCourse++) {
            for (int indexts = 1; indexts <= indexts; indexts++) {
                if (isTimeslotAvailableSorted(jumlahCourse, indexts, courseSorted, conflictMatrix, this.timeslot)) {
                    this.timeslot[courseSorted[jumlahCourse][0] - 1] = indexts;
                    break;
                }
            }
        }
        if (tampil) {
            System.out.println("Timeslot Scheduling: ");
            for (int i = 0; i < jumlahCourse; i++)
                System.out.println("Timeslot untuk course " + (i + 1) + " adalah timeslot " + timeslot[i]);
        }
        return this.timeslot;
    }

    public void getTimeslot() {
        //sort timeslot secara ascending
        Arrays.sort(timeslot);
        System.out.print("");
        System.out.println("Minimal Timeslots: " + timeslot[timeslot.length - 1]);

    }

    public boolean isConflicted() {
        for (int i = 0; i < this.timeslot.length; i++) {
            for (int j = i; j < this.timeslot.length; j++) {
                if (timeslot[i] == timeslot[j]) {
                    int course1 = i;
                    int course2 = j;
                    if (this.conflictMatrix[course1][course2] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}