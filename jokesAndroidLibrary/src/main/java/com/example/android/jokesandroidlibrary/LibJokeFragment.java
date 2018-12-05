package com.example.android.jokesandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LibJokeFragment extends Fragment {

    private final static String JOKE_KEY = "joke";

    public LibJokeFragment() {
        // mandatory empty constructor
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lib_fragment_joke, container, false);

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        assert bundle != null;
        String joke = bundle.getString(JOKE_KEY);
        TextView jokeTextView = rootView.findViewById(R.id.joke_text_view);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }
        return rootView;
    }
}
