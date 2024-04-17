package com.ebmobile.composepermission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.ebmobile.composepermission.ext.findActivity
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun ComposePermission(
    permissions: List<String>,
    permissionsGranted: () -> Unit,
    permissionsDenied: () -> Unit,
    completedRequest: () -> Unit,
    showPermissionPermanentlyDenied: () -> Unit
) {
    when (permissions.size) {
        0 -> {
            throw IllegalArgumentException("Permission list is empty")
        }

        1 -> {
            SinglePermission(
                permission = permissions[0],
                granted = permissionsGranted,
                denied = permissionsDenied,
                done = completedRequest,
                showPermissionPermanentlyDenied = showPermissionPermanentlyDenied
            )
        }

        else -> {
            MultiplePermission(
                permissions = permissions,
                granted = permissionsGranted,
                denied = permissionsDenied,
                done = completedRequest,
                showPermissionPermanentlyDenied = showPermissionPermanentlyDenied
            )
        }


    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun SinglePermission(
    permission: String,
    granted: () -> Unit,
    denied: () -> Unit,
    done: () -> Unit,
    showPermissionPermanentlyDenied: () -> Unit

) {
    val context = LocalContext.current
    val activity = context.findActivity() ?: return
    val permissionState =
        rememberPermissionState(permission = permission, onPermissionResult = { isGranted ->

            val permissionPermanentlyDenied = !ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permission
            ) && !isGranted

            when {
                permissionPermanentlyDenied -> {
                    showPermissionPermanentlyDenied()
                    done()
                }

                isGranted.not() -> {
                    denied()
                    done()
                }
            }

        })

    if (permissionState.status.isGranted) {
        granted()
        done()
    } else {
        LaunchedEffect(key1 = Unit) {
            permissionState.launchPermissionRequest()

        }
    }


}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MultiplePermission(
    permissions: List<String>,
    granted: () -> Unit,
    denied: () -> Unit,
    done: () -> Unit,
    showPermissionPermanentlyDenied: () -> Unit

) {
    val context = LocalContext.current
    val activity = context.findActivity() ?: return
    val multiplePermissionState =
        rememberMultiplePermissionsState(
            permissions = permissions,
            onPermissionsResult = { permissionsResult ->
                var isGranted = true

                val permissionPermanentlyDenied = permissionsResult.filter {
                    if (it.value.not()) {
                        isGranted = false
                    }
                    !ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        it.key
                    ) && it.value.not()
                }.isNotEmpty()

                when {
                    permissionPermanentlyDenied -> {
                        showPermissionPermanentlyDenied()
                        done()
                    }

                    isGranted.not() -> {
                        denied()
                        done()
                    }
                }

            })

    if (multiplePermissionState.allPermissionsGranted) {
        granted()
        done()
    } else {
        LaunchedEffect(key1 = Unit) {
            multiplePermissionState.launchMultiplePermissionRequest()

        }
    }


}