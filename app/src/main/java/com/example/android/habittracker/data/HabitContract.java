package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {
    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_ENJOYMENT = "enjoyment";

        public static final int ENJOYMENT_ONE = 1;
        public static final int ENJOYMENT_TWO = 2;
        public static final int ENJOYMENT_THREE = 3;
        public static final int ENJOYMENT_FOUR = 4;
        public static final int ENJOYMENT_FIVE = 5;

        public static final String SUNDAY = "Sunday";
        public static final String MONDAY = "Monday";
        public static final String TUESDAY = "Tuesday";
        public static final String WEDNESDAY = "Wednesday";
        public static final String THURSDAY = "Thursday";
        public static final String FRIDAY = "Friday";
        public static final String SATURDAY = "Saturday";
    }
}