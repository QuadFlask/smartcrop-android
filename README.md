# smartcrop-android
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-smartcrop--android-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2871)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-15+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)](http://opensource.org/licenses/MIT)

smartcrop implementation in Java(Android)

This library will analyze *best crop position and size* by calculating some features; edge, skin tone, staturation and face.

> [original javascript version](https://github.com/jwagner/smartcrop.js/)

## Screenshot

![Sample app screenshot 1](https://github.com/QuadFlask/smartcrop-android/blob/master/captures/device-2015-11-16-235416.png?raw=true)

![Sample app screenshot 2](https://github.com/QuadFlask/smartcrop-android/blob/master/captures/device-2015-11-16-235553.png?raw=true)


## Usage

### With [RxJava](https://github.com/ReactiveX/RxJava)(+[RxAndroid](https://github.com/ReactiveX/RxAndroid))

```java

SmartCrop
    .analyzeWithObservable(Options.newInstance().cropSquareSize(200), selectedImage)
    .subscribeOn(Schedulers.computation())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Action1<CropResult>() {
        @Override
        public void call(CropResult cropResult) {
            // do what you want
        }
    });

```

### Without Rx

> But I recommend using RxJava or background thread because it took some times(about 500ms on 2013 Nexus5) so that it may stop the UI refresh and make bad UX.

```java
// this code will block about 500ms
CropResult cropResult = SmartCrop.analyze(Options.newInstance().cropSquareSize(200), selectedImage);

```

#### Output

[CropResult.java](https://github.com/QuadFlask/smartcrop-android/blob/master/library/src/main/java/com/github/quadflask/smartcrop/CropResult.java)

```java
public class CropResult {
    public final Crop topCrop; // The best cropx, y, width, height and score
    public final List<Crop> crops; // crop candidates
    public final Bitmap debugImage;
    public final Bitmap resultImage; // cropped image from original image. Size is same with options.cropWidth/Height
}

```


## How to add dependency?

This library is not released in Maven Central, but instead you can use [JitPack](https://jitpack.io)

add remote maven url

```groovy
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

then add a library dependency

```groovy
dependencies {
    compile 'com.github.QuadFlask:smartcrop-android:v.0.0.0'
}
```

or, you can manually download `aar` and put into your project's `libs` directory.

and add dependency

```groovy
dependencies {
    compile(name:'[arrFileName]', ext:'aar')
}
```

> check out [latestVersion] at [releases](https://github.com/QuadFlask/smartcrop-android/releases)


## Features

skin, edge, staturation is almost same with javascript version. Android's face detection is used which draw white circle at face


## License

```
The MIT License (MIT)

Copyright 2014-2015 QuadFlask

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
