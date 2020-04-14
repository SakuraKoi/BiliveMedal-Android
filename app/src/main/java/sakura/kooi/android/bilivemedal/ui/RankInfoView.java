package sakura.kooi.android.bilivemedal.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;
import sakura.kooi.android.bilivemedal.entity.RoomRankEntity;

public class RankInfoView extends FrameLayout {
    private TextView lblSort;
    private MedalLogoView medalLogo;
    private TextView lblGuard;
    private TextView lblName;
    private TextView lblPoint;
    private TextView lblLeft;
    private TextView lblDiff;

    public RankInfoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public RankInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RankInfoView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_rank_info, this);
        lblSort = findViewById(R.id.textSort);
        medalLogo = findViewById(R.id.medalLogo);
        lblGuard = findViewById(R.id.textGuard);
        lblName = findViewById(R.id.textName);
        lblPoint = findViewById(R.id.textPoint);
        lblLeft = findViewById(R.id.textLeft);
        lblDiff = findViewById(R.id.textDiff);
    }

    public void update(String rankPosition, @Nullable RoomRankEntity.DataBean.MedalBean rankBean, @NonNull MedalEntity.DataBean.UserBean.MedalBean medalBean,
                       @Nullable MedalEntity.DataBean.UserBean.MedalBean selfBean, String selfName) {
        lblSort.setText(rankPosition);
        lblPoint.setText(String.valueOf(medalBean.intimacy));
        lblGuard.setText(toGuardLevelString(medalBean.guardType));
        medalLogo.update(medalBean.medalName, medalBean.medalColor, medalBean.level);
        if (medalBean.todayIntimacy == medalBean.dayLimit) {
            lblLeft.setText("今日已满");
        } else {
            lblLeft.setText("辣条 +"+(medalBean.dayLimit - medalBean.todayIntimacy));
        }
        lblName.setText(rankBean == null ? selfName : rankBean.uname);
        if (selfBean != null) {
            lblDiff.setText(compareWithSelf(medalBean, selfBean));
            lblDiff.setTextColor(compareColor(medalBean, selfBean));
        } else {
            lblDiff.setText("");
        }
    }

    private int compareColor(MedalEntity.DataBean.UserBean.MedalBean medalBean, MedalEntity.DataBean.UserBean.MedalBean selfBean) {
        if (selfBean.uid == medalBean.uid) return 0xFF1976d2;
        if (medalBean.level > selfBean.level) return 0xFFff9100;
        if (medalBean.level < selfBean.level) return 0xFF00796b;
        if (medalBean.level == selfBean.level) {
            if (medalBean.intimacy > selfBean.intimacy) {
                if (medalBean.intimacy - selfBean.intimacy > 2000)
                    return 0xFFff5722;
                return 0xFF651fff;
            }
            if (medalBean.intimacy < selfBean.intimacy) {
                if (selfBean.intimacy - medalBean.intimacy > 3000)
                    return 0xFF2e7d32;
                return 0xFFb71c1c;
            }
        }
        return 0xFF1976d2;
    }

    private String compareWithSelf(MedalEntity.DataBean.UserBean.MedalBean medalBean, MedalEntity.DataBean.UserBean.MedalBean selfBean) {
        if (selfBean == null) return "";
        if (selfBean.uid == medalBean.uid) return "我自己";
        if (selfBean.level != medalBean.level) {
            boolean oneLevel = Math.abs(selfBean.level - medalBean.level) == 1;
            if (selfBean.level > medalBean.level) {
                return getCrossLevelDiff(medalBean, selfBean, oneLevel);
            } else {
                return getCrossLevelDiff(selfBean, medalBean, oneLevel);
            }
        } else return "还差 " + Math.abs(medalBean.intimacy - selfBean.intimacy) + " 亲密度";
    }
    
    private String getCrossLevelDiff(MedalEntity.DataBean.UserBean.MedalBean lower, MedalEntity.DataBean.UserBean.MedalBean greater, boolean oneLevel) {
        int nextLevelRequire = lower.nextIntimacy - lower.intimacy;
        if (oneLevel) {
            return "还差 1 级 (" + (nextLevelRequire + greater.intimacy) + " 亲密度)";
        } else return "还差 " + (greater.level - lower.level - 1) + " 级 " + (nextLevelRequire + greater.intimacy) + " 亲密度";
    }

    private String toGuardLevelString(int guardLevel) {
        if (guardLevel == 0) return "";
        if (guardLevel == 1) return "总督";
        if (guardLevel == 2) return "提督";
        if (guardLevel == 3) return "舰长";
        return "未知";
    }
}
