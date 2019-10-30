package com.yuong.demo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class CommentDialog extends Dialog implements KeyBoardHelper.SoftKeyboardStateListener {
    private static final String TAG = CommentDialog.class.getSimpleName();
    private EditText et_comment;
    private TextView tv_send;
    private Context context;

    public CommentDialog(Context context) {
        this(context, R.style.InputDialogTheme);
    }

    public CommentDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comment_dialog);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        //设置显示动画
        //wl.windowAnimations = R.style.dialogShow;
        wl.x = 0;
        wl.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(wl);

        initView();
        initListener();
    }

    private void initView() {
        et_comment = findViewById(R.id.et_comment);
        tv_send = findViewById(R.id.tv_send);
    }

    private void initListener() {

        //返回键监听
//        setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    dialog.dismiss();
//                }
//                return false;
//            }
//        });
        View rootView = ((Activity) context).getWindow().getDecorView();
        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(rootView);
        keyBoardHelper.addSoftKeyboardStateListener(this);
    }

    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
    }

    @Override
    public void onSoftKeyboardClosed() {
        CommentDialog.this.dismiss();
    }
}
