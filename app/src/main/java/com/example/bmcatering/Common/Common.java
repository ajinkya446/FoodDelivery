package com.example.bmcatering.Common;

import com.example.bmcatering.Model.User;

public class Common {
    public static User CurrentUser;

    public static final String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Placed..";
        else if(status.equals("1"))
            return  "In Processing.";
        else
            return "shipped";
    }
}
