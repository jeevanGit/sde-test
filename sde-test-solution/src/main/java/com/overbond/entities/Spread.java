package com.overbond.entities;

public class Spread {

    String corporate_bond_id;
    String government_bond_id;
    String spread_to_benchmark;

    public String getCorporate_bond_id() {
        return corporate_bond_id;
    }

    public void setCorporate_bond_id(String corporate_bond_id) {
        this.corporate_bond_id = corporate_bond_id;
    }

    public String getGovernment_bond_id() {
        return government_bond_id;
    }

    public void setGovernment_bond_id(String government_bond_id) {
        this.government_bond_id = government_bond_id;
    }

    public String getSpread_to_benchmark() {
        return spread_to_benchmark;
    }

    public void setSpread_to_benchmark(String spread_to_benchmark) {
        this.spread_to_benchmark = spread_to_benchmark;
    }

}
