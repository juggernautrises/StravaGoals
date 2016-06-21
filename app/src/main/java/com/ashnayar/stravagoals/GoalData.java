package com.ashnayar.stravagoals;

/**
 * Created by ashoknayar on 4/26/16.
 */
public class GoalData {

    public static String strava_id = ""; // Fill in strava user id
    public static String auth_key =  ""; // Fill in api key from strava
    public static double miles_to_km = 1.60934;
    public static double miles_to_meters = 1609.344;
    public static double km_to_miles = 0.621371;
    public static double meters_to_miles = 0.000621371192;
    public static double meters_to_feet = 3.28084;
    public static double feet_to_meters = 0.3048;
    public static double feet_to_miles = 0.000189394;
    public static double miles_to_feet = 5280;
    public static double km_to_feet = 3280.84;

    private double km, miles, feet, meters;
    public GoalData(String distance_type, double distance) {
        if(distance_type.equals("km")){
            km = distance;
            miles = distance * km_to_miles;
            feet = distance * km_to_feet;
            meters = distance * 1000.0;
        }else if(distance_type.equals("miles")){
            miles = distance;
            km = distance * miles_to_km;
            feet = distance * miles_to_feet;
            meters = distance * miles_to_meters;
        }else if(distance_type.equals("feet")){
            feet = distance;
            miles = distance * feet_to_miles;
            meters = distance * feet_to_meters;
            km  =  meters/1000.0;
        }else if (distance_type.equals("meters")){
            meters = distance;
            miles = distance * meters_to_miles;
            feet = distance * meters_to_feet;
            km = distance/1000.0;
        }else
        {
            km = miles = feet = meters = 0;
        }
    }

    public double getFeet() {
        return feet;
    }

    public double getKm() {
        return km;
    }

    public double getMeters() {
        return meters;
    }

    public double getMiles() {
        return miles;
    }
}

