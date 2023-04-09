package com.org.tmdb.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkObserverImpl(
    private val context: Context
) : NetworkObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<NetworkStatus> {
        return callbackFlow {
            val networkCallBack = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch {
                        send(NetworkStatus.CONNECTED)
                    }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch {
                        send(NetworkStatus.DISCONNECTING)
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch {
                        send(NetworkStatus.DISCONNECTED)
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch {
                        send(NetworkStatus.UNAVAILABLE)
                    }
                }
            }
            /**
             * Since registerDefaultNetworkCallback doesn't work on api level below 24
             * */
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                val networkChangeFilter = NetworkRequest.Builder().build()
                connectivityManager.registerNetworkCallback(networkChangeFilter, networkCallBack)
            } else {
                connectivityManager.registerDefaultNetworkCallback(networkCallBack)
            }
            /**
             * unregistering in awaitClose so that we don't have unregister the
             * network callback manually later
             * */
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallBack)
            }
        }.distinctUntilChanged()
    }
}