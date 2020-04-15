package sakura.kooi.android.bilivemedal.fragments.rank;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import sakura.kooi.android.bilivemedal.BilibiliAPI;
import sakura.kooi.android.bilivemedal.MainActivity;
import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.activities.RankActivity;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;
import sakura.kooi.android.bilivemedal.entity.RoomInfoEntity;
import sakura.kooi.android.bilivemedal.entity.RoomRankEntity;
import sakura.kooi.android.bilivemedal.entity.UserInfoEntity;
import sakura.kooi.android.bilivemedal.utils.RequestUtils;

public class RankFragment extends Fragment {
    private RankViewModel rankViewModel;

    private EditText editRoom;
    private EditText editUid;
    private CheckBox chkUseApp;
    private Button btnQuery;
    private ProgressDialog progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rankViewModel =
                ViewModelProviders.of(this).get(RankViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rank, container, false);
        editRoom = root.findViewById(R.id.editRoom);
        editUid = root.findViewById(R.id.editUid);
        chkUseApp = root.findViewById(R.id.checkUseApp);
        btnQuery = root.findViewById(R.id.btnQuery);
        btnQuery.setOnClickListener(this::execute);
        return root;
    }

    private void execute(View view) {
        String roomString = editRoom.getText().toString().trim();
        if (roomString.isEmpty()) {
            new AlertDialog.Builder(MainActivity.context).setMessage("你是不是忘了输入什么?").setNeutralButton("我有问题", (dialogInterface, i) -> {}).show();
            return;
        }
        final long room;
        try {
            room = Long.valueOf(roomString);
        } catch (NumberFormatException ex) {
            new AlertDialog.Builder(MainActivity.context).setMessage("请输入正确的房间号").setNeutralButton("OK", (dialogInterface, i) -> {}).show();
            return;
        }
        final long uid;
        String uidString = editUid.getText().toString().trim();
        if (!uidString.isEmpty()) {
            try {
                uid = Long.valueOf(uidString);
            } catch (NumberFormatException ex) {
                new AlertDialog.Builder(MainActivity.context).setMessage("请输入正确的用户UID").setNeutralButton("OK", (dialogInterface, i) -> {
                }).show();
                return;
            }
        } else {
            uid = -1L;
        }
        boolean useAppInterface = chkUseApp.isChecked();
        progress = new ProgressDialog(MainActivity.context);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("正在查询房间信息...");
        progress.show();
        btnQuery.setEnabled(false);
        collectRoomInfo(room, uid, useAppInterface);
    }

    private void collectRoomInfo(long room, long uid, boolean useAppInterface) {
        BilibiliAPI.roomInfo(room, obj -> {
            if (RequestUtils.assertData(progress, btnQuery, obj)) return;
            if (obj instanceof RoomInfoEntity) {
                long streamerUid = ((RoomInfoEntity) obj).data.uid;
                if (uid != -1L) {
                    collectSelfName(room, uid, useAppInterface, streamerUid);
                } else {
                    collectRank(room, uid, "", useAppInterface, streamerUid);
                }
            }
        });
    }

    private void collectSelfName(long room, long uid, boolean useAppInterface, long streamerUid) {
        progress.setMessage("正在查询用户信息...");
        BilibiliAPI.userInfo(uid, obj -> {
            if (RequestUtils.assertData(progress, btnQuery, obj)) return;
            if (obj instanceof UserInfoEntity) {
                String selfName = ((UserInfoEntity) obj).data.name;
                collectRank(room, uid, selfName, useAppInterface, streamerUid);
            }
        });
    }

    private void collectRank(long room, long uid, String selfName, boolean useAppInterface, long streamerUid) {
        final ArrayList<RoomRankEntity.DataBean.MedalBean> ranks = new ArrayList<>();
        if (useAppInterface) {
            progress.setMessage("正在查询排行榜... [1/2]");
            BilibiliAPI.roomRankMobile(room, streamerUid, 1, obj -> {
                if (RequestUtils.assertData(progress, btnQuery, obj)) return;
                if (obj instanceof RoomRankEntity) {
                    ranks.addAll(((RoomRankEntity) obj).data.list);
                    collectRankAppAgain(room, uid, selfName, streamerUid, ranks);
                }
            });
        } else {
            progress.setMessage("正在查询排行榜...");
            BilibiliAPI.roomRankWeb(room, streamerUid, obj -> {
                if (RequestUtils.assertData(progress, btnQuery, obj)) return;
                if (obj instanceof RoomRankEntity) {
                    ranks.addAll(((RoomRankEntity) obj).data.list);
                    collectMedals(room, uid, selfName, streamerUid, ranks);
                }
            });
        }
    }

    private void collectRankAppAgain(long room, long uid, String selfName, long streamerUid, ArrayList<RoomRankEntity.DataBean.MedalBean> ranks) {
        progress.setMessage("正在查询排行榜... [2/2]");
        BilibiliAPI.roomRankMobile(room, streamerUid, 2, obj -> {
            if (RequestUtils.assertData(progress, btnQuery, obj)) return;
            if (obj instanceof RoomRankEntity) {
                ranks.addAll(((RoomRankEntity) obj).data.list);
                collectMedals(room, uid, selfName, streamerUid, ranks);
            }
        });
    }

    private void collectMedals(long room, long uid, String selfName, long streamerUid, ArrayList<RoomRankEntity.DataBean.MedalBean> ranks) {
        if (ranks.isEmpty()) {
            new AlertDialog.Builder(MainActivity.context)
                    .setMessage("所查询的直播间没有任何人拥有粉丝勋章")
                    .setNeutralButton("OK", (dialogInterface, i) -> {}).show();
        }
        HashMap<String, MedalEntity.DataBean.UserBean.MedalBean> medals = new HashMap<>();
        HashSet<Long> uidList = new HashSet<>();
        if (uid != -1) uidList.add(uid);
        LinkedList<RoomRankEntity.DataBean.MedalBean> clone = new LinkedList<>(ranks);
        collectMedalsStep(room, uid, selfName, streamerUid, ranks, medals, uidList, clone, 0, clone.size());
    }

    private void collectMedalsStep(long room, long uid, String selfName, long streamerUid, ArrayList<RoomRankEntity.DataBean.MedalBean> ranks, HashMap<String, MedalEntity.DataBean.UserBean.MedalBean> medals, HashSet<Long> uidList, LinkedList<RoomRankEntity.DataBean.MedalBean> clone, int current, int size) {
        if (clone.isEmpty()) {
            progress.dismiss();
            btnQuery.setEnabled(true);
            Intent intent = new Intent(MainActivity.context, RankActivity.class);
            intent.putExtra("ROOM_ID", room);
            intent.putExtra("SELF_UID", uid);
            intent.putExtra("SELF_NAME", selfName);
            intent.putExtra("ROOM_RANKS", ranks);
            intent.putExtra("ROOM_MEDALS", medals);
            startActivity(intent);
        } else {
            progress.setMessage("正在查询勋章信息... ["+current+"/"+size+"]");
            while (uidList.size() < 10) {
                if (clone.isEmpty()) break;
                uidList.add((long) clone.pop().uid);
                current++;
            }
            int last = current;
            BilibiliAPI.liveMedals(new ArrayList<>(uidList), obj -> {
                if (RequestUtils.assertData(progress, btnQuery, obj)) return;
                if (obj instanceof MedalEntity) {
                    ((MedalEntity) obj).data.users.entrySet().stream().forEach(entry -> medals.put(entry.getKey(), entry.getValue().medals.get(String.valueOf(streamerUid))));
                    uidList.clear();
                    collectMedalsStep(room, uid, selfName, streamerUid, ranks, medals, uidList, clone, last, size);
                }
            });
        }
    }

}