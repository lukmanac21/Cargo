package com.reza.jenazah;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by REZA on 04/12/2017.
 */

public class beranda extends Fragment {
    View i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        i = inflater.inflate(R.layout.content_main, container, false);
        return i;
    }
}
