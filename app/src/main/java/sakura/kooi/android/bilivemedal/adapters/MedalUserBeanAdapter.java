package sakura.kooi.android.bilivemedal.adapters;

import com.google.gson.*;
import sakura.kooi.android.bilivemedal.entity.MedalEntity;

import java.lang.reflect.Type;

public class MedalUserBeanAdapter implements JsonDeserializer<MedalEntity.DataBean.UserBean> {
    @Override
    public MedalEntity.DataBean.UserBean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        MedalEntity.DataBean.UserBean dataBean = new MedalEntity.DataBean.UserBean();
        if (jsonElement.isJsonObject()) {
            JsonElement object = jsonElement.getAsJsonObject().get("medal");
            if (object.isJsonObject()) {
                object.getAsJsonObject().entrySet().forEach(entry -> dataBean.medals.put(entry.getKey(), context.deserialize(entry.getValue(), MedalEntity.DataBean.UserBean.MedalBean.class)));
            }
        }
        return dataBean;
    }
}
