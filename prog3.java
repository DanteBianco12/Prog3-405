// Dante Bianco
// March 2023
// CSCI 405
// Programming Assignment 3

import java.util.*;
//import java.io.*;

public class prog3 {

    static class Ride {
        String name;
        int startTime;
        int endTime;
        int duration;

        // Ride object
        public Ride(String name, int startTime, int endTime){
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
            this.duration = endTime - startTime;
        }
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Requires an input string!");
            System.exit(1);
        }

        String input  = args[0];
        // Scanner for user input
        Scanner s = new Scanner(input);
        // Empty List to store the selected rides
        List<Ride> rides = new ArrayList<>();

        while(s.hasNext()){
            String[] rideSpec = s.next().split(":|\\[|\\)|,");
            String name = rideSpec[0];
            int startTime = Integer.parseInt(rideSpec[2]);
            int endTime = Integer.parseInt(rideSpec[3]);
            rides.add(new Ride(name, startTime, endTime));
        }
        s.close();

        // Sort rides in non-decreasing order of their endtime
        rides.sort((r1, r2) -> r1.endTime - r2.startTime);

        // Select Rides and Calculate the total time spent on rides
        List<String> selectedRides = new ArrayList<>();
        int totalTime = 0;
        int lastEndTime = 0;
        for(Ride ride : rides){
            if(ride.startTime >= lastEndTime){
                selectedRides.add(ride.name);
                totalTime += ride.duration;
                lastEndTime = ride.endTime;
            }
        }

        // Output Results
        System.out.println("Selected Rides: " + String.join(", ", selectedRides));
        System.out.println("Total Time Spent: " + totalTime);
    }
}