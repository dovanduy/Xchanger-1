package com.therichclass.marquant.qwikdial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marquant on 7/21/2017.
 */

public class NetworkAirtelSub  extends  android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_datasub_airtel,container,false);

        /* Define Your Functionality Here
           Find any view  => v.findViewById()
          Specifying Application Context in Fragment => getActivity() */

        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Data Subscription");
        return v;
    }
}



