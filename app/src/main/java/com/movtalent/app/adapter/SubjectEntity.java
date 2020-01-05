package com.movtalent.app.adapter;

import android.text.TextUtils;

import com.movtalent.app.App_Config;

/**
 * @author huangyong
 * createTime 2019-09-15
 */
public class SubjectEntity {

    private String subJectName;

    private String subJectNameSub;

    private String posterUrl;

    private int topId;

    public SubjectEntity(String subJectName, String posterUrl, int topId, String subJectNameSub) {
        this.subJectName = subJectName;
        this.posterUrl = posterUrl;
        this.topId = topId;
        this.subJectNameSub = subJectNameSub;
    }

    public String getSubJectName() {
        return subJectName;
    }

    public String getPosterUrl() {
        if (TextUtils.isEmpty(posterUrl)){
            return posterUrl;
        }else {
            String replace = posterUrl.replace("mac://", "http://");
            if (replace.startsWith("upload/")){
                replace= App_Config.BASE_URL+replace;
            }
            return replace;
        }

    }

    public int getTopId() {
        return topId;
    }

    public String getSubJectNameSub() {
        return subJectNameSub;
    }

}