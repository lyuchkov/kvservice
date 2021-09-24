package ru.lyuchkov.kvservice.utils;

import java.util.Date;

public final class TtlUtil {
    public static boolean isTimeLimitOver(Date endDate) {
        return new Date().after(endDate);
    }
}
