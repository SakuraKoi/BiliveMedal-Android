package sakura.kooi.android.bilivemedal.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomInfoEntity {

    /**
     * code : 0
     * msg : ok
     * message : ok
     * data : {"uid":2191383,"room_id":7811723,"short_id":0,"attention":47741,"online":9571,"is_portrait":false,"description":"<p>小狐神转播~<\/p>","live_status":0,"area_id":192,"parent_area_id":5,"parent_area_name":"电台","old_area_id":6,"background":"https://static.hdslb.com/live-static/images/bg/2.jpg","title":"【Soy】萌新狐狐早安闲聊台","user_cover":"https://i0.hdslb.com/bfs/live/new_room_cover/c99bfd08270ef4a8f9259a114da59cb3353edc78.jpg","keyframe":"https://i0.hdslb.com/bfs/live/keyframe03250820000007811723g4otgn.jpg","is_strict_room":false,"live_time":"0000-00-00 00:00:00","tags":"","is_anchor":0,"room_silent_type":"","room_silent_level":0,"room_silent_second":0,"area_name":"聊天电台","pendants":"","area_pendants":"","hot_words":["2333333","喂，妖妖零吗","红红火火恍恍惚惚","FFFFFFFFFF","Yooooooo","啪啪啪啪啪","666666666","老司机带带我","你为什么这么熟练啊","gg","prprpr","向大佬低头","请大家注意弹幕礼仪哦！","还有这种操作！","囍","打call","你气不气？","队友呢？"],"hot_words_status":0,"verify":"","new_pendants":{"frame":{"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false},"badge":null,"mobile_frame":{"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false},"mobile_badge":null},"up_session":"","pk_status":0,"pk_id":0,"battle_id":0,"allow_change_area_time":0,"allow_upload_cover_time":0,"studio_info":{"status":0,"master_list":[]}}
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
        /**
         * uid : 2191383
         * room_id : 7811723
         * short_id : 0
         * attention : 47741
         * online : 9571
         * is_portrait : false
         * description : <p>小狐神转播~</p>
         * live_status : 0
         * area_id : 192
         * parent_area_id : 5
         * parent_area_name : 电台
         * old_area_id : 6
         * background : https://static.hdslb.com/live-static/images/bg/2.jpg
         * title : 【Soy】萌新狐狐早安闲聊台
         * user_cover : https://i0.hdslb.com/bfs/live/new_room_cover/c99bfd08270ef4a8f9259a114da59cb3353edc78.jpg
         * keyframe : https://i0.hdslb.com/bfs/live/keyframe03250820000007811723g4otgn.jpg
         * is_strict_room : false
         * live_time : 0000-00-00 00:00:00
         * tags :
         * is_anchor : 0
         * room_silent_type :
         * room_silent_level : 0
         * room_silent_second : 0
         * area_name : 聊天电台
         * pendants :
         * area_pendants :
         * hot_words : ["2333333","喂，妖妖零吗","红红火火恍恍惚惚","FFFFFFFFFF","Yooooooo","啪啪啪啪啪","666666666","老司机带带我","你为什么这么熟练啊","gg","prprpr","向大佬低头","请大家注意弹幕礼仪哦！","还有这种操作！","囍","打call","你气不气？","队友呢？"]
         * hot_words_status : 0
         * verify :
         * new_pendants : {"frame":{"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false},"badge":null,"mobile_frame":{"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false},"mobile_badge":null}
         * up_session :
         * pk_status : 0
         * pk_id : 0
         * battle_id : 0
         * allow_change_area_time : 0
         * allow_upload_cover_time : 0
         * studio_info : {"status":0,"master_list":[]}
         */

        @SerializedName("uid")
        public int uid;
        @SerializedName("room_id")
        public int roomId;
        @SerializedName("short_id")
        public int shortId;
        @SerializedName("attention")
        public int attention;
        @SerializedName("online")
        public int online;
        @SerializedName("is_portrait")
        public boolean isPortrait;
        @SerializedName("description")
        public String description;
        @SerializedName("live_status")
        public int liveStatus;
        @SerializedName("area_id")
        public int areaId;
        @SerializedName("parent_area_id")
        public int parentAreaId;
        @SerializedName("parent_area_name")
        public String parentAreaName;
        @SerializedName("old_area_id")
        public int oldAreaId;
        @SerializedName("background")
        public String background;
        @SerializedName("title")
        public String title;
        @SerializedName("user_cover")
        public String userCover;
        @SerializedName("keyframe")
        public String keyframe;
        @SerializedName("is_strict_room")
        public boolean isStrictRoom;
        @SerializedName("live_time")
        public String liveTime;
        @SerializedName("tags")
        public String tags;
        @SerializedName("is_anchor")
        public int isAnchor;
        @SerializedName("room_silent_type")
        public String roomSilentType;
        @SerializedName("room_silent_level")
        public int roomSilentLevel;
        @SerializedName("room_silent_second")
        public int roomSilentSecond;
        @SerializedName("area_name")
        public String areaName;
        @SerializedName("pendants")
        public String pendants;
        @SerializedName("area_pendants")
        public String areaPendants;
        @SerializedName("hot_words_status")
        public int hotWordsStatus;
        @SerializedName("verify")
        public String verify;
        @SerializedName("new_pendants")
        public NewPendantsBean newPendants;
        @SerializedName("up_session")
        public String upSession;
        @SerializedName("pk_status")
        public int pkStatus;
        @SerializedName("pk_id")
        public int pkId;
        @SerializedName("battle_id")
        public int battleId;
        @SerializedName("allow_change_area_time")
        public int allowChangeAreaTime;
        @SerializedName("allow_upload_cover_time")
        public int allowUploadCoverTime;
        @SerializedName("studio_info")
        public StudioInfoBean studioInfo;
        @SerializedName("hot_words")
        public List<String> hotWords;

        public static class NewPendantsBean {
            /**
             * frame : {"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false}
             * badge : null
             * mobile_frame : {"name":"","value":"","position":0,"desc":"","area":0,"area_old":0,"bg_color":"","bg_pic":"","use_old_area":false}
             * mobile_badge : null
             */

            @SerializedName("frame")
            public FrameBean frame;
            @SerializedName("badge")
            public Object badge;
            @SerializedName("mobile_frame")
            public MobileFrameBean mobileFrame;
            @SerializedName("mobile_badge")
            public Object mobileBadge;

            public static class FrameBean {
                /**
                 * name :
                 * value :
                 * position : 0
                 * desc :
                 * area : 0
                 * area_old : 0
                 * bg_color :
                 * bg_pic :
                 * use_old_area : false
                 */

                @SerializedName("name")
                public String name;
                @SerializedName("value")
                public String value;
                @SerializedName("position")
                public int position;
                @SerializedName("desc")
                public String desc;
                @SerializedName("area")
                public int area;
                @SerializedName("area_old")
                public int areaOld;
                @SerializedName("bg_color")
                public String bgColor;
                @SerializedName("bg_pic")
                public String bgPic;
                @SerializedName("use_old_area")
                public boolean useOldArea;
            }

            public static class MobileFrameBean {
                /**
                 * name :
                 * value :
                 * position : 0
                 * desc :
                 * area : 0
                 * area_old : 0
                 * bg_color :
                 * bg_pic :
                 * use_old_area : false
                 */

                @SerializedName("name")
                public String name;
                @SerializedName("value")
                public String value;
                @SerializedName("position")
                public int position;
                @SerializedName("desc")
                public String desc;
                @SerializedName("area")
                public int area;
                @SerializedName("area_old")
                public int areaOld;
                @SerializedName("bg_color")
                public String bgColor;
                @SerializedName("bg_pic")
                public String bgPic;
                @SerializedName("use_old_area")
                public boolean useOldArea;
            }
        }

        public static class StudioInfoBean {
            /**
             * status : 0
             * master_list : []
             */

            @SerializedName("status")
            public int status;
            @SerializedName("master_list")
            public List<?> masterList;
        }
    }
}
