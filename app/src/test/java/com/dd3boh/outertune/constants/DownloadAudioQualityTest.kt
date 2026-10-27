/*
 * Copyright (C) 2026 Amorim Music contributors
 *
 * SPDX-License-Identifier: GPL-3.0
 */

package com.dd3boh.outertune.constants

import org.junit.Assert.assertEquals
import org.junit.Test

class DownloadAudioQualityTest {
    @Test
    fun `default follows every playback quality`() {
        AudioQuality.entries.forEach { playback ->
            assertEquals(playback, DownloadAudioQuality.FOLLOW_PLAYBACK.resolve(playback))
        }
    }

    @Test
    fun `all download modes resolve to a valid audio quality`() {
        DownloadAudioQuality.entries.forEach { download ->
            AudioQuality.entries.forEach { playback ->
                assertEquals(true, download.resolve(playback) in AudioQuality.entries)
            }
        }
    }

    @Test
    fun `explicit offline quality is independent of playback`() {
        val cases = mapOf(
            DownloadAudioQuality.MAX to AudioQuality.MAX,
            DownloadAudioQuality.AUTO to AudioQuality.AUTO,
            DownloadAudioQuality.HIGH to AudioQuality.HIGH,
            DownloadAudioQuality.LOW to AudioQuality.LOW,
        )
        cases.forEach { (download, expected) ->
            AudioQuality.entries.forEach { playback ->
                assertEquals(expected, download.resolve(playback))
            }
        }
    }
}
