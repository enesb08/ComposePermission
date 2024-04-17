package com.ebmobile.composepermission

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebmobile.composepermission.ui.theme.ComposePermissionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePermissionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Samples()
                }
            }
        }
    }
}


@Composable
fun Samples() {

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(30.dp))
        Sample1()
        Spacer(modifier = Modifier.size(10.dp))
        Sample2()
        Spacer(modifier = Modifier.size(10.dp))
    }

}

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePermissionTheme {
        Samples()
    }
}
