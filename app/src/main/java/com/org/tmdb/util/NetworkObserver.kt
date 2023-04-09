package com.org.tmdb.util

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun observe(): Flow<NetworkStatus>
}

enum class NetworkStatus{
    CONNECTED,
    DISCONNECTED,
    DISCONNECTING,
    UNAVAILABLE
}
