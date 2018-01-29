package com.laimiux.samples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.laimiux.rxnetwork.RxNetwork;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SampleActivity extends Activity {
  Button sendButton;
  private Disposable sendStateSubscription;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_view);

    sendButton = findViewById(R.id.send_button);

    final Flowable<ButtonState> sendStateStream =
        RxNetwork.flow(this).map(new Function<Boolean, ButtonState>() {
          @Override public ButtonState apply(Boolean hasInternet) {
            if (!hasInternet) {
              return new ButtonState(R.string.not_connected, false);
            }

            return new ButtonState(R.string.send, true);
          }
        });

    sendStateSubscription =
        sendStateStream.observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ButtonState>() {
              @Override
              public void accept(ButtonState buttonState) throws Exception {
                sendButton.setText(buttonState.textId);
                sendButton.setEnabled(buttonState.isEnabled);
              }
            });
  }


  @Override protected void onDestroy() {
    sendStateSubscription.dispose();
    sendStateSubscription = null;

    super.onDestroy();
  }

  static class ButtonState {
    final int textId;
    final boolean isEnabled;

    public ButtonState(int textId, boolean isEnabled) {
      this.textId = textId;
      this.isEnabled = isEnabled;
    }
  }
}
