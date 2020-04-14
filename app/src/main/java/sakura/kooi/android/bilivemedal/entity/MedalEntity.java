package sakura.kooi.android.bilivemedal.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MedalEntity {
    /**
     * code : 0
     * msg : success
     * message : success
     * data : {}
     */

    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public DataBean data;

    public static class DataBean {
        @Expose(deserialize = false)
        public Map<String, UserBean> users = new HashMap<>();

        public static class UserBean {
            /**
             * medal : {}
             */
            @Expose(deserialize = false)
            public Map<String, MedalBean> medals = new HashMap<>();

            public static class MedalBean implements Serializable {
                /**
                 * uid : 3874987
                 * target_id : 11073
                 * medal_id : 1865
                 * score : 30
                 * level : 1
                 * intimacy : 30
                 * status : 0
                 * source : 1
                 * receive_channel : 1
                 * is_receive : 1
                 * master_status : 0
                 * receive_time : 2018-01-28 14:02:02
                 * today_intimacy : 0
                 * last_wear_time : 0
                 * medal_name : 憨色
                 * master_available : 1
                 * guard_type : 0
                 * lpl_status : 0
                 * target_name : hanser
                 * target_face : https://i1.hdslb.com/bfs/face/0eea1850f6e08c29af7b61dbfe5ec555babe7741.jpg
                 * live_stream_status : 0
                 * icon_code : 0
                 * icon_text :
                 * rank : -
                 * medal_color : 9953448
                 * today_feed : 0
                 * day_limit : 500
                 * next_intimacy : 201
                 * master_id : 11073
                 * roomid : 0
                 */

                @SerializedName("uid")
                public int uid;
                @SerializedName("target_id")
                public int targetId;
                @SerializedName("medal_id")
                public int medalId;
                @SerializedName("score")
                public int score;
                @SerializedName("level")
                public int level; // 等级
                @SerializedName("intimacy")
                public int intimacy; // 当前亲密
                @SerializedName("status")
                public int status;
                @SerializedName("source")
                public int source;
                @SerializedName("receive_channel")
                public int receiveChannel;
                @SerializedName("is_receive")
                public int isReceive;
                @SerializedName("master_status")
                public int masterStatus;
                @SerializedName("receive_time")
                public String receiveTime;
                @SerializedName("today_intimacy")
                public int todayIntimacy; // 今日亲密
                @SerializedName("last_wear_time")
                public int lastWearTime;
                @SerializedName("medal_name")
                public String medalName; // 牌子
                @SerializedName("master_available")
                public int masterAvailable;
                @SerializedName("guard_type")
                public int guardType;
                @SerializedName("lpl_status")
                public int lplStatus;
                @SerializedName("target_name")
                public String targetName; // 主播
                @SerializedName("target_face")
                public String targetFace;
                @SerializedName("live_stream_status")
                public int liveStreamStatus;
                @SerializedName("icon_code")
                public int iconCode;
                @SerializedName("icon_text")
                public String iconText;
                @SerializedName("rank")
                public String rank;
                @SerializedName("medal_color")
                public int medalColor; // 牌子颜色
                @SerializedName("today_feed")
                public int todayFeed;
                @SerializedName("day_limit")
                public int dayLimit; // 亲密上限
                @SerializedName("next_intimacy")
                public int nextIntimacy; // 升级所需亲密
                @SerializedName("master_id")
                public int masterId; // 主播UID (不是房间号)
                @SerializedName("roomid")
                public int roomid; // 永远是0
            }
        }
    }
}
