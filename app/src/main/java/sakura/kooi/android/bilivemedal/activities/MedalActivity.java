package sakura.kooi.android.bilivemedal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.List;

import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;
import sakura.kooi.android.bilivemedal.ui.MedalInfoView;

public class MedalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medal);
        LinearLayout container = this.findViewById(R.id.containerMedals);
        List<MedalEntity.DataBean.UserBean.MedalBean> medals = (List<MedalEntity.DataBean.UserBean.MedalBean>) getIntent().getSerializableExtra("MEDAL_DATA");
        long uid = getIntent().getLongExtra("UID", -1L);
        if (medals == null || uid < 0) {
            throw new IllegalStateException("Illegal data");
        }
        setTitle("用户 "+uid+" 的粉丝勋章");
        for (MedalEntity.DataBean.UserBean.MedalBean medal : medals) {
            MedalInfoView infoView = new MedalInfoView(this);
            infoView.update(medal);
            container.addView(infoView);
        }
    }
}
