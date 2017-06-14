package com.example.leebeomwoo.viewbody_final.ItemGroup;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.leebeomwoo.viewbody_final.CameraUse.AutoFitTextureView;
import com.example.leebeomwoo.viewbody_final.CameraUse.CameraHelper;
import com.example.leebeomwoo.viewbody_final.R;
import com.example.leebeomwoo.viewbody_final.Support.VideoViewCustom;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LeeBeomWoo on 2017-02-13.
 */

public class Item_follow_fragment extends Fragment implements Camera.PreviewCallback {
    public Camera mCamera;
    private MediaRecorder mMediaRecorder;
    private File mOutputFile;
    public VideoView videoView;
    private boolean isRecording = false;
    private static final String TAG = "Item_follow_fragment";
    private final static String FURL = "<html><body><iframe width=\"1280\" height=\"720\" src=\"";
    private final static String BURL = "\" frameborder=\"0\" allowfullscreen></iframe></html></body>";
    private final static String CHANGE = "https://www.youtube.com/embed";

    Boolean record_plag = false; // true = 녹화중, false = 정지
    Boolean play_plag = false; //true = 재생, false = 정지
    Boolean play_record = true; //true = 촬영, false = 재생
    Button play, record, load, camerachange, play_recordBtn;
    WebView webView;
    private final int SELECT_MOVIE = 2;
    public MediaRecorder mediaRecorder;
    public SurfaceHolder surfaceHolder;
    int page_num, witch;
    public static AutoFitTextureView textureView;
    String tr_id, imageUrl, tr_password, URL, section, change, temp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tr_id = getArguments().getString("tr_Id");
            imageUrl = getArguments().getString("itemUrl");
            page_num = getArguments().getInt("page_num");
            temp = getArguments().getString("video");
            Log.d(TAG, "imageUrl : " + "temp : " + temp + "," + "tr_id : " + tr_id + "," + "section : " + section );
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_itemview, container, false);
            textureView = (AutoFitTextureView) view.findViewById(R.id.AutoView);
            textureView.setSurfaceTextureListener(mSurfaceTextureListener);
            webView = (WebView) view.findViewById(R.id.web_movie);
            videoView = (VideoView) view.findViewById(R.id.videoView);
            SeekBar seekBar = (SeekBar) view.findViewById(R.id.alpha_control);
            seekBar.setMax(100);
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            webView.setWebViewClient(new WebViewClient());
            final WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setPluginState(WebSettings.PluginState.ON);
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
            double d = 0.5;
            float f = (float)d;
            webView.setAlpha(f);
            change = temp.replace("https://youtu.be", CHANGE);
            URL = FURL + change + BURL;
            Log.d(TAG, URL);
            webView.loadData(URL, "text/html", "charset=utf-8");
            seekBar.setOnSeekBarChangeListener(alphaChangListener);
        mCamera.startPreview();
        record = (Button) view.findViewById(R.id.record_Btn);
        play = (Button) view.findViewById(R.id.play_Btn);
        load = (Button) view.findViewById(R.id.load_Btn);
        play_recordBtn = (Button) view.findViewById(R.id.play_record);
        camerachange = (Button) view.findViewById(R.id.viewChange_Btn);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(record_plag) {
                    releaseMediaRecorder();
                    record_plag = false;
                } else{
                    if(play_record) {
                        mMediaRecorder.start();
                    }else{
                        //closeCamera();
                        //startRecordingVideo();
                        play_record =true;
                    }
                    record_plag = true;
                }
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                getActivity().startActivityForResult(Intent.createChooser(intent, "Select Video"), SELECT_MOVIE);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play_plag){
                    play_plag = false;
                    Log.d(TAG, "video stop");
                    play.setBackgroundResource(R.drawable.playbutton);
                    videoView.pause();
                }else {
                    play_plag = true;
                    Log.d(TAG, "video play");
                    videoView.start();
                    play.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        camerachange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipit();
            }
        });
        play_recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play_record){
                    videoView.setVisibility(View.VISIBLE);
                    textureView.setVisibility(View.GONE);
                } else{
                    videoView.setVisibility(View.GONE);
                    textureView.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }
    private TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                              int width, int height) {        }


        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            mCamera.release();
        }

    };
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        mCamera.startPreview();
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
                            webView.setAlpha(progress);
                        }
                    });
                }
            }).start();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if(webView != null){
                webView.pauseTimers();
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(webView != null){
                webView.resumeTimers();
            }
        }
    };
    /**
     * The capture button controls all user interaction. When recording, the button click
     * stops recording, releases {@link MediaRecorder} and {@link Camera}. When not recording,
     * it prepares the {@link MediaRecorder} and starts recording.
     *
     * @param view the view generating the event.
     */
    public void onCaptureClick(View view) {
        if (isRecording) {
            // BEGIN_INCLUDE(stop_release_media_recorder)
            releaseMediaRecorder();
            releaseCamera();
            // stop recording and release camera
            try {
                mMediaRecorder.stop();  // stop the recording
            } catch (RuntimeException e) {
                // RuntimeException is thrown when stop() is called immediately after start().
                // In this case the output file is not properly constructed ans should be deleted.
                Log.d(TAG, "RuntimeException: stop() is called immediately after start()");
                //noinspection ResultOfMethodCallIgnored
                mOutputFile.delete();
            }
            releaseMediaRecorder(); // release the MediaRecorder object
            mCamera.lock();         // take camera access back from MediaRecorder
            // inform the user that recording has stopped
            isRecording = false;
            releaseCamera();
            // END_INCLUDE(stop_release_media_recorder)

        } else {
            // BEGIN_INCLUDE(prepare_start_media_recorder)
            prepareVideoRecorder();
            new MediaPrepareTask().execute(null, null, null);
            // END_INCLUDE(prepare_start_media_recorder)
        }
    }
    public void flipit() {
        //myCamera is the Camera object
        if (Camera.getNumberOfCameras()>=2) {
            mCamera.stopPreview();
            mCamera.release();
            //"which" is just an integer flag
            switch (witch) {
                case 0:
                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
                    witch = 1;
                    break;
                case 1:
                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    witch = 0;
                    break;
            }
            try {
                mCamera.setPreviewDisplay(surfaceHolder);
                //"this" is a SurfaceView which implements SurfaceHolder.Callback,
                //as found in the code examples
                mCamera.setPreviewCallback(this);
                mCamera.startPreview();
            } catch (IOException exception) {
                mCamera.release();
                mCamera = null;
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // if we are using MediaRecorder, release it first
        releaseMediaRecorder();
        // release the camera immediately on pause event
        releaseCamera();
    }

    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            // clear recorder configuration
            mMediaRecorder.reset();
            // release the recorder object
            mMediaRecorder.release();
            mMediaRecorder = null;
            // Lock camera for later use i.e taking it back from MediaRecorder.
            // MediaRecorder doesn't need it anymore and we will release it if the activity pauses.
            mCamera.lock();
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            // release the camera for other applications
            mCamera.release();
            mCamera = null;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private boolean prepareVideoRecorder(){

        // BEGIN_INCLUDE (configure_preview)
        mCamera = CameraHelper.getDefaultCameraInstance();

        // We need to make sure that our preview and recording video size are supported by the
        // camera. Query camera to find all the sizes and choose the optimal size given the
        // dimensions of our preview surface.
        Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> mSupportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> mSupportedVideoSizes = parameters.getSupportedVideoSizes();
        Camera.Size optimalSize = CameraHelper.getOptimalVideoSize(mSupportedVideoSizes,
                mSupportedPreviewSizes, textureView.getWidth(), textureView.getHeight());

        // Use the same size for recording profile.
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        profile.videoFrameWidth = optimalSize.width;
        profile.videoFrameHeight = optimalSize.height;

        // likewise for the camera object itself.
        parameters.setPreviewSize(profile.videoFrameWidth, profile.videoFrameHeight);
        mCamera.setParameters(parameters);
        try {
            // Requires API level 11+, For backward compatibility use {@link setPreviewDisplay}
            // with {@link SurfaceView}
            mCamera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (IOException e) {
            Log.e(TAG, "Surface texture is unavailable or unsuitable" + e.getMessage());
            return false;
        }
        // END_INCLUDE (configure_preview)


        // BEGIN_INCLUDE (configure_media_recorder)
        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC );
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(profile);

        // Step 4: Set output file
        mOutputFile = CameraHelper.getOutputMediaFile(CameraHelper.MEDIA_TYPE_VIDEO);
        if (mOutputFile == null) {
            return false;
        }
        mMediaRecorder.setOutputFile(mOutputFile.getPath());
        // END_INCLUDE (configure_media_recorder)

        // Step 5: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }

    /**
     * Asynchronous task for preparing the {@link MediaRecorder} since it's a long blocking
     * operation.
     */
    class MediaPrepareTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            // initialize video camera
            if (prepareVideoRecorder()) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
                mMediaRecorder.start();

                isRecording = true;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                getActivity().finish();
            }
            // inform the user that recording has started

        }
    }
}