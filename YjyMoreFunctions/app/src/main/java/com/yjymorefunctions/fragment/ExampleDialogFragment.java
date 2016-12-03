package com.yjymorefunctions.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.yjymorefunctions.R;

/**
 * Auth：yujunyao
 * Since: 2016/12/3 15:09
 * Email：yujunyao@yonglibao.com
 */

public class ExampleDialogFragment extends DialogFragment {

    /**第一种做法*/
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示标题
//        getDialog().setCanceledOnTouchOutside(false);//屏幕外不可点击
//        return inflater.inflate(R.layout.dialog_fragment_example, container);
//    }

    /**第二种，嵌入AlertDialog或者Dialog，不过官网不推荐使用Dialog*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.dialog_fragment_example).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ResponseListener responseListener = (ResponseListener) getActivity();
                responseListener.onResponse("传递消息");
            }
        }).setNegativeButton("Cancel", null);
        return builder.create();
    }

    public interface ResponseListener {
        void onResponse(String response);
    }

}
