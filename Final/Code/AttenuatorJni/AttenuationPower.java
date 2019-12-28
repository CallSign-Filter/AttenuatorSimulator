package com.brandonhessler.Attenuator;

/**
 * Created by Cpl Hess on 4/15/2017.
 */
public class AttenuationPower {
    private static final String BOX_1_ATTN = "Box 1 Attenuation";
    private static final String BOX_2_ATTN = "Box 2 Attenuation";

    private int box1Attn;
    private int box2Attn;
    private float box1Pwr;
    private float box2Pwr;

    /**
     * This will get the objects that are retrieved from JNI and then convert them to their
     * respective data types
     * int box1Attn,
     * int box2Attn,
     * float box1Pwr,
     * float box2Pwr
     */
    public AttenuationPower(String[] strings) {

        box1Attn = Integer.parseInt(strings[0]);
        box2Attn = Integer.parseInt(strings[1]);
        box1Pwr = Float.parseFloat(strings[2]);
        box2Pwr = Float.parseFloat(strings[3]);
    }

    public String makeString(){
        StringBuilder sb = new StringBuilder();
        sb.append(BOX_1_ATTN)
                .append(" is ")
                .append(Integer.toString(getBox1Attn()))
                .append(" at a Power of ")
                .append(Float.toString(getBox1Pwr()))
                .append(" db")
                .append("\n");
        sb.append(BOX_2_ATTN)
                .append(" is ")
                .append(Integer.toString(getBox2Attn()))
                .append(" at a Power of ")
                .append(Float.toString(getBox2Pwr()))
                .append(" db")
                .append("\n");
        return sb.toString();
    }

    public int getBox1Attn() {
        return box1Attn;
    }

    public int getBox2Attn() {
        return box2Attn;
    }

    public float getBox1Pwr() {
        return box1Pwr;
    }

    public float getBox2Pwr() {
        return box2Pwr;
    }
}
