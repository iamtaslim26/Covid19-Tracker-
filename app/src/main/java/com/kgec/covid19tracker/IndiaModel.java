package com.kgec.covid19tracker;

  public class IndiaModel {

    public String loc,deaths,discharged,totalConfirmed;

    public IndiaModel() {
    }

    public IndiaModel(String loc, String deaths, String discharged, String totalConfirmed) {
        this.loc = loc;
        this.deaths = deaths;
        this.discharged = discharged;
        this.totalConfirmed = totalConfirmed;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getDischarged() {
        return discharged;
    }

    public void setDischarged(String discharged) {
        this.discharged = discharged;
    }

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }
}
