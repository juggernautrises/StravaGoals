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

    double ride_diff, ride_goal, ride_current;
    private Athlete athlete;
    private OnFragmentInteractionListener mListener;

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

        goal_txt.setText(Athlete.formatDistance(ride_goal));
        current_txt.setText(Athlete.formatDistance(ride_current));
        diff_txt.setText(Athlete.formatDistance(ride_diff));
        if(ride_diff >=0){
            diff_txt.setTextColor(Color.GREEN);
        }else{
            diff_txt.setTextColor(Color.RED);
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

        ride_goal = a.getCycleGoal();
        ride_current = a.getYtd_ride();
        ride_diff = a.getRideDiff();

        goal_txt.setText(Athlete.formatDistance(ride_goal));
        current_txt.setText(Athlete.formatDistance(ride_current));
        diff_txt.setText(Athlete.formatDistance(ride_diff));
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
