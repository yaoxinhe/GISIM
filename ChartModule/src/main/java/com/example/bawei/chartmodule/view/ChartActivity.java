package com.example.bawei.chartmodule.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.bawei.basemodel.device.AliyunUtils;
import com.example.bawei.basemodel.device.FileUtil;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.chartmodule.R;
import com.example.bawei.chartmodule.adapter.ChartRecycleViewAdapter;
import com.example.bawei.chartmodule.adapter.MyBiaoqingItem;
import com.example.bawei.chartmodule.bean.BianqingBean;
import com.example.bawei.chartmodule.bean.JsonParseUtil;
import com.example.bawei.chartmodule.bean.MyChartBean;
import com.google.gson.Gson;
import com.iceteck.silicompressorr.SiliCompressor;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wildma.pictureselector.PictureSelector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.github.rockerhieu.emojicon.emoji.Emojicon;
import io.github.rockerhieu.emojiconize.Emojiconize;

public class ChartActivity extends BaseMVPActivity {

    private static int a=0;
    private TextView chart_tv;
    private RecyclerView chart_recycleview;
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
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String usercode;
    private RecyclerView biaoqing_recycle;
    private List<BianqingBean> biaoqinglist;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;

            String fileName = usercode + System.currentTimeMillis() + ".mp4";
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/video/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", "video/" + fileName, str, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), remotePath);
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                }
            });
            sendmessage(str);

        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(Bundle savedInstanceState) {
        biaoqinglist =    JsonParseUtil.parseEmojiList(FileUtil.readAssetsFile(this, "EmojiList.json"));

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
        biaoqing_recycle=findViewById(R.id.biaoqing_recycle);
        SharedPreferences yxh = getSharedPreferences("yxh", 0);
        usercode = yxh.getString("usercode", "");
        String s = FileUtil.readAssetsFile(this, "EmojiList.json");
        LogUtils.i(s);
        Gson gson = new Gson();

        MyBiaoqingItem myBiaoqingItem = new MyBiaoqingItem(R.layout.biaoqingitem, biaoqinglist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        biaoqing_recycle.setLayoutManager(gridLayoutManager);
        biaoqing_recycle.setAdapter(myBiaoqingItem);
        biaoqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a++;
                if(a%2==0){
                    biaoqing_recycle.setVisibility(View.GONE);
                }else{
                    biaoqing_recycle.setVisibility(View.VISIBLE);

                }
            }
        });
        xiangji.setOnClickListener(v -> {
            Intent intent12 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            //启动摄像头应用程序
            startActivityForResult(intent12, 200);
        });
        luyin.setOnTouchListener((v, event) -> chartvrv.onPressToSpeakBtnTouch(v, event, (voiceFilePath, voiceTimeLength) -> {
            String fileName = usercode + System.currentTimeMillis() + ".mp3";
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/audio/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", "audio/" + fileName, voiceFilePath, new OSSCompletedCallback() {
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
                setMediaPlayer(list.get(position).getMessage());
            } else if (list.get(position).getMessage().contains(".mp4")) {
                Intent intent1 = new Intent(ChartActivity.this, VideoPlayer_Activity.class);
                intent1.putExtra("path", list.get(position).getMessage());
                startActivity(intent1);
            }
        });
        XmppManager.getInstance().addMessageListener(new IMsgCallback() {
            @Override
            public void Success(MsgEntity msgEntity) {
                LogUtils.i("接收到新消息的回调");
                if (msgEntity.getMsgType() == MsgEntity.MsgType.Txt) {
                    MyChartBean myChartBean = new MyChartBean(1, msgEntity.getMsg());
                    if (msgEntity.getFrom().equals(username)) {
                        list.add(myChartBean);
                        runOnUiThread(() -> {
                            chart_recycleview.scrollToPosition(list.size() - 1);
                            chartRecycleViewAdapter.notifyDataSetChanged();
                        });
                    }

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
            String fileName = usercode + System.currentTimeMillis() + ".jpg";
            String filePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/img/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", "img/" + fileName, filePath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), remotePath);
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                }
            });
            sendmessage(remotePath);


        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                String path = cursor.getString(column_index);
                String s = Environment.getExternalStorageDirectory().toString();
                new Thread(new Runnable() {
                    @Override

                    public void run() {
                        try {

                            /**
                             * 视频压缩
                             * 第一个参数:视频源文件路径
                             * 第二个参数:压缩后视频保存的路径
                             */
                            String comPressPath = SiliCompressor.with(ChartActivity.this).compressVideo(path, s);
                            LogUtils.d(comPressPath);
                            Message message = new Message();
                            message.obj = comPressPath;
                            handler.sendMessage(message);


                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }

                    }

                }).start();

            }
//            LogUtils.d(data1.getPath() + "========" + s);
//
        }
    }
}
