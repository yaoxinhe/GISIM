package com.example.bawei.chartmodule.bean;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParseUtil {
    public static List<BianqingBean> parseEmojiList(String json) {
        List<BianqingBean> emojiEntityList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("emoji_list");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                    if (jsonObject1 != null) {
                        BianqingBean mEmojiEntity = new BianqingBean();
                        mEmojiEntity.setName(jsonObject1.optString("name", ""));
                        mEmojiEntity.setUnicode(jsonObject1.optInt("unicode", 0));
                        emojiEntityList.add(mEmojiEntity);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return emojiEntityList;
    }
}
