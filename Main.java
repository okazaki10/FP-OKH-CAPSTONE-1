import java.util.Scanner;
import java.io.IOException;

public class Main {
    static String fileName[][] = { { "car-s-91", "CAR91" }, { "car-f-92", "CAR92" }, { "ear-f-83", "EAR83" },
            { "hec-s-92", "HEC92" }, { "kfu-s-93", "KFU93" }, { "lse-f-91", "LSE91" }, { "pur-s-93", "PUR93" },
            { "rye-s-93", "RYE92" }, { "sta-f-83", "STA83" }, { "tre-s-92", "TRE92" }, { "uta-s-92", "UTA92" },
            { "ute-s-92", "UTE92" }, { "yor-f-83", "YOR83" } };;
    static int[][] conflictMatrix, sortedCourse, weightedClashMatrix, sortedWeightedCourse;
    static int timeslot[];

    public static void main(String[] args) throws IOException {
        System.out.println("");
        System.out.println("Daftar Dataset: ");

        for (int i = 0; i < fileName.length; i++) {
            System.out.println(i + 1 + ". " + fileName[i][1]);
        }
        System.out.println(fileName.length + 1 + ". tampilkan semua");
        System.out.print("\nPilih dataset  : ");
        Scanner input = new Scanner(System.in);

        int dataset = input.nextInt();
        // jika input tidak sama dengan 14 maka akan menampilkan dataset yang dipilih
        // dengan menampilkan semua timeslot
        if (dataset != 14) {
            jalankan(dataset, fileName[dataset - 1][0], fileName[dataset - 1][1], true);
        } else {
            // jika input sama dengan 14 maka akan menampilkan semua dataset tanpa
            // menampilkan timeslot
            for (int i = 0; i < fileName.length; i++) {
                jalankan(dataset, fileName[i][0], fileName[i][1], false);
            }
        }
    }

    public static void jalankan(int dataset, String filePilihanInput, String namadataset, boolean tampil)
            throws IOException {
        long startTime = System.nanoTime();
        // jika tampil = true maka tampilkan nama dataset
        if (tampil) {
            System.out.println("\n================================================\n");
        }

        CourseData course = new CourseData(filePilihanInput);
        course.tampil = tampil;

        int jumlahCourse = course.getNumberOfCourses();
        conflictMatrix = new int[jumlahCourse][jumlahCourse];

        // mengambil conflict_matrix:
        conflictMatrix = course.getConflictMatrix();
        System.out.println(" ");

        // mengambil weightedClashMatrix:
        weightedClashMatrix = course.getWeightedClashMatrix();
        System.out.println(" ");

        // mengambil hasil sorting largest degree:
        sortedCourse = course.sortByDegree(conflictMatrix, jumlahCourse);
        System.out.println("\n================================================\n");

        // melakukan scheduling dengan largest degree order
        ExamScheduling sch = new ExamScheduling(conflictMatrix);
        sch.tampil = tampil;
        timeslot = sch.scheduleByDegree(sortedCourse, jumlahCourse);

        // menampilkan semua informasi
        System.out.println("Dataset yang dipilih : " + namadataset);
        System.out.println("Ada konflik? : " + (sch.isConflicted()? "Ya" : "Tidak"));
        System.out.println("Penalty : " + Penalty.countPenalty(course.getStudentData(), timeslot));
        sch.getTimeslot();
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Waktu eksekusi dalam milliseconds : " + timeElapsed / 1000000);
    }
}
