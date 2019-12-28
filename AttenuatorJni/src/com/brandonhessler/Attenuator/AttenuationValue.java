package com.brandonhessler.Attenuator;

/**
 * Created by Cpl Hess on 4/15/2017.
 */
public class AttenuationValue {
    private static final String BOX_1_ATTN = "Box 1 Attenuation";
    private static final String BOX_2_ATTN = "Box 2 Attenuation";

    private int box1Attn;
    private int box2Attn;
    private boolean box1On;
    private boolean box2On;

    /**
     * This will get the objects that are retrieved from JNI and then convert them to their
     * respective data types
     * int box1AttenValue,
     * int box2AttenValue,
     * boolean box1On,
     * boolean box2On
     */
    public AttenuationValue(String[] strings) {

         box1Attn = Integer.parseInt(strings[0]);
         box1On = Boolean.parseBoolean(strings[1]);
         box2Attn = Integer.parseInt(strings[2]);
         box2On = Boolean.parseBoolean(strings[3]);
    }

    public String makeString(){
        StringBuilder sb = new StringBuilder();
        sb.append(BOX_1_ATTN)
                .append(" is ")
                .append(Integer.toString(getBox1Attn()))
                .append(" and")
                .append(turnBoolToString(isBox1On()))
                .append("\n");
        sb.append(BOX_2_ATTN)
                .append(" is ")
                .append(Integer.toString(getBox2Attn()))
                .append(" and")
                .append(turnBoolToString(isBox2On()))
                .append("\n");
        return sb.toString();
    }

    private String turnBoolToString(boolean on) {
        String str;
        if (on)
            str = " is currently on";
        else
            str = " is currently off";
        return str;
    }

    public int getBox1Attn() {
        return box1Attn;
    }

    public int getBox2Attn() {
        return box2Attn;
    }

    public boolean isBox1On() {
        return box1On;
    }

    public boolean isBox2On() {
        return box2On;
    }

}
