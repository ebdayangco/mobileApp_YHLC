package com.example.eunarbdayangco.younghomesapp.Section;

import com.example.eunarbdayangco.younghomesapp.Model.Guardian;

public class StationSection {

    private static Guardian userGuardian;

    public static Guardian getUserGuardian() {
        return userGuardian;
    }

    public static void setUserGuardian(Guardian userGuardian) {
        StationSection.userGuardian = userGuardian;
    }
}
