#include "AttenuatorSimulatorVS.h"
#include <iostream>

namespace SimulatedAttenuators {	

	string Sim::boolToString(bool b) {
		return b ? "true" : "false";
	}

	bool Sim::connect(int port1, int port2) {
		status = STOPPED;
		return port1 == 5150 && port2 == 5151;

	}

	bool Sim::start(int runNumber) {
		if (runNumber > 0 && status == STOPPED) {
			status = RUNNING;
			return true;
		}
		else
			return false;

	}

	bool Sim::stop() {
		if (status == RUNNING) {
			status = STOPPED;
			return true;
		}
		else
			return false;
	}

	bool Sim::pause() {
		if (status == RUNNING) {
			isPaused = !isPaused;
			return true;
		}
		else
			return false;
	}

	bool Sim::box1OnOff(bool turnOn) {
		if (turnOn != box1On) {
			box1On = turnOn;
			return true;
		}
		else
			return false;
	}

	bool Sim::box1StartVal(int startVal) {
		if (startVal > 0 && startVal < 256) {
			box1Attn = startVal;
			return true;
		}
		else
			return false;
	}

	bool Sim::box2OnOff(bool turnOn) {
		if (turnOn != box2On) {
			box2On = turnOn;
			return true;
		}
		else
			return false;
	}

	bool Sim::box2StartVal(int startVal) {
		if (startVal > 0 && startVal < 256) {
			box2Attn = startVal;
			return true;
		}
		else
			return false;
	}

	bool Sim::setScanRate(int attenIncrement, int period, int boxNumber) {
		return period > 0 && (boxNumber == 1 || boxNumber == 2);
	}

	vector<string> Sim::getAttenuationValues() {
		vector<string> vals(4);
		vals[0] = to_string(box1Attn);
		vals[1] = boolToString(box1On);
		vals[2] = to_string(box2Attn);
		vals[3] = boolToString(box2On);
		return vals;
	}

	vector<string> Sim::getStatus() {
		vector<string> statusVector(2);
		if (status == RUNNING)
			statusVector[0] = "true";
		else
			statusVector[0] = "false";
		
		if (isPaused)
			statusVector[1] = "true";
		else
			statusVector[1] = "false";

		return statusVector;
	}

	vector<string> Sim::getData() {
		vector<string> vals(4);
		vals[0] = to_string(box1Attn);
		vals[1] = to_string(box2Attn);
		vals[2] = to_string(3.14159f);
		vals[3] = to_string(2.71828f);
		return vals;
	}
}

