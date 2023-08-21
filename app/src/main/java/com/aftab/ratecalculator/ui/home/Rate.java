package com.aftab.ratecalculator.ui.home;

import android.graphics.Color;

import com.aftab.ratecalculator.R;

public class Rate {
    float length;
    float width;
    float height;
    int gsm;
    int rate;
    float result;

    Ids ids;


    Rate(Ids ids, float length, float width, float height, int gsm, int rate) {
        this.ids = ids;
        this.length = length;
        this.width = width;
        this.height = height;
        this.gsm = gsm;
        this.rate = rate;
    }

    float deckleValidation(float deckle, boolean toggleCheck) {
        while (toggleCheck) {
            if (deckle > 0 && deckle <= 26) {
                ids.txtDeckle.setBackgroundResource(R.color.red);
                return (float) Math.ceil(deckle);
            }
        }
        if (deckle >= 25 && (int) deckle <= 51) {
            if ((int) deckle % 2 == 0) {
                ids.txtDeckle.setBackgroundColor(Color.TRANSPARENT);
                return (int) deckle + 2;
            } else {
                ids.txtDeckle.setBackgroundColor(Color.TRANSPARENT);
                return (int) deckle + 1;
            }
        }

        if (deckle > 0 && deckle <= 25) {
            ids.txtDeckle.setBackgroundColor(Color.TRANSPARENT);
            return (int) deckle + 1;
        }


//        System.out.println("Deckle is cut to cut");
        ids.txtDeckle.setBackgroundResource(R.color.red);
        return deckle;

    }

    float lengthValidation(float totalLength) {
        if (totalLength <= 72) {
            return totalLength += 2;
        } else if (totalLength <= 144) {
            return totalLength += 4;
        }
        return totalLength + 6;
    }

    public float finalresult(float Deckle, float totallength) {
        float result = 0;

        float b = Deckle * totallength * gsm;
        result = (float) (b / 1550.0 / 1000.0);
//        weight=result;
        result *= rate;

        return result;
    }

    public void calulate(boolean toggleCheck) {

        float deckle = width + height;
        float totalLength = (length * 2) + (width * 2);
        if (length < width) {
            ids.txtRate.setBackgroundResource(R.color.red);
            ids.txtRate.setText("Length is small");
//            System.out.println("length cannt be less than width");

        } else {

            if (deckle > 52) {
                ids.txtRate.setBackgroundResource(R.color.red);
                ids.txtRate.setText("Deckle is too large, Enter smaller Width or Height");
//                System.out.println("Enter samller Value");

            } else {
                deckle = deckleValidation(deckle, toggleCheck);
                totalLength = lengthValidation(totalLength);
                result = finalresult(deckle, totalLength);
                float weight = result / rate;

//            System.out.println(totalLength);
                ids.txtRate.setBackgroundColor(Color.TRANSPARENT);
//                ids.txtRate.setText(Float.toString(totalLength));
//                System.out.println(String.format("%.2f", result));
                ids.txtRate.setText(String.format("Price of the box is %.2f/-", result));

                ids.txtSize.setText(String.format("Size - %.2f\'\'*%.2f\'\'*%.2f\'\'", length, width, height));
                if (weight > 1) {
                    ids.txtWeight.setText(String.format("Weight - %.3f kg", (weight)));
                } else ids.txtWeight.setText(String.format("Weight - %.0f gm", (weight * 1000)));

                ids.txtDeckle.setText(String.format("Deckle - %.2f", deckle));
                ids.txtLength.setText(String.format("Length - %.2f", totalLength));
            }
        }
    }


}
