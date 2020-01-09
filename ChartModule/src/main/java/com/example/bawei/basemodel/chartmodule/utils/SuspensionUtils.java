package com.example.bawei.basemodel.chartmodule.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @Author yaoxinhe
 * @CreateDate 2020/1/8 18:42
 * @Email 1151403054@qq.com
 */
public class SuspensionUtils {
    public static ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final View contentView) {

        return () -> {

            Rect r = new Rect();

            decorView.getWindowVisibleDisplayFrame(r);


            int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;

            int diff = height - r.bottom;


            if (diff != 0) {

                if (contentView.getPaddingBottom() != diff) {

                    contentView.setPadding(0, 0, 0, diff);

                }

            } else {

                if (contentView.getPaddingBottom() != 0) {

                    contentView.setPadding(0, 0, 0, 0);

                }

            }
        };

    }
}
