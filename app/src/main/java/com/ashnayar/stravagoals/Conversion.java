package com.ashnayar.stravagoals;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Conversion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Conversion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Conversion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Conversion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Conversion.
     */
    // TODO: Rename and change types and number of parameters
    public static Conversion newInstance() {
        Conversion fragment = new Conversion();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.fragment_conversion, container, false);
        final Spinner dist_spinner = (Spinner) root_view.findViewById(R.id.distance_spinner);
        final TextView meter_convert =  (TextView) root_view.findViewById(R.id.meter_convert);
        final TextView km_convert =  (TextView) root_view.findViewById(R.id.km_convert);
        final TextView feet_convert =  (TextView) root_view.findViewById(R.id.feet_convert);
        final TextView miles_convert =  (TextView) root_view.findViewById(R.id.miles_convert);
        ArrayList<String> dist_opts = new ArrayList<>();
        dist_opts.add("feet");
        dist_opts.add("km");
        dist_opts.add("meters");
        dist_opts.add("miles");
        ArrayAdapter<String> dist_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dist_opts);
        dist_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dist_spinner.setAdapter(dist_adapter);

        Button convert_btn = (Button) root_view.findViewById(R.id.convert_btn);
        final EditText input_txt = (EditText) root_view.findViewById(R.id.input_distance);

        // TODO: refactor into a function to be called after spinner is selected

        dist_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String distance_str = input_txt.getText().toString();
                if (!distance_str.equals("")) {
                    String selected_distance = dist_spinner.getSelectedItem().toString();
                    //Toast.makeText(getActivity().getApplicationContext(),selected_distance, Toast.LENGTH_SHORT).show();
                    double distance = Double.parseDouble(distance_str);
                    GoalData gd = new GoalData(selected_distance, distance);
                    meter_convert.setText(Athlete.formatDistance(gd.getMeters()));
                    km_convert.setText(Athlete.formatDistance(gd.getKm()));
                    miles_convert.setText(Athlete.formatDistance(gd.getMiles()));
                    feet_convert.setText(Athlete.formatDistance(gd.getFeet()));
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        convert_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String distance_str = input_txt.getText().toString();
                if (!distance_str.equals("")) {

                    String selected_distance = dist_spinner.getSelectedItem().toString();
                    double distance = Double.parseDouble(distance_str);
                    if (selected_distance.equals("feet")){
                        double ft_to_meters = distance * GoalData.feet_to_meters;
                        double ft_to_km = ft_to_meters/1000.0;
                        double ft_to_miles = distance * GoalData.feet_to_miles;
                        meter_convert.setText(Athlete.formatDistance(ft_to_meters));
                        km_convert.setText(Athlete.formatDistance(ft_to_km));
                        miles_convert.setText(Athlete.formatDistance(ft_to_miles));
                        feet_convert.setText(Athlete.formatDistance(distance));
                    } else if (selected_distance.equals("meters")){
                        double meters_to_ft = distance * GoalData.meters_to_feet;
                        double meters_to_km =  distance/1000.0;
                        double meters_to_miles = distance * GoalData.meters_to_miles;
                        meter_convert.setText(Athlete.formatDistance(distance));
                        km_convert.setText(Athlete.formatDistance(meters_to_km));
                        miles_convert.setText(Athlete.formatDistance(meters_to_miles));
                        feet_convert.setText(Athlete.formatDistance(meters_to_ft));
                    } else if (selected_distance.equals("km")){
                        double km_to_feet = distance * GoalData.km_to_feet;
                        double km_to_miles = distance * GoalData.km_to_miles;
                        double km_to_meters = distance * 1000.0;
                        meter_convert.setText(Athlete.formatDistance(km_to_meters));
                        km_convert.setText(Athlete.formatDistance(distance));
                        miles_convert.setText(Athlete.formatDistance(km_to_miles));
                        feet_convert.setText(Athlete.formatDistance(km_to_feet));

                    } else if (selected_distance.equals("miles")){
                        double miles_to_feet = distance * GoalData.miles_to_feet;
                        double miles_to_meters = distance * GoalData.miles_to_meters;
                        double miles_to_km = distance * GoalData.miles_to_km;
                        meter_convert.setText(Athlete.formatDistance(miles_to_meters));
                        km_convert.setText(Athlete.formatDistance(miles_to_km));
                        miles_convert.setText(Athlete.formatDistance(distance));
                        feet_convert.setText(Athlete.formatDistance(miles_to_feet));
                    }
                }
            }
        });

        return root_view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
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
