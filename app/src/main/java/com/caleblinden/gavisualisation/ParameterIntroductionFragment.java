package com.caleblinden.gavisualisation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParameterIntroductionFragment extends Fragment {

    private static final String GA_PARAMETER_TYPE = "type";
    private int gaParameterType;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.parameter_type)
    TextView parameterTitle;
    @BindView(R.id.parameter_explanation)
    TextView parameterExplanation;
    @BindView(R.id.parameter_input)
    EditText parameterValue;
    @BindView(R.id.btn_proceed)
    Button btnProceed;

    private static final String INPUT_EXTRA = "INPUT_EXTRA";

    public static ParameterIntroductionFragment newInstance(int position) {
        ParameterIntroductionFragment fragment = new ParameterIntroductionFragment();
        Bundle args = new Bundle();
        args.putInt(GA_PARAMETER_TYPE, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gaParameterType = getArguments().getInt(GA_PARAMETER_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupView();
    }

    private void setupView() {
        switch (gaParameterType) {
            case 0:
                setParameters(getResources().getString(R.string.genetic_algorithm_population_size),
                        getResources().getString(R.string.genetic_algorithm_population_size_description));
                break;
            case 1:
                setParameters(getResources().getString(R.string.genetic_algorithm_mutation_rate),
                        getResources().getString(R.string.genetic_algorithm_mutation_rate_description));
                break;
            case 2:
                setParameters(getResources().getString(R.string.genetic_algorithm_uniform_rate),
                        getResources().getString(R.string.genetic_algorithm_uniform_rate_description));
                break;
            case 3:
                setParameters(getResources().getString(R.string.genetic_algorithm_tournament_size),
                        getResources().getString(R.string.genetic_algorithm_tournament_size_description));
                break;
            case 4:
                setParameters(getResources().getString(R.string.genetic_algorithm_elitism),
                        getResources().getString(R.string.genetic_algorithm_elitism_description));
                break;
            case 5:
                setParameters(getResources().getString(R.string.solution_title),
                        getResources().getString(R.string.genetic_algorithm_population_size_description));
                btnProceed.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setParameters(String title, String explanation) {
        parameterTitle.setText(title);
        parameterExplanation.setText(explanation);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Get user input email from the edit text box.
        String inputValue = parameterValue.getText().toString();

        if (outState != null) {
            // Save user email instance variable value in bundle.
            outState.putString(INPUT_EXTRA, inputValue);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            // Retrieve the user email value from bundle.
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
