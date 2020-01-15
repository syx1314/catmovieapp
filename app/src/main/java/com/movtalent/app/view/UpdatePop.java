package com.movtalent.app.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.lxj.xpopup.core.CenterPopupView;
import com.movtalent.app.R;
import com.movtalent.app.model.dto.PostDto;
import com.movtalent.app.model.dto.UpdateDto;

/**
 * @author huangyong
 *         createTime 2019-10-16
 */
public class UpdatePop extends CenterPopupView {


    private TextView postTitle;
    private TextView content;
    private UpdateDto dto;
    OnConfirmListener onConfirmListener;

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    public UpdatePop(@NonNull Context context, UpdateDto dto) {
        super(context);
        this.dto = dto;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.update_layout;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        content = findViewById(R.id.content);
        postTitle = findViewById(R.id.post_title);
        postTitle.setText(dto.getMsg());
        content.setText(Html.fromHtml(dto.getData().getUpdateMsg()));
        findViewById(R.id.iv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        findViewById(R.id.tv_update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onConfirmListener != null) {
                    onConfirmListener.confirm();
                }
                /**
                 * 打开浏览器下载App
                 */
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(dto.getData().getDownloadUrl());
                intent.setData(content_url);
//        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                getContext().startActivity(intent);
            }
        });
    }

    public interface OnConfirmListener {
        void confirm();
    }
}
