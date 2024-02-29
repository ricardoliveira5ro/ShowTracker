package com.example.showtracker

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object TVShowSerializer : Serializer<ShowItem> {
    override val defaultValue: ShowItem = ShowItem.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ShowItem {
        try {
            return ShowItem.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ShowItem,
        output: OutputStream
    ) = t.writeTo(output)
}

object TVShowsSerializer : Serializer<ProtoShowItems> {
    override val defaultValue: ProtoShowItems = ProtoShowItems.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoShowItems {
        try {
            return ProtoShowItems.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: ProtoShowItems,
        output: OutputStream
    ) = t.writeTo(output)
}