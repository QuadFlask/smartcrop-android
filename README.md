# smartcrop-android
smartcrop implementation in Java(Android)

## Screenshot



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
	compile 'com.github.QuadFlask:colorpicker:[latestVersion]'
}
```

or, you can manually download `aar` and put into your project's `libs` directory.

and add dependency

```groovy
dependencies {
	compile(name:'[arrFileName]', ext:'aar')
}
```

> check out [latestVersion] at [releases](https://github.com/QuadFlask/colorpicker/releases)

## Usage


## Performance


## License

```
Copyright 2014-2015 QuadFlask

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
