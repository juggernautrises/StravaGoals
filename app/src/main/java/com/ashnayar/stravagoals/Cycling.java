package com.ashnayar.stravagoals;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cycling.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cycling#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cycling extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String time_update = "";
    double ride_diff, ride_goal, ride_current, next_week_diff;
    private Athlete athlete;
    private OnFragmentInteractionListener mListener;
    String daily_average, current_week, next_week;
    public Cycling() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param a Parameter 1.
     * @return A new instance of fragment Cycling.
     */
    // TODO: Rename and change types and number of parameters
    public static Cycling newInstance(Athlete a) {
        Cycling fragment = new Cycling();
        Bundle args = new Bundle();
        args.putDouble("ride_goal",a.getCycleGoal());
        args.putDouble("ride_current",a.getYtd_ride());
        args.putDouble("ride_diff", a.getRideDiff());
        args.putDouble("next_week_diff", a.getNextWeekRideDiff());
        args.putString("update_time", a.getUpdatedTime());
        args.putString("daily_average",a.getDailyAverage());
        args.putString("current_week",Athlete.getCurrentWeek());
        args.putString("next_week", Athlete.getNextWeek());

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ride_goal = getArguments().getDouble("ride_goal");
            ride_current = getArguments().getDouble("ride_current");
            ride_diff = getArguments().getDouble("ride_diff");
            next_week_diff = getArguments().getDouble("next_week_diff");
            time_update = getArguments().getString("update_time");
            daily_average = getArguments().getString("daily_average");
            current_week = getArguments().getString("current_week");
            next_week = getArguments().getString("next_week");
            Log.e("smashyGoal", String.valueOf(ride_goal));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("smashyCalledView", "view");
        View root_view = inflater.inflate(R.layout.fragment_cycling, container, false);
        TextView goal_txt = (TextView) root_view.findViewById(R.id.goal_cycling);
        TextView current_txt = (TextView) root_view.findViewById(R.id.current_cycling);
        TextView diff_txt = (TextView) root_view.findViewById(R.id.diff_cycling);
        TextView next_diff = (TextView) root_view.findViewById(R.id.next_week_diff);
        TextView time_txt = (TextView) root_view.findViewById(R.id.cycling_time_update);
        TextView current_week_txt = (TextView)root_view.findViewById(R.id.current_week_txt);
        TextView next_week_txt = (TextView)root_view.findViewById(R.id.next_week_txt);
        TextView days_remain_txt = (TextView)root_view.findViewById(R.id.days_remaining);
        TextView avg_txt =  (TextView)root_view.findViewById(R.id.avg_per_day_txt);

        goal_txt.setText(Athlete.formatDistance(ride_goal));
        current_txt.setText(Athlete.formatDistance(ride_current));
        diff_txt.setText(Athlete.formatDistance(ride_diff));
        next_diff.setText(Athlete.formatDistance(next_week_diff));
        current_week_txt.setText("Week "+current_week);
        next_week_txt.setText("Week "+next_week);
        time_txt.setText(time_update);
        days_remain_txt.setText("Days Remaining: "+Athlete.getDaysRemaining());
        avg_txt.setText(daily_average);

        if(ride_diff >=0){
            diff_txt.setTextColor(Color.GREEN);
        }else{
            diff_txt.setTextColor(Color.RED);
        }

        if(next_week_diff >=0){
            next_diff.setTextColor(Color.GREEN);
        }else{
            next_diff.setTextColor(Color.RED);
        }

        return root_view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void refreshData(Athlete a){
        View root_view = getView();
        //View root_view = inflater.inflate(R.layout.fragment_cycling, container, false);
        TextView goal_txt = (TextView) root_view.findViewById(R.id.goal_cycling);
        TextView current_txt = (TextView) root_view.findViewById(R.id.current_cycling);
        TextView diff_txt = (TextView) root_view.findViewById(R.id.diff_cycling);
        TextView update_txt = (TextView) root_view.findViewById(R.id.cycling_time_update);

        ride_goal = a.getCycleGoal();
        ride_current = a.getYtd_ride();
        ride_diff = a.getRideDiff();
        time_update = a.getUpdatedTime();
        Log.d("smashyTime", time_update);
        goal_txt.setText(Athlete.formatDistance(ride_goal));
        current_txt.setText(Athlete.formatDistance(ride_current));
        diff_txt.setText(Athlete.formatDistance(ride_diff));
        update_txt.setText(time_update);

        if(ride_diff >=0){
            diff_txt.setTextColor(Color.GREEN);
        }else{
            diff_txt.setTextColor(Color.RED);
        }

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
