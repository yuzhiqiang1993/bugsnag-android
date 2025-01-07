package com.yzq.bugsnag_app

import android.util.Log
import com.bugsnag.android.Delivery
import com.bugsnag.android.DeliveryParams
import com.bugsnag.android.DeliveryStatus
import com.bugsnag.android.EventPayload
import com.bugsnag.android.Session


/**
 * @description: 自定义上报处理
 * @author : yuzhiqiang
 */


class CustomerDelivery : Delivery {

    companion object {
        const val TAG = "CustomerDelivery"
    }

    override fun deliver(payload: Session, deliveryParams: DeliveryParams): DeliveryStatus {
        Log.i(TAG, "deliver Session  payload: ${payload},deliveryParams:${deliveryParams}")
        return DeliveryStatus.DELIVERED
    }

    override fun deliver(payload: EventPayload, deliveryParams: DeliveryParams): DeliveryStatus {
        Log.i(
            TAG, "deliver EventPayload payload: ${payload.event},deliveryParams:${deliveryParams}"
        )
        return DeliveryStatus.DELIVERED
    }
}