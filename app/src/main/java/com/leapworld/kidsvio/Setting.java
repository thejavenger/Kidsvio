package com.leapworld.kidsvio;

public class Setting {

    private int _id;
    private String _setting;
    private int _intvalue;
    private String _textvalue;

    public Setting() {
    }

    public Setting(int _id, String _setting, int _intvalue, String _textvalue) {
        this._id = _id;
        this._setting = _setting;
        this._intvalue = _intvalue;
        this._textvalue = _textvalue;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_setting() {
        return _setting;
    }

    public void set_setting(String _setting) {
        this._setting = _setting;
    }

    public int get_intvalue() {
        return _intvalue;
    }

    public void set_intvalue(int _intvalue) {
        this._intvalue = _intvalue;
    }

    public String get_textvalue() {
        return _textvalue;
    }

    public void set_textvalue(String _textvalue) {
        this._textvalue = _textvalue;
    }
}
