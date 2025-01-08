package com.yzq.app

import com.bugsnag.android.Delivery
import com.bugsnag.android.DeliveryParams
import com.bugsnag.android.DeliveryStatus
import com.bugsnag.android.EventPayload
import com.bugsnag.android.Session
import com.yzq.logger.Logger


/**
 * @description: 自定义上报处理
 * @author : yuzhiqiang
 */


class CustomerDelivery : Delivery {

    companion object {
        const val TAG = "CustomerDelivery"
    }

    override fun deliver(payload: Session, deliveryParams: DeliveryParams): DeliveryStatus {
        Logger.it(TAG, "deliver Session")
        val jsonBytes = payload.toByteArray()
        //转成json
        val json = String(jsonBytes)
        Logger.jsont(
            TAG, json
        )


        return DeliveryStatus.DELIVERED
    }

    override fun deliver(payload: EventPayload, deliveryParams: DeliveryParams): DeliveryStatus {
        val jsonBytes = payload.trimToSize().toByteArray()

        //转成json
        val json = String(jsonBytes)

        Logger.jsont(
            TAG, json
        )

        return DeliveryStatus.DELIVERED
    }
}