package com.example.ywq9682.eyepetizer.my;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.base.BaseFragment;
import com.example.ywq9682.eyepetizer.base.SingleLiteOrm;
import com.example.ywq9682.eyepetizer.main.MyApp;
import com.example.ywq9682.eyepetizer.welcome.Users;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private ImageView headImage, headImageTrue;
    private PopupWindow popupWindow;
    private ImageView mMap;
    private TextView returnTv;
    Users users;
    BmobUser bmobUser;
    private BroadCast broadCast;
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private BroadCasst broadCasst;
    private BreadCast breadCast;
    private static final String TAG = "MyActivity";
    private static final int ALBUM_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 4;


    @Override
    public int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        broadCast = new BroadCast();
        broadCasst = new BroadCasst();
        breadCast = new BreadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.ywq9682.eyepetizer.ILK");
        intentFilter.addAction("com.example.ywq9682.eyepetizer.ILKL");
        intentFilter.addAction("com.example.ywq9682.eyepetizer.ILKLL");
        context.registerReceiver(breadCast, intentFilter);
        context.registerReceiver(broadCasst, intentFilter);
        context.registerReceiver(broadCast, intentFilter);
        popupWindow = new PopupWindow();
        headImage = (ImageView) view.findViewById(R.id.head_image);
        mMap = (ImageView) view.findViewById(R.id.map);
        returnTv = (TextView) view.findViewById(R.id.return_login);
        headImageTrue = (ImageView) view.findViewById(R.id.head_image_true);
        headImage.setOnClickListener(this);
        mMap.setOnClickListener(this);
        headImageTrue.setOnClickListener(this);
        returnTv.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        users = BmobUser.getCurrentUser(context, Users.class);
        Log.d("lolojj", "users:" + users);
        if (users != null) {
            headImageTrue.setVisibility(View.VISIBLE);
            headImage.setVisibility(View.GONE);
            BmobQuery<Users> query = new BmobQuery<>();
            query.addWhereEqualTo("username", users.getUsername());
            query.findObjects(context, new FindListener<Users>() {
                @Override
                public void onSuccess(List<Users> list) {
                    Bitmap bitmap = users.getImagePhoto();
                    if (bitmap != null) {
                        headImageTrue.setImageBitmap(bitmap);
                        Log.d("OLOLOL", bitmap + "ksjdksdk");
                        //  Toast.makeText(MineInformationActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "aaa");
                    }
                }

                @Override
                public void onError(int i, String s) {
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ALBUM_REQUEST_CODE:
                Log.i(TAG, "相册，开始裁剪");
                Log.i(TAG, "相册 [ " + data + " ]");
                if (data == null) {
                    return;
                }
                startCrop(data.getData());
                break;
            case CAMERA_REQUEST_CODE:
                File picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
                startCrop(Uri.fromFile(picture));
                break;
            case CROP_REQUEST_CODE:
                Log.i(TAG, "相册裁剪成功");
                Log.i(TAG, "裁剪以后 [ " + data + " ]");
                if (data == null) {
                    // TODO 如果之前以后有设置过显示之前设置的图片 否则显示默认的图片
                    return;
                }
                Bundle extras = data.getExtras();
                Log.d(TAG, "extras == null:" + (extras == null));
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                    // 此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                    // eventBus.setImage(photo);
                    headImageTrue.setImageBitmap(photo);
                    // EventBus.getDefault().post(eventBus);


                    // 把图片显示在ImageView控件上
                    //                设置数据保存到bmob
                    Users users = BmobUser.getCurrentUser(context, Users.class);
                    //  collect = BmobUser.getCurrentUser(this, Collect.class);
                    Matrix matrix = new Matrix();
                    matrix.setScale(0.5f, 0.5f);
                    photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, false);
                    users.setImagePhoto(photo);
//                  SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(users);
                    users.update(context, new UpdateListener() {
                        @Override
                        public void onSuccess() {
//                            Log.d(TAG, "成功");
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Log.d(TAG, s);
                        }
                    });
                    mygetName();
                }
                break;
            default:
                break;
        }
    }

    //    获取数据显示到对应的位置
    public void mygetName() {
        users = BmobUser.getCurrentUser(context, Users.class);
        if (users != null) {
            BmobQuery<Users> query = new BmobQuery<>();
            query.addWhereEqualTo("username", users.getUsername());
            query.findObjects(context, new FindListener<Users>() {
                @Override
                public void onSuccess(List<Users> list) {
                    Bitmap bitmap = users.getImagePhoto();
                    if (bitmap != null) {
                        headImageTrue.setImageBitmap(bitmap);
                        //  Toast.makeText(MineInformationActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "aaa");
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }

    /**
     * 开始裁剪
     *
     * @param uri
     */
    private void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");// 调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");// 进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_image:
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);


                break;


            case R.id.head_image_true:

                if (popupWindow != null || !popupWindow.isShowing()) {
                    popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    View view1 = LayoutInflater.from(context).inflate(R.layout.change_headimage_popwindow, null);
                    TextView headimageTv = (TextView) view1.findViewById(R.id.change_headimage);
                    TextView cancelTv = (TextView) view1.findViewById(R.id.cancel);
                    headimageTv.setOnClickListener(this);
                    cancelTv.setOnClickListener(this);
                    popupWindow.setContentView(view1);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setFocusable(false);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 0.6f;
                    getActivity().getWindow().setAttributes(lp);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                            lp.alpha = 1f;
                            getActivity().getWindow().setAttributes(lp);
                        }
                    });
                }

                break;
            case R.id.map:


                break;
            case R.id.return_login:

                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().deleteAll(Users.class);
                BmobUser bmobUser = BmobUser.getCurrentUser(context);

                bmobUser.logOut(MyApp.context);

                Toast.makeText(context, "退出", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("是否退出");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent("com.example.ywq9682.eyepetizer.ILKL");
                        context.sendBroadcast(intent);


                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(context, "杨文庆", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;

            case R.id.cancel:

                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();

                break;
            case R.id.change_headimage:


                Intent intent3 = new Intent(Intent.ACTION_PICK, null);
                intent3.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent3, ALBUM_REQUEST_CODE);


                break;


        }
    }

    class BroadCast extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            BmobUser bmobUser = BmobUser.getCurrentUser(context);
            if (bmobUser != null) {
                headImageTrue.setVisibility(View.VISIBLE);
                headImage.setVisibility(View.GONE);

            } else {
                headImageTrue.setVisibility(View.GONE);
                headImage.setVisibility(View.VISIBLE);
            }


        }
    }

    class BroadCasst extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            BmobUser bmobUser = BmobUser.getCurrentUser(context);
            if (bmobUser != null) {
                headImageTrue.setVisibility(View.VISIBLE);
                headImage.setVisibility(View.GONE);

            } else {

                headImageTrue.setVisibility(View.GONE);
                headImage.setVisibility(View.VISIBLE);
            }
        }


    }

    class BreadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

//            headImageTrue.setVisibility(View.VISIBLE);
//            headImage.setVisibility(View.GONE);


        }
    }

    @Override
    public void onDestroy() {
        context.unregisterReceiver(broadCast);
        context.unregisterReceiver(broadCasst);
        context.unregisterReceiver(breadCast);
        super.onDestroy();
    }
}
