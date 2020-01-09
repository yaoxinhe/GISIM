package com.example.bawei.basemodel.chartmodule.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.basemodel.chartmodule.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class VideoPlayer_Activity extends BaseMVPActivity {

    private StandardGSYVideoPlayer player_gsy;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_player_;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        player_gsy = (StandardGSYVideoPlayer) findViewById(R.id.player_gsy);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {
        Intent intent = getIntent();
        String audio = intent.getStringExtra("path");
        player_gsy.setUp(audio,true,"asd");
        player_gsy.startPlayLogic();
    }
}
