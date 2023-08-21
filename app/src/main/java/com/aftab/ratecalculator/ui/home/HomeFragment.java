package com.aftab.ratecalculator.ui.home;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.aftab.ratecalculator.MainActivity;
import com.aftab.ratecalculator.R;
import com.aftab.ratecalculator.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    EditText edt1;
    EditText edt2;
    EditText edt3;
    EditText edtRate;
    Button btnResult;
    Button btnClear;
    Button shareButton;
    TextView txtRate;
    TextView txtSize;
    TextView txtWeight;
    TextView txtPly;
    TextView txtDeckle;
    TextView txtLength;
    TextView txtSpinnerPly;
    TextView txtSpinnerGSm;
    TextView shareText;
    LinearLayout resultLayout;
    private Spinner plySpinner, gsmSpinner;
    private ArrayAdapter<CharSequence> plyAdapter, gsmAdapter;
    String ply = "";
    String selectedPly = "";
    String selectedGsm = "";
    ImageButton shareBtn;
    SwitchCompat toggle;

    boolean check = false;
    boolean toggleCheck = false;
    Date date;

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

        shareBtn = root.findViewById(R.id.imgBtn);
        toggle = root.findViewById(R.id.toggle);

        // Adaper
        plyAdapter = ArrayAdapter.createFromResource(getContext(), R.array.array_plyGsm, R.layout.spinner_layout);
        plyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plySpinner.setAdapter(plyAdapter);


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    toggleCheck = true;
                    Toast.makeText(getContext(), "On", Toast.LENGTH_SHORT).show();
                } else {
                    toggleCheck = false;
                    Toast.makeText(getContext(), "Of", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                            check = false;
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

                            if (selectedGsm.equals("Select GSM")) {
                                check = false;
                            }
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
                        check = false;
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
                        rate.calulate(toggleCheck);


                        GSM gsm1 = new GSM(Integer.parseInt(selectedPly), Integer.parseInt(selectedGsm));
                        txtPly.setText(selectedPly + "ply, (" + gsm1.getGSM() + ")");
                        Toast.makeText(getContext(), "Result", Toast.LENGTH_SHORT).show();
                        check = true;

                        //To hide keyboard


                    }


                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check) {
                    if (edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0 || edtRate.getText().toString().length() == 0) {
                        check = false;
                        Toast.makeText(getContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        float length = Float.parseFloat(edt1.getText().toString());
                        float width = Float.parseFloat(edt2.getText().toString());
                        float height = Float.parseFloat(edt3.getText().toString());
                        int price = Integer.parseInt(edtRate.getText().toString());

                        Ids id = new Ids(txtRate, txtSize, txtWeight, txtPly, txtDeckle, txtLength);

                        int gsm = Integer.parseInt(selectedGsm);

                        Rate rate = new Rate(id, length, width, height, gsm, price);
                        rate.calulate(toggleCheck);
                        float result = rate.result;

                        GSM gsm1 = new GSM(Integer.parseInt(selectedPly), Integer.parseInt(selectedGsm));

                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        String str = formatter.format(date);

                        String mobileNumber = getResources().getString(R.string.mobileNumber);
                        String companyName = getResources().getString(R.string.nav_header_title);


                        Toast.makeText(getContext(), "Sending...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");

                        Uri uri = Uri.parse(getResources().getString(R.string.link1_name));
                        String link = String.format(String.valueOf(uri));

                        String body = String.format("%s \n\n" +
                                "Price of box is Rs %.2f/-\n\nSize- %.2f*%.2f*%.2f\n\n%s ply,%s\n\nDated- %s\n\nMob number- %s\n\nCompany Deatails- %s", companyName, result, length, width, height, selectedPly, String.format(gsm1.getGSM()), str, mobileNumber, link);
                        String sub = "Details";

                        intent.putExtra(Intent.EXTRA_TEMPLATE, sub);
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                        startActivity(Intent.createChooser(intent, "Share"));


                    }


                } else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");

                    // For Company Name
                    String companyName = getResources().getString(R.string.nav_header_title);
                    String address = getResources().getString(R.string.address);
                    String gst = getResources().getString(R.string.GST);
                    String mobileNumber = getResources().getString(R.string.mobileNumber);


                    // For Company link
                    Uri uri = Uri.parse(getResources().getString(R.string.link1_name));
                    String link = String.format(String.valueOf(uri));

                    String sub = "Details";
                    String body = String.format(companyName + "\n\n" + gst + "\n\n" + address + "\n\n" + mobileNumber + "\n\n" + link);


                    intent.putExtra(Intent.EXTRA_TEMPLATE, sub);
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(intent, "Share"));
                    Toast.makeText(getContext(), "Sending", Toast.LENGTH_SHORT).show();
                }

            }
        });


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (check) {
//                    if (edt1.getText().toString().length() == 0 || edt2.getText().toString().length() == 0 || edt3.getText().toString().length() == 0 || edtRate.getText().toString().length() == 0) {
//                        check = false;
//                        Toast.makeText(getContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
//                    } else {
//                        float length = Float.parseFloat(edt1.getText().toString());
//                        float width = Float.parseFloat(edt2.getText().toString());
//                        float height = Float.parseFloat(edt3.getText().toString());
//                        int price = Integer.parseInt(edtRate.getText().toString());
//
//                        Ids id = new Ids(txtRate, txtSize, txtWeight, txtPly, txtDeckle, txtLength);
//
//                        int gsm = Integer.parseInt(selectedGsm);
//
//                        Rate rate = new Rate(id, length, width, height, gsm, price);
//                        rate.calulate();
//                        float result = rate.result;
//
//                        GSM gsm1 = new GSM(Integer.parseInt(selectedPly), Integer.parseInt(selectedGsm));
//
//
//                        Toast.makeText(getContext(), "Sending...", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.setType("text/plain");
//                        String body = String.format("XYZ Company \n\n" +
//                                "Price of box is %.2f\n\nSize- %.2f*%.2f*%.2f\n\n%s ply,%s\n\n", result, length, width, height, selectedPly, gsm1.getGSM());
//                        String sub = "";
//                        intent.putExtra(Intent.EXTRA_SUBJECT, sub);
//                        intent.putExtra(Intent.EXTRA_TEXT, body);
//                        startActivity(Intent.createChooser(intent, "Share"));
//
//
//                    }
//
//
//                }
//                Toast.makeText(getContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


        //btn clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
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
                txtDeckle.setBackgroundColor(Color.TRANSPARENT);
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

        // for clickable link from main home
        String customLinkText = "My Company";
        String fullText = "" + customLinkText + "";
        SpannableString spannableString = new SpannableString(fullText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click event here, like opening a website
                Uri uri = Uri.parse(getResources().getString(R.string.link2_name));
                String link2 = String.valueOf(uri);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link2));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true); // You can change this to false if you don't want underlines
                ds.setColor(ContextCompat.getColor(requireContext(), R.color.black));
            }
        };
        int startIndex = fullText.indexOf(customLinkText);
        int endIndex = startIndex + customLinkText.length();
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView customLinkTextView = root.findViewById(R.id.customLinkTextView);
        customLinkTextView.setText(spannableString);
        customLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        customLinkTextView.setHighlightColor(Color.TRANSPARENT); // Prevent highlighting when clicked

// for clickable link from main home -------------upto here


        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Your fragment initialization code here
    }


    public void enableKeyboard() {
        View view = getView(); // Replace with your target input view
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                }
            }, 200); // Delay in milliseconds
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

    // Back Pressed alert
//    private void showExitAlertDialog() {
//        ExitAlertDialogFragment dialogFragment = new ExitAlertDialogFragment();
//        dialogFragment.show(getChildFragmentManager(), "exit_dialog");
//    }

    // Custom dialog fragment for exit alert
//    public static class ExitAlertDialogFragment extends DialogFragment {
//        @NonNull
//        @Override
//        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("Exit Alert")
//                    .setMessage("Are you sure you want to exit?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            getActivity().finish(); // Close the activity or perform exit action
//                        }
//                    })
//                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Do nothing, just close the dialog
//                        }
//                    });
//            return builder.create();
//        }
//    }



}
