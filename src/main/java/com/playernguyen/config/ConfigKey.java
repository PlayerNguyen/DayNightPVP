package com.playernguyen.config;

import java.util.Arrays;
import java.util.Collections;

public enum ConfigKey {

    WORLDS       ("worlds", Collections.singletonList("world")),
    REGIONS       ("regions", Arrays.asList("khuvuc0", "khuvuc1", "khuvuc2")),

    DAY_PVP       ("day.pvp", true),
    DAY_MESSAGE       ("day.message", "&aHiện tại đã có thể choảng nhau!"),
    DAY_TIME_FROM       ("day.time.from", 0L),
    DAY_TIME_TO       ("day.time.to", 11999L),

    NIGHT_PVP       ("night.pvp", true),
    NIGHT_MESSAGE       ("night.message", "&cHiện tại đã không còn có thể choảng nhau"),
    NIGHT_TIME_FROM       ("night.time.from", 12000L),
    NIGHT_TIME_TO       ("night.time.to", 23999L);

    private String path;
    private Object df;
    ConfigKey(String path, Object df) {
        this.path = path;
        this.df = df;
    }

    public String getPath() {
        return path;
    }

    public Object getDefaultSetting() {
        return df;
    }
}
