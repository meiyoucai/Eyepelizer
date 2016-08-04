package com.example.ywq9682.eyepetizer.video;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.android.tedcoder.wkvideoplayer.dlna.engine.DLNAContainer;
import com.android.tedcoder.wkvideoplayer.dlna.service.DLNAService;
import com.android.tedcoder.wkvideoplayer.model.Video;
import com.android.tedcoder.wkvideoplayer.model.VideoUrl;
import com.android.tedcoder.wkvideoplayer.util.DensityUtil;
import com.android.tedcoder.wkvideoplayer.view.Brightness;
import com.android.tedcoder.wkvideoplayer.view.MediaController;
import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;
import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by YWQ9682 on 2016/7/25.
 */
public class VideoPlayerActivity extends BaseActivity implements Brightness, View.OnClickListener {
    private SuperVideoPlayer mSuperVideoPlayer;
    private View mPlayBtnView;
    private String urlS;
    private String urlF;
    private String title;
    private MediaController mediaController;
    private Button btnDownLoad;
    private ServiceConnection connection;
    private String path;

    @Override
    public int setLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        mPlayBtnView = findViewById(R.id.play_btn);
        btnDownLoad = (Button) findViewById(R.id.btn_download);
        btnDownLoad.setOnClickListener(this);
//        mPlayBtnView.setOnClickListener(this);
        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
        mediaController = new MediaController(this);
        mediaController.setBrightness(this);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                DownloadService.MyBind myBind = (DownloadService.MyBind) iBinder;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        startDLNAService();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        urlF = intent.getStringExtra("urlF");
        urlS = intent.getStringExtra("urlS");
        title = intent.getStringExtra("title");
        path = intent.getStringExtra("path");
        if (path != null) {
            mSuperVideoPlayer.loadLocalVideo(path);
        } else {
            Log.d("ssd", urlF + " ");
            Log.d("ssd", urlS + " ");
            Log.d("ssd", title + " ");
//        mSuperVideoPlayer.loadLocalVideo(Environment.getExternalStorageDirectory()
//                + File.separator + "kaiyan/video/" + "当建筑空间可以繁衍生长.mp4");
            playerVideo();
        }
    }


    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }
    };

    public void playerVideo() {
        mPlayBtnView.setVisibility(View.GONE);
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
        mSuperVideoPlayer.setAutoHideController(false);
        Video video = new Video();
        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl(urlS);
        VideoUrl videoUrl2 = new VideoUrl();
        videoUrl2.setFormatName("480P");
        videoUrl2.setFormatUrl(urlS);
        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();
        arrayList1.add(videoUrl1);
        arrayList1.add(videoUrl2);
        video.setVideoName(title);
        video.setVideoUrl(arrayList1);
        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video);
        mSuperVideoPlayer.loadMultipleVideo(videoArrayList, 0, 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDLNAService();
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("bbcc", "dsf");
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            Log.d("bbcc", height + ", " + width);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }

    private void startDLNAService() {
        // Clear the device container.
        DLNAContainer.getInstance().clear();
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        startService(intent);
    }

    private void stopDLNAService() {
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        stopService(intent);
    }

    @Override
    public void setBrightness(float distanceY, float toucheRang) {
        Log.d("ddssa", "getScreenBrightness(this):" + getScreenBrightness(this));
        int fanalBright = (int) Math.min(distanceY / toucheRang * 255 + getScreenBrightness(this), 255);
        if (fanalBright < 0) {
            fanalBright = 0;
        }
        setBrightness(this, fanalBright);
    }

    /**
     * 获取屏幕的亮度
     *
     * @param activity
     * @return
     */
    public static int getScreenBrightness(Activity activity) {
        int brightnessValue = 0;
        try {
            brightnessValue = android.provider.Settings.System.getInt(
                    activity.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightnessValue;
    }

    /**
     * 设置屏幕亮度
     *
     * @param activity
     * @param brightness
     */
    public static void setBrightness(Activity activity, int brightness) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        activity.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra("urlS", urlS);
        Log.d("ywq66a", title);
        intent.putExtra("title", title);
        startService(intent);
        Util.showLog("sss0", "ddddd");

    }
}
