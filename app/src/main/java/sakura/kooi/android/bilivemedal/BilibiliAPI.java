package sakura.kooi.android.bilivemedal;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import sakura.kooi.android.bilivemedal.entity.*;
import sakura.kooi.android.bilivemedal.utils.UTF8StringRequest;

public class BilibiliAPI {
    public static void roomInfo(long room, Consumer<Object> handler) {
        String url = "https://api.live.bilibili.com/room/v1/Room/get_info?id=" + room;
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    RoomInfoEntity roomInfoEntity = Constants.GSON.fromJson(object, RoomInfoEntity.class);
                    handler.accept(roomInfoEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }

    public static void userInfo(long uid, Consumer<Object> handler) {
        String url = "https://api.bilibili.com/x/space/acc/info?mid=" + uid + "&jsonp=jsonp";
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    UserInfoEntity userInfoEntity = Constants.GSON.fromJson(object, UserInfoEntity.class);
                    handler.accept(userInfoEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }

    public static void roomRankWeb(long room, long streamerUid, Consumer<Object> handler) {
        String url = "https://api.live.bilibili.com/rankdb/v1/RoomRank/webMedalRank?roomid=" + room + "&ruid=" + streamerUid;
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    RoomRankEntity roomRankEntity = Constants.GSON.fromJson(object, RoomRankEntity.class);
                    handler.accept(roomRankEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }

    public static void roomRankMobile(long room, long streamerUid, int page, Consumer<Object> handler) {
        String url = "https://api.live.bilibili.com/rankdb/v2/RoomRank/mobileMedalRank?roomid=" + room + "&ruid=" + streamerUid + "&page=" + page;
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    RoomRankEntity roomRankEntity = Constants.GSON.fromJson(object, RoomRankEntity.class);
                    handler.accept(roomRankEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }

    public static void liveMedals(List<Long> uidList, Consumer<Object> handler) {
        String url = "https://api.live.bilibili.com/user/v2/User/getMultiple?" + joinUidList(uidList) + "attributes[]=medal";
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    MedalEntity medalEntity = Constants.GSON.fromJson(object, MedalEntity.class);
                    if (medalEntity.code == 500003) {
                        handler.accept(new BiliException("所查询的用户没有任何粉丝勋章"));
                        return;
                    }
                    handler.accept(medalEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }

    private static String joinUidList(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < list.size(); i++) {
            sb.append("uids[").append(i).append("]=").append(list.get(i)).append('&');
        }
        return sb.toString();
    }

    public static void getFollowing(long uid, int page, Consumer<Object> handler) {
        String url = "https://api.bilibili.com/x/relation/followings?vmid=" + uid + "&pn=" + page + "&ps=50&order=desc&jsonp=jsonp";
        UTF8StringRequest req = new UTF8StringRequest(Request.Method.GET, url,
                data -> {
                    JsonObject object = JsonParser.parseString(data).getAsJsonObject();
                    if (object.get("code").getAsInt() != 0) {
                        handler.accept(new BiliException(object.get("code").getAsInt() + " " + object.get("message").getAsString()));
                        return;
                    }
                    UserFollowEntity followEntity = Constants.GSON.fromJson(object, UserFollowEntity.class);
                    handler.accept(followEntity);
                },
                error -> handler.accept(error)
        );
        MainActivity.queue.add(req);
    }
}
