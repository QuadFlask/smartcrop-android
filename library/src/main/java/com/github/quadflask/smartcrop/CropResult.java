package com.github.quadflask.smartcrop;

import android.graphics.Bitmap;

import java.util.List;

public class CropResult {
    public final Crop topCrop;
    public final List<Crop> crops;
    public final Bitmap debugImage;
    public final Bitmap resultImage;

    private CropResult(Crop topCrop, List<Crop> crops, Bitmap debugImage, Bitmap resultImage) {
        this.topCrop = topCrop;
        this.crops = crops;
        this.debugImage = debugImage;
        this.resultImage = resultImage;
    }

    static CropResult newInstance(Crop topCrop, List<Crop> crops, Bitmap debugImage, Bitmap resultImage) {
        return new CropResult(topCrop, crops, debugImage, resultImage);
    }
}
