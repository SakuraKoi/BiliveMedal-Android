package sakura.kooi.android.bilivemedal.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.widget.Button;

import sakura.kooi.android.bilivemedal.BiliException;
import sakura.kooi.android.bilivemedal.MainActivity;

public class RequestUtils {
    public static boolean assertData(ProgressDialog progressDialog, Button btn, Object obj) {
        if (obj instanceof BiliException) {
            progressDialog.dismiss();
            btn.setEnabled(true);
            new AlertDialog.Builder(MainActivity.context)
                    .setMessage(((BiliException) obj).getMessage())
                    .setNeutralButton("OK", (dialogInterface, i) -> {}).show();
            return true;
        } else if (obj instanceof Throwable) {
            progressDialog.dismiss();
            btn.setEnabled(true);
            new AlertDialog.Builder(MainActivity.context).setTitle("查询出错")
                    .setMessage("发生了网络错误")
                    .setNeutralButton("OK", (dialogInterface, i) -> {}).show();
            return true;
        }
        return false;
    }
}
