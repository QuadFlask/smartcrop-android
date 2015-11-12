package com.github.quadflask.smartcrop.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.quadflask.smartcrop.CropResult;
import com.github.quadflask.smartcrop.Options;
import com.github.quadflask.smartcrop.SmartCrop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.EasyImage;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.iv_src)
    ImageView ivSrc;
    @Bind(R.id.iv_debug)
    ImageView ivDebug;
    @Bind(R.id.iv_result)
    ImageView ivResult;
    @Bind(R.id.tv_exec)
    TextView tvExec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_open)
    public void onClick(View v) {
        EasyImage.openGalleryPicker(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source) {
                try {
                    final Bitmap selectedImage = BitmapFactory.decodeStream(new FileInputStream(imageFile));
                    final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "processing...", "", true);
                    final long time = System.currentTimeMillis();
                    SmartCrop.analyzeWithObservable(Options.DEFAULT, selectedImage)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<CropResult>() {
                                @Override
                                public void call(CropResult cropResult) {
                                    long executionTime = System.currentTimeMillis() - time;
                                    tvExec.setText(new DecimalFormat("0.000s").format(executionTime / 1000.));

                                    ivSrc.setImageBitmap(selectedImage);
                                    ivDebug.setImageBitmap(cropResult.debugImage);
                                    ivResult.setImageBitmap(cropResult.resultImage);

                                    dialog.hide();
                                }
                            });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source) {
            }

            @Override
            public void onCanceled(EasyImage.ImageSource imageSource) {
            }
        });
    }
}
