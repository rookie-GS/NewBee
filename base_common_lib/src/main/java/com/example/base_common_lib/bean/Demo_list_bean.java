package com.example.base_common_lib.bean;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/19 13:51
 *
 */
public class Demo_list_bean {
    private String name = "";
    private String arouter = "";

    @Override
    public String toString() {
        return "Demo_list_bean{" +
                "name='" + name + '\'' +
                ", arouter='" + arouter + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArouter() {
        return arouter;
    }

    public void setArouter(String arouter) {
        this.arouter = arouter;
    }
}
