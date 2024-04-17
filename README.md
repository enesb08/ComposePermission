
[![](https://jitpack.io/v/enesb08/ComposePermission.svg)](https://jitpack.io/#enesb08/ComposePermission)

ComposePermission , Android uygulamaları için [Jetpack Compose Permissions](https://google.github.io/accompanist/permissions/) kütüphaneisini kullanarak tekli veya çoklu izin isteminize yardımcı olur.

## Nasıl Kullanılır

1. **Projeye Ekleme:**
 - Projenizin `build.gradle` dosyasına aşağıdaki bağımlılığı ekleyin:

 ```gradle
   dependencies {
	 implementation 'com.github.enesb08:ComposePermission:Tag'
   ```
  - Projenizin `settings.gradle` dosyasına aşağıdaki bağımlılığı ekleyin:
  
  
   Eklencek kod:
 ```gradle
 maven {
         url = uri("https://jitpack.io")
     }
  ```
  Eklenecek kısım:
 ```gradle
  dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
  
  ```
1. **Kullanım Örnekleri:**
 -  Sample:1
```kotlin
@Composable
fun Sample1() {


    val permissionList = listOf(android.Manifest.permission.CAMERA)
    var clickedPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current


    Button(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), onClick = {
        clickedPermission = true
    }) {
        Text(text = "Single Permission")
    }

    if (clickedPermission) {
        ComposePermission(permissions = permissionList, permissionsGranted = {
            Log.e("ComposePermission", "permissionsGranted")
            Toast.makeText(
                context, "Permission Granted", Toast.LENGTH_LONG
            ).show()

        }, permissionsDenied = {
            Log.e("ComposePermission", "permissionsDenied")
            Toast.makeText(
                context, "Permission Denied", Toast.LENGTH_LONG
            ).show()
        }, showPermissionPermanentlyDenied = {
            Log.e("ComposePermission", "showPermissionPermanentlyDenied")
            Toast.makeText(
                context, "Permission denied permanently. go to settings", Toast.LENGTH_LONG
            ).show()

        }, completedRequest = {
            clickedPermission = false
        })
    }
}

```
 -  Sample:2
```kotlin
@Composable
fun Sample2() {

    val permissionList = listOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.CAMERA,

        )

    var clickedPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Button(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp), onClick = {
        clickedPermission = true
    }) {
        Text(text = "Multiple Permission")
    }

    if (clickedPermission) {
        ComposePermission(permissions = permissionList, permissionsGranted = {
            Log.e("ComposePermission", "permissionsGranted")
            Toast.makeText(
                context, "Permission Granted", Toast.LENGTH_LONG
            ).show()

        }, permissionsDenied = {
            Log.e("ComposePermission", "permissionsDenied")
            Toast.makeText(
                context, "Permission Denied", Toast.LENGTH_LONG
            ).show()
        }, showPermissionPermanentlyDenied = {
            Log.e("ComposePermission", "showPermissionPermanentlyDenied")
            Toast.makeText(
                context, "Permission denied permanently. go to settings", Toast.LENGTH_LONG
            ).show()

        }, completedRequest = {
            clickedPermission = false
        })
    }


}
```

3. **GIF:**

<p align="start">
 <img src="assets/SpannableText.gif" width="40%"/>
</p>

<div align="start"> <h2 align="start">License</h1> </div>

``` xml

Copyright 2024 enesb08

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
