package com.coressoft.breader.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.coressoft.breader.utils.XToast;

import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by xingyw on 18-2-8.
 */

public class PermissionActivity extends BaseActivity{
    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    public void onPermissionSuccess() {

    }

    public void onPermissionFail() {
        XToast.showShort(this,"要给权限哦");
    }

    public void getPermission(String... permissions) {

        if (Build.VERSION.SDK_INT >= 23) {

            if (findDeniedPermissions(this, permissions).size() > 0) {
                PermissionGen.with(this)
                        .addRequestCode(100)
                        .permissions(
                                permissions
                        )
                        .request();
            } else {
                onPermissionSuccess();
            }


        } else {
            onPermissionSuccess();
        }

    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    //申请拍照和文件读写权限
    public void getTakePicPermission() {
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        for (int i = 0; i < grantResults.length; i++) {

            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                onPermissionFail();
                return;
            }
        }

        onPermissionSuccess();

    }
}
