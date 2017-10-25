package seoulnightmarket.seoulnightmarket.etc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Yookmoonsu on 2017-10-25.
 */

public class TaskService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Singleton.getInstance().setLoginState(false);
        Log.e("Test", String.valueOf(Singleton.getInstance().getLoginState()));

        stopSelf();
    }
}