package com.example.leebeomwoo.viewbody_final.ItemGroup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.leebeomwoo.viewbody_final.Adapter.StableArrayAdapter;
import com.example.leebeomwoo.viewbody_final.CameraUse.AutoFitTextureView;
import com.example.leebeomwoo.viewbody_final.CameraUse.CameraHelper;
import com.example.leebeomwoo.viewbody_final.ItemViewActivity;
import com.example.leebeomwoo.viewbody_final.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import cn.gavinliu.android.lib.scale.ScaleFrameLayout;
import cn.gavinliu.android.lib.scale.ScaleRelativeLayout;
import cn.gavinliu.android.lib.scale.helper.ScaleLayoutHelper;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;


/**
 * Created by LeeBeomWoo on 2017-02-15.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class Item_follow_fragment_21 extends Fragment
        implements FragmentCompat.OnRequestPermissionsResultCallback {
    private static final int SENSOR_ORIENTATION_DEFAULT_DEGREES = 90;
    private static final int SENSOR_ORIENTATION_INVERSE_DEGREES = 270;
    private static final SparseIntArray DEFAULT_ORIENTATIONS = new SparseIntArray();
    private static final SparseIntArray INVERSE_ORIENTATIONS = new SparseIntArray();
    ImageButton play, record, load, camerachange, play_recordBtn;
    private final static String FURL = "<html><body><iframe width=\"1280\" height=\"720\" src=\"";
    private final static String BURL = "\" frameborder=\"0\" allowfullscreen></iframe></html></body>";
    private final static String CHANGE = "https://www.youtube.com/embed";
    private final int SELECT_MOVIE = 2;
    private OrientationEventListener mOrientationListener;
    private static final int REQUEST_CODE = 6384; // onActivityResult request
    private static final String TAG = "Item_follow_fragment_21";
    private static final int REQUEST_VIDEO_PERMISSIONS = 1;
    private static final String FRAGMENT_DIALOG = "dialog";
    //ScaleRelativeLayout bTnLayout;
    //ScaleFrameLayout cameraLayout;
    RelativeLayout main, bTnLayout;
    FrameLayout cameraLayout;
    int page_num;
   // WebView.LayoutParams deFaultWebView;
   // ScaleFrameLayout.LayoutParams deFaultCamera;
   // ScaleRelativeLayout.LayoutParams deFaultButton;
    RelativeLayout.LayoutParams LandButton, LandCamera, LandWebView, deFaultButton, deFaultCamera, deFaultWebView, mainlayout;
    Boolean play_record = true; //true 가 촬영모드, false 가 재생모드
    public static final String CAMERA_FRONT = "1";
    public static final String CAMERA_BACK = "0";
    public String change, temp, videoString;
    public Uri videopath;
    public int videoPosition;
    private String cameraId = CAMERA_FRONT;
    ItemViewActivity activity = (ItemViewActivity)getActivity();
    public VideoView videoView;
    SeekBar seekBar;
    private static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };
    private static final String[] FILE_ACCESSPERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    static {
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_0, 90);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_90, 0);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_180, 270);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    static {
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_0, 270);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_90, 180);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_180, 90);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_270, 0);
    }
    /**
     * An {@link AutoFitTextureView} for camera preview.
     */
    public static AutoFitTextureView mTextureView;
    WebView webView;
    View view;

    /**
     * Button to record video
     */

    /**
     * A refernce to the opened {@link CameraDevice}.
     */
    private CameraDevice mCameraDevice;

    String tr_id, imageUrl, tr_password, URL;
    /**
     * A reference to the current {@link CameraCaptureSession} for
     * preview.
     */
    private CameraCaptureSession mPreviewSession;

    /**
     * {@link TextureView.SurfaceTextureListener} handles several lifecycle events on a
     * {@link TextureView}.
     */
    private TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                              int width, int height) {
            openCamera(width, height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                int width, int height) {
            configureTransform(width, height);
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }

    };

    public Item_follow_fragment_21() {
    }

    /**
     * The {@link Size} of camera preview.
     */
    private Size mPreviewSize;

    /**
     * The {@link Size} of video recording.
     */
    private Size mVideoSize;

    /**
     * MediaRecorder
     */
    private MediaRecorder mMediaRecorder;
    private MediaController mMediaController;
    /**
     * Whether the app is recording video now
     */
    public boolean mIsRecordingVideo;

    /**
     * An additional thread for running tasks that shouldn't block the UI.
     */
    private HandlerThread mBackgroundThread;

    /**
     * A {@link Handler} for running tasks in the background.
     */
    private Handler mBackgroundHandler;

    /**
     * A {@link Semaphore} to prevent the app from exiting before closing the camera.
     */
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);

    /**
     * {@link CameraDevice.StateCallback} is called when {@link CameraDevice} changes its status.
     */
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            startPreview();
            mCameraOpenCloseLock.release();
            if (null != mTextureView) {
                configureTransform(mTextureView.getWidth(), mTextureView.getHeight());
            }
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice cameraDevice, int error) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
            Activity activity = getActivity();
            if (null != activity) {
                activity.finish();
            }
        }

    };
    private Integer mSensorOrientation;
    private String mNextVideoAbsolutePath;
    private CaptureRequest.Builder mPreviewBuilder;
    private Surface mRecorderSurface;
    public static Item_follow_fragment_21 newInstance(String tr_id, String imageUrl, int section) {
        Item_follow_fragment_21 item_follow_fragment_21 = new Item_follow_fragment_21();
        Bundle bundle = new Bundle();
        bundle.putString("tr_id", tr_id);
        bundle.putString("imageUrl", imageUrl);
        bundle.putInt("section", section);
        item_follow_fragment_21.setArguments(bundle);
        Log.d("프래그먼트 호출:", imageUrl);
        return item_follow_fragment_21;
    }

    /**
     * In this sample, we choose a video size with 3x4 aspect ratio. Also, we don't use sizes
     * larger than 1080p, since MediaRecorder cannot handle such a high-resolution video.
     * 이 샘플에서는 가로 세로 비율이 3x4 인 비디오 크기를 선택합니다. 또한 크기를 사용하지 않습니다.
     * MediaRecorder가 이러한 고해상도 비디오를 처리 할 수 없기 때문에 1080p보다 큽니다.
     * @param choices The list of available sizes
     * @return The video size
     */
    private static Size chooseVideoSize(Size[] choices) {
        for (Size size : choices) {
            if (size.getWidth() == size.getHeight() * 16 / 9 && size.getWidth() <= 1080) {
                return size;
            }
        }
        Log.e(TAG, "Couldn't find any suitable video size");
        return choices[choices.length - 1];
    }

    /**
     * Given {@code choices} of {@code Size}s supported by a camera, chooses the smallest one whose
     * width and height are at least as large as the respective requested values, and whose aspect
     * ratio matches with the specified value.
     *
     * 녹화가 가능한 사이즈들 중에서 프리뷰로 보여줄 수 있는 사이즈를 선택한다.
     * @param choices     The list of sizes that the camera supports for the intended output class
     * @param width       The minimum desired width
     * @param height      The minimum desired height
     * @param aspectRatio The aspect ratio
     * @return The optimal {@code Size}, or an arbitrary one if none were big enough
     */
    private static Size chooseOptimalSize(Size[] choices, int width, int height, Size aspectRatio) {
        // Collect the supported resolutions that are at least as big as the preview Surface
        List<Size> bigEnough = new ArrayList<Size>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }

        // Pick the smallest of those, assuming we found any
        if (bigEnough.size() > 0) {
            return Collections.max(bigEnough, new CompareSizesByArea());
        } else {
            Log.e(TAG, "Couldn't find any suitable preview size");
            return choices[0];
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
        if(getArguments() !=null){
            tr_id = getArguments().getString("tr_Id");
            imageUrl = getArguments().getString("itemUrl");
            page_num = getArguments().getInt("page_num");
            temp = getArguments().getString("video");
            change = temp.replace("https://youtu.be", CHANGE);
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d(TAG, "onAttachFragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        if(savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        if(activity !=null){
            activity.videoPath = videoString;
            if(videoView.isPlaying()){
                videoView.pause();
                videoPosition = videoView.getCurrentPosition();
                activity.videoSeek = videoPosition;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int rotation = display.getRotation();
        switch (rotation){
            case Surface.ROTATION_0: // 원래 모양
                PortrainSet(deFaultCamera, deFaultButton,deFaultWebView);
                seekBar.setVisibility(View.GONE);
                break;
            case Surface.ROTATION_90: // 오른쪽이 위
                LandSet(LandCamera, LandButton, LandWebView);
                seekBar.setVisibility(View.VISIBLE);
                break;

            case Surface.ROTATION_180: // 뒤집은 모양
                PortrainSet(deFaultCamera, deFaultButton,deFaultWebView);
                seekBar.setVisibility(View.GONE);
                break;
            case Surface.ROTATION_270: // 왼쪽이 위
                LandSet(LandCamera, LandButton, LandWebView);
                seekBar.setVisibility(View.VISIBLE);
                break;
        };
        if(videoView.isPlaying()){
            mTextureView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
        }else {
            mTextureView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
        };
        if (mTextureView != null && mTextureView.isAvailable()) {
            configureTransform(mTextureView.getWidth(), mTextureView.getHeight());
        }
    }
    private void LandSet(RelativeLayout.LayoutParams caParams, RelativeLayout.LayoutParams btParams, RelativeLayout.LayoutParams weParams){
        caParams.removeRule(RelativeLayout.ABOVE);
        caParams.removeRule(RelativeLayout.BELOW);
        btParams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        weParams.removeRule(RelativeLayout.ABOVE);
        weParams.removeRule(RelativeLayout.RIGHT_OF);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x; //긴 부분
        int height = size.y; // 짧은 부분
        Log.d("x Length", String.valueOf(width));
        Log.d("y Length", String.valueOf(height));
        mainlayout = new RelativeLayout.LayoutParams(main.getLayoutParams());
        btParams = new RelativeLayout.LayoutParams(bTnLayout.getLayoutParams());
        btParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        btParams.width = 500;
        btParams.height = height;
        play.setRotation(90);
        record.setRotation(90);
        camerachange.setRotation(90);
        load.setRotation(90);
        play_recordBtn.setRotation(90);
        bTnLayout.setLayoutParams(btParams);

        weParams = new RelativeLayout.LayoutParams(webView.getLayoutParams());
        webView.setScaleX(webView.getWidth()/ width);
        webView.setScaleY(webView.getHeight() / height);
        weParams.addRule(RelativeLayout.ABOVE, R.id.button_layout);
        weParams.addRule(RelativeLayout.LEFT_OF, R.id.alpha_control);
        webView.setLayoutParams(weParams);

        caParams = new RelativeLayout.LayoutParams(height-50, width -40);
        caParams.addRule(RelativeLayout.ABOVE, R.id.button_layout);
        cameraLayout.setScaleX(cameraLayout.getWidth()/ width);
        cameraLayout.setScaleY(cameraLayout.getHeight() / height);
        cameraLayout.setLayoutParams(caParams);
    }
    private void PortrainSet(RelativeLayout.LayoutParams caParams, RelativeLayout.LayoutParams btParams, RelativeLayout.LayoutParams weParams){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x; // 짧은 것
        int height = size.y; // 긴 것
        Log.d("x Length", String.valueOf(width));
        Log.d("y Length", String.valueOf(height));

        btParams = new RelativeLayout.LayoutParams(width, 50);
        btParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        bTnLayout.setLayoutParams(btParams);

        play.setRotation(270);
        record.setRotation(270);
        camerachange.setRotation(270);
        load.setRotation(270);
        play_recordBtn.setRotation(270);

        weParams = new RelativeLayout.LayoutParams(width, height/3);
        weParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        webView.setLayoutParams(weParams);

        caParams = new RelativeLayout.LayoutParams(width, height / 3);
        caParams.addRule(RelativeLayout.ABOVE, R.id.web_movie);
        caParams.addRule(RelativeLayout.BELOW, R.id.button_layout);
        cameraLayout.setLayoutParams(caParams);
    }
    @SuppressLint("SetJavaScriptEnabled")
    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if (!hasPermissionsGranted(FILE_ACCESSPERMISSIONS)) {
            requestFilePermissions();
        }
        if (!hasPermissionsGranted(VIDEO_PERMISSIONS)) {
            requestVideoPermissions();
        }
        startBackgroundThread();
        view = inflater.inflate(R.layout.fragment_follow_portrain_itemview, container, false);
        viewSet();
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTextureView.getVisibility() == View.GONE){
                    mTextureView.setVisibility(View.VISIBLE);
                }
                if(mTextureView != null){
                    if(mIsRecordingVideo){
                        stopRecordingVideo();
                        record.setBackgroundResource(R.drawable.record);
                    } else{
                        startRecordingVideo();
                        record.setBackgroundResource(R.drawable.stop);
                    }
                }
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "load click");
                videoString = null;
                videopath = null;
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Video"), SELECT_MOVIE);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoView.isPlaying()){
                    videoView.pause();
                    play.setBackgroundResource(R.drawable.playbutton);
                }else {
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        play_recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play_record){
                    if(mIsRecordingVideo){
                        stopRecordingVideo();
                    }
                    closeCamera();
                    mTextureView.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                } else {
                    if(videoView.isPlaying()){
                        videoView.stopPlayback();
                    }
                    startPreview();
                    mTextureView.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.GONE);
                }
            }
        });
        camerachange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCamera();
            }
        });
        seekBar.setMax(100);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webView.setWebViewClient(new WebViewClient());
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        URL = FURL + change + BURL;
        Log.d(TAG, "temp : " + temp + "," + "tr_id : " + tr_id );
        webView.loadData(URL, "text/html", "charset=utf-8");
        if(videoString != null){
            videoView.setVisibility(View.VISIBLE);
            mTextureView.setVisibility(View.GONE);
            videoView.start();
        }
        if(getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            play_recordBtn.setVisibility(View.GONE);
            mTextureView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.VISIBLE);
            play_recordBtn.setClickable(false);
            webView.setAlpha((float)0.9);
        }else {
            seekBar.setProgress(50);
        }
        seekBar.setOnSeekBarChangeListener(alphaChangListener);
        if (savedInstanceState != null) {
            Log.d("onAttach", "to " + TAG);
            if (savedInstanceState.getString("videopath") != null) {
                videoString = savedInstanceState.getString("videopath");
                videopath = Uri.parse(videoString);
                videoPosition = savedInstanceState.getInt("videoseek");
                videoView.setVideoURI(videopath);
                videoView.setVisibility(View.VISIBLE);
                mTextureView.setVisibility(View.GONE);
                int p, r;
                p = savedInstanceState.getInt("play");
                r = savedInstanceState.getInt("record");
                if (p == 1) {
                    videoView.start();
                }
                if (r == 1) {
                    mIsRecordingVideo = true;
                }
            }
            ButtonImageSetUp();
        } else {
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (videoPosition > 91) {
                        videoView.seekTo(videoPosition);
                        videoView.start();
                    } else {
                        videoView.seekTo(90);
                    }
                }
            });
        }
        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int p, r;
        if(videoView.isPlaying()){
            p = 1;
        } else {
            p = 0;
        }
        if(mIsRecordingVideo){
            r =1;
        }else {
            r =0;
        }
        outState.putString("videopath", videoString);
        outState.putInt("videoseek", videoPosition);
        outState.putInt("play", p);
        outState.putInt("record", r);
        webView.saveState(outState);
    }
    private void viewSet(){
        mTextureView = (AutoFitTextureView) view.findViewById(R.id.AutoView);
        webView = (WebView) view.findViewById(R.id.web_movie);
        record = (ImageButton) view.findViewById(R.id.record_Btn);
        play = (ImageButton) view.findViewById(R.id.play_Btn);
        load = (ImageButton) view.findViewById(R.id.load_Btn);
        videoView = (VideoView) view.findViewById(R.id.videoView);
        play_recordBtn = (ImageButton) view.findViewById(R.id.play_record);
        camerachange = (ImageButton) view.findViewById(R.id.viewChange_Btn);
        seekBar = (SeekBar) view.findViewById(R.id.alpha_control);
        bTnLayout = (RelativeLayout) view.findViewById(R.id.button_layout);
        cameraLayout = (FrameLayout) view.findViewById(R.id.video_layout);
        main = (RelativeLayout) view.findViewById(R.id.item_mainLayout);
    }

    private void ButtonImageSetUp(){
        if(videoView.isPlaying()){
            play.setBackgroundResource(R.drawable.pause);
        }else{
            play.setBackgroundResource(R.drawable.playbutton);
        }
        if(mIsRecordingVideo){
            record.setBackgroundResource(R.drawable.stop);
        }else {
            record.setBackgroundResource(R.drawable.record);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        Log.d("requestCode", String.valueOf(requestCode));
        Log.d("resultCode", String.valueOf(resultCode));
        //if (resultCode != RESULT_OK)
        if (requestCode == 2 && data != null) {
            Uri mVideoURI = data.getData();
            videopath = mVideoURI;
            videoString = videopath.toString();
            Log.d("onActivityResult", mVideoURI.toString());
            Log.d("Result videoString", videoString);
            //Log.d("getRealPathFromURI", getRealPathFromURI(getContext(), mVideoURI));
            videoviewSetup(mVideoURI);
        }
    }

    private void videoviewSetup(Uri path){
        videoView.setVideoURI(path);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ButtonImageSetUp();
        startPreview();
        if(getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            deFaultWebView = new RelativeLayout.LayoutParams(webView.getLayoutParams());
            deFaultButton = new RelativeLayout.LayoutParams(bTnLayout.getLayoutParams());
            deFaultCamera = new RelativeLayout.LayoutParams(cameraLayout.getLayoutParams());
        }
        LandWebView = new RelativeLayout.LayoutParams(webView.getLayoutParams());
        LandButton = new RelativeLayout.LayoutParams(bTnLayout.getLayoutParams());
        LandCamera = new RelativeLayout.LayoutParams(cameraLayout.getLayoutParams());
            Log.d(TAG, "onViewCreated");
    }

    private SeekBar.OnSeekBarChangeListener alphaChangListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                        // 현재 UI 스레드가 아니기 때문에 메시지 큐에 Runnable을 등록 함
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                // 메시지 큐에 저장될 메시지의 내용;
                                double a = progress/100.0;
                                float b = (float)a;
                                webView.setAlpha(b);
                            }
                        });
                    }
            }).start();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if(webView != null){
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(webView != null){
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        startBackgroundThread();
        if (mTextureView.isAvailable()) {
            openCamera(mTextureView.getWidth(), mTextureView.getHeight());
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        closeCamera();
        stopBackgroundThread();
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    /**
     * Starts a background thread and its {@link Handler}.
     */
    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    public void switchCamera() {
        if (cameraId.equals(CAMERA_FRONT)) {
            cameraId = CAMERA_BACK;
            onPause();
            onResume();
        } else if (cameraId.equals(CAMERA_BACK)) {
            cameraId = CAMERA_FRONT;
            onPause();
            onResume();
        }
    }

    /**
     * Stops the background thread and its {@link Handler}.
     */
    private void stopBackgroundThread() {
        if(mBackgroundThread !=null) {
            mBackgroundThread.quitSafely();
            try {
                mBackgroundThread.join();
                mBackgroundThread = null;
                mBackgroundHandler = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets whether you should show UI with rationale for requesting permissions.
     *
     * @param permissions The permissions your app wants to request.
     * @return Whether you can show permission rationale UI.
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Requests permissions needed for recording video.
     */
    private void requestVideoPermissions() {
        if (shouldShowRequestPermissionRationale(VIDEO_PERMISSIONS)) {
            new ConfirmationDialog().show(getActivity().getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(getActivity(), VIDEO_PERMISSIONS, REQUEST_VIDEO_PERMISSIONS);
        }
    }

    private void requestFilePermissions(){
        if (shouldShowRequestPermissionRationale(FILE_ACCESSPERMISSIONS)) {
            new ConfirmationDialog().show(getActivity().getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(getActivity(), FILE_ACCESSPERMISSIONS, REQUEST_VIDEO_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == REQUEST_VIDEO_PERMISSIONS) {
            if (grantResults.length == VIDEO_PERMISSIONS.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        ErrorDialog.newInstance(getString(R.string.permission_request))
                                .show(getChildFragmentManager(), FRAGMENT_DIALOG);
                        break;
                    }
                }
            } else {
                ErrorDialog.newInstance(getString(R.string.permission_request))
                        .show(getChildFragmentManager(), FRAGMENT_DIALOG);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (requestCode == REQUEST_VIDEO_PERMISSIONS) {
            if (grantResults.length == FILE_ACCESSPERMISSIONS.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        ErrorDialog.newInstance(getString(R.string.permission_request))
                                .show(getChildFragmentManager(), FRAGMENT_DIALOG);
                        break;
                    }
                }
            } else {
                ErrorDialog.newInstance(getString(R.string.permission_request))
                        .show(getChildFragmentManager(), FRAGMENT_DIALOG);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean hasPermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tries to open a {@link CameraDevice}. The result is listened by `mStateCallback`.
     */
    private void openCamera(int width, int height) {
        if (!hasPermissionsGranted(VIDEO_PERMISSIONS)) {
            requestVideoPermissions();
            return;
        }
        final Activity activity = getActivity();
        if (null == activity || activity.isFinishing()) {
            return;
        }
        CameraManager manager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
        try {
            Log.d(TAG, "tryAcquire");
            if (!mCameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Time out waiting to lock camera opening.");
            }

            // Choose the sizes for camera preview and video recording
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics
                    .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            mSensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            mVideoSize = chooseVideoSize(map.getOutputSizes(MediaRecorder.class));
            mPreviewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class),
                    width, height, mVideoSize);
/**
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == ORIENTATION_LANDSCAPE) {
                mTextureView.setAspectRatio(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            } else {
                mTextureView.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
            } **/
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == ORIENTATION_PORTRAIT) {
                mTextureView.setAspectRatio(mPreviewSize.getHeight(), mPreviewSize.getWidth());
            }
            configureTransform(width, height);
            mMediaRecorder = new MediaRecorder();
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            manager.openCamera(cameraId, mStateCallback, null);
        } catch (CameraAccessException e) {
            Toast.makeText(activity, "Cannot access the camera.", Toast.LENGTH_SHORT).show();
            activity.finish();
        } catch (NullPointerException e) {
            // Currently an NPE is thrown when the Camera2API is used but not supported on the
            // device this code runs.
            ErrorDialog.newInstance(getString(R.string.camera_error))
                    .show(getActivity().getSupportFragmentManager(), FRAGMENT_DIALOG);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera opening.");
        }
    }

    private void closeCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            closePreviewSession();
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
            if (null != mMediaRecorder) {
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.");
        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    /**
     * Start the camera preview.
     */
    private void startPreview() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == mPreviewSize) {
            return;
        }
        try {
            closePreviewSession();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            Surface previewSurface = new Surface(texture);
            mPreviewBuilder.addTarget(previewSurface);

            mCameraDevice.createCaptureSession(Arrays.asList(previewSurface), new CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(CameraCaptureSession cameraCaptureSession) {
                    mPreviewSession = cameraCaptureSession;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                    Activity activity = getActivity();
                    if (null != activity) {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the camera preview. {@link #startPreview()} needs to be called in advance.
     */
    private void updatePreview() {
        if (null == mCameraDevice) {
            return;
        }
        try {
            setUpCaptureRequestBuilder(mPreviewBuilder);
            HandlerThread thread = new HandlerThread("CameraPreview");
            thread.start();
            mPreviewSession.setRepeatingRequest(mPreviewBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setUpCaptureRequestBuilder(CaptureRequest.Builder builder) {
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
    }

    /**
     * Configures the necessary {@link Matrix} transformation to `mTextureView`.
     * This method should not to be called until the camera preview size is determined in
     * openCamera, or until the size of `mTextureView` is fixed.
     *
     * @param viewWidth  The width of `mTextureView`
     * @param viewHeight The height of `mTextureView`
     */
    private void configureTransform(int viewWidth, int viewHeight) {
        Activity activity = getActivity();
        if (null == mTextureView || null == mPreviewSize || null == activity) {
            return;
        }
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        RectF deviceRect = new RectF(0, 0, width, height);
        RectF viewRect = new RectF(0, 0, viewWidth, viewHeight);
        Log.d("viewRect :", String.valueOf(viewWidth) + "*" + String.valueOf(viewHeight));
        RectF landRect = new RectF(0, 0, mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Log.d("bufferRect :", String.valueOf(mPreviewSize.getWidth()) + "*" + String.valueOf(mPreviewSize.getHeight()));
        float centerX = deviceRect.centerX();
        float centerY = deviceRect.centerY();
        Log.d("center :", String.valueOf(centerX) + "*" + String.valueOf(centerY));
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            Log.d("beforecenter :", String.valueOf(deviceRect.centerX()) + "*" + String.valueOf(deviceRect.centerY()));
            // deviceRect.offset(centerX - deviceRect.centerX(), centerY - deviceRect.centerY());
            Log.d("aftercenter :", String.valueOf(deviceRect.centerX()) + "*" + String.valueOf(deviceRect.centerX()));
            matrix.setRectToRect(deviceRect, deviceRect, Matrix.ScaleToFit.CENTER);
            float scale = Math.max(
                    (float) viewHeight / height,
                    (float) viewWidth / width);
            Log.d("scale :", String.valueOf(scale));
            matrix.postScale(1,  2 , deviceRect.centerX(), deviceRect.centerY());
            Log.d("postScale :", String.valueOf(scale*2) + ":" + String.valueOf(centerX) + ":" + String.valueOf(centerY));
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }
        mTextureView.setTransform(matrix);
        Log.d("mTextureView :", String.valueOf(mTextureView.getWidth()) + "*" + String.valueOf(mTextureView.getHeight()));
    }

    private void setUpMediaRecorder() throws IOException {
        final Activity activity = getActivity();
        if (null == activity) {
            return;
        }
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        if (mNextVideoAbsolutePath == null || mNextVideoAbsolutePath.isEmpty()) {
            mNextVideoAbsolutePath = getVideoFilePath(getActivity());
        }
        mMediaRecorder.setOutputFile(mNextVideoAbsolutePath);
        mMediaRecorder.setVideoEncodingBitRate(10000000);
        mMediaRecorder.setVideoFrameRate(30);
        mMediaRecorder.setVideoSize(mVideoSize.getWidth(), mVideoSize.getHeight());
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        switch (mSensorOrientation) {
            case SENSOR_ORIENTATION_DEFAULT_DEGREES:
                mMediaRecorder.setOrientationHint(DEFAULT_ORIENTATIONS.get(rotation));
                break;
            case SENSOR_ORIENTATION_INVERSE_DEGREES:
                mMediaRecorder.setOrientationHint(INVERSE_ORIENTATIONS.get(rotation));
                break;
        }
        mMediaRecorder.prepare();
    }

    private String getVideoFilePath(Context context) {
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getPath() + "/"
                + "ViewBody_" +System.currentTimeMillis() + ".mp4";
    }

    public void startRecordingVideo() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == mPreviewSize) {
            return;
        }
        try {
            closePreviewSession();
            setUpMediaRecorder();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            List<Surface> surfaces = new ArrayList<>();

            // Set up Surface for the camera preview
            Surface previewSurface = new Surface(texture);
            surfaces.add(previewSurface);
            mPreviewBuilder.addTarget(previewSurface);

            // Set up Surface for the MediaRecorder
            mRecorderSurface = mMediaRecorder.getSurface();
            surfaces.add(mRecorderSurface);
            mPreviewBuilder.addTarget(mRecorderSurface);

            // Start a capture session
            // Once the session starts, we can update the UI and start recording
            mCameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    mPreviewSession = cameraCaptureSession;
                    updatePreview();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // UI
                            mIsRecordingVideo = true;

                            // Start recording
                            mMediaRecorder.start();
                        }
                    });
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Activity activity = getActivity();
                    if (null != activity) {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void closePreviewSession() {
        if (mPreviewSession != null) {
            mPreviewSession.close();
            mPreviewSession = null;
        }
    }

    public void stopRecordingVideo() {
        // UI
        mIsRecordingVideo = false;
        // Stop recording
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        CameraHelper.getOutputMediaFile(2);

        Activity activity = getActivity();
        if (null != activity) {
            Toast.makeText(activity, "Video saved: " + mNextVideoAbsolutePath,
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Video saved: " + mNextVideoAbsolutePath);
        }
        mNextVideoAbsolutePath = null;
        startPreview();
    }

    /**
     * Compares two {@code Size}s based on their areas.
     */
    static class CompareSizesByArea implements Comparator<Size> {

        @Override
        public int compare(Size lhs, Size rhs) {
            // We cast here to ensure the multiplications won't overflow
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }

    }

    public static class ErrorDialog extends DialogFragment {

        private static final String ARG_MESSAGE = "message";

        public static ErrorDialog newInstance(String message) {
            ErrorDialog dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putString(ARG_MESSAGE, message);
            dialog.setArguments(args);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Activity activity = getActivity();
            return new AlertDialog.Builder(activity)
                    .setMessage(getArguments().getString(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            activity.finish();
                        }
                    })
                    .create();
        }

    }

    public static class ConfirmationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Fragment parent = getParentFragment();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.permission_request)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(VIDEO_PERMISSIONS,
                                    REQUEST_VIDEO_PERMISSIONS);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    parent.getActivity().finish();
                                }
                            })
                    .create();
        }

    }
}