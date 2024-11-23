// Written by Siddh Patel
import java.io.*;
import java.util.*;

public class SheepShearingScheduler {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String filename;

        // ask for file name.
        System.out.print("Enter the sheep schedule file name: ");
        filename = scanner.nextLine();

        // read sheep data from file and initialize heap.
        List<Sheep> sheepList = readSheepFile(filename);
        MinHeap<Sheep> waitingHeap = new MinHeap<>();

        int currentTime = 0; // start at time 0.
        List<Sheep> shearingSchedule = new ArrayList<>();

        // process sheep while there are sheep to shear.
        while (!sheepList.isEmpty() || !waitingHeap.isEmpty()) {
            // add all sheep that have arrived to the heap.
            Iterator<Sheep> iterator = sheepList.iterator();
            while (iterator.hasNext()) {
                Sheep sheep = iterator.next();
                if (sheep.arrivalTime <= currentTime) {
                    waitingHeap.add(sheep);
                    iterator.remove();
                }
            }

            if (!waitingHeap.isEmpty()) {
                Sheep nextSheep = waitingHeap.remove();
                shearingSchedule.add(nextSheep);
                currentTime += nextSheep.shearTime;
                System.out.println(nextSheep);
            } else {
                currentTime++; // if no sheep are ready, just increment time.
            }
        }

        // print final schedule.
        System.out.println("\nShearing Schedule:");
        for (Sheep sheep : shearingSchedule) {
            System.out.println(sheep);
        }

        // ask user if they want to run again.
        System.out.print("Would you like to run again? (yes/no): ");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes")) {
            main(args); // recursively call the main method to restart the program.
        } else {
            System.out.println("Goodbye!");
        }

        scanner.close();
    }

    // reads the sheep scheduling file and returns a list of Sheep objects.
    public static List<Sheep> readSheepFile(String filename) throws IOException {
        List<Sheep> sheepList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            String name = parts[0];
            int shearTime = Integer.parseInt(parts[1]);
            int arrivalTime = Integer.parseInt(parts[2]);
            sheepList.add(new Sheep(name, shearTime, arrivalTime));
        }
        reader.close();
        return sheepList;
    }
}