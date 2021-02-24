package com.application;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This class is a holder for the grid view of the day elements in MainActivity. It is called in DayAdapter and
 * handles all TextViews and ImageViews for the different days
 */
public class DayAdapterHolder {
    public TextView txV; // Contains the day like Mo. 01
    public ImageView imV; // Contains the background color (sun = red, actual day = blue, normal day = turquoise)
    public ImageView imVEmpty; // Contains the the picture of empty set
    public ImageView imVBar1; // Contains the bar which is representing only one element in this day
    public ImageView imVBar2; // Contains the bar which is representing two elements in the day
    public ImageView ImVBar3; // Contains the bar which is representing three or more elements in the day

    public DayAdapterHolder() {
        // empty
    }
}
