package com.github.quadflask.smartcrop.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 0x1234;

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
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }

            final ProgressDialog dialog = ProgressDialog.show(this, "processing...", "", true);
            final long time = System.currentTimeMillis();
            final Bitmap[] selectedImage = new Bitmap[1];

            Observable
                    .create(new Observable.OnSubscribe<CropResult>() {
                        @Override
                        public void call(Subscriber<? super CropResult> subscriber) {
                            Uri selectedImageUri = data.getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();

                            selectedImage[0] = BitmapFactory.decodeFile(filePath);

                            CropResult cropResult = SmartCrop.analyze(Options.DEFAULT, selectedImage[0]);

                            subscriber.onNext(cropResult);
                        }
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<CropResult>() {
                        @Override
                        public void call(CropResult cropResult) {
                            long executionTime = System.currentTimeMillis() - time;
                            tvExec.setText(new DecimalFormat("0.000s").format(executionTime / 1000.));

                            ivSrc.setImageBitmap(selectedImage[0]);
                            ivDebug.setImageBitmap(cropResult.debugImage);
                            ivResult.setImageBitmap(cropResult.resultImage);

                            dialog.hide();
                        }
                    });
        }
    }
}
