# Android-Extensions
Android Extensions to handle several components, since resources, context and activity.

## Install Guide
Follow below step in order to configure Android Extensions module in your application.

### Settings Gradle File
In order to use this module in other projects you just need to add the following entry to your repositories settings:
```groovy
repositories {
    //...
    maven { url 'https://jitpack.io' }
    //...
}
```

### App Build Gradle File
And after remember to add the dependency in your build.gradle file in app folder.
```groovy
dependencies {
    // ...
    // Android Extensions 
    implementation "com.github.jobernas:Android-Extensions:${latestVersion}"

    // ...   
}
```
For more details check the following link [here](https://jitpack.io/#jobernas/Android-Extensions)

## Publish Artifact
In order to publish the artifact you need to create a tag with the version and send it to git.
After this jitpack will be created and you can publish it to Maven.

## Changelog
    - v1.0.0
        - ExtensionsWrapper
        - ResourcesExtensions - handles convertion from id ressources to objects correcponding to the type of object.
        - DimensExtensions - handles several android dimens by code.
    - v1.0.1
        - ContextExtensions - handles objects that need context to work.
    - v1.0.3
        - DrawableExtensions - handles Drawables Objects, tint method for example.
    - v1.0.4
        - LayoutParamsExtensions - handles ViewGroup.LayoutParams as Several Types ViewGroup.LayoutParams;
    - v1.0.7
         - Several fixes in ExtensionsWrapper initialisation;
         - Updated AndroidX Libraries versions to latest;


## Contributors
Any doubt about this project you should contact any of the following contributors:
- João Luís aka: [jobernas](https://github.com/jobernas)
