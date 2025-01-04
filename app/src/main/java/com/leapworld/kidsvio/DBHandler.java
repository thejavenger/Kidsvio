package com.leapworld.kidsvio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "kidsvio.db";

    private static final String TABLE_SETTINGS = "settings";
    private static final String TABLE_SCORES = "scores";
    private static final String TABLE_STICKERS = "stickers";

    private static final String SETTINGS_ID = "id";
    private static final String SETTINGS_SETTING = "setting";
    private static final String SETTINGS_INTVALUE = "intvalue";
    private static final String SETTINGS_TEXTVALUE = "textvalue";

    private static final String SCORES_ID = "id";
    private static final String SCORES_GAMENAME = "gamename";
    private static final String SCORES_SCORE = "score";
    private static final String SCORES_ALLPOINTS = "allpoints";
    private static final String SCORES_STATUS = "status";

    private static final String STICKERS_ID = "id";
    private static final String STICKERS_STICKER = "sticker";
    private static final String STICKERS_STICKERNAME = "stickername";
    private static final String STICKERS_REWARDID = "rewardid";
    //private static final String STICKERS_STATUS = "status";

    private static final String CREATE_TABLE_SETTINGS = "CREATE TABLE " + TABLE_SETTINGS + " ( " +
            SETTINGS_ID + " INTEGER PRIMARY KEY, " +
            SETTINGS_SETTING + " TEXT, " +
            SETTINGS_INTVALUE + " INTEGER, " +
            SETTINGS_TEXTVALUE + " TEXT " +  " ) ";

    private static final String CREATE_TABLE_SCORES = "CREATE TABLE " + TABLE_SCORES + " ( " +
            SCORES_ID + " INTEGER PRIMARY KEY, " +
            SCORES_GAMENAME + " TEXT, " +
            SCORES_SCORE + " INTEGER, " +
            SCORES_ALLPOINTS + " INTEGER, " +
            SCORES_STATUS + " TEXT " +
            " ) ";


    private static final String CREATE_TABLE_STICKERS = "CREATE TABLE " + TABLE_STICKERS + " ( " +
            STICKERS_ID + " INTEGER PRIMARY KEY, " +
            STICKERS_STICKER + " INTEGER, " +
            STICKERS_STICKERNAME + " TEXT, " +
            STICKERS_REWARDID + " INTEGER" +
            " ) ";


    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SETTINGS);
        db.execSQL(CREATE_TABLE_SCORES);
        db.execSQL(CREATE_TABLE_STICKERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STICKERS);

        onCreate(db);
    }

    public void addScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SCORES_GAMENAME, score.get_gamename());
        values.put(SCORES_SCORE, score.get_score());
        values.put(SCORES_ALLPOINTS, score.get_allpoints());
        values.put(SCORES_STATUS, score.get_status());

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    public Score getGameScore(String game){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE " + SCORES_GAMENAME + " = ?";

        Cursor c = db.rawQuery(query, new String[]{game});

        if (null!=c)
            c.moveToFirst();

        Score score = new Score();
        score.set_id(c.getInt(c.getColumnIndex(SCORES_ID)));
        score.set_gamename(c.getString(c.getColumnIndex(SCORES_GAMENAME)));
        score.set_score(c.getInt(c.getColumnIndex(SCORES_SCORE)));
        score.set_allpoints(c.getInt(c.getColumnIndex(SCORES_ALLPOINTS)));
        score.set_status(c.getString(c.getColumnIndex(SCORES_STATUS)));

        return score;
    }

    public boolean isGameDataEmpty(String game){

        boolean empty;

        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE " + SCORES_GAMENAME + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, new String[]{game});

        empty = true;

        if (c.moveToFirst()){
            empty = false;
        }

        return empty;

    }

    public void initializeGame(String game){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SCORES_GAMENAME, game);
        values.put(SCORES_SCORE, 0);
        values.put(SCORES_ALLPOINTS, 0);
        values.put(SCORES_STATUS, "--");

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }


    public Score getScore(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE " + SCORES_ID + " = " + id;

        Cursor c = db.rawQuery(query, null);

        if (null!=c)
            c.moveToFirst();

        Score score = new Score();
        score.set_id(c.getInt(c.getColumnIndex(SCORES_ID)));
        score.set_gamename(c.getString(c.getColumnIndex(SCORES_GAMENAME)));
        score.set_score(c.getInt(c.getColumnIndex(SCORES_SCORE)));
        score.set_allpoints(c.getInt(c.getColumnIndex(SCORES_ALLPOINTS)));
        score.set_status(c.getString(c.getColumnIndex(SCORES_STATUS)));

        return score;
    }

    public List<Score> getAllScores(){
        List<Score> scores = new ArrayList<Score>();

        String query = "SELECT * FROM " + TABLE_SCORES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do {
                Score score = new Score();

                score.set_id(c.getInt(c.getColumnIndex(SCORES_ID)));
                score.set_gamename(c.getString(c.getColumnIndex(SCORES_GAMENAME)));
                score.set_score(c.getInt(c.getColumnIndex(SCORES_SCORE)));
                score.set_allpoints(c.getInt(c.getColumnIndex(SCORES_ALLPOINTS)));
                score.set_status(c.getString(c.getColumnIndex(SCORES_STATUS)));

                scores.add(score);
            }while (c.moveToNext());
        }

        return scores;
    }

    public int updateScore(Score score){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(SCORES_GAMENAME, score.get_gamename());
        values.put(SCORES_SCORE, score.get_score());
        values.put(SCORES_ALLPOINTS, score.get_allpoints());
        values.put(SCORES_STATUS, score.get_status());

        return db.update(TABLE_SCORES, values, SCORES_ID + " = ?", new String[] {String.valueOf(score.get_id())});
    }

    public void addStickers(Stickers stickers){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STICKERS_STICKER, stickers.get_sticker());
        values.put(STICKERS_STICKERNAME, stickers.get_stickername());
        values.put(STICKERS_REWARDID, stickers.get_rewardid());

        db.insert(TABLE_STICKERS, null, values);
        db.close();
    }

    public boolean stickerExist(int sticker){

        boolean exists;


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_STICKERS + " WHERE " + STICKERS_STICKER + " = " + sticker;
        Cursor c = db.rawQuery(query, null);

        exists = false;

        if (c.moveToFirst()){
            exists = true;
        }

        return exists;

    }

    public Stickers getStickers(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_STICKERS + " WHERE " + STICKERS_ID + " = " + id;

        Cursor c = db.rawQuery(query, null);

        if (null!=c)
            c.moveToFirst();

        Stickers stickers = new Stickers();
        stickers.set_id(c.getInt(c.getColumnIndex(STICKERS_ID)));
        stickers.set_sticker(c.getInt(c.getColumnIndex(STICKERS_STICKER)));
        stickers.set_stickername(c.getString(c.getColumnIndex(STICKERS_STICKERNAME)));
        stickers.set_rewardid(c.getInt(c.getColumnIndex(STICKERS_REWARDID)));

        return stickers;
    }

    public List<Stickers> getAllStickers(){
        List<Stickers> allstickers = new ArrayList<Stickers>();

        String query = "SELECT * FROM " + TABLE_STICKERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do {
                Stickers stickers = new Stickers();
                stickers.set_id(c.getInt(c.getColumnIndex(STICKERS_ID)));
                stickers.set_sticker(c.getInt(c.getColumnIndex(STICKERS_STICKER)));
                stickers.set_stickername(c.getString(c.getColumnIndex(STICKERS_STICKERNAME)));
                stickers.set_rewardid(c.getInt(c.getColumnIndex(STICKERS_REWARDID)));

                allstickers.add(stickers);
            }while (c.moveToNext());
        }

        return allstickers;
    }

    public int updateStickers(Stickers stickers){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(STICKERS_STICKER, stickers.get_sticker());
        values.put(STICKERS_STICKERNAME, stickers.get_stickername());
        values.put(STICKERS_REWARDID, stickers.get_rewardid());

        return db.update(TABLE_STICKERS, values, STICKERS_ID + " = ?", new String[] {String.valueOf(stickers.get_id())});
    }

    public void addSetting(Setting setting){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SETTINGS_SETTING, setting.get_setting());
        values.put(SETTINGS_INTVALUE, setting.get_intvalue());
        values.put(SETTINGS_TEXTVALUE, setting.get_textvalue());

        db.insert(TABLE_SETTINGS, null, values);
        db.close();
    }

    public Setting getSetting(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + SETTINGS_ID + " = " + id;

        Cursor c = db.rawQuery(query, null);

        if (null!=c)
            c.moveToFirst();

        Setting setting = new Setting();
        setting.set_id(c.getInt(c.getColumnIndex(SETTINGS_ID)));
        setting.set_setting(c.getString(c.getColumnIndex(SETTINGS_SETTING)));
        setting.set_intvalue(c.getInt(c.getColumnIndex(SETTINGS_INTVALUE)));
        setting.set_textvalue(c.getString(c.getColumnIndex(SETTINGS_TEXTVALUE)));

        return setting;
    }

    public List<Setting> getAllSettings(){
        List<Setting> settings = new ArrayList<Setting>();

        String query = "SELECT * FROM " + TABLE_SETTINGS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do {
                Setting setting = new Setting();

                setting.set_id(c.getInt(c.getColumnIndex(SETTINGS_ID)));
                setting.set_setting(c.getString(c.getColumnIndex(SETTINGS_SETTING)));
                setting.set_intvalue(c.getInt(c.getColumnIndex(SETTINGS_INTVALUE)));
                setting.set_textvalue(c.getString(c.getColumnIndex(SETTINGS_TEXTVALUE)));

                settings.add(setting);
            }while (c.moveToNext());
        }

        return settings;
    }

    public int updateSetting(Setting setting){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(SETTINGS_SETTING, setting.get_setting());
        values.put(SETTINGS_INTVALUE, setting.get_intvalue());
        values.put(SETTINGS_TEXTVALUE, setting.get_textvalue());

        return db.update(TABLE_SETTINGS, values, SETTINGS_ID + " = ?", new String[] {String.valueOf(setting.get_id())});
    }

    public boolean isSettingsEmpty(){
        boolean empty;

        String query = "SELECT * FROM " + TABLE_SETTINGS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        empty = true;

        if (c.moveToFirst()){
            empty = false;
        }

        return empty;
    }

    public void initializeSettings(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SETTINGS_SETTING, "MUSIC");
        values.put(SETTINGS_INTVALUE, 1);
        values.put(SETTINGS_TEXTVALUE, "--");

        db.insert(TABLE_SETTINGS, null, values);

        values.put(SETTINGS_SETTING, "SOUND");
        values.put(SETTINGS_INTVALUE, 1);
        values.put(SETTINGS_TEXTVALUE, "--");

        db.insert(TABLE_SETTINGS, null, values);
        db.close();
    }

    public Setting getMusicSetting(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + SETTINGS_SETTING + " = 'MUSIC'";

        Cursor c = db.rawQuery(query, null);

        if (null!=c)
            c.moveToFirst();

        Setting setting = new Setting();
        setting.set_id(c.getInt(c.getColumnIndex(SETTINGS_ID)));
        setting.set_setting(c.getString(c.getColumnIndex(SETTINGS_SETTING)));
        setting.set_intvalue(c.getInt(c.getColumnIndex(SETTINGS_INTVALUE)));
        setting.set_textvalue(c.getString(c.getColumnIndex(SETTINGS_TEXTVALUE)));

        return setting;
    }

    public Setting getSoundSetting(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_SETTINGS + " WHERE " + SETTINGS_SETTING + " = 'SOUND'";

        Cursor c = db.rawQuery(query, null);

        if (null!=c)
            c.moveToFirst();

        Setting setting = new Setting();
        setting.set_id(c.getInt(c.getColumnIndex(SETTINGS_ID)));
        setting.set_setting(c.getString(c.getColumnIndex(SETTINGS_SETTING)));
        setting.set_intvalue(c.getInt(c.getColumnIndex(SETTINGS_INTVALUE)));
        setting.set_textvalue(c.getString(c.getColumnIndex(SETTINGS_TEXTVALUE)));

        return setting;
    }

    public int updateSettingByType(Setting setting){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(SETTINGS_ID, setting.get_id());
        values.put(SETTINGS_INTVALUE, setting.get_intvalue());
        values.put(SETTINGS_TEXTVALUE, setting.get_textvalue());

        return db.update(TABLE_SETTINGS, values, SETTINGS_SETTING + " = ?", new String[] {String.valueOf(setting.get_setting())});
    }

}
