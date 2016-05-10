package com.ashnayar.stravagoals;

/**
 * Created by ashoknayar on 5/3/16.
 */


import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;


public class Athlete {
    private JSONObject ytd_ride_totals ;
    private JSONObject ytd_run_totals;
    private double ytd_run, ytd_ride, ytd_climb;
    private double YEARLY_CYCLE_GOAL = 10000;
    private double YEARLY_RUN_GOAL = 500;
    private double YEARLY_CLIMB_GOAL = 500000;
    private double weekly_climb_goal, weekly_run_goal, weekly_cycle_goal;
    private double run_est, climb_est, cycle_est;
    private boolean loaded = false;

    public Athlete(String api_string){
        Calendar now = Calendar.getInstance(Locale.UK);
        now.setFirstDayOfWeek(Calendar.MONDAY);
        now.setMinimalDaysInFirstWeek(4);
        int week_no = now.get(Calendar.WEEK_OF_YEAR);

        weekly_climb_goal = (YEARLY_CLIMB_GOAL / 52 )* week_no;
        weekly_run_goal = (YEARLY_RUN_GOAL / 52) * week_no;
        weekly_cycle_goal = (YEARLY_CYCLE_GOAL /52) * week_no;

        try {
            JSONObject athlete_stats = new JSONObject(api_string);
            ytd_ride_totals = (JSONObject) athlete_stats.get("ytd_ride_totals");
            ytd_run_totals = (JSONObject) athlete_stats.get("ytd_run_totals");

            //Log.e("smashy", ytd_ride_totals.get("distance"));

            ytd_ride = Double.parseDouble(ytd_ride_totals.getString("distance"));

            ytd_run = Double.parseDouble(ytd_run_totals.getString("distance"));
            ytd_climb = Double.parseDouble(ytd_ride_totals.getString("elevation_gain"));
        }catch (Exception e){
            Log.d("SmashyError", e.getMessage());
        }



    }
    public Athlete() {


    }

    // Get YTD climb

    public void setYtd_ride(){
        ytd_ride = 0;
    }
    public boolean getLoadState(){
        return loaded;
    }
    public double getYtd_climb() {
        return ytd_climb * GoalData.meters_to_feet;
    }

    public double getYtd_ride() {
        //Log.d("smashy", "getYtd_ride "+String.valueOf(ytd_ride));

        return (ytd_ride * GoalData.meters_to_miles);
    }

    public double getYtd_run() {
        return ytd_run * GoalData.meters_to_miles;
    }

    public static String formatDistance(double distance){
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(distance);

    }

    public  double getCycleGoal(){
        return weekly_cycle_goal;
    }

    public double getRunGoal(){
        return weekly_run_goal;
    }

    public double getClimbGoal(){
        return weekly_climb_goal;
    }
    public double getClimbDiff(){
        Log.d("smashyClimb",String.valueOf(ytd_climb));
        Log.d("smashyClimbWeek",String.valueOf(ytd_climb));
        return (ytd_climb * GoalData.meters_to_feet ) - weekly_climb_goal;
    }

    public double getRideDiff() {
        return (ytd_ride  * GoalData.meters_to_miles) - weekly_cycle_goal;
    }

    public double getRunDiff(){
        return (ytd_run * GoalData.meters_to_miles)- weekly_run_goal;
    }


    // Get YTD run

    // Get YTD ride

    // Get Run Goal for week

    // Get Ride Goal for week

    // Get Climb Goal for week


}
