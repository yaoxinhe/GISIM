package com.example.bawei.chartmodule.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.bawei.basemodel.device.AliyunUtils;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.chartmodule.R;
import com.example.bawei.chartmodule.adapter.ChartRecycleViewAdapter;
import com.example.bawei.chartmodule.bean.MyChartBean;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wildma.pictureselector.PictureSelector;

import org.jivesoftware.smack.chat2.Chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends BaseMVPActivity {

    private TextView chart_tv;
    private RecyclerView chart_recycleview;
    private ImageView chart_imlist;
    private EditText chart_etmessage;
    private String username;
    private Button chart_btnsend;
    private ChartRecycleViewAdapter chartRecycleViewAdapter;
    private List<MyChartBean> list = new ArrayList<>();
    private ImageButton luyin;
    private ImageButton tupian;
    private ImageButton xiangji;
    private ImageButton diwei;
    private ImageButton biaoqing;
    private ImageButton more;
    private VoiceRecorderView chartvrv;
    MediaPlayer mediaPlayer = new MediaPlayer();
    String usercode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(Bundle savedInstanceState) {
        chart_tv = findViewById(R.id.chart_tv);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        chart_tv.setText(username);
        luyin = findViewById(R.id.luyin);
        tupian = findViewById(R.id.tupian);
        xiangji = findViewById(R.id.xiangji);
        diwei = findViewById(R.id.diwei);
        biaoqing = findViewById(R.id.biaoqing);
        more = findViewById(R.id.more);
        chart_recycleview = findViewById(R.id.chart_recycleview);
        chart_etmessage = findViewById(R.id.chart_etmessage);
        chart_btnsend = findViewById(R.id.chart_btnsend);
        chartvrv = findViewById(R.id.chartvrv);
        SharedPreferences yxh = getSharedPreferences("yxh", 0);
        usercode= yxh.getString("usercode", "");
        luyin.setOnTouchListener((v, event) -> chartvrv.onPressToSpeakBtnTouch(v, event, (voiceFilePath, voiceTimeLength) -> {
            String fileName = usercode+System.currentTimeMillis() + ".mp3";
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", fileName, voiceFilePath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), remotePath);
                }
                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                }
            });
            sendmessage(remotePath);

        }));
        tupian.setOnClickListener(v ->
                PictureSelector
                        .create(ChartActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1)
        );
        chartRecycleViewAdapter = new ChartRecycleViewAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chart_recycleview.setLayoutManager(linearLayoutManager);
        chart_recycleview.setAdapter(chartRecycleViewAdapter);
        chart_btnsend.setOnClickListener(v -> {
            String string = chart_etmessage.getText().toString();
            if (string.equals("")) {
                Toast.makeText(this, "不可以为空", Toast.LENGTH_SHORT).show();
                return;
            }
            XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), string);

            sendmessage(string);


        });
        chartRecycleViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (list.get(position).getMessage().contains(".mp3")) {
                LogUtils.d("你点击" + list.get(position).getMessage());
                setMediaPlayer(list.get(position).getMessage());
            }
        });
        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                LogUtils.i("接收到新消息的回调");
                if (msgEntity.getMsgType() == MsgEntity.MsgType.Txt) {
                    MyChartBean myChartBean = new MyChartBean(1, msgEntity.getMsg());
                    list.add(myChartBean);
                    runOnUiThread(() -> {
                        chart_recycleview.scrollToPosition(list.size() - 1);
                        chartRecycleViewAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void Failed(Throwable throwable) {

            }
        });
    }

    private void sendmessage(String string) {
        list.add(new MyChartBean(0, string));
        chart_recycleview.scrollToPosition(list.size() - 1);
        chart_etmessage.setText("");
        chartRecycleViewAdapter.notifyDataSetChanged();

    }

    private void setMediaPlayer(String msg) {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(msg);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void doEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data == null) {
                return;
            }

            String fileName = usercode+System.currentTimeMillis() + ".jpg";
            String filePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", fileName, filePath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), remotePath);
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                }
            });
            sendmessage(remotePath);


        }
    }


}
