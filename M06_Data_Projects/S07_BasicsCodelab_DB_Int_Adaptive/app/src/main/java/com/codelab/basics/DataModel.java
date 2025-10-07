package com.codelab.basics;

// Object DB ... see Room for Android Studio
// https://developer.android.com/training/data-storage/room
public class DataModel {

    private long id;
    private String name_col;
    private String type_col;
    private Integer dex_col;
    private String desc_col;
    private Integer access_col;

    public DataModel() {
        this.setId(0);
        this.setModelName("default modelName");
        this.setType("None");
        this.setDesc("");
        this.setDexNum(0);
        this.setAccess(0);
    }

    public DataModel(long id, String modelName, String type, Integer dexNum,String desc, Integer access) {
        this.setId(id);
        this.setModelName(modelName);
        this.setType(type);
        this.setDesc(desc);
        this.setDexNum(dexNum);
        this.setAccess(access);
    }

    @Override
    public String toString() {
        return
                ", pkmn name ='" + getModelName() + '\'' +
                ", pkmn type =" + getType() + '\'' +
                ", pkmn description =" + getDesc() + '\'' +
                ", pkmn dex number = " + getDexNum() + '\'' +
                ", access count = " + getAccess()
                ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelName() {
        return name_col;
    }

    public void setModelName(String modelName) {
        this.name_col = modelName;
    }

    public String getType() { return type_col; }

    public void setType(String type_col) {this.type_col = type_col; }

    public Integer getDexNum() { return dex_col; }

    public void setDexNum(Integer dex_col) {this.dex_col = dex_col; }

    public String getDesc() { return desc_col; }

    public void setDesc(String desc_col) {this.desc_col = desc_col; }

    public Integer getAccess() {return access_col; }

    public void setAccess(Integer access) {this.access_col = access; }


}
