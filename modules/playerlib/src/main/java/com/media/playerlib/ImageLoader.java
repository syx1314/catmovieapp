package com.media.playerlib;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by 呆呆 on 2020/1/4.
 */

public class ImageLoader {

    public static  void load(Context context, String imgUrl, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.tupian);
        if (!TextUtils.isEmpty(imgUrl)) {
            GlideUrl glideUrl = new GlideUrl(imgUrl, new LazyHeaders.Builder().addHeader("Referer", "http://catmovie.cn/").build());
            Glide.with(context).load(glideUrl).transition(DrawableTransitionOptions.withCrossFade(300)).apply(requestOptions).into(imageView);
        }

    }
}
