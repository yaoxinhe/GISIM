package com.example.bawei.basemodel.chartmodule.view;

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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.example.bawei.basemodel.chartmodule.utils.SuspensionUtils;
import com.example.bawei.basemodel.device.AliyunUtils;
import com.example.bawei.basemodel.log.LogUtils;
import com.example.bawei.basemodel.ui.BaseMVPActivity;
import com.example.bawei.basemodel.chartmodule.R;
import com.example.bawei.basemodel.chartmodule.adapter.ChartRecycleViewAdapter;
import com.example.bawei.basemodel.chartmodule.adapter.MyBiaoqingItem;
import com.example.bawei.basemodel.chartmodule.bean.EmoticonBean;
import com.example.bawei.basemodel.chartmodule.bean.MyAddressBean;
import com.example.bawei.basemodel.chartmodule.bean.MyChartBean;
import com.example.bawei.basemodel.chartmodule.contract.Contract;
import com.example.bawei.basemodel.chartmodule.presenter.Presenter;
import com.iceteck.silicompressorr.SiliCompressor;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends BaseMVPActivity<Contract.ChartPresenter> implements Contract.ChartView {

    private MyBiaoqingItem myBiaoqingItem;
    private List<String> getlist;
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
    public static String usercode;
    private RecyclerView biaoqing_recycle;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            /**
             * 发送视频
             */
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
        //初始化表情的集合
        getlist = EmoticonBean.getlist();
        //设置edittext悬浮在软键盘的上面不会遮挡
        View decorView = getWindow().getDecorView();
        // 此处的控件ID可以使用界面当中的指定的任意控件
        View contentView = findViewById(Window.ID_ANDROID_CONTENT);
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(SuspensionUtils.getGlobalLayoutListener(decorView, contentView));
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
        biaoqing_recycle = findViewById(R.id.biaoqing_recycle);
        //mvp初始化p层
        presenter = new Presenter();
        presenter.attachView(this);

        myBiaoqingItem = new MyBiaoqingItem(R.layout.biaoqingitem, getlist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        biaoqing_recycle.setLayoutManager(gridLayoutManager);
        biaoqing_recycle.setAdapter(myBiaoqingItem);


        /**
         *接收消息的回调是个异步任务需要跳到主线程更新UI
         */
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
        chartRecycleViewAdapter = new ChartRecycleViewAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        chart_recycleview.setLayoutManager(linearLayoutManager);
        chart_recycleview.setAdapter(chartRecycleViewAdapter);


    }

    /**
     * @param string 发送消息更新本地的方法
     */

    private void sendmessage(String string) {
        list.add(new MyChartBean(0, string));
        chart_recycleview.scrollToPosition(list.size() - 1);
        chart_etmessage.setText("");
        chartRecycleViewAdapter.notifyDataSetChanged();

    }

    /**
     * @param msg 初始化mediaplayer
     */

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

        //拿到储存的usercode
        SharedPreferences yxh = getSharedPreferences("yxh", 0);
        usercode = yxh.getString("usercode", "");

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void doEvent() {

        //表情recycleview的条目点击事件点击之后获取输入框里面的文本并且拼接到输入框里面去
        myBiaoqingItem.setOnItemClickListener((adapter, view, position) -> {
            String string = chart_etmessage.getText().toString();
            LogUtils.d(position + "");
            chart_etmessage.setText(string + getlist.get(position));
        });

        //调用p层的获得地址的接口
        diwei.setOnClickListener(v -> {
            presenter.getAddress(usercode);
        });

        //判断表情list的显示与隐藏
        biaoqing.setOnClickListener(v -> {
            if (biaoqing_recycle.getVisibility() == View.GONE) {
                biaoqing_recycle.setVisibility(View.VISIBLE);
            } else {
                biaoqing_recycle.setVisibility(View.GONE);

            }
        });


        xiangji.setOnClickListener(v -> {
            Intent intent12 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            //启动摄像头应用程序
            startActivityForResult(intent12, 200);
        });


        /**
         * 发送文本消息
         */
        chart_btnsend.setOnClickListener(v -> {
            String string = chart_etmessage.getText().toString();
            if (string.equals("")) {
                Toast.makeText(this, "不可以为空", Toast.LENGTH_SHORT).show();
                return;
            }
            XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), string);

            sendmessage(string);


        });

        /**
         *
         * 发送音频
         */
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

        //图片选择器
        tupian.setOnClickListener(v ->
                PictureSelector
                        .create(ChartActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1)
        );

        /**
         * 处理视频或者音频的方法
         * 是音频的话点击播放
         * 如果是视频跳转界面播放
         */
        chartRecycleViewAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (list.get(position).getMessage().contains(".mp3")) {
                setMediaPlayer(list.get(position).getMessage());
            } else if (list.get(position).getMessage().contains(".mp4")) {
                Intent intent1 = new Intent(ChartActivity.this, VideoPlayer_Activity.class);
                intent1.putExtra("path", list.get(position).getMessage());
                startActivity(intent1);
            }
        });
    }


    /**
     * @param requestCode
     * @param resultCode
     * @param data        Actvity的回调方法用来处理录制视频发送图片的返回值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            String fileName = usercode + System.currentTimeMillis() + ".jpg";
            //拿到图片的文件路径
            String filePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            //获取sd卡根目录
            String s = Environment.getExternalStorageDirectory().toString();
            String comPressPath = SiliCompressor.with(ChartActivity.this).compress(filePath, new File(s));
            String remotePath = "http://baweitest6.oss-cn-beijing.aliyuncs.com/img/" + fileName;
            AliyunUtils.getInstance().upload("baweitest6", "img/" + fileName, comPressPath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), remotePath);
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                }
            });
            sendmessage(comPressPath);


        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            //压缩视频并且拿到返回的视频地址
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
                            compressvideo(path, s);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }


    /**
     * @param bean
     * @param flag
     * @param <T>  请求网络最新位置成功的调用方法
     */
    @Override
    public <T> void Success(T bean, int flag) {
        MyAddressBean myAddressBean = (MyAddressBean) bean;
        MyAddressBean.DataBean data = myAddressBean.getData();
        double lon = data.getLon();
        double lat = data.getLat();
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onGeocodeSearched(GeocodeResult result, int rCode) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
                String formatAddress = result.getRegeocodeAddress().getFormatAddress();
                if (rCode == 1000) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(username + "@" + XmppManager.getInstance().getXmppConfig().getDomainName(), formatAddress);
                    sendmessage(formatAddress);
                }
                LogUtils.e("formatAddress:" + formatAddress);
                LogUtils.e("rCode:" + rCode);

            }
        });
        LatLonPoint lp = new LatLonPoint(lat, lon);
        RegeocodeQuery query = new RegeocodeQuery(lp, 500f, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);

    }

    @Override
    public void Failed() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHideIng() {

    }

    /**
     * @return 获取owner
     */

    @Override
    public LifecycleOwner getOwner() {
        return this;
    }

    /**
     * 视频压缩
     * 第一个参数:视频源文件路径
     * 第二个参数:压缩后视频保存的路径
     */
    private void compressvideo(String path, String s) throws URISyntaxException {

        String comPressPath = SiliCompressor.with(ChartActivity.this).compressVideo(path, s);
        LogUtils.d(comPressPath);
        Message message = new Message();
        message.obj = comPressPath;
        handler.sendMessage(message);
    }


}
