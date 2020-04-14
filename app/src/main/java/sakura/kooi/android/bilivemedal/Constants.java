package sakura.kooi.android.bilivemedal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sakura.kooi.android.bilivemedal.adapters.*;
import sakura.kooi.android.bilivemedal.entity.*;

class Constants {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(MedalEntity.DataBean.class, new MedalDataBeanAdapter())
            .registerTypeAdapter(MedalEntity.DataBean.UserBean.class, new MedalUserBeanAdapter())
            .create();
}
