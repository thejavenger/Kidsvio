package com.leapworld.kidsvio;

public class Stickers {

    int _id;
    int _sticker;
    String _stickername;
    int _rewardid;

    public Stickers() {
    }

    public Stickers(int _id, int _sticker, String _stickername, int _rewardid) {
        this._id = _id;
        this._sticker = _sticker;
        this._stickername = _stickername;
        this._rewardid = _rewardid;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_sticker() {
        return _sticker;
    }

    public void set_sticker(int _sticker) {
        this._sticker = _sticker;
    }

    public String get_stickername() {
        return _stickername;
    }

    public void set_stickername(String _stickername) {
        this._stickername = _stickername;
    }

    public int get_rewardid() {
        return _rewardid;
    }

    public void set_rewardid(int _rewardid) {
        this._rewardid = _rewardid;
    }
}
