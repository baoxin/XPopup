package com.lxj.xpopup.impl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lxj.xpopup.R;
import com.lxj.xpopup.XPopupConfig;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

/**
 * Description: 确定和取消的对话框
 * Create by dance, at 2018/12/16
 */
public class ConfirmPopupView extends CenterPopupView implements View.OnClickListener{

    public ConfirmPopupView(@NonNull Context context) {
        super(context);
    }

    public ConfirmPopupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ConfirmPopupView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout._xpopup_center_impl_confirm;
    }

    TextView tv_title, tv_content, tv_cancel, tv_confirm;
    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);

        applyPrimaryColor();

        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        tv_title.setText(title);
        tv_content.setText(content);
    }

    protected void applyPrimaryColor(){
        tv_cancel.setTextColor(XPopupConfig.primaryColor);
        tv_confirm.setTextColor(XPopupConfig.primaryColor);
    }

    OnCancelListener cancelListener;
    OnConfirmListener confirmListener;
    public void setListener( OnConfirmListener confirmListener,OnCancelListener cancelListener){
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
    }
    String title;
    String content;
    public void setTitleContent(String title, String content){
        this.title = title;
        this.content = content;
    }
    @Override
    public void onClick(View v) {
        if(v==tv_cancel){
            if(cancelListener!=null)cancelListener.onCancel();
        }else if(v==tv_confirm){
            if(confirmListener!=null)confirmListener.onConfirm();
        }
        dismiss();
    }
}