package sakura.kooi.android.bilivemedal.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;

import sakura.kooi.android.bilivemedal.entity.MedalEntity;

public class MedalDataBeanAdapter implements JsonDeserializer<MedalEntity.DataBean> {
    @Override
    public MedalEntity.DataBean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        MedalEntity.DataBean dataBean = new MedalEntity.DataBean();
        if (jsonElement.isJsonObject()) {
            JsonObject object = jsonElement.getAsJsonObject();
            object.entrySet().forEach(entry -> dataBean.users.put(entry.getKey(), context.deserialize(entry.getValue(), MedalEntity.DataBean.UserBean.class)));
        }
        return dataBean;
    }
}
