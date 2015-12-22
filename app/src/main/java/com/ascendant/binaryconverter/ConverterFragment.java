package com.ascendant.binaryconverter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by AJ on 3/31/15.
 */
public class ConverterFragment extends Fragment{

    int base = 2;

    public ConverterFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_converter,parent,false);

        EditText editText = (EditText)v.findViewById(R.id.editText);
        EditText editText2 = (EditText)v.findViewById(R.id.editext2);

        final TextView textView = (TextView)v.findViewById(R.id.textView);

        editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL) {
                    String text = v.getText().toString();
                    try {
                        int i = Integer.parseInt(text);
                        if (i <= 10 && i >= 0){
                            base = i;
                            textView.setText("Base is now " + base + ".");
                        } else if (i < 0) {
                            base = -i;
                            textView.setText("Base is now " + base + ".");


                        } else if (i > 10) {
                            textView.setText("Too high!");
                        }

                    } catch (NumberFormatException e){
                        textView.setText("That's no number!");

                    }
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);//hides keyboard
                    return true;
                }

                return false;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL) {
                    String text = v.getText().toString();
                    try {
                        int i = Integer.parseInt(text);
                        int newi = changeBase(i, base);
                        textView.setText(text + " = " + newi);
                    } catch (NumberFormatException e){
                        textView.setText("That's no number!");

                    }
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    private int changeBase(int q, int n){
        int r;
        int i = 0;
        int binaryq = 0;
        while (q > 0) {
            r = q%n;//get remainder
            binaryq = binaryq + (r*((int)Math.pow(10,i))); //add remainder
            i++;
            q = q/n;

        }
        return binaryq;
    }
}
