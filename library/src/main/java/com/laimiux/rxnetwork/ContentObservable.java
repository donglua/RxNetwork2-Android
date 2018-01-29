package com.laimiux.rxnetwork;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

final class ContentObservable {
    private ContentObservable() {
        throw new AssertionError("No instances");
    }

    /**
     * Create Observable that wraps BroadcastReceiver and emits received intents.
     *
     * @param filter Selects the Intent broadcasts to be received.
     */
    public static Observable<Intent> fromObservableBroadcast(Context context, IntentFilter filter) {
        return Observable.create(
                new ObservableBroadcastRegister(context, filter, null, null));
    }

    public static Flowable<Intent> fromFlowableBroadcast(Context context, IntentFilter filter) {
        return Flowable.create(
                new FlowableBroadcastRegister(context, filter, null, null),
                BackpressureStrategy.LATEST);
    }
}