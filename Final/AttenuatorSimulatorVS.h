#pragma once
#include <stdio.h>
#include <string>
#include <vector>
using std::vector;
using std::string;
using std::to_string;

namespace SimulatedAttenuators {
	class Sim {
	public:
		enum Status {
			RUNNING, STOPPED
		};
		Status status = STOPPED;
		bool isPaused = false;
		bool box1On = false;
		bool box2On = false;
		int box1Attn = 0;
		int box2Attn = 0;

		string boolToString(bool);

		bool connect(int, int);

		bool start(int);

		bool stop();

		bool pause();

		bool box1OnOff(bool);

		bool box1StartVal(int);

		bool box2OnOff(bool);

		bool box2StartVal(int);

		bool setScanRate(int, int, int);

		vector<string> getAttenuationValues();

		vector<string> getStatus();

		vector<string> getData();
	};
}
