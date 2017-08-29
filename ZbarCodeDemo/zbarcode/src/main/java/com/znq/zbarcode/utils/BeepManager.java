/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.znq.zbarcode.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.znq.zbarcode.R;

/**
 * Desc: 扫描滴滴声
 * Update by znq on 2016/11/8.
 */
public class BeepManager implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private MediaPlayer mMediaPlayer;
    private boolean isComplete = false;

    public BeepManager(Context context) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.qrcode);
        mMediaPlayer.setLooping(false);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
    }

    public void startRing() {
        if (null != mMediaPlayer) {
            mMediaPlayer.start();
        }
    }


    public void releaseRing() {
        if (null != mMediaPlayer) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public boolean isComplete() {
        return isComplete;
    }
     public int getTimeDuration(){
         return mMediaPlayer.getDuration();
     }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (null != mMediaPlayer) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        isComplete = true;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (null != mMediaPlayer) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        isComplete = true;
        return false;
    }
}
