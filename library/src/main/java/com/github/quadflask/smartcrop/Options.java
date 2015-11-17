package com.github.quadflask.smartcrop;

import android.graphics.Bitmap;

/**
 * Created by flask on 2015. 10. 30..
 */
public class Options {
    public static final Options DEFAULT = new Options();

    protected int cropWidth = 100;
    protected int cropHeight = 100;
    protected float detailWeight = .2f;
    protected float[] skinColor = {0.7f, 0.57f, 0.44f};
    protected float skinBias = .01f;
    protected float skinBrightnessMin = 0.2f;
    protected float skinBrightnessMax = 1.0f;
    protected float skinThreshold = 0.8f;
    protected float skinWeight = 1.8f;
    protected float saturationBrightnessMin = 0.05f;
    protected float saturationBrightnessMax = 0.9f;
    protected float saturationThreshold = 0.4f;
    protected float saturationBias = 0.2f;
    protected float saturationWeight = 0.3f;
    // step * minscale rounded down to the next power of two should be good
    protected int scoreDownSample = 8;
    //	private int step = 8;
    protected float scaleStep = 0.1f;
    protected float minScale = 0.8f;
    protected float maxScale = 1.0f;
    protected float edgeRadius = 0.4f;
    protected float edgeWeight = -20f;
    protected float outsideImportance = -.5f;
    protected boolean ruleOfThirds = false;
    protected Bitmap.Config bitmapConfig = Bitmap.Config.RGB_565; // for face detection
    protected int maxFaceCount = 3;
    protected int analyzeSizeLimit = 512; // for speed up

    public static class Instance extends Options {
        public Options cropWidth(int cropWidth) {
            this.cropWidth = cropWidth;
            return this;
        }

        public Options cropHeight(int cropHeight) {
            this.cropHeight = cropHeight;
            return this;
        }

        public Options cropSquareSize(int size) {
            this.cropWidth = size;
            this.cropHeight = size;
            return this;
        }

        public Options detailWeight(float detailWeight) {
            this.detailWeight = detailWeight;
            return this;
        }

        public Options skinColor(float[] skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        public Options skinBias(float skinBias) {
            this.skinBias = skinBias;
            return this;
        }

        public Options skinBrightnessMin(float skinBrightnessMin) {
            this.skinBrightnessMin = skinBrightnessMin;
            return this;
        }

        public Options skinBrightnessMax(float skinBrightnessMax) {
            this.skinBrightnessMax = skinBrightnessMax;
            return this;
        }

        public Options skinThreshold(float skinThreshold) {
            this.skinThreshold = skinThreshold;
            return this;
        }

        public Options skinWeight(float skinWeight) {
            this.skinWeight = skinWeight;
            return this;
        }

        public Options saturationBrightnessMin(float saturationBrightnessMin) {
            this.saturationBrightnessMin = saturationBrightnessMin;
            return this;
        }

        public Options saturationBrightnessMax(float saturationBrightnessMax) {
            this.saturationBrightnessMax = saturationBrightnessMax;
            return this;
        }

        public Options saturationThreshold(float saturationThreshold) {
            this.saturationThreshold = saturationThreshold;
            return this;
        }

        public Options saturationBias(float saturationBias) {
            this.saturationBias = saturationBias;
            return this;
        }

        public Options saturationWeight(float saturationWeight) {
            this.saturationWeight = saturationWeight;
            return this;
        }

        public Options scoreDownSample(int scoreDownSample) {
            this.scoreDownSample = scoreDownSample;
            return this;
        }

        public Options scaleStep(float scaleStep) {
            this.scaleStep = scaleStep;
            return this;
        }

        public Options minScale(float minScale) {
            this.minScale = minScale;
            return this;
        }

        public Options maxScale(float maxScale) {
            this.maxScale = maxScale;
            return this;
        }

        public Options edgeRadius(float edgeRadius) {
            this.edgeRadius = edgeRadius;
            return this;
        }

        public Options edgeWeight(float edgeWeight) {
            this.edgeWeight = edgeWeight;
            return this;
        }

        public Options outsideImportance(float outsideImportance) {
            this.outsideImportance = outsideImportance;
            return this;
        }

        public Options ruleOfThirds(boolean ruleOfThirds) {
            this.ruleOfThirds = ruleOfThirds;
            return this;
        }

        public Options setBitmapConfig(Bitmap.Config bitmapConfig) {
            this.bitmapConfig = bitmapConfig;
            return this;
        }

        public Options maxFacesCount(int maxFaceCount) {
            this.maxFaceCount = maxFaceCount;
            return this;
        }

        public Options analyzeSizeLimit(int analyzeSizeLimit) {
            this.analyzeSizeLimit = analyzeSizeLimit;
            return this;
        }
    }

    public static Options.Instance newInstance() {
        return new Options.Instance();
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public int getCropHeight() {
        return cropHeight;
    }

    public float getDetailWeight() {
        return detailWeight;
    }

    public float[] getSkinColor() {
        return skinColor;
    }

    public float getSkinBias() {
        return skinBias;
    }

    public float getSkinBrightnessMin() {
        return skinBrightnessMin;
    }

    public float getSkinBrightnessMax() {
        return skinBrightnessMax;
    }

    public float getSkinThreshold() {
        return skinThreshold;
    }

    public float getSkinWeight() {
        return skinWeight;
    }

    public float getSaturationBrightnessMin() {
        return saturationBrightnessMin;
    }

    public float getSaturationBrightnessMax() {
        return saturationBrightnessMax;
    }

    public float getSaturationThreshold() {
        return saturationThreshold;
    }

    public float getSaturationBias() {
        return saturationBias;
    }

    public float getSaturationWeight() {
        return saturationWeight;
    }

    public int getScoreDownSample() {
        return scoreDownSample;
    }

    public float getScaleStep() {
        return scaleStep;
    }

    public float getMinScale() {
        return minScale;
    }

    public float getMaxScale() {
        return maxScale;
    }

    public float getEdgeRadius() {
        return edgeRadius;
    }

    public float getEdgeWeight() {
        return edgeWeight;
    }

    public float getOutsideImportance() {
        return outsideImportance;
    }

    public boolean isRuleOfThirds() {
        return ruleOfThirds;
    }

    public Bitmap.Config getBitmapConfig() {
        return bitmapConfig;
    }

    public int getMaxFaceCount() {
        return maxFaceCount;
    }

    public int getAnalyzeSizeLimit() {
        return analyzeSizeLimit;
    }
}