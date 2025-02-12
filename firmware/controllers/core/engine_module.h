#pragma once

class EngineModule {
public:
	// Called when 'Burn' is invoked
	virtual void onConfigurationChange(engine_configuration_s const * /*previousConfig*/) { }

	// Called approx 20Hz
	virtual void onSlowCallback() { }

	// Called approx 200Hz
	virtual void onFastCallback() { }

	// Called whenever the ignition switch state changes
	virtual void onIgnitionStateChanged(bool /*ignitionOn*/) { }

	// Queried to determine whether this module needs a delayed shutoff, defaults to false
	virtual bool needsDelayedShutoff() { return false; }
};
