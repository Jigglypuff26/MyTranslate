package hfad.com.mytranslate.Fragments.non_services;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hfad.com.mytranslate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonGPS extends Fragment {


    public NonGPS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_g, container, false);
    }

}
