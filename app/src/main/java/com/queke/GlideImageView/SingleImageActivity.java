package com.queke.GlideImageView;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.queke.GlideImageView.progress.CircleProgressView;
import com.queke.GlideImageView.progress.OnProgressListener;

public class SingleImageActivity extends AppCompatActivity {
    private static final String TAG = "SingleImageActivity";
    public static final String KEY_IMAGE_URL = "image_url";
    public static final String KEY_IMAGE_URL_THUMBNAIL = "image_url_thumbnail";

    GlideImageView glideImageView;
    CircleProgressView progressView;

    String image_url;
    String image_url_thumbnail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);

        image_url = getIntent().getStringExtra(KEY_IMAGE_URL);
        image_url_thumbnail = getIntent().getStringExtra(KEY_IMAGE_URL_THUMBNAIL);

        glideImageView = findViewById(R.id.glideImageView);
        progressView = findViewById(R.id.progressView);
        glideImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        loadImage();
    }

    private void loadImage() {
        glideImageView.load(image_url, R.color.transparent, new OnProgressListener() {
            @Override
            public void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes) {
                if (isComplete) {
                    progressView.setVisibility(View.GONE);
                } else {
                    progressView.setVisibility(View.VISIBLE);
                    progressView.setProgress(percentage);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
}