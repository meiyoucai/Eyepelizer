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
import com.example.ywq9682.eyepetizer.main.MyApp;
import com.example.ywq9682.eyepetizer.my.collect.CollectActivity;
import com.example.ywq9682.eyepetizer.tools.Merchant;
import com.example.ywq9682.eyepetizer.tools.OrderUtils;
import com.example.ywq9682.eyepetizer.welcome.Users;
import com.fuqianla.paysdk.FuQianLaPay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by YWQ9682 on 2016/7/16.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private ImageView headImage, headImageTrue;
    private PopupWindow popupWindow;
    private ImageView mMap;
    private TextView theme, collectTv;
    private TextView returnTv, pay;
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
        theme = (TextView) view.findViewById(R.id.buqiyan);
        headImage = (ImageView) view.findViewById(R.id.head_image);
        mMap = (ImageView) view.findViewById(R.id.map);
        collectTv = (TextView) view.findViewById(R.id.my_collect);
        collectTv.setOnClickListener(this);
        pay = (TextView) view.findViewById(R.id.my_pay);
        pay.setOnClickListener(this);
        returnTv = (TextView) view.findViewById(R.id.return_login);
        headImageTrue = (ImageView) view.findViewById(R.id.head_image_true);
        headImage.setOnClickListener(this);
        mMap.setOnClickListener(this);
        theme.setOnClickListener(this);
        headImageTrue.setOnClickListener(this);
        returnTv.setOnClickListener(this);
        mygetName();
    }

    @Override
    public void onResume() {
        super.onResume();
        users = BmobUser.getCurrentUser(context, Users.class);
        // Log.d("lolojj", "users:" + users);
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
                    users = BmobUser.getCurrentUser(context, Users.class);
                    //  collect = BmobUser.getCurrentUser(this, Collect.class);

                    Matrix matrix = new Matrix();
                    matrix.setScale(0.5f, 0.5f);
                    photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, false);
                    users.setImagePhoto(photo);
                    Log.d("MyFragment", "users:" + users);
                    Log.d("MyFragment", "photo:" + photo);
//                  SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(users);
                    users.update(context, new UpdateListener() {
                        @Override
                        public void onSuccess() {
//
                            Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
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
            case R.id.my_collect:

                Intent intent1 = new Intent(context, CollectActivity.class);
                context.startActivity(intent1);

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
            case R.id.buqiyan:


                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

                View view2 = LayoutInflater.from(context).inflate(R.layout.qq_login_popwindow, null);
                ImageView imageViewQQ = (ImageView) view2.findViewById(R.id.qq_image);
                imageViewQQ.setOnClickListener(this);
                builder1.setView(view2);
                builder1.show();
                break;
            case R.id.qq_image:


                break;
            case R.id.my_pay:
//我们在这里继承了支付宝的功能添加了FUQIANLA 的 jar包 在添加一些方法 清单文件加入权限  这里就可以实现功能了
                //alipaysSdk   与fuqianla  JAr 的包
                FuQianLaPay pay = new FuQianLaPay.Builder(getActivity())
                        .partner(Merchant.MERCHANT_NO)//商户号
                        .alipay(true)
                        .orderID(OrderUtils.getOutTradeNo())//订单号
                        .amount(Double.parseDouble("0.01"))//金额
                        .subject("意外怀孕怎么办??到大连天伦不孕不育医院")//商品名称
                        .body("你负责来,我们负责生")//商品交易详情
                        .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                        .build();
                pay.startPay();


                break;
            case R.id.map:
                //显示现在官网上的JAR包  然后打开那里面的一个jar包 输入你需要分享的去处
                //assets  放入src 下的 main包 下面
                // res 下的 drawable   value等 放入res下
                //cn放在android 下的与你工程平级的java下
                //  解压下来之后会出现一个新的包  将里面的内容 挨个放入project中
                //全部放入 切记全部放在对应的包下 之后再清单文件中 注册,
                //在从官网上下载对应的方法,导入即可


                ShareSDK.initSDK(getContext());
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//                oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(getContext());

                break;
            case R.id.return_login:
                // SingleLiteOrm.getSingleLiteOrm().getLiteOrm().deleteAll(Users.class);
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

                        Toast.makeText(context, "取消退出", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                }).show();

                break;

            case R.id.cancel:
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
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
