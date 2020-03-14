package com.zs.wanandroid.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zs_wan_android.R
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks

class BActivity : AppCompatActivity(), PermissionCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //进来之后，手动调用申请权限的方法，
//建议在用到相关功能的时候，才去请求相关权限，提高用户体验
        RequestLocationAndCallPermission()
    }

    //带有这个注释的方法，会在某一次请求的所有权限都通过后，才回调
    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun RequestLocationAndCallPermission() { //        Toast.makeText(this, "RequestLocationAndCallPermission,out", Toast.LENGTH_SHORT).show();
        val perms =
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) { //用户同意了权限
//进行后面的操作
        } else { //用户拒绝了权限
//正常情况（没有勾选《拒绝后不再询问》）会先弹下面这个框一，点击确定会出现系统的框二
//非正常情况（有勾选《拒绝后不再询问》）框一和系统的框二都不会出现
//无论哪种情况，最终，都会根据用户的选择同意还是拒绝，而回调对应的方法；（勾选过《拒绝后不再询问》，这种情况也是属于拒绝，所以他也会走拒绝的回调）
            EasyPermissions.requestPermissions(
                this,
                "请求定位权限和拨打电话权限",
                WRITE_EXTERNAL_STORAGE,
                *perms
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
// 把请求权限的操作转交给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //一次请求中，只要有一个权限允许了，就会走这个方法
    override fun onPermissionsGranted(
        requestCode: Int,
        perms: List<String>
    ) { //用户同意了部分权限，你可以对权限做判断，根据通过的权限，开放相应功能
    }

    //一次请求中，只要有一个权限拒绝了，就会走这个方法
    override fun onPermissionsDenied(
        requestCode: Int,
        perms: List<String>
    ) { //如果曾经有勾选《拒绝后不再询问》，则会进入下面这个条件
//建议做一个判断，判断用户是不是刚刚勾选的《拒绝后不再询问》，如果是，就不做下面这个判断，而只进行相应提示，这样就可以避免再一次弹框，影响用户体验
//否则就是用户可能在之前曾经勾选过《拒绝后不再询问》，那就可以用下面这个判断，强制弹出一个对话框
        if (EasyPermissions.somePermissionPermanentlyDenied(
                this,
                perms
            )
        ) { //但是这个api有个问题，他会显示一个对话框，但是这个对话框，点空白区域是可以取消的，如果用户点了空白区域，你就没办法进行后续操作了
        }
    }

    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        //EasyPermissions会有一个默认的请求码，根据这个请求码，就可以判断是不是从APP的设置界面过来的
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) { // Do something after user returned from app settings screen, like showing a Toast.
//在这儿，你可以再对权限进行检查，从而给出提示，或进行下一步操作
        }
    }

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }
}