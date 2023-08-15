package com.aftab.ratecalculator.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aftab.ratecalculator.R;
import com.aftab.ratecalculator.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public EditText edt1;
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
    LinearLayout resultLayout;

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
        txtLength = root.findViewById(R.id.txtLength);
        resultLayout = root.findViewById(R.id.resultLayout);

// btnResult
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
                    } else {
                        edtRate.requestFocus();
                    }


                } else {
                    edtRate.requestFocus();
                    edtRate.clearFocus();

                    Toast.makeText(getContext(), "Result", Toast.LENGTH_SHORT).show();

                    int num1 = Integer.parseInt(edt1.getText().toString());
                    int num2 = Integer.parseInt(edt2.getText().toString());
                    int num3 = Integer.parseInt(edt3.getText().toString());
                    String str = Integer.toString((num1 + num2 + num3));
                    txtRate.setText(str);

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
                edtRate.setText("");
                edt1.requestFocus();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}