package com.android.tedcoder.wkvideoplayer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tedcoder.wkvideoplayer.R;
import com.android.tedcoder.wkvideoplayer.model.Video;
import com.android.tedcoder.wkvideoplayer.model.VideoUrl;
import com.android.tedcoder.wkvideoplayer.util.DensityUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ted on 2015/8/4.
 * MediaController
 */
public class MediaController extends FrameLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private ImageView mPlayImg;//播放按钮
    private SeekBar mProgressSeekBar;//播放进度条
    private TextView mTimeTxt;//播放时间
    private ImageView mExpandImg;//最大化播放按钮
    private ImageView mShrinkImg;//缩放播放按钮
    private EasySwitcher mVideoSrcSwitcher;//视频源切换器
    private EasySwitcher mVideoFormatSwitcher;//视频清晰度切换器
    private View mMenuView;
    private View mMenuViewPlaceHolder;

    private MediaControlImpl mMediaControl;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
        if (isFromUser)
            mMediaControl.onProgressTurn(ProgressState.DOING, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.START, 0);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaControl.onProgressTurn(ProgressState.STOP, 0);
    }

    private EasySwitcher.EasySwitcherCallbackImpl mSrcSwitcherCallback = new EasySwitcher.EasySwitcherCallbackImpl() {
        @Override
        public void onSelectItem(int position, String name) {
            mMediaControl.onSelectSrc(position);
        }

        @Override
        public void onShowList() {
            mMediaControl.alwaysShowController();
            mVideoFormatSwitcher.closeSwitchList();
        }
    };

    private EasySwitcher.EasySwitcherCallbackImpl mFormatSwitcherCallback = new EasySwitcher.EasySwitcherCallbackImpl() {
        @Override
        public void onSelectItem(int position, String name) {
            mMediaControl.onSelectFormat(position);
        }

        @Override
        public void onShowList() {
            mMediaControl.alwaysShowController();
            mVideoSrcSwitcher.closeSwitchList();
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.pause) {
            mMediaControl.onPlayTurn();
        } else if (view.getId() == R.id.expand) {
            mMediaControl.onPageTurn();
        } else if (view.getId() == R.id.shrink) {
            mMediaControl.onPageTurn();
        }
    }

    public void initVideoList(ArrayList<Video> videoList) {
        ArrayList<String> name = new ArrayList<>();
        for (Video video : videoList) {
            name.add(video.getVideoName());
        }
        mVideoSrcSwitcher.initData(name);
    }

    public void initPlayVideo(Video video) {
        ArrayList<String> format = new ArrayList<>();
        for (VideoUrl url : video.getVideoUrl()) {
            format.add(url.getFormatName());
        }
        mVideoFormatSwitcher.initData(format);
    }

    public void closeAllSwitchList() {
        mVideoFormatSwitcher.closeSwitchList();
        mVideoSrcSwitcher.closeSwitchList();
    }

    /**
     * 初始化精简模式
     */
    public void initTrimmedMode() {
        mMenuView.setVisibility(GONE);
        mMenuViewPlaceHolder.setVisibility(GONE);
        mExpandImg.setVisibility(INVISIBLE);
        mShrinkImg.setVisibility(INVISIBLE);
    }

    /***
     * 强制横屏模式
     */
    public void forceLandscapeMode() {
        mExpandImg.setVisibility(INVISIBLE);
        mShrinkImg.setVisibility(INVISIBLE);
    }


    public void setProgressBar(int progress, int secondProgress) {
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
        if (secondProgress < 0) secondProgress = 0;
        if (secondProgress > 100) secondProgress = 100;
        mProgressSeekBar.setProgress(progress);
        mProgressSeekBar.setSecondaryProgress(secondProgress);
    }

    public void setPlayState(PlayState playState) {
        mPlayImg.setImageResource(playState.equals(PlayState.PLAY) ? R.drawable.biz_video_pause : R.drawable.biz_video_play);
    }

    public void setPageType(PageType pageType) {
        mExpandImg.setVisibility(pageType.equals(PageType.EXPAND) ? GONE : VISIBLE);
        mShrinkImg.setVisibility(pageType.equals(PageType.SHRINK) ? GONE : VISIBLE);
    }

    public void setPlayProgressTxt(int nowSecond, int allSecond) {
        mTimeTxt.setText(getPlayTime(nowSecond, allSecond));
    }

    public void playFinish(int allTime) {
        mProgressSeekBar.setProgress(0);
        setPlayProgressTxt(0, allTime);
        setPlayState(PlayState.PAUSE);
    }

    public void setMediaControl(MediaControlImpl mediaControl) {
        mMediaControl = mediaControl;
    }

    public MediaController(Context context) {
        super(context);
        initView(context);
    }

    public MediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public MediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.biz_video_media_controller, this);
        mPlayImg = (ImageView) findViewById(R.id.pause);
        mProgressSeekBar = (SeekBar) findViewById(R.id.media_controller_progress);
        mVideoSrcSwitcher = (EasySwitcher) findViewById(R.id.video_src_switcher);
        mVideoFormatSwitcher = (EasySwitcher) findViewById(R.id.video_format_switcher);
        mTimeTxt = (TextView) findViewById(R.id.time);
        mExpandImg = (ImageView) findViewById(R.id.expand);
        mShrinkImg = (ImageView) findViewById(R.id.shrink);
        mMenuView = findViewById(R.id.view_menu);
        mMenuViewPlaceHolder = findViewById(R.id.view_menu_placeholder);
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        maxVolum = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        initData();
    }

    private void initData() {
        mProgressSeekBar.setOnSeekBarChangeListener(this);
        mPlayImg.setOnClickListener(this);
        mShrinkImg.setOnClickListener(this);
        mExpandImg.setOnClickListener(this);
        setPageType(PageType.SHRINK);
        setPlayState(PlayState.PAUSE);
        mVideoFormatSwitcher.setEasySwitcherCallback(mFormatSwitcherCallback);
        mVideoSrcSwitcher.setEasySwitcherCallback(mSrcSwitcherCallback);
    }

    private float height;
    private float width;
    private float startY;
    private int mVolume;
    private AudioManager audioManager;
    private float toucheRang;
    private int maxVolum;
    private int volumuAdd;
    private float startX;
    private float middle;
    private Brightness brightness;

    public void setBrightness(Brightness brightness) {
        this.brightness = brightness;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下屏幕的时候回调这个方法
                //获取屏幕高度
                height = DensityUtil.getWidthInPx(getContext());
                width = DensityUtil.getHeightInPx(getContext());

                //记录我们第一次按下的坐标
                startY = event.getRawY();
                startX = event.getRawX();
                middle = Math.max(height, width) / 2;

                Log.d("ssdf", "middle:" + middle);
                Log.d("ssdf", "startX:" + startX);
                toucheRang = (int) Math.min(height, width);
                Log.d("sss", "toucheRang:" + toucheRang);
                if (startX > middle) {
                    Log.d("sss", "startY:" + startY);
                    //获取当前的音量
                    mVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    Log.d("sssm", "mVolume:" + mVolume);
                }
                break;
            case MotionEvent.ACTION_MOVE://手指在屏幕移动的时候回调这个方法
                // 来到新的坐标
                    float endY = event.getRawY();
                    //计算偏移
                    float distanceY = startY - endY;
                    Log.d("sss", "distanceY:" + distanceY);
                if (startX > middle) {
                    //改变的音量 = 最大声音 * (滑动距离 / 滑动的最大区域)
                    int delta = (int) ((distanceY / toucheRang) * maxVolum);
                    Log.d("sssd", "delta:" + delta);
                    if (delta != 0) {
                        //具体的音量 = 当前的音量 + 改变的音量
//                    int volumuAdd = Math.min(Math.max(mVolume + delta, 0), maxVolum);
                        volumuAdd = Math.min(mVolume + delta, maxVolum);
                        Log.d("sss", "volumu:" + volumuAdd);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumuAdd, AudioManager.ADJUST_RAISE);
                    }
//                if (delta < 0) {
////                    int volumuReduction = Math.min(Math.max(mVolume + delta, 0), maxVolum);
//                    int volumuReduction = Math.max(mVolume + delta, 0);
//                    Log.d("sssf", "volumuReduction:" + volumuReduction);
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumuReduction, AudioManager.ADJUST_LOWER);
//                }
                }else {
                    Log.d("ddff", "distanceY/ toucheRang:" + (distanceY / toucheRang));
                    if(brightness == null){
                        brightness = (Brightness) getContext();
                    }
                    brightness.setBrightness(distanceY, toucheRang);
                }
                break;
            case MotionEvent.ACTION_UP://手指在抬起的时候回调这个方法

                break;
        }

        return true;
    }

    @SuppressLint("SimpleDateFormat")
    private String formatPlayTime(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    private String getPlayTime(int playSecond, int allSecond) {
        String playSecondStr = "00:00";
        String allSecondStr = "00:00";
        if (playSecond > 0) {
            playSecondStr = formatPlayTime(playSecond);
        }
        if (allSecond > 0) {
            allSecondStr = formatPlayTime(allSecond);
        }
        return playSecondStr + "/" + allSecondStr;
    }

    /**
     * 播放样式 展开、缩放
     */
    public enum PageType {
        EXPAND, SHRINK
    }

    /**
     * 播放状态 播放 暂停
     */
    public enum PlayState {
        PLAY, PAUSE
    }

    public enum ProgressState {
        START, DOING, STOP
    }


    public interface MediaControlImpl {
        void onPlayTurn();

        void onPageTurn();

        void onProgressTurn(ProgressState state, int progress);

        void onSelectSrc(int position);

        void onSelectFormat(int position);

        void alwaysShowController();
    }

}
