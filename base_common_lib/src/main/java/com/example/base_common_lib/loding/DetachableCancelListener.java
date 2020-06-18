package com.example.base_common_lib.loding;

import android.content.DialogInterface;

/**
 * CreateDate:2019/3/11
 * Author:lmy
 */
public class DetachableCancelListener implements DialogInterface.OnCancelListener {

    public static DetachableCancelListener wrap(DialogInterface.OnCancelListener delegate) {
        return new DetachableCancelListener(delegate);
    }

    private DialogInterface.OnCancelListener delegateOrNull;

    private DetachableCancelListener(DialogInterface.OnCancelListener delegate) {
        this.delegateOrNull = delegate;
    }


    public void clearOnDetach(){
        if (delegateOrNull != null) {
            delegateOrNull = null;
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (delegateOrNull != null) {
            delegateOrNull.onCancel(dialog);
        }
    }
}
