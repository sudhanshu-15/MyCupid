package me.ssiddh.mycupid.data.model;

import java.util.List;

//Data model which contains a list of matches
public class Data {
    public List<MatchPerson> data;

    public Data(List<MatchPerson> data) {
        this.data = data;
    }

    public List<MatchPerson> getData() {
        return data;
    }

    public void setData(List<MatchPerson> data) {
        this.data = data;
    }
}
