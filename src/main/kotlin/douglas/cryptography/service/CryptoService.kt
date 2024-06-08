package douglas.cryptography.service

import org.jasypt.util.text.StrongTextEncryptor

object CryptoService {

    private val encryptor: StrongTextEncryptor = StrongTextEncryptor()

    init {
        encryptor.setPassword("minha_senha")
    }

    fun encrypt(rawText: String): String {
        return encryptor.encrypt(rawText)
    }

    fun decrypt(encryptedText: String): String {
        return encryptor.decrypt(encryptedText)
    }
}


