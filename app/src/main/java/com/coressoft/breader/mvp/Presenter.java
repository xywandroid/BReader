package com.coressoft.breader.mvp;

import android.os.Bundle;


/**
 * Created by lgp on 2015/9/4.
 */
public interface Presenter {
    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onStart();

    void onPause();

    void onStop();

    void onDestroy();

    void attachView(View v);

}
