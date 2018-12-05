package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public class JokeFragmentPaid extends Fragment {

    public final static String JOKE_KEY = "JOKE";

    public JokeFragmentPaid() {
        // Required empty public constructor
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//
//        assert bundle != null;
//        String joke = bundle.getString(JOKE_KEY);
//        TextView jokeTextView = rootView.findViewById(R.id.paid_joke);
//        if (joke != null && joke.length() != 0) {
//            jokeTextView.setText(joke);
//        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke_paid, container, false);
    }
}
