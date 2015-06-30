package com.example.hello;
//package com.signal360.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by wainetam on 6/30/15.
 */
public class SignalPG extends CordovaPlugin {

    // Signal methods
    private static final String INITIALIZE="initialize";
    private static final String START="start";
    private static final String STOP="stop";
    private static final String IS_BLUETOOTH_ENABLED="isBluetoothEnabled";
    private static final String IS_ADVERTISING_IDENTIFIER_ENABLED="isAdvertisingIdentifierEnabled";
    private static final String SET_CUSTOMER_IDENTIFIER="setCustomerIdentifier";
    private static final String RESET="reset";
    private static final String CHECK_CONFIG="checkConfig";
    private static final String GET_ACTIVATIONS_WITH_CODEHEARD="getActivationsWithCodeHeard";
    private static final String ALL_ACTIVE_CONTENT="allActiveContent";
    private static final String DID_HEAR_CODE_CB_="_nativeDidHearCodeCB";
    private static final String DID_RECEIVE_ACTIVATIONS_CB="_nativeDidReceiveActivationsCB";
    private static final String DID_STATUS_CHANGE_CB="_nativeDidStatusChangeCB";
    private static final String DID_GEOFENCE_ENTERED_CB="_nativeDidGeoFenceEnteredCB";
    private static final String DID_GEOFENCE_EXITED_CB="_nativeDidGeoFenceExitedCB";
    private static final String DID_GEOFENCES_UPDATED_CB="_nativeDidGeoFencesUpdatedCB";
    private static final String DID_COMPLETE_REGISTRATION_CB="_nativeDidCompleteRegistrationCB";
    private static final String DID_UPDATE_CONFIGURATION_CB="_nativeDidUpdateConfigurationCB";
    private static final String GET_TAGS_FOR_CODE_CB="_nativeGetTagsForCodeCB";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        try {
            if (INSTALL.equals(function)) {
                helpshift = new Helpshift(cordova.getActivity());
                String apiKey = arguments.getString(0);
                String domainName = arguments.getString(1);
                String appID = arguments.getString(2);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("sdkType", "phonegap");

                if (arguments.length() >= 4) {
                    JSONObject object = arguments.getJSONObject(3);
                    Iterator iter = object.keys();
                    while (iter.hasNext()) {
                        String key = (String) iter.next();
                        Boolean value = object.getBoolean(key);
                        map.put(key, value);
                    }
                }
                helpshift.install(cordova.getActivity(), apiKey, domainName, appID, map);
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ""));
                return true;

            }
        }
    }
}
