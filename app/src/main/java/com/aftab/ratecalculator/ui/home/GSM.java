package com.aftab.ratecalculator.ui.home;

public class GSM {
    int gsm;
    int ply;

    GSM(int ply,int gsm) {
        this.gsm = gsm;
        this.ply = ply;
    }

    public String getGSM() {
        String selectedGSM = "";
        if(ply==7 && gsm==900){
            return "150/100/100";
        }
        else{
            switch (gsm) {
                case 350:
                    selectedGSM = "100/100/100";
                    break;
                case 400:
                    selectedGSM = "150/100/100";
                    break;
                case 450:
                    selectedGSM = "150/100/150";
                    break;
                case 525:
                    selectedGSM = "150/150/150";
                    break;
                case 600:
                    selectedGSM = "100/100/100";
                    break;
                case 650:
                    selectedGSM = "150/100/100";
                    break;
                case 750:
                    selectedGSM = "150/100/150";
                    break;
                case 900:
                    selectedGSM = "150/150/150";
                    break;

                case 850:
                    selectedGSM = "100/100/100";
                    break;

                case 1050:
                    selectedGSM = "150/100/150";
                    break;
                case 1275:
                    selectedGSM = "150/150/150";
                    break;

                case 1100:
                    selectedGSM = "100/100/100";
                    break;
                case 1150:
                    selectedGSM = "150/100/100";
                    break;
                case 1350:
                    selectedGSM = "150/100/150";
                    break;
                case 1650:
                    selectedGSM = "150/150/150";
                    break;

            }
        }


        return selectedGSM;
    }
}
