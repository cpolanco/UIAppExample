package com.example.telematica.uiappexample.presenters;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.telematica.uiappexample.ui.views.ViewInter;

/**
 * Created by cpola on 15-01-2016.
 */
public class PresenterImpl {

    private Activity mContext;
    private ViewInter mViewInter;

    public PresenterImpl(Activity mContext, ViewInter mViewInter) {

        this.mContext = mContext;
        this.mViewInter = mViewInter;
        mViewInter.showConnectionErrorMsg();
    }




    }



