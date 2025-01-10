package com.yzq.bugsnagapp

import com.bugsnag.android.Delivery
import com.bugsnag.android.DeliveryParams
import com.bugsnag.android.DeliveryStatus
import com.bugsnag.android.EventPayload
import com.bugsnag.android.Session
import com.yzq.bugsnagapp.data.PayloadData
import com.yzq.bugsnagapp.ext.toCrashInfo
import com.yzq.bugsnagapp.utils.MoshiUtils
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

    override fun deliver(
        payload: EventPayload, deliveryParams: DeliveryParams
    ): DeliveryStatus {


        val eventFile = payload.eventFile

        Logger.it(TAG, "deliver eventFile:${eventFile?.name}")


        kotlin.runCatching {
            val jsonStr = String(payload.trimToSize().toByteArray())

            //把json转成对象
            val payloadData = MoshiUtils.fromJson<PayloadData>(jsonStr)

            //把json转成对象
            val crashInfo = payloadData?.events?.get(0)?.toCrashInfo()

            Logger.it(TAG, "deliver crashInfo:${crashInfo}")

            Logger.jsont(TAG, MoshiUtils.toJson(crashInfo) ?: "")
        }.onFailure {
            it.printStackTrace()
        }


        return DeliveryStatus.UNDELIVERED
    }

}