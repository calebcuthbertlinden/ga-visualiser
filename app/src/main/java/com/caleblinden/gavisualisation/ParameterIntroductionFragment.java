package com.caleblinden.gavisualisation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.GAParameters;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParameterIntroductionFragment extends Fragment {

    private static final String GA_PARAMETER_ID = "type";
    private int gaParameterId;

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
        args.putInt(GA_PARAMETER_ID, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gaParameterId = getArguments().getInt(GA_PARAMETER_ID);
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
        GAParameters param = GAParameters.fromValue(gaParameterId);
        if (param == null) {
            return;
        }
        switch (param) {
            case POPULATION_SIZE:
                setParameters(getResources().getString(R.string.genetic_algorithm_population_size),
                        getResources().getString(R.string.genetic_algorithm_population_size_description));
                parameterValue.addTextChangedListener(getTextWatcher());
                break;
            case MUTATION_RATE:
                setParameters(getResources().getString(R.string.genetic_algorithm_mutation_rate),
                        getResources().getString(R.string.genetic_algorithm_mutation_rate_description));
                parameterValue.addTextChangedListener(getTextWatcher());
                break;
            case UNIFORM_RATE:
                setParameters(getResources().getString(R.string.genetic_algorithm_uniform_rate),
                        getResources().getString(R.string.genetic_algorithm_uniform_rate_description));
                parameterValue.addTextChangedListener(getTextWatcher());
                break;
            case TOURNAMENT_SIZE:
                setParameters(getResources().getString(R.string.genetic_algorithm_tournament_size),
                        getResources().getString(R.string.genetic_algorithm_tournament_size_description));
                parameterValue.addTextChangedListener(getTextWatcher());
                break;
            case ELITISM:
                setParameters(getResources().getString(R.string.genetic_algorithm_elitism),
                        getResources().getString(R.string.genetic_algorithm_elitism_description));
                parameterValue.addTextChangedListener(getTextWatcher());
                break;
            case SOLUTION:
                setParameters(getResources().getString(R.string.solution_title),
                        getResources().getString(R.string.genetic_algorithm_population_size_description));
                btnProceed.setVisibility(View.VISIBLE);
                btnProceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onFragmentInteraction();
                    }
                });
                break;
        }
    }

    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            // The user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (c == null || c.equals("")) {
                    return;
                }

                if (c.toString().contains(".")) {
                    try {
                        setDoubleParamter(Double.valueOf(c.toString()));
                    } catch (Exception e) {}
                } else {
                    try {
                        setIntegerParameter(Integer.valueOf(c.toString()));
                    } catch (Exception e) {}
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }
        };
    }

    private void setDoubleParamter(Double parameter) {
        mListener.saveInputValue(gaParameterId, 0, parameter);
    }

    private void setIntegerParameter(Integer parameter) {
        mListener.saveInputValue(gaParameterId, parameter, 0);
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
        // Get user input and save it to the instance
        String inputValue = parameterValue.getText().toString();
        if (outState != null) {
            outState.putString(INPUT_EXTRA, inputValue);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {}
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
        void saveInputValue(int type, int intValue, double doubleValue);
    }
}
