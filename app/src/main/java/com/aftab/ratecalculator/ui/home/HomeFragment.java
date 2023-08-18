package com.aftab.ratecalculator.ui.home;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aftab.ratecalculator.R;
import com.aftab.ratecalculator.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edtRate;
    Button btnResult;
    Button btnClear;
    TextView txtRate;
    TextView txtSize;
    TextView txtWeight;
    TextView txtPly;
    TextView txtDeckle;
    TextView txtLength;
    TextView txtSpinnerPly;
    TextView txtSpinnerGSm;
    LinearLayout resultLayout;
    private Spinner plySpinner, gsmSpinner;
    private ArrayAdapter<CharSequence> plyAdapter, gsmAdapter;
    String ply = "";
    String selectedPly = "";
    String selectedGsm = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        edt1 = root.findViewById(R.id.length);
        edt2 = root.findViewById(R.id.width);
        edt3 = root.findViewById(R.id.height);
        btnResult = root.findViewById(R.id.resultbtn);
        btnClear = root.findViewById(R.id.btnClear);
        edtRate = root.findViewById(R.id.rate);

        txtRate = root.findViewById(R.id.txtPrice);
        txtSize = root.findViewById(R.id.txtsize);
        txtWeight = root.findViewById(R.id.txtWeight);
        txtPly = root.findViewById(R.id.txtPly);
        txtDeckle = root.findViewById(R.id.txtDeckle);

        txtSpinnerPly = root.findViewById(R.id.plySpinnerText);
        txtSpinnerGSm = root.findViewById(R.id.gsmSpinnerText);
        txtLength = root.findViewById(R.id.txtLength);

        resultLayout = root.findViewById(R.id.resultLayout);

        plySpinner = root.findViewById(R.id.plySpinner);
        gsmSpinner = root.findViewById(R.id.gsmSpinner);

        // Adaper
        plyAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_plyGsm, R.layout.spinner_layout);
        plyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plySpinner.setAdapter(plyAdapter);


        plySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedPly = plySpinner.getSelectedItem().toString();
                ply = (selectedPly);
                int parentID = parent.getId();
                if (parentID == R.id.plySpinner) {
                    switch (selectedPly) {
                        case "Select Ply":
                            gsmAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_gsm, R.layout.spinner_layout);
                            break;
                        case "3":
                            gsmAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_3ply, R.layout.spinner_layout);
                            break;
                        case "5":
                            gsmAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_5ply, R.layout.spinner_layout);
                            break;
                        case "7":
                            gsmAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_7ply, R.layout.spinner_layout);
                            break;
                        case "9":
                            gsmAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_9ply, R.layout.spinner_layout);
                            break;
                    }
                    gsmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    gsmSpinner.setAdapter(gsmAdapter);
                    gsmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedGsm = gsmSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//// btnResult
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0 || edtRate.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
                    if (edt1.getText().toString().length() == 0) {
                        edt1.requestFocus();
                    } else if (edt2.getText().toString().length() == 0) {
                        edt2.requestFocus();
                    } else if (edt3.getText().toString().length() == 0) {
                        edt3.requestFocus();
                    } else if (edtRate.getText().toString().length() == 0) {
                        edtRate.requestFocus();
                    }


                } else {
                    edt1.clearFocus();
                    edt2.clearFocus();
                    edt3.clearFocus();
                    edtRate.clearFocus();
                    hideKeyboard();
                    if (selectedPly.equals("Select Ply")) {
                        Toast.makeText(getContext(), "Select Ply", Toast.LENGTH_SHORT).show();
                        txtSpinnerPly.setError("Ply is required");
                        txtSpinnerPly.requestFocus();
                    } else if (selectedGsm.equals("Select GSM")) {

                        Toast.makeText(getContext(), "Select Gsm", Toast.LENGTH_SHORT).show();
                        txtSpinnerGSm.setError("GSM is required");
                        txtSpinnerGSm.requestFocus();
                        txtSpinnerPly.setError(null);


                    } else {
                        txtSpinnerGSm.setError(null);
                        txtSpinnerPly.setError(null);


                        float length = Float.parseFloat(edt1.getText().toString());
                        float width = Float.parseFloat(edt2.getText().toString());
                        float height = Float.parseFloat(edt3.getText().toString());
                        int price = Integer.parseInt(edtRate.getText().toString());

                        Ids id = new Ids(txtRate, txtSize, txtWeight, txtPly, txtDeckle, txtLength);

                        int gsm = Integer.parseInt(selectedGsm);
                        Rate rate = new Rate(id, length, width, height, gsm, price);
                        rate.calulate();

                        GSM gsm1 = new GSM(Integer.parseInt(selectedPly), Integer.parseInt(selectedGsm));

                        txtPly.setText(selectedPly + "ply, (" + gsm1.getGSM() + ")");

                        Toast.makeText(getContext(), "Result", Toast.LENGTH_SHORT).show();

                        //To hide keyboard


                    }


                }
            }
        });

        //btn clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRate.setText("");
                txtSize.setText("");
                txtWeight.setText("");
                txtPly.setText("");
                txtDeckle.setText("");
                txtLength.setText("");
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                edt1.requestFocus();
                enableKeyboard();
            }
        });

//Focus change

        edt1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    edt2.requestFocus();
                }
                return false;
            }
        });
        edt2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    edt3.requestFocus();
                }
                return false;
            }
        });
        edt3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    edtRate.requestFocus();
                }
                return false;
            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Sending...", Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }
    public void enableKeyboard(){
        View view = getView(); // Replace with your target input view
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                }
            }, 200  ); // Delay in milliseconds
        }
    }

    public void hideKeyboard() {
        View view = getView();
        if (view != null) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
