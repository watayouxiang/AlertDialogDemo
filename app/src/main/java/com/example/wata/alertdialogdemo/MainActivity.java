package com.example.wata.alertdialogdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 最简单的 AlertDialog
     *
     * @param view
     */
    public void showSimpleDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.setNeutralButton("中性", null);
        builder.show();
    }

    /**
     * AlertDialog 添加额外 View
     *
     * @param view
     */
    public void setView(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setView(new EditText(this));
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    /**
     * 单选列表 AlertDialog
     *
     * @param view
     */
    public void setSingleChoiceItems(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选列表");
        builder.setSingleChoiceItems(
                new String[]{"11111", "22222", "33333", "44444", "55555"},
                0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    /**
     * 多选列表 AlertDialog
     *
     * @param view
     */
    public void setMultiChoiceItems(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选列表");
        builder.setMultiChoiceItems(
                new String[]{"11111", "22222", "33333", "44444", "55555"},
                new boolean[]{false, true, true, false, false},
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(mContext, "which " + which + ", isChecked " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    /**
     * 多条目 AlertDialog
     *
     * @param view
     */
    public void setItems(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setItems(
                new String[]{"11111", "22222", "33333", "44444", "55555"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "which " + which, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    /**
     * 完全自定义 AlertDialog
     *
     * @param view
     */
    public void showMyDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        //取消外部点击隐藏
        alertDialog.setCanceledOnTouchOutside(false);
        //设置取消监听
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(mContext, "onCancel", Toast.LENGTH_SHORT).show();
            }
        });
        //设置自定义布局
        View rootView = getLayoutInflater().inflate(R.layout.view_my_dialog, null);
        rootView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.setView(rootView);
        alertDialog.show();
        //设置弹窗参数（注意：弹窗样式修改必须在 dialog.show() 方法后调用，否则不生效）
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //dialog即使没有设置四周的margin也有margin，可以设置给dialog设置个background来解决
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //宽度为屏幕宽度的80%
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (getWindowManager().getDefaultDisplay().getWidth() * 0.8f);
            window.setAttributes(params);
        }
    }
}
