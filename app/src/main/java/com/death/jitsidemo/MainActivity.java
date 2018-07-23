package com.death.jitsidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.calendarevents.CalendarEventsPackage;
import com.facebook.react.bridge.UiThreadUtil;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetView;
import org.jitsi.meet.sdk.JitsiMeetViewListener;
import org.jitsi.meet.sdk.invite.AddPeopleController;
import org.jitsi.meet.sdk.invite.AddPeopleControllerListener;
import org.jitsi.meet.sdk.invite.InviteController;
import org.jitsi.meet.sdk.invite.InviteControllerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements JitsiMeetViewListener {
    private JitsiMeetView view;

//    @Override
//    public void onBackPressed() {
//        if (!JitsiMeetView.onBackPressed()) {
//            // Invoke the default handler if it wasn't handled by React.
//            super.onBackPressed();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new JitsiMeetView(this);
        Bundle config = new Bundle();
        config.putBoolean("startWithAudioMuted", false);
        config.putBoolean("startWithVideoMuted", true);
        config.putBoolean("startAudioOnly", true);
        Bundle urlObject = new Bundle();
        urlObject.putBundle("config", config);
        urlObject.putString("url", "https://meet.jit.si/k2k2");
        view.loadURLObject(urlObject);

        view.loadURLString("https://meet.jit.si/k2k2");
        setContentView(view);
        view.setListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        view.dispose();
        view = null;

        JitsiMeetView.onHostDestroy(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        JitsiMeetView.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        JitsiMeetView.onHostResume(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        JitsiMeetView.onHostPause(this);
    }

    @Override
    public void onConferenceFailed(Map<String, Object> map) {
        Log.e("Conference","Failed");
    }

    @Override
    public void onConferenceJoined(Map<String, Object> map) {
        Log.e("Conference","Joined");
    }

    @Override
    public void onConferenceLeft(Map<String, Object> map) {
        Log.e("Conference","Left");
    }

    @Override
    public void onConferenceWillJoin(Map<String, Object> map) {
        Log.e("Conference","Will Join");
    }

    @Override
    public void onConferenceWillLeave(Map<String, Object> map) {
        Log.e("Conference","Will Leave");
    }

    @Override
    public void onLoadConfigError(Map<String, Object> map) {
        Log.e("Configure","Error");
    }
}
