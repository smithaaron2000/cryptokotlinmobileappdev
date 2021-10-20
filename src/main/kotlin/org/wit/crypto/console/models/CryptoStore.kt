package org.wit.crypto.console.models

interface CryptoStore {
    fun findAll(): List<CryptoModel>
    fun findOne(id: Long): CryptoModel?
    fun create(crypto: CryptoModel)
    fun update(crypto: CryptoModel)
}