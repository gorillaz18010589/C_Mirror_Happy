package com.dyaco.spiritbike;
//https://cloud.hpplay.cn:9889/dev/#https://doc.hpplay.com.cn/web/#/1?page_id=24
//https://doc.hpplay.com.cn/web/#/1/25
//1.注測樂播帳號,申請appid,提交packageName,取得sdk
/*2.配置sdk引用Lib     implementation(name: 'sdk-lecast-release', ext: 'aar'),
*  flatDir {
            dirs 'libs'
        }

      ndk {
            abiFilters  "armeabi-v7a"
       }
*
* */
//3.ManiFast加上    xmlns:tools="http://schemas.android.com/tools",tools:replace="android:allowBackup"
//4.packageName當時有註冊上去,估需要一模一樣的PakcageName,或是重新申請一組新的
//5.
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hpplay.sdk.sink.api.CastInfo;
import com.hpplay.sdk.sink.api.ClientInfo;
import com.hpplay.sdk.sink.api.IServerListener;
import com.hpplay.sdk.sink.api.ServerInfo;

public class MainActivity extends AppCompatActivity {
    private AllCast mAllCast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLelink();
    }


    private void initLelink() {
        String appid ="";
        String deviceName ="";
        String secret ="";

        mAllCast = new AllCast(this, appid, secret);
        mAllCast.setDeviceName(deviceName);
        mAllCast.setDisplayFrameRateSwitch(false);
        mAllCast.setServerListener(mServerListener);
        mAllCast.startServer();
    }

    private static final String TAG = "MirroringFragment";
    private IServerListener mServerListener = new IServerListener() {
        @Override
        public void onStart(int id, ServerInfo info) {
            String deviceName2 = "onStart service: " + id + " deviceName: " + info.deviceName;
            Log.d(TAG, "服務發佈成功 deviceName: " + deviceName2);
//            deviceName = info.deviceName;
//            showWifi_DeviceName();
            // ios_bottom_text.setText(getString(isWorkout ? R.string.ios_bottom_text2 : R.string.ios_bottom_text, wifiName, deviceName));
        }

        @Override
        public void onStop(int id) {
            String info = "onStop service: " + id;
            Log.i(TAG, "停止服務 info: " + info);
        }

        @Override
        public void onError(int id, int what, int extra) {
            String info = "onError service: " + id + " what: " + what + " extra: " + extra;
            Log.i(TAG, "服務發佈失敗 info: " + info);
        }

        @Override
        public void onAuthSDK(int id, int status) {
            Log.i(TAG, "onAuthSDK status: " + status);
            switch (status) {
                case IServerListener.SDK_AUTH_SUCCESS:
                    Log.i(TAG, "認證成功");
                    break;
                case IServerListener.SDK_AUTH_FAILED:
                    Log.i(TAG, "認證失败");
                    break;
                case IServerListener.SDK_AUTH_SERVER_FAILED:
                    Log.i(TAG, "連接認證服務器失敗");
                    break;
            }
        }

        @Override
        public void onCast(int id, CastInfo info) {
//            if (!mirrorRun) {
//                CommonUtils.closePackage(mActivity);
//                mirrorRun = true;
//                MyApplication.SSEB = false;
//            }
            //會跑好幾次
//            mActivity.overridePendingTransition(0, 0);
//            Log.d("樂播", "onCast: 開小按鈕");
//
//            ((DashboardActivity) mActivity).showBtnFullScreenExit(5);
        }

        @Override
        public void onAuthConnect(int id, String authCode, int expiry) {
            // 若开启随机投屏码功能，在发送端连接的时候，SDK会将生成的随机投屏码回调到这里
            // authCode 为生成的投屏码，expiry为投屏码的有限时间，单位秒
            String info = "onAuth service: " + id + " authCode: " + authCode + "\nexpiry: " + expiry;
            Log.i("MMMMMM", "投屏碼連接 info: " + info);
        }

        @Override
        public void onConnect(int id, ClientInfo clientInfo) {
            // Log.i("MMMMMM", "新用户連接 " + "onConnect id: " + id + " info:" + clientInfo);
        }

        @Override
        public void onDisconnect(int i, ClientInfo clientInfo) {
            //预留接口，暂未实现
        }
    };

}