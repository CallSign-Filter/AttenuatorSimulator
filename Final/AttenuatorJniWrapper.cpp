//
// Created by Cpl Hess on 4/15/2017.
//
//#include <stdio.h>
//#include <stdlib.h>
#include "AttenuatorJniWrapper.h"
//#include <jni.h>
//#include "pch.h"
#include "AttenuatorSimulatorVS.h"

static SimulatedAttenuators::Sim library;

	
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_connect(JNIEnv *env, jobject obj, jint port1, jint port2) {
	jboolean jsuccess;
	int cPort1 = port1;
	int cPort2 = port2;
	bool success = library.connect(cPort1, cPort2);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    start
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_start
(JNIEnv *env, jobject obj, jint jrunNumber){
	jboolean jsuccess;
	bool success;
	int runNum = jrunNumber;
	success = library.start(runNum);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    stop
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_stop
(JNIEnv *env, jobject obj){
	jboolean jsuccess;
	bool success = library.stop();
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    pause
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_pause
(JNIEnv *env, jobject obj){
	jboolean jsuccess;
	bool success = library.pause();
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    box1OnOff
* Signature: (Z)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_box1OnOff
(JNIEnv *env, jobject obj, jboolean jBool){
	bool on = jBool;
	jboolean jsuccess;
	bool success = library.box1OnOff(on);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    box1StartVal
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_box1StartVal
(JNIEnv *env, jobject obj, jint jStartVal){
	int startVal = (int)jStartVal;
	jboolean jsuccess;
	bool success = library.box1StartVal(startVal);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    box2OnOff
* Signature: (Z)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_box2OnOff
(JNIEnv *env, jobject obj, jboolean jBool){
	bool on = jBool;
	jboolean jsuccess;
	bool success = library.box2OnOff(on);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    box2StartVal
* Signature: (I)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_box2StartVal
(JNIEnv *env, jobject obj, jint jStartVal){
	int startVal = (int)jStartVal;
	jboolean jsuccess;
	bool success = library.box2StartVal(startVal);
	jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    setScanRate
* Signature: (III)Z
*/
JNIEXPORT jboolean JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_setScanRate
(JNIEnv *env, jobject obj, jint jIncrement, jint jPeriod, jint jBoxNum){
	int increment = (int)jIncrement;
	int period = (int)jPeriod;
	int boxNum = (int)jBoxNum;
	bool success = library.setScanRate(increment, period, boxNum);
	jboolean jsuccess = (jboolean)success;
	return jsuccess;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    getAttenuationValues
* Signature: ()[Ljava/lang/String;
*/
JNIEXPORT jobjectArray JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_getAttenuationValues
(JNIEnv *env, jobject obj){
	vector<string> strings = library.getAttenuationValues();

	jobjectArray vals = (jobjectArray)env->
		NewObjectArray(4, env->FindClass("java/lang/String"), env->NewStringUTF(""));

	for (unsigned long i = 0; i < 4; i++) {
		env->SetObjectArrayElement(vals, i, env->NewStringUTF(strings.at(i).c_str()));
	}
	return vals;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    getStatus
* Signature: ()[Z
*/
JNIEXPORT jobjectArray JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_getStatus
(JNIEnv *env, jobject obj){
	vector<string> strings = library.getStatus();

	jobjectArray vals = (jobjectArray)env->
		NewObjectArray(2, env->FindClass("java/lang/String"), env->NewStringUTF(""));

	for (unsigned long i = 0; i < 2; i++) {
		env->SetObjectArrayElement(vals, i, env->NewStringUTF(strings.at(i).c_str()));
	}
	return vals;
}

/*
* Class:     com_brandonhessler_Attenuator_AttenuatorJni
* Method:    getData
* Signature: ()[Ljava/lang/String;
*/
JNIEXPORT jobjectArray JNICALL Java_com_brandonhessler_Attenuator_AttenuatorJni_getData
(JNIEnv *env, jobject obj){
	vector<string> strings = library.getData();

	jobjectArray vals = (jobjectArray)env->
		NewObjectArray(4, env->FindClass("java/lang/String"), env->NewStringUTF(""));

	for (unsigned long i = 0; i < 4; i++) {
		env->SetObjectArrayElement(vals, i, env->NewStringUTF(strings.at(i).c_str()));
	}
	return vals;
}

