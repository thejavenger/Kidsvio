package com.leapworld.kidsvio;

public class Score {

    private int _id;
    private String _gamename;
    private int _score;
    private int _allpoints;
    private String _status;


    public Score() {
    }

    public Score(int _id, String _gamename, int _score, int _allpoints, String _status) {
        this._id = _id;
        this._gamename = _gamename;
        this._score = _score;
        this._allpoints = _allpoints;
        this._status = _status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_gamename() {
        return _gamename;
    }

    public void set_gamename(String _gamename) {
        this._gamename = _gamename;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public int get_allpoints() {
        return _allpoints;
    }

    public void set_allpoints(int _allpoints) {
        this._allpoints = _allpoints;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }
}
