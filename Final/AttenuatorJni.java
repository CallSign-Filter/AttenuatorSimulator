package com.brandonhessler.Attenuator;

/**
 * Created by Cpl Hess on 4/15/2017.
 */
public class AttenuatorJni {

    public native boolean connect(int box1Port, int box2Port);
    public native boolean start(int runNumber);
    public native boolean stop();
    public native boolean pause();
    public native boolean box1OnOff(boolean turnOn);
    public native boolean box1StartVal(int startVal);
    public native boolean box2OnOff(boolean turnOn);
    public native boolean box2StartVal(int startVal);
    public native boolean setScanRate(int attenIncrement, int period, int boxNumber);

    //the values returned will be in the form of (int box1AttenuationValue,
    // int box2AttenuationValue, boolean box1On, boolean box2On)
    private native String[] getAttenuationValues();

    //the status returned will be in the form of (boolean runInProgress, boolean isPaused)
    public native String[] getStatus();

    //The data returned will be in the form of (float box1Power, float box2Power,
    // int box1AttenuationCurrentValue, int box2AttenuationCurrentValue
    private native String[] getData();

    static {
        System.loadLibrary("AttenuatorJniWrapper"); // Load native library at runtime
    }

    public AttenuatorJni() {
    }

    /**
     * This will use the JNI to get the data and will convert them into the correct data types
     * @return The string of all of the values
     * float box1Power,
     * float box2Power,
     * int box1CurrentAttenuationValue,
     * int box2CurrentAttenuationValue
     */
    public String getDataMessage() {
        return new AttenuationPower(new AttenuatorJni().getData()).makeString();
    }

    public String getAttenuationValueMessage() {
        return new AttenuationValue(new AttenuatorJni().getAttenuationValues()).makeString();
    }

    public String getStatusMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = getStatus();
        boolean[] bools = new boolean[2];
        bools[0] = stringToBoolean(strings[0]);
        bools[1] = stringToBoolean(strings[1]);

        stringBuilder.append("The Run is currently ");
        if (!bools[0])
            stringBuilder.append("Not ");
        stringBuilder.append("Running");
        stringBuilder.append("\n");
        stringBuilder.append("And is currently ");
        if (!bools[1])
            stringBuilder.append("Not ");
        stringBuilder.append("Paused");
            return stringBuilder.toString();
    }

    private boolean stringToBoolean(String str) {
        return str.equalsIgnoreCase("true");
    }

}
