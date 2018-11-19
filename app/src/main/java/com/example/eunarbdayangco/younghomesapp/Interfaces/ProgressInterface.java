package com.example.eunarbdayangco.younghomesapp.Interfaces;

public interface ProgressInterface {

    public void startProgress();
    public void endProgress();
    public void successResult();
    public void failedResult(Exception ex);
}
