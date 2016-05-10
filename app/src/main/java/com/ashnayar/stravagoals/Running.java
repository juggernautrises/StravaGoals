package com.ashnayar.stravagoals;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Running.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Running#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Running extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    double run_diff, run_goal, run_current;
    private Athlete athlete;


    private OnFragmentInteractionListener mListener;

    public Running() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param a Parameter 1.
     * @return A new instance of fragment Running.
     */
    // TODO: Rename and change types and number of parameters
    public static Running newInstance(Athlete a) {
        Running fragment = new Running();
        Bundle args = new Bundle();
        args.putDouble("run_goal",a.getRunGoal());
        args.putDouble("run_current",a.getYtd_run());
        args.putDouble("run_diff", a.getRunDiff());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            run_goal = getArguments().getDouble("run_goal");
            run_current = getArguments().getDouble("run_current");
            run_diff = getArguments().getDouble("run_diff");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root_view = inflater.inflate(R.layout.fragment_running, container, false);
        TextView goal_txt = (TextView) root_view.findViewById(R.id.goal_running);
        TextView current_txt = (TextView) root_view.findViewById(R.id.current_running);
        TextView diff_txt = (TextView) root_view.findViewById(R.id.diff_running);

        goal_txt.setText(Athlete.formatDistance(run_goal));
        current_txt.setText(Athlete.formatDistance(run_current));
        diff_txt.setText(Athlete.formatDistance(run_diff));
        if(run_diff >=0){
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
