package com.example.dev4you;

public class DataHolder {
    String name,number,pnr_no,tic_no,luggage_weight;

    public DataHolder() {
    }

    public DataHolder(String name, String number, String pnr_no, String tic_no, String luggage_weight) {
        this.name = name;
        this.number = number;
        this.pnr_no = pnr_no;
        this.tic_no = tic_no;
        this.luggage_weight = luggage_weight;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPnr_no() {
        return pnr_no;
    }

    public String getTic_no() {
        return tic_no;
    }

    public String getLuggage_weight() {
        return luggage_weight;
    }
}
