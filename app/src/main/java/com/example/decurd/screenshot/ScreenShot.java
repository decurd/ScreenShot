package com.example.decurd.screenshot;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by decurd on 2017-06-19.
 */

public class ScreenShot {

    public static Bitmap takeScreenShot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takeScreenShotOfRootView(View v) {
        return takeScreenShot(v.getRootView());
    }

}
