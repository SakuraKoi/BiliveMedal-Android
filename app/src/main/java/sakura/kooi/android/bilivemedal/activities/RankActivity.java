package sakura.kooi.android.bilivemedal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;
import sakura.kooi.android.bilivemedal.entity.RoomRankEntity;
import sakura.kooi.android.bilivemedal.ui.RankInfoView;

public class RankActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        LinearLayout container = this.findViewById(R.id.containerRanks);
        long room = getIntent().getLongExtra("ROOM_ID", -1L);
        long selfUid = getIntent().getLongExtra("SELF_UID", -1L);
        String selfName = getIntent().getStringExtra("SELF_NAME");
        ArrayList<RoomRankEntity.DataBean.MedalBean> ranks = (ArrayList<RoomRankEntity.DataBean.MedalBean>) getIntent().getSerializableExtra("ROOM_RANKS");
        HashMap<String, MedalEntity.DataBean.UserBean.MedalBean> medals = (HashMap<String, MedalEntity.DataBean.UserBean.MedalBean>) getIntent().getSerializableExtra("ROOM_MEDALS");
        if (room < 0 || ranks == null || medals == null) {
            throw new IllegalStateException("Illegal data");
        }

        setTitle("直播间 "+room+" 的排行榜");
        MedalEntity.DataBean.UserBean.MedalBean selfBean = selfUid == -1 ? null : medals.get(String.valueOf(selfUid));

        boolean onRank = false;
        for (int i = 0, size = ranks.size(); i < size; i++) {
            RoomRankEntity.DataBean.MedalBean rank = ranks.get(i);
            MedalEntity.DataBean.UserBean.MedalBean medal = medals.get(String.valueOf(rank.uid));
            RankInfoView rankInfoView = new RankInfoView(this);
            rankInfoView.update("#"+rank.rank, rank, medal, selfBean, null);
            container.addView(rankInfoView);
            if (rank.uid == selfUid)
                onRank = true;
        }
        if (!onRank && selfUid != -1) {
            if (selfBean != null) {
                RankInfoView rankInfoView = new RankInfoView(this);
                rankInfoView.update("自己", null, selfBean, null, selfName);
                container.addView(rankInfoView);
            }
        }
    }
}
