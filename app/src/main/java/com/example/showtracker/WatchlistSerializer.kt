package com.example.showtracker

import androidx.datastore.core.Serializer
import com.example.showtracker.model.TVShow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object TVShowSerializer : Serializer<TVShow> {
    override val defaultValue: TVShow get() = TVShow(-1, "", "", -1, -1, "", "", null, null, -1f, -1, emptyList(), emptyList(), emptyList())

    override suspend fun readFrom(input: InputStream): TVShow {
        return try {
            Json.decodeFromString(
                deserializer = TVShow.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: TVShow, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = TVShow.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}