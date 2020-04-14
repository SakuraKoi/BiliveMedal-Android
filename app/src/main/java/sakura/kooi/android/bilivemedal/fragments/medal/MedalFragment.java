package sakura.kooi.android.bilivemedal.fragments.medal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Collections;

import sakura.kooi.android.bilivemedal.BilibiliAPI;
import sakura.kooi.android.bilivemedal.MainActivity;
import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.activities.MedalActivity;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;
import sakura.kooi.android.bilivemedal.utils.RequestUtils;

public class MedalFragment extends Fragment {

    private MedalViewModel medalViewModel;
    private EditText editUid;
    private Button btnQuery;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        medalViewModel =
                ViewModelProviders.of(this).get(MedalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_medal, container, false);
        editUid = root.findViewById(R.id.editUid);
        btnQuery = root.findViewById(R.id.btnQuery);

        btnQuery.setOnClickListener(this::execute);
        return root;
    }

    private void execute(View view) {
        String uidString = editUid.getText().toString().trim();
        if (uidString.isEmpty()) {
            new AlertDialog.Builder(MainActivity.context).setMessage("你是不是忘了输入什么?").setNeutralButton("我有问题", (dialogInterface, i) -> {}).show();
        }
        long uid;
        try {
            uid = Long.valueOf(uidString);
        } catch (NumberFormatException ex) {
            new AlertDialog.Builder(MainActivity.context).setMessage("请输入正确的用户UID").setNeutralButton("OK", (dialogInterface, i) -> {}).show();
            return;
        }
        ProgressDialog progress = new ProgressDialog(MainActivity.context);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("正在查询...");
        progress.show();
        btnQuery.setEnabled(false);
        BilibiliAPI.liveMedals(Collections.singletonList(uid), obj -> {
            if (RequestUtils.assertData(progress, btnQuery, obj)) return;
            btnQuery.setEnabled(true);
            progress.dismiss();
            if (obj instanceof MedalEntity) {
                Intent intent = new Intent(MainActivity.context, MedalActivity.class);
                intent.putExtra("UID", uid);
                intent.putExtra("MEDAL_DATA", new ArrayList<>(((MedalEntity) obj).data.users.get(String.valueOf(uid)).medals.values()));
                startActivity(intent);
            }
        });
    }
}